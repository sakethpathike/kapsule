package sakethh.kapsule.utils

enum class TimingFunction(val cssValue: String) {
    Ease("ease"), EaseIn("ease-in"), EaseOut("ease-out"), EaseInOut("ease-in-out"), Linear("linear"), StepStart("steps(start)"), StepEnd(
        "steps(end)"
    );

    override fun toString(): String = cssValue
}

enum class Overflow(val cssValue: String) {
    Hidden("hidden"), Visible("visible"), Scroll("scroll"), Auto("auto"), Clip("clip");

    override fun toString(): String = cssValue
}

enum class Cursor(val cssValue: String) {
    Auto("auto"), Default("default"), Pointer("pointer"), Text("text"), Wait("wait"), Help("help"), Move("move"), NotAllowed(
        "not-allowed"
    ),
    Crosshair("crosshair"), Grab("grab"), Grabbing("grabbing"), Progress("progress"), Alias("alias"), Copy("copy"), ContextMenu(
        "context-menu"
    ),
    ZoomIn("zoom-in"), ZoomOut("zoom-out"), None("none"), NResize("n-resize"), SResize("s-resize"), EResize("e-resize"), WResize(
        "w-resize"
    ),
    NEResize("ne-resize"), NWResize("nw-resize"), SEResize("se-resize"), SWResize("sw-resize");

    override fun toString(): String = cssValue
}

enum class BoxSizing(val cssValue: String) {
    ContentBox("content-box"), BorderBox("border-box");

    override fun toString(): String = cssValue
}

enum class FlexGrow(val cssValue: String) {
    Inherit("inherit"), Initial("initial"), Revert("revert"), RevertLayer("revert-layer"), Unset("unset");

    override fun toString(): String = cssValue
}

enum class Position(val cssValue: String) {
    Static("static"), Relative("relative"), Absolute("absolute"), Fixed("fixed"), Sticky("sticky"),

    // Global values
    Inherit("inherit"), Initial("initial"), Revert("revert"), RevertLayer("revert-layer"), Unset("unset");

    override fun toString(): String = cssValue
}

enum class Display(val cssValue: String) {
    // Precomposed values
    Block("block"), Inline("inline"), InlineBlock("inline-block"), Flex("flex"), InlineFlex("inline-flex"), Grid("grid"), InlineGrid(
        "inline-grid"
    ),
    FlowRoot("flow-root"),

    // Box generation
    None("none"), Contents("contents"),

    // Multi-keyword syntax
    BlockFlex("block flex"), BlockFlow("block flow"), BlockFlowRoot("block flow-root"), BlockGrid("block grid"), InlineFlexMulti(
        "inline flex"
    ),
    InlineFlow("inline flow"), InlineFlowRoot("inline flow-root"), InlineGridMulti("inline grid"),

    // Other values
    Table("table"), TableRow("table-row"), ListItem("list-item"),

    // Global values
    Inherit("inherit"), Initial("initial"), Revert("revert"), RevertLayer("revert-layer"), Unset("unset")
}

enum class AlignContent(val cssValue: String) {
    // Normal
    Normal("normal"),

    // Basic positional alignment
    Start("start"), Center("center"), Right("right"), Left("left"), End("end"), FlexStart("flex-start"), FlexEnd("flex-end"),

    // Baseline alignment
    Baseline("baseline"), FirstBaseline("first baseline"), LastBaseline("last baseline"),

    // Distributed alignment
    SpaceBetween("space-between"), SpaceAround("space-around"), SpaceEvenly("space-evenly"), Stretch("stretch"),

    // Overflow alignment
    SafeCenter("safe center"), UnsafeCenter("unsafe center"),

    // Global values
    Inherit("inherit"), Initial("initial"), Revert("revert"), RevertLayer("revert-layer"), Unset("unset")
}