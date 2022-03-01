package com.example.rickandmorty.presentation.characters.pagination

class PaginationAdapterHelper(
    private val onLoadMoreCallback: () -> Unit
) {

    fun onBind(adapterPosition: Int, totalItemCount: Int){
        if (adapterPosition == totalItemCount - LOAD_MORE_THRESHOLD) {
            onLoadMoreCallback()
        }
    }

    companion object {
        private const val LOAD_MORE_THRESHOLD = 5
    }
}