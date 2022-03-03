package com.example.rickandmorty.presentation.details

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.rickandmorty.App
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentDetailsBinding
import com.example.rickandmorty.presentation.MainActivity
import com.example.rickandmorty.presentation.Navigator

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() = _binding!!

    private lateinit var viewModel: DetailsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentDetailsBinding.bind(view)

        viewModel = ViewModelProvider(
            this,
            ((activity as MainActivity).application as App).appComponent.getDetailsViewModelFactory()
        ).get(DetailsViewModel::class.java)

        initUi()
        setUpObservers()

        if(savedInstanceState == null){
            arguments?.getInt(DETAILS_ARG_KEY)?.let { viewModel.loadDetails(it) }
        }
    }

    override fun onResume() {
        super.onResume()
        this.view?.requestFocus()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initUi(){
        binding.detailsToolbar.setNavigationOnClickListener {
            (activity as Navigator).navigateBack()
        }

        binding.detailsError.detailsRepeatButton.setOnClickListener {
            arguments?.getInt(DETAILS_ARG_KEY)?.let { viewModel.loadDetails(it) }
        }
    }

    private fun setUpObservers(){
        viewModel.uiState.observe(this.viewLifecycleOwner){
            binding.detailsError.detailsErrorBlock.isVisible = it.isError
            binding.detailsProgressBar.isVisible = it.isLoading
            it.userInfo?.let { character ->
                Glide.with(binding.detailsImageView)
                    .load(character.imageUrl)
                    .transform(RoundedCorners(resources.getDimensionPixelOffset(R.dimen.details_avatar_corner_radius)))
                    .placeholder(R.color.gray_light)
                    .into(binding.detailsImageView)
                binding.detailsBio.detailsNameTextView.text = character.name
                binding.detailsBio.detailStatusSpeciesTextView.text = getString(R.string.details_status_species, character.status, character.species)
                val html = getString(R.string.details_location, character.location.name)
                binding.detailsBio.detailsLocationTextView.text = HtmlCompat.fromHtml(html, FROM_HTML_MODE_COMPACT)
            }
        }
    }

    companion object{
        private const val DETAILS_ARG_KEY = "details_arguments_key"

        fun newInstance(characterId: Int): DetailsFragment{
            val arguments = Bundle()
            arguments.putInt(DETAILS_ARG_KEY, characterId)
            val fragment = DetailsFragment()
            fragment.arguments = arguments
            return fragment
        }
    }
}