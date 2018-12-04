import java.io.File
import java.util.*

fun main() {
    val day4 = Day4()
    day4.part1()
    day4.part2()
}

data class Data(val year: Int, val month: Int, val day: Int, val hour: Int, val minute: Int, val isAwake: Boolean, val isAsleep: Boolean, val isBeginShift: Boolean, val guardId: Int)

class Day4 {

    fun part1() {
        val scanner = Scanner(File("data-day4-part1"))
        val result = mutableListOf<Data>()
        while(scanner.hasNextLine()) {
            val line = scanner.nextLine().trim()
            val data = line.split("] ")
            val timestamp = data[0].removePrefix("[").split("\\s+".toRegex())
            val date = timestamp[0].split("-")
            val time = timestamp[1].split(":")

            val year = date[0].toInt()
            val month = date[1].toInt()
            val day = date[2].toInt()
            val hour = time[0].toInt()
            val minute = time[1].toInt()

            val isWakesUp = data[1] == "wakes up"
            val isAsleep = data[1] == "falls asleep"
            val isBeginShift = data[1].contains("Guard")
            if (isWakesUp) {
                result.add(Data(year, month, day, hour, minute, isWakesUp, isAsleep, isBeginShift, 0))
            } else if (isAsleep) {
                result.add(Data(year, month, day, hour, minute, isWakesUp, isAsleep, isBeginShift, 0))
            } else if (isBeginShift) {
                val guard = data[1].split("\\s+".toRegex())[1].removePrefix("#").toInt()
                result.add(Data(year, month, day, hour, minute, isWakesUp, isAsleep, isBeginShift, guard))
            } else {
                error("Error")
            }
        }
        val solution = mutableMapOf<Int, IntArray>()
        val sortedResult = result.sortedWith(compareBy({it.year}, {it.month}, {it.day}, {it.hour}, {it.minute}))
        var sleepAt = 0
        var guardId = 0
        sortedResult.forEach {
            println("Year: ${it.year}, Month: ${it.month}, Day: ${it.day}, Hour: ${it.hour}, Minute: ${it.minute}")
            if (it.isAsleep) {
                println("Asleep")
                sleepAt = it.minute
            } else if (it.isAwake) {
                val array = solution[guardId]!!
                for (i in sleepAt until it.minute) {
                    array[i] = array[i] + 1
                }
                solution[guardId] = array
                println("Awake")
            } else if (it.isBeginShift) {
                println("Guard: ${it.guardId}")
                guardId = it.guardId
                solution[it.guardId] = solution[it.guardId] ?: IntArray(60)
            } else {
                error("Error")
            }
        }
        val answer = solution.toList().sortedWith(compareByDescending{it.second.sum()}).first()
        val answerGuardId = answer.first
        val answerMinuteToEscape = _indexOfMax(solution[answerGuardId]!!)!!
        println(answerMinuteToEscape * answerGuardId)
    }

    fun part2() {
        val scanner = Scanner(File("data-day4-part2"))
        val result = mutableListOf<Data>()
        while(scanner.hasNextLine()) {
            val line = scanner.nextLine().trim()
            val data = line.split("] ")
            val timestamp = data[0].removePrefix("[").split("\\s+".toRegex())
            val date = timestamp[0].split("-")
            val time = timestamp[1].split(":")

            val year = date[0].toInt()
            val month = date[1].toInt()
            val day = date[2].toInt()
            val hour = time[0].toInt()
            val minute = time[1].toInt()

            val isWakesUp = data[1] == "wakes up"
            val isAsleep = data[1] == "falls asleep"
            val isBeginShift = data[1].contains("Guard")
            if (isWakesUp) {
                result.add(Data(year, month, day, hour, minute, isWakesUp, isAsleep, isBeginShift, 0))
            } else if (isAsleep) {
                result.add(Data(year, month, day, hour, minute, isWakesUp, isAsleep, isBeginShift, 0))
            } else if (isBeginShift) {
                val guard = data[1].split("\\s+".toRegex())[1].removePrefix("#").toInt()
                result.add(Data(year, month, day, hour, minute, isWakesUp, isAsleep, isBeginShift, guard))
            } else {
                error("Error")
            }
        }
        val solution = mutableMapOf<Int, IntArray>()
        val sortedResult = result.sortedWith(compareBy({it.year}, {it.month}, {it.day}, {it.hour}, {it.minute}))
        var sleepAt = 0
        var guardId = 0
        sortedResult.forEach {
            println("Year: ${it.year}, Month: ${it.month}, Day: ${it.day}, Hour: ${it.hour}, Minute: ${it.minute}")
            if (it.isAsleep) {
                println("Asleep")
                sleepAt = it.minute
            } else if (it.isAwake) {
                val array = solution[guardId]!!
                for (i in sleepAt until it.minute) {
                    array[i] = array[i] + 1
                }
                solution[guardId] = array
                println("Awake")
            } else if (it.isBeginShift) {
                println("Guard: ${it.guardId}")
                guardId = it.guardId
                solution[it.guardId] = solution[it.guardId] ?: IntArray(60)
            } else {
                error("Error")
            }
        }
        val answer = solution.toList().sortedWith(compareByDescending { it.second.max() }).first()
        val answerGuardId = answer.first
        val answerMostMinuteAsleepCount = _indexOfMax(answer.second)!!
        println(answerGuardId)
        println(answerMostMinuteAsleepCount)
        println(answerGuardId * answerMostMinuteAsleepCount)
    }

    fun _indexOfMax(a: IntArray): Int? {
        var maxIndex = 0
        for(elem in a.indices){
            val newElem = a[elem]
            if (newElem >= a[maxIndex]){
                maxIndex = elem;
            }
        }
        return maxIndex
    }
}