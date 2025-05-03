package sakethh.kapsule

import sakethh.kapsule.utils.*

class Modifier {
    private val currentStyle = StringBuilder()

    private fun appendNewStyle(property: String, value: Any) = apply {
        currentStyle.append("$property: $value; ")
    }

    fun backgroundColor(color: String) = apply {
        appendNewStyle(property = "background-color", value = color)
    }

    fun color(value: String) = apply {
        appendNewStyle(property = "color", value)
    }

    fun padding(value: String) = apply {
        appendNewStyle(property = "padding", value)
    }

    fun padding(top: String = 0.px, bottom: String = 0.px, start: String = 0.px, end: String = 0.px) = apply {
        appendNewStyle(property = "padding-top", value = top)
        appendNewStyle(property = "padding-end", value = end)
        appendNewStyle(property = "padding-start", value = start)
        appendNewStyle(property = "padding-bottom", value = bottom)
    }

    fun margin(px: Int) = apply {
        appendNewStyle(property = "margin", value = px.px)
    }

    fun margin(value: String) = apply {
        appendNewStyle(property = "margin", value = value)
    }

    fun margin(top: String = 0.px, bottom: String = 0.px, start: String = 0.px, end: String = 0.px) = apply {
        appendNewStyle(property = "margin-top", value = top)
        appendNewStyle(property = "margin-bottom", value = bottom)
        appendNewStyle(property = "margin-inline-start", value = start)
        appendNewStyle(property = "margin-inline-end", value = end)
    }

    fun border(radius: Int, color: String, width: Int = 1) = apply {
        currentStyle.append("border: ${width}px solid $color; border-radius: ${radius}px; ")
    }

    fun border(radius: String, color: String, width: String) = apply {
        currentStyle.append("border: $width solid $color; border-radius: $radius; ")
    }

    fun borderRadius(value: String) = apply {
        appendNewStyle(property = "border-radius", value)
    }

    fun width(value: String) = apply {
        currentStyle.append("width: $value; ")
    }

    fun height(value: String) = apply {
        currentStyle.append("height: $value; ")
    }

    fun opacity(value: Double) = apply {
        currentStyle.append("opacity: $value; ")
    }

    fun fontFamily(value: String) = apply {
        appendNewStyle(property = "font-family", value)
    }

    fun size(value: String) = apply {
        height(value)
        width(value)
    }
    /**
     * Appends raw CSS properties directly to the current modifier chain.
     *
     * Useful for adding custom styles that are not yet covered by predefined functions.
     * */
    fun custom(properties: String) = apply {
        currentStyle.append("$properties ".ensureSemicolon())
    }

    /**
     * Sets the `position` CSS property, which specifies how an element is positioned
     * in a document and how it interacts with the normal flow of the page.
     *
     * @param position Defines the positioning scheme used for the element.
     * @see <a href="https://developer.mozilla.org/en-US/docs/Web/CSS/position">MDN Documentation on position</a>
     */
    fun position(position: Position) = apply {
        appendNewStyle(property = "position", value = position.cssValue)
    }


    /**
     * Sets CSS `flex-grow` using predefined [FlexGrow] keyword values.
     *
     * These values modify inheritance/reset behavior:
     * - [FlexGrow.Inherit] - Inherits parent's `flex-grow` ([MDN Documentation on Reference](https://developer.mozilla.org/en-US/docs/Web/CSS/inherit))
     * - [FlexGrow.Initial] - Resets to default (`0`) ([MDN Documentation on Reference](https://developer.mozilla.org/en-US/docs/Web/CSS/initial))
     * - [FlexGrow.Revert] - Reverts to browser stylesheet ([MDN Documentation on Reference](https://developer.mozilla.org/en-US/docs/Web/CSS/revert))
     * - [FlexGrow.Unset] - Auto-inherits/resets ([MDN Documentation on Reference](https://developer.mozilla.org/en-US/docs/Web/CSS/unset))
     *
     * ### When to Use:
     * For resetting/inheriting values rather than proportional growth.
     *
     * @see <a href="https://developer.mozilla.org/en-US/docs/Web/CSS/flex-grow">MDN Documentation on flex-grow</a>
     */
    fun flexGrow(flexGrow: FlexGrow) = apply {
        appendNewStyle("flex-grow", flexGrow.cssValue)
    }

