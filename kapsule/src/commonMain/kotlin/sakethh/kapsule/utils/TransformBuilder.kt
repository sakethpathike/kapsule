package sakethh.kapsule.utils

data class Angle(val value: Double, val unit: String)

class TransformBuilder {
    val currentTransformation = StringBuilder()

    fun rotate(angle: Angle) = this.also {
        currentTransformation.append("rotate(${angle.value}${angle.unit}) ")
    }

    fun scale(factor: Double) = this.also {
        currentTransformation.append("scale($factor) ")
    }

    fun translate(x: String, y: String = "0") = this.also {
        currentTransformation.append("translate($x, $y) ")
    }

    fun skewX(angle: Angle) = this.also {
        currentTransformation.append("skewX(${angle.value}${angle.unit}) ")
    }

    fun skewY(angle: Angle) = this.also {
        currentTransformation.append("skewY(${angle.value}${angle.unit}) ")
    }

    fun custom(string: String) = this.also {
        currentTransformation.append(string)
    }

    fun buildTransformation(): String = currentTransformation.toString().trim()
}