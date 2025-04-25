package sakethh.kapsule.utils

enum class TimingFunction(val value: String) {
    EASE("ease"),
    EASE_IN("ease-in"),
    EASE_OUT("ease-out"),
    EASE_IN_OUT("ease-in-out"),
    LINEAR("linear"),
    STEP_START("steps(start)"),
    STEP_END("steps(end)")
}

enum class Overflow(val value: String) {
    Hidden("hidden"), Visible("visible"), Scroll("scroll"), Auto("auto"), Clip("clip")
}