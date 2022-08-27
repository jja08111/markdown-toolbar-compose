package com.foundy.markdown_toolbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class MarkdownToolBarTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun shouldChangeTextFieldValue_WhenClickMarkdownToolBarItem() {
        with(composeRule) {
            var textFieldValue by mutableStateOf(TextFieldValue())
            setContent {
                MarkdownToolBar(
                    value = textFieldValue,
                    onValueChange = { textFieldValue = it }
                )
            }

            onNodeWithContentDescription(MarkdownTag.BOLD.name).performClick()

            assertEquals("****", textFieldValue.text)
            assertEquals(TextRange(2), textFieldValue.selection)
        }
    }

    @Test
    fun shouldChangeIcon_WhenUsingIconBuilder() {
        with(composeRule) {
            var textFieldValue by mutableStateOf(TextFieldValue())
            val newBoldIcon = Icons.Default.Air

            setContent {
                MarkdownToolBar(
                    value = textFieldValue,
                    onValueChange = { textFieldValue = it },
                    iconBuilder = { tag ->
                        when(tag) {
                            MarkdownTag.BOLD -> newBoldIcon
                            else -> null
                        }
                    }
                )
            }

            onNodeWithTag(newBoldIcon.name).assertIsDisplayed()
        }
    }
}
