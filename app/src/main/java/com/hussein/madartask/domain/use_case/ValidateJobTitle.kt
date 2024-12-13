package com.hussein.madartask.domain.use_case

class ValidateJobTitle {
    fun execute(jobTitle: String): ValidationResult {
        if(jobTitle.isBlank() || jobTitle.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The job title can't be empty"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}
