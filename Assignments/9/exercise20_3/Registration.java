/**
 * Program for interacting with a database that stores registration information for a competition
 * @author Nafisa Tabassum <a href="mailto:nafisa.tabassum@ucalgary.ca">nafisa.tabassum@ucalgary.ca</a>
 * @version 1.0
 */

package edu.ucalgary.ensf409;

import java.sql.*;

public class Registration {

    public final String DBURL; // store the database url information
    public final String USERNAME; // store the user's account username
    public final String PASSWORD; // store the user's account password
    private Connection dbConnect;
    private ResultSet results;

    public Registration(String DBURL, String USERNAME, String PASSWORD) {
        this.DBURL = DBURL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }

    public String getDburl() {
        return this.DBURL;
    }

    public String getUsername() {
        return this.USERNAME;
    }

    public String getPassword() {
        return this.PASSWORD;
    }

    /**
     * Creates the connection to the database with the provided url and credentials
     */
    public void initializeConnection() {
        try {
            dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes connection to database to release resources
     */
    private void closeConnection() {
        try {
            dbConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Queries the user-provided database to find all names
     * 
     * @param dbTableName
     * @return last/first name string combination listed as LName, FName on its own
     *         line
     */
    public String selectAllNames(String dbTableName) {
        StringBuffer allNames = new StringBuffer();
        String query = "SELECT LName, FName FROM $tableName".replace("$tableName", dbTableName);

        try {
            Statement selectNamesQuery = dbConnect.createStatement();
            results = selectNamesQuery.executeQuery(query);

            while (results.next()) {
                allNames.append(results.getString("LName") + ", " + results.getString("FName"));
                allNames.append("\n");
            }

            selectNamesQuery.close();
            results.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allNames.toString().trim();
    }

    /**
     * Queries the studio table to find all studio names
     * 
     * @return String containing list of all names in the Studio table, each name is
     *         listed on its own line
     */
    public String showStudios() {
        StringBuffer allStudios = new StringBuffer();

        try {
            Statement selectStudiosQuery = dbConnect.createStatement();
            results = selectStudiosQuery.executeQuery("SELECT Name FROM STUDIO");

            while (results.next()) {
                allStudios.append(results.getString("Name"));
                allStudios.append("\n");
            }

            selectStudiosQuery.close();
            results.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allStudios.toString().trim();
    }

    /**
     * Inserts a record into the Competitor table if it does not already exist
     * 
     * @param competitorID Competitor ID
     * @param lastName     Competitor's last name
     * @param firstName    Competitor's first name
     * @param age          Competitor's age
     * @param instrument   The instrument the competitor plays
     * @param teacherID    Competitor's teacher
     */
    public void insertNewCompetitor(String competitorID, String lastName, String firstName, int age, String instrument,
            String teacherID) {

        if (age < 5 || age > 18) {
            throw new IllegalArgumentException("Competitor is not between the ages of 5 and 18");
        }

        try {
            String checkTeacher = "SELECT * FROM TEACHER WHERE TeacherID = ?";

            PreparedStatement checkTeacherQuery = dbConnect.prepareStatement(checkTeacher);
            checkTeacherQuery.setString(1, teacherID);
            results = checkTeacherQuery.executeQuery();

            if (!results.isBeforeFirst()) {
                throw new IllegalArgumentException("Teacher does not exist");
            }
            checkTeacherQuery.close();
            results.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            String query = "INSERT INTO COMPETITOR (CompetitorID, LName, FName, Age, Instrument, TeacherID) VALUES (?,?,?,?,?,?)";
            PreparedStatement insertCompetitor = dbConnect.prepareStatement(query);

            insertCompetitor.setString(1, competitorID);
            insertCompetitor.setString(2, lastName);
            insertCompetitor.setString(3, firstName);
            insertCompetitor.setInt(4, age);
            insertCompetitor.setString(5, instrument);
            insertCompetitor.setString(6, teacherID);

            insertCompetitor.executeUpdate();

            insertCompetitor.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserts a record in the Teacher table if it does not already exist
     * 
     * @param teacherID         Teacher ID
     * @param lastName          Teacher's last name
     * @param firstName         Teacher's first name
     * @param phoneNumber       Teacher's phone number
     * @param studioName        Studio teacher works at
     * @param studioPhoneNumber Phone number of studio
     * @param studioAddress     Studio address
     */
    public void registerNewTeacher(String teacherID, String lastName, String firstName, String phoneNumber,
            String studioName, String studioPhoneNumber, String studioAddress) {

        try {
            String checkTeacher = "SELECT * FROM TEACHER WHERE TeacherID = ?";

            PreparedStatement checkTeacherQuery = dbConnect.prepareStatement(checkTeacher);
            checkTeacherQuery.setString(1, teacherID);
            results = checkTeacherQuery.executeQuery();

            if (results.isBeforeFirst()) {
                throw new IllegalArgumentException("Teacher already registered");
            }

            checkTeacherQuery.close();
            results.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String studios = showStudios();

        if (!studios.contains(studioName)) {
            try {
                String query = "INSERT INTO STUDIO (Name, Phone, Address) VALUES (?,?,?)";
                PreparedStatement insertStudio = dbConnect.prepareStatement(query);

                insertStudio.setString(1, studioName);
                insertStudio.setString(2, studioPhoneNumber);
                insertStudio.setString(3, studioAddress);
                insertStudio.executeUpdate();

                insertStudio.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            String query = "INSERT INTO TEACHER (teacherID, LName, FName, Phone, StudioName) VALUES (?,?,?,?,?)";
            PreparedStatement insertTeacher = dbConnect.prepareStatement(query);

            insertTeacher.setString(1, teacherID);
            insertTeacher.setString(2, lastName);
            insertTeacher.setString(3, firstName);
            insertTeacher.setString(4, phoneNumber);
            insertTeacher.setString(5, studioName);
            insertTeacher.executeUpdate();

            insertTeacher.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    /**
     * Deletes entry in Competitor table that matches the competitor's ID
     * 
     * @param competitorID Competitor's ID
     */
    public void deleteCompetitor(String competitorID) {
        try {
            String query = "DELETE FROM COMPETITOR WHERE CompetitorID = ?";
            PreparedStatement removeCompetitor = dbConnect.prepareStatement(query);

            removeCompetitor.setString(1, competitorID);
            removeCompetitor.executeUpdate();

            removeCompetitor.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes entry in Teacher table that matches the teacher's ID
     * 
     * @param teacherID Teacher's ID
     */
    public void deleteTeacher(String teacherID) {
        try {
            String query = "DELETE FROM TEACHER WHERE TeacherID = ?";
            PreparedStatement removeTeacher = dbConnect.prepareStatement(query);

            removeTeacher.setString(1, teacherID);
            removeTeacher.executeUpdate();

            removeTeacher.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Some example test data is shown here. This is not a full list of all possible
    // tests (i.e. competitors under the age of 5, over 18, etc.)
    // Tests will be done on a database with the same table names/attributes, but
    // different data records.

    public static void main(String[] args) {

        Registration myJDBC = new Registration("jdbc:mysql://localhost/COMPETITION", "root", "ensf409");
        myJDBC.initializeConnection();

        System.out.println(myJDBC.selectAllNames("competitor".toUpperCase()));
        /*
         * Example: Williams, Sophie Warren, Harper
         */

        System.out.println(myJDBC.selectAllNames("teacher".toUpperCase()));
        /*
         * Example: Estrada, Ethan Lee, Jasmine
         */

        System.out.println(myJDBC.showStudios());
        /*
         * Example: Harmony Inc. Melody Time Music Mastery
         */

        // myJDBC.insertNewCompetitor("123", "Smyth", "Ali", 12, "Oboe", "0023");
        // myJDBC.registerNewTeacher("0987", "Marasco", "Emily", "403-222-5656",
        // "Marasco Music", "587-222-5656",
        // "123 Main Street NW");

        // myJDBC.deleteCompetitor("123");
        // myJDBC.deleteTeacher("0023");
        myJDBC.closeConnection();

    }
}
