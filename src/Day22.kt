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
