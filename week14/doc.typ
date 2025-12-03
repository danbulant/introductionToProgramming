#import "./common/common.typ" : *

#show: template

= Week 14

Write a graphical user interface for a simple calculator application.

Here are some steps to get started:

- Add a label to show the currently entered number.
- Use horizontal and vertical boxes, or a grid, to construct a layout of buttons numbered from 0 to 9.
- Add buttons for addition, subtraction, multiplication, and division.

The calculator should work as follows: You press “5”, it shows up in the display, then you press “+”, and then you press “7” and it becomes “12”.

Hint: Experiment with the calculator on your system to discover how it works.

Here are some optional extensions to consider:

- Style the label to make the result bigger.
- Style the digit buttons so they are bigger.
- Add an event handler such that user can press the keys 0 to 9 on the keyboard with the same effect as using the buttons.
- Add a try-catch block to detect division by zero and show an appropriate alert message.
- Add an event handler such that the escape key resets the calculator.

#embedClass(name: "Calculator")