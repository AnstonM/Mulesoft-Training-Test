import java.sql.*; //importing essential library functions

public class Mulesoft { 
    public static Connection connect() { // User defined method for connecting to / creating new database
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:./db.db"; // String for connecting to DB
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            return conn;            
        } 
        catch (SQLException e) { // Handling SQL errors
            System.out.println(e.getMessage());
            return null;
        } 
    }
    
    public static void create(Connection conn) { // User defined method for creating MOVIE table if it doesnt exist
        try{       
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE MOVIE " +
            "(MOV_ID INTEGER PRIMARY KEY," +
            " MOV_NAME TEXT NOT NULL, " +
            " ACTOR TEXT , " +
            " ACTRESS TEXT , " +
            " DIRECTOR TEXT NOT NULL, " +
            " YEAR_OF_RELEASE INTEGER) " ;
            stmt.executeUpdate(sql); // Executing the SQL create Statement
            stmt.close();
        }   
        catch(Exception e){ // Handling error when table already exists , CAN ALSO BE HANDLED USING ......IF NOT EXISTS
            return;
        }
    }

    public static void insert(Connection conn){ // User defined method for inserting values into the MOVIE Table
        try{
        Statement stmt = conn.createStatement();
        String sql = "INSERT INTO MOVIE VALUES (101,'TIGER','SALMAN KHAN','KATRINA','KABIR',2005)";
        stmt.executeUpdate(sql);// Executing the SQL insert Statement
        sql = "INSERT INTO MOVIE VALUES (102,'LION','SALMAN KHAN','KAREENA','KABIR',2007)";
        stmt.executeUpdate(sql);// Executing the SQL insert Statement
        sql = "INSERT INTO MOVIE VALUES (103,'KILLER','AMIR KHAN','KANJANA','KARAN',2010)";
        stmt.executeUpdate(sql);// Executing the SQL insert Statement
        sql = "INSERT INTO MOVIE VALUES (104,'KAJOOR','VARUN DUKE','KRITI','KARAN',2013)";
        stmt.executeUpdate(sql);// Executing the SQL insert Statement
        sql = "INSERT INTO MOVIE VALUES (105,'LOVER','AMIR KHAN','KAREENA','MAHESH',2017)";
        stmt.executeUpdate(sql);// Executing the SQL insert Statement
        stmt.close();
        }
        catch(Exception ex){return;}
    }

    public static void query(Connection conn) // User defined method for querying the MOVIE Table
    {
        
        try{
            Statement stmt = conn.createStatement();
            System.out.println();
            System.out.println("SELECT * FROM MOVIE;"); // To retrieve all the data
            System.out.println();
            ResultSet rs = stmt.executeQuery("SELECT * FROM MOVIE;");
            System.out.println("MOV_ID\t\tMOV_NAME\tACTOR\t\tACTRESS\t\tDIRECTOR\tYEAR OF RELEASE ");
            System.out.println();
            while ( rs.next() ) {
            int id = rs.getInt("MOV_ID");
            String name = rs.getString("MOV_NAME");
            String aname = rs.getString("ACTOR");
            String acname = rs.getString("ACTRESS");
            String dname = rs.getString("DIRECTOR");
            int year = rs.getInt("YEAR_OF_RELEASE");
            System.out.println(id+"\t\t"+name+"\t\t"+aname+"\t"+acname+"\t\t"+dname+"\t\t"+year);
            }
            rs.close();
            System.out.println();
            System.out.println();
            System.out.println("SELECT * FROM MOVIE WHERE ACTOR='AMIR KHAN';"); // To retrieve data specific to the ACTOR
            System.out.println();
            rs = stmt.executeQuery("SELECT * FROM MOVIE WHERE ACTOR='AMIR KHAN';");
            System.out.println("MOV_ID\t\tMOV_NAME\tACTOR\t\tACTRESS\t\tDIRECTOR\tYEAR OF RELEASE ");
            System.out.println();
            while ( rs.next() ) {
            int id = rs.getInt("MOV_ID");
            String name = rs.getString("MOV_NAME");
            String aname = rs.getString("ACTOR");
            String acname = rs.getString("ACTRESS");
            String dname = rs.getString("DIRECTOR");
            int year = rs.getInt("YEAR_OF_RELEASE");
            System.out.println(id+"\t\t"+name+"\t\t"+aname+"\t"+acname+"\t\t"+dname+"\t\t"+year);
            }
            System.out.println();
            rs.close();
            stmt.close();

        }
        catch(Exception ex){return;}
    }



    public static void main(String[] args) throws SQLException{
        Connection c = connect(); // Connection establishment
        create(c);// Creating the MOVIE table
        insert(c);// Inserting values into the MOVIE table
        query(c);// Querying and Output
        c.close();// Closing the Connection
    }
}