import java.util.*;
import java.io.*;
import java.time.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.awt.Toolkit;
import java.time.format.DateTimeFormatter;
import java.nio.file.*;
import javax.sound.sampled.*;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.time.temporal.ChronoUnit;
import javax.sound.sampled.*;


public class ChronoSphere {
    public static void main(String[] args) throws InterruptedException {
        ChronoSphereApp app = new ChronoSphereApp();
        app.run();
    }
}

class ChronoSphereApp {
    //  COLOR CODES
    private static final String RESET = "\033[0m";
    private static final String BOLD = "\033[1m";
    private static final String DIM = "\033[2m";

    // Status icons 
    private static final String CHECK = "✓";
    private static final String CROSS = "✗";
    private static final String INFO = "ⓘ";

    // standard ANSI
    private static final String CYAN = "\033[36m";
    private static final String BLUE = "\033[34m";
    private static final String GREEN = "\033[32m";
    private static final String YELLOW = "\033[33m";
    private static final String RED = "\033[31m";
    private static final String MAGENTA = "\033[35m";
    private static final String WHITE = "\033[37m";

    // Bright colors 
    private static final String BRIGHT_CYAN = "\033[96m";
    private static final String BRIGHT_YELLOW = "\033[93m";
    private static final String BRIGHT_GREEN = "\033[92m";
    private static final String BRIGHT_RED = "\033[91m";

    private static final String PURPLE = MAGENTA; 
    
    private Scanner input;
    private WorldClockModule worldClock;
    private PrayerTimeModule prayerTime;
    private CalendarTaskModule calendarTask;
    private AlarmModule alarm;
    private UtilitiesModule utilities;
    
    public ChronoSphereApp() {
        this.input = new Scanner(System.in);
        this.worldClock = new WorldClockModule();
        this.prayerTime = new PrayerTimeModule();
        this.calendarTask = new CalendarTaskModule();
        this.alarm = new AlarmModule();
        this.utilities = new UtilitiesModule();
    }
    
    public void run() throws InterruptedException {
        displaySplashScreen();
        mainMenu();
    }
    
    public void displaySplashScreen() throws InterruptedException {
        clearScreen();
        
        // Get current date and time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("EEEE");
        
        String date = now.format(dateFormatter);
        String time = now.format(timeFormatter);
        String day = now.format(dayFormatter);
        
        // Print splash screen with colors
        System.out.println(CYAN + "╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════╗" + RESET);
        System.out.println(CYAN + "║" + RESET + "                                                                                                           " + CYAN + "║" + RESET);
        System.out.println(CYAN + "║" + RESET + "                                                                                                       " + CYAN + "    ║" + RESET);
        System.out.println(CYAN + "║" + RESET + "                          ██████╗ ██║  ██ ██████╗  ██████╗ ███╗   ██╗ ██████╗                     " + CYAN + "         ║" + RESET);
        System.out.println(CYAN + "║" + RESET + "                         ██╔════╝ ██║  ██ ██╔══██ ██╔═══██╗████╗  ██║██╔═══██╗                    " + CYAN + "         ║" + RESET);
        System.out.println(CYAN + "║" + RESET + "                         ██║      ███████ ██████  ██║   ██║██╔██╗ ██║██║   ██║                    " + CYAN + "         ║" + RESET);
        System.out.println(CYAN + "║" + RESET + "                         ██║      ██╔══██ ██╔══██╗██║   ██║██║╚██╗██║██║   ██║                    " + CYAN + "         ║" + RESET);
        System.out.println(CYAN + "║" + RESET + "                         ╚██████╔╝██║  ██ ██║  ██║╚██████╔╝██║ ╚████║╚██████╔╝                    " + CYAN + "         ║" + RESET);
        System.out.println(CYAN + "║" + RESET + "                          ╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝ ╚═════╝ ╚═╝  ╚═══╝ ╚═════╝                     " + CYAN + "         ║" + RESET);
        System.out.println(CYAN + "║" + RESET + "                                                                                                       " + CYAN + "    ║" + RESET);
        System.out.println(CYAN + "║" + RESET + "                         ███████╗██████╗  ██╗  ██╗ ███████╗ ██████╗ ███████╗                   " + CYAN + "            ║" + RESET);
        System.out.println(CYAN + "║" + RESET + "                         ██╔════╝██╔══██╗ ██║  ██║ ██╔════  ██╔══██╗██╔════╝                    " + CYAN + "           ║" + RESET);
        System.out.println(CYAN + "║" + RESET + "                         ███████╗██████╔╝ ███████║ █████╗   ██████╔╝█████╗                     " + CYAN + "            ║" + RESET);
        System.out.println(CYAN + "║" + RESET + "                         ╚════██║██╔═══╝  ██╔══██║ ██╔══╝   ██╔══██╗██╔══╝                    " + CYAN + "             ║" + RESET);
        System.out.println(CYAN + "║" + RESET + "                         ███████║██║      ██║  ██║ ███████╗ ██║  ██║███████╗                 " + CYAN + "              ║" + RESET);
        System.out.println(CYAN + "║" + RESET + "                         ╚══════╝╚═╝      ╚═╝  ╚═╝ ╚══════╝ ╚═╝  ╚═╝╚══════╝                    " + CYAN + "           ║" + RESET);
        System.out.println(CYAN + "║" + RESET + "                                                                                                       " + CYAN + "    ║" + RESET);
        System.out.println(CYAN + "║" + RESET + BRIGHT_YELLOW + "                             A Global Time and Smart Utility Application " + RESET + "                              " + CYAN + "    ║" + RESET);
        System.out.println(CYAN + "║" + RESET + "                                                                                                       " + CYAN + "    ║" + RESET);
        System.out.println(CYAN + "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════╣" + RESET);
        System.out.println(CYAN + "║" + RESET + "                                                                                                           " + CYAN + "║" + RESET);
        System.out.println(CYAN + "║" + RESET +BRIGHT_YELLOW  + "                                      Welcome to ChronoSphere !                            " + CYAN + "                ║" + RESET);
        System.out.println(CYAN + "║" + RESET +BRIGHT_YELLOW + "                          \"Your Gateway to Time Management & Global Utilities\"            " + CYAN + "                 ║" + RESET);
        System.out.println(CYAN + "║" + RESET + "                                                                                                           " + CYAN + "║" + RESET);
        System.out.println(CYAN + "║" + RESET + WHITE + "               World Clock System            Prayer Times              Task Manager                " + CYAN + "        ║" + RESET);
        System.out.println(CYAN + "║" + RESET + WHITE + "               Alarm System                  Event Scheduler           Timer & Stopwatch           " + CYAN + "        ║" + RESET);
        System.out.println(CYAN + "║" + RESET + "                                                                                                           " + CYAN + "║" + RESET);
        System.out.println(CYAN + "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════╣" + RESET);
        System.out.println(CYAN + "║" + RESET + "                                                                                                           " + CYAN + "║" + RESET);
        System.out.println(CYAN + "║" + RESET + "                                         Current System Time:                                              " + CYAN + "║" + RESET);
        System.out.println(CYAN + "║" + RESET + BRIGHT_YELLOW + "                                  [ " + date + " | " + time + " | " + day + " ]" + RESET +"" + CYAN + "                                    ║" + RESET);
        System.out.println(CYAN + "║" + RESET + "                                                                                                           " + CYAN + "║" + RESET);
        System.out.println(CYAN + "╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════╝" + RESET);
        System.out.println();
        System.out.println(CYAN + "                               Press " + BRIGHT_YELLOW + "ENTER" + CYAN + " to continue to the Main Menu..." + RESET);
        
        // Wait for Enter key
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
    
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // ---------------- MAIN MENU ----------------
    public void mainMenu() {
        int choice1;
        do {
            showMainMenu();
            System.out.print(BLUE + "\nEnter your choice: " + WHITE);
            choice1 = input.nextInt();

            switch(choice1) {
                case 1:
                    worldClock.worldClock();
                    break;
                case 2:
                    prayerTime.prayerTimeMenu();
                    break;
                case 3:
                    calendarTask.calendarTaskMenu();
                    break;
                case 4:
                    alarm.alarmMenu();
                    break;
                case 5:
                    utilities.utilitiesMenu();
                    break;
                case 6:
                    System.out.println(YELLOW + "\nExiting Chronosphere..." + RESET);
                    break;
                default:
                    System.out.println(RED + "\nInvalid choice. Try again." + RESET);
            }

        } while(choice1 != 6);
    }

    public void showMainMenu() {
        System.out.println(BLUE + "\n╔═══════════════════════════════════════════════════════════════════╗");
        System.out.println("║" + CYAN + BOLD + "-------------------- CHRONOSPHERE MAIN MENU -----------------------" + BLUE + "║");
        System.out.println("╠═══════════════════════════════════════════════════════════════════╣");
        System.out.println("║" + WHITE + " 1 - World Clock                                                   " + BLUE + "║");
        System.out.println("║" + WHITE + " 2 - Prayer Times (PAKISTAN)                                       " + BLUE + "║");
        System.out.println("║" + WHITE + " 3 - Calendar and Task Planner                                     " + BLUE + "║");
        System.out.println("║" + WHITE + " 4 - Alarms                                                        " + BLUE + "║");
        System.out.println("║" + WHITE + " 5 - Other Utilities                                               " + BLUE + "║");
        System.out.println("║" + WHITE + " 6 - Exit                                                          " + BLUE + "║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════╝" + RESET);
    }
}

class WorldClockModule {
    // ANSI COLOR CODES (copied from main class for consistency)
    private static final String RESET = "\033[0m";
    private static final String BOLD = "\033[1m";
    private static final String DIM = "\033[2m";
    private static final String CYAN = "\033[36m";
    private static final String BLUE = "\033[34m";
    private static final String GREEN = "\033[32m";
    private static final String YELLOW = "\033[33m";
    private static final String RED = "\033[31m";
    private static final String MAGENTA = "\033[35m";
    private static final String WHITE = "\033[37m";
    private static final String BRIGHT_YELLOW = "\033[93m";
    
    private String[] cities = new String[100];    // max 100 cities
    private int cityCount = 0;                   // current number of cities 
    private final String FILE_PATH = "C:\\Users\\User\\OneDrive\\Desktop\\ChronoSphere\\data\\WorldClock.txt";
    
