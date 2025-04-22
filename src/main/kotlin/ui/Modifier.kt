package sakethh.ui


class Modifier {
    private val finalStyle = StringBuilder()

    fun backgroundColor(color: String) = this.also {
        finalStyle.append("background-color: $color; ")
    }

    fun padding(px: Int) = this.also {
        finalStyle.append("padding: ${px}px; ")
    }

    fun margin(px: Int) = this.also {
        finalStyle.append("margin: ${px}px; ")
    }

    fun border(radius: Int, color: String, width: Int = 1) = this.also {
        finalStyle.append("border: ${width}px solid $color; border-radius: ${radius}px; ")
    }

    fun border(radius: String, color: String, width: String) = this.also {
        finalStyle.append("border: $width solid $color; border-radius: $radius; ")
    }

    fun width(value: String) = this.also {
        finalStyle.append("width: $value; ")
    }

    fun height(value: String) = this.also {
        finalStyle.append("height: $value; ")
    }

    fun display(value: String) = this.also {
        finalStyle.append("display: $value; ")
    }

    fun opacity(value: Double) = this.also {
        finalStyle.append("opacity: $value; ")
    }

    fun custom(properties: String) = this.also {
        finalStyle.append("$properties ")
    }

    fun position(position: String) = this.also {
        finalStyle.append("position: $position; ")
    }

    fun justifyContent(value: String) = this.also {
        finalStyle.append("justify-content: $value; ")
    }

    fun flexDirection(value: String) = this.also {
        finalStyle.append("flex-direction: $value;")
    }

    fun gap(px: Int) = this.also {
        finalStyle.append("gap: ${px}px;")
    }

    fun flexGrow(value: Int) = this.also {
        finalStyle.append("flex-grow: $value;")
    }

    fun then(other: Modifier): Modifier {
        val combinedModifier = Modifier()
        combinedModifier.finalStyle.append(this.finalStyle)
        combinedModifier.finalStyle.append(other.finalStyle)
        return combinedModifier
    }

    fun buildStyle(): String = finalStyle.toString().trim()
}
