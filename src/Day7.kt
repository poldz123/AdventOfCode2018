import java.io.File
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

fun main() {
    val day7 = Day7()
//    day7.part1()
    day7.part2()
}

class Day7 {

    fun part1() {
        val inputs = File("data-day7-part1").readLines().map {
            val data = it.split("\\s+".toRegex())
            require(data[1].count() == 1)
            require(data[7].count() == 1)
            Pair(data[1].first(), data[7].first())
        }

        val stepMapToNextSteps = mutableMapOf<Char, MutableSet<Char>>()
        val stepMapToPreviousSteps  = mutableMapOf<Char, MutableSet<Char>>()
        inputs.forEach {
            val nextArray = stepMapToNextSteps[it.first]
            if (nextArray == null){
                stepMapToNextSteps[it.first] = mutableSetOf(it.second)
            } else {
                nextArray.add(it.second)
            }
            val previousArray = stepMapToPreviousSteps[it.second]
            if (previousArray == null) {
                stepMapToPreviousSteps[it.second] = mutableSetOf(it.first)
            } else {
                previousArray.add(it.first)
            }
        }

        val initialSteps = inputs.map { it.first }.filter { stepMapToPreviousSteps[it] == null }.toSortedSet()
        var solution = ""

        fun nextStep(steps: MutableSet<Char>) {
            if (steps.isEmpty()) return

            val step = steps.first()
            val nextSteps = stepMapToNextSteps[step]

            steps.remove(step)
            solution += step.toString()

            if (nextSteps == null || nextSteps.isEmpty())
                return

            nextSteps.forEach {
                val previousSteps = stepMapToPreviousSteps[it]!!
                previousSteps.remove(step)
                if (previousSteps.isEmpty()) {
                    steps.add(it)
                }
            }

            nextStep(steps)
        }

        nextStep(initialSteps)
        println(solution)
    }

    fun part2() {
        val numWorkers = 5
        val inputs = File("data-day7-part2").readLines().map {
            val data = it.split("\\s+".toRegex())
            require(data[1].count() == 1)
            require(data[7].count() == 1)
            Pair(data[1].first(), data[7].first())
        }

        val stepMapToNextSteps = mutableMapOf<Char, MutableSet<Char>>()
        val stepMapToPreviousSteps  = mutableMapOf<Char, MutableSet<Char>>()
        inputs.forEach {
            val nextArray = stepMapToNextSteps[it.first]
            if (nextArray == null){
                stepMapToNextSteps[it.first] = mutableSetOf(it.second)
            } else {
                nextArray.add(it.second)
            }
            val previousArray = stepMapToPreviousSteps[it.second]
            if (previousArray == null) {
                stepMapToPreviousSteps[it.second] = mutableSetOf(it.first)
            } else {
                previousArray.add(it.first)
            }
        }

        val initialSteps = inputs.map { it.first }.filter { stepMapToPreviousSteps[it] == null }.toSortedSet()
        var solution = 0

        fun nextStep(steps: MutableSet<Char>) {
            if (steps.isEmpty()) return

            val step = steps.first()
            val nextSteps = stepMapToNextSteps[step]

            steps.remove(step)

            if (nextSteps == null || nextSteps.isEmpty())
                return

            if (nextSteps.count() == 1) {
                solution += step.toLowerCase() - 'a' + 1
            }

            nextSteps.forEach {
                val previousSteps = stepMapToPreviousSteps[it]!!
                previousSteps.remove(step)
                if (previousSteps.isEmpty()) {
                    steps.add(it)
                }
            }

            nextStep(steps)
        }

        nextStep(initialSteps)
        println(solution)
    }
}