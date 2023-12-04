package Dec04

import Dec01.Dec01
import kotlin.math.pow

object Dec04 {

    fun first(): String {
        val input = Dec01::class.java.getResource("/Dec04Input.txt").readText().lines()
        val cards = mutableListOf<Card>()

        input!!.forEach {
            val cardId = it.substringBefore(":").filter { it.isDigit() }.toInt()
            val numbers = it.substringAfter(":")
            val winningNumbers = numbers.substringBefore("|")
            val myNumbers = numbers.substringAfter("|")
            val winningNumbersList = Regex("[0-9]+").findAll(winningNumbers)
                .map(MatchResult::value)
                .toList()
            val myNumbersList = Regex("[0-9]+").findAll(myNumbers)
                .map(MatchResult::value)
                .toList()

            cards.add(Card(cardId, winningNumbersList, myNumbersList))
        }

        var totalScore = 0
        cards.forEach {
            totalScore += getScore(it.winningList, it.myList)
        }

        return totalScore.toString()
    }

    private fun getScore(winningNumbers: List<String>, myNumbers: List<String>) : Int {
        var score: Int
        var counter = 0

        myNumbers.forEach {
            if(winningNumbers.contains(it)) {
                counter++
            }
        }
        score = counter
        if(counter > 1) {
            score = 1
            for (i in 1..counter - 1) {
                score *= 2
            }
        }
        return score
    }
}

data class Card(val id: Int, val winningList: List<String>, val myList: List<String>)