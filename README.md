Homework Content

The Eclipse project you will write must be in Java and must be a console application. People will be able to use spacecraft An application will be designed where they travel from one planet to another. When the application runs
will read contacts from the Contacts.txt file, the format is given below. Remaining life is in hours.

Once the remaining life reaches zero, the person dies and is not included in the census.

name#age#remaining_life#space_vehicle_name ====> Ahmet#25#8500#A

Spacecraft will be read from the Vehicles.txt file. The format is given below. Spacecraft

When the date of the planet it is on is equal to the launch date of the spacecraft, the spacecraft moves away from the planet.

If all people on a spacecraft die, the spacecraft will also be destroyed. The list on the screen

will appear but will write the status as DESTRUCTION.


Space_vehicle_name#exit_planet#arrival_planet#exit_date#distance_as_time => A#X#Y#3.11.2022#580

Planets will be read from the Planets.txt file. The format is given below. Each planet

times are different so the number of hours in a day and the date on the planet must be read from the file.


Planet_Name#hour_of_day#Date_on_the_planet ====> X#24#5.10.2020

When the program starts, information should be written on the screen in the following format. (The information is written randomly.

This information is not related to the above data.) This information is continuously cleared in a cycle. It should be updated. It takes 1 hour for the 1st iteration of the loop to be completed.
will be considered. When the program starts, the time on each planet will start as 00:00. Space

Since there is no time in the launch dates of the vehicles, the spacecraft must start its journey as soon as the relevant date is entered.

The spacecraft is on a planet, and its passengers are included in the population of that planet. The spacecraft is on its way

passengers will not be included on any planet. When all spacecraft reach their destinations

The program will be completed and the latest situation on the planets will be written on the screen. Therefore, the program

When it is finished, the remaining time of all spacecraft to the target should be 0. The date it will reach the target is the time of the planet it will reach.

should be written according to the date range. The format below should be exactly the same as shown on the screen.

The remaining time to the destination and the date on which the destroyed vehicles will arrive at the destination should be written as --.
 
















The tools, methods and libraries to be used are free, but the minimum is the following class:

hierarchy must be provided. The program must be made fully object-oriented and modular.

Time Class
Person Class
Spacecraft Class Planet Class Simulation Class FileReader Class


The program should be tested with large files. A folder named dist will be added to the project folder and the executable .jar file of the last version of the program before it was loaded to the system will be copied into this folder. This jar file should have the feature of cleaning the console when it is run from the console.
