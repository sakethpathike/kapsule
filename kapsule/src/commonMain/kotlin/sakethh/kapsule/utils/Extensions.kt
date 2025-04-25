package sakethh.kapsule.utils


fun String.ensureSemicolon(): String {
    return if (this.trim().endsWith(";")) {
            this
        } else {
            "$this; "
        }
}