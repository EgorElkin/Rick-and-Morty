package com.example.rickandmorty.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.domain.usecase.GetDetailsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailsViewModel(private val getDetailsUseCase: GetDetailsUseCase) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val _uiState: MutableLiveData<DetailsUiState> = MutableLiveData(DetailsUiState())
    val uiState: LiveData<DetailsUiState> = _uiState


    private var isNextPageLoading = false

    fun loadDetails(characterId: Int){
        if (isNextPageLoading){
            return
        }
        isNextPageLoading = true
        _uiState.value = _uiState.value?.copy(isLoading = true, isError = false, userInfo = null)
        compositeDisposable.add(
            getDetailsUseCase(characterId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate {
                    isNextPageLoading = false
                }
                .subscribe({
                    println("debug: onNext: ${it.name}")
                    _uiState.value = _uiState.value?.copy(isLoading = false, isError = false, userInfo = it)
                },{
                    println("debug: onError: $it")
                    _uiState.value = _uiState.value?.copy(isLoading = false, isError = true, userInfo = null)
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}