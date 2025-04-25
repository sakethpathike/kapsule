package sakethh.kapsule

import sakethh.kapsule.utils.*

class Modifier {
    private val currentStyle = StringBuilder()

    fun backgroundColor(color: String) = apply {
        currentStyle.append("background-color: $color; ")
    }

    fun color(value: String) = apply {
        currentStyle.append("color: $value; ")
    }

    fun padding(value: String) = apply {
        currentStyle.append("padding: $value; ")
    }

    fun margin(px: Int) = apply {
        currentStyle.append("margin: ${px}px; ")
    }

    fun margin(top: String, bottom: String, start: String, end: String) = apply {
        currentStyle.append("margin-top: $top; margin-bottom: $bottom; margin-inline-start: $start, margin-inline-end: $end; ")
    }

    fun border(radius: Int, color: String, width: Int = 1) = apply {
        currentStyle.append("border: ${width}px solid $color; border-radius: ${radius}px; ")
    }

    fun border(radius: String, color: String, width: String) = apply {
        currentStyle.append("border: $width solid $color; border-radius: $radius; ")
    }

    fun width(value: String) = apply {
        currentStyle.append("width: $value; ")
    }

    fun height(value: String) = apply {
        currentStyle.append("height: $value; ")
    }

    fun display(value: String) = apply {
        currentStyle.append("display: $value; ")
    }

    fun opacity(value: Double) = apply {
        currentStyle.append("opacity: $value; ")
    }

    fun custom(properties: String) = apply {
        currentStyle.append("$properties ")
    }

    fun position(position: String) = apply {
        currentStyle.append("position: $position; ")
    }

    fun justifyContent(value: String) = apply {
        currentStyle.append("justify-content: $value; ")
    }

    fun flexDirection(value: String) = apply {
        currentStyle.append("flex-direction: $value;")
    }

    fun gap(px: Int) = apply {
        currentStyle.append("gap: ${px}px;")
    }

    fun flexGrow(value: Int) = apply {
        currentStyle.append("flex-grow: $value;")
    }

    /**
    Sets width to 100% of the parent.
    - Use this only when the parent has a defined width.
     */
    fun fillMaxWidth(fraction: Double = 1.0) = apply {
        require(fraction in 0.0..1.0) { "Fraction must be between 0 and 1" }
        currentStyle.append("width: ${fraction * 100}%; ")
    }

    /**
    Sets height to 100% of the parent.
    - Use this only when the parent has a defined height.
     */
    fun fillMaxHeight(fraction: Double = 1.0) = apply {
        require(fraction in 0.0..1.0) { "Fraction must be between 0 and 1" }
        currentStyle.append("height: ${fraction * 100}%; ")
    }

    /**
    Sets width and height to 100% of the parent.
    - Use this only when the parent has a defined height and width.
    - Use [fillViewportSize] when you want to fill the full screen.
     * */
    fun fillMaxSize(fraction: Double = 1.0) = apply {
        fillMaxWidth(fraction)
        fillMaxHeight(fraction)
    }

    fun matchParentWidth() = apply {
        currentStyle.append("width: 100%; ")
    }

    fun matchParentHeight() = apply {
        currentStyle.append("height: 100%; ")
    }

    fun matchParentSize() = apply {
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
    fun fillViewportSize() = apply {
        currentStyle.append("width: 100vw; height: 100vh; ")
    }

    fun minHeight(value: String) = apply {
        currentStyle.append("min-height: $value; ")
    }

    fun minWidth(value: String) = apply {
        currentStyle.append("min-width: $value; ")
    }

    fun zIndex(value: Int) = apply {
        currentStyle.append("z-index: $value; ")
    }

    fun boxSizing(value: String) = apply {
        currentStyle.append("box-sizing: $value; ")
    }

    fun cursor(value: String) = apply {
        currentStyle.append("cursor: $value; ")
    }

    fun transition(transitionBuilder: TransitionBuilder) = apply {
        require(transitionBuilder.buildTransition().trim().isNotBlank()){
            "Invalid transition. Apply valid transitions.\nNote: `.transition(TransitionBuilder())` is not valid. Please apply actual transitions using functions provided by the TransitionBuilder."
        }

        currentStyle.append("transition: ${transitionBuilder.buildTransition().ensureSemicolon()}; ")
    }

    fun transform(transformBuilder: TransformBuilder) = apply {
        require(transformBuilder.buildTransformation().trim().isNotBlank()) {
            "Invalid transformation. Apply valid transformations.\nNote: `.transform(TransformBuilder())` is not valid. Please apply actual transformations using functions provided by the TransformBuilder."
        }

        currentStyle.append(
            "transform: ${
            transformBuilder.buildTransformation().ensureSemicolon()
        }")
    }

    fun clip(shape: Shape, overflow: Overflow = Overflow.Hidden) = apply {
        require(shape.buildPath().isNotBlank() && overflow.value.isNotBlank()) {
            "Both shape path and overflow value must be provided and cannot be empty."
        }

        currentStyle.append(
            "clip-path: ${
                shape.buildPath().ensureSemicolon()
        } overflow: ${overflow.value}; ")
    }

    fun buildStyle(): String = currentStyle.toString().trim()
}