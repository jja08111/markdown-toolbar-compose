# Markdown Toolbar - Compose

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.jja08111/markdown-toolbar-compose/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.jja08111/markdown-toolbar-compose)

모바일 기기에서 편하게 마크다운 태그를 입력할 수 있도록 도와주는 Android 라이브러리입니다.

키보드가 있는 컴퓨터 환경과는 다르게 모바일 환경은 마크다운 태그 같은 특수 문자를 입력하는데 번거롭습니다.
이러한 문제를 해결하고자 라이브러리를 개발했습니다.

![preview](/images/preview.gif)

# Faced Issues

**수동으로 로직을 검증하는데 시간 소모가 큼** 

- 문제: 테스트를 위해 반복적으로 테스트 기기에서 직접 값을 입력하고, 태그를 클릭하고 결과물을 확인하는 과정이 번거로움  
- 해결: 유닛 테스트 및 UI 테스트 코드를 작성하여 빠르게 코드를 검증할 수 있도록 함

**유닛 테스트 코드의 중복이 많음**

- 문제: 함수를 테스트하기 위해 반복되는 테스트 코드가 많아 가독성 및 유지보수성이 떨어짐
- 해결: JUnit의 Parameterized Test를 이용하여 input, output만을 정의하도록 수정하여 중복 제거 및 유지보수성 향상

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
