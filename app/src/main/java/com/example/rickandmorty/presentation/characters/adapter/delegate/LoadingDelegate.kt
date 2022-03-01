package com.example.rickandmorty.presentation.characters.adapter.delegate

import com.example.rickandmorty.databinding.CharactersLoadingItemBinding
import com.example.rickandmorty.presentation.characters.adapter.item.LoadingItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun loadingDelegate() = adapterDelegateViewBinding<LoadingItem, Any, CharactersLoadingItemBinding>(
    viewBinding = {layoutInflater, parent -> CharactersLoadingItemBinding.inflate(layoutInflater, parent, false) },
    on = {item, items, position -> item is LoadingItem },
    block = {}
)