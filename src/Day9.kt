fun main() {
    val day9 = Day9()
    day9.part1()
    day9.part2()
}

class Day9 {
    private val numPlayers = 404
    private val lastMarble = 71852L

    fun part1() {
        val arrayPlayers = LongArray(numPlayers)

        val baseNode = Node(null, null, 0)
        baseNode.right = baseNode
        baseNode.left = baseNode
        var currentNode = baseNode
        var currentPlayerIndex = 0
        for (i in 1..lastMarble) {
            currentPlayerIndex = if (currentPlayerIndex + 1 > arrayPlayers.count() - 1) 0 else currentPlayerIndex + 1
            if (i.rem(23) == 0L) {
                for (i2 in 1..7) {
                    requireNotNull(currentNode)
                    currentNode = currentNode.moveLeft()
                }
                arrayPlayers[currentPlayerIndex] += i
                arrayPlayers[currentPlayerIndex] += currentNode.value
                currentNode = currentNode.remove()
            } else {
                currentNode = currentNode.moveRight().addRight(i)
            }
        }
        println(arrayPlayers.max())
    }

    fun part2() {
        val arrayPlayers = LongArray(numPlayers)

        val baseNode = Node(null, null, 0)
        baseNode.right = baseNode
        baseNode.left = baseNode
        var currentNode = baseNode
        var currentPlayerIndex = 0
        for (i in 1..lastMarble * 100) {
            currentPlayerIndex = if (currentPlayerIndex + 1 > arrayPlayers.count() - 1) 0 else currentPlayerIndex + 1
            if (i.rem(23) == 0L) {
                for (i2 in 1..7) {
                    requireNotNull(currentNode)
                    currentNode = currentNode.moveLeft()
                }
                arrayPlayers[currentPlayerIndex] += i
                arrayPlayers[currentPlayerIndex] += currentNode.value
                currentNode = currentNode.remove()
            } else {
                currentNode = currentNode.moveRight().addRight(i)
            }
        }
        println(arrayPlayers.max())

    }

    data class Node(var left: Node?, var right: Node?, var value: Long) {
        fun moveRight(): Node {
            val right = this.right
            requireNotNull(right)
            return right
        }

        fun moveLeft(): Node {
            val left = this.left
            requireNotNull(left)
            return left
        }

        fun addRight(value: Long): Node {
            val node = Node(this, right, value)
            right?.left = node
            right = node
            return node
        }

        fun remove(): Node {
            val right = this.right
            val left = this.left
            requireNotNull(right)
            requireNotNull(left)
            right.left = left
            left.right = right
            return right
        }

        fun addLeft(value: Long): Node {
            val node = Node(left, this, value)
            left?.right = node
            left = node
            return node
        }
    }
}