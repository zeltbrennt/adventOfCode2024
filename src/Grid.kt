data class Coord(val x: Int, val y: Int) {

    /*******************

    o --> +1
    |
    v
    +1

     *******************/
    fun north(): Coord {
        return copy(y = y - 1)
    }

    fun south(): Coord {
        return copy(y = y + 1)
    }

    fun east(): Coord {
        return copy(x = x + 1)
    }

    fun west(): Coord {
        return copy(x = x - 1)
    }

    fun northWest(): Coord {
        return copy(x = x - 1, y = y - 1)
    }

    fun northEast(): Coord {
        return copy(x = x + 1, y = y - 1)
    }

    fun southWest(): Coord {
        return copy(x = x - 1, y = y + 1)
    }

    fun southEast(): Coord {
        return copy(x = x + 1, y = y + 1)
    }

    fun neighbor(direction: Direction): Coord {
        return when (direction) {
            Direction.NORTH -> north()
            Direction.SOUTH -> south()
            Direction.EAST -> east()
            Direction.WEST -> west()
            Direction.NORTHEAST -> northEast()
            Direction.SOUTHEAST -> southEast()
            Direction.NORTHWEST -> northWest()
            Direction.SOUTHWEST -> southWest()
        }
    }
}

typealias Grid<T> = Map<Coord, T>

enum class Direction { NORTH, SOUTH, EAST, WEST, NORTHEAST, SOUTHEAST, NORTHWEST, SOUTHWEST }

val allDirections = listOf(
    Direction.NORTH,
    Direction.SOUTH,
    Direction.WEST,
    Direction.EAST,
    Direction.NORTHWEST,
    Direction.NORTHEAST,
    Direction.SOUTHWEST,
    Direction.SOUTHEAST,
)