    /**
     * Sets the flex item's growth factor (how much it grows relative to siblings).
     *
     * - **0**: No growth (default)
     * - **1**: Equal share of remaining space
     * - **≥2**: Proportionally more space than items with lower values
     *
     * ### Key Behaviors:
     * - Negative values are **invalid** (treated as `0`)
     * - Decimal values work (e.g., `0.5` takes half the space of `1`)
     * - Relative to other items' `flex-grow` values
     *
     * @param value Non-negative growth factor (typically 0, 1, or 2)
     * @see <a href="https://developer.mozilla.org/en-US/docs/Web/CSS/flex-grow">MDN Documentation on flex-grow</a>
     */
    fun flexGrow(value: Float) = apply {
        require(value >= 0) { "flex-grow must be ≥ 0 (was $value)" }
        appendNewStyle("flex-grow", value)
    }

    /**
    Sets width to 100% of the parent.
    - Use this only when the parent has a defined width.
     */
    fun fillMaxWidth(fraction: Double = 1.0) = apply {
        require(fraction in 0.0..1.0) { "Fraction must be between 0 and 1" }
        appendNewStyle(property = "width", value = (fraction * 100).percent)
    }

    /**
    Sets height to 100% of the parent.
    - Use this only when the parent has a defined height.
     */
    fun fillMaxHeight(fraction: Double = 1.0) = apply {
        require(fraction in 0.0..1.0) { "Fraction must be between 0 and 1" }
        appendNewStyle(property = "height", value = (fraction * 100).percent)
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
        appendNewStyle(property = "width", value = 100.percent)
    }

    fun matchParentHeight() = apply {
        appendNewStyle(property = "height", value = 100.percent)
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
        appendNewStyle(property = "width", value = 100.vw)
        appendNewStyle(property = "height", value = 100.vh)
    }

    fun minHeight(value: String) = apply {
        appendNewStyle(property = "min-height", value)
    }

    fun minWidth(value: String) = apply {
        appendNewStyle(property = "min-width", value = value)
    }

    /**
     * Sets the z-order of an element using a numeric [value].
     *
     * Controls element stacking context:
     * - Higher values appear **above** lower values
     * - Negative values can appear behind parent's background
     * - Auto-stacking occurs when `z-index` is equal
     *
     * ### Example Usage:
     * ```kotlin
     * .zIndex(10)   // Renders on top of default content
     * .zIndex(-1)   // Renders behind parent background
     * .zIndex(999)  // Common overlay value
     * ```
     *
     * @param value Stacking order (higher = visually closer).
     *             Typical range: -1 to 100 (extreme values work but are discouraged).
     *
     * @see <a href="https://developer.mozilla.org/en-US/docs/Web/CSS/z-index">MDN Documentation on z-index</a>
     */
    fun zIndex(value: Int) = apply {
        appendNewStyle(property = "z-index", value)
    }

    /**
     * Sets the CSS `box-sizing` property using type-safe [BoxSizing] values.
     *
     * This controls how an element's width/height is calculated:
     *
     * - [BoxSizing.ContentBox] (default):
     *      - `width`/`height` = content dimensions only
     *      - Padding and border add to total size
     *      - Traditional web layout behavior
     *
     * - [BoxSizing.BorderBox]:
     *      - `width`/`height` include content + padding + border
     *      - More predictable sizing (recommended for modern layouts)
     *      - Matches mobile layout systems (iOS/Android)
     *
     * ### Example Usage:
     * ```kotlin
     * // Modern layout approach (recommended)
     * .boxSizing(BoxSizing.BorderBox)
     *
     * // Legacy browser behavior
     * .boxSizing(BoxSizing.ContentBox)
     * ```
     *
     * @see <a href="https://developer.mozilla.org/en-US/docs/Web/CSS/box-sizing">MDN Documentation on box-sizing</a>
     */
    fun boxSizing(boxSizing: BoxSizing) = apply {
        appendNewStyle(property = "box-sizing", value = boxSizing.cssValue)
    }

