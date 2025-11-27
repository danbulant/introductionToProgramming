#import "./common/common.typ" : *

#show: template

= Week 13

== Exercise 13.01

Explain - in your own words - what is an exception?

An object containing information about why a semi-hidden branching happened, usually used for error states. 
Thrown exceptions bubble up the call stack, as if a return was used, until there's a registered exception handler for a given code region, usually by try/catch.

== Exercise 13.05

For each of the following exceptions, mark whether it is a checked or unchecked:

 - `NullPointerException` - unchecked
 - `IOException` - checked
 - `IllegalArgumentException` - unchecked
 - `ArrayIndexOutOfBoundsException` - unchecked
 - `NumberFormatException` - unchecked
 - `ConcurrentModificationException` - unchecked
 - `InterruptedException` - checked

How many of these have you experienced?

Most of these, except the last two as I haven't written threaded programs in java yet.

== Exercise 13.07

Write a class to represent a gearbox with five gears and a gear for reverse. Add a method `changeGear(int gear)` to change the current gear. The method must `throw IllegalArgumentException` if the gear is not one of `-1`, `1`, `2`, `3`, `4`, and `5`. Here reverse is represented as `-1`. Write a `class IllegalGearChangeException`, which `extends RuntimeException`, and throw this exception:

a. when switching from any gear other than the first gear into reverse (and vice versa), and
b. when skipping one or more gears. For example, it is illegal to switch directly from the first gear to the third gear. It is also not allowed to switch directly from reverse to the fourth gear.

#embedClass(name: "Gearbox")

== Exercise 13.09

Write a class to represent a printer from hell. The class should have a single method `print()`. Whenever this method is called, the printer randomly throws one of the following exceptions: `OutOfPaperException`, `OutOfTonerException`, `PaperJamException`. Write classes for these exceptions. Write a main method, which calls `print()`, catches any exception, prompts the user to take action (e.g. "replace toner"), waits for confirmation from the user, and then calls `print()` again. Bonus points for infuriating or vaguely worded instructions.

#embedClass(name: "Printer")