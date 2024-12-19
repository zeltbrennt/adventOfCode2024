fun main() {


    fun solve(input: List<String>, maxCoord: Int, nBytes: Int) : Int {
        val blocked = buildSet{
            input.slice(0..<nBytes).forEach {
                val line = it.split(",")
                this.add(Coord(line.first().toInt(), line.last().toInt()))
            }
        }
        val start = Coord(0,0)
        val finish = Coord(maxCoord, maxCoord)
        val path = mutableMapOf(start to start)
        val visited = mutableSetOf(start)
        val queue = ArrayDeque<Coord>()
        queue.add(start)
        //bfs
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            if (current == finish) break
            Grid.mainNeighbors(current)
                .filter { it.x in 0..maxCoord && it.y in 0..maxCoord &&
                        visited.contains(it).not() && blocked.contains(it).not() }
                .forEach {
                    visited.add(it)
                    queue.add(it)
                    path[it] = current
                }
        }
        //trace path
        var current = finish
        var steps = 0
        while (current != start) {
            current = path[current] ?: break
            steps++
        }
        return steps
    }

    fun solve2(input: List<String>, maxCoord: Int, nByte: Int) : Float {
        for(byte in nByte..input.lastIndex) {
            if (solve(input, maxCoord, byte) == 0) {
                return input[byte-1].replace(",", ".").toFloat()
            }
        }
        return 0F
    }


    fun part1(input: List<String>): Number {
        return solve(input, 70, 1024)
    }

    fun part2(input: List<String>): Number {
        return solve2(input, 70, 1024)
    }

    // test before attempt to solve
    val testInput = readInput("Day18_test")
    check(solve(testInput, 6, 12) == 22)
    check(solve2(testInput, 6, 12) == 6.1F)

    // solve with real input
    solve("Day18", ::part1, ::part2)
}
