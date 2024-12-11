# adventOfCode2024

Welcome to the Advent of Code[^aoc] Kotlin project created by [zeltbrennt][github] using
the [Advent of Code Kotlin Template][template] delivered by JetBrains.

In this repository, zeltbrennt is about to provide solutions for the puzzles using [Kotlin][kotlin] language.

If you're stuck with Kotlin-specific questions or anything related to this template, check out the following resources:

- [Kotlin docs][docs]
- [Kotlin Slack][slack]
- Template [issue tracker][issues]

## Solutions

### Day 01: Historian Hysteria

Split the input into two lists and then abuse Kotlins convenient Collection functions :)

### Day 02: Red-Nosed Reports

Another easy day thanks to `windowed`, `any` and `all`. Brute forced part 2 with `filter` ond `indices`.

### Day 03: Mull It Over

Regex! The `.+?` pattern was key. Without, everything between the first "do" and the last "don't" would be removed.

### Day 04: Ceres Search

Reimplement the grid again. Use OOP to have more expressive code.

### Day 05: Print Queue

The text hints about sorting all the time, so just build a comparator from the instructions.

### Day 06: Guard Gallivant

Good thing, I had a grid implementation ready, solved Part 1 quite fast. Part 2 is brute force again, but with the use
of coroutines, only checking coordinates from the original path and reducing memory access by only saving the turning
points, I got the runtime down from 12s to 300ms :)

### Day 07: Bridge Repair

Came up with a recursive solution that solves Part 2 in 1.5 Seconds. Did not manage to get Coroutines working,
memoization was also not helping. The key was to not manipulate the list, instead increasing a pointer... without
mutable data,
the execution time went town to just half a second.
The final big improvement came from hint to reverse the operations. This way, the tree was pruning much faster. Down to
below 20ms.

### Day 08: Resonant Collinearity

Again made use of the Grid-Class and extended a little Grid-Vector functionality. Then just a little Vector Math..

### Day 09: Disk Fragmenter

First time much pain, I still don't know what went wrong with my initial solution.
Now just update the positon of files, size and start of empty space and done.
Interestingly, I didn't need to update the space left behind by the files, because there will never be a file inserted
into this space. Breaks the visualisation, but the solution is correct.

### Day 10: Hoof It

Simple recursive DFS, accidentally solved part 2 before part 1, lol

### Day 11: Plutonian Pebbles 

Main insights: We do not care about the values on the stones. It's a binary tree.
So we just need to know if a stone has one or two children.
Use recursive DFS to find the last node, and bubble up the answer. 
For Part two, a cache was paramount, brought the runtime down from literally years to just Milliseconds.

[^aoc]:
[Advent of Code][aoc] – An annual event of Christmas-oriented programming challenges started December 2015.
Every year since then, beginning on the first day of December, a programming puzzle is published every day for
twenty-five days.
You can solve the puzzle and provide an answer using the language of your choice.

[aoc]: https://adventofcode.com

[docs]: https://kotlinlang.org/docs/home.html

[github]: https://github.com/zeltbrennt

[issues]: https://github.com/kotlin-hands-on/advent-of-code-kotlin-template/issues

[kotlin]: https://kotlinlang.org

[slack]: https://surveys.jetbrains.com/s3/kotlin-slack-sign-up

[template]: https://github.com/kotlin-hands-on/advent-of-code-kotlin-template
