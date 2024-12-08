import jdk.jfr.Frequency
import kotlin.io.path.fileVisitor

typealias Antenna = Char

fun main() {

    fun addHarmonicFrequencies(coord: Coord, distance: Coord, input: List<String>, antiFrequencies: MutableSet<Coord>) {
        var scaleFactor = 0
        while (true) {
            val antiFreq = coord + distance * scaleFactor
            if (antiFreq.x in input[0].indices && antiFreq.y in input.indices) {
                antiFrequencies.add(antiFreq)
                scaleFactor++
            } else {
                break
            }
        }
    }

    fun addAntinodes(coord: Coord, distance: Coord, input: List<String>, antiFrequencies: MutableSet<Coord>) {
        val node = coord + distance
        if (node.x in input[0].indices && node.y in input.indices) {
            antiFrequencies.add(node)
        }
    }

    fun List<String>.solveWith(add: (Coord, Coord, List<String>, MutableSet<Coord>) -> Unit): Number {
        val cityMap: Grid<Antenna> = inputToSparseGrid(this)
        val antiFrequencies = mutableSetOf<Coord>()
        val visited = mutableSetOf<Antenna>()
        for (antenna in cityMap.values) {
            if (antenna in visited) continue
            visited.add(antenna)
            val coords = cityMap.data.filter { it.value == antenna }.keys
            for (first in coords) {
                for (second in coords) {
                    if (first == second) continue
                    val distance = first - second
                    add(first, distance, this, antiFrequencies)
                }
            }
        }
        return antiFrequencies.count()
    }

    fun part1(input: List<String>): Number {
        return input.solveWith(::addAntinodes)
    }

    fun part2(input: List<String>): Number {
        return input.solveWith(::addHarmonicFrequencies)
    }

    // test before attempt to solve
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 14)
    check(part2(testInput) == 34)

    // solve with real input
    solve("Day08", ::part1, ::part2)
}
