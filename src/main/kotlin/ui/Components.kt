package sakethh.ui

import kotlinx.html.*

fun HTML.Surface(
    modifier: Modifier,
    /**List of font URLs*/
    fonts: List<String> = emptyList(),
    style: STYLE.() -> Unit = {},
    className: String? = null,
    id: String? = null,
    onTheHeadElement: HEAD.() -> Unit = {},
    onTheBodyElement: BODY.() -> Unit = {},
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
        onTheHeadElement()
    }
    body(classes = className) {
        if (id != null) {
            this.id = id
        }
        this.style = modifier.buildStyle()
        onTheBodyElement()
        content()
    }
}

fun HTML.Surface(
    style: String,
    className: String? = null,
    onThisElement: BODY.() -> Unit = {},
    id: String? = null,
    content: BODY.() -> Unit = {}
) {
    body(classes = className) {
        if (id != null) {
            this.id = id
        }
        this.style = style
        onThisElement()
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
    onThisElement: DIV.() -> Unit = {},
    modifier: Modifier = Modifier()
) {
    div(classes = className) {
        if (id != null) {
            this.id = id
        }
        style = buildString {
            append("font-weight: $fontWeight; ")
            append("color: $color; ")
            append("font-family: \"$fontFamily\"; ")
            append("font-size: $fontSize; ")
            append(modifier.buildStyle())
        }
        onThisElement()
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
    onThisElement: DIV.() -> Unit = {},
    content: DIV.() -> Unit
) {
    div(classes = className) {
        if (id != null) {
            this.id = id
        }
        style = buildString {
            append("display: flex; ")
            append("flex-direction: column; ")
            if (verticalAlignment.cssValue.isNotBlank()) {
                append("justify-content: ${verticalAlignment.cssValue}; ")
            }
            if (horizontalAlignment.cssValue.isNotBlank()) {
                append("align-items: ${horizontalAlignment.cssValue}; ")
            }
            append(modifier.buildStyle())
        }
        onThisElement()
        content()
    }
}


fun FlowContent.Row(
    modifier: Modifier = Modifier(),
    verticalAlignment: VerticalAlignment = VerticalAlignment.None,
    horizontalAlignment: HorizontalAlignment = HorizontalAlignment.None, className: String? = null, id: String? = null,
    onThisElement: DIV.() -> Unit = {},
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
        onThisElement()
        content()
    }
}

fun FlowContent.Spacer(
    className: String? = null, id: String? = null, onThisElement: DIV.() -> Unit = {}, modifier: Modifier = Modifier()
) {
    div(classes = className) {
        if (id != null) {
            this.id = id
        }
        style = modifier.buildStyle()
        onThisElement()
    }
}

fun FlowContent.Box(
    modifier: Modifier,
    className: String? = null,
    onThisElement: DIV.() -> Unit = {},
    id: String? = null,
    init: DIV.() -> Unit
) {
    div(classes = className) {
        if (id != null) {
            this.id = id
        }
        style = modifier.buildStyle()
        onThisElement()
        init()
    }
}

fun FlowContent.Button(
    modifier: Modifier,
    className: String? = null,
    onThisElement: BUTTON.() -> Unit = {},
    id: String? = null,
    onClick: () -> String,
    content: BUTTON.() -> Unit
) {
    button(classes = className, type = ButtonType.button) {
        if (id != null) {
            this.id = id
        }
        style = modifier.buildStyle()
        this.onClick = onClick()
        onThisElement()
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
    modifier: Modifier, onThisElement: INPUT.() -> Unit = {}
) {
    textInput(classes = className) {
        if (id != null) {
            this.id = id
        }
        style = buildString {
            append("font-size: $fontSize; ")
            append("font-family: $fontFamily; ")
            append("font-weight: $fontWeight; ")
            append(modifier.buildStyle())
        }
        this.value = value
        onThisElement()
    }
}