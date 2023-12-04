package Dec03

import Dec01.Dec01
import java.util.regex.Pattern

object Dec03 {

    fun first(): String {
        val input = Dec01::class.java.getResource("/Dec03Input.txt").readText().lines()

        val pattern = Pattern.compile("[*#$&=+\\-%@/]")
        var partNumbers = mutableListOf<Int>()
        input.forEachIndexed { index, s ->
            val numbers = Regex("[0-9]+").findAll(s)
                .map(MatchResult::value)
                .toList()

            var listOfStringsToCheck = mutableListOf<String>()
            if (index > 0) {
                if (index != input.size - 1) {
                    listOfStringsToCheck.add(input[index - 1])
                    listOfStringsToCheck.add(input[index])
                    listOfStringsToCheck.add(input[index + 1])
                } else {
                    listOfStringsToCheck.add(input[index - 1])
                    listOfStringsToCheck.add(input[index])
                }
            } else {
                listOfStringsToCheck.add(input[index])
                listOfStringsToCheck.add(input[1])
            }

            var line = s

            numbers.forEach { number ->
                var isPartNumber = false
                val startIndex = line.indexOf(number)
                val endIndex = startIndex + number.length - 1

                listOfStringsToCheck.forEach {
                    if (!isPartNumber) {
                        if (startIndex != 0) {
                            if (endIndex != it.length - 1) {
                                val stringToCheck = it.subSequence(startIndex - 1, endIndex + 2)
                                isPartNumber = pattern.matcher(stringToCheck).find()
                            }
                            else {
                                val stringToCheck = it.subSequence(startIndex - 1, endIndex + 1)
                                isPartNumber = pattern.matcher(stringToCheck).find()
                            }
                        } else {
                            val stringToCheck = it.subSequence(startIndex, endIndex + 2)
                            isPartNumber = pattern.matcher(stringToCheck).find()
                        }
                    }
                }

                listOfStringsToCheck = updateListOfStringsToCheck(endIndex + 1, listOfStringsToCheck)
                line = line.substring(endIndex + 1)

                if(isPartNumber) {
                    partNumbers.add(number.toInt())
                }
            }

        }

        return partNumbers.sum().toString()
    }

    fun second() : String {

        return ""
    }

    private fun updateListOfStringsToCheck(endIndex: Int, listOfString: MutableList<String>): MutableList<String> {

        var newList = mutableListOf<String>()
        listOfString.forEach {
            val newString = it.substring(endIndex)
            newList.add(newString)
        }

        return newList
    }
}