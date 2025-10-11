#import "./common/common.typ" : *

#show: template

= Week 6

== Exercise 2.1.19

Write a static method `histogram()` that takes an `int array a[]` and an
integer $m$ as arguments and returns an array of length $m$ whose $i$th element is the
number of times the integer $i$ appeared in `a[]`. Assuming the values in `a[]` are
all between $0$ and $m-1$, the sum of the values in the returned array should equal
`a.length`.

#embedClass(name: "Histogram")

== Exercise 2.1.30

_Calendar_. Write a program `Calendar` that takes two integer commandline
arguments $m$ and $y$ and prints the monthly calendar for month $m$ of year $y$, as
in this example:

```
% java Calendar 2 2009
February 2009
 S  M Tu  W Th  F  S
 1  2  3  4  5  6  7
 8  9 10 11 12 13 14
15 16 17 18 19 20 21
22 23 24 25 26 27 28
```

#embedClass(name: "Calendar")

== Exercise 2.2.26

_Poker analysis_. Write a `StdRandom` and `StdStats` client (with appropriate
static methods of its own) to estimate the probabilities of getting one pair, two pair,
three of a kind, a full house, and a flush in a five-card poker hand via simulation.

Divide your program into appropriate static methods and defend your design decisions.
_Extra credit_ : Add straight and straight flush to the list of possibilities.

#embedClass(name: "PokerAnalysis")

#embedClass(name: "Hand")

_Note that this reuses #context {
  let label = <card>;
  if query(label).len() == 0 {
    [ class Card ]
  } else {
    ref(label)
  }
} from week 4._
