import kotlin.math.pow

fun main() {

    data class Counter(var value: Long)

    fun blink(stone: Long, times: Int, counter: Counter) {
        val nDigits = getNumberOfDigits(stone)
        when {
            times == 0 -> {
                counter.value++
               // print("$stone ")
            }
            stone == 0L -> blink(1, times -1, counter)
            nDigits % 2 == 0 -> {
                val split = 10.0.pow(nDigits / 2).toLong()
                blink(stone / split, times - 1,counter)
                blink(stone % split, times - 1,counter)
            }
            else -> blink(stone * 2024, times -1, counter)
        }

    }

    fun part1(input: List<String>): Number {
        val counter = Counter(0)
        input[0].split(" ").map { it.toLong() }.forEach {
            blink(it, 25, counter)
        }
       // counter.println()
        return counter.value
    }

    fun part2(input: List<String>): Number {
        val counter = Counter(0)
        input[0].split(" ").map { it.toLong()}.forEach {
            blink(it, 6, counter)
        }
        //counter.println()
        return counter.value
    }

    // test before attempt to solve
    val testInput = readInput("Day11_test")
    check(part1(testInput) == 55312L)
    check(part2(testInput) == 22L)

    // solve with real input
    solve("Day11", ::part1, ::part2)
}
