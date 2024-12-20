fun main() {

    tailrec fun dfs(pos: Coord, steps: Int, maze: Map<Coord, Char>, dist: MutableMap<Coord, Int>) {
        dist[pos] = steps
        val next = when {
            maze[Grid.north(pos)] != '#' && Grid.north(pos) !in dist -> Grid.north(pos)
            maze[Grid.south(pos)] != '#' && Grid.south(pos) !in dist -> Grid.south(pos)
            maze[Grid.west(pos)] != '#' && Grid.west(pos) !in dist -> Grid.west(pos)
            maze[Grid.east(pos)] != '#' && Grid.east(pos) !in dist -> Grid.east(pos)
            else -> return
        }
        dfs(next, steps + 1, maze, dist)
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

    data class BFSNode(val pos: Coord, val dist: Int)

    fun bfs(pos: Coord, maze: Grid<Int>, maxDepth: Int, cheats: MutableMap<Pair<Coord, Coord>, Int>) {
        val visited = mutableSetOf(pos)
        val queue = ArrayDeque<BFSNode>()
        queue.addLast(BFSNode(pos, 0))
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            if (current.dist > maxDepth) continue
            if ((maze[current.pos] ?: 0) > maze[pos]!! + current.dist) {
                cheats[pos to current.pos] = maze[current.pos]!! - maze[pos]!! - current.dist
            }
            Grid.mainNeighbors(current.pos).filter { it !in visited }.forEach {
                visited.add(it)
                queue.add(BFSNode(it, current.dist + 1))
            }
        }
    }

    fun part1(input: List<String>): Number {
        val (maze, walls) = parse(input)
        val cheats = mutableMapOf<Pair<Coord, Coord>, Int>()

        maze.coords.forEach {
            bfs(it, maze, 2, cheats)
        }
        //cheats.values.groupingBy { it }.eachCount().toSortedMap().forEach(::println)
        return cheats.filter { it.value >= 100 }.count()
    }


    fun part2(input: List<String>): Number {
        val (maze, walls) = parse(input)
        val cheats = mutableMapOf<Pair<Coord, Coord>, Int>()

        maze.coords.forEach {
            bfs(it, maze, 20, cheats)
        }
        //cheats.values.filter { it >= 50 }.groupingBy { it }.eachCount().toSortedMap().forEach(::println)
        return cheats.filter { it.value >= 100 }.count()
    }

    // test before attempt to solve
    val testInput = readInput("Day20_test")
    //check(part1(testInput) == 0L)
    //check(part2(testInput) == 0L)

    // solve with real input
    solve("Day20", ::part1, ::part2)
}
