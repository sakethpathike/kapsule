package sakethh.kapsule

import kotlinx.html.*
import sakethh.kapsule.utils.*

fun HTML.Surface(
    modifier: Modifier = Modifier(),
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
    fontFamily: String? = null,
    color: String? = null,
    fontWeight: FontWeight? = null,
    display: Display? = Display.Inline,
    className: String? = null,
    id: String? = null,
    textAlign: TextAlign = TextAlign.Start,
    onThisElement: DIV.() -> Unit = {},
    modifier: Modifier = Modifier()
) {
    div(classes = className) {
        if (id != null) {
            this.id = id
        }
        style = buildString {

            if (fontWeight != null) {
                append("font-weight: ${fontWeight.cssValue()}; ")
            }

            if (color != null) {
                append("color: $color; ")
            }

            if (fontFamily != null) {
                append("font-family: \"$fontFamily\"; ")
            }
            append("font-size: $fontSize; ")
            append("text-align: ${textAlign.cssValue}; ")
            if (display != null) {
                append("display: ${display.cssValue}; ")
            }
            append(modifier.buildStyle())
        }
        onThisElement()
        unsafe {
            raw(text.replace("\n", "<br>"))
        }
    }
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
            if (verticalAlignment.value.isNotBlank()) {
                append("justify-content: ${verticalAlignment.value}; ")
            }
            if (horizontalAlignment.value.isNotBlank()) {
                append("align-items: ${horizontalAlignment.value}; ")
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
            if (verticalAlignment.value.isNotBlank()) {
                append("justify-content: ${verticalAlignment.value}; ")
            }
            if (horizontalAlignment.value.isNotBlank()) {
                append("align-items: ${horizontalAlignment.value}; ")
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
    modifier: Modifier = Modifier(),
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
    id: String? = null, fontWeight: FontWeight,
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
            append("font-weight: ${fontWeight.cssValue()}; ")
            append(modifier.buildStyle())
        }
        this.value = value
        onThisElement()
    }
}

fun FlowContent.BreakFlow(onThisElement: BR.() -> Unit = {}, className: String? = null, id: String? = null) {
    br(classes = className) {
        if (id != null) {
            this.id = id
        }
        onThisElement()
    }
}

private val headingBuilders: List<FlowContent.(className: String?, block: FlowOrHeadingContent.() -> Unit) -> Unit> =
    listOf(
        FlowContent::h1, FlowContent::h2, FlowContent::h3, FlowContent::h4, FlowContent::h5, FlowContent::h6
    )

fun FlowContent.Heading(
    level: Int,
    text: String,
    className: String? = null,
    id: String? = null, onThisElement: FlowOrHeadingContent.() -> Unit = {}, modifier: Modifier = Modifier()
) {
    require(level in 1..6) { "Invalid heading level: $level" }

    headingBuilders[level - 1].invoke(this, className) {
        id?.let { attributes["id"] = it }
        attributes["style"] = modifier.buildStyle()
        +text
        onThisElement()
    }
}

fun FlowContent.InlineCode(
    code: String,
    className: String? = null,
    id: String? = null,
    modifier: Modifier = Modifier().backgroundColor("#BFC2FF").borderRadius(4.px).color("#272B60")
        .custom("padding:2px 4px;")
) {
    code(classes = className) {
        if (id != null) {
            this.id = id
        }
        style = modifier.buildStyle()
        +code
    }
}

fun FlowContent.Image(
    modifier: Modifier = Modifier(),
    alt: String? = null,
    src: String,
    id: String? = null,
    imgLoading: ImgLoading? = null,
    className: String? = null,
    contentScale: ContentScale = ContentScale.Cover,
    alignment: ObjectPosition = ObjectPosition.Predefined.Center,
    onThisElement: IMG.() -> Unit = {}
) {
    img(alt = alt, src = src, loading = imgLoading, classes = className) {
        style = buildString {
            append("object-position: ${alignment.position()}; ")
            append("object-fit: ${contentScale.cssValue}; ")
            append(modifier.buildStyle())
        }
        id?.let {
            this.id = it
        }
        onThisElement()
    }
}

fun FlowContent.StaggeredGrid(
    modifier: Modifier = Modifier(),
    columnCount: Int,
    columnGap: String = 16.px,
    className: String? = null,
    id: String? = null,
    onThisElement: DIV.() -> Unit = {},
    content: DIV.() -> Unit
) {
    div(classes = className) {
        if (id != null) {
            this.id = id
        }
        style = buildString {
            append("column-count: $columnCount; ")
            append("column-gap: $columnGap; ")
            append(modifier.buildStyle())
        }
        onThisElement()
        content()
    }
}

fun FlowContent.MaterialIcon(
    iconCode: String,
    id: String? = null,
    modifier: Modifier = Modifier(),
    onClick: String? = null,
    onThisElement: SPAN.() -> Unit = {}
) {
    span(classes = "material-icons") {
        if (id != null) {
            this.id = id
        }
        style = modifier.buildStyle()
        onThisElement()
        if (onClick != null) {
            this.onClick = onClick
        }
        +iconCode
    }
}

fun FlowContent.Span(
    className: String? = null,
    id: String? = null,
    modifier: Modifier = Modifier(),
    onThisElement: SPAN.() -> Unit,
    content: SPAN.() -> Unit
) {
    span(classes = className) {
        if (id != null) {
            this.id = id
        }
        style = modifier.buildStyle()
        onThisElement()
        content()
    }
}

fun FlowContent.Div(
    className: String? = null,
    id: String? = null,
    modifier: Modifier = Modifier(),
    onThisElement: DIV.() -> Unit = {},
    content: DIV.() -> Unit
) {
    div(classes = className) {
        if (id != null) {
            this.id = id
        }
        style = modifier.buildStyle()
        onThisElement()
        content()
    }
}