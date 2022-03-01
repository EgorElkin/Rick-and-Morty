package com.example.rickandmorty.presentation.characters.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.presentation.characters.CharactersUiState
import com.example.rickandmorty.presentation.characters.adapter.delegate.characterItemDelegate
import com.example.rickandmorty.presentation.characters.adapter.delegate.loadingDelegate
import com.example.rickandmorty.presentation.characters.adapter.delegate.loadingErrorDelegate
import com.example.rickandmorty.presentation.characters.adapter.item.CharacterItem
import com.example.rickandmorty.presentation.characters.adapter.item.LoadingErrorItem
import com.example.rickandmorty.presentation.characters.adapter.item.LoadingItem
import com.example.rickandmorty.presentation.characters.pagination.PaginationAdapterHelper
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class CharactersAdapter(
    private val paginationAdapterHelper: PaginationAdapterHelper,
    characterItemClicked: (CharacterItem) -> Unit,
    repeatLoadingClicked: () -> Unit
) : AsyncListDifferDelegationAdapter<Any>(
    diffItemCallback(),
    characterItemDelegate(characterItemClicked),
    loadingDelegate(),
    loadingErrorDelegate(repeatLoadingClicked)
) {

    fun setUi(uiState: CharactersUiState) {
        items = when {
            uiState.isLoading -> {
                uiState.characters + listOf(LoadingItem)
            }
            uiState.isRepeatAvailable -> {
                uiState.characters + listOf(LoadingErrorItem)
            }
            else -> {
                uiState.characters
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        paginationAdapterHelper.onBind(position, itemCount)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any?>) {
        super.onBindViewHolder(holder, position, payloads)
        paginationAdapterHelper.onBind(position, itemCount)
    }
}

fun diffItemCallback() = object : DiffUtil.ItemCallback<Any>() {
    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return true
    }
}