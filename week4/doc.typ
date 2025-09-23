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

Input:

```
99 85 98
98 57 78
92 77 76
94 32 11
99 34 22
90 46 54
76 59 88
92 66 89
97 71 24
89 29 38
```
