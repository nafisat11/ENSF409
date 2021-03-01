
/**
 * Log parser
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

interface FormattedOutput {
    String getFormatted();
}

enum Actions {
    END, ENABLE, START, TEST, DISABLE;
}

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

    public abstract String toString();

    public abstract int toInt();

    public abstract String toLog();
}

class IPv4 implements FormattedOutput {
    private final String ip;
    private static final String regex = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
    private static final Pattern pattern = Pattern.compile(regex);

    public IPv4(String ip) { // gets the log line
        this.ip = ip;
    }

    public String getFormatted() { // IPv4: xx.xxx
        return "IPv4: " + getIP();
    }

    public String getIP() { // returns only ip addr
        Matcher matcher = pattern.matcher(this.ip);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    public String getRegex() {
        return regex;
    }

}

class Device implements FormattedOutput {
    private final String device;
    private static final String regex = "(?<=\\s)[a-zA-Z\\s]+";
    private static final Pattern pattern = Pattern.compile(regex);

    public Device(String device) {
        this.device = device;
    }

    public String getFormatted() { // returns Device: ...
        return "Device: " + getDevice();
    }

    public String getDevice() { // returns device name
        Matcher matcher = pattern.matcher(this.device);
        if (matcher.find()) {
            return matcher.group().strip();
        }
        return null;
    }

    public static String getRegex() { // gets the regex string
        return regex;
    }

}

class DateTime implements FormattedOutput {
    private final int day;
    private final int month;
    private final int year;
    private final int hour;
    private final int minute;
    private final int second;
    private static final String regex = "\\[([0-9]{1,2})/([a-zA-Z]{3})/([0-9]{4}):([0-9]{1,2}):([0-9]{2}):([0-9]{2})\\]";
    private static final Pattern pattern = Pattern.compile(regex);

    public DateTime(String datetime) {
        Matcher matcher = pattern.matcher(datetime);
        if (matcher.find()) {
            this.day = Integer.parseInt(matcher.group(1));
            this.month = abbrevToInt(matcher.group(2));
            this.year = Integer.parseInt(matcher.group(3));
            this.hour = Integer.parseInt(matcher.group(4));
            this.minute = Integer.parseInt(matcher.group(5));
            this.second = Integer.parseInt(matcher.group(6));
        } else {
            this.day = 0;
            this.month = 0;
            this.year = 0;
            this.hour = 0;
            this.minute = 0;
            this.second = 0;
        }
    }

    private int abbrevToInt(String month) {
        for (Months m : Months.values()) {
            if (m.toLog().equals(month)) {
                return m.toInt();
            }
        }
        return 1;
    }

    public String getFormatted() { // returns String in the form of Day: , Month: etc
        return "Day: " + this.day + ", Month: " + monthToString() + ", Year: " + this.year + ", Hour: " + this.hour
                + ", Minute: " + this.minute + ", Second: " + this.second;
    }

    public String monthToString() { // if given 3, return March
        for (Months m : Months.values()) {
            if (m.toInt() == this.month) {
                return m.toString();
            }
        }
        return null;
    }

    public int getDay() {
        return this.day;
    }

    public int getMonth() {
        return this.month;
    }

    public int getYear() {
        return this.year;
    }

    public int getHour() {
        return this.hour;
    }

    public int getMinute() {
        return this.minute;
    }

    public int getSecond() {
        return this.second;
    }

    public String getRegex() {
        return regex;
    }

}

class Action implements FormattedOutput {
    private final String action;
    private static final String regex = "(?<=\")([A-Z]+)\\s";
    private static final Pattern pattern = Pattern.compile(regex);

    public Action(String action) {
        this.action = action;
    }

    public String getFormatted() { // returns Action: ...
        for (Actions a : Actions.values()) {
            if (a.name().equals(getAction())) {
                return "Action: " + getAction();
            }
        }
        return null;
    }

    public String getAction() { // returns action
        Matcher matcher = pattern.matcher(this.action);
        if (matcher.find()) {
            return matcher.group().strip();
        }
        return null;
    }

    public String getRegex() {
        return regex;
    }
}

class Location implements FormattedOutput {
    private final String room;
    private final String building;
    private final String regex = "\\((.*?)\\)";
    private final Pattern pattern = Pattern.compile(regex);

    public Location(String location) { // log line get room and building
        Matcher matcher = pattern.matcher(location);
        if (matcher.find()) {
            String[] roomAndBuilding = matcher.group(1).strip().split(" - ");
            this.room = roomAndBuilding[0];
            this.building = roomAndBuilding[1];
        } else {
            this.room = null;
            this.building = null;
        }
    }

    public String getFormatted() { // returns String in the form of Room: Secured room, Building: Airport location
        return "Room: " + this.room + ", Building: " + this.building;
    }

    public String getRoom() {
        return this.room;
    }

    public String getBuilding() {
        return this.building;
    }

    public String getRegex() {
        return regex;
    }

}

class ParseLogfile {
    private ArrayList<ParseLine> log;

    public ParseLogfile(String[] array) {
        log = new ArrayList<ParseLine>();
        for (String line : array) {
            this.log.add(new ParseLine(line));
        }
    }

    public ParseLine getLine(int index) {
        return this.log.get(index);
    }

    public ArrayList<ParseLine> getLog() {
        return this.log;
    }

}

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

    public IPv4 getIPv4() {
        return this.ipv4;
    }

    public String getLogLine() {
        return this.logLine;
    }

    public Location getLocation() {
        return this.location;
    }

    public Device getDevice() {
        return this.device;
    }

    public Action getAction() {
        return this.action;
    }

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
