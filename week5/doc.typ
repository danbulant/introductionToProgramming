#import "./common/common.typ" : *

#show: template

= Week 5

== Exercise 1.5.7

Write a program that takes an integer command-line argument $n$, reads in
$n-1$ distinct integers between 1 and $n$, and determines the missing value.

#embedClass(name: "MissingInt")

== Exercise 1.5.19

Write a program that takes as command-line arguments an integer $n$ and
a floating-point number $p$ (between $0$ and $1$), plots $n$ equally spaced points on the
circumference of a circle, and then, with probability $p$ for each pair of points, draws
a gray line connecting them.

#embedClass(name: "CircleLines")

== Exercise 1.5.26

Write a program Circles that draws filled circles of random radii at random
positions in the unit square, producing images like those below. Your program
should take four command-line arguments: the number of circles, the probability
that each circle is black, the minimum radius, and the maximum radius.

#embedClass(name: "Circles")