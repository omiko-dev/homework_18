package com.example.homework_18.presentation.fragment

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.homework_18.BaseFragment
import com.example.homework_18.data.common.Resource
import com.example.homework_18.databinding.FragmentUserBinding
import com.example.homework_18.domain.response.UserData
import com.example.homework_18.presentation.event.UserEvent
import com.example.homework_18.presentation.view_model.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserFragment : BaseFragment<FragmentUserBinding>(FragmentUserBinding::inflate) {

    private val viewModel: UserViewModel by viewModels()
    private val args: UserFragmentArgs by navArgs()

    override fun observe() {
        getUserData()
        observeUser()
    }

    override fun bind() {
//        TODO("Not yet implemented")
    }

    override fun listener() {
//        TODO("Not yet implemented")
    }

    private fun getUserData() {
        viewModel.onEvent(UserEvent.GetUser(args.id))
    }

    private fun bindUserInfo(userData: UserData) {
        with(binding) {
            tvEmail.text = userData.email
            tvFirstName.text = userData.firstName
            tvLastName.text = userData.lastName
            Glide
                .with(requireContext())
                .load(userData.avatar)
                .into(ivUserImage)
        }
    }

    private fun observeUser(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.userStateFlow.collect {
                    when(it){
                        is Resource.Success -> {
                            with(binding){
                                tvEmailTitle.visibility = View.VISIBLE
                                tvFirstNameTitle.visibility = View.VISIBLE
                                tvLastNameTitle.visibility = View.VISIBLE
                            }
                            bindUserInfo(it.success.data)
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

}