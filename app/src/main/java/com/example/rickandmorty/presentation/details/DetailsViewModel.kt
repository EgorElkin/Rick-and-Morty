package com.example.rickandmorty.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.domain.entity.Character
import com.example.rickandmorty.domain.usecase.GetDetailsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailsViewModel(private val getDetailsUseCase: GetDetailsUseCase) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val _uiState: MutableLiveData<DetailsUiState> = MutableLiveData(DetailsUiState())
    val uiState: LiveData<DetailsUiState> = _uiState

    private val _uiEvent: MutableLiveData<DetailsUiEvents> = MutableLiveData(DetailsUiEvents())
    val uiEvent: LiveData<DetailsUiEvents> = _uiEvent

    private var isDetailsLoading = false
    private var currentCharacter: Character? = null

    fun loadDetails(characterId: Int){
        if (isDetailsLoading){
            return
        }
        isDetailsLoading = true
        _uiState.value = _uiState.value?.copy(isLoading = true, isError = false, userInfo = null)
        compositeDisposable.add(
            getDetailsUseCase(characterId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate {
                    isDetailsLoading = false
                }
                .subscribe({
                    currentCharacter = it
                    _uiState.value = _uiState.value?.copy(isLoading = false, isError = false, userInfo = it)
                },{
                    _uiState.value = _uiState.value?.copy(isLoading = false, isError = true, userInfo = null)
                })
        )
    }

    fun showEpisodes(){
        val episodesList = currentCharacter?.episodes?.map { episodeUrl ->
            episodeUrl.replace(Regex("[^0-9]"), "").toInt()
        }
        _uiEvent.value = _uiEvent.value?.copy(navigateToEpisodes = episodesList)
    }

    fun eventHandled(){
        _uiEvent.value = _uiEvent.value?.copy(navigateToEpisodes = null)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}