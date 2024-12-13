package com.hussein.madartask.domain.use_case

class ValidateName {
    fun execute(name: String): ValidationResult {
        if(name.isBlank() || name.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The name can't be empty"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}
