package com.example.rickandmorty.presentation.episodes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.entity.Episode
import com.example.rickandmorty.domain.usecase.GetEpisodesUseCase
import io.reactivex.Notification
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class EpisodesViewModel(private val getEpisodesUseCase: GetEpisodesUseCase) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val _uiState: MutableLiveData<EpisodesUiState> = MutableLiveData(EpisodesUiState())
    val uiState: LiveData<EpisodesUiState> = _uiState

    private val _uiEvent: MutableLiveData<EpisodesUiEvents> = MutableLiveData(EpisodesUiEvents())
    val uiEvent: LiveData<EpisodesUiEvents> = _uiEvent

    private var isEpisodesLoading = false
    private var events = 0
    private val episodesList = mutableListOf<Episode>()

    @Suppress("UnstableApiUsage")
    fun loadEpisodes(episodesIds: List<Int>){
        if (isEpisodesLoading){
            return
        }
        isEpisodesLoading = true
        _uiState.value = _uiState.value?.copy(isLoading = true, isError = false, episodes = null)
        compositeDisposable.add(
            getEpisodesUseCase(episodesIds)
                .subscribeOn(Schedulers.io())
                .materialize()
                .observeOn(AndroidSchedulers.mainThread())
                .dematerialize {
                    when {
                        it.isOnError -> {
                            Notification.createOnError(it.error ?: Throwable())
                        }
                        it.isOnNext -> {
                            Notification.createOnNext(it.value!!)
                        }
                        else -> {
                            Notification.createOnComplete()
                        }
                    }
                }
                .doOnTerminate {
                    isEpisodesLoading = false
                }
                .doOnComplete { events = 0 }
                .subscribe({
                    if(it.isNotEmpty()){
                        events++
                        episodesList.clear()
                        episodesList.addAll(it)
                        _uiState.value = _uiState.value?.copy(isLoading = false, isError = false, episodes = it)
                    }
                },{
                    when(events){
                        0 -> { _uiState.value = _uiState.value?.copy(isLoading = false, isError = true, episodes = null) }
                        1 -> { _uiEvent.value = _uiEvent.value?.copy(errorRes = R.string.episodes_network_error) }
                    }
                })
        )
    }

    fun errorShown(){
        _uiEvent.value = _uiEvent.value?.copy(errorRes = null)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}