package com.example.lab_ta.presentations.fragments.dashboard.presentation


import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.lab_ta.R
import com.example.lab_ta.base.BaseFragment
import com.example.lab_ta.common.extention.launchAndRepeatWithViewLifecycle
import com.example.lab_ta.common.extention.toast
import com.example.lab_ta.databinding.FragmentDashboardBinding
import com.example.lab_ta.network.core.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding>() {

    private val dashboardViewModel: DashboardViewModel by viewModels()


    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentDashboardBinding = DataBindingUtil.inflate(
        layoutInflater, R.layout.fragment_dashboard, container, false
    )

    override fun init() {
        initLoadingDialog()

        binding.apply {
            viewModel = dashboardViewModel
        }


        launchAndRepeatWithViewLifecycle {
            launch {
                dashboardViewModel.uiAction.collectLatest {
                    when (it) {
                        DashboardUiActions.NavigateBack -> findNavController().popBackStack()
                        DashboardUiActions.OnProfile -> findNavController().navigate(R.id.action_dashboardFragment_to_profileFragment)
                        else -> {}
//                            findNavController().navigate(
//                            DashboardFragmentDirections.actionDashboardFragmentToDetailsFragment(it)
//                        )
                    }
                }
            }

        }


        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                getContent(s.toString())
            }

        })
    }

    private fun getContent(query: String) {

        launchAndRepeatWithViewLifecycle {
            launch {
                dashboardViewModel.getContent(query).collect {
                    when (it) {
                        is Resource.Error -> {
                            dismissLoadingDialog()
                            activity?.toast("${it.message}")
                            Timber.d("getContent: ${it.message}")
                        }

                        is Resource.Loading -> {
                            showLoadingDialog()
                        }

                        is Resource.NetworkError -> {
                            dismissLoadingDialog()
                            activity?.toast("${it.message}")
                        }

                        is Resource.SessionExpired -> {
                            dismissLoadingDialog()
                        }

                        is Resource.Success -> {
                            dismissLoadingDialog()
                            val contents = it.data

                            Timber.d("fwanwenowanoef ${it.data}")

//                            contents?.let { items ->
//                                dashboardViewModel.updateItems(items)
//                            }
                        }

                        else -> {}
                    }
                }
            }
        }
    }
}