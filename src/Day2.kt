import java.io.File
import java.util.*

fun main() {
    val day2 = Day2()
    day2.part1()
    day2.part2()
}

class Day2 {

    fun part1() {
        val scanner = Scanner(File("data-day2-part1"))
        var twice = 0
        var trice = 0
        while(scanner.hasNextLine()) {
            val line = scanner.nextLine().trim()
            val data = line.groupBy { it }.values.map { it.size }
            if (data.contains(2)) {
                twice += 1
            }
            if ( data.contains(3)) {
                trice += 1
            }
        }
        println(twice * trice)
    }

    fun part2() {
        val scanner = Scanner(File("data-day2-part2"))
        var difference = Int.MAX_VALUE
        var letters = ""
        val inputs = mutableListOf<String>()
        while(scanner.hasNextLine()) {
            val line = scanner.nextLine().trim()
            inputs.add(line)
        }
        for (input1 in inputs) {
            for (input2 in inputs) {
                if (input1 == input2) {
                   continue
                }
                var inputDifference = 0
                var inputLetter = ""
                for (i in 0 until input2.count()) {
                    val input1Char = input1[i]
                    val input2Char = input2[i]
                    if (input1Char != input2Char) {
                        inputDifference += 1
                    } else {
                        inputLetter += input1Char
                    }
                }
                if (inputDifference < difference) {
                    difference = inputDifference
                    letters = inputLetter
                }
            }
        }
        println(letters)
    }
}