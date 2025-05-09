package sakethh.kapsule

import sakethh.kapsule.utils.*

interface Modifier {

    fun then(other: Modifier): Modifier

    companion object : Modifier {

        override fun then(other: Modifier): Modifier {/*
            currently we are at no where in styling,
             so we return whatever we get
             instead of transforming it to anything,
             because there is nothing is to form a chain
            */
            return other
        }

        override fun toString(): String {
            return ""
        }
    }
}

class StyleModifier(private val rawCSS: String) : Modifier {

    constructor(property: String, value: Any) : this(rawCSS = "$property: $value; ")


    override fun then(other: Modifier): Modifier {
        return CombinedModifier(current = this, new = other)
    }

    override fun toString(): String = rawCSS
}

class CombinedModifier(private val current: Modifier, private val new: Modifier) : Modifier {
    override fun then(other: Modifier): Modifier {
        return CombinedModifier(current, new.then(other))
    }

    override fun toString(): String {
        return "$current $new"
    }
}

fun Modifier.appendNewStyle(property: String, value: Any) = this.then(StyleModifier(property, value))

fun Modifier.appendNewStyle(rawCSS: String) = this.then(StyleModifier(rawCSS))

fun Modifier.backgroundColor(color: String) = appendNewStyle(property = "background-color", value = color)

fun Modifier.color(value: String) = appendNewStyle(property = "color", value)


fun Modifier.padding(value: String) = run {
    appendNewStyle(property = "padding", value)
}

fun Modifier.padding(top: String = 0.px, bottom: String = 0.px, start: String = 0.px, end: String = 0.px) = run {
    appendNewStyle(property = "padding-top", value = top).then(
            appendNewStyle(property = "padding-end", value = end)
        ).then(
            appendNewStyle(property = "padding-start", value = start)
        ).then(
            appendNewStyle(property = "padding-bottom", value = bottom)
        )
}

fun Modifier.margin(px: Int) = run {
    appendNewStyle(property = "margin", value = px.px)
}

fun Modifier.margin(value: String) = run {
    appendNewStyle(property = "margin", value = value)
}

fun Modifier.margin(top: String = 0.px, bottom: String = 0.px, start: String = 0.px, end: String = 0.px) = run {
    appendNewStyle(property = "margin-top", value = top).then(
        appendNewStyle(property = "margin-bottom", value = bottom)
    ).then(
        appendNewStyle(property = "margin-inline-start", value = start)
    ).then(
        appendNewStyle(property = "margin-inline-end", value = end)
    )
}

fun Modifier.border(radius: Int, color: String, width: Int = 1) = run {
    appendNewStyle("border: ${width}px solid $color; border-radius: ${radius}px; ")
}

fun Modifier.border(radius: String, color: String, width: String) = run {
    appendNewStyle("border: $width solid $color; border-radius: $radius; ")
}

fun Modifier.borderRadius(value: String) = run {
    appendNewStyle(property = "border-radius", value)
}

fun Modifier.width(value: String) = run {
    appendNewStyle("width: $value; ")
}

fun Modifier.height(value: String) = run {
    appendNewStyle("height: $value; ")
}

fun Modifier.opacity(value: Double) = run {
    appendNewStyle("opacity: $value; ")
}

fun Modifier.fontFamily(value: String) = run {
    appendNewStyle(property = "font-family", value)
}

fun Modifier.fontSize(value: String) = run {
    appendNewStyle(property = "font-size", value)
}

fun Modifier.size(value: String) = run {
    height(value).then(
        width(value)
    )
}

/**
 * Appends raw CSS properties directly to the current modifier chain.
 *
 * Useful for adding custom styles that are not yet covered by predefined functions.
 * */
fun Modifier.custom(properties: String) = run {
    appendNewStyle(properties.ensureSemicolon())
}

/**
 * Sets the `position` CSS property, which specifies how an element is positioned
 * in a document and how it interacts with the normal flow of the page.
 *
 * @param position Defines the positioning scheme used for the element.
 * @see <a href="https://developer.mozilla.org/en-US/docs/Web/CSS/position">MDN Documentation on position</a>
 */