    /**
     * Sets a custom `box-sizing` value using raw CSS.
     *
     * ⚠️ Use this **only** for:
     * - Browser-specific experimental values
     * - Non-standard implementations
     *
     * For 99% of cases, prefer the type-safe [BoxSizing] enum:
     * ```kotlin
     * .boxSizing(BoxSizing.BorderBox)  // ← Recommended
     * ```
     *
     * @param value Must be a valid CSS `box-sizing` value
     * @throws IllegalArgumentException if blank
     *
     * @see BoxSizing For standard (`content-box`/`border-box`) options
     * @see <a href="https://developer.mozilla.org/en-US/docs/Web/CSS/box-sizing">MDN Documentation on box-sizing</a>
     */
    fun boxSizing(value: String) = apply {
        require(value.isNotBlank()) { "Box-sizing value cannot be blank. Please provide a valid value (e.g., 'content-box' or 'border-box')." }

        appendNewStyle(property = "box-sizing", value = value)
    }

    /**
     * Sets the mouse cursor style using a predefined type-safe option from the [Cursor] enum.
     *
     * This is the **recommended approach** for standard cursor styles, as it ensures:
     * - Compile-time safety (no typos)
     * - IDE autocompletion
     *
     * ### Commonly Used Cursors:
     * - Interactive: [Cursor.Pointer] (↗️ link clickable)
     * - Text: [Cursor.Text] (I-beam)
     * - Wait states: [Cursor.Wait] (⏳), [Cursor.Progress] (🌀)
     * - Drag: [Cursor.Grab] (👆), [Cursor.Grabbing] (✊)
     * - Not allowed: [Cursor.NotAllowed] (🚫)
     *
     * ### Full Cursor Options:
     * See all available cursors in the [Cursor] enum class.
     *
     * @param cursor One of the predefined cursor styles from [Cursor]
     *
     * Example:
     * ```kotlin
     * // For clickable elements
     * .cursor(Cursor.Pointer)
     *
     * // For draggable items
     * .cursor(Cursor.Grab)
     * ```
     * @see <a href="https://developer.mozilla.org/en-US/docs/Web/CSS/cursor">MDN Documentation on cursor</a>
     */
    fun cursor(cursor: Cursor) = apply {
        appendNewStyle(property = "cursor", value = cursor.cssValue)
    }

    /**
     * Sets a custom cursor style using raw CSS syntax.
     *
     * Use this **only** when you need:
     * 1. Browser-specific cursors not in [Cursor] enum
     * 2. Custom image cursors
     * 3. Experimental cursor values
     *
     * ### Common Advanced Use Cases:
     * ```kotlin
     * // Custom image cursor with fallback
     * .cursor("url(custom.cur), auto")
     *
     * // Browser-specific resize cursor
     * .cursor("nesw-resize")  // diagonal double-arrow
     *
     * // Multiple fallbacks
     * .cursor("url(pointer.cur), url(pointer.png), pointer")
     * ```
     *
     * @param value Raw CSS `cursor` value. Must be non-blank.
     * @throws IllegalArgumentException if value is empty/blank
     *
     * ### Security Note:
     * - Image cursors may be restricted by browsers
     * - Always provide fallbacks: `"url(...), default"`
     *
     * @see [Cursor] For standard, type-safe cursor options
     * @see <a href="https://developer.mozilla.org/en-US/docs/Web/CSS/cursor">MDN Documentation on cursor</a>
     */
    fun cursor(value: String) = apply {
        require(value.isNotBlank()) {
            "Cursor value cannot be blank. Please provide a valid cursor style (e.g., 'pointer', 'default')."
        }

        appendNewStyle(property = "cursor", value = value)
    }

    /**
     * Applies CSS transitions to the element using a [TransitionBuilder] to define animation behavior.
     *
     * Configures smooth transitions for CSS properties by specifying:
     * - **Duration** (e.g., `duration(0.3f, "s")` for 300ms)
     * - **Timing function** (e.g., `timingFunction(TimingFunction.EaseInOut)`)
     * - **Delay** (e.g., `delay(0.1f, "s")` for 100ms)
     *
     * The transitions are combined into a single CSS `transition` property.
     *
     * @param transitionBuilder Configured [TransitionBuilder] instance with at least one transition rule.
     *
     * @throws IllegalArgumentException If the builder is empty (no transitions defined).
     *
     * ### Example Usage:
     * ```kotlin
     * .transition(
     *     TransitionBuilder()
     *         .duration(0.3f)  // Default unit: seconds (0.3s)
     *         .timingFunction(TimingFunction.EaseInOut)
     *         .delay(0.1f)     // Starts after 100ms
     * )
     * ```
     *
     * ### Custom Transitions:
     * For advanced use cases, pass raw CSS strings with `.custom()`:
     * ```kotlin
     * .transition(
     *     TransitionBuilder().custom("opacity 200ms cubic-bezier(0.4, 0, 0.2, 1)")
     * )
     * ```
     */
    fun transition(transitionBuilder: TransitionBuilder) = apply {
        require(transitionBuilder.buildTransition().trim().isNotBlank()){
            "Invalid transition. Apply valid transitions.\nNote: `.transition(TransitionBuilder())` is not valid. Please apply actual transitions using functions provided by the TransitionBuilder."
        }

        appendNewStyle(
            property = "transition", value = transitionBuilder.buildTransition().ensureSemicolon()
        )
    }

