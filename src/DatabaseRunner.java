import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
 
import java.nio.file.*; //this is to use Path
 
 
 
public class DatabaseRunner { 
  //Path dbpath = FileSystems.getDefault().getPath("db", "LateNightCravings"); 
  public String databasepath = "jdbc:sqlite:/home/c/workbench/LateNightCravings/db/LateNightCravings"; 
  public Connection connect = null; 
  
  public static void main(String[] args){
		 DatabaseRunner j = new DatabaseRunner(); 
		 j.setupConnection();
		 CreateProfile pr =new CreateProfile();
		j.executeQuery("INSERT INTO Items VALUES (1,'cats',2.2,'a tasty cat')");
	}
	
  public DatabaseRunner() { 
   
  } 
   
  public void setupConnection() { 
	  System.out.println(databasepath);
    try { 
      connect = DriverManager.getConnection(databasepath); 
    } catch (SQLException e) { 
      // TODO Auto-generated catch block 
      e.printStackTrace(); 
    } 
  }
  /* 
   * @param connect: the database connection. 
   * @param query: what SQL query to execute 
   */ 
  public ResultSet executeQuery(String SQLquery) { 
     
    ResultSet resultSet = null; 
     
    PreparedStatement query; 
    try { 
      query = connect.prepareStatement(SQLquery); 
      resultSet = query.executeQuery(); 
    } catch (SQLException e) { 
      e.printStackTrace(); 
    } 
    return resultSet; 
  } 
   
   
} 