    public void worldClock() {
        Scanner input = new Scanner(System.in);    
        int choice2;   
        
        // Load data once at the start
        loadWorldClockData();

        do{
            System.out.println(BLUE + "\n╔════════════════════════════════════════════════════════════════════╗");
            System.out.println("║" + CYAN + BOLD + "----------------- WORLD CLOCK OPTIONS ------------------------------" + BLUE + "║");
            System.out.println("╠════════════════════════════════════════════════════════════════════╣");
            System.out.println("║" + WHITE + "   1 - Add City                                                     " + BLUE + "║");
            System.out.println("║" + WHITE + "   2 - Delete City                                                  " + BLUE + "║");
            System.out.println("║" + WHITE + "   3 - Show All Cities Time                                         " + BLUE + "║");
            System.out.println("║" + WHITE + "   4 - Back to Main Menu                                            " + BLUE + "║");
            System.out.println("╠════════════════════════════════════════════════════════════════════╣");
            System.out.println("║" + BLUE + "   Enter your choice:                                               " + BLUE + "║");
            System.out.println("╚════════════════════════════════════════════════════════════════════╝" + RESET);
            System.out.print(BLUE + "   Choice: " + WHITE);

            choice2 = input.nextInt();
            input.nextLine(); // Consume newline
            
            switch(choice2) {
                case 1: 
                    addCity();
                    saveWorldClockData();
                    break;
                case 2: 
                    deleteCity();
                    saveWorldClockData();
                    break;
                case 3: 
                    showAllCitiesTime();
                    break;
                case 4:
                    System.out.println(YELLOW + "Exiting World Clock..." + RESET);
                    break;
                default:
                    System.out.println(RED + "Invalid choice. Try again." + RESET);
            }
        } while(choice2 != 4);
    }
    
    public void worldClockMenu() {
        System.out.println(BLUE + "\n╔════════════════════════════════════════════════════════════════════╗");
        System.out.println("║" + CYAN + BOLD + "-------------------- WORLD CLOCK MENU ------------------------------" + BLUE + "║");
        System.out.println("╠════════════════════════════════════════════════════════════════════╣");
        System.out.println("║" + YELLOW + "                            ASIA                                    " + BLUE + "║");
        System.out.println("╠════════════════════════════════════════════════════════════════════╣");
        System.out.println("║" + WHITE + "    A1) Asia/Karachi          A2) Asia/Dubai                        " + BLUE + "║");
        System.out.println("║" + WHITE + "    A3) Asia/Riyadh           A4) Asia/Kolkata                      " + BLUE + "║");
        System.out.println("║" + WHITE + "    A5) Asia/Shanghai         A6) Asia/Tokyo                        " + BLUE + "║");
        System.out.println("║" + WHITE + "    A7) Asia/Singapore        A8) Asia/Tehran                       " + BLUE + "║");
        System.out.println("╠════════════════════════════════════════════════════════════════════╣");
        System.out.println("║" + YELLOW + "                           EUROPE                                   " + BLUE + "║");
        System.out.println("╠════════════════════════════════════════════════════════════════════╣");
        System.out.println("║" + WHITE + "    E1) Europe/London         E2) Europe/Paris                      " + BLUE + "║");
        System.out.println("║" + WHITE + "    E3) Europe/Berlin         E4) Europe/Rome                       " + BLUE + "║");
        System.out.println("║" + WHITE + "    E5) Europe/Madrid         E6) Europe/Moscow                     " + BLUE + "║");
        System.out.println("╠════════════════════════════════════════════════════════════════════╣");
        System.out.println("║" + YELLOW + "                       NORTH AMERICA                                " + BLUE + "║");
        System.out.println("╠════════════════════════════════════════════════════════════════════╣");
        System.out.println("║" + WHITE + "    N1) America/New_York      N2) America/Chicago                   " + BLUE + "║");
        System.out.println("║" + WHITE + "    N3) America/Los_Angeles   N4) America/Toronto                   " + BLUE + "║");
        System.out.println("║" + WHITE + "    N5) America/Vancouver                                           " + BLUE + "║");
        System.out.println("╠════════════════════════════════════════════════════════════════════╣");
        System.out.println("║" + YELLOW + "                       SOUTH AMERICA                                " + BLUE + "║");
        System.out.println("╠════════════════════════════════════════════════════════════════════╣");
        System.out.println("║" + WHITE + "    S1) America/Sao_Paulo     S2) America/Bogota                    " + BLUE + "║");
        System.out.println("║" + WHITE + "    S3) America/Lima          S4) America/Buenos_Aires              " + BLUE + "║");
        System.out.println("╠════════════════════════════════════════════════════════════════════╣");
        System.out.println("║" + YELLOW + "                     OCEANIA / AUSTRALIA                            " + BLUE + "║");
        System.out.println("╠════════════════════════════════════════════════════════════════════╣");
        System.out.println("║" + WHITE + "    O1) Australia/Sydney      O2) Australia/Melbourne               " + BLUE + "║");
        System.out.println("║" + WHITE + "    O3) Pacific/Auckland                                            " + BLUE + "║");
        System.out.println("╠════════════════════════════════════════════════════════════════════╣");
        System.out.println("║" + YELLOW + "                            AFRICA                                  " + BLUE + "║");
        System.out.println("╠════════════════════════════════════════════════════════════════════╣");
        System.out.println("║" + WHITE + "    AF1) Africa/Cairo         AF2) Africa/Johannesburg              " + BLUE + "║");
        System.out.println("║" + WHITE + "    AF3) Africa/Nairobi                                             " + BLUE + "║");
        System.out.println("╠════════════════════════════════════════════════════════════════════╣");
        System.out.println("║" + CYAN + "   0) Enter custom Zone ID                                          " + BLUE + "║");
        System.out.println("║" + WHITE + "     Format: Continent/City (case-sensitive)                        " + BLUE + "║");
        System.out.println("║" + WHITE + "     Example: Asia/Karachi, Europe/London, America/New_York         " + BLUE + "║");
        System.out.println("╚════════════════════════════════════════════════════════════════════╝" + RESET);
    }
    
    public String getZoneId(String code) {
        // function for converting Country-Code to its ZoneId
        switch(code) {
            // Asia
            case "A1": return "Asia/Karachi";
            case "A2": return "Asia/Dubai";
            case "A3": return "Asia/Riyadh";
            case "A4": return "Asia/Kolkata";
            case "A5": return "Asia/Shanghai";
            case "A6": return "Asia/Tokyo";
            case "A7": return "Asia/Singapore";
            case "A8": return "Asia/Tehran";

            // Europe
            case "E1": return "Europe/London";
            case "E2": return "Europe/Paris";
            case "E3": return "Europe/Berlin";
            case "E4": return "Europe/Rome";
            case "E5": return "Europe/Madrid";
            case "E6": return "Europe/Moscow";

            // North America
            case "N1": return "America/New_York";
            case "N2": return "America/Chicago";
            case "N3": return "America/Los_Angeles";
            case "N4": return "America/Toronto";
            case "N5": return "America/Vancouver";

            // South America
            case "S1": return "America/Sao_Paulo";
            case "S2": return "America/Bogota";
            case "S3": return "America/Lima";
            case "S4": return "America/Argentina/Buenos_Aires";

            // Oceania / Australia
            case "O1": return "Australia/Sydney";
            case "O2": return "Australia/Melbourne";
            case "O3": return "Pacific/Auckland";

            // Africa
            case "AF1": return "Africa/Cairo";
            case "AF2": return "Africa/Johannesburg";
            case "AF3": return "Africa/Nairobi";

           // Invalid code
            default: return null; 
        }
    }
    
    // Function for Adding cities in the File
    public void addCity() { 
        worldClockMenu();
        Scanner sc = new Scanner(System.in);
        boolean adding = true;
        
        while (adding) {
            System.out.print(BLUE + "\nEnter city code (e.g., A1, E2) or 0 for custom Zone ID, or X to exit:\n> " + WHITE);
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("X")) {
                adding = false;
                break;
            }
            String zoneId;

            if (input.equals("0")) {
                // custom Zone ID
                System.out.print(BLUE + "Enter custom Zone ID (Continent/City):\n> " + WHITE);
                String customZone = sc.nextLine().trim();
                try {
                    ZoneId.of(customZone); // validate
                    zoneId = customZone;
                } catch (DateTimeException e) {
                    System.out.println(RED + "Invalid Zone ID format. Try again." + RESET);
                    continue;
                }
            } else {
                // convert menu code to ZoneId
                zoneId = getZoneId(input);
                if (zoneId == null) {
                    System.out.println(RED + "Invalid code. Try again." + RESET);
                    continue;
                }
            }
            
            // check for duplicates
            boolean exists = false;
            for (int i = 0; i < cityCount; i++) {
                if (cities[i].equals(zoneId)) {
                    exists = true;
                    break;
                }
            }

            if (exists) {
                System.out.println(YELLOW + zoneId + " is already in the list." + RESET);
                continue;
            }

            // add city to array
            if (cityCount < cities.length) {
                cities[cityCount] = zoneId;
                cityCount++;
                System.out.println(GREEN + zoneId + " added successfully." + RESET);
            } else {
                System.out.println(RED + "City list is full!" + RESET);
            }
        }
    }
    
