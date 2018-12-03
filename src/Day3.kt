import java.io.File
import java.util.*

fun main() {
    val day3 = Day3()
    day3.part1()
    day3.part2()
}

class Day3 {

    fun part1() {
        val scanner = Scanner(File("data-day3-part1"))
        val array = Array(1000) {
            IntArray(1000)
        }
        while(scanner.hasNextLine()) {
            val line = scanner.nextLine().trim()
            val data = line.split("\\s+".toRegex())
            val coordinates = data[2].removeSuffix(":").split(",")
            val x = coordinates[0].toInt()
            val y = coordinates[1].toInt()
            val dimension = data[3].split("x")
            val width = dimension[0].toInt()
            val height = dimension[1].toInt()
            for (row in y until y+height) {
                for (column in x until x+width) {
                    array[row][column] += array[row][column] + 1
                }
            }
        }
        val sum = array.sumBy {
            it.sumBy { count ->
                if (count > 1) {
                    1
                } else {
                    0
                }
            }
        }
        println(sum)
    }

    fun part2() {
        val scanner = Scanner(File("data-day3-part2"))
        val array = Array(1000) {
            IntArray(1000) {
                -1
            }
        }
        val idDoesNotOverlap = mutableSetOf<Int>()
        while(scanner.hasNextLine()) {
            val line = scanner.nextLine().trim()
            val data = line.split("\\s+".toRegex())
            val id = data[0].removePrefix("#").toInt()
            val coordinates = data[2].removeSuffix(":").split(",")
            val x = coordinates[0].toInt()
            val y = coordinates[1].toInt()
            val dimension = data[3].split("x")
            val width = dimension[0].toInt()
            val height = dimension[1].toInt()
            var didOverlap = false
            for (row in y until y+height) {
                for (column in x until x+width) {
                    val currentId = array[row][column]
                    if (currentId == -1) {
                        array[row][column] = id
                        idDoesNotOverlap.add(id)
                    } else {
                        idDoesNotOverlap.remove(currentId)
                        didOverlap = true
                    }
                }
            }
            if (didOverlap) {
                idDoesNotOverlap.remove(id)
            }
        }
        println(idDoesNotOverlap.first())
    }
}