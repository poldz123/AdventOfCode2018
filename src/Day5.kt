import java.io.File

fun main() {
    val day5 = Day5()
    day5.part1()
    day5.part2()
}

class Day5 {

    fun part1() {
        val inputs = File("data-day5-part1").readLines()
        val input = inputs.first().toMutableList()

        var i = 0
        while(true) {
            val c = input[i]
            if (i == input.count() - 1) {
                break
            }
            if (c.isLowerCase() && input[i + 1] == c.toUpperCase() ||
                c.isUpperCase() && input[i + 1] == c.toLowerCase()) {
                input.removeAt(i)
                input.removeAt(i)
                i = if (i == 0) 0 else i - 1
            } else {
                i += 1
            }
        }
        println(input.count())
    }

    fun part2() {
        val inputs = File("data-day5-part2").readLines()
        val input = inputs.first().toMutableList()

        val uniqueChars = input.filter { it.isLowerCase() }.toSet()

        var solution = Int.MAX_VALUE
        uniqueChars.forEach {
            val testInput = inputs.first().toMutableList()
            testInput.removeAll(mutableListOf(it.toLowerCase(), it.toUpperCase()))
            var i = 0
            while(true) {
                val c = testInput[i]
                if (i == testInput.count() - 1) {
                    break
                }
                if (c.isLowerCase() && testInput[i + 1] == c.toUpperCase() ||
                    c.isUpperCase() && testInput[i + 1] == c.toLowerCase()) {
                    testInput.removeAt(i)
                    testInput.removeAt(i)
                    i = if (i == 0) 0 else i - 1
                } else {
                    i += 1
                }
            }
            if (testInput.count() < solution) {
                solution = testInput.count()
            }
        }
        println(solution)
    }
}