    // Function for Deleting cities from the File
    public void deleteCity() { 
        if (cityCount == 0) {
            System.out.println(YELLOW + "No cities to delete." + RESET);
            return;
        }
        
        Scanner sc = new Scanner(System.in);
        boolean deleting = true;

        while (deleting) {
            // Display current cities
            System.out.println(CYAN + "\nCurrent cities:" + RESET);
            for (int i = 0; i < cityCount; i++) {
                System.out.println(WHITE + (i + 1) + ") " + BLUE + cities[i] + RESET);
            }
            
            System.out.print(BLUE + "\nEnter city number to delete (1-" + cityCount + "), or X to exit:\n> " + WHITE);
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("X")) {
                deleting = false;
                break;
            }

            // Try to parse as number
            try {
                int cityNum = Integer.parseInt(input);
                
                if (cityNum < 1 || cityNum > cityCount) {
                    System.out.println(RED + "Invalid city number. Please enter 1-" + cityCount + RESET);
                    continue;
                }
                
                // Remove the city
                String deletedCity = cities[cityNum - 1];
                
                // Shift remaining elements left
                for (int i = cityNum - 1; i < cityCount - 1; i++) {
                    cities[i] = cities[i + 1];
                }
                cities[cityCount - 1] = null;
                cityCount--;
                
                System.out.println(GREEN + deletedCity + " deleted successfully." + RESET);
                
                // Show remaining cities or exit if none left
                if (cityCount == 0) {
                    System.out.println(YELLOW + "All cities have been removed." + RESET);
                    deleting = false;
                }
                
            } catch (NumberFormatException e) {
                System.out.println(RED + "Please enter a valid number or X to exit." + RESET);
            }
        }
    }
    
    // Function for Showing the time for all the user selected cities
    public void showAllCitiesTime() {
        if (cityCount == 0) {
            System.out.println(YELLOW + "No cities added yet." + RESET);
            return;
        }

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        System.out.println(CYAN + BOLD + "\n╔════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                     LIVE WORLD CLOCK - GLOBAL TIME                 ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════╝" + RESET);
        System.out.println(YELLOW + "Press Enter to exit live view..." + RESET);
        System.out.println();

        // Print initial blank lines for all cities
        for (int i = 0; i < cityCount; i++) {
            System.out.println();
        }
        
        // Small delay for better UX
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            // Ignore
        }
        
        // Fixed column widths for consistent layout
        final int CITY_COLUMN_WIDTH = 38;    // Width for city names
        final int ARROW_WIDTH = 3;           // Width for arrow (space + → + space)
        final int DATE_TIME_WIDTH = 24;      // Width for date-time (dd-MM-yyyy   HH:mm:ss a)
        
        while (running) {
            // Move cursor up by cityCount + 1 lines (just the cities)
            System.out.print(String.format("\033[%dA", cityCount));

            // Display all cities with consistent spacing
            for (int i = 0; i < cityCount; i++) {
                try {
                    ZoneId zone = ZoneId.of(cities[i]);
                    ZonedDateTime time = ZonedDateTime.now(zone);
                    
                    // Format date and time with exactly 3 spaces between
                    String dateStr = time.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    String timeStr = time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                    String amPm = time.format(DateTimeFormatter.ofPattern("a"));
                    String formattedDateTime = dateStr + "   " + timeStr + " " + amPm;
                    
                    // Pad date-time to fixed width
                    if (formattedDateTime.length() < DATE_TIME_WIDTH) {
                        formattedDateTime = formattedDateTime + " ".repeat(DATE_TIME_WIDTH - formattedDateTime.length());
                    }
                    
                    // Determine color based on region (NO ICONS)
                    String regionColor = BLUE;
                    
                    if (cities[i].startsWith("Asia/")) {
                        regionColor = CYAN;
                    } else if (cities[i].startsWith("Europe/")) {
                        regionColor = MAGENTA;
                    } else if (cities[i].startsWith("America/")) {
                        regionColor = GREEN;
                    } else if (cities[i].startsWith("Australia/") || cities[i].startsWith("Pacific/")) {
                        regionColor = YELLOW;
                    } else if (cities[i].startsWith("Africa/")) {
                        regionColor = RED;
                    }
                    
                    // Format the city name (NO ICONS)
                    String displayCity = cities[i].replace("_", " ");
                    
                    // Truncate if too long
                    if (displayCity.length() > CITY_COLUMN_WIDTH) {
                        displayCity = displayCity.substring(0, CITY_COLUMN_WIDTH - 3) + "...";
                    }
                    
                    // Pad city name to fixed width
                    if (displayCity.length() < CITY_COLUMN_WIDTH) {
                        displayCity = displayCity + " ".repeat(CITY_COLUMN_WIDTH - displayCity.length());
                    }
                    
                    // Display with consistent spacing (NO ICONS)
                    System.out.println(regionColor + "  " + displayCity + 
                                     WHITE + " → " + 
                                     WHITE + formattedDateTime + RESET);
                    
                } catch (DateTimeException e) {
                    // Format city name for error display (NO ICONS)
                    String displayCity = cities[i];
                    
                    // Truncate if too long
                    if (displayCity.length() > CITY_COLUMN_WIDTH) {
                        displayCity = displayCity.substring(0, CITY_COLUMN_WIDTH - 3) + "...";
                    }
                    
                    // Pad city name to fixed width
                    if (displayCity.length() < CITY_COLUMN_WIDTH) {
                        displayCity = displayCity + " ".repeat(CITY_COLUMN_WIDTH - displayCity.length());
                    }
                    
                    // Error message with same width as date-time
                    String errorMessage = "INVALID ZONE";
                    String paddedErrorMessage = errorMessage;
                    if (paddedErrorMessage.length() < DATE_TIME_WIDTH) {
                        paddedErrorMessage = paddedErrorMessage + 
                            " ".repeat(DATE_TIME_WIDTH - paddedErrorMessage.length());
                    }
                    
                    System.out.println(RED + "  " + displayCity + 
                                     WHITE + " → " + 
                                     RED + paddedErrorMessage + RESET);
                }
            }

            // Check if user pressed Enter (non-blocking check)
            try {
                if (System.in.available() > 0) {
                    int input = System.in.read();
                    if (input == 10 || input == 13) { // Enter key
                        // Consume any remaining input
                        while (System.in.available() > 0) {
                            System.in.read();
                        }
                        running = false;
                        System.out.println(YELLOW + "\nExiting live view..." + RESET);
                        break;
                    }
                }
                Thread.sleep(1000); // update every second
            } catch (IOException | InterruptedException e) {
                System.out.println(RED + "\nError in live clock: " + e.getMessage() + RESET);
                running = false;
            }
        }
        
        // Clear input buffer after exiting
        try {
            while (System.in.available() > 0) {
                System.in.read();
            }
        } catch (IOException e) {
            // Ignore
        }
    }
    
    // Function for loading the data from file to the memory (array)
    public void loadWorldClockData() {
        File file = new File(FILE_PATH);
        cityCount = 0; // reset the count
        
        // Create file if it doesn't exist
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs(); // Create data directory if needed
                file.createNewFile();
                System.out.println(GREEN + "Created new WorldClock.txt file." + RESET);
            } catch (IOException e) {
                System.out.println(RED + "Error creating file: " + e.getMessage() + RESET);
                return;
            }
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null && cityCount < cities.length) {
                String trimmedLine = line.trim();
                if (!trimmedLine.isEmpty()) { // Skip empty lines
                    cities[cityCount] = trimmedLine;
                    cityCount++;
                }
            }
            if (cityCount > 0) {
                System.out.println(GREEN + "Loaded " + cityCount + " cities from file." + RESET);
            }
        } catch (IOException e) {
            System.out.println(RED + "Error loading data: " + e.getMessage() + RESET);
        }
    }
    
    // Function for Saving all the changes done by the user
    public void saveWorldClockData() {
        File file = new File(FILE_PATH);

        try {
            file.getParentFile().mkdirs(); // Ensure data directory exists
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < cityCount; i++) {
                bw.write(cities[i]);
                bw.newLine();
            }
            bw.close();
            System.out.println(GREEN + "Saved " + cityCount + " cities to file." + RESET);
        } catch (IOException e) {
            System.out.println(RED + "Error saving data: " + e.getMessage() + RESET);
        }
    }
}

class PrayerTimeModule {
    // ANSI COLOR CODES
    private static final String RESET = "\033[0m";
    private static final String BOLD = "\033[1m";
    private static final String DIM = "\033[2m";
    private static final String CYAN = "\033[36m";
    private static final String BLUE = "\033[34m";
    private static final String GREEN = "\033[32m";
    private static final String YELLOW = "\033[33m";
    private static final String RED = "\033[31m";
    private static final String MAGENTA = "\033[35m";
    private static final String WHITE = "\033[37m";
    
