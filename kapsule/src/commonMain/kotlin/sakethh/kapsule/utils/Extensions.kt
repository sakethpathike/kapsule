package sakethh.kapsule.utils


fun String.ensureSemicolon(): String {
    return if (this.trim().endsWith(";")) {
            this
        } else {
            "$this; "
        }
}

val Number.px: String get() = "${this}px"
val Number.pt: String get() = "${this}pt"
val Number.`in`: String get() = "${this}in"

val Number.em: String get() = "${this}em"
val Number.rem: String get() = "${this}rem"
val Number.vw: String get() = "${this}vw"
val Number.vh: String get() = "${this}vh"
val Number.vmin: String get() = "${this}vmin"
val Number.vmax: String get() = "${this}vmax"
val Number.ch: String get() = "${this}ch"
val Number.ex: String get() = "${this}ex"
val Number.lh: String get() = "${this}lh"
val Number.percent: String get() = "${this}%"
val Number.fr: String get() = "${this}fr"

val Number.deg get() = Angle(this.toDouble(), "deg")
val Number.rad get() = Angle(this.toDouble(), "rad")