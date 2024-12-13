import kotlinx.coroutines.processNextEventInCurrentThread
import java.util.PriorityQueue

fun main() {

    data class Equation(val a: Long, val b: Long, val p: Long) {
        operator fun plus(other: Equation) = this.copy(a = this.a + other.a, b = this.b + other.b, p = this.p + other.p)
    }

    fun lineToEquation(line: String): Pair<Long, Long> {
        val (a, b) = Regex("\\d+").findAll(line).toList().map { it.value.toLong() }
        return a to b
    }

    fun parse(input: List<String>, offset: Long): List<Pair<Equation, Equation>> = buildList {
        for (i in 0..input.lastIndex step 4) {
            val a = lineToEquation(input[i])
            val b = lineToEquation(input[i + 1])
            val x = lineToEquation(input[i + 2])
            val e1 = Equation(
                a.first,
                b.first,
                (x.first + offset)
            )
            val e2 = Equation(
                a.second,
                b.second,
                (x.second + offset)
            )
            this.add(e1 to e2)
        }
    }

    fun findMinimumTokenToWin(original: Pair<Equation, Equation>): Pair<Long, Long> {
        // multiply
        val f1 = original.first.b
        val f2 = -original.second.b
        val first =
            original.first.copy(a = original.first.a * f2, b = original.first.b * f2, p = original.first.p * f2)
        val second =
            original.second.copy(a = original.second.a * f1, b = original.second.b * f1, p = original.second.p * f1)
        // add together
        val added = first + second
        // sove for a
        if (added.p % added.a != 0L) return 0L to 0L
        val a = added.p / added.a
        // solve for b
        val b = (original.first.p - original.first.a * a) / original.first.b
        // check
        if (original.second.p == original.second.a * a + original.second.b * b) return a to b
        return 0L to 0L
    }

    fun part1(input: List<String>): Number {
        return parse(input, 0).map { findMinimumTokenToWin(it) }.filter { it.first <= 100 && it.second <= 100 }
            .sumOf { it.first * 3 + it.second }
    }

    fun part2(input: List<String>): Number {
        return parse(input, 10000000000000L).map { findMinimumTokenToWin(it) }
            .sumOf { it.first * 3 + it.second }
    }

    // test before attempt to solve
    val testInput = readInput("Day13_test")
    check(part1(testInput) == 480L)
    //check(part2(testInput) == 0L)

    // solve with real input
    solve("Day13", ::part1, ::part2)
}
