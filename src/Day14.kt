fun main() {

    data class Vector(val position: Coord, val velocity: Coord)

    fun parse(input: List<String>): List<Vector> {
        return input.map { line ->
            val (px, py, vx, vy) = Regex("-?\\d+").findAll(line).toList().map { it.value.toInt() }
            Vector(Coord(px, py), Coord(vx, vy))
        }
    }

    fun move(p: Int, v: Int, s: Int, m: Int) = (m + ((p + s * v) % m)) % m

    fun simulate(vector: Vector, seconds: Int, width: Int, height: Int): Coord {
        val x = move(vector.position.x, vector.velocity.x, seconds, width)
        val y = move(vector.position.y, vector.velocity.y, seconds, height)
        return Coord(x, y)
    }

    fun safetyFactor(robots: List<Coord>, width: Int, height: Int): Int {
        val q1 = robots.count { it.x < width / 2 && it.y < height / 2 }
        val q2 = robots.count { it.x > width / 2 && it.y < height / 2 }
        val q3 = robots.count { it.x > width / 2 && it.y > height / 2 }
        val q4 = robots.count { it.x < width / 2 && it.y > height / 2 }
        return q1 * q2 * q3 * q4
    }

    fun part1(input: List<String>): Number {
        val moved = parse(input).map { simulate(it, 100, 101, 103) }
        return safetyFactor(moved, 101, 103)
    }

    fun part2(input: List<String>): Number {
        /*       for (i in 0..10) {
                   val moved = parse(input).map { simulate(it, i, 101, 103) }
                   println("$i${"=".repeat(100)}")
                   for (row in 0..<103) {
                       buildString {
                           for (col in 0..<101) {
                               val c = moved.count { it.x == col && it.y == row }
                               if (c > 0) this.append(c) else this.append(".")
                           }
                       }.println()
                   }
               }
               */
        return 0
    }
    /* test single robot
        for (i in 0..5) {
            simulate(Vector(Coord(2, 4), Coord(2, -3)), i, 11, 7).println()
        }
        println()
    */
    // test before attempt to solve
    val testInput = readInput("Day14_test")
    val moved = parse(testInput).map { simulate(it, 100, 11, 7) }
    /* check visually
    for (row in 0..<7) {
        for (col in 0..<11) {
            val c = moved.count { it.x == col && it.y == row }
            if (c > 0) print(c) else print(".")
        }
        println()
    }
    */
    check(safetyFactor(moved, 11, 7) == 12)
    //check(part2(testInput) == 0L)

    // solve with real input
    solve("Day14", ::part1, ::part2)
}
