import kotlin.time.measureTime

typealias Report = List<Int>

fun main() {

    fun Report.isSafe(strict: Boolean = true): Boolean {
        return if (strict) {
            if (this.first() < this.last()) {
                this.windowed(2).all { it.last() - it.first() in 1..3 }
            } else {
                this.windowed(2).all { it.first() - it.last() in 1..3 }
            }
        } else {
            indices.any { this.filterIndexed { index, _ -> index != it }.isSafe() }
        }
    }

    fun inputToReports(input: List<String>): List<Report> {
        return input.map { line -> line.split(" ").map { it.toInt() } }
    }

    fun part1(input: List<String>): Int {
        val reports = inputToReports(input)
        return reports.count { it.isSafe() }
    }

    fun part2(input: List<String>): Int {
        val reports = inputToReports(input)
        return reports.count { it.isSafe(false) }
    }

    // test before attempt to solve
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    // solve with real input
    val input = readInput("Day02")
    measureTime { print("Part 1: ${part1(input)}".padEnd(40, ' ')) }.also { println("$it") }
    measureTime { print("Part 2: ${part2(input)}".padEnd(40, ' ')) }.also { println("$it") }
}
