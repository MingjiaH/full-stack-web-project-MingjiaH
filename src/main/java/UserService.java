
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.sql.Connection;
import java.sql.SQLException;


public class UserService {
   // returns a list of all users
    public List<User> getAllUsers() {
          List<User> users=new ArrayList<User>();
      return users;
    }
     
    // returns a single user by id
    public User getUser(String name,String password) throws Exception {
          Connection connection = null;
          User u = null;
          String gender="female";
          String firstStar="EXO";
          String secondStar="Bigbang";
          try {
              // TODO: SELECT user by userAccount from database.
              UUID userId = UUID.randomUUID();
              u = new User(name, password,gender,firstStar,secondStar);
              return u;
              
          } catch (Exception e) {
              throw new Exception(this.getClass() + ".selectUserByAccount: Database failure.");
          } finally {
              if (connection != null)
                  try {
                      connection.close();
                  } catch (SQLException e) {
                      System.out.println(this.getClass() + ".selectUserByAccount: Could not close connection to database.");
                  }
          }
    }
   
    // creates a new user
    public User createUser(String name, String password,String email,String gender,String firstStar,
      String secondStar,String thirdStar) throws Exception {
         User user = new User(name, password,email,gender,firstStar,secondStar,thirdStar);
         Connection connection = null;
          try {
              // TODO: insert new record.
          } catch (Exception e) {
              throw new Exception(this.getClass() + ".addUser: Database failure.");
          } finally {
              if (connection != null)
                  try {
                      connection.close();
                  } catch (SQLException e) {
                      System.out.println(this.getClass() + ".addUser: Could not close connection to database.");
                  }
          }
         return user;
         
    }
   
    // updates an existing user
    public User updateUser(String name, String password,String email,String gender,String firstStar,
          String secondStar,String thirdStar) {
      User user = new User(name, password,email,gender,firstStar,secondStar,thirdStar);
      return user;
       
    }
}
