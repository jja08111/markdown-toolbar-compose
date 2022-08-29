package com.foundy.markdown_toolbar

internal fun String.replaceAt(char: Char, index: Int): String {
    return substring(0, index) + char + substring(index + 1, length)
}

internal fun String.wrapWith(prefix: String, suffix: String, start: Int, end: Int): String {
    return substring(0, start) + prefix + substring(start, end) + suffix + substring(end, length)
}

internal fun String.wrapWith(str: String, start: Int, end: Int): String {
    return wrapWith(str, str, start, end)
}

/**
 * Returns the first index of this line that positions at [cursorPosition].
 *
 * The Result can be overflow the string length if it is empty.
 */
internal fun String.firstIndexOfLine(cursorPosition: Int): Int {
    return substring(0, cursorPosition).lastIndexOf('\n') + 1
}

internal fun String.insertAtFrontOfLine(str: String, cursorPosition: Int): String {
    val targetIndex = firstIndexOfLine(cursorPosition)
    return substring(0, targetIndex) + str + substring(targetIndex, length)
}

internal fun String.hasHeadingTagAtFrontOfLine(cursorPosition: Int): Boolean {
    if (isEmpty()) {
        return false
    }
    val index = firstIndexOfLine(cursorPosition)
    if (index > this.lastIndex) {
        return false
    }
    return this[index] == '#'
}

internal fun String.checkIfHasCheckBoxTag(cursorPosition: Int): String {
    val start = firstIndexOfLine(cursorPosition)
    val end = minOf(start + 6, length)
    val thisCheckBoxString = substring(start, end)
    val hasUncheckedBox = thisCheckBoxString == "- [ ] "
    val hasCheckedBox = thisCheckBoxString == "- [x] " || thisCheckBoxString == "- [X] "
    return if (hasUncheckedBox) {
        replaceAt('x', start + 3)
    } else if (hasCheckedBox) {
        replaceAt(' ', start + 3)
    } else {
        this
    }
}
