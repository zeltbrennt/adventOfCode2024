fun main() {

    tailrec fun dfs(pos: Coord, steps: Int, maze: Map<Coord, Char>, dist: MutableMap<Coord, Int>) {
        dist[pos] = steps
        when {
            maze[Grid.north(pos)] != '#' && Grid.north(pos) !in dist -> dfs(Grid.north(pos), steps + 1, maze, dist)
            maze[Grid.south(pos)] != '#' && Grid.south(pos) !in dist -> dfs(Grid.south(pos), steps + 1, maze, dist)
            maze[Grid.west(pos)] != '#' && Grid.west(pos) !in dist -> dfs(Grid.west(pos), steps + 1, maze, dist)
            maze[Grid.east(pos)] != '#' && Grid.east(pos) !in dist -> dfs(Grid.east(pos), steps + 1, maze, dist)
        }
    }

    fun parse(input: List<String>): Pair<Grid<Int>, Set<Coord>> {
        val tempMaze = buildMap {
            input.forEachIndexed { y, row ->
                row.forEachIndexed { x, char ->
                    this[Coord(x, y)] = char
                }
            }
        }
        val start = tempMaze.filter { it.value == 'S' }.keys.first()
        val distMap = mutableMapOf<Coord, Int>()
        dfs(start, 0, tempMaze, distMap)
        return Grid(distMap) to tempMaze.filter { it.value == '#' }.keys
    }

    fun part1(input: List<String>): Number {
        val (maze, walls) = parse(input)
        val cheats = mutableMapOf<Pair<Coord, Coord>, Int>()

        maze.data.forEach { pos, distance ->
            var shortCutEntry = Grid.north(pos)
            var shorCutExit = Grid.north(shortCutEntry)
            if ((maze[shorCutExit] ?: 0) > distance + 2) cheats[shortCutEntry to shorCutExit] =
                maze[shorCutExit]!! - 2 - distance
            shortCutEntry = Grid.south(pos)
            shorCutExit = Grid.south(shortCutEntry)
            if ((maze[shorCutExit] ?: 0) > distance + 2) cheats[shortCutEntry to shorCutExit] =
                maze[shorCutExit]!! - 2 - distance
            shortCutEntry = Grid.east(pos)
            shorCutExit = Grid.east(shortCutEntry)
            if ((maze[shorCutExit] ?: 0) > distance + 2) cheats[shortCutEntry to shorCutExit] =
                maze[shorCutExit]!! - 2 - distance
            shortCutEntry = Grid.west(pos)
            shorCutExit = Grid.west(shortCutEntry)
            if ((maze[shorCutExit] ?: 0) > distance + 2) cheats[shortCutEntry to shorCutExit] =
                maze[shorCutExit]!! - 2 - distance
        }

        //cheats.values.groupingBy { it }.eachCount().toSortedMap().forEach(::println)
        return cheats.filter { it.value >= 100 }.count()
    }

    fun part2(input: List<String>): Number {
        return 0
    }

    // test before attempt to solve
    val testInput = readInput("Day20_test")
    //check(part1(testInput) == 0L)
    //check(part2(testInput) == 0L)

    // solve with real input
    solve("Day20", ::part1, ::part2)
}
