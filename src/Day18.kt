fun main() {


    fun solve(input: List<String>, maxCoord: Int, nBytes: Int): List<Coord> {
        val blocked = buildSet {
            input.slice(0..<nBytes).forEach {
                val line = it.split(",")
                this.add(Coord(line.first().toInt(), line.last().toInt()))
            }
        }
        val start = Coord(0, 0)
        val finish = Coord(maxCoord, maxCoord)
        val connections = mutableMapOf(start to start)
        val visited = mutableSetOf(start)
        val queue = ArrayDeque<Coord>()
        queue.add(start)
        //bfs
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            if (current == finish) break
            Grid.mainNeighbors(current)
                .filter {
                    it.x in 0..maxCoord && it.y in 0..maxCoord &&
                            visited.contains(it).not() && blocked.contains(it).not()
                }
                .forEach {
                    visited.add(it)
                    queue.add(it)
                    connections[it] = current
                }
        }
        //trace path
        var current = finish
        val path = mutableListOf<Coord>()
        while (current != start) {
            current = connections[current] ?: break
            path.add(current)
        }
        return path.reversed()
    }

    fun solve2(input: List<String>, maxCoord: Int, nByte: Int): Float {
        var path = solve(input, maxCoord, 0)
        input.forEachIndexed { i, byte ->
            val coord = byte.split(",").map { it.trim().toInt() }
            if (Coord(coord.first(), coord.last()) in path) {
                path = solve(input, maxCoord, i + 1)
                if (path.isEmpty()) {
                    return byte.replace(",", ".").toFloat()
                }
            }
        }
        return 0F
    }


    fun part1(input: List<String>): Number {
        return solve(input, 70, 1024).size
    }

    fun part2(input: List<String>): Number {
        return solve2(input, 70, 1024)
    }

    // test before attempt to solve
    val testInput = readInput("Day18_test")
    check(solve(testInput, 6, 12).size == 22)
    check(solve2(testInput, 6, 12) == 6.1F)

    // solve with real input
    solve("Day18", ::part1, ::part2)
}
