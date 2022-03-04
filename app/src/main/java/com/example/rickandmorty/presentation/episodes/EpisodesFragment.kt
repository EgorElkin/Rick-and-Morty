package com.example.rickandmorty.presentation.episodes

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.App
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentEpisodesBinding
import com.example.rickandmorty.presentation.MainActivity
import com.example.rickandmorty.presentation.Navigator
import com.example.rickandmorty.presentation.episodes.adapter.EpisodesAdapter
import java.util.*

class EpisodesFragment : Fragment(R.layout.fragment_episodes) {

    private var _binding: FragmentEpisodesBinding? = null
    private val binding: FragmentEpisodesBinding
        get() = _binding!!

    private val episodesAdapter: EpisodesAdapter = EpisodesAdapter()
    private lateinit var viewModel: EpisodesViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentEpisodesBinding.bind(view)
        viewModel = ViewModelProvider(
            this,
            ((activity as MainActivity).application as App).appComponent.getEpisodesViewModelFactory()
        ).get(EpisodesViewModel::class.java)

        initUi()
        setOuObservables()

        if(savedInstanceState == null){
            arguments?.getIntegerArrayList(EPISODES_ARG_KEY)?.toList()?.let {
                viewModel.loadEpisodes(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initUi(){
        binding.episodesToolbar.setNavigationOnClickListener {
            (activity as Navigator).navigateBack()
        }
        binding.episodesError.repeatButton.setOnClickListener {
            arguments?.getIntegerArrayList(EPISODES_ARG_KEY)?.toList()?.let {
                viewModel.loadEpisodes(it)
            }
        }
        binding.episodesRecyclerView.adapter = episodesAdapter
    }

    private fun setOuObservables(){
        viewModel.uiState.observe(this.viewLifecycleOwner){
            binding.episodesProgressBar.isVisible = it.isLoading
            binding.episodesError.errorBlock.isVisible = it.isError
            it.episodes?.let { episodes ->
                episodesAdapter.setEpisodes(episodes)
            }
        }

        viewModel.uiEvent.observe(this.viewLifecycleOwner){ event ->
            event.errorRes?.let{
                showError(it)
            }
        }
    }

    private fun showError(errorRes: Int){
        val toast = Toast.makeText(requireContext(), getString(errorRes), Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
        viewModel.errorShown()
    }

    companion object{
        private const val EPISODES_ARG_KEY = "episodes_arguments_key"

        fun newInstance(episodesIds: List<Int>): EpisodesFragment {
            val arguments = Bundle()
            arguments.putIntegerArrayList(EPISODES_ARG_KEY, (episodesIds as ArrayList<Int>))
            val fragment = EpisodesFragment()
            fragment.arguments = arguments
            return fragment
        }
    }
}