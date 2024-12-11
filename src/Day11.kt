import kotlin.math.pow

fun main() {

    fun blink(stone: Long, depth: Int, cache: MutableMap<Pair<Long, Int>, Long>) : Long {
        if (stone to depth in cache) return cache[stone to depth]!!
        when {
            depth == 0 -> {
                cache[stone to 0] = 1
                return 1
            }

            stone == 0L -> {
                cache[0L to depth] = blink(1, depth - 1, cache)
                return cache[0L to depth]!!
            }
        }
        val nDigits = getNumberOfDigits(stone)
        when {
            nDigits % 2 == 0 -> {
                val split = 10.0.pow(nDigits / 2).toLong()
                cache[stone to depth] = blink(stone / split, depth - 1,cache) +
                                        blink(stone % split, depth - 1,cache)
                return cache[stone to depth]!!
            }
            else -> {
                cache[stone to depth] = blink(stone * 2024, depth - 1, cache)
                return cache[stone to depth]!!
            }
        }
    }

    fun solve(input: List<String>, times: Int): Long {
        val cache = mutableMapOf<Pair<Long, Int>,Long>()
        return input[0].split(" ").sumOf { blink(it.toLong(), times, cache) }
    }

    fun part1(input: List<String>): Number {
         return solve(input, 25)
    }

    fun part2(input: List<String>): Number {
        return solve(input, 75)
    }

    // test before attempt to solve
    val testInput = readInput("Day11_test")
    check(part1(testInput) == 55312L)
    //check(part2(testInput) == 22L)

    // solve with real input
    solve("Day11", ::part1, ::part2)
}
