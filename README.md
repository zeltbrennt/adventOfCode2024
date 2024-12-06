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