    public void prayerTimeMenu() {
        Scanner sc = new Scanner(System.in);
        
        // Load prayer data into HashMap
        HashMap<String, String[]> prayerData = loadPrayerData();
        if (prayerData == null || prayerData.isEmpty()) {
            System.out.println(RED + "Prayer times data not available." + RESET);
            return;
        }
        
        int cityChoice;
        String selectedCity = "";
        
        do {
            // Show city menu
            System.out.println(CYAN + "\n╔════════════════════════════════════════════════════════════════════╗");
            System.out.println("║" + BOLD + "               PRAYER TIMES - PAKISTAN CITIES                       " + CYAN + "║");
            System.out.println("║" + YELLOW + "                    (January 2026 Data)                            " + CYAN + " ║");
            System.out.println("╠════════════════════════════════════════════════════════════════════╣");
            System.out.println("║" + WHITE + "   1. Karachi         2. Lahore         3. Islamabad                " + CYAN + "║");
            System.out.println("║" + WHITE + "   4. Rawalpindi      5. Faisalabad     6. Multan                   " + CYAN + "║");
            System.out.println("║" + WHITE + "   7. Peshawar        8. Quetta         9. Sialkot                  " + CYAN + "║");
            System.out.println("╠════════════════════════════════════════════════════════════════════╣");
            System.out.println("║" + BLUE + "   0. Exit to Main Menu                                             " + CYAN + "║");
            System.out.println("╚════════════════════════════════════════════════════════════════════╝" + RESET);
            System.out.print(BLUE + "\nEnter city number (0-9): " + WHITE);
            
            cityChoice = sc.nextInt();
            sc.nextLine(); // Consume newline
            
            // Map choice to city name
            switch (cityChoice) {
                case 1: selectedCity = "Karachi"; break;
                case 2: selectedCity = "Lahore"; break;
                case 3: selectedCity = "Islamabad"; break;
                case 4: selectedCity = "Rawalpindi"; break;
                case 5: selectedCity = "Faisalabad"; break;
                case 6: selectedCity = "Multan"; break;
                case 7: selectedCity = "Peshawar"; break;
                case 8: selectedCity = "Quetta"; break;
                case 9: selectedCity = "Sialkot"; break;
                case 0: return;
                default:
                    System.out.println(RED + "Invalid choice! Try again." + RESET);
                    continue;
            }
            
            // Ask for date
            System.out.println(BLUE + "\n╔═══════════════════════════════════════════════════════════════════╗");
            System.out.println("║" + CYAN + BOLD + "                 DATE SELECTION - JANUARY 2026                    " + BLUE + " ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════╝" + RESET);
            System.out.print(BLUE + "\n> " + WHITE);
            
            int day = sc.nextInt();
            sc.nextLine(); // Consume newline
            
            if (day < 1 || day > 31) {
                System.out.println(RED + "Invalid day! Must be 1-31." + RESET);
                continue;
            }
            
            // Create date string in format "2026-01-DD"
            String date = String.format("2026-01-%02d", day);
            
            // Create lookup key
            String key = selectedCity + "|" + date;
            
            // Get prayer times from HashMap
            String[] times = prayerData.get(key);
            
            if (times == null) {
                System.out.println(YELLOW + "Prayer times not found for " + selectedCity + " on " + date + RESET);
            } else {
                // Display prayer times
                System.out.println(CYAN + "\n╔════════════════════════════════════════════════════════════════════╗");
                System.out.println("" + BOLD + "                 PRAYER TIMES FOR " + selectedCity.toUpperCase() + "    " + CYAN   );
                System.out.println("╠════════════════════════════════════════════════════════════════════╣");
                System.out.println("" + YELLOW + "                     Date: " + date + "     " + CYAN     );
                System.out.println("╚════════════════════════════════════════════════════════════════════╝" + RESET);
                System.out.println(BLUE + "┌─────────────────────────────────────┐" + RESET);
                System.out.printf(BLUE + "│" + CYAN + "  Fajr    " + BLUE + "│ " + WHITE + "%-25s" + BLUE + "│\n" + RESET, times[0]);
                System.out.println(BLUE + "├─────────────────────────────────────┤" + RESET);
                System.out.printf(BLUE + "│" + CYAN + "  Sunrise " + BLUE + "│ " + WHITE + "%-25s" + BLUE + "│\n" + RESET, times[1]);
                System.out.println(BLUE + "├─────────────────────────────────────┤" + RESET);
                System.out.printf(BLUE + "│" + CYAN + "  Dhuhr   " + BLUE + "│ " + WHITE + "%-25s" + BLUE + "│\n" + RESET, times[2]);
                System.out.println(BLUE + "├─────────────────────────────────────┤" + RESET);
                System.out.printf(BLUE + "│" + CYAN + "  Asr     " + BLUE + "│ " + WHITE + "%-25s" + BLUE + "│\n" + RESET, times[3]);
                System.out.println(BLUE + "├─────────────────────────────────────┤" + RESET);
                System.out.printf(BLUE + "│" + CYAN + "  Maghrib " + BLUE + "│ " + WHITE + "%-25s" + BLUE + "│\n" + RESET, times[4]);
                System.out.println(BLUE + "├─────────────────────────────────────┤" + RESET);
                System.out.printf(BLUE + "│" + CYAN + "  Isha    " + BLUE + "│ " + WHITE + "%-25s" + BLUE + "│\n" + RESET, times[5]);
                System.out.println(BLUE + "└─────────────────────────────────────┘" + RESET);
            }
            
            // Show current prayer status
            showCurrentPrayerStatus(times);
            
            // Ask for live countdown
            System.out.print(BLUE + "\nStart live countdown? (Y/N): " + WHITE);
            String liveChoice = sc.nextLine();
            if (liveChoice.equalsIgnoreCase("Y")) {
                showLiveCountdown(times); // Fixed: Don't pass Scanner
            }
            
            // Ask if user wants to check another date
            System.out.print(BLUE + "\nCheck another date? (Y/N): " + WHITE);
            String again = sc.nextLine();
            if (!again.equalsIgnoreCase("Y")) {
                break;
            }
        } while (true);
        
    }
    
    // Function for loading prayer times from the file  
    public HashMap<String, String[]> loadPrayerData() {
        HashMap<String, String[]> prayerData = new HashMap<>();
        File file = new File("C:\\Users\\User\\OneDrive\\Desktop\\ChronoSphere\\data\\PrayerTimesPAK.csv");
        
        if (!file.exists()) {
            System.out.println(RED + "Prayer times file not found: data/PrayerTimesPAK.csv" + RESET);
            System.out.println(YELLOW + "Please make sure the 'data' folder exists with PrayerTimesPAK.csv file." + RESET);
            return prayerData;
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean firstLine = true; // Skip header
            
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Skip header line
                }
                
                // Split CSV line
                String[] values = line.split(",");
                
                if (values.length >= 8) {
                    String city = values[0];
                    String date = values[1];
                    String fajr = values[2];
                    String sunrise = values[3];
                    String dhuhr = values[4];
                    String asr = values[5];
                    String maghrib = values[6];
                    String isha = values[7];
                    
                    // Create key and value array
                    String key = city + "|" + date;
                    String[] times = {fajr, sunrise, dhuhr, asr, maghrib, isha};
                    
                    // Store in HashMap
                    prayerData.put(key, times);
                }
            }
            
            System.out.println(GREEN + "Loaded prayer times for the cities of Pakistan." + RESET);
            
        } catch (IOException e) {
            System.out.println(RED + "Error reading prayer times file: " + e.getMessage() + RESET);
        }
        
        return prayerData;
    }

    // Method to find current and next prayer 
    public void showCurrentPrayerStatus(String[] times) {
        String[] prayerNames = {"Fajr", "Sunrise", "Dhuhr", "Asr", "Maghrib", "Isha"};
        LocalTime now = LocalTime.now();
        
        // Find current prayer
        String currentPrayer = "None";
        LocalTime nextPrayerTime = null;
        String nextPrayerName = "";
        
        // Handle midnight case (after Isha, before Fajr next day)
        LocalTime firstPrayer = LocalTime.parse(times[0]);
        LocalTime lastPrayer = LocalTime.parse(times[5]);
        
        if (now.isBefore(firstPrayer)) {
            // Between midnight and Fajr
            currentPrayer = prayerNames[5] + " (from yesterday)";
            nextPrayerTime = firstPrayer;
            nextPrayerName = prayerNames[0];
        } else {
            // Normal daytime
            for (int i = 0; i < times.length; i++) {
                LocalTime prayerTime = LocalTime.parse(times[i]);
                
                // Check if it's the current prayer (between this prayer and next)
                if (i < times.length - 1) {
                    LocalTime nextTime = LocalTime.parse(times[i + 1]);
                    if (now.isAfter(prayerTime) && now.isBefore(nextTime)) {
                        currentPrayer = prayerNames[i];
                    }
                } else if (now.isAfter(prayerTime)) {
                    // After last prayer (Isha)
                    currentPrayer = prayerNames[i];
                }
                
                // Find next prayer
                if (nextPrayerTime == null && prayerTime.isAfter(now)) {
                    nextPrayerTime = prayerTime;
                    nextPrayerName = prayerNames[i];
                }
            }
            
            // If no next prayer found, use first prayer tomorrow
            if (nextPrayerTime == null) {
                nextPrayerTime = LocalTime.parse(times[0]);
                nextPrayerName = prayerNames[0] + " (tomorrow)";
            }
        }
        
        // Calculate countdown - FIXED Duration usage
        long secondsBetween = now.until(nextPrayerTime, ChronoUnit.SECONDS);
        if (secondsBetween < 0) {
            secondsBetween += 24 * 3600; // Add 24 hours if negative
        }
        
        long hours = secondsBetween / 3600;
        long minutes = (secondsBetween % 3600) / 60;
        long seconds = secondsBetween % 60;
        
        // Display status
        System.out.println(BLUE + "\n╔═══════════════════════════════════════════╗");
        System.out.println("║" + CYAN + BOLD + "          CURRENT PRAYER STATUS            " + BLUE + "║");
        System.out.println("╠═══════════════════════════════════════════╣" + RESET);
        System.out.printf(BLUE + "║" + WHITE + "  Current Prayer: " + CYAN + "%-24s " + BLUE + "║\n" + RESET, currentPrayer);
        System.out.printf(BLUE + "║" + WHITE + "  Next Prayer:    " + CYAN + "%-24s " + BLUE + "║\n" + RESET, nextPrayerName);
        System.out.printf(BLUE + "║" + WHITE + "  Time:           " + YELLOW + "%-24s " + BLUE + "║\n" + RESET, nextPrayerTime);
        System.out.printf(BLUE + "║" + WHITE + "  Time Left:      " + GREEN + "%02d:%02d:%02d                 " + BLUE + "║\n" + RESET, hours, minutes, seconds);
        System.out.println(BLUE + "╚═══════════════════════════════════════════╝" + RESET);
    }

    // LIVE COUNTDOWN METHOD 
    public void showLiveCountdown(String[] times) {
        String[] prayerNames = {"Fajr", "Sunrise", "Dhuhr", "Asr", "Maghrib", "Isha"};
        
        System.out.println(BLUE + "\n╔═══════════════════════════════════════════╗");
        System.out.println("║" + CYAN + BOLD + "    LIVE COUNTDOWN - Press Enter to stop   " + BLUE + "║");
        System.out.println("╠═══════════════════════════════════════════╣" + RESET);
        
        try {
            // Clear input buffer before starting
            clearInputBuffer();
            
            boolean running = true;
            long startTime = System.currentTimeMillis();
            
            while (running) {
                LocalTime now = LocalTime.now();
                LocalTime nextPrayerTime = null;
                String nextPrayerName = "";
                
                // Find next prayer
                for (int i = 0; i < times.length; i++) {
                    LocalTime prayerTime = LocalTime.parse(times[i]);
                    if (prayerTime.isAfter(now)) {
                        nextPrayerTime = prayerTime;
                        nextPrayerName = prayerNames[i];
                        break;
                    }
                }
                
                // If no prayer found for today
                if (nextPrayerTime == null) {
                    nextPrayerTime = LocalTime.parse(times[0]);
                    nextPrayerName = prayerNames[0] + " (tomorrow)";
                }
                
                // Calculate countdown - FIXED Duration usage
                long secondsBetween = now.until(nextPrayerTime, ChronoUnit.SECONDS);
                if (secondsBetween < 0) {
                    secondsBetween += 24 * 3600; // Add 24 hours if negative
                }
                
                long hours = secondsBetween / 3600;
                long minutes = (secondsBetween % 3600) / 60;
                long seconds = secondsBetween % 60;
                
                // Clear line and display countdown
                System.out.print("\r " + CYAN + nextPrayerName + ": " + GREEN + 
                               String.format("%02d:%02d:%02d", hours, minutes, seconds) + 
                               YELLOW + " left       " + RESET);
                
                // Check if countdown reached zero
                if (secondsBetween <= 0) {
                    System.out.println("\n\n" + GREEN + "Time for " + nextPrayerName + "!" + RESET);
                    running = false;
                    break;
                }
                
                // Check if Enter was pressed
                if ((System.currentTimeMillis() - startTime) % 500 < 50) {
                    if (System.in.available() > 0) {
                        int input = System.in.read();
                        // Check if it's Enter key (10 = \n, 13 = \r)
                        if (input == 10 || input == 13) {
                            running = false;
                            // Consume any remaining newline characters
                            clearInputBuffer();
                            break;
                        }
                    }
                }
                
                Thread.sleep(1000); 
            }
            
            System.out.println(BLUE + "\n╔═══════════════════════════════════════════╗");
            System.out.println("║" + YELLOW + "          LIVE COUNTDOWN STOPPED           " + BLUE + "║");
            System.out.println("╚═══════════════════════════════════════════╝" + RESET);
            
            // Clear buffer after stopping
            clearInputBuffer();
            
        } catch (Exception e) {
            System.out.println(RED + "\nCountdown stopped." + RESET);
        }
    }
    
    // Method to input buffer
    private void clearInputBuffer() {
        try {
            // Small delay to ensure all input is available
            Thread.sleep(50);
            while (System.in.available() > 0) {
                System.in.read();
            }
        } catch (Exception e) {
            // Ignore exception
         }
    }
}

