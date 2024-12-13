package com.hussein.madartask.domain.use_case

import com.hussein.madartask.data.model.Gender

class ValidateGender {
    fun execute(gender: Gender): ValidationResult {
        if(gender.name.isBlank() || gender.name.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The gender can't be empty"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}
