package com.foundy.markdown_toolbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.CheckBox
import androidx.compose.material.icons.outlined.IntegrationInstructions
import androidx.compose.ui.graphics.vector.ImageVector

enum class MarkdownTag(val imageVector: ImageVector) {

    BOLD(Icons.Default.FormatBold),

    ITALIC(Icons.Default.FormatItalic),

    LINK(Icons.Default.Link),

    LIST_ITEM(Icons.Default.List),

    TASK_LIST_ITEM(Icons.Outlined.CheckBox),

    HEADING(Icons.Default.HMobiledata),

    STRIKETHROUGH(Icons.Default.StrikethroughS),

    QUOTE(Icons.Default.FormatQuote),

    CODE_HIGHLIGHT(Icons.Default.Code),

    CODE_BLOCK(Icons.Outlined.IntegrationInstructions)
}
