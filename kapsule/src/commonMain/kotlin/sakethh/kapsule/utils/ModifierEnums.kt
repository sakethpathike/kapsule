package sakethh.kapsule.utils

enum class TimingFunction(val cssValue: String) {
    Ease("ease"),
    EaseIn("ease-in"),
    EaseOut("ease-out"),
    EaseInOut("ease-in-out"),
    Linear("linear"),
    StepStart("steps(start)"),
    StepEnd("steps(end)");

    override fun toString(): String = cssValue
}

enum class Overflow(val cssValue: String) {
    Hidden("hidden"), Visible("visible"), Scroll("scroll"), Auto("auto"), Clip("clip");

    override fun toString(): String = cssValue
}

enum class Cursor(val cssValue: String) {
    Auto("auto"),
    Default("default"),
    Pointer("pointer"),
    Text("text"),
    Wait("wait"),
    Help("help"),
    Move("move"),
    NotAllowed("not-allowed"),
    Crosshair("crosshair"),
    Grab("grab"),
    Grabbing("grabbing"),
    Progress("progress"),
    Alias("alias"),
    Copy("copy"),
    ContextMenu("context-menu"),
    ZoomIn("zoom-in"),
    ZoomOut("zoom-out"),
    None("none"),
    NResize("n-resize"),
    SResize("s-resize"),
    EResize("e-resize"),
    WResize("w-resize"),
    NEResize("ne-resize"),
    NWResize("nw-resize"),
    SEResize("se-resize"),
    SWResize("sw-resize");

    override fun toString(): String = cssValue
}

enum class BoxSizing(val cssValue: String) {
    ContentBox("content-box"),
    BorderBox("border-box");

    override fun toString(): String = cssValue
}

enum class FlexGrow(val cssValue: String) {
    Inherit("inherit"),
    Initial("initial"),
    Revert("revert"),
    RevertLayer("revert-layer"),
    Unset("unset");

    override fun toString(): String = cssValue
}

enum class Position(val cssValue: String) {
    Static("static"),
    Relative("relative"),
    Absolute("absolute"),
    Fixed("fixed"),
    Sticky("sticky"),

    // Global values
    Inherit("inherit"),
    Initial("initial"),
    Revert("revert"),
    RevertLayer("revert-layer"),
    Unset("unset");

    override fun toString(): String = cssValue
}