package com.example.homework_18.presentation.fragment

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework_18.BaseFragment
import com.example.homework_18.data.common.Resource
import com.example.homework_18.databinding.FragmentUsersBinding
import com.example.homework_18.presentation.view_model.UsersViewModel
import com.example.homework_18.presentation.adapter.UsersRecyclerAdapter
import com.example.homework_18.presentation.event.UsersEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UsersFragment : BaseFragment<FragmentUsersBinding>(FragmentUsersBinding::inflate) {

    private val viewModel: UsersViewModel by viewModels()
    private lateinit var adapter: UsersRecyclerAdapter

    override fun observe() {
        usersObserve()
    }

    override fun bind() {
        bindRecycler()
    }

    override fun listener() {
        goToUserPage()
    }

    private fun usersObserve() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.usersStateFlow.collect {
                    when (it) {
                        is Resource.Success -> {
                            adapter.submitList(it.success)
                        }

                        is Resource.Error -> {
                            Log.i("omiko", it.error.toString())
                        }

                        is Resource.Loader -> binding.loader.visibility = View.VISIBLE
                        is Resource.Idle -> binding.loader.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun bindRecycler() {
        adapter = UsersRecyclerAdapter()
        with(binding) {
            recycler.adapter = adapter
            recycler.layoutManager = LinearLayoutManager(requireContext())
        }
        viewModel.onEvent(UsersEvent.GetAllUser)
    }

    private fun goToUserPage() {
        adapter.onClick = {
            findNavController().navigate(
                UsersFragmentDirections.actionUsersFragmentToUserFragment(
                    it
                )
            )
        }
    }
}