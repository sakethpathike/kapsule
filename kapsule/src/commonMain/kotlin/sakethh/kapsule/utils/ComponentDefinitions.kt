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
    fun value(): String

    enum class Predefined : FontWeight {
        Thin {
            override fun value() = "100"
        },
        ExtraLight {
            override fun value() = "200"
        },
        Light {
            override fun value() = "300"
        },
        Normal {
            override fun value() = "400"
        },
        Medium {
            override fun value() = "500"
        },
        SemiBold {
            override fun value() = "600"
        },
        Bold {
            override fun value() = "700"
        },
        ExtraBold {
            override fun value() = "800"
        },
        Black {
            override fun value() = "900"
        },
        ExtraBlack {
            override fun value() = "1000"
        },
        NormalKeyword {
            override fun value() = "normal"
        },
        BoldKeyword {
            override fun value() = "bold"
        },
        Lighter {
            override fun value() = "lighter"
        },
        Bolder {
            override fun value() = "bolder"
        }
    }

    data class Custom(val value: String) : FontWeight {
        override fun value() = value
    }
}