class CalendarTaskModule {
    //ANSI COLOR CODES
    private static final String RESET = "\033[0m";
    private static final String BOLD = "\033[1m";
    private static final String DIM = "\033[2m";
    private static final String CYAN = "\033[36m";
    private static final String BLUE = "\033[34m";
    private static final String GREEN = "\033[32m";
    private static final String YELLOW = "\033[33m";
    private static final String RED = "\033[31m";
    private static final String MAGENTA = "\033[35m";
    private static final String WHITE = "\033[37m";
    
    private FileHelper fileHelper = new FileHelper();
    
    public void calendarTaskMenu() {
        Scanner input = new Scanner(System.in);
        int choice;
        do {
            System.out.println(BLUE + "\n╔════════════════════════════════════════════════════════════════════╗");
            System.out.println("║" + CYAN + BOLD + "                    CALENDAR AND TASK MENU                          " + BLUE + "║");
            System.out.println("╠════════════════════════════════════════════════════════════════════╣");
            System.out.println("║" + WHITE + "   1. Calendar                                                      " + BLUE + "║");
            System.out.println("║" + WHITE + "   2. Task Manager                                                  " + BLUE + "║");
            System.out.println("║" + WHITE + "   3. Events                                                        " + BLUE + "║");
            System.out.println("║" + WHITE + "   4. Exit to Main Menu                                             " + BLUE + "║");
            System.out.println("╚════════════════════════════════════════════════════════════════════╝" + RESET);
            System.out.print(BLUE + "\nEnter your choice (1-4): " + WHITE);
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1: showCalendar(); break;
                case 2: TaskManager(); break;
                case 3: events(); break;
                case 4: System.out.println(YELLOW + "Exiting Calendar and Task Menu..." + RESET); break;
                default: System.out.println(RED + "Invalid choice! Try again." + RESET);
            }
        } while (choice != 4);
    }
    
    public void showCalendar() {
        Scanner input = new Scanner(System.in);

        System.out.println(CYAN + "\n╔═══════════════════════════════════════════╗");
        System.out.println("║" + BOLD + "                 CALENDAR                  " + CYAN + "║");
        System.out.println("╚═══════════════════════════════════════════╝" + RESET);

        try {
            // After showing calendar, show pending tasks and events
            String tasksFile = "C:\\Users\\User\\OneDrive\\Desktop\\ChronoSphere\\data\\TasksManager.txt";
            String eventsFile = "C:\\Users\\User\\OneDrive\\Desktop\\ChronoSphere\\data\\events.txt";

            List<String> tasks = fileHelper.readFileToList(tasksFile);
            List<String> events = fileHelper.readFileToList(eventsFile);

            // Tasks Box - COMPLETELY CLOSED
            System.out.println(CYAN + "   ┌─────────────────────────────────────┐");
            System.out.println("   │" + BOLD + "          PENDING TASKS              " + CYAN + "│");
            System.out.println("   ├─────────────────────────────────────┤" + RESET);
            
            if (tasks.isEmpty()) {
                System.out.println(CYAN + "   │" + YELLOW + "        No tasks found.                " + CYAN +  " │" + RESET);
                System.out.println(CYAN + "   └─────────────────────────────────────┘" + RESET);
            } else {
                for (int i = 0; i < tasks.size(); i++) {
                    String task = tasks.get(i);
                    if (task.length() > 30) {
                        task = task.substring(0, 27) + "...";
                    }
                    System.out.printf(CYAN + "   │" + WHITE + " %2d. " + BLUE + "%-30s" + CYAN +  "  │\n" + RESET, 
                                    i + 1, task);
                }
                System.out.println(CYAN + "   └─────────────────────────────────────┘" + RESET);
            }

            // Events Box - COMPLETELY CLOSED
            System.out.println(CYAN + "\n   ┌─────────────────────────────────────┐");
            System.out.println("   │" + BOLD + "        SCHEDULED EVENTS             " + CYAN + "│");
            System.out.println("   ├─────────────────────────────────────┤" + RESET);
            
            if (events.isEmpty()) {
                System.out.println(CYAN + "   │" + YELLOW + "       No events found.                " + CYAN +  " │" + RESET);
                System.out.println(CYAN + "   └─────────────────────────────────────┘" + RESET);
            } else {
                for (int i = 0; i < events.size(); i++) {
                    String event = events.get(i);
                    if (event.length() > 30) {
                        event = event.substring(0, 27) + "...";
                    }
                    System.out.printf(CYAN + "   │" + WHITE + " %2d. " + MAGENTA + "%-30s" + CYAN +  "  │\n" + RESET, 
                                    i + 1, event);
                }
                System.out.println(CYAN + "   └─────────────────────────────────────┘" + RESET);
            }

            input.nextLine(); // clear buffer

            // Ask user if tasks are done
            if (!tasks.isEmpty()) {
                System.out.print(BLUE + "\n   Mark task as completed? (yes/no): " + WHITE);
                String ans = input.nextLine().trim().toLowerCase();
                if (ans.equals("yes") || ans.equals("y")) {
                    System.out.print(BLUE + "   Enter task number to delete: " + WHITE);
                    try {
                        int taskNum = Integer.parseInt(input.nextLine().trim());
                        if (taskNum >= 1 && taskNum <= tasks.size()) {
                            String target = tasks.get(taskNum - 1);
                            fileHelper.deleteFileLine(tasksFile, target);
                            System.out.println(GREEN + "   Task deleted successfully." + RESET);
                        } else {
                            System.out.println(RED + "    Invalid task number." + RESET);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(RED + "   Please enter a valid number." + RESET);
                    }
                }
            }

            // Ask user if events are done
            if (!events.isEmpty()) {
                System.out.print(BLUE + "\n   Mark event as completed? (yes/no): " + WHITE);
                String ans2 = input.nextLine().trim().toLowerCase();
                if (ans2.equals("yes") || ans2.equals("y")) {
                    System.out.print(BLUE + "   Enter event number to delete: " + WHITE);
                    try {
                        int eventNum = Integer.parseInt(input.nextLine().trim());
                        if (eventNum >= 1 && eventNum <= events.size()) {
                            String target = events.get(eventNum - 1);
                            fileHelper.deleteFileLine(eventsFile, target);
                            System.out.println(GREEN + "    Event deleted successfully." + RESET);
                        } else {
                            System.out.println(RED + "    Invalid event number." + RESET);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(RED + "    Please enter a valid number." + RESET);
                    }
                }
            }
            
        } catch (Exception e) {
            System.out.println(RED + "   Error displaying calendar: " + e.getMessage() + RESET);
        }
    }

    // Task & Events menus
    public void events() {
        System.out.println(CYAN + "\n╔═══════════════════════════════════════════╗");
        System.out.println("║" + BOLD + "                 EVENTS                    " + CYAN + "║");
        System.out.println("╚═══════════════════════════════════════════╝" + RESET);

        Scanner input = new Scanner(System.in);
        int choice;
        do {
            System.out.println(BLUE + "\n╔═══════════════════════════════════════════╗");
            System.out.println("║" + CYAN + BOLD + "              TASK MANAGER MENU            " + BLUE + "║");
            System.out.println("╠═══════════════════════════════════════════╣");
            System.out.println("║" + WHITE + "   1. Add Events                           " + BLUE + "║");
            System.out.println("║" + WHITE + "   2. View Events                          " + BLUE + "║");
            System.out.println("║" + WHITE + "   3. Delete Events                        " + BLUE + "║");
            System.out.println("║" + WHITE + "   4. Exit                                 " + BLUE + "║");
            System.out.println("╚═══════════════════════════════════════════╝" + RESET);
            System.out.print(BLUE + "\nEnter your choice (1-4): " + WHITE);
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1: addEvent(); break;
                case 2: viewEvents(); break;
                case 3: deleteEvents(); break;
                case 4: System.out.println(BLUE + "\n╔═══════════════════════════════════════════╗");
                        System.out.println("║" + YELLOW + "         EXITING TASK MANAGER              " + BLUE + "║");
                        System.out.println("╚═══════════════════════════════════════════╝" + RESET);
                        break;
                default: System.out.println(BLUE + "\n╔═══════════════════════════════════════════╗");
                         System.out.println("║" + RED + "          INVALID CHOICE!                  " + BLUE + "║");
                         System.out.println("╚═══════════════════════════════════════════╝" + RESET);
            }
        } while (choice != 4);
    }

    public void TaskManager() {
        System.out.println(CYAN + "\n╔═══════════════════════════════════════════╗");
        System.out.println("║" + BOLD + "          TASK MANAGER                     " + CYAN + "║");
        System.out.println("╚═══════════════════════════════════════════╝" + RESET);

        Scanner input = new Scanner(System.in);
        int choice;
        do {
            System.out.println(BLUE + "\n╔═══════════════════════════════════════════╗");
            System.out.println("║" + CYAN + BOLD + "          CALENDAR TASK MANAGER            " + BLUE + "║");
            System.out.println("╠═══════════════════════════════════════════╣");
            System.out.println("║" + WHITE + "   1. Add Task                             " + BLUE + "║");
            System.out.println("║" + WHITE + "   2. View Tasks                           " + BLUE + "║");
            System.out.println("║" + WHITE + "   3. Delete Task                          " + BLUE + "║");
            System.out.println("║" + WHITE + "   4. Exit                                 " + BLUE + "║");
            System.out.println("╚═══════════════════════════════════════════╝" + RESET);
            System.out.print(BLUE + "\nEnter your choice (1-4): " + WHITE);
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1: addTask(); break;
                case 2: viewTasks(); break;
                case 3: deleteTask(); break;
                case 4: System.out.println(BLUE + "\n╔═══════════════════════════════════════════╗");
                        System.out.println("║" + YELLOW + "       EXITING TASK MANAGER                " + BLUE + "║");
                        System.out.println("╚═══════════════════════════════════════════╝" + RESET);
                        break;
                default: System.out.println(BLUE + "\n╔═══════════════════════════════════════════╗");
                         System.out.println("║" + RED + "         INVALID CHOICE!                   " + BLUE + "║");
                         System.out.println("╚═══════════════════════════════════════════╝" + RESET);
            }
        } while (choice != 4);
    }

    // ===== Tasks (file: TasksManager.txt) =====
    public void addTask() {
        Scanner input = new Scanner(System.in);
        String fileName = "C:\\Users\\User\\OneDrive\\Desktop\\ChronoSphere\\data\\TasksManager.txt";
        fileHelper.createFileIfMissing1(fileName);

        do {
            System.out.print(BLUE + "Enter your tasks YOU want to complete in upcoming days:\n> " + WHITE);
            String line1 = input.nextLine();
            fileHelper.appendFile1(fileName, line1);
            System.out.println(GREEN + "Added Successfully!" + RESET);
            
            System.out.print(BLUE + "Do You want to add more tasks? (1 for Yes, 0 for No)\n> " + WHITE);
            int n = input.nextInt();
            input.nextLine();
            if (n != 1) break;
        } while(true);
    }

    public void viewTasks() {
        System.out.println(YELLOW + "Wait! , Working on it ..." + RESET);
        String fileName = "C:\\Users\\User\\OneDrive\\Desktop\\ChronoSphere\\data\\TasksManager.txt";
        fileHelper.readFile(fileName);
    }

    public void deleteTask() {
        Scanner input = new Scanner(System.in);
        System.out.print(BLUE + "Enter which task you want to delete (enter full exact line):\n> " + WHITE);
        String line = input.nextLine();
        String fileName = "C:\\Users\\User\\OneDrive\\Desktop\\ChronoSphere\\data\\TasksManager.txt";
        fileHelper.deleteFileLine(fileName, line);
    }

    // ===== Events (file: events.txt) =====
    public void addEvent() {
        Scanner input = new Scanner(System.in);
        String FileName = "C:\\Users\\User\\OneDrive\\Desktop\\ChronoSphere\\data\\events.txt";
        fileHelper.createFileIfMissing1(FileName);

        do {
            System.out.print(BLUE + "Enter event description:\n> " + WHITE);
            String line2 = input.nextLine();
            fileHelper.appendFile2(FileName, line2);
            System.out.println(GREEN + "Added Successfully!" + RESET);
            
            System.out.print(BLUE + "Do You want to add more events? (1 for Yes, 0 for No)\n> " + WHITE);
            int n = input.nextInt();
            input.nextLine();
            if (n != 1) break;
        } while(true);
    }

    public void viewEvents() {
        System.out.println(YELLOW + "Wait! , Working on it ..." + RESET);
        String FileName = "C:\\Users\\User\\OneDrive\\Desktop\\ChronoSphere\\data\\events.txt";
        fileHelper.readFile(FileName);
    }

    public void deleteEvents() {
        Scanner input = new Scanner(System.in);
        System.out.print(BLUE + "Enter which event you want to delete (enter full exact line):\n> " + WHITE);
        String line2 = input.nextLine();
        String FileName = "C:\\Users\\User\\OneDrive\\Desktop\\ChronoSphere\\data\\events.txt";
        fileHelper.deleteFileLine(FileName, line2);
    }
}

