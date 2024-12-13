package com.hussein.madartask.domain.use_case

import androidx.core.text.isDigitsOnly

class ValidateAge {
    fun execute(age: String): ValidationResult {
        if(age.isBlank() || age.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The age can't be empty"
            )
        }
        if(!age.isDigitsOnly()){
            return ValidationResult(
                successful = false,
                errorMessage = "The age should be a number"
            )
        }
        if(age.toInt() < 10) {
            return ValidationResult(
                successful = false,
                errorMessage = "Age should be greater than 10"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}
