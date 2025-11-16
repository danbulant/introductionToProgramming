#import "./common/common.typ" : *

#show: template

= Week 12

== Exercise 12.07

Write a class `Pair` with two type parameters `A` and `B` to represent an immutable pair of values (i.e. the class should have two `final` fields of type `A` and `B`).

- Add an appropriate constructor and getter methods.
  - Do not add any setters, as the class should be immutable.
- Add a method `swap` to the `Pair` class. The `swap` method should return a *new* pair where the first component becomes the second component and vice versa. For example, for the pair `(true, 42)` the method should return `(42, true)`.
- Add methods `withFst` and `withSnd` to the Pair class. Each method should take a type parameter `C` and return a new pair where the appropriate component has been updated. For example, calling `withFst` with the integer `42` on the pair `(true, "Hello World")` should return `(42, "Hello World")`.

== Exercise 12.08

Write a class `Dict` that takes two type parameters `K` and `V` to represent a dictionary, i.e. a mapping from items of type `K` to items of type `V`. Internally, the dictionary should maintain a single array of pairs of type `Pair<K, V>`. Add the following methods:

- `V get(K key)` returns the value associated with the given key, or null if the key is not found.
- `void put(K key, V value)` updates the dictionary with a mapping from the key to the value. If the key already exists, its value is updated. Otherwise, a new pair is added.

You may assume the `Dict` can contain at most 100 entries.

== Exercise 12.16

Write a class, which takes one type parameter `E`, to represent a multiset. A multiset is a set that counts how many times it contains each of its elements. Add the following methods:

- `int count(E e)` returns the number of times the element `e` occurs in the multiset.
- `void add(E e)` adds the element `e` to the multiset. (Adding increments its count.)
- `void remove(E e)` removes the element `e` from the multiset. (Removing decrements its count.)
- `int size()` returns the number of different elements in the set (non-duplicate count).

An element can never occur a negative number of times in a multiset.

_Hint: Use an internal map of type `Map<E, Integer>`._

