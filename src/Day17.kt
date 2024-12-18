import kotlin.math.pow

fun main() {

    var A = 0
    var B = 0
    var C = 0
    val stdout = mutableListOf<Int>()
    var pointer = 0

    fun combo(op: Int) = when (op) {
        in 0..3 -> op
        4 -> A
        5 -> B
        6 -> C
        else -> throw Error("nope")
    }

    fun adv(op: Int) {
        A = (A / 2.0.pow(combo(op))).toInt()
    }

    fun bxl(op: Int) {
        B = B xor op
    }

    fun bst(op: Int) {
        B = combo(op) % 8
    }

    fun jnz(op: Int) {
        if (A == 0) return
        pointer = op - 2 //pointer is not increased...
    }

    fun bxc(op: Int) {
        B = C xor B
    }

    fun out(op: Int) {
        val p = combo(op) % 8
        stdout += p
        // print("$p ")
    }

    fun bdv(op: Int) {
        B = (A / 2.0.pow(combo(op))).toInt()
    }

    fun cdv(op: Int) {
        C = (A / 2.0.pow(combo(op))).toInt()
    }

    fun run(program: List<Int>): String {
        while (pointer < program.lastIndex) {
            when (program[pointer]) {
                0 -> adv(program[pointer + 1])
                1 -> bxl(program[pointer + 1])
                2 -> bst(program[pointer + 1])
                3 -> jnz(program[pointer + 1])
                4 -> bxc(program[pointer + 1])
                5 -> out(program[pointer + 1])
                6 -> bdv(program[pointer + 1])
                7 -> cdv(program[pointer + 1])
            }
            pointer += 2
        }
        return stdout.joinToString(",")
    }

    fun reset() {
        A = 0
        B = 0
        C = 0
        pointer = 0
        stdout.clear()
    }

    fun part1(input: List<String>): String {
        A = input[0].substringAfter(": ").toInt()
        B = input[1].substringAfter(": ").toInt()
        C = input[2].substringAfter(": ").toInt()
        val program = input[4].substringAfter(": ").split(",").map { it.toInt() }
        val result = run(program)
        //println(result)
        return result
    }

    fun part2(input: List<String>): Number {
        return 0
    }

    print("test 1: ")
    reset()
    C = 9
    run(listOf(2, 6))
    check(B == 1)
    println("OK")

    print("test 2: ")
    reset()
    A = 10
    run(listOf(5, 0, 5, 1, 5, 4))
    check(stdout == listOf(0, 1, 2))
    println("OK")

    print("test 3: ")
    reset()
    A = 2024
    run(listOf(0, 1, 5, 4, 3, 0))
    check(stdout == listOf(4, 2, 5, 6, 7, 7, 7, 7, 3, 1, 0))
    check(A == 0)
    println("OK")

    print("test 4: ")
    reset()
    B = 29
    run(listOf(1, 7))
    check(B == 26)
    println("OK")

    print("test 5: ")
    reset()
    B = 2024
    C = 43690
    run(listOf(4, 0))
    check(B == 44354)
    println("OK")


    reset()
    // test before attempt to solve
    val testInput = readInput("Day17_test")
    check(part1(testInput) == "4,6,3,5,6,3,5,2,1,0")

    //check(part2(testInput) == 0L)

    // solve with real input
    reset()
    println("part 1:")
    part1(readInput("Day17")).println()

    //solve("Day17", ::part1, ::part2)
}
