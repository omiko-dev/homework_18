package com.example.homework_18.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_18.data.common.Resource
import com.example.homework_18.domain.response.UserResponse
import com.example.homework_18.domain.use_case.UserUseCase
import com.example.homework_18.presentation.event.UserEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userUseCase: UserUseCase): ViewModel() {
    private var _userStateFlow = MutableStateFlow<Resource<UserResponse>>(Resource.Idle)
    val userStateFlow get() = _userStateFlow.asStateFlow()

    fun onEvent(event: UserEvent){
        when(event){
            is UserEvent.GetUser -> getUser(event.id)
        }
    }

    private fun getUser(id: Int){
        viewModelScope.launch {
            userUseCase.invoke(id).collect{
                _userStateFlow.value = it
            }
        }
    }
}

