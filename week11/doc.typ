#import "./common/common.typ" : *

#show: template

= Week 10

== Exercise 1.3.45

_Chaos_. Write a program to study the following simple model for population growth, which might be applied to study fish in a pond, bacteria in a test tube,
or any of a host of similar situations. We suppose that the population ranges from
$0$ (extinct) to $1$ (maximum population that can be sustained). If the population at
time $t$ is $x$, then we suppose the population at time $t + 1$ to be $r x (1-x)$, where the
argument r, known as the _fecundity parameter_, controls the rate of growth. Start
with a small population—say, $x = 0.01$—and study the result of iterating the model, for various values of $r$. For which values of $r$ does the population stabilize at
$x = 1 - 1/r$ ? Can you say anything about the population when $r$ is $3.5$? $3.8$? $5$?

#table(
  columns: 3,
  $3.5$,
  $3.8$,
  $5$,
  $2$,
  [Oscillation between 4 values],
  [Sustained chaos],
  [Dies from overpopulation in 5 ticks],
  [Sustained $1-1/r$]
)

== Exercise 1.4.26

_Music shuffling_. You set your music player to shuffle mode. It plays each of
the n songs before repeating any. Write a program to estimate the likelihood that
you will not hear any sequential pair of songs (that is, song 3 does not follow song
2, song 10 does not follow song 9, and so on).

== Exercise 1.5.32

_Clock_. Write a program that displays an animation of the second, minute,
and hour hands of an analog clock. Use the method `StdDraw.pause(1000)` to
update the display roughly once per second.