class AlarmModule {
    // ANSI COLOR CODES
    private static final String RESET = "\033[0m";
    private static final String BOLD = "\033[1m";
    private static final String DIM = "\033[2m";
    private static final String CYAN = "\033[36m";
    private static final String BLUE = "\033[34m";
    private static final String GREEN = "\033[32m";
    private static final String YELLOW = "\033[33m";
    private static final String RED = "\033[31m";
    private static final String MAGENTA = "\033[35m";
    private static final String WHITE = "\033[37m";
    
    private FileHelper fileHelper = new FileHelper();
    private static volatile boolean alarmRinging = false;
    private static volatile Thread currentSoundThread = null;
    private static Map<String, String> alarmStatus = new HashMap<>(); // Track alarm statuses
    private static String currentRingingAlarm = null; // Track which alarm is currently ringing
    
    public void alarmMenu() {
        Scanner input = new Scanner(System.in);
        int choice;
        do {
            System.out.println(BLUE + "\n╔═══════════════════════════════════════════╗");
            System.out.println("║" + CYAN + BOLD + "               ALARM MENU                  " + BLUE + "║");
            System.out.println("╠═══════════════════════════════════════════╣");
            System.out.println("║" + WHITE + "   1. Add Alarm                            " + BLUE + "║");
            System.out.println("║" + WHITE + "   2. View Alarms                          " + BLUE + "║");
            System.out.println("║" + WHITE + "   3. Delete Alarm                         " + BLUE + "║");
            System.out.println("║" + WHITE + "   4. Exit                                 " + BLUE + "║");
            System.out.println("╚═══════════════════════════════════════════╝" + RESET);
            System.out.print(BLUE + "\nEnter your choice (1-4): " + WHITE);
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1: addAlarm(); break;
                case 2: viewAlarms(); break;
                case 3: deleteAlarm(); break;
                case 0: stopCurrentAlarm(); break;
                case 4: 
                    System.out.println(BLUE + "\n╔═══════════════════════════════════════════╗");
                    System.out.println("║" + YELLOW + "         EXITING ALARM MENU                " + BLUE + "║");
                    System.out.println("╚═══════════════════════════════════════════╝" + RESET);
                    break;
                default: System.out.println(BLUE + "\n╔═══════════════════════════════════════════╗");
                         System.out.println("║" + RED + "         INVALID CHOICE!                   " + BLUE + "║");
                         System.out.println("╚═══════════════════════════════════════════╝" + RESET);
            }
        } while (choice != 4);
    }
    
    // New method to stop current alarm
    private void stopCurrentAlarm() {
        if (!alarmRinging) {
            System.out.println(YELLOW + "No alarm is currently ringing." + RESET);
            return;
        }
        
        System.out.println(CYAN + "\n╔═══════════════════════════════════════════╗");
        System.out.println("║" + BOLD + "         STOPPING ALARM                    " + CYAN + "║");
        System.out.println("╚═══════════════════════════════════════════╝" + RESET);
        
        if (currentSoundThread != null && currentSoundThread.isAlive()) {
            // Update the status of the currently ringing alarm to "Rang"
            if (currentRingingAlarm != null) {
                alarmStatus.put(currentRingingAlarm, "Rang");
            }
            
            currentSoundThread.interrupt();
            System.out.println(GREEN + "Alarm stopped successfully!" + RESET);
            
            // Wait for 2 seconds to show the message clearly
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // Ignore
            }
            
            alarmRinging = false;
            currentRingingAlarm = null;
        }
    }
    
    public void addAlarm() {
        System.out.println(CYAN + "\n╔═══════════════════════════════════════════╗");
        System.out.println("║" + BOLD + "         ADD ALARM                         " + CYAN + "║");
        System.out.println("╚═══════════════════════════════════════════╝" + RESET);
        
        Scanner input = new Scanner(System.in);
        ArrayList<String> alarms = new ArrayList<>();
        String Filename = "C:\\Users\\User\\OneDrive\\Desktop\\ChronoSphere\\data\\addAlarm.txt";
        fileHelper.createFileIfMissing(Filename);

        boolean addingMore = true;
        
        while (addingMore) {
            System.out.print(BLUE + "Enter Alarm Time (HH-MM):\n> " + WHITE);
            String alarm = input.nextLine();
            
            // Validate alarm format
            if (!alarm.matches("^([0-1]?[0-9]|2[0-3])-[0-5][0-9]$")) {
                System.out.println(RED + "Invalid format! Please use HH-MM format (e.g., 14-30 for 2:30 PM)" + RESET);
                continue;
            }
            
            // Check if alarm already exists in file
            List<String> existingAlarms = fileHelper.readFileToList(Filename);
            if (existingAlarms.contains(alarm)) {
                System.out.println(YELLOW + "Alarm for " + alarm + " already exists!" + RESET);
                System.out.print(BLUE + "Do you want to set it anyway? (Y/N): " + WHITE);
                String response = input.nextLine().trim().toLowerCase();
                if (!response.equals("y") && !response.equals("yes")) {
                    continue;
                }
            }
            
            alarms.add(alarm);
            fileHelper.appendFile(Filename, alarm);
            
            // Initialize status as "Pending"
            alarmStatus.put(alarm, "Pending");

            // Start alarm thread
            Thread alarmThread = new Thread(() -> ringAlarm(alarm));
            alarmThread.setDaemon(true); // Set as daemon so it doesn't block program exit
            alarmThread.start();
            
            // Convert to 12-hour format for display
            try {
                DateTimeFormatter formatter24 = DateTimeFormatter.ofPattern("HH-mm");
                DateTimeFormatter formatter12 = DateTimeFormatter.ofPattern("hh:mm a");
                LocalTime alarmTime = LocalTime.parse(alarm, formatter24);
                String displayTime = alarmTime.format(formatter12);
                System.out.println(GREEN + "Alarm set for " + displayTime + " (" + alarm + ")" + RESET);
            } catch (Exception e) {
                System.out.println(GREEN + "Alarm set for " + alarm + RESET);
            }

            System.out.print(BLUE + "\nDo you want to add more alarms? (Y/N): " + WHITE);
            String choice = input.nextLine().trim().toLowerCase();
            
            if (choice.equals("n") || choice.equals("no")) {
                addingMore = false;
                System.out.println(YELLOW + "\nReturning to Alarm Menu..." + RESET);
            }
        }
    }

   public void viewAlarms() {
    System.out.println(CYAN + "\n╔═══════════════════════════════════════════╗");
    System.out.println("║" + BOLD + "         VIEW ALARMS                       " + CYAN + "║");
    System.out.println("╚═══════════════════════════════════════════╝" + RESET);
    String Filename = "C:\\Users\\User\\OneDrive\\Desktop\\ChronoSphere\\data\\addAlarm.txt";
    
    List<String> alarms = fileHelper.readFileToList(Filename);
    
    if (alarms.isEmpty()) {
        System.out.println(YELLOW + "No alarms set." + RESET);
        return;
    }
    
    System.out.println(CYAN + "┌─────┬───────────┬─────────────┬───────────────────────┐" + RESET);
    System.out.println(CYAN + "│" + WHITE + " No. " + CYAN + "│" + WHITE + " 24-Hour  " + CYAN + " │" + WHITE + "  12-Hour   " + CYAN + " │" + WHITE + "     Status    " + CYAN + "        │" + RESET);
    System.out.println(CYAN + "├─────┼───────────┼─────────────┼───────────────────────┤" + RESET);
    
    DateTimeFormatter formatter24 = DateTimeFormatter.ofPattern("HH-mm");
    DateTimeFormatter formatter12 = DateTimeFormatter.ofPattern("hh:mm a");
    
    for (int i = 0; i < alarms.size(); i++) {
        String alarmTime = alarms.get(i);
        try {
            LocalTime time = LocalTime.parse(alarmTime, formatter24);
            String displayTime12 = time.format(formatter12);
            String displayTime24 = alarmTime.replace("-", ":");
            
            LocalTime now = LocalTime.now();
            
            // Get stored status or calculate based on time
            String storedStatus = alarmStatus.get(alarmTime);
            String status;
            String statusColor;
            
            if (storedStatus != null && storedStatus.equals("Rang")) {
                // Use stored status if it's "Rang"
                status = "Rang";
                statusColor = BLUE;
            } else if (time.isBefore(now)) {
                // If time has passed, mark as missed
                status = "Missed";
                statusColor = RED;
                alarmStatus.put(alarmTime, "Missed");
            } else if (time.isAfter(now)) {
                // Calculate time until alarm
                long minutesUntil = java.time.Duration.between(now, time).toMinutes();
                if (minutesUntil >= 60) {
                    long hours = minutesUntil / 60;
                    long mins = minutesUntil % 60;
                    if (mins == 0) {
                        status = String.format("In %dh        ", hours);
                    } else {
                        status = String.format("In %dh %02dm    ", hours, mins);
                    }
                } else {
                    status = String.format("In %02dm       ", minutesUntil);
                } 
                statusColor = GREEN;
                alarmStatus.put(alarmTime, "Pending");
            } else {
                status = "Now!";
                statusColor = YELLOW;
                alarmStatus.put(alarmTime, "Now!");
            }
            
            // Format status with consistent width 
            String formattedStatus;
            if (status.equals("Missed")) {
                formattedStatus = "Missed       "; 
            } else if (status.equals("Rang")) {
                formattedStatus = "Rang         "; 
            } else if (status.equals("Now!")) {
                formattedStatus = "Now!      "; 
            } else if (status.startsWith("In") && !status.contains("h")) {
                formattedStatus = String.format("%-10s", status); 
            } else if (status.startsWith("In") && status.contains("h")) {
                formattedStatus = String.format("%-10s", status); 
            } else {
                formattedStatus = String.format("%-10s", status); 
            }
            
            System.out.printf(CYAN + "│" + WHITE + " %2d  " + CYAN + "│" + WHITE + "   %-7s " + CYAN + "│" + WHITE + "   %-8s  " + CYAN + "│" + statusColor + "   %-10s " + CYAN + "      │\n" + RESET, 
                i + 1, displayTime24, displayTime12, formattedStatus);
        } catch (Exception e) {
            // Handle invalid format
            String formattedStatus = "Invalid   ";
            System.out.printf(CYAN + "│" + WHITE + " %2d  " + CYAN + "│" + RED + "   %-7s " + CYAN + "│" + RED + "   %-8s  " + CYAN + "│" + RED + "   %-10s " + CYAN + "      │\n" + RESET, 
                i + 1, alarmTime, "Invalid", formattedStatus);
        }
    }
    System.out.println(CYAN + "└─────┴───────────┴─────────────┴───────────────────────┘" + RESET);
    System.out.println(YELLOW + "Total alarms: " + alarms.size() + RESET);
    
    // Legend for status colors
    System.out.println("\n" + WHITE + "Status Legend:" + RESET);
    System.out.println(GREEN + "  In XXm        " + RESET + "= Alarm is scheduled (minutes only)");
    System.out.println(GREEN + "  In Xh XXm     " + RESET + "= Alarm is scheduled (hours and minutes)");
    System.out.println(BLUE + "  Rang          " + RESET + "= Alarm rang and was stopped");
    System.out.println(RED + "  Missed        " + RESET + "= Alarm time passed");
}

    public void deleteAlarm() {
        System.out.println(CYAN + "\n╔═══════════════════════════════════════════╗");
        System.out.println("║" + BOLD + "         DELETE ALARM                      " + CYAN + "║");
        System.out.println("╚═══════════════════════════════════════════╝" + RESET);
        
        Scanner input = new Scanner(System.in);
        String Filename = "C:\\Users\\User\\OneDrive\\Desktop\\ChronoSphere\\data\\addAlarm.txt";
        
        // First show the alarms
        viewAlarms();
        
        List<String> alarms = fileHelper.readFileToList(Filename);
        if (alarms.isEmpty()) {
            return;
        }
        
        System.out.print(BLUE + "\nEnter alarm number to delete (1-" + alarms.size() + "):\n> " + WHITE);
        
        try {
            int alarmNumber = Integer.parseInt(input.nextLine().trim());
            
            if (alarmNumber < 1 || alarmNumber > alarms.size()) {
                System.out.println(RED + "Invalid number! Please enter 1-" + alarms.size() + RESET);
                return;
            }
            
            String alarmToDelete = alarms.get(alarmNumber - 1);
            
            System.out.print(BLUE + "Delete alarm for " + alarmToDelete + "? (Y/N): " + WHITE);
            String confirm = input.nextLine().trim().toLowerCase();
            
            if (confirm.equals("y") || confirm.equals("yes")) {
                fileHelper.deleteFileLine(Filename, alarmToDelete);
                
                // Remove from status map
                alarmStatus.remove(alarmToDelete);
                
                // Convert to 12-hour for display
                try {
                    DateTimeFormatter formatter24 = DateTimeFormatter.ofPattern("HH-mm");
                    DateTimeFormatter formatter12 = DateTimeFormatter.ofPattern("hh:mm a");
                    LocalTime alarmTime = LocalTime.parse(alarmToDelete, formatter24);
                    String displayTime = alarmTime.format(formatter12);
                    System.out.println(GREEN + "Alarm deleted: " + displayTime + " (" + alarmToDelete + ")" + RESET);
                } catch (Exception e) {
                    System.out.println(GREEN + "Alarm deleted: " + alarmToDelete + RESET);
                }
                
                // Small pause before returning to menu
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    // Ignore
                }
                
            } else {
                System.out.println(YELLOW + "Deletion cancelled." + RESET);
            }
            
        } catch (NumberFormatException e) {
            System.out.println(RED + "Please enter a valid number!" + RESET);
        }
    }

    public void ringAlarm(String alarm) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH-mm");
            LocalTime alarmTime = LocalTime.parse(alarm, formatter);
            
            // Convert to 12-hour format for display
            String displayTime = alarmTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
            
            // Calculate how long to wait
            LocalTime now = LocalTime.now();
            long secondsToWait = 0;
            
            if (alarmTime.isAfter(now)) {
                secondsToWait = java.time.Duration.between(now, alarmTime).getSeconds();
            } else {
                // If alarm time has passed for today, schedule for tomorrow
                secondsToWait = java.time.Duration.between(now, LocalTime.MAX).getSeconds() + 1;
                secondsToWait += java.time.Duration.between(LocalTime.MIN, alarmTime).getSeconds();
            }
            
            // Wait until alarm time
            if (secondsToWait > 0) {
                Thread.sleep(secondsToWait * 1000);
            }

            // NOW the alarm is ringing!
            alarmRinging = true;
            currentRingingAlarm = alarm; // Track which alarm is ringing
            System.out.println("\n" + RED + BOLD + "╔═══════════════════════════════════════════╗");
            System.out.println(RED + BOLD + "║           ALARM! ALARM! ALARM!            ║");
            System.out.println(RED + BOLD + "║             Time: " + displayTime + "                ║");
            System.out.println(RED + BOLD + "╚═══════════════════════════════════════════╝" + RESET);
            System.out.println(YELLOW + "Enter 0 to Stop" + RESET);
            
            // Update status to "Now!" while ringing
            alarmStatus.put(alarm, "Now!");

            // Create and start sound thread
            Thread soundThread = new Thread(() -> {
                int beepCount = 0;
                boolean stoppedManually = false;
                try {
                    while (!Thread.currentThread().isInterrupted() && beepCount < 60) { // Ring for max 30 seconds
                        Toolkit.getDefaultToolkit().beep();
                        beepCount++;
                        Thread.sleep(500); // Beep every 500ms
                    }
                    
                    if (beepCount >= 60) {
                        System.out.println(YELLOW + "\nAlarm for " + displayTime + " stopped automatically after 30 seconds. \n" + RESET);
                        alarmMenu();
                        // Mark as missed since it rang but wasn't stopped manually
                        alarmStatus.put(alarm, "Missed");
                    }
                } catch (InterruptedException e) {
                    stoppedManually = true;
                    System.out.println(GREEN + "\nAlarm for " + displayTime + " stopped manually." + RESET);
                    // Mark as rang since it was stopped manually
                    alarmStatus.put(alarm, "Rang");
                    Thread.currentThread().interrupt();
                } finally {
                    // Small pause to show the message clearly
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // Ignore
                    }
                }
            });

            soundThread.setDaemon(true);
            currentSoundThread = soundThread;
            soundThread.start();

            // Wait for sound thread to complete or be interrupted
            soundThread.join();
            currentSoundThread = null;
            alarmRinging = false;
            currentRingingAlarm = null;

        } catch (InterruptedException e) {
            // Alarm was cancelled
            System.out.println(YELLOW + "Alarm for " + alarm + " was cancelled." + RESET);
            alarmStatus.put(alarm, "Cancelled");
            alarmRinging = false;
            currentRingingAlarm = null;
        } catch (Exception e) {
            System.out.println(RED + "Error in alarm: " + e.getMessage() + RESET);
            alarmStatus.put(alarm, "Error");
            alarmRinging = false;
            currentRingingAlarm = null;
        }
    }
}
class UtilitiesModule {
    // VS-COMPATIBLE ANSI COLOR CODES
    private static final String RESET = "\033[0m";
    private static final String BOLD = "\033[1m";
    private static final String DIM = "\033[2m";
    private static final String CYAN = "\033[36m";
    private static final String BLUE = "\033[34m";
    private static final String GREEN = "\033[32m";
    private static final String YELLOW = "\033[33m";
    private static final String RED = "\033[31m";
    private static final String MAGENTA = "\033[35m";
    private static final String WHITE = "\033[37m";
    