fun Modifier.position(position: Position) = run {
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
fun Modifier.flexGrow(flexGrow: FlexGrow) = run {
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
fun Modifier.flexGrow(value: Float) = run {
    require(value >= 0) { "flex-grow must be ≥ 0 (was $value)" }
    appendNewStyle("flex-grow", value)
}

/**
Sets width to 100% of the parent.
- Use this only when the parent has a defined width.
 */
fun Modifier.fillMaxWidth(fraction: Double = 1.0) = run {
    require(fraction in 0.0..1.0) { "Fraction must be between 0 and 1" }
    appendNewStyle(property = "width", value = (fraction * 100).percent)
}

/**
Sets height to 100% of the parent.
- Use this only when the parent has a defined height.
 */
fun Modifier.fillMaxHeight(fraction: Double = 1.0) = run {
    require(fraction in 0.0..1.0) { "Fraction must be between 0 and 1" }
    appendNewStyle(property = "height", value = (fraction * 100).percent)
}

/**
Sets width and height to 100% of the parent.
- Use this only when the parent has a defined height and width.
- Use [fillViewportSize] when you want to fill the full screen.
 * */
fun Modifier.fillMaxSize(fraction: Double = 1.0) = run {
    fillMaxWidth(fraction).then(
        fillMaxHeight(fraction)
    )
}

fun Modifier.matchParentWidth() = run {
    appendNewStyle(property = "width", value = 100.percent)
}

fun Modifier.matchParentHeight() = run {
    appendNewStyle(property = "height", value = 100.percent)
}

fun Modifier.matchParentSize() = run {
    matchParentWidth().then(
        matchParentHeight()
    )
}

/**
Use `fillViewportSize()` when you want an element to fill the full screen.
 */
fun Modifier.fillViewportSize() = run {
    appendNewStyle(property = "width", value = 100.vw).then(
        appendNewStyle(property = "height", value = 100.vh)
    )
}

fun Modifier.minHeight(value: String) = run {
    appendNewStyle(property = "min-height", value)
}

fun Modifier.minWidth(value: String) = run {
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
fun Modifier.zIndex(value: Int) = run {
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
fun Modifier.boxSizing(boxSizing: BoxSizing) = run {
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
fun Modifier.boxSizing(value: String) = run {
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
fun Modifier.cursor(cursor: Cursor) = run {
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
fun Modifier.cursor(value: String) = run {
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
fun Modifier.transition(transitionBuilder: TransitionBuilder) = run {
    require(transitionBuilder.buildTransition().trim().isNotBlank()) {
        "Invalid transition. Apply valid transitions.\nNote: `.transition(TransitionBuilder())` is not valid. Please run actual transitions using functions provided by the TransitionBuilder."
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
 * @param transformBuilder A [TransformBuilder] instance containing the transformations to run.
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
fun Modifier.transform(transformBuilder: TransformBuilder) = run {
    require(transformBuilder.buildTransformation().trim().isNotBlank()) {
        "Invalid transformation. Apply valid transformations.\nNote: `.transform(TransformBuilder())` is not valid. Please run actual transformations using functions provided by the TransformBuilder."
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
fun Modifier.clip(shape: Shape, overflow: Overflow = Overflow.Hidden) = run {
    require(shape.buildPath().isNotBlank() && overflow.cssValue.isNotBlank()) {
        "Both shape path and overflow value must be provided and cannot be empty."
    }
    appendNewStyle(property = "clip-path", value = shape.buildPath().ensureSemicolon()).then(
        appendNewStyle(property = "overflow", value = overflow.cssValue)
    )
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
fun Modifier.display(display: Display) = run {
    appendNewStyle(property = "display", value = display.cssValue)
}

/**
 * Sets the `align-content` CSS property using the provided [AlignContent] value.
 *
 * Applies the corresponding CSS value to the current style.
 *
 * @see <a href="https://developer.mozilla.org/en-US/docs/Web/CSS/align-content">MDN Documentation on align-content</a>
 */
fun Modifier.alignContent(alignContent: AlignContent) = run {
    appendNewStyle(property = "align-content", value = alignContent.cssValue)
}