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
                    var i = 0
                    while (true) {
                        val antiFreq = a + relative * i
                        if (antiFreq.x in input[0].indices && antiFreq.y in input.indices) {
                            antiFrequencies.add(antiFreq)
                            i++
                        } else {
                            break
                        }
                    }
                }
            }
        }
        return antiFrequencies.count()
    }

    // test before attempt to solve
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 14)
    check(part2(testInput) == 34)

    // solve with real input
    solve("Day08", ::part1, ::part2)
}
