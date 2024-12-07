import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.time.Duration
import kotlin.time.measureTime

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/input/$name.txt").readText().trim().lines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

fun solve(day: String, part1: (List<String>) -> Number, part2: (List<String>) -> Number) {
    val input = readInput(day)
    println("\n $day\n${"=".repeat(50)}")
    var result1: Number = 0
    var result2: Number = 0
    val time1 = measureTime { result1 = part1(input) }
    println(" Part 1: ${result1.toString().padEnd(20, ' ')}${time1.pretty()}")
    val time2 = measureTime { result2 = part2(input) }
    println(" Part 2: ${result2.toString().padEnd(20, ' ')}${time2.pretty()}")
}

private fun Duration.pretty(): String {
    val sec = (this.inWholeSeconds).toString().padStart(3, ' ')
    val milli = (this.inWholeMilliseconds % 1_000).toString().padStart(5, ' ')
    val micro = ((this.inWholeMicroseconds % 1_0000) / 10.0).toString().padStart(7, ' ')
    return "${sec}s${milli}ms${micro}µs"
}
