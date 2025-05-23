package sakethh.kapsule.utils

data class Angle(val value: Double, val unit: String)

class TransformBuilder {
    val currentTransformation = StringBuilder()

    fun rotate(angle: Angle) = apply {
        currentTransformation.append("rotate(${angle.value}${angle.unit}) ")
    }

    fun scale(factor: Double) = apply {
        currentTransformation.append("scale($factor) ")
    }

    fun translate(x: String, y: String = "0") = apply {
        currentTransformation.append("translate($x, $y) ")
    }

    fun skewX(angle: Angle) = apply {
        currentTransformation.append("skewX(${angle.value}${angle.unit}) ")
    }

    fun skewY(angle: Angle) = apply {
        currentTransformation.append("skewY(${angle.value}${angle.unit}) ")
    }

    fun custom(string: String) = apply {
        currentTransformation.append(string)
    }

    fun buildTransformation(): String = currentTransformation.toString().trim()
}