typealias Report = List<Int>

fun main() {

    fun Report.isSafe(strict: Boolean = true): Boolean {
        return if (strict) {
            if (this.first() < this.last()) {
                this.zipWithNext().all { it.second - it.first in 1..3 }
            } else {
                this.zipWithNext().all { it.first - it.second in 1..3 }
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
    solve("Day02", ::part1, ::part2)
}
