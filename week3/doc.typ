#import "./common/common.typ" : *

#show: template

= Week 3

== Exercise 1.3.5

Write a program `RollLoadedDie` that prints the result of rolling
a loaded die such that the probability of getting a $1$, $2$, $3$, $4$,
or $5$ is $1/8$ and the probability of getting a $6$ is $3/8$.

#embedClass(name: "RollLoadedDie")

== Exercise 1.3.24

Write a program `GamblerPlot` that traces a gambler's ruin simulation
by printing a line after each bet in which one asterisk corresponds
to each dollar held by the gambler.

#embedClass(name: "GamblerPlot")

== Exercise 1.3.25

Modify `Gambler` to take an extra command-line argument that specifies
the (fixed) probability that the gambler wins each bet. Use your program
to try to learn how this probability affects the chance of winning and the
expected number of bets. Try a value of $p$ close to $0.5$ (say, $0.48$).

#embedClass(name: "Gambler")