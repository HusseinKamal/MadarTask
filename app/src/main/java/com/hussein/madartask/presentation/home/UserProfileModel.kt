package com.hussein.madartask.presentation.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hussein.madartask.data.model.Gender
import com.hussein.madartask.data.model.User
import com.hussein.madartask.domain.UserDao
import com.hussein.madartask.presentation.navigation.USER_ID_ARG
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserProfileModel(
    private val userDao: UserDao,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val selectOrderId: Int = savedStateHandle.get<Int>(USER_ID_ARG)  ?: -1

    private var _user = MutableStateFlow(User())
    val user: StateFlow<User> = _user.asStateFlow()


    init {
        getUserData()
    }
    fun getUserData(){
        try {
            viewModelScope.launch {
                if (selectOrderId > 0) {
                    _user.value = userDao.getUserById(selectOrderId)!!
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
    fun onNameChanged(name: String) {
        _user.update { it.copy(name = name) }
    }

    fun onAgeChanged(age: String) {
        _user.update { it.copy(age = age) }
    }

    fun onJobTitleChanged(jobTitle: String) {
        _user.update { it.copy(jobTitle = jobTitle) }
    }

    fun onGenderChanged(gender: Gender) {
        _user.update { it.copy(gender = gender) }
    }
}