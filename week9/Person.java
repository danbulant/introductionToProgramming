public class Person {
    final String firstName;
    String lastName;
    int age;
    Person spouse;

    public Person(String first, String last) {
        this(first, last, 0);
    }
    public Person(String first, String last, int age) {
        this.firstName = first;
        this.lastName = last;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public int getAge() {
        return age;
    }
    public Person getSpouse() {
        return spouse;
    }
    public void setSpouse(Person spouse) {
        this.spouse = spouse;
    }

    public void birthday() {
        this.age += 1;
    }

    public boolean marry(Person that) {
        if(this.spouse != null || that.spouse != null) return false;
        if(this.age < 18 || that.age < 18) return false;
        if(this == that) return false;
        this.spouse = that;
        that.spouse = this;

        var lastName = String.format("%s-%s", this.lastName, that.lastName);
        this.lastName = lastName;
        that.lastName = lastName;
        return true;
    }

    public String toString()  {
        var isMarried = this.spouse == null ? "unmarried" : "married";
        return String.format("%s %s, %s, %s", this.firstName, this.lastName, this.age, isMarried);
    }

    public static void main(String[] args) {
        // check example toString
        assert (new Person("Lucky", "Luke", 23)).toString().equals("Lucky Luke, 23, unmarried");

        var nathan = new Person("Nathan", "Cole", 17);
        var emily = new Person("Emily", "Parker", 18);

        // not 18
        assert !nathan.marry(emily);
        assert !emily.marry(nathan);

        // is 18
        nathan.birthday();

        assert nathan.marry(emily);
        // marry must fail if married
        assert !nathan.marry(emily);
        assert !emily.marry(nathan);
        assert nathan.lastName.equals("Cole-Parker");
        assert emily.spouse == nathan;
        assert nathan.spouse == emily;
    }
}
