# Markdown Toolbar - Compose

It helps users write markdown tags easier.

# Preview

![preview](/images/preview.gif)

# Example

Pass the `TextFieldValue` to the argument. You can customize icons by using the `iconBuilder`. 

```kotlin
var textFieldValue by remember { mutableStateOf(TextFieldValue()) }

MarkdownToolBar(
    value = textFieldValue,
    onValueChange = { textFieldValue = it },
    iconBuilder = { tag ->
        when (tag) {
            MarkdownTag.BOLD -> Icons.Default.FormatBold
            else -> null
        }
    }
)
```

# Tags

- Bold
- Italic
- List item
- Task list item
- Hading
- Strikethrough
- Quote
- Code highlight
- Code block

# TODO 

- Image