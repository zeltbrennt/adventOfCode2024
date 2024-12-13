import kotlinx.coroutines.processNextEventInCurrentThread
import java.util.PriorityQueue

fun main() {

    data class Button(val displacement: Coord, val cost: Int)
    data class Machine(val buttonA: Button, val buttonB: Button, val prize: Coord)
    data class Node(val position: Coord, val a: Int, val b: Int, val cost: Int)

    fun lineToCoord(line: String): Coord {
        val nums = Regex("\\d+").findAll(line).map { it.value.toInt() }
        return Coord(nums.first(), nums.last())
    }

    fun parse(input: List<String>): List<Machine> = buildList {
        for (i in 0..input.lastIndex step 4) {
            this.add(
                Machine(
                    Button(lineToCoord(input[i]), 3),
                    Button(lineToCoord(input[i + 1]), 1),
                    lineToCoord(input[i + 2])
                )
            )
        }
    }

    fun findMinimumTokenToWin(machine: Machine): Node {
        val queue = PriorityQueue<Node>(compareBy { it.cost })
        val visited = mutableSetOf<Node>()
        queue.add(Node(machine.buttonA.displacement, 1, 0, machine.buttonA.cost))
        queue.add(Node(machine.buttonB.displacement, 0, 1, machine.buttonB.cost))
        visited.addAll(queue)
        while (queue.isNotEmpty()) {
            val current = queue.remove() ?: break

            //println("${queue.size} ${current}")
            if (current.a > 100 || current.b > 100) continue
            if (current.position.x > machine.prize.x) continue
            if (current.position.y > machine.prize.y) continue
            if (current.position == machine.prize) return current
            val pressA = Node(
                current.position + machine.buttonA.displacement,
                current.a + 1,
                current.b,
                current.cost + machine.buttonA.cost
            )
            if (!visited.contains(pressA)) {
                visited.add(pressA)
                queue.add(pressA)
            }
            val pressB = Node(
                current.position + machine.buttonB.displacement,
                current.a,
                current.b + 1,
                current.cost + machine.buttonB.cost
            )
            if (!visited.contains(pressB)) {
                visited.add(pressB)
                queue.add(pressB)
            }
        }
        return Node(Coord(0, 0), 0, 0, 0)
    }

    fun part1(input: List<String>): Number {
        return parse(input).sumOf { findMinimumTokenToWin(it).cost }
    }

    fun part2(input: List<String>): Number {
        return 0
    }

    // test before attempt to solve
    val testInput = readInput("Day13_test")
    check(part1(testInput) == 480)
    //check(part2(testInput) == 0L)

    // solve with real input
    solve("Day13", ::part1, ::part2)
}
