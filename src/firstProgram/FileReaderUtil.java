// İsim: Rabee Sultan
// Öğrenci No: b121210080
// Ödev: Uzay Simülasyonu
package firstProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FileReaderUtil {
   // private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static List<Planet> readPlanets(String filePath) throws IOException {
        List<Planet> planets = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // تخطي السطور الفارغة
                
                String[] parts = line.split("#");
                if (parts.length != 3) continue; // تنسيق غير صحيح

                String name = parts[0];//اسم الكوكب
                int hoursInDay = Integer.parseInt(parts[1]);//عمر الكوكب
                LocalDate date = LocalDate.parse(parts[2], formatter); //التاريخ الحالي للكوكب

                Planet planet = new Planet(name, hoursInDay, date);
                planets.add(planet);
            }
        }

        return planets;
    }

    	public static List<Spacecraft> readSpacecrafts(String filename, List<Planet> planets) throws IOException {
    	    List<Spacecraft> spacecrafts = new ArrayList<>();
    	    List<String> lines = Files.readAllLines(Paths.get(filename));
    	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");

    	    for (String line : lines) {
    	        String[] parts = line.split("#");
    	        String name = parts[0];
    	        String departureName = parts[1];
    	        String arrivalName = parts[2];
    	        LocalDate departureDate = LocalDate.parse(parts[3], formatter);
    	        int travelTime = Integer.parseInt(parts[4]);

    	        //Planet departurePlanet = findPlanetByName(planets, departureName);
//    	        Planet arrivalPlanet = findPlanetByName(planets, arrivalName);

    	        spacecrafts.add(new Spacecraft(name, departureName, arrivalName, departureDate, travelTime));
    	    }

    	    return spacecrafts;
    	}

//    	private static Planet findPlanetByName(List<Planet> planets, String name) {
//    	    for (Planet p : planets) {
//    	        if (p.getName().equals(name)) {
//    	            return p;
//    	        }
//    	    }
//    	    return null;
//    	}

    	public static List<Person> readPersons(String filePath, List<Spacecraft> spacecrafts) throws IOException {
    	    List<Person> persons = new ArrayList<>();
    	    BufferedReader reader = new BufferedReader(new FileReader(filePath));
    	    String line;
    	    
    	    while ((line = reader.readLine()) != null) {
    	        String[] parts = line.split("#");
    	        if (parts.length == 4) {
    	            String name = parts[0];//اسم الشخص
    	            int age = Integer.parseInt(parts[1]); //العمر
    	            int lifeRemaining = Integer.parseInt(parts[2]); //العمر الافتراضي
    	            String vehicleName = parts[3]; //اسم المركبة

    	            Person person = new Person(name, age, lifeRemaining, vehicleName);
    	            persons.add(person);
//اضافة الاشخاص الى المركبات
    	            for (Spacecraft spacecraft : spacecrafts) {
    	                if (spacecraft.getName().equals(vehicleName)) {
    	                    spacecraft.getPassengers().add(person);
    	                   

    	                    break;
    	                }
    	                //System.out.println(spacecraft.getPassengers());
    	            }
    	        }
    	    }
//    	    System.out.println(vehicleName);
//            System.out.println(a);

    	    reader.close();
    	    return persons;
    	}

    	

}
