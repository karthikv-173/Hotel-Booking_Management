Hotel Room Booking System (Java Console App)

A Java-based console application that simulates a hotel room booking system. It allows users to view room availability, book rooms, check-in/check-out, and generate bill summaries with accurate time tracking.

---

Features

- View available room types with pricing
- Book rooms by room number and customer name
- Check-in with real-time timestamp
- Auto-generated 12-hour checkout window
- Track actual checkout time
- Apply ₹250 penalty for late checkout
- Bill summary with duration and total charge

---

Concepts Used

- Object-Oriented Programming (OOP)
- Java Collections (`ArrayList`)
- Java Date and Time API (`LocalDateTime`, `Duration`)
- Conditional logic and user input
- Formatted output with `DateTimeFormatter`

---

Technologies

- Language: Java
- Platform: Console (CLI)
- Java Version: 8+
- IDE: IntelliJ IDEA / VS Code / Eclipse

---

How to Run

1. Clone this repository
2. Open `Hotel_Management.java` in your IDE
3. Compile and run the program
4. Use menu options to interact with the system

---

Sample Output

1 -> Show available rooms.
2 -> Book a new room.
3 -> Check In
4 -> Check Out
5 -> View bill summary.
Enter the Choice: 2
Enter the room number: 202
Enter customer name: Karthik
Room 202 is successfully booked for Karthik.


---

Future Improvements

- GUI using Java Swing or JavaFX
- Admin dashboard for managing rooms
- Database (MySQL or SQLite) integration
- Save bill summary to a `.txt` file

---

License

This project is open-source and free to use for educational purposes.
