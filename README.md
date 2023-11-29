# Markdown Toolbar - Compose

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.jja08111/markdown-toolbar-compose/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.jja08111/markdown-toolbar-compose)

It helps users write markdown tags easier.

# Preview

![preview](/images/preview.gif)

# Usage

Add below code to the dependencies and sync the build gradle.

```groovy
dependencies {
    implementation "io.github.jja08111:markdown-toolbar-compose:0.1.0"
}
```

# Example

Pass the `TextFieldValue` to the argument. You can customize icons by using the `icons`.

```kotlin
var textFieldValue by remember { mutableStateOf(TextFieldValue()) }

MarkdownToolBar(
    value = textFieldValue,
    onValueChange = { textFieldValue = it },
    icons = mapOf(MarkdownTag.BOLD to Icons.Default.FormatBold),
)
```

# Supported Markdown Tags

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
