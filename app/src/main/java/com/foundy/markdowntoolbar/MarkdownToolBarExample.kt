package com.foundy.markdowntoolbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatBold
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.foundy.markdown_toolbar.MarkdownTag
import com.foundy.markdown_toolbar.MarkdownToolBar
import com.foundy.markdowntoolbar.ui.theme.MarkdownToolbarTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarkdownToolbarExample() {
    var textFieldValue by remember { mutableStateOf(TextFieldValue()) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            TextField(
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = Color.Transparent,
                    placeholderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                ),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                value = textFieldValue,
                onValueChange = { textFieldValue = it },
                placeholder = { Text(text = "Type texts here") }
            )
            MarkdownToolBar(
                value = textFieldValue,
                onValueChange = { textFieldValue = it },
                icons = mapOf(MarkdownTag.BOLD to Icons.Default.FormatBold),
            )
        }
    }

}

@Preview
@Composable
fun MarkdownToolbarExamplePreview() {
    MarkdownToolbarTheme {
        MarkdownToolbarExample()
    }
}