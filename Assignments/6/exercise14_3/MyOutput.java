
/**
 * Parses log entries for ip address, date, time, device, location, and action taken
 * @author Nafisa Tabassum <a href="mailto:nafisa.tabassum@ucalgary.ca">nafisa.tabassum@ucalgary.ca</a>
 * @version 1.0
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

interface FormattedOutput {
    String getFormatted();
}

/**
 * Actions that can be taken
 */
enum Actions {
    END, ENABLE, START, TEST, DISABLE;
}

/**
 * Months in year, includes functions for ordinal, named month, and month
 * abbreviation
 */
enum Months {
    JAN {
        public String toString() {
            return "January";
        }

        public int toInt() {
            return 1;
        }

        public String toLog() {
            return "Jan";
        }
    },
    FEB {
        public String toString() {
            return "February";
        }

        public int toInt() {
            return 2;
        }

        public String toLog() {
            return "Feb";
        }
    },

    MAR {
        public String toString() {
            return "March";
        }

        public int toInt() {
            return 3;
        }

        public String toLog() {
            return "Mar";
        }
    },
    APR {
        public String toString() {
            return "April";
        }

        public int toInt() {
            return 4;
        }

        public String toLog() {
            return "Apr";
        }
    },
    MAY {
        public String toString() {
            return "May";
        }

        public int toInt() {
            return 5;
        }

        public String toLog() {
            return "May";
        }
    },
    JUN {
        public String toString() {
            return "June";
        }

        public int toInt() {
            return 6;
        }

        public String toLog() {
            return "Jun";
        }
    },
    JUL {
        public String toString() {
            return "July";
        }

        public int toInt() {
            return 7;
        }

        public String toLog() {
            return "Jul";
        }
    },
    AUG {
        public String toString() {
            return "August";
        }

        public int toInt() {
            return 8;
        }

        public String toLog() {
            return "Aug";
        }
    },
    SEP {
        public String toString() {
            return "September";
        }

        public int toInt() {
            return 9;
        }

        public String toLog() {
            return "Sep";
        }
    },
    OCT {
        public String toString() {
            return "October";
        }

        public int toInt() {
            return 10;
        }

        public String toLog() {
            return "Oct";
        }
    },
    NOV {
        public String toString() {
            return "November";
        }

        public int toInt() {
            return 11;
        }

        public String toLog() {
            return "Nov";
        }
    },
    DEC {
        public String toString() {
            return "December";
        }

        public int toInt() {
            return 12;
        }

        public String toLog() {
            return "Dec";
        }
    };

    // Returns full name of month
    public abstract String toString();

    // Returns month ordinal
    public abstract int toInt();

    // Returns month abbreviation
    public abstract String toLog();
}

/**
 * Extracts and processes ipv4 addresses from log entry
 */
class IPv4 implements FormattedOutput {
    private final String IP;
    private static final String REGEX = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    // Stores full log line
    public IPv4(String ip) {
        this.IP = ip;
    }

    // Returns a string in the format:
    // IPv4: xxx.xxx.xxx.xxx
    public String getFormatted() {
        return "IPv4: " + getIP();
    }

    // Returns ipv4 address
    public String getIP() {
        Matcher matcher = PATTERN.matcher(this.IP);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    // Returns regex used to extract ipv4 address
    public static String getRegex() {
        return REGEX;
    }

}

/**
 * Extracts and processes device name from log entry
 */
class Device implements FormattedOutput {
    private final String DEVICE;
    private static final String REGEX = "(?<=\\s)[a-zA-Z\\s]+";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    public Device(String device) {
        this.DEVICE = device;
    }

    // Returns a string in the format:
    // Device: sprinkler
    public String getFormatted() {
        return "Device: " + getDevice();
    }

    // Returns name of device
    public String getDevice() {
        Matcher matcher = PATTERN.matcher(this.DEVICE);
        if (matcher.find()) {
            return matcher.group().strip();
        }
        return null;
    }

