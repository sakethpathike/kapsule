package sakethh.ui

import kotlinx.html.*

fun HTML.Surface(
    modifier: Modifier, content: BODY.() -> Unit = {}
) {
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

fun DIV.Text(
    text: String, fontSize: String, textOverflow: String = "ellipsis", modifier: Modifier = Modifier()
) {
    div {
        style = modifier.buildStyle() + """
            font-size: $fontSize;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: $textOverflow;
            display: inline-block;
        """.trimIndent()
        +text
    }
}

fun BODY.Text(
    text: String, fontSize: Int, textOverflow: String = "ellipsis", modifier: Modifier = Modifier()
) {
    div {
        style = modifier.buildStyle() + """
            font-size: ${fontSize}px;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: $textOverflow;
            display: inline-block;
        """.trimIndent()
        +text
    }
}

fun BODY.Column(modifier: Modifier = Modifier(), content: DIV.() -> Unit) {
    div {
        style = "display: flex; flex-direction: column; " + modifier.buildStyle()
        content()
    }
}

fun BODY.Row(modifier: Modifier = Modifier(), content: DIV.() -> Unit) {
    div {
        style = "display: flex; flex-direction: row; " + modifier.buildStyle()
        content()
    }
}

fun DIV.Spacer(modifier: Modifier = Modifier()) {
    div {
        style = modifier.buildStyle()
    }
}