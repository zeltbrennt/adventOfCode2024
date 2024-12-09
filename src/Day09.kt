fun main() {

    fun parse(input: List<String>): MutableList<Int?> {
        val disc = mutableListOf<Int?>()
        input[0].mapIndexed { i, x ->
            if (i % 2 == 0) {
                repeat(x.digitToInt()) {
                    disc.add(i / 2)
                }
            } else {
                repeat(x.digitToInt()) {
                    disc.add(null)
                }
            }

        }
        return disc
    }

    fun part1(input: List<String>): Number {

        val disc = parse(input)
        var nextSpot = 0
        var nextBlock = disc.lastIndex
        do {
            while (disc[nextSpot] != null) {
                nextSpot++
            }
            while (disc[nextBlock] == null) {
                nextBlock--
            }
            disc[nextSpot] = disc[nextBlock]
            disc[nextBlock] = null
            nextSpot++
            nextBlock--
        } while (nextSpot < nextBlock)
        return disc.mapIndexed { i, x -> x?.toLong()?.times(i) ?: 0  }.sum()
    }


    data class Block(val id: Int? = null, var size: Int, var position: Int) {
        override fun toString() = "${id ?: '.'}".repeat(size)
    }

    fun part2(input: List<String>): Number {

        val files = mutableListOf<Block>()
        val spaces = mutableListOf<Block>()
        var pos = 0
        input[0].mapIndexed { i, x ->
            if (i % 2 == 0) {
                files.add(Block(id = i / 2, size = x.digitToInt(), position =  pos))
            } else {
                spaces.add(Block(size = x.digitToInt(), position = pos))
            }
            pos += x.digitToInt()

        }

        for (file in files.reversed()) {
            try {
                val space = spaces.first { it.size >= file.size && it.position < file.position }
                file.position = space.position
                space.size -= file.size
                space.position += file.size
            } catch (e: NoSuchElementException) {
                continue
            }
        }
        return files
            .flatMap { file ->
                (file.position..<file.position + file.size).map { it * file.id!! }
            }.sumOf { it.toLong() }
    }

    // test before attempt to solve
    val testInput = readInput("Day09_test")

    check(part1(testInput) == 1928L)
    check(part2(testInput) == 2858L)

    // 6398177437733 too high
    // solve with real input
    solve("Day09", ::part1, ::part2)
}
