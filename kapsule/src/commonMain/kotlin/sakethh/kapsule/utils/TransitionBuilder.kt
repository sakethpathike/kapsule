package sakethh.kapsule.utils

class TransitionBuilder {
    private val currentTransition = StringBuilder()

    fun duration(value: Float, unit: String = "s") = apply {
        currentTransition.append("${value}${unit} ")
    }

    fun timingFunction(value: TimingFunction) = apply {
        currentTransition.append("${value.value} ")
    }

    fun delay(value: Float, unit: String = "s") = apply {
        currentTransition.append("${value}${unit} ")
    }

    fun custom(string: String) = apply {
        currentTransition.append(string)
    }

    fun buildTransition(): String = currentTransition.trim().toString()
}
