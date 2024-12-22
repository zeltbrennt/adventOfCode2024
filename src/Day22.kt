import kotlin.math.max
import kotlin.math.roundToInt

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
        val prices = input.map { it.toLong() }.associateWith {
            buildList {
                var secret = it
                repeat(2000) {
                    secret = nextSecret(secret)
                    this.add((secret % 10).toInt())
                }
            }
        }
        val priceDiffs = prices.toList().associate {
            it.first to (listOf((it.first % 10).toInt()) + it.second).zipWithNext { a, b -> b - a }
        }

        val sequenceMap = mutableSetOf<List<Int>>()
        priceDiffs.values.forEach { buyer ->
            buyer.windowed(4).forEach { sequenceMap.add(it) }
        }

        // brute force
        var maxBananas = 0
        var seqCount = 0
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
            seqCount += 1
            if (seqCount % 1000 == 0) println("${(seqCount / (sequenceMap.size.toFloat()) * 100).roundToInt()}% ($seqCount / ${sequenceMap.size})")
            maxBananas = max(maxBananas, bananas)
        }
        return maxBananas
    }

    // test before attempt to solve
    val testInput = readInput("Day22_test")
    check(part1(testInput) == 37327623L)
    check(part2(readInput("Day22_test_2")) == 23)

    // solve with real input
    solve("Day22", ::part1, ::part2)
}
