package firstProgram;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import java.util.List;



public class Simulation {
    private List<Planet> planets;
    private List<Spacecraft> spacecrafts;
    //private List<Person> people;
 public static void main(String[] args) throws  InterruptedException {
	 try {
     	
         // قراءة الكواكب
		 String pathP = System.getProperty("user.dir") + File.separator + "dist/Planets.txt";



		 List<Planet> planets = FileReaderUtil.readPlanets(pathP);

		 
         // قراءة المركبات وربطها بالكواكب
		 String pathV = System.getProperty("user.dir") + File.separator + "dist/Vehicles.txt";

		 List<Spacecraft> spacecrafts = FileReaderUtil.readSpacecrafts(pathV, planets);
		 String pathC = System.getProperty("user.dir") + File.separator + "dist/contacts.txt";

         List<Person> persons = FileReaderUtil.readPersons(pathC, spacecrafts);
      
         // بدء المحاكاة
         Simulation simulation = new Simulation(planets, spacecrafts, persons);
         simulation.run();
     } catch (IOException e) {
         System.out.println("EXCEPTION READING FILES: " + e.getMessage());
     }
	 
 }
    public Simulation(List<Planet> planets, List<Spacecraft> spacecrafts, List<Person> people) {
        this.planets = planets;
        this.spacecrafts = spacecrafts;
       
    }

    public void run() {
        boolean allArrived;
        do {
            allArrived = true;
            // في الكواكب زيادة الوقت
            for (Planet planet : planets) {
                planet.advanceOneHour();
            }

            for (Spacecraft spacecraft : spacecrafts) {
            	Planet departurePlanet = getPlanetByName(spacecraft.getDeparturePlanet());//اسم الكوكب المغادر اليه

                switch (spacecraft.getStatus()) {
                    case "Waiting":
                        

                        // ✅ تأكد أن الركاب محتسبين في الكوكب طالما لم تنطلق المركبة
                        for (Person p : spacecraft.getPassengers()) {
                            if (!departurePlanet.getPeople().contains(p)) {
                                departurePlanet.addPerson(p);
                            }
                        }
                    	if (departurePlanet.getDate().isEqual(spacecraft.getDepartureDate())) //اذا كان تاريخ الكوكب المغادرة اليه نفس تاريخ الاقلاع
                        {
                            spacecraft.setStatus("In Transit");
                        }
                        break;

                    case "In Transit":

                    	for (Person p : spacecraft.getPassengers()) {
                    		 if (departurePlanet.getPeople().contains(p))
                            departurePlanet.removePerson(p);
                        }
                        spacecraft.updateTime(); //تحديث وقت الرحلة 
                        spacecraft.easeLife(); //تحديث وقت حياة الشخص

                        boolean allDead = spacecraft.getPassengers().stream()
                                .allMatch(p -> p.getRemainingLife() <= 0);
                        if (allDead) {
                            spacecraft.setStatus("DESTRUCTION");
                            break;
                        }

//                       

                        if (spacecraft.getRemainingTime() <= 0) {
                            Planet destinationPlanet = getPlanetByName(spacecraft.getArrivalPlanet());
                            //Planet depratinationPlanet = getPlanetByName(spacecraft.getDeparturePlanet());
                            int hoursToTravel = spacecraft.getTravelTime();
                            int hoursInDay = destinationPlanet.getHoursInDay(); 

                            int travelDays = (int) Math.ceil((double) hoursToTravel / hoursInDay); //عدد الأيام المستغرقة للوصول
                            LocalDate arrivalDate = spacecraft.getDepartureDate().plusDays(travelDays); // تاريخ المغادرة+عدد الأيام المستغرقة للوصول

                            spacecraft.setArrivalDate(arrivalDate);
                            for (Person p : spacecraft.getPassengers()) {
                                if (p.getRemainingLife() > 0) {
                                    destinationPlanet.addPerson(p);
                                }
                            }
                            
                            spacecraft.setStatus("ARRIVED");
                        }
                        break;

                    case "ARRIVED":
                    case "DESTRUCTION":
                        break;
                }

                if (!spacecraft.getStatus().equals("ARRIVED") && !spacecraft.getStatus().equals("DESTRUCTION")) {
                    allArrived = false;
                }
            }

            printStatus();
            clearConsole();
            try {
                Thread.sleep(100); //يعني دورة كل ساعة
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        } while (!allArrived);

        System.out.println("\n✅ End of simulation:");
        printStatus();
    }

    private Planet getPlanetByName(String name) {
        return planets.stream().filter(p -> p.getName().equals(name)).findFirst().orElse(null);
    }



    private void printStatus() {

    	System.out.println("\n Planets:");

    	int columnWidth = 15; // العرض الثابت لكل عمود

    	// سطر العناوين (أسماء الكواكب)
    	System.out.printf("%-20s", ""); // فراغ أولي قبل الأعمدة
    	for (Planet planet : planets) {
    	    System.out.printf("%-" + columnWidth + "s", "---" + planet.getName() + "---");
    	}
    	System.out.println();

    	// سطر التواريخ
    	System.out.printf("%-20s", "Date");
    	for (Planet planet : planets) {
    	    System.out.printf("%-" + columnWidth + "s", planet.getDate().toString());
    	}
    	System.out.println();

    	// سطر عدد السكان
    	System.out.printf("%-20s", "Population");
    	for (Planet planet : planets) {
    	    System.out.printf("%-" + columnWidth + "s","  "+ planet.getPopulation()+"  ");
    	}
    	System.out.println();




    	System.out.println("\n Spacecrafts :");
    	System.out.printf("%s\n", "═".repeat(90));
    	System.out.printf(" %-12s  %-14s  %-18s  %-18s  %-16s  %-14s \n",
    	        "Name", "Status", "Departure Planet", "Arrival Planet", "Remaining Hours", "Arrival Date");
    	System.out.printf("%s\n", "═".repeat(90));

    	for (Spacecraft s : spacecrafts) {
    	   
    	    
    	    String arrivalDate = (s.getArrivalDate() != null) ? 
    	        " " + s.getArrivalDate().toString() : "--";
    	    String remainingHoursStr;

    	    if(s.getStatus() == "DESTRUCTION") {
    	    	  remainingHoursStr = "--";
    	    }
    	    else {
    	        remainingHoursStr = String.valueOf(s.getRemainingTime());
    	    }
    	    System.out.printf(" %-12s  %-14s  %-2s%-16s  %-2s%-16s  %-16s  %-14s \n",
    	            s.getName(),
    	            s.getStatus(),
    	            "", s.getDeparturePlanet(),
    	            "", s.getArrivalPlanet(),
    	            remainingHoursStr,
    	           // s.getRemainingTime(),
    	            arrivalDate);
    	}
    	System.out.printf("%s\n", "═".repeat(90));

    }
    private static void clearConsole() 
    {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); // لو كنت على Windows
        } catch (Exception e) {
            // طريقة بديلة (Linux أو IDE)
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }
} 