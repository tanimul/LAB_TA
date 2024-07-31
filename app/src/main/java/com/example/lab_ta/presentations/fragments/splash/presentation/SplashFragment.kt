package com.example.lab_ta.presentations.fragments.splash.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.lab_ta.R
import com.example.lab_ta.base.BaseFragment
import com.example.lab_ta.common.constants.AppConstants
import com.example.lab_ta.common.extention.launchAndRepeatWithViewLifecycle
import com.example.lab_ta.common.viewmodel.DataStoreViewModel
import com.example.lab_ta.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    private val dataStoreViewModel: DataStoreViewModel by viewModels()
    private val splashViewModel: SplashViewModel by viewModels()


    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentSplashBinding = DataBindingUtil.inflate(
        layoutInflater, R.layout.fragment_splash, container, false
    )

    override fun init() {

        launchAndRepeatWithViewLifecycle {
            launch {
                splashViewModel.uiAction.collectLatest {
                    when (it) {
                        SplashUiActions.NavigateBack -> findNavController().popBackStack()
                        SplashUiActions.OnNavigate ->
                            checkLoggedUser()
                    }
                }
            }
        }

    }

    private fun checkLoggedUser() {
        launchAndRepeatWithViewLifecycle {
            launch {
                dataStoreViewModel.getStringValue(AppConstants.DataStore.USER_NAME).collectLatest {
                    val action = if (it == null) {
                        R.id.action_splashFragment_to_loginFragment
                    } else {
                        R.id.action_splashFragment_to_dashboardFragment
                    }
                    findNavController().navigate(action)
                }
            }
        }
    }

}