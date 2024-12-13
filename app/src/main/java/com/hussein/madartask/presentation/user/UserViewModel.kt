package com.hussein.madartask.presentation.user

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hussein.madartask.data.model.User
import com.hussein.madartask.domain.RegistrationFormEvent
import com.hussein.madartask.domain.RegistrationFormState
import com.hussein.madartask.domain.RequestState
import com.hussein.madartask.domain.UserDao
import com.hussein.madartask.domain.use_case.ValidateAge
import com.hussein.madartask.domain.use_case.ValidateGender
import com.hussein.madartask.domain.use_case.ValidateJobTitle
import com.hussein.madartask.domain.use_case.ValidateName
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel(
    private val userDao: UserDao,
    private val validateName: ValidateName = ValidateName(),
    private val validateAge: ValidateAge = ValidateAge(),
    private val validateJobTitle: ValidateJobTitle = ValidateJobTitle(),
    private val validateGender: ValidateGender = ValidateGender()
) : ViewModel() {
    var _userRequestField =  MutableStateFlow<RequestState<User>>(RequestState.Idle)
    val userRequestField: StateFlow<RequestState<User>> = _userRequestField.asStateFlow()


    var registerStateField by mutableStateOf(RegistrationFormState())

    fun onEvent(event: RegistrationFormEvent) {
        when(event) {
            is RegistrationFormEvent.NameChanged -> {
                registerStateField = registerStateField.copy(name = event.name, nameError = null)
                _userRequestField.value = RequestState.Idle
            }
            is RegistrationFormEvent.AgeChanged -> {
                registerStateField = registerStateField.copy(age = event.age, ageError = null)
            }
            is RegistrationFormEvent.JobTitleChanged -> {
                registerStateField = registerStateField.copy(jobTitle = event.jobTitle, jobTitleError = null)
            }
            is RegistrationFormEvent.GenderChanged -> {
                registerStateField = registerStateField.copy(gender = event.gender, genderError = null)
            }
            is RegistrationFormEvent.Submit -> {
                insertUser()
            }
        }
    }

    private fun insertUser() {
        _userRequestField.value = RequestState.Loading // Indicate success (no specific data)

        val nameResult = validateName.execute(registerStateField.name)
        val ageResult = validateAge.execute(registerStateField.age)

        val jobTitleResult = validateJobTitle.execute(registerStateField.jobTitle)
        val genderResult = validateGender.execute(registerStateField.gender)


        val hasError = listOf(
            nameResult,
            ageResult,
            jobTitleResult,
            genderResult
        ).any { !it.successful }

        if(hasError) {
            _userRequestField.value = RequestState.Idle
            registerStateField = registerStateField.copy(
                nameError = nameResult.errorMessage,
                ageError = ageResult.errorMessage,
                jobTitleError = jobTitleResult.errorMessage,
                genderError = genderResult.errorMessage
            )
            return
        }
        try {
            val user = User(
                name = registerStateField.name,
                age = registerStateField.age,
                jobTitle = registerStateField.jobTitle,
                gender = registerStateField.gender
            )
            viewModelScope.launch {
                val insertedUserId = userDao.insert(user)
                val insertedUser =User(id = insertedUserId.toInt())
                _userRequestField.value = RequestState.Success(data = insertedUser) // Indicate success (no specific data)
            }
        } catch (e: Exception) {
            _userRequestField.value = RequestState.Error(e) // Indicate success (no specific data)
        }
    }
}