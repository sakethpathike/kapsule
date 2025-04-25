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