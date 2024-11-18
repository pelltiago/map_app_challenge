package com.example.uala.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleSearchBar(
    searchQuery: String,
    onQueryChanged: (String) -> Unit,
    onClearQuery: () -> Unit,
    onSearch: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = searchQuery,
        onValueChange = { newQuery ->
            onQueryChanged(newQuery)
        },
        placeholder = { Text("Buscar ciudades...") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Buscar",
                tint = Color.Gray
            )
        },
        trailingIcon = {
            if (searchQuery.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Borrar texto",
                    modifier = Modifier.clickable {
                        onClearQuery()
                        focusManager.clearFocus()
                    },
                    tint = Color.Gray
                )
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color.LightGray,
            focusedBorderColor = Color.Gray,
            unfocusedBorderColor = Color.Transparent,
            cursorColor = Color.Gray
        ),
        shape = RoundedCornerShape(16.dp),
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search,
            keyboardType = androidx.compose.ui.text.input.KeyboardType.Text
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch()
                focusManager.clearFocus()
            }
        )
    )
}
