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

### Day 12: Garden Groups

Part 1 was easy enough with BFS. Part 2 was tricky, so many *edge cases* so one couldn't cut any *corners*...
In the end, found a reliable method to count the corners, since number of corners and numbers of edges must be the same.

### Day 13: Claw Contraption

I initaly solved part 1 with Dijkstra, but I had a hunch, this would bite me for part 2 and it did.
In the end, I solved the Linear Algebra problem, which was also much faster for part 1.

### Day 14: Restroom Redoubt

What a day, my favorite so far. Part 1 was easy enough, but for part 2 thinking outside the box was required.
That twist came truly unexpected.
In the end, it's anomaly detection in the variance of the x and y coordiantes of the robots.
Could have implemented a ANOVA, but in the end just checking against a relative difference above a reasonable cutoff was
enough.

### Day 15: Warehouse Woes

This was more tedious than hard, obvious solution did work, but was a little tricky to implement the larger boxes.

### Day 16: Reindeer Maze (*WIP*)

Part 1 was easy enough, just with a Priority Queue, have an idea for part 2, but I get confused all the time...

### Day 17: Chronospatial Computer (*WIP*)

Implementing the Intcode Computer was fun, but finding the (presumably very large) initial value for part 2 is not at
the moment.
Will probably search backwards again.

### Day 18: RAM Run

Another maze, thankfully not dynamic. Dijkstra to the resque. Brute force for part 2 was ok, but made it under half a
second by just checking if the path is still possible,
when a byte falls on the last shortest path.

### Day 19: Linen Layout

Again, starting from the end was enough to get the search space small. But for part 2, memoization was needed.
Would have been easy, if not for a sneaky off-by-one-error.

### Day 20: Race Condition

DFS to get the distance value for each tile was possible, since the original maze had only one path. `tailrec` allowed
me to write it as a recursive function without hitting stack overflow. For part 1 it was enough to test twice in each
direction for each position, if a shortcut is created. I reused the solution from part 2, since this is more general,
but runs really badly. To get all shortcuts, BFS from each position.

### Day 21: Keypad Conundrum (*WIP*)

Skipping this for now, have an idea witch BFS and caches, but I don't have time at the moment.

### Day 22: Monkey Market

Part 1 was straight forward, for part 2 I just brute forced it, after realizing, that I need to check for sequences that
actually occur. Another approach must be more effective, but that gets the solution for now. Runs for about 12 Minutes,
haha!

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
