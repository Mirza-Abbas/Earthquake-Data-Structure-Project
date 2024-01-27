import java.util.Scanner;

class PBL{      //PBL Class

    PBL(){      //PBL Class' Constructor

        try {
            YearlyCollection Y = new YearlyCollection(); // YearlyCollection Class' Object
            CountryCollection C = new CountryCollection(); // CountryCollection Class' Object
            BiggestEarthquakes B = new BiggestEarthquakes(); // BiggestEarthquakes Class' Object
            int choice = 0;
            do { // do while loop
                choice = menu();
                switch (choice) { // switch case for int choice
                    case 0: // EXIT
                        //clearScreen();
                        break;
                    case 1: // Display Earthquakes by Year
                        Y.display();
                        choice = option();
                        clearScreen();
                        break;
                    case 2: // Display Earthquakes by Country
                        C.display();
                        choice = option();
                        clearScreen();
                        break;
                    case 3: // Display Average No. of Earthquakes per Year
                        C.avg();
                        choice = option();
                        clearScreen();
                        break;
                    case 4: // Display Biggest Earthquakes from 2005 to 2015
                        B.display2();
                        choice = option();
                        clearScreen();
                        break;
                    case 5: // Display Recent 5 Earthquakes from each Country
                        C.recent5();
                        choice = option();
                        clearScreen();
                        break;
                    case 6: // Display Most Recent above 6 Magnitude Earthquakes
                        C.above6();
                        choice = option();
                        clearScreen();
                        break;
                    default:
                        System.out.println("Invalid Choice");
                        choice = option();
                        clearScreen();
                        break;
                }
            } while (choice != 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    int menu(){     //Menu Method
        Scanner s=new Scanner(System.in);
        System.out.println("\n\t\t+---------------------------------+");
        System.out.println("\t\t| 1965 - 2016 Earthquakes Dataset |");
        System.out.println("\t\t+---------------------------------+");
        System.out.println("\nOperations:");
        System.out.println("1. Earthquakes by Year");
        System.out.println("2. Earthquakes by Country");
        System.out.println("3. Average No. of Earthquakes per Year");
        System.out.println("4. Biggest Earthquakes from 2005 to 2015");
        System.out.println("5. Recent 5 Earthquakes from each Country");
        System.out.println("6. Most Recent above 6 Magnitude Earthquakes");
        System.out.print("0. EXIT\n\nEnter Your Choice: ");
        return (s.nextInt());
    }



    int option(){       //method to ask user for continuance
        Scanner s=new Scanner(System.in);
        System.out.println("\nEnter any No. to continue");
        System.out.print("      0 to EXIT\n\nChoice: ");
        return (s.nextInt());
    }

    public static void clearScreen() {      //clear screen method
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    } 
    public static void main(String[] args) {        //main method
        try {
            new PBL();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}