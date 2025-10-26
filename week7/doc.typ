#import "@preview/wrap-it:0.1.1": wrap-content
#import "./common/common.typ" : *

#show: template

= Week 7

== Exercise 2.3.22

_Recursive squares._ Write a program to produce each of the following recursive
patterns. The ratio of the sizes of the squares is $2.2:1$. To draw a shaded square,
draw a filled gray square, then an unfilled black square.

#image("squares.png")

#embedClass(name: "Squares")

== Exercise 2.3.27

_Sierpinski triangles._ Write a recursive program to draw Sierpinski triangles (see PROGRAM 2.2.3).
As with Htree, use a command-line argument to control the depth of the recursion.

#embedClass(name: "Triangles")

== Exercise 2.3.31

_Plasma clouds._ Write a recursive program to draw plasma clouds, using the
method suggested in the text.

#embedClass(name: "PlasmaClouds")