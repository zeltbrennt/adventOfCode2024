import kotlinx.coroutines.processNextEventInCurrentThread

fun main() {

    fun parseDirections(line: String, list: MutableList<Direction>) {
        line.forEach { d ->
            val dir = when (d) {
                '<' -> Direction.WEST
                '>' -> Direction.EAST
                '^' -> Direction.NORTH
                'v' -> Direction.SOUTH
                else -> throw Error("Invalid input")
            }
            list.add(dir)
        }
    }

    fun parse(input: List<String>): Pair<MutableMap<Coord, Char>, List<Direction>> {
        val map = mutableMapOf<Coord, Char>()
        val list = mutableListOf<Direction>()
        input.forEachIndexed { row, line ->
            if (line.startsWith('#')) {
                line.forEachIndexed { col, c ->
                    map[Coord(col, row)] = c
                }
            }
            if ("<>v^".any { line.contains(it) }) {
                parseDirections(line, list)
            }
        }
        return map to list.toList()
    }

    fun parse2(input: List<String>): Pair<MutableMap<Coord, Char>, List<Direction>> {
        val map = mutableMapOf<Coord, Char>()
        val list = mutableListOf<Direction>()
        input.forEachIndexed { row, line ->
            if (line.startsWith('#')) {
                line.forEachIndexed { col, c ->
                    when (c) {
                        in "#." -> {
                            map[Coord(col * 2, row)] = c
                            map[Coord(col * 2 + 1, row)] = c
                        }

                        'O' -> {
                            map[Coord(col * 2, row)] = '['
                            map[Coord(col * 2 + 1, row)] = ']'
                        }

                        else -> {
                            map[Coord(col * 2, row)] = '@'
                            map[Coord(col * 2 + 1, row)] = '.'
                        }
                    }
                }
            }
            if ("<>v^".any { line.contains(it) }) {
                parseDirections(line, list)
            }
        }
        return map to list.toList()
    }

    fun attemptToMove(warhouse: MutableMap<Coord, Char>, position: Coord, direction: Direction) {
        val next = Grid.next(position, direction)
        if (warhouse[next] == '#') return
        if (warhouse[next] == 'O' || warhouse[next] == '[' || warhouse[next] == ']') attemptToMove(
            warhouse,
            next,
            direction
        )
        if (warhouse[next] == '.') {
            val tmp = warhouse[next]!!
            warhouse[next] = warhouse[position]!!
            warhouse[position] = tmp
            return
        }
    }

    fun attemptToMoveVertically(
        warhouse: MutableMap<Coord, Char>,
        position: Coord,
        direction: Direction,
        update: MutableSet<Pair<Coord, Coord>>,
        secondHalf: Boolean,
    ) {
        val next = warhouse[Grid.next(position, direction)]!!
        if (next == '#') throw Error("blocked")
        update.add(position to Grid.next(position, direction))
        if (warhouse[position]!! == '[' && !secondHalf) attemptToMoveVertically(
            warhouse,
            Grid.east(position),
            direction,
            update,
            true
        ) else if (warhouse[position]!! == ']' && !secondHalf) attemptToMoveVertically(
            warhouse,
            Grid.west(position),
            direction,
            update,
            true
        )
        if (next in "[]") {
            attemptToMoveVertically(
                warhouse,
                Grid.next(position, direction),
                direction,
                update,
                false
            )
        }
    }


    fun toString(warhouse: MutableMap<Coord, Char>) = buildString {

        for (y in 0..warhouse.keys.maxOf { it.y }) {
            for (x in 0..warhouse.keys.maxOf { it.x }) {
                this.append(warhouse[Coord(x, y)])
            }
            this.append("\n")
        }
    }


    fun part1(input: List<String>): Number {
        val (warehouse, moves) = parse(input)
        var robot = warehouse.filter { it.value == '@' }.keys.first()
        moves.forEach { move ->
            //println("Move $move")
            attemptToMove(warehouse, robot, move)
            robot = Grid.mainNeighbors(robot).firstOrNull { warehouse[it] == '@' } ?: robot
            //println(toString(warehouse))
        }
        return warehouse.filter { it.value == 'O' }.keys.sumOf { it.y * 100 + it.x }
    }

    fun part2(input: List<String>): Number {
        val (warehouse, moves) = parse2(input)
        var robot = warehouse.filter { it.value == '@' }.keys.first()
        //println("Initial position")
        //print(toString(warehouse))
        moves.forEach { move ->
            // println("Move $move")
            if (move == Direction.NORTH || move == Direction.SOUTH) {
                try {
                    val newPositions = mutableSetOf<Pair<Coord, Coord>>()
                    attemptToMoveVertically(warehouse, robot, move, newPositions, false)
                    newPositions
                        .sortedBy { if (move == Direction.NORTH) it.second.y else -it.second.y }
                        .forEach {
                            warehouse[it.second] = warehouse[it.first]!!
                            warehouse[it.first] = '.'
                        }
                } catch (_: Error) {
                    // blocked
                }
            } else {
                attemptToMove(warehouse, robot, move)
            }
            robot = Grid.mainNeighbors(robot).firstOrNull { warehouse[it] == '@' } ?: robot
            // println(toString(warehouse))
        }
        //println(toString(warehouse))
        return warehouse.filter { it.value == '[' }.keys.sumOf { it.y * 100 + it.x }
    }

// test before attempt to solve
    check(part1(readInput("Day15_test_small")) == 2028)
    check(part1(readInput("Day15_test_big")) == 10092)

    //part2(readInput("Day15_test_p2_small"))

    check(part2(readInput("Day15_test_big")) == 9021)

// solve with real input
    solve("Day15", ::part1, ::part2)
}
