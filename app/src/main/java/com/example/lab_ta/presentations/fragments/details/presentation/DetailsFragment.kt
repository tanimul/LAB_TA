package com.example.lab_ta.presentations.fragments.details.presentation


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.lab_ta.R
import com.example.lab_ta.base.BaseFragment
import com.example.lab_ta.common.extention.launchAndRepeatWithViewLifecycle
import com.example.lab_ta.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {

    private val detailsViewModel: DetailsViewModel by viewModels()
    private val args: DetailsFragmentArgs by navArgs()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentDetailsBinding = DataBindingUtil.inflate(
        layoutInflater, R.layout.fragment_details, container, false
    )

    override fun init() {

        binding.apply {
            url = args.url
            viewModel = detailsViewModel
        }

        launchAndRepeatWithViewLifecycle {
            launch {
                detailsViewModel.uiAction.collectLatest {
                    when (it) {
                        DetailsUiActions.NavigateBack -> findNavController().popBackStack()
                    }
                }
            }

        }

    }


}