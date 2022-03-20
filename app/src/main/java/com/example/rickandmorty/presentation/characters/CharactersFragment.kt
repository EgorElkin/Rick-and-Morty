package com.example.rickandmorty.presentation.characters

import android.os.Bundle
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.App
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentCharactersBinding
import com.example.rickandmorty.presentation.MainActivity
import com.example.rickandmorty.presentation.characters.adapter.CharactersAdapter
import com.example.rickandmorty.presentation.characters.adapter.SpaceItemDecoration
import com.example.rickandmorty.presentation.characters.pagination.PaginationAdapterHelper

class CharactersFragment : Fragment(R.layout.fragment_characters) {

    private var _binding: FragmentCharactersBinding? = null
    private val binding: FragmentCharactersBinding
        get() = _binding!!

    private lateinit var charactersAdapter: CharactersAdapter
    private lateinit var viewModel: CharactersViewModel
    private var dySum = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentCharactersBinding.bind(view)

        viewModel = ViewModelProvider(
            this,
            ((activity as MainActivity).application as App).appComponent.getCharactersViewModelFactory()
        ).get(CharactersViewModel::class.java)

        initUi()
        setUpObservers()
        setUpBackButtonBehavior()

        if(savedInstanceState == null){
            viewModel.loadFirstPage()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(LIST_POSITION_KEY, dySum)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        dySum = savedInstanceState?.getInt(LIST_POSITION_KEY) ?: 0
    }

    private fun initUi(){
        charactersAdapter = CharactersAdapter(
            paginationAdapterHelper = PaginationAdapterHelper {
                viewModel.loadNextPage()
            },
            characterItemClicked = {
                viewModel.characterClicked(it.id)
            },
            repeatLoadingClicked = {
                viewModel.repeat()
            }
        )
        binding.charactersRecyclerView.addItemDecoration(SpaceItemDecoration(resources.getDimensionPixelOffset(R.dimen.item_space_size)))
        binding.charactersRecyclerView.adapter = charactersAdapter
        binding.charactersRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                dySum += dy
                if(dySum == 0){
                    this@CharactersFragment.view?.clearFocus()
                } else{
                    this@CharactersFragment.view?.requestFocus()
                }
            }
        })
    }

    private fun setUpBackButtonBehavior(){
        this.view?.setOnKeyListener( object : View.OnKeyListener{
            override fun onKey(p0: View?, keyCode: Int, p2: KeyEvent?): Boolean {
                if(keyCode == KeyEvent.KEYCODE_BACK){
                    binding.charactersRecyclerView.scrollToPosition(0)
                    dySum = 0
                    p0?.clearFocus()
                    return true
                }
                return false
            }
        })
    }

    private fun setUpObservers(){
        viewModel.uiState.observe(this.viewLifecycleOwner){ uiState ->
            charactersAdapter.setUi(uiState)
        }

        viewModel.uiEvent.observe(this.viewLifecycleOwner){ event ->
            event.errorMessageRes?.let {
                showError(it)
            }
        }
    }

    private fun showError(errorRes: Int){
        val toast = Toast.makeText(requireContext(), getString(errorRes), Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
        viewModel.eventHandled()
    }

    companion object{
        private const val LIST_POSITION_KEY = "list_position_shift_key"
    }
}