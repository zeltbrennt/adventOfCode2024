import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow
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
    val sec = this.inWholeSeconds
    val milli = (this.inWholeMilliseconds % 1_000).toString().padStart(5, ' ')
    val micro = ((this.inWholeMicroseconds % 1_0000) / 10.0).toString().padStart(7, ' ')
    return "${if (sec > 0L) sec.toString().padStart(3, ' ') + "s" else ""}${milli}ms${micro}us"
}

fun concat(a: Long, b: Long): Long {
    if (b < 10) return a * 10 + b
    var aShifted = a * 10
    var bRemain = b / 10
    while (bRemain > 0) {
        aShifted *= 10
        bRemain /= 10
    }
    return aShifted + b
}

fun endsWith(a: Long, b: Long): Boolean {
    return a % 10.0.pow(getNumberOfDigits(b)).toLong() == b
}

fun getNumberOfDigits(a: Long): Int {
    return floor(1 + log10(a.toFloat())).toInt()
}
