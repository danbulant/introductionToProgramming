#import "@preview/showybox:2.0.4": showybox
#import "./common/common.typ" : *

#show: template

= Week 9

== Exercise 3.1.26

Find a website that publishes the current temperature in your area, and
write a screen-scraper program Weather so that typing java Weather followed by
your ZIP code will give you a weather forecast.

- You can use: https://www.flotvejr.dk/%C3%A5rhus/observations
- You may use city names instead of zip code
- Note: Do not overcommit; we are expecting something simple.

#embedClass(name: "Weather")

Note: Other used classes omited for clarity in PDF form, see ZIP or git source. Includes a simple HTML parser and tag-name/classes selector queries.

Example output:

#[
#show raw.where(block: true): set par(leading: 2pt)
```
╭────────────┬─────────────────────────────┬────────────╮
│ 5.8°       │ Oedum                       │ 1 m/s      │
│            │ 91 mins ago  - 16 km away   │            │
├────────────┼─────────────────────────────┼────────────┤
│ 9.1°       │ Sletterhage Fyr             │ 7 m/s      │
│            │ 91 mins ago  - 20 km away   │            │
├────────────┼─────────────────────────────┼────────────┤
│ 4.7°       │ Tirstrup                    │ 2 m/s      │
│            │ 31 mins ago  - 30 km away   │            │
├────────────┼─────────────────────────────┼────────────┤
│ 4.7°       │ Horsens/Bygholm             │ 3 m/s      │
│            │ 91 mins ago  - 42 km away   │            │
├────────────┼─────────────────────────────┼────────────┤
│ 5.0°       │ Hald V                      │ 2 m/s      │
│            │ 91 mins ago  - 46 km away   │            │
├────────────┼─────────────────────────────┼────────────┤
│ 9.8°       │ Roesnaes                    │ 9 m/s      │
│            │ 91 mins ago  - 61 km away   │            │
├────────────┼─────────────────────────────┼────────────┤
│ 4.9°       │ Isenvad                     │ 2 m/s      │
│            │ 91 mins ago  - 64 km away   │            │
├────────────┼─────────────────────────────┼────────────┤
│ 9.2°       │ Gniben                      │ 9 m/s      │
│            │ 91 mins ago  - 69 km away   │            │
├────────────┼─────────────────────────────┼────────────┤
│ 5.0°       │ Karup                       │ 2 m/s      │
│            │ 31 mins ago  - 69 km away   │            │
├────────────┼─────────────────────────────┼────────────┤
│ 7.1°       │ Odense / Beldringe          │ 3 m/s      │
│            │ 31 mins ago  - 76 km away   │            │
├────────────┼─────────────────────────────┼────────────┤
│ 4.8°       │ Aars Syd                    │ 0 m/s      │
│            │ 91 mins ago  - 79 km away   │            │
├────────────┼─────────────────────────────┼────────────┤
│ 5.6°       │ Billund Lufthavn            │ 1 m/s      │
│            │ 41 mins ago  - 81 km away   │            │
├────────────┼─────────────────────────────┼────────────┤
│ 8.6°       │ Aarslev                     │ 3 m/s      │
│            │ 91 mins ago  - 95 km away   │            │
├────────────┼─────────────────────────────┼────────────┤
│ 6.4°       │ Vamdrup                     │ 3 m/s      │
│            │ 31 mins ago  - 98 km away   │            │
├────────────┼─────────────────────────────┼────────────┤
│ 6.5°       │ Mejrup                      │ 2 m/s      │
│            │ 91 mins ago  - 99 km away   │            │
├────────────┼─────────────────────────────┼────────────┤
│ 8.1°       │ Borris                      │ 2 m/s      │
│            │ 91 mins ago  - 100 km away  │            │
├────────────┼─────────────────────────────┼────────────┤
│ 7.8°       │ Askov                       │ 2 m/s      │
│            │ 91 mins ago  - 103 km away  │            │
├────────────┼─────────────────────────────┼────────────┤
│ 9.8°       │ Assens/Toroe                │ 4 m/s      │
│            │ 91 mins ago  - 103 km away  │            │
├────────────┼─────────────────────────────┼────────────┤
│ 8.7°       │ Holbaek                     │ 5 m/s      │
│            │ 91 mins ago  - 106 km away  │            │
├────────────┼─────────────────────────────┼────────────┤
│ 5.4°       │ Aalborg                     │ 3 m/s      │
│            │ 31 mins ago  - 107 km away  │            │
╰────────────┴─────────────────────────────┴────────────╯
```
]

