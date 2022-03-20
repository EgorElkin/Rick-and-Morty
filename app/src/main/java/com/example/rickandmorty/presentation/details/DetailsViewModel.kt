package com.example.rickandmorty.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.entity.Character
import com.example.rickandmorty.domain.usecase.GetDetailsUseCase
import io.reactivex.Notification
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailsViewModel(
    private val getDetailsUseCase: GetDetailsUseCase,
    private var onEpisodesClicked: ((List<Int>) -> Unit)?,
    private var onBackClicked: (() -> Unit)?
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val _uiState: MutableLiveData<DetailsUiState> = MutableLiveData(DetailsUiState())
    val uiState: LiveData<DetailsUiState> = _uiState

    private val _uiEvent: MutableLiveData<DetailsUiEvents> = MutableLiveData(DetailsUiEvents())
    val uiEvent: LiveData<DetailsUiEvents> = _uiEvent

    private var isDetailsLoading = false
    private var currentCharacter: Character? = null
    private var events = 0

    @Suppress("UnstableApiUsage")
    fun loadDetails(characterId: Int){
        if (isDetailsLoading){
            return
        }
        isDetailsLoading = true
        _uiState.value = _uiState.value?.copy(isLoading = true, isError = false, userInfo = null)
        compositeDisposable.add(
            getDetailsUseCase(characterId)
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
                    isDetailsLoading = false
                }
                .subscribe({
                    if(it.id != 0){
                        events++
                        currentCharacter = it
                        _uiState.value = _uiState.value?.copy(isLoading = false, isError = false, userInfo = it)
                    } else {
                        currentCharacter = null
                    }
                },{
                    when(events){
                        0 -> { _uiState.value = _uiState.value?.copy(isLoading = false, isError = true, userInfo = null) }
                        1 -> { _uiEvent.value = _uiEvent.value?.copy(errorRes = R.string.characters_network_error) }
                    }
                },{
                    events = 0
                })
        )
    }

    fun errorShown(){
        _uiEvent.value = _uiEvent.value?.copy(errorRes = null)
    }

    fun episodesClicked(){
        val episodesList = currentCharacter?.episodes?.map { episodeUrl ->
            episodeUrl.replace(Regex("[^0-9]"), "").toInt()
        } ?: emptyList()
        onEpisodesClicked?.invoke(episodesList)
    }

    fun backClicked(){
        onBackClicked?.invoke()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
        onEpisodesClicked = null
        onBackClicked = null
    }
}