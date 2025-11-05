public class Employee extends Person {
    String jobTitle;
    int salary;

    public Employee(String name, int age, String jobTitle, int salary) {
        super(name, age);
        this.jobTitle = jobTitle;
        this.salary = salary;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public int getSalary() {
        return salary;
    }

    public int getBaseSalary() {
        return salary;
    }
}
