import java.util.UUID;

public class User {

    private String id;
    private String name;
    private String password;
    private String email;
    private String gender;
    private String firstStar;
    private String secondStar;
    private String thirdStar;
    
    User(){}
    User(String name, String password,String email,String gender,String firstStar,
          String secondStar,String thirdStar){
      UUID uuid = UUID.randomUUID();  
      String id = name+uuid.toString();
      this.id = id;
      this.name = name;
      this.password=password;
      this.email = email;
      this.gender=gender;
      this.firstStar=firstStar;
      this.secondStar=secondStar;
      this.thirdStar=thirdStar;
    }
    User(String name, String password,String gender,String firstStar,String secondStar){
      UUID uuid = UUID.randomUUID();  
      String id = name+uuid.toString();
      this.id = id;
      this.name = name;
      this.password=password;
      this.gender=gender;
      this.firstStar=firstStar;
      this.secondStar=secondStar;
      this.thirdStar="";
    }
    
    //id
    public void setid(String id){
        this.id=id;
    }
    public String getid(){
        return this.id;
    }
    //name
    public void setname(String name){
        this.name = name;
    }
    public String getname(){
        return this.name;
    }
    //password
    public void setpassword(String password){
        this.password=password;
    }
    public String getpassword(){
        return this.password;
    }
    //gender
    public void setgender(String gender){
        this.gender=gender;
    }
    public String getgender(){
        return this.gender;
    }
    //first
    public void setfirstStar(String firstStar){
        this.firstStar=firstStar;
    }
    public String getfirstStar(){
        return this.firstStar;
    }
    //second
    public void setsecondStar(String secondStar){
        this.secondStar=secondStar;
    }
    public String getsecondStar(){
        return this.secondStar;
    }
    //thirdStar
    public void setthirdStar(String thirdStar){
        this.thirdStar=thirdStar;
    }
    public String GetthirdStar(){
        return this.thirdStar;
    }
}
