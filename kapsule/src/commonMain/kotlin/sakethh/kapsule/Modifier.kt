package sakethh.kapsule

import sakethh.kapsule.utils.Overflow
import sakethh.kapsule.utils.Shape
import sakethh.kapsule.utils.TransformBuilder

class Modifier {
    private val currentStyle = StringBuilder()

    fun backgroundColor(color: String) = this.also {
        currentStyle.append("background-color: $color; ")
    }

    fun color(value: String) = this.also {
        currentStyle.append("color: $value; ")
    }

    fun padding(value: String) = this.also {
        currentStyle.append("padding: $value; ")
    }

    fun margin(px: Int) = this.also {
        currentStyle.append("margin: ${px}px; ")
    }

    fun margin(top: String, bottom: String, start: String, end: String) = this.also {
        currentStyle.append("margin-top: $top; margin-bottom: $bottom; margin-inline-start: $start, margin-inline-end: $end; ")
    }

    fun border(radius: Int, color: String, width: Int = 1) = this.also {
        currentStyle.append("border: ${width}px solid $color; border-radius: ${radius}px; ")
    }

    fun border(radius: String, color: String, width: String) = this.also {
        currentStyle.append("border: $width solid $color; border-radius: $radius; ")
    }

    fun width(value: String) = this.also {
        currentStyle.append("width: $value; ")
    }

    fun height(value: String) = this.also {
        currentStyle.append("height: $value; ")
    }

    fun display(value: String) = this.also {
        currentStyle.append("display: $value; ")
    }

    fun opacity(value: Double) = this.also {
        currentStyle.append("opacity: $value; ")
    }

    fun custom(properties: String) = this.also {
        currentStyle.append("$properties ")
    }

    fun position(position: String) = this.also {
        currentStyle.append("position: $position; ")
    }

    fun justifyContent(value: String) = this.also {
        currentStyle.append("justify-content: $value; ")
    }

    fun flexDirection(value: String) = this.also {
        currentStyle.append("flex-direction: $value;")
    }

    fun gap(px: Int) = this.also {
        currentStyle.append("gap: ${px}px;")
    }

    fun flexGrow(value: Int) = this.also {
        currentStyle.append("flex-grow: $value;")
    }

    /**
    Sets width to 100% of the parent.
    - Use this only when the parent has a defined width.
     */
    fun fillMaxWidth(fraction: Double = 1.0) = this.also {
        require(fraction in 0.0..1.0) { "Fraction must be between 0 and 1" }
        currentStyle.append("width: ${fraction * 100}%; ")
    }

    /**
    Sets height to 100% of the parent.
    - Use this only when the parent has a defined height.
     */
    fun fillMaxHeight(fraction: Double = 1.0) = this.also {
        require(fraction in 0.0..1.0) { "Fraction must be between 0 and 1" }
        currentStyle.append("height: ${fraction * 100}%; ")
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
        currentStyle.append("width: 100%; ")
    }

    fun matchParentHeight() = this.also {
        currentStyle.append("height: 100%; ")
    }

    fun matchParentSize() = this.also {
        matchParentWidth()
        matchParentHeight()
    }

    fun then(other: Modifier): Modifier {
        val combinedModifier = Modifier()
        combinedModifier.currentStyle.append(this.currentStyle)
        combinedModifier.currentStyle.append(other.currentStyle)
        return combinedModifier
    }

    /**
    Use `fillViewportSize()` when you want an element to fill the full screen.
     */
    fun fillViewportSize() = this.also {
        currentStyle.append("width: 100vw; height: 100vh; ")
    }

    fun minHeight(value: String) = this.also {
        currentStyle.append("min-height: $value; ")
    }

    fun minWidth(value: String) = this.also {
        currentStyle.append("min-width: $value; ")
    }

    fun zIndex(value: Int) = this.also {
        currentStyle.append("z-index: $value; ")
    }

    fun boxSizing(value: String) = this.also {
        currentStyle.append("box-sizing: $value; ")
    }

    fun cursor(value: String) = this.also {
        currentStyle.append("cursor: $value; ")
    }

    fun transition(value: String) = this.also {
        currentStyle.append("transition: $value; ")
    }

    fun transform(transformBuilder: TransformBuilder) = this.also {
        require(transformBuilder.buildTransformation().trim().isNotBlank()) {
            "Invalid transformation. Apply valid transformations.\nNote: `.transform(TransformBuilder())` is not valid. Please apply actual transformations using methods provided by the TransformBuilder."
        }

        currentStyle.append(
            "transform: ${
            transformBuilder.buildTransformation().run {
                if (this.endsWith(";")) {
                    this
                } else {
                    "$this; "
                }
            }
        }")
    }

    fun clip(shape: Shape, overflow: Overflow = Overflow.Hidden) = this.also {
        require(shape.buildPath().isNotBlank() && overflow.value.isNotBlank()) {
            "Both shape path and overflow value must be provided and cannot be empty."
        }

        currentStyle.append(
            "clip-path: ${
            shape.buildPath().run {
                if (this.trim().endsWith(";")) {
                    this
                } else {
                    "$this; "
                }
            }
        } overflow: ${overflow.value}; ")
    }

    fun buildStyle(): String = currentStyle.toString().trim()
}