package sakethh.kapsule.utils

sealed interface Shape {
    fun buildPath(): String

    data class Custom(val path: String) : Shape {
        override fun buildPath(): String {
            return path.run {
                if (this.trim().endsWith(";")) {
                    this
                } else {
                    "$this; "
                }
            }
        }
    }

    data class Rectangle(
        val x: Float, val y: Float, val width: Float, val height: Float
    ) : Shape {
        override fun buildPath(): String {
            return "rect($x, $y, $height, $width); "
        }
    }

    data class Polygon(val points: List<Pair<Float, Float>>) : Shape {
        override fun buildPath(): String {
            val path = points.joinToString(", ") { "${it.first},${it.second}" }
            return "polygon($path); "
        }
    }

    data class Circle(
        val centerX: Float, val centerY: Float, val radius: Float
    ) : Shape {
        override fun buildPath(): String {
            return "circle($centerX, $centerY, $radius); "
        }
    }

    companion object {
        val Circle = Custom("circle(); ")
        val Rectangle = Custom("rect(0, 0, 100%, 100%); ")
        val RoundedRectangle = Custom("rect(0, 0, 100%, 100%, 20px); ")
        val Ellipse = Custom("ellipse(); ")
    }

    data class RoundedRectangle(
        val x: Float, val y: Float, val width: Float, val height: Float, val cornerRadius: String
    ) : Shape {
        override fun buildPath(): String {
            return "rect($x, $y, $height, $width, $cornerRadius); "
        }
    }
}