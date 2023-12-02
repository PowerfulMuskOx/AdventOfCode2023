package Dec02

import Dec01.Dec01

object Dec02 {

    fun first(): String {
        val gameList = getGameList()

        var validGameList = mutableListOf<Game>()
        gameList.forEach {
            if(isValidGame(it))
                validGameList.add(it)
        }
        return validGameList.sumOf { it.id }.toString()
    }

    fun second(): String {
        val gameList = getGameList();

        var totalPower = 0

        gameList.forEach {
            val minRed = it.rounds.maxOf { it.red }
            val minGreen = it.rounds.maxOf { it.green }
            val minBlue = it.rounds.maxOf { it.blue }

            totalPower += minRed * minGreen * minBlue
        }

        return totalPower.toString();
    }

    private fun getGameList() : List<Game> {
        val input = Dec01::class.java.getResource("/Dec02Input.txt").readText().lines()
        var gameList = mutableListOf<Game>()

        input!!.forEach {
            val gameId = it.substringBefore(":").filter { it.isDigit() }.toInt()
            val gameString = it.substringAfter(":")
            val roundString = gameString.split(";")
            val roundList = mutableListOf<Round>()

            roundString.forEach {
                var blue = 0
                var red = 0
                var green = 0
                val cubeList = it.split(",")
                cubeList.forEach {
                    if(it.contains("blue")) { blue += it.filter { it.isDigit() }.toInt() }
                    else if(it.contains("red")) { red += it.filter { it.isDigit() }.toInt() }
                    else {green += it.filter { it.isDigit() }.toInt()}
                }
                val round = Round(red, green, blue)
                roundList.add(round)
            }
            val game = Game(gameId, roundList)
            gameList.add(game)
        }

        return gameList
    }

    private fun isValidGame(game: Game): Boolean {
        return game.rounds.all { isValidRound(it) }
    }

    private fun isValidRound(round: Round): Boolean {
        return round.red <= 12 && round.green <= 13 && round.blue <= 14
    }
}

data class Game(val id: Int, val rounds: List<Round>)

data class Round(val red: Int, val green: Int, val blue: Int)