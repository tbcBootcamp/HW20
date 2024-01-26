package com.example.hw20.presentation.fragments.list

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw20.databinding.FragmentListBinding
import com.example.hw20.presentation.adapter.UserAdapter
import com.example.hw20.presentation.base.BaseFragment
import com.example.hw20.presentation.model.UserUiModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragment : BaseFragment<FragmentListBinding>(FragmentListBinding::inflate) {

    private val viewModel: ListViewModel by viewModels()
    private lateinit var adapter: UserAdapter


    override fun setUp() {
        adapter = UserAdapter()
        binding.mRecycler.layoutManager =
            LinearLayoutManager(requireContext())
        binding.mRecycler.adapter = adapter
        adapter.onEditClick = {
            onEditClick(it)
        }
        adapter.onRemoveClick = {
            onRemoveClick(it)
        }
    }
    private fun onRemoveClick(user: UserUiModel) {
        viewModel.delete(user)
        Toast.makeText(requireContext(), "deleted", Toast.LENGTH_SHORT).show()
    }
    override fun listeners() {
        binding.ibAdd.setOnClickListener {
            navigateToAddUser()
        }

    }

    override fun observers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getAll().collect {
                    adapter.setData(it)
                }
            }
        }


    }

    private fun onEditClick(user: UserUiModel) {
        findNavController().navigate(ListFragmentDirections.actionListFragmentToUsersFragment(user))
    }

    private fun navigateToAddUser() {
        findNavController().navigate(ListFragmentDirections.actionListFragmentToUsersFragment(null))
    }
}