package Dec04

import Dec01.Dec01
import kotlin.math.pow

object Dec04 {

    fun first(): String {
        var cards = getCards()

        var totalScore = 0
        cards.forEach {
            totalScore += getScore(it)
        }

        return totalScore.toString()
    }

    fun second() :String {
        var cards = getCards()
        var collectedCards = mutableListOf<Card>()
        var cardsToPlay = mutableListOf<Card>()

        cardsToPlay.addAll(cards)
        collectedCards.addAll(cards)

        while (cardsToPlay.isNotEmpty()) {
            val car = mutableListOf<Card>()
            car.addAll(cardsToPlay)
            car.forEach {
                val matches = getMatches(it)
                val newCards = getNewCards(cards, it.id, it.id + matches - 1)

                cardsToPlay.addAll(newCards)
                collectedCards.addAll(newCards)
                cardsToPlay.removeFirst()
            }
        }

        return collectedCards.size.toString()
    }

    private fun getCards() : List<Card> {
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

        return cards
    }

    private fun getScore(card: Card) : Int {
        var score: Int
        var counter = 0

        card.myList.forEach {
            if(card.winningList.contains(it)) {
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

    private fun getMatches(card: Card) : Int {
        var counter = 0

        card.myList.forEach {
            if(card.winningList.contains(it)) {
                counter++
            }
        }

        return counter
    }

    private fun getNewCards(cards: List<Card>, start: Int, end: Int) : List<Card> {
        val newCards = mutableListOf<Card>()
        for (i in start .. end) {
            newCards.add(cards[i])
        }

        return newCards
    }
}

data class Card(val id: Int, val winningList: List<String>, val myList: List<String>)