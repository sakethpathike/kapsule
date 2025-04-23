package sakethh.ui

import kotlinx.html.*

fun HTML.Surface(
    modifier: Modifier,
    /**List of font URLs*/
    fonts: List<String> = emptyList(), content: BODY.() -> Unit = {}
) {
    head {
        fonts.filter { font ->
            font.startsWith("http")
        }.forEach { font ->
            link(href = font, rel = "stylesheet")
        }
    }
    body {
        style = modifier.buildStyle()
        content()
    }
}

fun HTML.Surface(style: String, content: BODY.() -> Unit = {}) {
    body {
        this.style = style
        content()
    }
}

fun FlowContent.Text(
    text: String,
    fontSize: String = 12.px,
    fontFamily: String,
    textColor: String,
    textOverflow: String = "ellipsis",
    modifier: Modifier = Modifier()
) {
    div {
        style = modifier.buildStyle() + """
            color: $textColor;
            font-family: "$fontFamily";
            font-size: $fontSize;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: $textOverflow;
        """.trimIndent()
        +text
    }
}

enum class VerticalAlignment(val cssValue: String) {
    Top("flex-start"), Center("center"), Bottom("flex-end"), SpaceBetween("space-between"), SpaceAround("space-around"), SpaceEvenly(
        "space-evenly"
    ),
    None("")
}

enum class HorizontalAlignment(val cssValue: String) {
    Start("flex-start"), Center("center"), End("flex-end"), Stretch("stretch"), None("")
}

fun FlowContent.Column(
    modifier: Modifier = Modifier(),
    verticalAlignment: VerticalAlignment = VerticalAlignment.None,
    horizontalAlignment: HorizontalAlignment = HorizontalAlignment.None,
    content: DIV.() -> Unit
) {
    div {
        style =
            "display: flex; flex-direction: column; ${if (verticalAlignment.cssValue.isNotBlank()) "justify-content: ${verticalAlignment.cssValue}; " else ""}; ${if (horizontalAlignment.cssValue.isNotBlank()) "align-items: ${horizontalAlignment.cssValue}; " else ""}" + modifier.buildStyle()
        content()
    }
}


fun FlowContent.Row(
    modifier: Modifier = Modifier(),
    verticalAlignment: VerticalAlignment = VerticalAlignment.None,
    horizontalAlignment: HorizontalAlignment = HorizontalAlignment.None,
    content: DIV.() -> Unit
) {
    div {
        style = buildString {
            append("display: flex; ")
            append("flex-direction: row; ")
            if (verticalAlignment.cssValue.isNotBlank()) {
                append("justify-content: ${verticalAlignment.cssValue}; ")
            }
            if (horizontalAlignment.cssValue.isNotBlank()) {
                append("align-items: ${horizontalAlignment.cssValue}; ")
            }
            append(modifier.buildStyle())
        }
        content()
    }
}

fun FlowContent.Spacer(modifier: Modifier = Modifier()) {
    div {
        style = modifier.buildStyle()
    }
}

fun FlowContent.Box(modifier: Modifier, init: DIV.() -> Unit) {
    div {
        style = modifier.buildStyle()
        init()
    }
}

fun FlowContent.Button(modifier: Modifier, onClick: () -> String, content: BUTTON.() -> Unit) {
    button(type = ButtonType.button) {
        style = modifier.buildStyle()
        this.onClick = onClick()
        content()
    }
}