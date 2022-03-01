package com.example.rickandmorty.presentation.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.usecase.GetCharactersUseCase
import com.example.rickandmorty.presentation.characters.adapter.item.CharacterItem
import com.example.rickandmorty.presentation.characters.adapter.item.CharactersToItemsMapper
import com.example.rickandmorty.presentation.characters.pagination.Paginator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CharactersViewModel(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val paginator: Paginator
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val _uiState: MutableLiveData<CharactersUiState> = MutableLiveData(CharactersUiState())
    val uiState: LiveData<CharactersUiState> = _uiState

    private val _uiEvent: MutableLiveData<CharactersUiEvents> = MutableLiveData()
    val uiEvent: LiveData<CharactersUiEvents> = _uiEvent

    private val usersList = mutableListOf<CharacterItem>()
    private var isNextPageLoading = false
    private var isError = false

    fun loadNextPage() {
        if (isNextPageLoading || isError){
            return
        }
        isNextPageLoading = true
        _uiState.value = _uiState.value?.copy(isLoading = true, isRepeatAvailable = false)
        compositeDisposable.add(
            getCharactersUseCase(paginator.currentPage)
                .subscribeOn(Schedulers.io())
                .map(CharactersToItemsMapper())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate {
                    isNextPageLoading = false
                }
                .doOnComplete {
                    if (paginator.isFirstPage)
                        paginator.pageLoaded()
                }
                .subscribe({
                    isError = false
                    if(paginator.isFirstPage){
                        usersList.clear()
                    } else {
                        paginator.pageLoaded()
                    }
                    usersList.addAll(it)
                    _uiState.value = _uiState.value?.copy(isLoading = false, isRepeatAvailable = false, characters = usersList)
                },{
                    isError = true
                    _uiState.value = _uiState.value?.copy(isLoading = false, isRepeatAvailable = true)
                    _uiEvent.value = CharactersUiEvents(R.string.characters_loading_error)
                })
        )
    }

    fun loadFirstPage(){
        paginator.reset()
        isNextPageLoading = false
        isError = false
        loadNextPage()
    }

    fun repeat(){
        isError = false
        loadNextPage()
    }

    fun eventHandled(){
        _uiEvent.value = _uiEvent.value?.copy(errorMessageRes = null)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}