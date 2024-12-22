fun main() {

    fun nextSecret(number: Long): Long {
        var secret = number
        secret = ((secret * 64L) xor secret) % 16777216L
        secret = ((secret / 32L) xor secret) % 16777216L
        return ((secret * 2048L) xor secret) % 16777216L
    }

    fun part1(input: List<String>): Long {
        return input
            .map { it.toLong() }.sumOf {
                var secret = it
                repeat(2000) {
                    secret = nextSecret(secret)
                }
                secret
            }
    }

    fun part2(input: List<String>): Number {
        val prices = input.map { it.toLong() }
            .map {
                buildList {
                    this.add((it % 10).toInt())
                    var secret = it
                    repeat(10) {
                        secret = nextSecret(secret)
                        this.add((secret % 10).toInt())
                    }
                }
            }
        val priceDiffs = prices.map {
            it.zipWithNext { a, b -> b - a }
        }
        /*
            maybe need to drop the initial price..
            todo: given the optimal sequence, find the best price after
            todo: find the optimal sequence for one buyer
            todo: find the optimal sequence with the best result for all buyers
         */
        val optimalSequence = listOf(-1, 1, 0, 2)
        return 0
    }

    // test before attempt to solve
    val testInput = readInput("Day22_test")
    val t1 = part1(testInput)
    check(part1(testInput) == 37327623L)
    part2(listOf("123"))
    check(part2(testInput) == 0L)

    // solve with real input
    solve("Day22", ::part1, ::part2)
}
