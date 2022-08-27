package com.foundy.markdown_toolbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

typealias MarkdownTagIconBuilder = ((MarkdownTag) -> ImageVector?)

/**
 * Compose the [MarkdownToolBar] that helps users write markdown tags easier.
 *
 * @param iconBuilder Builds custom markdown icons. If a return value is null,
 * the [MarkdownTag.imageVector] will be used.
 */
@Composable
fun MarkdownToolBar(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    iconTint: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    iconPadding: Dp = 8.dp,
    iconBuilder: MarkdownTagIconBuilder? = null
) {
    Row(
        modifier
            .fillMaxWidth()
            .background(color = backgroundColor)
            .horizontalScroll(rememberScrollState())
    ) {
        val tags = remember { MarkdownTag.values() }
        for (tag in tags) {
            MarkdownToolBarItemButton(
                tag = tag,
                iconTint = iconTint,
                iconPadding = iconPadding,
                iconBuilder = iconBuilder
            ) {
                onValueChange(value.addMarkdownTag(tag))
            }
        }
    }
}

@Composable
private fun MarkdownToolBarItemButton(
    tag: MarkdownTag,
    iconTint: Color,
    iconPadding: Dp,
    iconBuilder: MarkdownTagIconBuilder?,
    onClick: () -> Unit
) {
    val imageVector = remember { iconBuilder?.invoke(tag) ?: tag.imageVector }

    Icon(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(iconPadding)
            .semantics { testTag = imageVector.name },
        imageVector = imageVector,
        tint = iconTint,
        contentDescription = tag.name
    )
}

internal fun TextFieldValue.addMarkdownTag(tag: MarkdownTag): TextFieldValue {
    val text = this.text
    val selection = this.selection

    return when (tag) {
        MarkdownTag.BOLD -> TextFieldValue(
            text = text.wrapWith("**", selection.start, selection.end),
            selection = TextRange(selection.end + 2)
        )
        MarkdownTag.ITALIC -> TextFieldValue(
            text = text.wrapWith("_", selection.start, selection.end),
            selection = TextRange(selection.end + 1)
        )
        MarkdownTag.LINK -> TextFieldValue(
            text = text.wrapWith(
                prefix = "[",
                suffix = "]()",
                selection.start,
                selection.end
            ),
            selection = TextRange(selection.end + 4)
        )
        MarkdownTag.LIST_ITEM -> TextFieldValue(
            text = text.insertAtFrontOfLine("- ", selection.start),
            selection = TextRange(selection.end + 2)
        )
        MarkdownTag.TASK_LIST_ITEM -> {
            var newText = text.checkIfHasCheckBoxTag(selection.start)
            var newTextRange = TextRange(selection.end)
            // Add a task list item in front of the line if there is no task list item.
            if (newText == text) {
                newText = text.insertAtFrontOfLine("- [ ] ", selection.start)
                newTextRange = TextRange(selection.end + 6)
            }
            TextFieldValue(
                text = newText,
                selection = newTextRange
            )
        }
        MarkdownTag.HEADING -> {
            val hasHeadingTag = text.hasHeadingTagAtFrontOfLine(selection.start)
            val headingTag = if (hasHeadingTag) "#" else "# "

            TextFieldValue(
                text = text.insertAtFrontOfLine(headingTag, selection.start),
                selection = TextRange(
                    index = selection.end + headingTag.length
                )
            )
        }
        MarkdownTag.STRIKETHROUGH -> TextFieldValue(
            text = text.wrapWith("~~", selection.start, selection.end),
            selection = TextRange(selection.end + 2)
        )
        MarkdownTag.QUOTE -> TextFieldValue(
            text = text.insertAtFrontOfLine("> ", selection.start),
            selection = TextRange(selection.end + 2)
        )
        MarkdownTag.CODE_HIGHLIGHT -> TextFieldValue(
            text = text.wrapWith("`", selection.start, selection.end),
            selection = TextRange(selection.end + 1)
        )
        MarkdownTag.CODE_BLOCK -> TextFieldValue(
            text = text.wrapWith(
                prefix = "```\n",
                suffix = "\n```",
                selection.start,
                selection.end
            ),
            selection = TextRange(selection.start + 3)
        )
    }
}

@Preview
@Composable
private fun MarkdownToolBarPreview() {
    MarkdownToolBar(value = TextFieldValue(), onValueChange = {})
}
