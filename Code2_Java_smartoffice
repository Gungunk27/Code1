import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

class ConferenceRoom {
    private int roomId;
    private boolean isBooked;
    private boolean isOccupied;
    private boolean airConditioningOn;
    private boolean lightsOn;
    private Booking booking;

    public ConferenceRoom(int roomId) {
        this.roomId = roomId;
        this.isBooked = false;
        this.isOccupied = false;
        this.airConditioningOn = false;
        this.lightsOn = false;
        this.booking = null;
    }

    public void bookRoom(Booking booking) {
        if (isBooked) {
            System.out.println("Room " + roomId + " is already booked.");
        } else {
            this.isBooked = true;
            this.booking = booking;
            System.out.println("Room " + roomId + " booked successfully.");
        }
    }

    public void cancelBooking() {
        if (isBooked) {
            this.isBooked = false;
            this.booking = null;
            turnOffAirConditioningAndLights();
            System.out.println("Room " + roomId + " booking cancelled.");
        } else {
            System.out.println("Room " + roomId + " is not currently booked.");
        }
    }

    public void detectOccupancy(boolean occupancyStatus) {
        this.isOccupied = occupancyStatus;
        if (this.isOccupied) {
            turnOnAirConditioningAndLights();
        } else {
            turnOffAirConditioningAndLights();
        }
    }

    public void releaseRoomIfNotOccupied(int minutesElapsed) {
        if (isBooked && !isOccupied && minutesElapsed > 5) {
            System.out.println("Room " + roomId + " released due to no occupancy.");
            cancelBooking();
        }
    }

    private void turnOnAirConditioningAndLights() {
        this.airConditioningOn = true;
        this.lightsOn = true;
        System.out.println("Air conditioning and lights turned ON in Room " + roomId + ".");
    }

    private void turnOffAirConditioningAndLights() {
        this.airConditioningOn = false;
        this.lightsOn = false;
        System.out.println("Air conditioning and lights turned OFF in Room " + roomId + ".");
    }
}

class Booking {
    private String bookedBy;
    private String startTime;
    private String endTime;

    public Booking(String bookedBy, String startTime, String endTime) {
        this.bookedBy = bookedBy;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getBookedBy() {
        return bookedBy;
    }
}

class Sensor {
    public static boolean detectOccupancy() {
        Random random = new Random();
        return random.nextBoolean();  // Randomly simulate occupancy detection.
    }
}

class OfficeManager {
    private static OfficeManager instance;
    private List<ConferenceRoom> rooms;

    private OfficeManager() {
        rooms = new ArrayList<>();
    }

    public static OfficeManager getInstance() {
        if (instance == null) {
            instance = new OfficeManager();
        }
        return instance;
    }

    public void configureOffice(int numRooms) {
        for (int i = 1; i <= numRooms; i++) {
            rooms.add(new ConferenceRoom(i));
        }
        System.out.println("Office configured with " + numRooms + " conference rooms.");
    }

    public void bookRoom(int roomId, Booking booking) {
        if (roomId >= 1 && roomId <= rooms.size()) {
            ConferenceRoom room = rooms.get(roomId - 1);
            room.bookRoom(booking);
        } else {
            System.out.println("Invalid room ID.");
        }
    }

    public void cancelRoomBooking(int roomId) {
        if (roomId >= 1 && roomId <= rooms.size()) {
            ConferenceRoom room = rooms.get(roomId - 1);
            room.cancelBooking();
        } else {
            System.out.println("Invalid room ID.");
        }
    }

    public void checkOccupancy(int roomId) {
        if (roomId >= 1 && roomId <= rooms.size()) {
            ConferenceRoom room = rooms.get(roomId - 1);
            boolean occupied = Sensor.detectOccupancy();
            room.detectOccupancy(occupied);
            System.out.println("Room " + roomId + " occupancy status: " + (occupied ? "Occupied" : "Not Occupied"));
        } else {
            System.out.println("Invalid room ID.");
        }
    }

    public void releaseUnoccupiedRoom(int roomId, int minutesElapsed) {
        if (roomId >= 1 && roomId <= rooms.size()) {
            ConferenceRoom room = rooms.get(roomId - 1);
            room.releaseRoomIfNotOccupied(minutesElapsed);
        } else {
            System.out.println("Invalid room ID.");
        }
    }
}

public class SmartOfficeApp {
    public static void main(String[] args) {
        OfficeManager officeManager = OfficeManager.getInstance();

        // Configure the office with 3 conference rooms
        officeManager.configureOffice(3);

        // Book a room
        Booking booking = new Booking("John Doe", "09:00 AM", "10:00 AM");
        officeManager.bookRoom(1, booking);

        // Simulate checking occupancy
        officeManager.checkOccupancy(1);

        // Simulate occupancy detection and release the room if not occupied
        TimerTask checkOccupancyTask = new TimerTask() {
            @Override
            public void run() {
                officeManager.releaseUnoccupiedRoom(1, 6); // Release room if unoccupied for more than 5 minutes
            }
        };

        // Schedule the task to run every minute
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(checkOccupancyTask, 0, 60000);

        // Simulate cancelling a booking
        officeManager.cancelRoomBooking(1);

        // Allow some time for the task to execute before ending the program
        try {
            Thread.sleep(200000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
