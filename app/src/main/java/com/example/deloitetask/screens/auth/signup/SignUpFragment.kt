package com.example.deloitetask.screens.auth.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.deloitetask.R
import com.example.deloitetask.common.BaseFragment
import com.example.deloitetask.databinding.FragmentSignUpBinding
import com.example.deloitetask.extensions.collectLatest
import com.example.deloitetask.extensions.showDatePickerDialog
import com.example.deloitetask.extensions.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment :
    BaseFragment<FragmentSignUpBinding, SignUpViewModel>(R.layout.fragment_sign_up) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDataBinding()
        collectLatest(viewModel.signUpSuccess, ::handleSignUpSuccess)
        collectLatest(viewModel.state, ::handleViewState)
        collectLatest(viewModel.validationError, ::handleValidationError)
        viewBinding?.birthSignUpEditText?.setOnClickListener {
            requireContext().showDatePickerDialog(viewBinding?.birthSignUpEditText)
        }
    }

    private fun initDataBinding() {
        viewBinding?.viewModel = viewModel
    }

    private fun handleSignUpSuccess(isSignUp: Boolean?) {
        requireContext().showToast(getString(R.string.success_sign_up))
    }

    private fun handleValidationError(validationError: ValidationViewState) {
        ValidationFactory.validate(validationError, requireContext())
    }
}