== Exercise 9.1

Write a class `Person` to represent a person. The class should have the following fields: `String firstName`, `String lastName`, `int age`, and `Person spouse`. The `spouse` field is initially `null`.

Add two constructors:

- `Person(String first, String last)`
- `Person(String first, String last, int age)`

Add getters and setters:

- Add getters for all fields.
- Add setters, but only for lastName and spouse.

Add a method `void birthday()` which increases the persons age by one year.

Add a method `boolean marry(Person that)` which marries this person to that person by updating the `spouse` fields and joining their last names. For example: If Nathan Cole and Emily Parker are married they become Nathan Cole-Parker and Emily Cole-Parker.

- A person cannot be married until they are 18 years old.
- A person cannot be married if they are already married.

The method should return `true` if the marriage is successful.

Add a `toString` method that returns a `String` of the form `Lucky Luke, 23, unmarried`.

Add a `main` method to test your implementation.

#embedClass(name: "Person")

== Exercise 9.6

A student was asked to solve the following exercise:

#showybox([
    Write a class `ShoppingCart` to represent a shopping cart in an online store. The class should store the cart owner's name, an array of item names, an array of item prices, and track how many items are in the cart. Include a constructor, methods to add items, remove items, calculate the total price, and apply a discount.
])

The student wrote the following code:

```java
class cart {
    public String n; // name, maybe unused?
    protected double total; //total price of items
    public String[] items; // this is the items

    public void add(String item, double p) {
        items[count] = item; prices[count] = p; count++;
        total = total + p;
        return;
    }

    // count how many items are in the shopping cart
    int size;

    cart(String n, int size) {
        n = n;
        this.size = this.size;
        //initialise the fields of the class.
        items = new String[100];
        prices = new double[100];
        items = new String[100];
    }

    // Getter method
    public double getTotal() { return total; }

    // count how many items are in the shopping bag
    public static int count = 0; // counter variable
    private double[] prices; //stores the cost

    public void remove(String x) {
        for(int i=0;i<count;i++){
    if(items[i].equals(x)){
        total=total-prices[i];
                for(int j=i;j<count-1;j++){
            items[j]=items[j+1];
            prices[j]=prices[j+1];
        }
                 count--;
        return;
            }}
    }

    public String discount(   double persent) {
        if (persent > 0) {
            total = (double) ((double) total - ((double) total * (double) persent));
            } else {
                total = total - (total * persent);
        } return null; }}
```

- What do you think of the code style?

    Meaningless indentation and (lack of) use of whitespace makes the code hard to follow. Indescriptive naming of variables (`n`, `total`) may be confusing to users of this class (and later to anyone reading it), especially since the comments aren't written in such a way to show on hover on relevant properties (Javadoc). Ordering of methods and properties hides their relationship and makes it harder to skim through the class. Total being calculated when items are added or when discount is applied makes operations order sensitive (applying discount before adding items results in no effect). Not using a subclass/object for linking names/prices of items makes cart manipulation prone to errors, but that's part of the exercise, similar to using arrays instead of vectors/arraylist.

    Persent, if read as percent, may imply the function takes a percentage, but it expects a 'direct' value (90% = 0.90). Some fields being public may allow (accidental) logic errors from class users (e.g. desynchronized items and prices).

- Do you find the comments helpful?

    Better naming of variables would result in less comments needed. Some are self describing and thus only add to the visual noise.

- Refactor the code such that it follows best practices.

#embedClass(name: "ShoppingCart")
