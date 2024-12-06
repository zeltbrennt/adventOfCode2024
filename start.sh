#!/bin/bash

year=$1
day=$(printf '%02d' $2)

if [[ ! -e src/input/Day$day.txt ]]; then
  echo "getting input..."
  curl -s https://adventofcode.com/$year/day/$2/input -b "session=$AOC_COOKIE" -o src/input/Day$day.txt
fi

if [[ ! -e src/Day$day.kt ]]; then
  echo "creating stub..."
  sed "s/00/$day/g" src/Template.kt > src/Day$day.kt
fi

touch src/input/Day${day}_test.txt

git add src/Day$day.kt
idea64 src/Day$day.kt
idea64 src/input/Day${day}_test.txt
