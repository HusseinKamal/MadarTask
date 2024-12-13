package com.hussein.madartask.presentation.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hussein.madartask.R
import com.hussein.madartask.domain.RegistrationFormEvent
import com.hussein.madartask.domain.RequestState
import com.hussein.madartask.presentation.compose.ButtonUI
import com.hussein.madartask.presentation.compose.CustomOutlinedTextField
import com.hussein.madartask.presentation.compose.GenderDropDownUI
import com.hussein.madartask.presentation.compose.TextUI
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@ExperimentalMaterial3Api
@Composable
fun UserRegisterScreen(modifier: Modifier = Modifier,
                       onNavigationProfile:(Int) -> Unit) {
    val scope = rememberCoroutineScope()
    val userViewModel: UserViewModel = koinViewModel()
    val isLoading = remember { mutableStateOf(false) }
    val requestState = userViewModel.userRequestField.collectAsState()
    val registerFormState = userViewModel.registerStateField
    // Handle the request state
     LaunchedEffect(key1 = requestState.value) { // Observe userRequestState changes
         scope.launch {
             userViewModel.userRequestField.collectLatest {state ->
                 when (state) {
                     is RequestState.Loading -> {
                         isLoading.value = true
                     }
                     // Update isLoading here, not in onClick
                     is RequestState.Success -> {
                         isLoading.value = false
                         val userId = state.data.id
                         onNavigationProfile(userId)
                     }

                     is RequestState.Error -> {
                         isLoading.value = false
                     }
                     else -> {
                         isLoading.value = false
                     }
                 }
             }
         }

     }

    Scaffold(
        topBar = {
            TopAppBar(modifier = Modifier
                .fillMaxWidth(),
                title = {
                    TextUI(
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 1,
                        colorText = Color.White,
                        textSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        text = stringResource(id = R.string.user_register),
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary // Set the background color here
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.White, Color.White) // Customize colors
                    )
                )
                .padding(16.dp)
                .verticalScroll(rememberScrollState()) // Make content scrollable
        ) {
            CustomOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = registerFormState.name,
                onValueChange = { userViewModel.onEvent(RegistrationFormEvent.NameChanged(it)) },
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                errorMessage = registerFormState.nameError ?: "",
                placeholder = stringResource(R.string.name)
            )
            Spacer(modifier = Modifier.height(8.dp))
            CustomOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = registerFormState.age,
                onValueChange = { userViewModel.onEvent(RegistrationFormEvent.AgeChanged(it)) },
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
                errorMessage = registerFormState.ageError ?: "",
                placeholder = stringResource(R.string.age)
            )
            Spacer(modifier = Modifier.height(8.dp))
            CustomOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = registerFormState.jobTitle,
                onValueChange = { userViewModel.onEvent(RegistrationFormEvent.JobTitleChanged(it))},
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
                errorMessage = registerFormState.jobTitleError ?: "",
                placeholder = stringResource(R.string.job_title)
            )
            Spacer(modifier = Modifier.height(8.dp))

            GenderDropDownUI(
                selectedGender = registerFormState.gender,
                onGenderSelected = { newGender ->
                    userViewModel.onEvent(RegistrationFormEvent.GenderChanged(newGender))
                })
            Spacer(modifier = Modifier.height(4.dp))

            ButtonUI(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onClick = {
                    scope.launch {
                        userViewModel.onEvent(RegistrationFormEvent.Submit)
                    }
                },
                text = stringResource(R.string.register),
                enabled = true,
                isLoading = isLoading.value,
                cornerRadius = 20,
                backgroundColor = Color.Magenta,
                textColor = Color.White,
                progressColor = Color.White
            )

        }
    }
}

@ExperimentalMaterial3Api
@Composable
@Preview(showBackground = true)
fun UserRegisterScreenPreview(modifier: Modifier = Modifier) {
    UserRegisterScreen(onNavigationProfile = {
    })
}