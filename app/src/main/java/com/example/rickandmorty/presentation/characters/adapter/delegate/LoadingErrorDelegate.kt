package com.example.rickandmorty.presentation.characters.adapter.delegate

import com.example.rickandmorty.databinding.CharactersErrorItemBinding
import com.example.rickandmorty.presentation.characters.adapter.item.LoadingErrorItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun loadingErrorDelegate(repeatClicked: () -> Unit) = adapterDelegateViewBinding<LoadingErrorItem, Any, CharactersErrorItemBinding>(
    viewBinding = {layoutInflater, parent ->  CharactersErrorItemBinding.inflate(layoutInflater, parent, false)},
    on = {item, items, position -> item is LoadingErrorItem },
    block = {
        binding.charactersRepeatButton.setOnClickListener {
            repeatClicked()
        }
    }
)