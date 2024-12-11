import kotlin.math.pow

fun main() {

    fun blink(stone: Long, depth: Int, memo: MutableMap<Pair<Long, Int>, Long>) : Long{
        val nDigits = getNumberOfDigits(stone)
        when {
            stone to depth in memo -> return memo[stone to depth]!!
            depth == 0 -> {
                memo[stone to 0] = 1
                return 1
            }
            stone == 0L -> {
                memo[0L to depth] = blink(1, depth - 1, memo)
                return memo[0L to depth]!!
            }
            nDigits % 2 == 0 -> {
                val split = 10.0.pow(nDigits / 2).toLong()
                memo[stone to depth] =  blink(stone / split, depth - 1,memo) +
                                        blink(stone % split, depth - 1,memo)
                return memo[stone to depth]!!
            }
            else -> {
                memo[stone to depth] = blink(stone * 2024, depth - 1, memo)
                return memo[stone to depth]!!
            }
        }

    }

    fun part1(input: List<String>): Number {
         return input[0].split(" ").sumOf {
             blink(it.toLong(), 25, mutableMapOf())
         }
    }

    fun part2(input: List<String>): Number {
        return input[0].split(" ").sumOf {
            blink(it.toLong(), 75, mutableMapOf())
        }
    }

    // test before attempt to solve
    val testInput = readInput("Day11_test")
    check(part1(testInput) == 55312L)
    //check(part2(testInput) == 22L)

    // solve with real input
    solve("Day11", ::part1, ::part2)
}
