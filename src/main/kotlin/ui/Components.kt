package sakethh.ui

import kotlinx.html.*

fun HTML.Surface(
    modifier: Modifier,
    /**List of font URLs*/
    fonts: List<String> = emptyList(),
    style: STYLE.() -> Unit = {},
    className: String? = null,
    id: String? = null,
    content: BODY.() -> Unit = {}
) {
    head {
        fonts.filter { font ->
            font.startsWith("http")
        }.forEach { font ->
            link(href = font, rel = "stylesheet")
        }

        style {
           style()
        }
    }
    body(classes = className) {
        if (id != null) {
            this.id = id
        }
        this.style = modifier.buildStyle()
        content()
    }
}

fun HTML.Surface(style: String, className: String? = null, id: String? = null, content: BODY.() -> Unit = {}) {
    body(classes = className) {
        if (id != null) {
            this.id = id
        }
        this.style = style
        content()
    }
}

fun FlowContent.Text(
    text: String,
    fontSize: String = 12.px,
    fontFamily: String,
    color: String,
    fontWeight: String,
    className: String? = null,
    id: String? = null,
    modifier: Modifier = Modifier()
) {
    div(classes = className) {
        if (id != null) {
            this.id = id
        }
        style = modifier.buildStyle() + """
            font-weight: $fontWeight;
            color: $color;
            font-family: "$fontFamily";
            font-size: $fontSize;
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
    horizontalAlignment: HorizontalAlignment = HorizontalAlignment.None, className: String? = null, id: String? = null,
    content: DIV.() -> Unit
) {
    div(classes = className) {
        if (id != null) {
            this.id = id
        }
        style =
            "display: flex; flex-direction: column; ${if (verticalAlignment.cssValue.isNotBlank()) "justify-content: ${verticalAlignment.cssValue}; " else ""}; ${if (horizontalAlignment.cssValue.isNotBlank()) "align-items: ${horizontalAlignment.cssValue}; " else ""}" + modifier.buildStyle()
        content()
    }
}


fun FlowContent.Row(
    modifier: Modifier = Modifier(),
    verticalAlignment: VerticalAlignment = VerticalAlignment.None,
    horizontalAlignment: HorizontalAlignment = HorizontalAlignment.None, className: String? = null, id: String? = null,
    content: DIV.() -> Unit
) {
    div(classes = className) {
        if (id != null) {
            this.id = id
        }
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

fun FlowContent.Spacer(
    className: String? = null, id: String? = null, modifier: Modifier = Modifier()
) {
    div(classes = className) {
        if (id != null) {
            this.id = id
        }
        style = modifier.buildStyle()
    }
}

fun FlowContent.Box(
    modifier: Modifier, className: String? = null, id: String? = null, init: DIV.() -> Unit
) {
    div(classes = className) {
        if (id != null) {
            this.id = id
        }
        style = modifier.buildStyle()
        init()
    }
}

fun FlowContent.Button(
    modifier: Modifier, className: String? = null, id: String? = null, onClick: () -> String, content: BUTTON.() -> Unit
) {
    button(classes = className, type = ButtonType.button) {
        if (id != null) {
            this.id = id
        }
        style = modifier.buildStyle()
        this.onClick = onClick()
        content()
    }
}

fun FlowContent.TextInputField(
    value: String,
    className: String? = null,
    id: String? = null,
    fontWeight: String,
    fontSize: String,
    fontFamily: String,
    modifier: Modifier,
    properties: (INPUT) -> Unit = {}
) {
    textInput(classes = className) {
        if (id != null) {
            this.id = id
        }
        style = """
            font-size: $fontSize;
            font-family: $fontFamily;
            font-weight: $fontWeight;
        """.trimIndent()+ modifier.buildStyle()
        this.value = value
        properties(this)
    }
}