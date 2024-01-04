package com.example.homework_18.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_18.data.common.Resource
import com.example.homework_18.domain.response.UsersResponse
import com.example.homework_18.domain.use_case.UsersUseCase
import com.example.homework_18.presentation.event.UsersEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val usersUseCase: UsersUseCase): ViewModel() {

    private var _usersStateFlow = MutableStateFlow<Resource<List<UsersResponse>>>(Resource.Idle)
    val usersStateFlow = _usersStateFlow.asStateFlow()

    init {
        getAllUser()
    }

    fun onEvent(event: UsersEvent){
        when(event){
            is UsersEvent.GetAllUser -> getAllUser()
        }
    }

    private fun getAllUser(){
        viewModelScope.launch {
            usersUseCase.invoke().collect {
                _usersStateFlow.value = it
            }
        }

    }
}


