package com.hussein.madartask.domain

import com.hussein.madartask.data.model.Gender

data class RegistrationFormState(
    val name: String = "",
    val nameError: String? = null,
    val age: String = "",
    val ageError: String? = null,
    val jobTitle: String = "",
    val jobTitleError: String? = null,
    val gender: Gender = Gender.MALE,
    val genderError: String? = null
)