    public void utilitiesMenu() {
        Scanner input = new Scanner(System.in);
        int choice;

        do {
            System.out.println(BLUE + "\n╔═══════════════════════════════════════════╗");
            System.out.println("║" + CYAN + BOLD + "             UTILITIES MENU                " + BLUE + "║");
            System.out.println("╠═══════════════════════════════════════════╣");
            System.out.println("║" + WHITE + "   1. Start Stopwatch                      " + BLUE + "║");
            System.out.println("║" + WHITE + "   2. Timer                                " + BLUE + "║");
            System.out.println("║" + WHITE + "   3. Back to Main Menu                    " + BLUE + "║");
            System.out.println("╚═══════════════════════════════════════════╝" + RESET);
            System.out.print(BLUE + "\nEnter your choice (1-3): " + WHITE);
            
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1: startStopwatch(); break;
                case 2: startTimer(); break;
                case 3: System.out.println(BLUE + "\n╔═══════════════════════════════════════════╗");
                        System.out.println("║" + YELLOW + "     RETURNING TO MAIN MENU                " + BLUE + "║");
                        System.out.println("╚═══════════════════════════════════════════╝" + RESET);
                        break;
                default: System.out.println(BLUE + "\n╔═══════════════════════════════════════════╗");
                         System.out.println("║" + RED + "         INVALID CHOICE!                   " + BLUE + "║");
                         System.out.println("╚═══════════════════════════════════════════╝" + RESET);
            }
        } while (choice != 3);
    }
    
    public void startStopwatch() {
        Scanner input = new Scanner(System.in);
        
        System.out.println(CYAN + "\n╔═══════════════════════════════════════════╗");
        System.out.println("║" + BOLD + "         Starting Stop Watch               " + CYAN + "║");
        System.out.println("╚═══════════════════════════════════════════╝" + RESET);
        
        System.out.println(YELLOW + "Press Enter to start the StopWatch" + RESET);
        input.nextLine();

        System.out.println(GREEN + "Starting StopWatch ...." + RESET);
        long start = System.currentTimeMillis();
        System.out.println(YELLOW + "StopWatch has been started. Press Enter to stop the StopWatch" + RESET);
        input.nextLine();

        long end = System.currentTimeMillis();

        long diff = end - start ;
        // conversion to seconds from milli seconds!!
        long seconds = diff/1000;
        long minutes = seconds/60;
        long hours = minutes/60;

        // this is for the range ( 0 - 59 )
        seconds %= 60;
        minutes %= 60;
        
        System.out.println(CYAN + "\n╔═══════════════════════════════════════════╗");
        System.out.println("║           TIME ELAPSED                    ║");
        System.out.println("╚═══════════════════════════════════════════╝" + RESET);
        System.out.println(WHITE + BOLD + "      " + 
            String.format("%02d:%02d:%02d", hours, minutes, seconds) + RESET);
        System.out.println();
    }
    
    public void startTimer() {
        Scanner input = new Scanner(System.in);
        
        System.out.println(CYAN + "\n╔═══════════════════════════════════════════╗");
        System.out.println("║" + BOLD + "         Starting Timer                    " + CYAN + "║");
        System.out.println("╚═══════════════════════════════════════════╝" + RESET);

        System.out.print(BLUE + "Enter how much time do you start the Timer (In Seconds): " + WHITE);
        int time = input.nextInt();

        System.out.println(GREEN + "Starting Timer ..." + RESET);

        while ( time > 0 ){
            try{
                Thread.sleep(1000);
            } catch ( InterruptedException e){
                System.out.println(RED + "ERROR." + RESET);
            }
            
            // Calculate display
            int hours = time / 3600;
            int minutes = (time % 3600) / 60;
            int seconds = time % 60;
            
            System.out.print("\r" + CYAN + "Time Left: " + 
                           String.format("%02d:%02d:%02d", hours, minutes, seconds) + 
                           "      " + RESET);
            time --;
        }
        
        System.out.println(RED + BOLD + "\n\n╔═══════════════════════════════════════════╗");
        System.out.println("║           TIME IS UP!                     ║");
        System.out.println("╚═══════════════════════════════════════════╝" + RESET);
        
        // Beep alert
        try {
            for (int i = 0; i < 3; i++) {
                Toolkit.getDefaultToolkit().beep();
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println(RED + "Timer interrupted." + RESET);
        }
    }
}

