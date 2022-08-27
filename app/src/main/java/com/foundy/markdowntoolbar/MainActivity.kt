package com.foundy.markdowntoolbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.foundy.markdowntoolbar.ui.theme.MarkdownToolbarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // For prevention of Software keyboard overlaps a content of a compose view
        WindowCompat.setDecorFitsSystemWindows(window, true)

        setContent {
            MarkdownToolbarTheme {
                MarkdownToolbarExample()
            }
        }
    }
}