    // Returns regex used to extract device name
    public static String getRegex() {
        return REGEX;
    }

}

/**
 * Extracts and processes date and time from log entry
 */
class DateTime implements FormattedOutput {
    private final int DAY;
    private final int MONTH;
    private final int YEAR;
    private final int HOUR;
    private final int MINUTE;
    private final int SECOND;
    private static final String REGEX = "\\[([0-9]{1,2})/([a-zA-Z]{3})/([0-9]{4}):([0-9]{1,2}):([0-9]{2}):([0-9]{2})\\]";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    public DateTime(String datetime) {
        Matcher matcher = PATTERN.matcher(datetime);
        if (matcher.find()) {
            Months enumMonth = Months.valueOf(matcher.group(2).toUpperCase());
            this.DAY = Integer.parseInt(matcher.group(1));
            this.MONTH = enumMonth.toInt();
            this.YEAR = Integer.parseInt(matcher.group(3));
            this.HOUR = Integer.parseInt(matcher.group(4));
            this.MINUTE = Integer.parseInt(matcher.group(5));
            this.SECOND = Integer.parseInt(matcher.group(6));
        } else {
            this.DAY = 0;
            this.MONTH = 0;
            this.YEAR = 0;
            this.HOUR = 0;
            this.MINUTE = 0;
            this.SECOND = 0;
        }
    }

    // Returns string in the format:
    // Day: , Month: , Year: , Hour: , Minute: , Second:
    public String getFormatted() {
        return "Day: " + this.DAY + ", Month: " + monthToString() + ", Year: " + this.YEAR + ", Hour: " + this.HOUR
                + ", Minute: " + this.MINUTE + ", Second: " + this.SECOND;
    }

    // Converts ordinal to named month
    public String monthToString() {
        for (Months enumMonth : Months.values()) {
            if (enumMonth.toInt() == this.MONTH) {
                return enumMonth.toString();
            }
        }
        return null;
    }

    // Returns day
    public int getDay() {
        return this.DAY;
    }

    // Returns month ordinal
    public int getMonth() {
        return this.MONTH;
    }

    // Returns year
    public int getYear() {
        return this.YEAR;
    }

    // Returns hour
    public int getHour() {
        return this.HOUR;
    }

    // Returns minute
    public int getMinute() {
        return this.MINUTE;
    }

    // Returns second
    public int getSecond() {
        return this.SECOND;
    }

    // Returns regex used to extract date and time
    public static String getRegex() {
        return REGEX;
    }

}

/**
 * Extracts action from log entry
 */
class Action implements FormattedOutput {
    private final String ACTION;
    private static final String REGEX = "(?<=\")([A-Z]+)\\s";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    public Action(String action) {
        this.ACTION = action;
    }

    // Returns string in the format:
    // Action: ENABLE
    public String getFormatted() {
        for (Actions enumAction : Actions.values()) {
            if (enumAction.name().equals(getAction())) {
                return "Action: " + getAction();
            }
        }
        return null;
    }

    // Returns extracted action
    public String getAction() {
        Matcher matcher = PATTERN.matcher(this.ACTION);
        if (matcher.find()) {
            return matcher.group().strip();
        }
        return null;
    }

    // Returns regex used to extract action
    public static String getRegex() {
        return REGEX;
    }
}

/**
 * Extracts location (room and building) from log entry
 */
class Location implements FormattedOutput {
    private final String ROOM;
    private final String BUILDING;
    private static final String REGEX = "\\((.*?)\\)";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    public Location(String location) {
        Matcher matcher = PATTERN.matcher(location);
        if (matcher.find()) {
            String[] roomAndBuilding = matcher.group(1).strip().split(" - ");
            this.ROOM = roomAndBuilding[0];
            this.BUILDING = roomAndBuilding[1];
        } else {
            this.ROOM = null;
            this.BUILDING = null;
        }
    }

    // Returns string in the format:
    // Room: Secured room, Building: Airport location
    public String getFormatted() {
        return "Room: " + this.ROOM + ", Building: " + this.BUILDING;
    }

    // Returns room
    public String getRoom() {
        return this.ROOM;
    }

    // Returns building
    public String getBuilding() {
        return this.BUILDING;
    }

    // Returns regex used to extract location
    public static String getRegex() {
        return REGEX;
    }

}

/**
 * Representation of entire log
 */
class ParseLogfile {
    private ArrayList<ParseLine> log;

    public ParseLogfile(String[] array) {
        this.log = new ArrayList<ParseLine>();
        for (String line : array) {
            this.log.add(new ParseLine(line));
        }
    }

    // Returns a specific entry in log file
    public ParseLine getLine(int index) {
        return this.log.get(index);
    }

    // Returns entire log
    public ArrayList<ParseLine> getLog() {
        return this.log;
    }

}

/**
 * Represents an entry in the log
 */
class ParseLine {
    private final String logLine;
    private final Location location;
    private final Device device;
    private final Action action;
    private final DateTime dateTime;
    private final IPv4 ipv4;

    public ParseLine(String line) {
        this.logLine = line;
        this.location = new Location(logLine);
        this.device = new Device(logLine);
        this.action = new Action(logLine);
        this.dateTime = new DateTime(logLine);
        this.ipv4 = new IPv4(logLine);
    }

