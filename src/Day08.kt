typealias Antenna = Char

fun main() {


    fun part1(input: List<String>): Number {
        val cityMap: Grid<Antenna> = inputToSparseGrid(input)
        val antiFrequencies = mutableSetOf<Coord>()
        val visited = mutableSetOf<Antenna>()
        for (antenna in cityMap.values) {
            if (antenna in visited) continue
            visited.add(antenna)
            val coords = cityMap.data.filter { it.value == antenna }.keys
            for (a in coords) {
                for (b in coords) {
                    if (a == b) continue
                    val relative = a - b
                    antiFrequencies.add(a + relative)
                }
            }
        }
        return antiFrequencies.count {
            it.x in input[0].indices && it.y in input.indices
        }
    }

    fun part2(input: List<String>): Number {
        return 0
    }

    // test before attempt to solve
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 14)
    //check(part2(testInput) == 0L)

    // solve with real input
    solve("Day08", ::part1, ::part2)
}
