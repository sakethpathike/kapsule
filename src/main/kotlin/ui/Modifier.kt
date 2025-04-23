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

    /**
    Sets width to 100% of the parent.
    - Use this only when the parent has a defined width.
     */
    fun fillMaxWidth(fraction: Double = 1.0) = this.also {
        require(fraction in 0.0..1.0) { "Fraction must be between 0 and 1" }
        finalStyle.append("width: ${fraction * 100}%; ")
    }

    /**
    Sets height to 100% of the parent.
    - Use this only when the parent has a defined height.
     */
    fun fillMaxHeight(fraction: Double = 1.0) = this.also {
        require(fraction in 0.0..1.0) { "Fraction must be between 0 and 1" }
        finalStyle.append("height: ${fraction * 100}%; ")
    }

    /**
    Sets width and height to 100% of the parent.
    - Use this only when the parent has a defined height and width.
    - Use [fillViewportSize] when you want to fill the full screen.
     * */
    fun fillMaxSize(fraction: Double = 1.0) = this.also {
        fillMaxWidth(fraction)
        fillMaxHeight(fraction)
    }

    fun matchParentWidth() = this.also {
        finalStyle.append("width: 100%; ")
    }

    fun matchParentHeight() = this.also {
        finalStyle.append("height: 100%; ")
    }

    fun matchParentSize() = this.also {
        matchParentWidth()
        matchParentHeight()
    }

    fun then(other: Modifier): Modifier {
        val combinedModifier = Modifier()
        combinedModifier.finalStyle.append(this.finalStyle)
        combinedModifier.finalStyle.append(other.finalStyle)
        return combinedModifier
    }

    /**
    Use `fillViewportSize()` when you want an element to fill the full screen.
     */
    fun fillViewportSize() = this.also {
        finalStyle.append("width: 100vw; height: 100vh; ")
    }

    fun minHeight(value: String) = this.also {
        finalStyle.append("min-height: $value; ")
    }

    fun minWidth(value: String) = this.also {
        finalStyle.append("min-width: $value; ")
    }

    fun buildStyle(): String = finalStyle.toString().trim()
}
