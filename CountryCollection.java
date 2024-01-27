import java.util.ArrayList;
import java.util.Scanner;

class Node{     ////Class Node for stack
    Object Country, Date, Magnitude;
    Node next;

    Node(Object C, Object D, Object M){
        Country=C;
        Date=D;
        Magnitude=M;
    }

    void display(){
        System.out.println("| Country: " + Country + "\t| Date: " + Date + "\t| Magnitude: " + Magnitude);
    }
}

interface Stack{        //Stack Interface
    public Object peek();
    public Object pop();
    public void push(Object C,  Object D, Object M);
    public int Size();

}

class LinkedStack implements Stack{     //LinkedStack class that implements Stack

    Node top;   //Node class object for the top of the stack
    int size;       

    LinkedStack(){      //LinkedStack Constructor
        top=null;
        size=0;
    }

    public Object peek(){       // method to Return the top of the Stack
        if(IsEmpty()){
            System.out.println("List's Empty");
            return null;
        }
        return top;
    }

    public Object pop(){        //method to remove the top of the stack
        if(IsEmpty()){
            System.out.println("List's Empty");
            return null;
        }

        Object d=top;
        top=top.next;
        --size;
        return d;
    }

    public void push(Object C, Object D, Object M){     //method to add a new node in the stack at the top 
        try {
            Node t = new Node(C, D, M);
            t.next = top;
            size++;
            top = t;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int Size(){      //returns size of stack
        return size;
    }

    boolean IsEmpty(){      //check if the Stack is Empty
        return top==null;
    }

    public void display(){      // method to display the Node Records
        try {
            if (IsEmpty()) {
                System.out.println("Stack Empty");
                return;
            }
            else {
                Node temp = top;
                int count=1;
                while (temp != null) {
                    System.out.print(count + ". ");
                    temp.display();     //calls Node Display Method
                    count++;
                    temp = temp.next;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void recent5(){      //method to display the recent five Earthquakes

        //SECTION - PROBLEM 3: How to determine the recent 5 earthquakes from each country?
        try {
            int count = 0;
            System.out.println();
            for (Node temp = top; temp != null; temp = temp.next) {
                if (count < 5) {
                    System.out.print((count + 1) + ". ");
                    temp.display(); // calls Node Display Method
                }
                count++;
                if (count >= 5) {
                    break;
                }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class CountryCollection{        //CountryCollection Class
    
    ArrayList <LinkedStack> CountryEarthquakes=new ArrayList<>();       //Array list to store Linkedstack of each country
    Node MostRecentEarthquakes;
    YearlyCollection Y=new YearlyCollection();      //YearlyCollection class Object

    CountryCollection(){        //COuntryCollection Constructor
        try {
            int index=0;
            while(index<Y.size()){      //loop to traverse through YearlyEarthquakes Arraylist

                //SECTION - STEP 3: Make a stack from the collections, one for each country which stores earthquake and its magnitude 
                //in the order of the event (the most recent event on top). 


                DataNode t=Y.get(index);

                while(t!=null){     //loop to traverse through Linkedlist of each year

                    if(CountryEarthquakes.size()!=0){       //Check if the CountryCollection Arraylist's size is not zero

                        for(int x=0;x<CountryEarthquakes.size();x++){   //loop to traverse through the CountryEarthquakes Arraylist

                            if(t.Country.equalsIgnoreCase((String)CountryEarthquakes.get(x).top.Country)){  //Checks if the Record's Country's LinkedStack is present in the CountryEarthquakes Arraylist
                                //adds the record at the top of the LinkedStack
                                CountryEarthquakes.get(x).push(t.Country,t.Date,t.Magnitude);
                                break;
                            }

                            else if(x==CountryEarthquakes.size()-1){    //This condtions implies that the Record's Country's LinkedStack isn't present in the CountryEarthquakes Arraylist

                                //adds a new LinkedStack to CountryEarthquakes Arraylist for that Record's Country
                                LinkedStack temp=new LinkedStack();
                                temp.push(t.Country, t.Date, t.Magnitude);
                                CountryEarthquakes.add(temp);
                                break;
                            }
                        }
                        t=t.next; 
                    }
                    else if(CountryEarthquakes.size()==0){      //checks if CountryEarthquakes size is zero
                        //adds a new LinkedStack to CountryEarthquakes Arraylist for that Record's Country
                        LinkedStack temp=new LinkedStack();
                        temp.push(t.Country, t.Date, t.Magnitude);
                        CountryEarthquakes.add(temp);
                        t=t.next;
                    }
                }
                index++;
            }

            //SECTION - STEP 4: Make a linked list which saves the one most recent earthquake with magnitude and country name 
            // from each country (use the stack from step 3). 
            
            //stores the most recent earthquakes of each country in the MostRecentEarthquakes Linkedlist
            MostRecentEarthquakes =new Node(CountryEarthquakes.get(0).top.Country, CountryEarthquakes.get(0).top.Date, CountryEarthquakes.get(0).top.Magnitude);
            Node temp=MostRecentEarthquakes;
            for(int i=1;i<CountryEarthquakes.size();i++){       //Loop to traverse through COuntryEarthquakes Arraylist
                Node t = CountryEarthquakes.get(i).top;
                temp.next=new Node(t.Country, t.Date, t.Magnitude);
                temp=temp.next;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }

    void display(){     //CountryCollection class' display method
        String Country;
        Scanner s=new Scanner(System.in);

        try {
            System.out.println("\nEnter Country to see records: ");
            Country=s.nextLine();       //takes country name as input from user

            for(int i=0;i<CountryEarthquakes.size();i++){       //Loop to traverse through COuntryEarthquakes Arraylist

                LinkedStack temp = CountryEarthquakes.get(i);
                String name=(String)temp.top.Country;

                if(name.equalsIgnoreCase(Country)){     //checks if the inputted Country name matches the LinkedStack's Country name
                    temp.display();     //Calls LinkedStack's display method
                    return;
                }
            }
            System.out.println("\nCountry Not Found");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    int size(){     //method to return size of CountryEarthquakes Arraylist
        return CountryEarthquakes.size();
    }

    LinkedStack get(int index){     //returns the LinkedStack of the given index no. from the CountryEarthquakes Arraylist
        return CountryEarthquakes.get(index);
    }

    void mostrecent(){      //method to display the most recent earthquake in a Country


        String Country;
        Scanner s=new Scanner(System.in);

        try {
            System.out.println("Enter Country to see most recent record: ");
            Country=s.nextLine();       //takes country name as input from user

            for(Node t=MostRecentEarthquakes;t!=null;t=t.next){        //loop to traverse through the mostrecent Linkedlist
                if(Country.equalsIgnoreCase((String)t.Country)){        //checks if the inputted Country name matches the LinkedStack's Country name
                    t.display();        //calls Node's Display Method
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void recent5(){     //method to display the recent 5 earthquakes of each country

        // String Country;
        // Scanner s=new Scanner(System.in);

        try {
        //     System.out.println("\nEnter Country to see recent 5 records: ");
        //     Country=s.nextLine();       //takes country name as input from user

            for(int i=0;i<CountryEarthquakes.size();i++){       //Loop to traverse through COuntryEarthquakes Arraylist

                LinkedStack temp = CountryEarthquakes.get(i);
                String name=(String)temp.top.Country;

                // if(name.equalsIgnoreCase(Country)){     //checks if the inputted Country name matches the LinkedStack's Country name
                    temp.recent5();     //Calls LinkedStack's recent5 Method
                    i++;
                }
            // }
            System.out.println("Country Not Found");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void avg(){         //method to calculate average no. of earthquakes per year

        //SECTION - PROBLEM 1: How to find the average number of earthquakes per year for each country and which country is 
        // most vulnerable to earthquakes (which country has the most number of earth quakes)? 


        double High=0;      //to store the highest no. of earthquakes out of all thr countries
        double totalEarthquakes=0;      //to store the total no of earthquakes in a country
        String vulnerable="";       //String to store the name of the country most vulnerable to earthquakes
        int index=0;

        try {
            while(index<CountryEarthquakes.size()){     //loop to traverse through CountryEarthquakes Arraylist

                LinkedStack t=CountryEarthquakes.get(index);
                totalEarthquakes=t.size;
    
                if(totalEarthquakes>High){      //Checks for the most number of earthquakes in a country
                    vulnerable=(String)CountryEarthquakes.get(index).top.Country;
                    High=totalEarthquakes;
                }
                Double td=(Double)(totalEarthquakes/52);        //calculates average no. of earthquakes per year
                double f = Math.round(td*100) / 100.0;
    
                System.out.println((index+1) + ". |" + CountryEarthquakes.get(index).top.Country + " \t|Total : " + totalEarthquakes + " \t|Average: " + f);
                index++;
            }
            //displays most vulnerable country to the earthquakes
            System.out.println("\nMost Vulnerable: " + vulnerable + " \t|Total: " + High + " \t|Average: " + High/52);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void above6(){      //method to display the most recent above 6 earthquakes from each country

        //SECTION - PROBLEM 4: How to find the most recent above 6 magnitude earthquakes (use step 4). 

        int count=1;

        try {
            for(Node t=MostRecentEarthquakes;t!=null;t=t.next){        //loop to traverse through the mostrecent Linkedlist
                if((float)t.Magnitude>6){        //checks if the magnitude of the earthquakes is above 6
                    System.out.print(count + ". ");
                    t.display();        //calls Node's Display Method
                    count++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
