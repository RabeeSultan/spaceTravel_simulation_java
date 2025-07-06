// İsim: Rabee Sultan
// Öğrenci No: b121210080
// Ödev: Uzay Simülasyonu
package firstProgram;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Spacecraft {
    private String name;
    private String departurePlanet;
    private String arrivalPlanet;
    private LocalDate departureDate;
    private int travelTime;
    private int remainingTime;
    private String status;
    private List<Person> passengers;
    private LocalDate arrivalDate;

    public Spacecraft(String name, String departurePlanet, String arrivalPlanet, LocalDate departureDate, int travelTime) {
        this.name = name; //اسم المركبة
        this.departurePlanet = departurePlanet; //اسم الكوكب المغادر منه
        this.arrivalPlanet = arrivalPlanet; //المركبة الهدف
        this.departureDate = departureDate; //تاريخ الرحلة
        this.travelTime = travelTime; // الوقت المستغرق للوصول بالساعات
        this.remainingTime = travelTime;// الوقت المستغرق للوصول بالساعات
        this.status = "Waiting"; //حالة الرحلة
        this.passengers = new ArrayList<>();
    }

    public String getName() { return name; }
    public String getDeparturePlanet() { return departurePlanet; }
    public String getArrivalPlanet() { return arrivalPlanet; }
    public LocalDate getDepartureDate() { return departureDate; }
    public int getRemainingTime() { return remainingTime; }
    public String getStatus() { return status; }
    public List<Person> getPassengers() { return passengers; }
    public LocalDate getArrivalDate() { return arrivalDate; }

    public void setStatus(String newStatus) { this.status = newStatus; }
    public void setArrivalDate(LocalDate arrivalDate) { this.arrivalDate = arrivalDate; }
    public int getTravelTime() {return travelTime;}
    public void addPassenger(Person person) {
        passengers.add(person);
        System.out.println("person:"+person.getName());
    }

    public void updateTime() {
        if (status.equals("In Transit") && remainingTime > 0) {
            remainingTime--;
           // System.out.println(remainingTime);
        }
    }

    public void easeLife() {
        if (status.equals("In Transit")) {
            for (Person p : passengers) {
                p.decreaseLife();
                //System.out.println("p:"+p.getName());
            }
        }
    }
}