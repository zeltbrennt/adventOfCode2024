import kotlin.math.max

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
            .associate {
                (it % 10).toInt() to
                        buildList {
                            var secret = it
                            repeat(10) {
                                secret = nextSecret(secret)
                                this.add((secret % 10).toInt())
                            }
                        }
            }
        val priceDiffs = prices.toList().associate {
            it.first to (listOf(it.first) + it.second).zipWithNext { a, b -> b - a }
        }

        val sequenceMap = mutableSetOf<List<Int>>()
        priceDiffs.values.forEach { buyer ->
            buyer.windowed(4).forEach { sequenceMap.add(it) }
        }


        /*
            todo: find the optimal sequence with the best result for all buyers
            -> for every sequence, find it for every buyer
            -> if found, add it to total
            -> for every sequence, check if total is maximum

            todo: find out why example sequence not in sequence map
         */
        // val sequenceMap = setOf(listOf(-2, 1, -1, 3))
        var maxBananas = 0
        for (seq in sequenceMap) {
            var bananas = 0
            for ((buyer, diff) in priceDiffs) {
                for (i in 0..diff.lastIndex - 3) {
                    var seqFound = true
                    for (k in 0..3) {
                        if (diff[i + k] != seq[k]) {
                            seqFound = false
                            break
                        }
                    }
                    if (seqFound) {
                        bananas += prices[buyer]!![i + 3]
                        break
                    }
                }
            }
            maxBananas = max(maxBananas, bananas)
        }
        return maxBananas
    }

    // test before attempt to solve
    val testInput = readInput("Day22_test")
    val t1 = part1(testInput)
    check(part1(testInput) == 37327623L)
    part2(listOf("123"))
    check(part2(readInput("Day22_test_2")) == 23)

    // solve with real input
    solve("Day22", ::part1, ::part2)
}
