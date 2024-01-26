interface Queue{        //Queue Interface
    public void add(Object C, Object M);
    public Object first();
    public Object remove();
    public int Size();
}

class LinkedQueue implements Queue{     //Class LinkedQueue that implements Queue Interface

    private Node head=new Node(null, null);     
    private int size;

    private static class Node{      //Class Node
        Object Country;
        Object Magnitude;
        Node prev=this;
        Node next=this;

        Node(Object C, Object M){       //Node Class' Constructor
            Country=C;
            Magnitude=M;
        }

        Node(Object C, Object M, Node n, Node p){       //Node Class' Constructor
            Country=C;
            Magnitude=M;
            next=n;
            prev=p;
        }

        void display(){     //Node Class' Display Method
            System.out.println("|Country: " + Country + " \t|Magnitude: " + Magnitude);
        }
    }

    public int Size(){      //method to return size of LinkedQueue
        return size;
    }

    boolean IsEmpty(){      //method to check if LinkedQueue is empty
        return (size==0);
    }

    public Object first(){      //method to return the first object in the Linked Queue
        if(IsEmpty()){
            throw new IllegalStateException();
        }
        return head.next;
    }

    public void add(Object C, Object M){    //method to add Node in LinkedQueue
        head.prev.next=new Node(C, M, head, head.prev);
        head.prev=head.prev.next;
        ++size;
    }

    public Object remove(){     //Method to remove Node from LinkedQueue
        Node temp=head.next;
        head.next=head.next.next;
        head.next.prev=head;
        --size;
        return temp;
    }

    void display(){     //LinkedQueue Class' Display Method
        Node temp=head.next;
        int i=1965;

        try {
            while(temp!=head){      //loop to treverse through Node
                System.out.print(i + ". ");
                temp.display();     //calls Node's Display Method
                temp=temp.next;
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void display2(){        //Method to display biggest Earthquakes from 2005 to 2015

        //SECTION - PROBLEM 2: Which are the biggest earthquakes from 2005 to 2015 and occurred and in which country (use step 2)? 


        Node temp=head.next;
        int i=1965;

        try {
            while(temp!=head){      //loop to treverse through Node
                if(i>2004 && i<2016){       //checks if record is between Years 2005 to 2015
                    System.out.print(i + ". ");
                    temp.display();        //calls Node's Display Method
                }
                temp=temp.next;
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class BiggestEarthquakes {      //BiggestEarthquakes Class

    LinkedQueue HighestEarthquakes;     //LinkedQueue Object to store biggest Earthquakes of each year

    YearlyCollection Y=new YearlyCollection();      //YearlyCollection Class Object
    
    BiggestEarthquakes(){       //BiggestEarthquakes Class' Constructor
        int index=0;
        String Country="";      //String to store name of Country
        HighestEarthquakes=new LinkedQueue();

        try {
            while(index<Y.size()){         //loop to traverse through YearlyEarthquakes Arraylist

                //SECTION - STEP 2: Make a queue storing biggest (with highest magnitude) quake of each year with magnitude and 
                //country, starting from 1965 to 2016. (52 elements in the queue approx.). 


                float highest=0;
                DataNode temp=Y.get(index);
    
                while(temp!=null){  //loop to traverse through that Year's Linkedlist
    
                    if(temp.Magnitude>highest){     //checks if the magnitude of current earthquakes is higher than the previous
                        highest=temp.Magnitude;
                        Country=temp.Country;
                    }
                    temp=temp.next;
                }
                HighestEarthquakes.add(Country, highest);
                index++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void display(){     //BiggestEarthquake class' Display method
        HighestEarthquakes.display();       //calls LinkedQueue Display Method
    }

    void display2(){        //BiggestEarthquake class' Display method to display earthquakes from 2005 to 2015
        HighestEarthquakes.display2();      //calls LinkedQueue Display2 Method
    }

    int size(){     //BiggestEarthquake class' size method
        return HighestEarthquakes.Size();
    }
}
