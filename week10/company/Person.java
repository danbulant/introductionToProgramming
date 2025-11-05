public class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public static void main(String[] args) {
        var person = new Person("P1", 20);
        var employee = new Employee("P2", 40, "Senior OOP Architect", 100_000);
        var manager = new Manager("P3", 40, "Product Manager", 130_000, 20_000);

        assert person.getAge() == person.age;
        assert employee.getAge() == employee.age;
        assert manager.getBaseSalary() == manager.salary;
        assert manager.getSalary() > manager.getBaseSalary();
        assert employee.getBaseSalary() == employee.getSalary();
        assert manager.getJobTitle().equals("Product Manager");
    }
}
