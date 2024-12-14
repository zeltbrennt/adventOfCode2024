import java.awt.Dimension
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import kotlin.math.pow
import kotlin.math.abs

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
        val size = Dimension(101, 103)

        val allVX = mutableListOf<Double>()
        val allVY = mutableListOf<Double>()

        for (i in 0..<10000) { // just a guess...

            val moved = parse(input).map { simulate(it, i, size.width, size.height) }

            val mx = moved.sumOf { it.x } / moved.count().toDouble()
            val my = moved.sumOf { it.y } / moved.count().toDouble()
            val vx = moved.sumOf { (it.x - mx).pow(2) }
            val vy = moved.sumOf { (it.y - my).pow(2) }

            val relx = vx / (allVX.sum() / allVX.count())
            val rely = vy / (allVY.sum() / allVY.count())
            allVX.add(vx)
            allVY.add(vy)
            if (abs(1 - relx) > 0.2 && abs(1 - rely) > 0.2) { // also guessing...

                val title = i.toString().padStart(4, '0')
                val img = BufferedImage(size.width, size.height, BufferedImage.TYPE_BYTE_GRAY)

                for (x in 0..<size.width) {
                    for (y in 0..<size.height) {
                        val c = moved.count { it.x == x && it.y == y }
                        if (c > 0) img.setRGB(x, y, 0x0) else img.setRGB(x, y, 0xFFFFFF)
                    }
                }
                ImageIO.write(img, "BMP", File("src/day14_img$title.bmp"))

                return i
            }
        }
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