class FileHelper {
    // ANSI COLOR CODES
    private static final String RESET = "\033[0m";
    private static final String BOLD = "\033[1m";
    private static final String DIM = "\033[2m";
    private static final String CYAN = "\033[36m";
    private static final String BLUE = "\033[34m";
    private static final String GREEN = "\033[32m";
    private static final String YELLOW = "\033[33m";
    private static final String RED = "\033[31m";
    private static final String MAGENTA = "\033[35m";
    private static final String WHITE = "\033[37m";
    
    public void createFileIfMissing1(String fileName) {
        try {
            File f1 = new File(fileName);
            f1.getParentFile().mkdirs(); // create directories if needed
            if (f1.createNewFile()) {
                System.out.println(YELLOW + "Created new file: " + fileName + RESET);
            }
        } catch ( IOException e ){
            System.out.println(RED + "Error creating file " + fileName + ": " + e.getMessage() + RESET);
        }
    }

    public void createFileIfMissing(String Filename) {
        try {
            File f = new File(Filename);
            f.getParentFile().mkdirs();
            if (f.createNewFile()) {
                System.out.println(YELLOW + "Created new file: " + Filename + RESET);
            }
        } catch (IOException e) {
            System.out.println(RED + "Error creating file " + Filename + ": " + e.getMessage() + RESET);
        }
    }

    public void appendFile1(String fileName, String line) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(line + "\n");
        } catch (IOException e) {
            System.out.println(RED + "Error writing to file " + fileName + ": " + e.getMessage() + RESET);
        }
    }

    public void appendFile2(String fileName, String line) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(line + "\n");
        } catch (IOException e) {
            System.out.println(RED + "Error writing to file " + fileName + ": " + e.getMessage() + RESET);
        }
    }

    public void appendFile(String Filename, String alarm) {
        try (FileWriter writer = new FileWriter(Filename, true)) {
            writer.write(alarm + "\n");
        } catch (IOException e) {
            System.out.println(RED + "Error writing to file " + Filename + ": " + e.getMessage() + RESET);
        }
    }

    public void deleteFileLine(String Filename, String target) {
        File existing = new File(Filename);
        
        if (!existing.exists()) {
            System.out.println(YELLOW + "File not found: " + Filename + RESET);
            return;
        }
        
        File temp = new File(Filename + ".tmp");
        boolean deleted = false;

        try (Scanner input = new Scanner(existing);
             FileWriter writer = new FileWriter(temp)) {

            while (input.hasNextLine()) {
                String line2 = input.nextLine();
                if (line2.trim().equals(target.trim())) {
                    deleted = true;
                    System.out.println(YELLOW + "Found and removing: " + line2 + RESET);
                    continue;
                }
                writer.write(line2 + "\n");
            }

        } catch (IOException e) {
            System.out.println(RED + "Error reading/writing: " + e.getMessage() + RESET);
            return;
        }
        if (!deleted) {
            temp.delete();
            System.out.println(YELLOW + "Entry not found!" + RESET);
            return;
        }

        int attempts = 0;
        boolean success = false;
        while (attempts < 5 && !success) {
            try {
                Files.move(temp.toPath(), existing.toPath(), StandardCopyOption.REPLACE_EXISTING);
                success = true;
                System.out.println(GREEN + "Entry deleted successfully!" + RESET);
            } catch (IOException e) {
                attempts++;
                try { Thread.sleep(200); } catch (InterruptedException ie) {}
            }
        }
        if (!success) {
            System.out.println(RED + "Failed to replace original file (file lock). Close any app using the file." + RESET);
            temp.delete();
        }
    }

    public void readFile(String fileName) {
        try (Scanner input = new Scanner(new File(fileName))) {
            System.out.println(CYAN + "\n------ Saved Entries ------" + RESET);
            boolean any = false;
            int lineNum = 1;
            while (input.hasNextLine()) {
                any = true;
                System.out.println(WHITE + lineNum + ". " + BLUE + input.nextLine() + RESET);
                lineNum++;
            }
            if (!any) System.out.println(YELLOW + "No entries found." + RESET);
        } catch (IOException e) {
            System.out.println(RED + "Error reading file " + fileName + ": " + e.getMessage() + RESET);
        }
    }

    public List<String> readFileToList(String fileName) {
        List<String> list = new ArrayList<>();
        File f = new File(fileName);
        if (!f.exists()) return list;
        try (Scanner input = new Scanner(f)) {
            while (input.hasNextLine()) {
                list.add(input.nextLine());
            }
        } catch (IOException e) {
            System.out.println(RED + "Error reading file to list: " + e.getMessage() + RESET);
        }
        return list;
    }
}