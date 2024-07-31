package com.example.lab_ta.presentations.fragments.profile.presentation


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.lab_ta.R
import com.example.lab_ta.base.BaseFragment
import com.example.lab_ta.common.constants.AppConstants.DataStore.USER_NAME
import com.example.lab_ta.common.extention.launchAndRepeatWithViewLifecycle
import com.example.lab_ta.common.viewmodel.DataStoreViewModel
import com.example.lab_ta.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    private val profileViewModel: ProfileViewModel by viewModels()
    private val dataStoreViewModel: DataStoreViewModel by viewModels()


    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentProfileBinding = DataBindingUtil.inflate(
        layoutInflater, R.layout.fragment_profile, container, false
    )

    override fun init() {

        binding.apply {
            viewModel = profileViewModel
        }

        launchAndRepeatWithViewLifecycle {
            launch {
                dataStoreViewModel.getStringValue(USER_NAME).collectLatest {
                    it?.let {
                        profileViewModel.getUserInfo(it)
                    }
                }

            }

            launch {
                profileViewModel.uiAction.collectLatest {
                    when (it) {
                        ProfileUiActions.NavigateBack -> findNavController().popBackStack()
                    }
                }
            }

            launch {
                profileViewModel.userInfo.collectLatest {
                    binding.user = it
                }
            }

        }

    }
}