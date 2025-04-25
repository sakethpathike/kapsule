package sakethh.kapsule.utils


fun String.ensureSemicolon() {
    return run {
        if (this.trim().endsWith(";")) {
            this
        } else {
            "$this; "
        }
    }
}