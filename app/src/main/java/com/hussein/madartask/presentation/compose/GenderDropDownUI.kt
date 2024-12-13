package com.hussein.madartask.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hussein.madartask.data.model.Gender

@Composable
fun GenderDropDownUI(
    selectedGender: Gender,
    onGenderSelected: (Gender) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier
        .fillMaxWidth()
        .border(1.dp, Color.LightGray, MaterialTheme.shapes.small)) {  // Use a Box for proper layout
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .padding(16.dp), // Add padding for better visual appearance
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween  // Align items to edges
        ) {
            TextUI(text = selectedGender.name, textSize = 14.sp)
            Icon(
                imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = null
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = modifier.fillMaxWidth(0.9f)  // Make dropdown fill width
        ) {
            Gender.entries.forEach { gender ->
                DropdownMenuItem(text = {
                    Column {
                        TextUI(modifier = Modifier.fillMaxWidth() , text =gender.name, textSize = 12.sp)
                        Spacer(modifier = Modifier
                            .height(1.dp)
                            .fillMaxWidth()
                            .padding(top = 4.dp, bottom = 4.dp)
                            .background(Color.LightGray))
                    }

                }, onClick = {
                    onGenderSelected(gender)
                    expanded = false
                })
            }
        }
    }
}