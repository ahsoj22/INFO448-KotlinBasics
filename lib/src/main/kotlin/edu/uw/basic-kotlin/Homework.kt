package edu.uw.basickotlin

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(arg: Any): String {
    return when (arg) {
        is String -> when (arg) {
            "Hello" -> "world"
            "Howdy" -> "Say what?"
            "Bonjour" -> "Say what?"
            else -> "I don't understand"
        }
        is Int -> when (arg) {
            0 -> "zero"
            1 -> "one"
            in 2..10 -> "low number"
            else -> "a number"
        }
        else -> "I don't understand"
    }
}

// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(lhs: Int, rhs: Int): Int {
    return lhs + rhs;
}

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(lhs: Int, rhs: Int): Int {
    return lhs - rhs;
}

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(lhs: Int, rhs: Int, op: (Int, Int) -> Int): Int {
    return op(lhs, rhs)
}
// write a class "Person" with first name, last name and age
class Person(val firstName: String, val lastName: String, var age: Int){
    val debugString: String
        get() = "[Person firstName:$firstName lastName:$lastName age:$age]"
}

// write a class "Money"

class Money(val amount: Int, val currency: String) {

    init {
        require(amount >= 0)

        val requiredCurrencies = listOf("USD", "EUR", "CAN", "GBP")
        require(currency in requiredCurrencies) { "Invalid currency: $currency" }
    }

    private val conversionRates = mapOf(
        "USD" to mapOf("GBP" to 0.5, "EUR" to 1.5, "CAN" to 1.25),
        "GBP" to mapOf("USD" to 2.0, "EUR" to 3.0, "CAN" to 2.5),
        "EUR" to mapOf("USD" to 2.0 / 3.0, "GBP" to 1.0 / 3.0, "CAN" to 5.0 / 6.0),
        "CAN" to mapOf("USD" to 4.0 / 5.0, "GBP" to 2.0 / 5.0, "EUR" to 6.0 / 5.0)
    )

    fun convert(toCurrency: String): Money {
        if (this.currency == toCurrency) return this
        val rate = conversionRates[this.currency]?.get(toCurrency)
            ?: throw IllegalArgumentException("null conversion rate")
        val newAmount = (this.amount * rate!!).toInt()
        return Money(newAmount, toCurrency)
    }

    operator fun plus(other: Money): Money {
        val convertedOther = other.convert(this.currency)
        return Money(this.amount + convertedOther.amount, this.currency)
    }

    override fun toString(): String {
        return "$amount $currency"
    }
}
