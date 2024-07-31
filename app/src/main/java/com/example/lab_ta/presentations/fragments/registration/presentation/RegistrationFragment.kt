package com.example.lab_ta.presentations.fragments.registration.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.lab_ta.R
import com.example.lab_ta.base.BaseFragment
import com.example.lab_ta.common.constants.AppConstants
import com.example.lab_ta.common.domain.model.User
import com.example.lab_ta.common.extention.launchAndRepeatWithViewLifecycle
import com.example.lab_ta.common.viewmodel.DataStoreViewModel
import com.example.lab_ta.databinding.FragmentRegistrationBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegistrationFragment : BaseFragment<FragmentRegistrationBinding>() {

    private val registrationViewModel: RegistrationViewModel by viewModels()
    private val dataStoreViewModel: DataStoreViewModel by viewModels()


    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentRegistrationBinding = DataBindingUtil.inflate(
        layoutInflater, R.layout.fragment_registration, container, false
    )

    override fun init() {

        binding.apply {
            viewModel = registrationViewModel
        }

        launchAndRepeatWithViewLifecycle {
            launch {
                registrationViewModel.uiAction.collectLatest {
                    when (it) {
                        RegistrationUiActions.NavigateBack -> findNavController().popBackStack()
                        RegistrationUiActions.OnRegistration -> addUser()
                    }
                }
            }
        }
    }

    private fun addUser() {
        registrationViewModel.addUser(
            user = User(
                name = binding.etName.text.toString(),
                userName = binding.etUserName.text.toString(),
                password = binding.etPassword.text.toString(),
                phoneNumber = binding.etPhone.text.toString()
            )
        )

        dataStoreViewModel.saveToDataStore(
            AppConstants.DataStore.USER_NAME,
            binding.etUserName.text.toString()
        )

        findNavController().navigate(R.id.action_registrationFragment_to_dashboardFragment)

    }

}