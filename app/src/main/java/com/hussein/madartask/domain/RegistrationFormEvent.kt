package com.hussein.madartask.domain

import com.hussein.madartask.data.model.Gender

sealed class RegistrationFormEvent {
    data class NameChanged(val name: String) : RegistrationFormEvent()
    data class AgeChanged(val age: String) : RegistrationFormEvent()
    data class JobTitleChanged(val jobTitle: String) : RegistrationFormEvent()
    data class GenderChanged(val gender: Gender) : RegistrationFormEvent()
    object Submit: RegistrationFormEvent()
}