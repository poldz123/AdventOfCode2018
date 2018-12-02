import java.util.*

fun main() {
    val day1 = Day1()
    day1.part1()
}

class Day1 {

    fun part1() {
        val data = ""
        val solution = 0
        val scanner = Scanner(data)
        while(scanner.hasNextLine()) {
            val line = scanner.nextLine().trim()
            val numbers = line.split("\\s+".toRegex())
        }
        println(solution)
    }
}