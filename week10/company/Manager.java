public class Manager extends Employee {
    int monthlyBonus;

    public Manager(String name, int age, String jobTitle, int salary, int monthlyBonus) {
        super(name, age, jobTitle, salary);
        this.monthlyBonus = monthlyBonus;
    }

    public int getMonthlyBonus() {
        return monthlyBonus;
    }

    public int getSalary() {
        return salary + monthlyBonus;
    }
}