    // Returns formatted ipv4 string
    public IPv4 getIPv4() {
        return this.ipv4;
    }

    // Returns log entry
    public String getLogLine() {
        return this.logLine;
    }

    // Returns formatted location string
    public Location getLocation() {
        return this.location;
    }

    // Returns formatted device string
    public Device getDevice() {
        return this.device;
    }

    // Returns formatted action string
    public Action getAction() {
        return this.action;
    }

    // Returns formatted date and time string
    public DateTime getDateTime() {
        return this.dateTime;
    }

}

public class MyOutput {
    public static void main(String[] args) {
        String[] exampleLog = exampleLog();

        var logfile = new ParseLogfile(exampleLog);
        var line = logfile.getLine(0); // ParseLine object
        System.out.println("Log line 0: " + line.getLogLine());

        var ip = line.getIPv4(); // ipv4 object
        System.out.println("IPv4: " + ip.getIP());

        var dt = line.getDateTime();
        System.out.println("Day: " + dt.getDay());
        System.out.println("Month: " + dt.getMonth());
        System.out.println("Month (named): " + dt.monthToString());
        System.out.println("Year: " + dt.getYear());
        System.out.println("Hour: " + dt.getHour());
        System.out.println("Minute: " + dt.getMinute());
        System.out.println("Second: " + dt.getSecond());

        var act = line.getAction();
        System.out.println("Action: " + act.getAction());

        var dev = line.getDevice();
        System.out.println("Device: " + dev.getDevice());

        var loc = line.getLocation();
        System.out.println("Room: " + loc.getRoom());
        System.out.println("Building: " + loc.getBuilding());

        System.out.println();
        line = logfile.getLine(6);
        System.out.println("Log line 6: " + line.getLogLine());
        System.out.println(line.getIPv4().getFormatted());
        System.out.println(line.getDateTime().getFormatted());
        System.out.println(line.getAction().getFormatted());
        System.out.println(line.getDevice().getFormatted());
        System.out.println(line.getLocation().getFormatted());

        System.out.println();
        line = logfile.getLine(3);
        System.out.println("Log line 3: " + line.getLogLine());
        System.out.println(line.getIPv4().getFormatted());
        System.out.println(line.getDateTime().getFormatted());
        System.out.println(line.getAction().getFormatted());
        System.out.println(line.getDevice().getFormatted());
        System.out.println(line.getLocation().getFormatted());

        System.out.println();
        line = logfile.getLine(10);
        System.out.println("Log line 10: " + line.getLogLine());
        System.out.println(line.getIPv4().getFormatted());
        System.out.println(line.getDateTime().getFormatted());
        System.out.println(line.getAction().getFormatted());
        System.out.println(line.getDevice().getFormatted());
        System.out.println(line.getLocation().getFormatted());

        System.out.println("\nExample of toLog() output: " + Months.AUG.toLog());
        System.out.println("\nExample regex (for DateTime): " + dt.getRegex());
    }

    // Contains example data
    public static String[] exampleLog() {
        String[] log = { "81.220.24.207 - - [2/Mar/2020:10:05:44] \"END sprinkler (Visitor entrance - Building A)\"",
                "81.220.24.207 - - [2/Mar/2020:10:05:26] \"ENABLE cooling system (Secured room - Building A)\"",
                "81.220.24.207 - - [2/Mar/2020:10:05:39] \"START heating system (Hall - Central)\"",
                "81.220.24.207 - - [2/Mar/2020:10:05:52] \"ENABLE door lock (Visitor entrance - Building B)\"",
                "81.220.24.207 - - [2/Mar/2020:10:05:21] \"TEST cooling system (Entrance - Building B)\"",
                "66.249.73.135 - - [17/May/2020:01:05:17] \"TEST fan (Secured room - Airport location)\"",
                "46.105.14.53 - - [17/May/2020:11:05:42] \"TEST cooling system (Secured room - Airport location)\"",
                "218.30.103.62 - - [17/May/2020:11:05:11] \"START sprinkler (Secured room - Airport location)\"",
                "218.30.103.62 - - [17/May/2020:11:05:46] \"DISABLE fan (Control room - Central)\"",
                "218.30.103.62 - - [17/May/2020:11:05:45] \"START door lock (Secured room - Building A)\"",
                "66.249.73.135 - - [27/Jun/2020:11:05:31] \"TEST sprinkler (Hall - Building B)\"" };
        return log;
    }

}
