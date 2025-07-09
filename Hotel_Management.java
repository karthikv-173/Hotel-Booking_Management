import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
class Room{
    int room_no;
    String room_type;
    double price_per_night;
    boolean is_booked;
    boolean is_occupied;
    String customer_name;
    LocalDateTime checkIn;
    LocalDateTime checkOut;
    Room(int room_no, String room_type, double price_per_night) {
        this.room_no = room_no;
        this.room_type = room_type;
        this.price_per_night = price_per_night;
        this.is_booked = false;
        this.is_occupied = false;
        this.customer_name = "";
        this.checkIn = null;
        this.checkOut = null;
    }
    void display()
    {
        System.out.println("Room no: "+room_no+
        "\n Room type: "+room_type+
        "\n Price per night: "+price_per_night+
        "\n Booking Avaialble: "+(is_booked? "Yes" : "No")+
        "\n Occupied: " +(is_occupied? "Yes" : "No"));
    }
}
public class Hotel_Management {
    public static void main(String[] args){
        Scanner scan=new Scanner(System.in);
        List<Room> rooms=new ArrayList<>();
        rooms.add(new Room(201, "Single", 7500));
        rooms.add(new Room(202, "Double", 10000));
        rooms.add(new Room(203, "Suite", 12500));
        rooms.add(new Room(204, "Luxury", 17500));
        rooms.add(new Room(205, "Extra Luxury", 25000));
        int x;
        System.out.println("1 -> Show available rooms.");
        System.out.println("2 -> Book a new room.");
        System.out.println("3 -> Check In");
        System.out.println("4 -> Check Out");
        System.out.println("5 -> View bill summary.");
        System.out.println("6 -> View booked and avaible rooms.");
        
        
        do{
            System.out.print("Enter the Choice: ");
            x=scan.nextInt();
            switch (x) {
            case 1:
                display_rooms(rooms);
                break;
            case 2:
                book_room(rooms);
                break;
            case 3:
                check_In(rooms);
                break;
            case 4:
                check_out(rooms);
                break;
            case 5:
                view_bill_summary(rooms);
                break;
            case 6:
                available_and_booked_rooms(rooms);
                break;
            default:
                System.out.println("Invalid choice.");
                break;
           }
        }while (x!=6);
        

    }
    public static void display_rooms(List<Room> rooms){
        System.out.println("<--Available Rooms-->");
        for(Room r : rooms)
        {
            r.display();
        }
    }
    public static void book_room(List<Room> rooms)
    {
        Scanner scan=new Scanner(System.in);
        System.out.print("Enter the room number: ");
        int book_room_no=scan.nextInt();
        Room selectedRoom=null;
        String cust_name;
        for(Room r: rooms)
        {
            if(book_room_no==r.room_no && !r.is_booked)
            {
                selectedRoom=r;
                break;
            }
        }
        if(selectedRoom==null)
        {
            System.out.println("No rooms available");
            return;
        }
        System.out.print("Enter customer name: ");
        cust_name=scan.next();

        selectedRoom.is_booked=true;
        selectedRoom.customer_name=cust_name;
        System.out.println("Room "+selectedRoom.room_no+" is successfully booked for "+selectedRoom.customer_name+".");
    }
    public static void check_In(List<Room> rooms)
    {
        Scanner scan =new Scanner(System.in);
        DateTimeFormatter timeFormatter=DateTimeFormatter.ofPattern("hh:mm a");
        System.out.print("Enter the room number to get the check-in time: ");
        int book_room_no;
        book_room_no=scan.nextInt();
        Room selectedRoom=null;
        for(Room r:rooms)
        {
            if(book_room_no==r.room_no && r.is_booked &&!r.is_occupied)
            {
                selectedRoom=r;
                break;
            }

        }
        if(selectedRoom==null)
        {
            System.out.println("Can't get the room number. Try again by entering a valid room number.");
            return;
        }
        selectedRoom.is_occupied=true;
        selectedRoom.checkIn=LocalDateTime.now();
        System.out.println(selectedRoom.customer_name+" your check-in time is - "+selectedRoom.checkIn.format(timeFormatter));
    }
    public static void check_out(List<Room> rooms)
    {
        Scanner scan=new Scanner(System.in);
        DateTimeFormatter timeFormatter=DateTimeFormatter.ofPattern("hh:mm a");
        System.out.print("Enter the room number to get checkout time: ");
        int book_room_no=scan.nextInt();
        Room selectedRoom=null;
        for(Room r: rooms)
        {
            if(book_room_no==r.room_no && r.is_booked && r.is_occupied)
            {
                selectedRoom=r;
                break;
            }
        }
        if(selectedRoom==null)
        {
            System.out.println("Please enter a valid room number.");
            return ;
        }
        selectedRoom.checkOut=selectedRoom.checkIn.plusHours(12);
        LocalDateTime actual_checkout_time=LocalDateTime.now();
        System.out.println(selectedRoom.customer_name+" assigned checkout time for you is "+selectedRoom.checkOut.format(timeFormatter));
        System.out.println(selectedRoom.customer_name+" your actual checkout time was "+actual_checkout_time.format(timeFormatter));
    }
    public static void view_bill_summary(List<Room> rooms)
    {
        Scanner scan =new Scanner(System.in);
        int bill_number=1;
        DateTimeFormatter timeFormatter=DateTimeFormatter.ofPattern("hh:mm a");
        System.out.print("Enter the booked room no: ");
        int book_room_no=scan.nextInt();
        Room selectedRoom=null;
        for(Room r : rooms)
        {
            if(book_room_no==r.room_no && r.is_booked && r.is_occupied)
            {
                selectedRoom=r;
                break;
            }
        }
        if(selectedRoom==null)
        {
            System.out.println("Kindly enter the valid room number.");
            return;
        }
        System.out.println("<--- Bill Summary --->");
        System.out.println(bill_number);
        System.out.println("Room booked by: "+selectedRoom.customer_name);
        System.out.println("Room type booked: "+selectedRoom.room_type);
        System.out.println("Room check-in time: "+selectedRoom.checkIn.format(timeFormatter));
        System.out.println("Room check-out time assigned: "+selectedRoom.checkOut.format(timeFormatter));
        LocalDateTime actuDateTime=LocalDateTime.now();
        
        long hours=Duration.between(selectedRoom.checkIn, selectedRoom.checkOut).toHours();
        long mins=Duration.between(selectedRoom.checkIn, selectedRoom.checkOut).toMinutes();
        long days=hours/24;
        if(hours%24!=0) days++;
        double total_price=days*(selectedRoom.price_per_night);
        if(actuDateTime.isAfter(selectedRoom.checkOut))
        {
            System.out.println("Your checkout time is exceeded.");
            total_price+=250;
        }
        selectedRoom.checkOut=actuDateTime;
        System.out.println("Actual room check-out time by "+selectedRoom.customer_name+" is "+selectedRoom.checkOut.format(timeFormatter));
        System.out.println("Total bill have to be pay: "+total_price);
        /*selectedRoom.customer_name="";
        selectedRoom.checkIn=null;
        selectedRoom.checkOut=null;
        selectedRoom.is_booked=false;
        selectedRoom.is_occupied=false;*/
        System.out.println("Your checkout completed.");
    }
    public static void available_and_booked_rooms(List<Room> rooms)
    {
        Room selectedRoom=null;
        System.out.println("<!-- Available rooms for booking -->");
        for(Room r:rooms)
        {
            selectedRoom=r;
            if(!r.is_booked && !r.is_occupied)
            {
                System.out.println(selectedRoom.room_no+" "+selectedRoom.room_type+" "+selectedRoom.price_per_night);
            }
            else{
                System.out.println("No rooms available for booking.");
            }
        }
        System.out.println("<-- Booked rooms -->");
        for(Room r:rooms)
        {
            if(r.is_booked && !r.is_occupied)
            {
                System.out.println(selectedRoom.room_no+" "+selectedRoom.room_type+" "+selectedRoom.price_per_night);
            }
            else{
                System.out.println("No room is booked.");
            }
        }
        System.out.println("<-- Occupied rooms -->");
        for(Room r:rooms)
        {
            if(r.is_booked && r.is_occupied)
            {
                System.out.println(selectedRoom.room_no+" "+selectedRoom.room_type+" "+selectedRoom.price_per_night);
            }
            else{
                System.out.println("No room is occupied.");
            }
        }
        
    }
}