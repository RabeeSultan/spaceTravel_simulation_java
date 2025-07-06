
// İsim: Rabee Sultan
// Öğrenci No: b121210080
// Ödev: Uzay Simülasyonu
package firstProgram;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Planet {
    private String name;
    private int hoursInDay;
    private LocalDate date;
    private int hour;
    private int population;
    private List<Person> people;

    public Planet(String name, int hoursInDay, LocalDate date) {
        this.name = name;
        this.hoursInDay = hoursInDay;
        this.date = date;
        this.hour = 0;
        this.population = 0;
        this.people = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getHoursInDay() {
        return hoursInDay;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getHour() {
        return hour;
    }

    public int getPopulation() 
    {
        return population;
        

    }

    public void addPerson(Person person) 
    {
        people.add(person);
        population++;
    }

    public List<Person> getPeople() {
        return people;
        
    }
    public void removePerson(Person person) {
        people.remove(person); // 
        population--;
    }

    public void advanceOneHour() {
        hour++;
        if (hour == hoursInDay) {
            hour = 0;
            date = date.plusDays(1);
        }
    }
    public void addPopulation(int number) {
        this.population += number;
    }

    @Override
    public String toString() {
        return name + " (تاريخ: " + date + " - الساعة: " + hour +
               " - ساعات اليوم: " + hoursInDay +
               " - عدد السكان: " + population + ")";
    }
}