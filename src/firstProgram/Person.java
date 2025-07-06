package firstProgram;
public class Person {
    private String name;
    private int age;
    private int remainingLife;
    private String assignedSpacecraft;

    public Person(String name, int age, int remainingLife, String assignedSpacecraft) {
        this.name = name;
        this.age = age;
        this.remainingLife = remainingLife;
        this.assignedSpacecraft = assignedSpacecraft;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public int getRemainingLife() { return remainingLife; }
    public String getAssignedSpacecraft() { return assignedSpacecraft; }

    public void decreaseLife() {
        if (remainingLife > 0) {
            remainingLife--;
           // System.out.println(remainingLife);
        }
    }
}