    /**
     * Applies CSS transformations to the element using the provided [TransformBuilder].
     *
     * This function allows chaining transformations (e.g., rotate, scale, translate)
     * defined in the [TransformBuilder]. The transformations are combined into a single
     * CSS `transform` property.
     *
     * @param transformBuilder A [TransformBuilder] instance containing the transformations to apply.
     *
     * @throws IllegalArgumentException If no valid transformations are provided (e.g., empty builder).
     *
     * Example usage:
     * ```
     * .transform(
     *     TransformBuilder()
     *         .rotate(45.deg)
     *         .scale(1.2f)
     * )
     * ```
     *
     * Note: An empty [TransformBuilder] (e.g., `TransformBuilder()`) will throw an exception.
     * Use at least one transformation function (e.g., `rotate()`, `scale()`).
     */
    fun transform(transformBuilder: TransformBuilder) = apply {
        require(transformBuilder.buildTransformation().trim().isNotBlank()) {
            "Invalid transformation. Apply valid transformations.\nNote: `.transform(TransformBuilder())` is not valid. Please apply actual transformations using functions provided by the TransformBuilder."
        }

        appendNewStyle(
            property = "transform", value = transformBuilder.buildTransformation().ensureSemicolon()
        )
    }

    /**
     * Applies a clipping path to the element with the specified shape and overflow behavior.
     *
     * The element's content will be clipped according to the provided [shape], and overflow handling
     * is controlled by [overflow] (defaults to [Overflow.Hidden]).
     *
     * @param shape The [Shape] defining the clipping path (e.g., rectangle, polygon, or custom path).
     * @param overflow The [Overflow] behavior for content outside the clip path
     *                 (e.g., `Hidden`, `Visible`). Defaults to `Hidden`.
     *
     * @throws IllegalArgumentException If the shape's path or overflow CSS value is blank.
     *
     * Example usage with [Shape.Rectangle]:
     * ```
     * .clip(
     *     shape = Shape.Rectangle(x = 10f, y = 10f, width = 100f, height = 50f),
     *     overflow = Overflow.Hidden
     * )
     * ```
     */
    fun clip(shape: Shape, overflow: Overflow = Overflow.Hidden) = apply {
        require(shape.buildPath().isNotBlank() && overflow.cssValue.isNotBlank()) {
            "Both shape path and overflow value must be provided and cannot be empty."
        }
        appendNewStyle(property = "clip-path", value = shape.buildPath().ensureSemicolon())
        appendNewStyle(property = "overflow", value = overflow.cssValue)
    }
    
    /**
     * Applies the CSS `display` property to the current element.
     *
     * The [Display] enum provides a type-safe way to specify common display values
     * without needing to manually write CSS strings.
     *
     * @param display The [Display] value representing the desired CSS display behavior.
     *
     * @see <a href="https://developer.mozilla.org/en-US/docs/Web/CSS/display">MDN Documentation on display</a>
     */
    fun display(display: Display) = apply {
        appendNewStyle(property = "display", value = display.cssValue)
    }

    /**
     * Sets the `align-content` CSS property using the provided [AlignContent] value.
     *
     * Applies the corresponding CSS value to the current style.
     *
     * @see <a href="https://developer.mozilla.org/en-US/docs/Web/CSS/align-content">MDN Documentation on align-content</a>
     */
    fun alignContent(alignContent: AlignContent) = apply {
        appendNewStyle(property = "align-content", value = alignContent.cssValue)
    }

    fun buildStyle(): String = currentStyle.toString().trim()
}