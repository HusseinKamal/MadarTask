package com.hussein.madartask.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hussein.madartask.R
import com.hussein.madartask.presentation.compose.TextUI
import org.koin.androidx.compose.koinViewModel

@ExperimentalMaterial3Api
@Composable
fun UserProfileScreen(modifier: Modifier = Modifier,
                      userId:Int,
                      onBackClick:() -> Unit) {
    val userViewModel: UserProfileModel = koinViewModel()
    val user by userViewModel.user.collectAsState() // Directly observe the StateFlow

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
                        text = stringResource(id = R.string.user_profile),
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Back arrow icon",
                            tint = Color.White
                        )
                    }
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
                .background(Color.White)
                .padding(
                    top = padding.calculateTopPadding(),
                    bottom = padding.calculateBottomPadding()
                )
                .padding(all = 12.dp)
                .verticalScroll(rememberScrollState())// Make content scrollable
        )
        {
            Card(
                modifier = Modifier.fillMaxWidth()

            ) {
                Column(Modifier.padding(16.dp)) { // Add padding within the Card
                    // Name
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Rounded.Person, // Replace with age icon
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary, // Or another suitable color
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        TextUI(
                            text = "${stringResource(R.string.name)} : ${user.name}",
                            fontWeight = FontWeight.Bold,
                            textSize = 16.sp,

                        )
                    }
                    Spacer(Modifier.height(12.dp)) // Add some spacing between rows
                    // Age
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Rounded.DateRange, // Replace with age icon
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary, // Or another suitable color
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        TextUI(
                            text = "${stringResource(R.string.age)} : ${user.age}",
                            fontWeight = FontWeight.Bold,
                            textSize = 16.sp,)
                    }
                    Spacer(Modifier.height(12.dp))
                    // Job Title
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Star, // Replace with age icon
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary, // Or another suitable color
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        TextUI(
                            text = "${stringResource(R.string.job_title)} : ${user.jobTitle}",
                            fontWeight = FontWeight.Bold,
                            textSize = 16.sp,
                        )
                    }

                    Spacer(Modifier.height(12.dp)) // Add some spacing between rows

                    // Gender
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Rounded.Person, // Replace with age icon
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary, // Or another suitable color
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        TextUI(
                            text = "Gender: ${user.gender.name}",
                            fontWeight = FontWeight.Bold,
                            textSize = 16.sp,)
                    }
                }
            }
        }
    }

}