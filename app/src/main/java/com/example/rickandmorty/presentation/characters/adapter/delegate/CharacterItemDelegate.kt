package com.example.rickandmorty.presentation.characters.adapter.delegate

import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.CharactersListItemBinding
import com.example.rickandmorty.presentation.characters.adapter.item.CharacterItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun characterItemDelegate(characterItemClicked: (CharacterItem) -> Unit) = adapterDelegateViewBinding<CharacterItem, Any, CharactersListItemBinding>(
    viewBinding = {layoutInflater, parent -> CharactersListItemBinding.inflate(layoutInflater, parent, false) },
    on = {item, items, position -> item is CharacterItem },
    block = {
        this.binding.root.setOnClickListener {
            characterItemClicked(item)
        }
        bind {
            Glide.with(binding.charactersItemImageView)
                .load(item.imageUrl)
                .circleCrop()
                .placeholder(R.color.gray_light)
                .into(binding.charactersItemImageView)
                .waitForLayout()
            binding.charactersItemTextView.text = item.name
        }
    }
)