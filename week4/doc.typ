#import "./common/common.typ" : *

#show: template

= Week 4

== Exercise 1.4.10

Write a program `Deal` that takes an integer command-line argument `n` and
prints `n` poker hands (five cards each) from a shuffled deck, separated by blank lines.

#embedClass(name: "Deal")

#embedClass(name: "Card")

== Exercise 1.4.14

Write a code fragment to print the transposition (rows and columns exchanged) of a square two-dimensional array.
For the example spreadsheet array in the text, you code would print the following:

```
99 98 92 94 99 90 76 92 97 89
85 57 77 32 34 46 59 66 71 29
98 78 76 11 22 54 88 89 24 38
```
