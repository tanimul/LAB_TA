package com.example.lab_ta.presentations.fragments.login.presentation


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.lab_ta.R
import com.example.lab_ta.base.BaseFragment
import com.example.lab_ta.common.constants.AppConstants.DataStore.USER_NAME
import com.example.lab_ta.common.extention.launchAndRepeatWithViewLifecycle
import com.example.lab_ta.common.extention.toast
import com.example.lab_ta.common.viewmodel.DataStoreViewModel
import com.example.lab_ta.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val loginViewmodel: LoginViewModel by viewModels()
    private val dataStoreViewModel: DataStoreViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentLoginBinding = DataBindingUtil.inflate(
        layoutInflater, R.layout.fragment_login, container, false
    )

    override fun init() {

        binding.apply {
            viewModel = loginViewmodel
        }

        launchAndRepeatWithViewLifecycle {
            launch {
                loginViewmodel.uiAction.collectLatest {
                    when (it) {
                        LoginUiActions.NavigateBack -> findNavController().popBackStack()
                        LoginUiActions.OnLogin -> login(
                            binding.etUserName.text.toString(), binding.etPassword.text.toString()
                        )

                        LoginUiActions.OnRegistration -> findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
                    }
                }
            }

        }

    }

    private fun login(userName: String, password: String) {
        launchAndRepeatWithViewLifecycle {
            launch {
                loginViewmodel.getUserInfo(userName)
            }

            launch {
                loginViewmodel.userInfo.collectLatest { userInfo ->
                    Timber.d("User Info: $userInfo")

                    when {
                        userInfo == null || userInfo.userName != userName || userInfo.password != password -> {
                            activity?.toast("Wrong Password")
                        }

                        else -> {
                            dataStoreViewModel.saveToDataStore(USER_NAME, userInfo.userName)
                            findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment)
                        }
                    }
                }

            }

        }

    }
}