package sakethh.kapsule.utils

enum class VerticalAlignment(val value: String) {
    Top("flex-start"), Center("center"), Bottom("flex-end"), SpaceBetween("space-between"), SpaceAround("space-around"), SpaceEvenly(
        "space-evenly"
    ),
    None("")
}

enum class HorizontalAlignment(val value: String) {
    Start("flex-start"), Center("center"), End("flex-end"), Stretch("stretch"), None("")
}

enum class TextAlign(val cssValue: String) {
    // Keyword values
    Start("start"), End("end"), Left("left"), Right("right"), Center("center"), Justify("justify"), MatchParent("match-parent"),

    // Block alignment values (Non-standard syntax)
    MozCenter("-moz-center"), WebkitCenter("-webkit-center"),

    // Global values
    Inherit("inherit"), Initial("initial"), Revert("revert"), RevertLayer("revert-layer"), Unset("unset")
}

enum class ContentScale(val cssValue: String) {
    Contain("contain"), Cover("cover"), Fill("fill"), None("none"), ScaleDown("scale-down");
}

sealed interface ObjectPosition {
    fun position(): String

    enum class Predefined : ObjectPosition {
        Top {
            override fun position() = "top"
        },
        Bottom {
            override fun position() = "bottom"
        },
        Left {
            override fun position() = "left"
        },
        Right {
            override fun position() = "right"
        },
        Center {
            override fun position() = "center"
        }
    }

    data class Custom(val value: String) : ObjectPosition {
        override fun position() = value
    }
}

sealed interface FontWeight {
    fun cssValue(): String

    enum class Predefined : FontWeight {
        Thin {
            override fun cssValue() = "100"
        },
        ExtraLight {
            override fun cssValue() = "200"
        },
        Light {
            override fun cssValue() = "300"
        },
        Normal {
            override fun cssValue() = "400"
        },
        Medium {
            override fun cssValue() = "500"
        },
        SemiBold {
            override fun cssValue() = "600"
        },
        Bold {
            override fun cssValue() = "700"
        },
        ExtraBold {
            override fun cssValue() = "800"
        },
        Black {
            override fun cssValue() = "900"
        },
        ExtraBlack {
            override fun cssValue() = "1000"
        },
        NormalKeyword {
            override fun cssValue() = "normal"
        },
        BoldKeyword {
            override fun cssValue() = "bold"
        },
        Lighter {
            override fun cssValue() = "lighter"
        },
        Bolder {
            override fun cssValue() = "bolder"
        }
    }

    data class Custom(val value: String) : FontWeight {
        override fun cssValue() = value
    }
}
