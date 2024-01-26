package com.example.hw20.presentation.fragments.users

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hw20.databinding.FragmentUsersBinding
import com.example.hw20.presentation.base.BaseFragment
import com.example.hw20.presentation.event.SuccessEvent
import com.example.hw20.presentation.event.UsersEvent
import com.example.hw20.presentation.model.UserUiModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UsersFragment : BaseFragment<FragmentUsersBinding>(FragmentUsersBinding::inflate) {

    private val args: UsersFragmentArgs by navArgs()
    private val viewModel: UsersViewModel by viewModels()


    override fun setUp() {
        args.user?.let {
            with(binding) {
                etFirstName.setText(it.firstName)
                etLastName.setText(it.lastName)
                etAge.setText(it.age.toString())
                etEmail.setText(it.email)
            }
        }
    }

    override fun listeners() {
        binding.btnAdd.setOnClickListener {
            if (args.user == null) {
                viewModel.onEvent(UsersEvent.CreateUser(collectUserInfo()))
            } else {
                viewModel.onEvent(UsersEvent.UpdateUser(collectUserInfo()))
            }
        }
    }

    private fun collectUserInfo(): UserUiModel {
        return UserUiModel(
            args.user?.id ?: 0,
            binding.etFirstName.text.toString(),
            binding.etLastName.text.toString(),
            binding.etAge.text.toString().toIntOrNull() ?: 0,
            binding.etEmail.text.toString()
        )
    }

    override fun observers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userState.collect {
                    handleState(it)
                }
            }
        }
    }

    private fun handleState(successEvent: SuccessEvent) {
        when (successEvent) {
            is SuccessEvent.SavedSuccessfully -> {
                toastMessage("saved successfully")
                findNavController().navigateUp()
            }

            SuccessEvent.EditedSuccessfully -> {
                toastMessage("edited successfully")
                findNavController().navigateUp()
            }
        }
    }

    private fun toastMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}