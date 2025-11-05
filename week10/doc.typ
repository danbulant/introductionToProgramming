#import "./common/common.typ" : *

#show: template

= Week 10

== Exercise 10.5

Write an interface to represent a spellchecker with a method `boolean isWord(String word)` that returns `true` if the given word is spelled correctly. Implement three classes for three different languages.

- Each language implementation should recognize at least three words.
- The spellchecker must correctly handle both uppercase and lowercase letters.
- Add a `main` method that takes two command-line arguments: a language name and a string. The program should split the string into words and print any misspelled words.

#embedClass(name: "Spellchecker", directory: "spellcheck", kind: "Interface")
#embedClass(name: "EnglishSpellchecker", directory: "spellcheck")
#embedClass(name: "CzechSpellchecker", directory: "spellcheck")
#embedClass(name: "JavaSpellchecker", directory: "spellcheck")
#embedClass(name: "SpellcheckerProgram", directory: "spellcheck")

== Exercise 10.6

Write an interface to represent a vehicle with a method `int getRemainingRange()` that returns the number of kilometers the vehicle can drive with its current fuel. Implement two classes: a gasoline car and a hybrid car.

- The gasoline car should store the amount of fuel left and its mileage (km per liter).
- The hybrid car should store both the amount of gasoline and electric energy left, along with the mileage for running on gasoline and electricity.
- The hybrid car's `getRemainingRange()` method should compute the total range by considering both gasoline and energy.
- Add an `int drive(int kms)` method that simulates driving the specified distance, depletes the fuel accordingly, and returns the actual number of kilometers driven. For the hybrid car, electricity is used before gasoline.
- Write a `main` method to test both vehicle implementations.

#embedClass(name: "Vehicle", directory: "cars", kind: "Interface")
#embedClass(name: "GasolineCar", directory: "cars")
#embedClass(name: "HybridCar", directory: "cars")

== Exercise 10.7

Write a class `Person` to represent a person with a name and an age. Create two subclasses: `Employee` (which extends `Person`) and `Manager` (which extends `Employee`).

- The `Person` class should have a constructor that takes a name and an age.
- The `Employee` class should add a job title and a salary, with an appropriate constructor.
- The `Manager` class should add a monthly bonus field, with an appropriate constructor.
- Add appropriate getter methods for all fields in each class.
- Add a `getSalary()` method. Make sure `Manager` class takes the manager's monthly bonus into account.
- Write a `main` method to test the inheritance hierarchy by creating instances of each class.

#embedClass(name: "Person", directory: "company")
#embedClass(name: "Employee", directory: "company")
#embedClass(name: "Manager", directory: "company")