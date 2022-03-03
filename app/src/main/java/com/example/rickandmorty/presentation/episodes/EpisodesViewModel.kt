package com.example.rickandmorty.presentation.episodes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.domain.usecase.GetEpisodesUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class EpisodesViewModel(private val getEpisodesUseCase: GetEpisodesUseCase) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val _uiState: MutableLiveData<EpisodesUiState> = MutableLiveData(EpisodesUiState())
    val uiState: LiveData<EpisodesUiState> = _uiState

    private var isEpisodesLoading = false

    fun loadEpisodes(episodesIds: List<Int>){
        if (isEpisodesLoading){
            return
        }
        isEpisodesLoading = true
        _uiState.value = _uiState.value?.copy(isLoading = true, isError = false, episodes = null)
        compositeDisposable.add(
            getEpisodesUseCase(episodesIds)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate {
                    isEpisodesLoading = false
                }
                .subscribe({
                    _uiState.value = _uiState.value?.copy(isLoading = false, isError = false, episodes = it)
                },{
                    _uiState.value = _uiState.value?.copy(isLoading = false, isError = true, episodes = null)
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}