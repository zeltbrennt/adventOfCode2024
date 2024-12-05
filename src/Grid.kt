data class Coord(val x: Int, val y: Int)


class Grid<T>(private val data: Map<Coord, T>) {

    /*******************

    o --> x+1
    |
    v
    y+1

     *******************/

    companion object {

        fun north(origin: Coord) = origin.copy(y = origin.y - 1)
        fun south(origin: Coord) = origin.copy(y = origin.y + 1)
        fun east(origin: Coord) = origin.copy(x = origin.x + 1)
        fun west(origin: Coord) = origin.copy(x = origin.x - 1)
        fun northEast(origin: Coord) = origin.copy(x = origin.x + 1, y = origin.y - 1)
        fun northWest(origin: Coord) = origin.copy(x = origin.x - 1, y = origin.y - 1)
        fun southEast(origin: Coord) = origin.copy(x = origin.x + 1, y = origin.y + 1)
        fun southWest(origin: Coord) = origin.copy(x = origin.x - 1, y = origin.y + 1)
        fun neighbor(origin: Coord, direction: Direction): Coord {
            return when (direction) {
                Direction.NORTH -> north(origin)
                Direction.SOUTH -> south(origin)
                Direction.EAST -> east(origin)
                Direction.WEST -> west(origin)
                Direction.NORTHEAST -> northEast(origin)
                Direction.NORTHWEST -> northWest(origin)
                Direction.SOUTHEAST -> southEast(origin)
                Direction.SOUTHWEST -> southWest(origin)
            }
        }

        val allDirections
            get() = listOf(
                Direction.NORTH,
                Direction.SOUTH,
                Direction.WEST,
                Direction.EAST,
                Direction.NORTHWEST,
                Direction.NORTHEAST,
                Direction.SOUTHWEST,
                Direction.SOUTHEAST,
            )

        val mainDirections
            get() = listOf(
                Direction.NORTH,
                Direction.SOUTH,
                Direction.WEST,
                Direction.EAST,
            )
    }

    val coords get() = data.keys
    operator fun get(coord: Coord) = data[coord]

}

enum class Direction { NORTH, SOUTH, EAST, WEST, NORTHEAST, SOUTHEAST, NORTHWEST, SOUTHWEST }


