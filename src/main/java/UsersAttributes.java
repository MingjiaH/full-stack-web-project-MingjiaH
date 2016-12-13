import spark.ModelAndView;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

import static spark.Spark.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.heroku.sdk.jdbc.DatabaseUrl;


public class UsersAttributes{
  private UserService us = new UserService();
  private ServiceUtils su = new ServiceUtils();
  private Gson gson = new GsonBuilder().create();
  private String tempUserName = "tempUserName";
  private NewsService ns =  new NewsService();
  private XmlUtils xu = new XmlUtils();

  public UsersAttributes(){

     //login and put user in the session
    get("/login", (req, res) -> {
      Map<String, Object> data = new HashMap<>();
      Session session = req.session(true);
      String userName = req.queryParams("userName");
      String password = req.queryParams("password");
      System.out.println(userName+":"+password);
      tempUserName = userName;
      User u=us.getUser(userName,password);
      if (u != null) {
        session.attribute("firstStar", u.getfirstStar());
        session.attribute("secondStar", u.getsecondStar());
      } else {
        // TODO: user does not exist.
      }
      session.attribute("user",u);
      session.attribute("userName", userName);
      data.put("userName", userName);
      data.put("password", password);
      System.out.println(u.getsecondStar());
      return gson.toJson(data);
    });
    //register
    post("/register", (req, res) -> {
      Session session = req.session(true);
      Map<String, Object> data = new HashMap<>();
      String userName = req.queryParams("userName");
      String password = req.queryParams("password");
      String email = req.queryParams("email");
      String gender = req.queryParams("gender");
      String firstStar = req.queryParams("firstStar");
      String secondStar = req.queryParams("secondStar");
      String thirdStar = req.queryParams("thirdStar");
      User u = us.createUser(userName,password,email,gender,firstStar,secondStar,thirdStar);
      System.out.println("hello"+u.getname()+"::::"+u.getpassword());
      data.put("userName", userName);
      data.put("password", password);
      data.put("email", email);
      data.put("gender", gender);
      data.put("firstStar", firstStar);
      data.put("secondStar", secondStar);
      data.put("thirdStar", thirdStar);
      tempUserName = userName;
      session.attribute("userName",userName);
      session.attribute("firstStar", u.getfirstStar());
      session.attribute("secondStar", u.getsecondStar());
      return data;
    }, gson::toJson);
   

    //index page to render
    get("/index", (req, res) -> {
      HashMap<String, Object> attributes = new HashMap<>();
      System.out.println("render index"+":"+tempUserName);
      Session session = req.session(true);
      String userName = session.attribute("userName");
      if(userName!=null){
        attributes.put("username",userName);
      }else {
        attributes.put("username","Guest");
      }
      // if (su.hasUserLoggedIn(req, res)) {
        // User u = (User) session.attribute("user");
        // u.setname(tempUserName);
        // attributes.put("user", u);
        // attributes.put("username", tempUserName);
        System.out.println("username"+"::::::"+attributes.get("username"));
     // }
      return new ModelAndView(attributes, "index.ftl");
    }, new FreeMarkerEngine());


    get("/checkLogin",(req,res)->{
      Session session = req.session(true);
      String userName = session.attribute("userName");
      HashMap<String, String> attributes = new HashMap<>();
      attributes.put("userName", userName);
      return attributes;
    }, gson::toJson); 
    
    //log out
    get("/logOut",(req,res)->{
      Session session = req.session(true);
      session.removeAttribute("userName");
      session.removeAttribute("user");
      System.out.println("log out Success");
      return "Success";
    });



    //newsInTopic
    get("/newsInTopic",(req,res)->{
      HashMap<String, Object> attributes = new HashMap<>();
      Session session = req.session(true);
      res.type("application/xml");
      String userName = session.attribute("userName");
      System.out.println(userName);
      User u=session.attribute("user");
      Map<String, HashMap<Integer, News>> newsCollection = new HashMap<String,HashMap<Integer,News>>();
      newsCollection=ns.getUserNews();
      System.out.println(newsCollection.size());
      System.out.println(session.attribute("firstStar")+":::::"+session.attribute("secondStar"));
      
      attributes.put("user",u);
      attributes.put("newsCollection",newsCollection); 
      String firstStar = session.attribute("firstStar");
      String secondStar =  session.attribute("secondStar");
      return xu.buildNewsXML(firstStar,secondStar, newsCollection);
    });



    //newsRandom
    get("/newsRandom",(req,res)->{
      HashMap<String, Object> attributes = new HashMap<>();
      List<News> newsCollection = new ArrayList<News>();
      newsCollection = ns.getAllNews();
      if(newsCollection.size() == 0 )
        System.out.print("No news Here");
      attributes.put("newsCollection",newsCollection);
      return new ModelAndView(attributes, "newsRandom.ftl");
    }, new FreeMarkerEngine());
  } 

}