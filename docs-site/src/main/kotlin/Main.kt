import kotlinx.html.DIV
import kotlinx.html.html
import kotlinx.html.stream.createHTML
import sakethh.kapsule.*
import sakethh.kapsule.utils.*
import java.io.File
import java.nio.file.Files

fun main() {
    createHTML().html {
        Surface(
            fonts = listOf("https://fonts.googleapis.com/css2?family=Abril+Fatface&family=Poppins&family=JetBrains+Mono&display=swap"),
            modifier = Modifier().padding(0.px).margin(0).backgroundColor("#292929")
        ) {
            Row(
                modifier = Modifier().fillMaxWidth().height(65.px).backgroundColor("#1b1b1b"),
                verticalAlignment = VerticalAlignment.SpaceBetween,
                horizontalAlignment = HorizontalAlignment.Center
            ) {
                Text(
                    modifier = Modifier().margin(start = 15.px),
                    text = "kapsule",
                    fontFamily = "Abril Fatface",
                    color = "white",
                    fontSize = 25.px,
                    fontWeight = 45.px
                )
                Button(
                    modifier = Modifier().cursor(Cursor.Pointer).margin(end = 15.px).height(35.px)
                        .clip(shape = Shape.RoundedRectangle(12.px)).backgroundColor("#d6d6d6"), onClick = {
                        """
                        window.open("https://github.com/sakethpathike/kapsule", "_blank");
                    """.trimIndent()
                    }) {
                    Text(
                        text = "⭐ on Github",
                        fontFamily = "Poppins",
                        color = "black",
                        fontSize = 14.px,
                        fontWeight = "0"
                    )
                }
            }
            Spacer(modifier = Modifier().fillMaxWidth().height(1.25.px).backgroundColor("#ffffff"))
            Row(modifier = Modifier().fillMaxSize()) {
                Column(
                    Modifier().fillMaxHeight().width((20.vw)).padding(value = 15.px).backgroundColor(color = "#1b1b1b")
                ) {
                    Text(
                        text = "Getting started",
                        fontSize = 20.px,
                        fontWeight = "bold",
                        fontFamily = "Poppins",
                        color = "white"
                    )
                    Spacer(modifier = Modifier().height(10.px))
                    SidebarSelectedTextComponent(selected = true, text = "Adding dependencies")
                }
            }
        }
    }.toString().let {
        File(System.getProperty("user.home"), "/Documents/kapsule-docs.html").also { file ->
            Files.write(file.toPath(), it.toByteArray())
        }
    }
}

fun DIV.SidebarSelectedTextComponent(selected: Boolean, text: String, onThisElement: DIV.() -> Unit = {}) {
    Row(
        horizontalAlignment = HorizontalAlignment.Center,
        modifier = Modifier().cursor(Cursor.Pointer)
            .then(if (selected) Modifier().backgroundColor("#292929") else Modifier())
            .clip(Shape.RoundedRectangle(right = 15f, cornerRadius = 10.px)),
        onThisElement = {
            onThisElement()
        }) {
        if (selected) {
            Spacer(modifier = Modifier().margin(end = 15.px).height(25.px).width(4.px).backgroundColor("#ffffff"))
        } else {
            Text(
                text = Typography.bullet.toString(),
                fontSize = 16.px,
                fontWeight = "25",
                fontFamily = "Poppins",
                color = "white",
                modifier = Modifier().margin(end = 5.px),
            )
        }
        Text(
            modifier = Modifier().margin(end = 15.px),
            text = text,
            fontSize = 16.px,
            fontWeight = "25",
            fontFamily = "Poppins",
            color = "white"
        )
    }
}