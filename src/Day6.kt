import java.io.File
import kotlin.math.max

fun main() {
    val day6 = Day6()
    day6.part1()
    day6.part2()
}

class Day6 {

    fun part1() {
        val inputs = File("data-day6-part1").readLines()
        val xs = inputs.map { it.split(",").first().trim().toInt() }
        val ys = inputs.map { it.split(",").last().trim().toInt() }
        val maxX = xs.max()!!
        val maxY = ys.max()!!
        val max = max(maxX, maxY)
        val grid = Array(maxX + 1) {
            IntArray(maxY + 1)
        }
        val infiniteAreasByIndex = BooleanArray(xs.count())
        val totalAreasOfIndex = IntArray(xs.count())
        for (x1 in 0 until max + 1) {
            for (y1 in 0 until max + 1) {
                val calculatedDistance = IntArray(xs.count())
                for (i in 0 until xs.count()) {
                    val x2 = xs[i]
                    val y2 = ys[i]
                    val distance = Math.abs(x1-x2) + Math.abs(y1-y2)
                    calculatedDistance[i] = distance
                }
                val closestDistance = calculatedDistance.min()!!
                val closestCoordinates = calculatedDistance.mapIndexed { index, i -> Pair(index, i) }.filter { it.second == closestDistance }
                if (closestCoordinates.count() == 1) {
                    val closestCoordinate = closestCoordinates.first()
                    val closestIndex = closestCoordinate.first
                    if ((x1 >= 0 && x1 <= max && (y1 == 0 || y1 == max)) ||
                        (y1 >= 0 && y1 <= max && (x1 == 0 || x1 == max))) {
                        infiniteAreasByIndex[closestIndex] = true
                    }
                    totalAreasOfIndex[closestIndex] += 1
                }
            }
        }
        val solution = totalAreasOfIndex.mapIndexed { index, i -> Pair(index, i) }.filter { !infiniteAreasByIndex[it.first] }.maxBy { it.second }!!
        println(solution.second)
    }

    fun part2() {
        val inputs = File("data-day6-part2").readLines()
        val xs = inputs.map { it.split(",").first().trim().toInt() }
        val ys = inputs.map { it.split(",").last().trim().toInt() }
        val maxX = xs.max()!!
        val maxY = ys.max()!!
        val max = max(maxX, maxY)
        val grid = Array(maxX + 1) {
            IntArray(maxY + 1)
        }
        var regionSize = 0
        for (x1 in 0 until max + 1) {
            for (y1 in 0 until max + 1) {
                var distance = 0
                for (i in 0 until xs.count()) {
                    val x2 = xs[i]
                    val y2 = ys[i]
                    distance += Math.abs(x1-x2) + Math.abs(y1-y2)
                }
                if (distance < 10000) {
                    regionSize += 1
                }
            }
        }
        println(regionSize)
    }
}