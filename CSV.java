//File Read & Write Libraries
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException; 

//CSV Read & Write Library
import com.opencsv.CSVWriter;

//Geocoding Libraries
import eu.bitm.NominatimReverseGeocoding.Address;
import eu.bitm.NominatimReverseGeocoding.NominatimReverseGeocodingJAPI;
import org.json.JSONObject;     //JSON Required for Geocoding Library


public class CSV{  //CSV Class
    public static void main(String[] args)   {    //Main Method
        String line = "";  //String to store record
        String splitBy = ",";       //String comma to split records

        NominatimReverseGeocodingJAPI n1 = new NominatimReverseGeocodingJAPI();     //Geocoding Object

        Address address;       //Geocoding Object Address
        Double lat, lng;        //to store latitude and longitude

        BufferedReader Reader;    
        CSVWriter CSVWrite;     //to Write in CSV
        File File1; 
        FileWriter FWrite;

        String[] data;      //String to store the record after being split into parts
        float index,total,percent;  //to calculate file reading completion percentage

        try{ 

            Reader = new BufferedReader(new FileReader("database.csv")); 
            File1=new File("Data.csv");
            FWrite=new FileWriter(File1);
            CSVWrite=new CSVWriter(FWrite);
            data=new String[10];
            String[] temp=new String[10];       //String to store the specific data that is to be written
            
            line = Reader.readLine();   //reads first line (i.e Date, Time, .....)
            data = line.split(splitBy);     //splits it into parts using comma as separator
            data[9]="Address";      //adds a Address column
            
            for(int z=0;z<temp.length;z++){     //loop to store only specific data in temp String
                System.out.println(data[z]);
                temp[z]=data[z];
            }

            CSVWrite.writeNext(temp);       //writes the first line (i.e Date, Time, ....)

            index=1;total=23413;percent=100;
            
            //While loop to read records from dataset
            while ((line = Reader.readLine()) != null){   //returns a Boolean value if there's a next line

                data = line.split(splitBy);    //splits it into parts using comma as separator
                index++;

                lat=Double.parseDouble(data[2]);        //stores latitude
                lng=Double.parseDouble(data[3]);        //stores longitude

                address=n1.getAdress(lat,lng);     //gets address using Address object by using the latitude & longitude
                data[9]=address.toString();        //stores it in String data 

                if(data[9].equals("")){     //checks if the address is null (i.e Unable to geocode)
                    continue;       //if address is null then skips one loop
                }

                //Displays records
                System.out.println("\nDate: [" + data[0] + "], Time: " + data[1] + ", Latitude: " + data[2] + ", Longitude: " + data[3] + ", Type: " + data[4] + ", Depth:" + data[5] +"]");  
                System.out.println("Address: " + data[9]);
                
                //Displays File Reading Completion Percentage
                System.out.println((index/total)*percent + "% Completed\n");

                for(int z=0;z<temp.length;z++){     //loop to store only specific data in temp String
                    temp[z]=data[z];
                }

                CSVWrite.writeNext(temp);       //Writes the record to CSV
            }  
            Reader.close();
            CSVWrite.close();
            FWrite.close();
        }   
        catch (IOException e){  
            e.printStackTrace();  
        }  
    }  
}  