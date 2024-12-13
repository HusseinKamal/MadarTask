package com.hussein.madartask.presentation.compose

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.hussein.madartask.R

@Composable
fun CustomOutlinedTextField(modifier: Modifier = Modifier,
                            value: String = "",
                            onValueChange: (String) -> Unit,
                            placeholder: String = "",
                            leadingIcon: @Composable (() -> Unit)? = null,
                            trailingIcon: @Composable (() -> Unit)? = null,
                            shape: Shape = OutlinedTextFieldDefaults.shape,
                            isError: Boolean = false,
                            errorMessage : String = "",
                            keyboardType: KeyboardType = KeyboardType.Text,
                            imeAction: ImeAction = ImeAction.Done,
                            keyboardActions: KeyboardActions = KeyboardActions.Default,
                            isPassword: Boolean = false) {
    val passwordVisible by remember { mutableStateOf(false) }
    var textValue by remember { mutableStateOf(TextFieldValue(value)) } // Use TextFieldValue

    OutlinedTextField(
        value = textValue,
        onValueChange = { newValue: TextFieldValue -> // Must take TextFieldValue
            textValue = newValue // Update the state
            onValueChange(newValue.text) // Update the callback
        },
        shape = shape,
        modifier = modifier,
        placeholder = { Text(placeholder) }, // Show placeholder conditionally
        label = { Text(placeholder) },
        isError = isError,
        supportingText = {
            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                )
            }
        },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        // errorMessage = errorMessage,
        //supportingText = errorMessage?.let { Text(it, color = MaterialTheme.colorScheme.error) },
        keyboardActions = keyboardActions, // Only one keyboardActions parameter
        textStyle = TextStyle(fontSize = 16.sp, fontFamily = fontFamilyName), // Input text size
        visualTransformation = if (isPassword) {
            if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
    )
}

val fontFamilyName = FontFamily(
    Font(R.font.sanfransisco_regular, FontWeight.Light)
)