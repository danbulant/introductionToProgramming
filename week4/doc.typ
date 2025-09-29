#import "./common/common.typ" : *

#show: template

= Week 4

== Exercise 1.4.10

Write a program `Deal` that takes an integer command-line argument `n` and
prints `n` poker hands (five cards each) from a shuffled deck, separated by blank lines.

A second class Card (@card) is used for visuals.

Example run for 2 hands (`java Deal.java 2`):

```
J   J K   K ⒑♥ ♥⒑ 9♦ ♦9 7♠ ♠7 
♥   ♥ ♠   ♠   ♥♥♥   ♦♦♦   ♠♠♠  
♥   ♥ ♠   ♠   ♥♥♥   ♦ ♦        
J   J K   K ⒑♥ ♥⒑ 9♦ ♦9 7♠ ♠7 

3 ♥ 3 4♠ ♠4 4♦ ♦4 7♦ ♦7 6♦ ♦6 
  ♥                ♦♦♦   ♦ ♦  
                              
3 ♥ 3 4♠ ♠4 4♦ ♦4 7♦ ♦7 6♦ ♦6 
```

#embedClass(name: "Deal")

#embedClass(name: "Card", label: <card>)

== Exercise 1.4.14

Write a code fragment to print the transposition (rows and columns exchanged) of a square two-dimensional array.

#place(
  right,
  figure(
    table(
      columns: 2,
      [Example input],
      [Output],
      ```
      99 85 98
      98 57 78
      92 77 76
      ```,
      ```
      99 98 92
      85 57 77
      98 78 76
      ```
    ),
    caption: [Example input and output matrix]
  )
)

#embedClass(name: "Transpose")