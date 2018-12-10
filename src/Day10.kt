import java.io.File

fun main() {
    val day10 = Day10()
    day10.part1()
    day10.part2()
}

class Day10 {

    fun part1() {
        val inputs = File("data-day10-part1").readLines()
        val data = inputs.map {
            var regex = "(-?[0-9]+)".toRegex().find(it)!!
            val x = regex.groups[0]!!.value.toInt()
            regex = regex.next()!!
            val y = regex.groups[0]!!.value.toInt()
            regex = regex.next()!!
            val vx = regex.groups[0]!!.value.toInt()
            regex = regex.next()!!
            val vy = regex.groups[0]!!.value.toInt()

            Pair(Pair(x, y),
                Pair(vx, vy))
        }

        val output = File("output/day-10.txt")
        var previousHeight = Int.MAX_VALUE
        output.printWriter().use { writer ->
            for (t in 0..1000000) {
                val coordinatesAfterNSeconds = data.map {
                    val x = it.first.first
                    val y = it.first.second
                    val vx = it.second.first
                    val vy = it.second.second
                    Pair(x + vx * t, y + vy * t)
                }.sortedWith(compareBy({it.second}, {it.first}))

                val coordinateYMap = coordinatesAfterNSeconds.groupBy { it.second }

                val currentHeight = coordinateYMap.count()
                if (currentHeight < previousHeight) {

                    val coordinateXMap = coordinatesAfterNSeconds.groupBy { it.first }

                    val minX = coordinatesAfterNSeconds.minBy { it.first }!!.first
                    val minY = coordinatesAfterNSeconds.first().second
                    val maxX = coordinatesAfterNSeconds.maxBy { it.first }!!.first
                    val maxY = coordinatesAfterNSeconds.last().second


                    for (y in minY..maxY) {
                        if (!coordinateYMap.contains(y)) {
                            continue
                        }
                        for (x in minX..maxX) {
                            if (!coordinateXMap.contains(x)) {
                                continue
                            }
                            val coordinate = coordinateYMap[y]
                            if (coordinate != null && coordinate.contains(Pair(x, y))) {
                                writer.print("#")
                            } else {
                                writer.print(".")
                            }
                        }
                        writer.println()
                    }
                    println(t)
                    previousHeight = currentHeight
                    writer.println()
                    writer.println()
                }

            }
        }
        println("Done")
    }

    fun part2() {
        val inputs = File("data-day10-part1").readLines()
        val data = inputs.map {
            var regex = "(-?[0-9]+)".toRegex().find(it)!!
            val x = regex.groups[0]!!.value.toInt()
            regex = regex.next()!!
            val y = regex.groups[0]!!.value.toInt()
            regex = regex.next()!!
            val vx = regex.groups[0]!!.value.toInt()
            regex = regex.next()!!
            val vy = regex.groups[0]!!.value.toInt()

            Pair(Pair(x, y),
                Pair(vx, vy))
        }

        val output = File("output/day-10.txt")
        var previousHeight = Int.MAX_VALUE
        output.printWriter().use { writer ->
            for (t in 0..1000000) {
                val coordinatesAfterNSeconds = data.map {
                    val x = it.first.first
                    val y = it.first.second
                    val vx = it.second.first
                    val vy = it.second.second
                    Pair(x + vx * t, y + vy * t)
                }.sortedWith(compareBy({it.second}, {it.first}))

                val coordinateYMap = coordinatesAfterNSeconds.groupBy { it.second }

                val currentHeight = coordinateYMap.count()
                if (currentHeight < previousHeight) {

                    val coordinateXMap = coordinatesAfterNSeconds.groupBy { it.first }

                    val minX = coordinatesAfterNSeconds.minBy { it.first }!!.first
                    val minY = coordinatesAfterNSeconds.first().second
                    val maxX = coordinatesAfterNSeconds.maxBy { it.first }!!.first
                    val maxY = coordinatesAfterNSeconds.last().second


                    for (y in minY..maxY) {
                        if (!coordinateYMap.contains(y)) {
                            continue
                        }
                        for (x in minX..maxX) {
                            if (!coordinateXMap.contains(x)) {
                                continue
                            }
                            val coordinate = coordinateYMap[y]
                            if (coordinate != null && coordinate.contains(Pair(x, y))) {
                                writer.print("#")
                            } else {
                                writer.print(".")
                            }
                        }
                        writer.println()
                    }
                    println(t)
                    previousHeight = currentHeight
                    writer.println()
                    writer.println()
                }

            }
        }
        println("Done")
    }
}