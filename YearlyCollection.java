import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class DataNode{         //Class DataNode for Linkedlist
    int Year;
    String Date, Time, Country, Address;
    float Magnitude;
    DataNode next;

    DataNode(int Y, String D, String T, float M, String C, String A){       //Constructor
        try {
            Year = Y;
            Date = D;
            Time = T;
            Magnitude = M;

            C = C.replace('\"', ' '); // replaces " with a space
            if (C.charAt(0) == ' ') { // removes c space at the starting of the country name
                C = C.substring(1, C.length() - 1);
            }

            Country = C;
            Address = A.substring(0, A.length()-1); //replace the comma at the end of address

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void display(){     //DataNode display method
        try {
            System.out.println("|Year: " + Year + "   | Date: " + Date + "   | Time: " + Time + "   | Country: " + Country + "   | Address: " + Address + "   | Magnitude: " + Magnitude + "|");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class YearlyCollection {        //YearlyCollection class
    ArrayList <DataNode> YearlyEarthquake=new ArrayList<>();        //Array list to store Linkedlist of each year

    YearlyCollection(){         //Constructor
        String line = "";       //String to store record
        String splitBy = ",";   //String comma to split records
        String record;          //String tp store the whole record
        int Year;
        float Magnitude;

        try {
            BufferedReader br = new BufferedReader(new FileReader("Data.csv"));
            br.readLine();

            //While loop to read records from the filtered data file
            while ((line = br.readLine()) != null) // returns a Boolean value if there's a next line
            {
                //SECTION - STEP 1: Store the address in yearly earthquake collection along with magnitude. 
                // (Collection of each year means 52 collections) 

                record="";
                String[] data = line.split(splitBy);    //splits record into parts using comma as separator

                for(int i=9;i<data.length;i++){     ////loop to store only specific data in temp String
                    record+=data[i]+",";
                }
                
                Magnitude=Float.parseFloat(data[8]);
                Year=Integer.parseInt(data[0].substring(data[0].length()-4, data[0].length()));
            
                    if(YearlyEarthquake.size()!=0){     //Check if the YearlyEarthquake size is not zero

                        for(int i=0; i<YearlyEarthquake.size();i++){    //loop to traverse through the YearlyEarthquakes Arraylist

                            if(Year==(YearlyEarthquake.get(i).Year)){       //Checks if the Record's Year's Linkedlist is present in the Yearly Earthquakes Arraylist

                                DataNode t=YearlyEarthquake.get(i);

                                while(t.next!=null){        //traverses through the Node to add the record at the end of the linkedlist
                                    t=t.next;
                                }

                                t.next=new DataNode(Year,data[0],data[1],Magnitude,data[data.length-1], record);
                                continue;
                            }
                            else if(i==YearlyEarthquake.size()-1){      //This condtions implies that the Record's Year's Linkedlist isn't present in the Yearly Earthquakes Arraylist
                                //adds a new Node to Yearly Earthquakes Arraylist for that Record's Year
                                DataNode t=new DataNode(Year,data[0],data[1],Magnitude,data[data.length-1], record);
                                YearlyEarthquake.add(t);
                                break;
                            }  
                        }
                    }
                    else if(YearlyEarthquake.size()==0){        //checks if YearlyEarthquakes size is zero
                        //adds a new Node to Yearly Earthquakes Arraylist for that Record's Year
                        DataNode t=new DataNode(Year,data[0],data[1],Magnitude,data[data.length-1], record);
                        YearlyEarthquake.add(t);
                    }   
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void display(){     //Yearly Collection display method
        int Year;
        Scanner s=new Scanner(System.in);

        try {
            System.out.println("\nEnter Year to see records (1965 to 2016): ");
            Year = s.nextInt() - 1965; // takes Year as input from user and subtracts 1965 to get the YearlyEarthquakes Arraylist index
            if (Year > -1 && Year < 52) { // checks if the index is between 0 to 51

                DataNode n = YearlyEarthquake.get(Year);
                int x = 1;
                while (n != null) { // traverses through that year's Linkedlist
                    System.out.print(x + ". ");
                    n.display(); // calls DataNode display method
                    n = n.next;
                    x++;
                }
            } else {
                System.out.println("\nInvalid Year");
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    int size(){     //returns size of YearlyEarthquake Arraylist
        return YearlyEarthquake.size();
    }

    DataNode get(int index){        //returns the Linkedlist of the given index no. from the Yearly earthquakes Arraylist
        return YearlyEarthquake.get(index);
    }
}
