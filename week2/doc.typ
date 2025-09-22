#import "./common/common.typ" : *

#show: template

= Week 2

== Exercise 1.2.25

_Wind chill_. Given the temperature $T$ (in degrees Fahrenheit)
and the wind speed v (in miles per hour), the National Weather Service
defines the effective temperature (the wind chill) as follows:

$w = 35.74 + 0.6215 T + (0.4275 T - 35.75) v^0.16$

Write a program that takes two double command-line arguments temperature
and velocity and prints the wind chill. Use ```java Math.pow(a, b)``` to
compute $a^b$. Note:
The formula is not valid if $T$ is larger than $50$ in absolute value or
if v is larger than $120$ or less than $3$ (you may assume that the values
you get are in that range).

#embedClass(name: "WindChill")

== Exercise 1.2.30

_Uniform random numbers_. Write a program that prints five uniform random
numbers between $0$ and $1$, their average value, and their minimum and
maximum values. Use ```java Math.random()```, ```java Math.min()```, and
```java Math.max()```.

#embedClass(name: "RandomValues")