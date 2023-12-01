package Dec01

object Dec01 {
    fun first(): String {
        val input = Dec01::class.java.getResource("/Dec01Input.txt").readText().lines()
        val numbers = mutableListOf<Int>()

        input!!.forEach {
            val stringWithNumbers = it.filter { it.isDigit()}
            val number = stringWithNumbers.first() to stringWithNumbers.last()
            val numberString: String = "" + number.first + number.second
            numbers.add(numberString.toInt())
        }

        return numbers.sum().toString()
    }

    fun second(): String {
        val input = Dec01::class.java.getResource("/Dec01Input.txt").readText().lines()
        val numbers = mutableListOf<Int>()

        val numberPairs = listOf(
            Pair("ONE", '1'),
            Pair("TWO", '2'),
            Pair("THREE", '3'),
            Pair("FOUR", '4'),
            Pair("FIVE", '5'),
            Pair("SIX", '6'),
            Pair("SEVEN", '7'),
            Pair("EIGHT", '8'),
            Pair("NINE", '9')
        )
        val nameList = mutableListOf<String>()
        numberPairs.forEach {
            nameList.add(it.first)
        }

        input!!.forEach {
            val numberChars = mutableListOf<Char>()
            var index = 0
            while(index < it.length) {
                if(it[index].isDigit()) {
                    numberChars.add(it[index])
                    index++
                }
                else {
                    var part = ""
                    if(it.length - index >= 3) {
                        part = it.subSequence(index, index + 3).toString().uppercase()
                        if (nameList.contains(part)) {
                            numberChars.add(numberPairs.find { it.first == part }?.second!!)
                        }
                    }
                    if(it.length - index >= 4) {
                        part = it.subSequence(index, index + 4).toString().uppercase()
                        if (nameList.contains(part)) {
                            numberChars.add(numberPairs.find { it.first == part }?.second!!)
                        }
                    }
                    if(it.length - index >= 5) {
                        part = it.subSequence(index, index + 5).toString().uppercase()
                        if(nameList.contains(part)) {
                            numberChars.add(numberPairs.find { it.first == part}?.second!!)
                        }
                    }
                    index++
                }
            }
            val numberString: String = "" + numberChars.first() + numberChars.last()
            numbers.add(numberString.toInt())
        }
        return numbers.sum().toString()
    }
}