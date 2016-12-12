import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class NewsService{
  // returns a list of all users
  private List<News> newsCollection = new ArrayList<News>();
    private Map<String, HashMap<Integer, News>> newsMap = new HashMap<String,HashMap<Integer,News>>();
    
  NewsService(){
     this.setAllNews();
     this.setUserNews();
  }
  
  public void setAllNews(){
      int n=7;
      News[] news=new News[n];
      news[0] = new News("IOI","Final Stage","I.O.I. is recently gaining fans for their talents and personalities. But as soon as they finally have established a reputation in the entertainment industry, I.O.I. had to bid farewell as the date of their disbandment approaches. I.O.I. performed “Very Very Very” on their final stage as group on KBS Music Bank on Nov. 4.","IOI","FINAL","","img/ioi.jpg");
      news[1] = new News("EXO-CBX","Hey!Mama!","EXO-CBX -- composed of Chen, Baekhyun, and Xiumin -- released a mini album on midnight of Monday, Oct. 31 with the title track, Hey Mama!, as reported by All Kpop. The trio's Hey Mama! music video is also gathering a lot of hits -- as of this writing the video is close to four million views on YouTube, less than 24 hours before it was released. "
                     + "The album has a total of five songs which include a combination of pop, disco and funk genres. Other tracks in the album include:The One, Rhythm After Summer,Juliet  and Cherish.The unit is scheduled to have their debut stages on Nov. 3 on M! Countdown, Nov. 4 on Music Bank, Nov. 5 on Show! Music Core and Nov. 6 on Inkigayo.","EXO","CBX","","img/CBX.jpg");
      news[2] = new News("VIXX","Comeback Stage","VIXX expresses their confidence in their ideas for comeback concepts.On October 31 at the Yes24 Live Hall in Seoul, the boy group held a showcase event for their third mini-album Kratos.While discussing their next concept during the showcase, N shares, We even talked about doing a Tarzan concept. Concepts really come to us without [much effort]. They also come to us while reading comics or watching movies. That’s why we never feel like we’ve run out of ideas.","VIXX","","","img/vixx.jpg");
      news[3] = new News("EXO","winter album","EXO winter album 2016 — SM Entertainment just revealed exciting details about EXO’s special album release this winter. On Nov. 30, the Korean entertainment company announced that the boy band’s comeback will happen this month. A new album and music video have also been confirmed for release this December, as EXO continues its tour in Asia.","EXO","Album","","img/exo_winter.jpg");
      news[4] = new News("Bigbang","SBS Gayo Daejun","BIGBANG was confirmed to appear on the 2016 SBS Gayo Daejun. The group will be performing their upcoming new songs through this stage following the release of their “MADE” full album on December 12.The 2016 SBS Gayo Daejun is apart of SBS’s SAF (SBS Awards Festival) which will take place from December 22 to December 26 at COEX.","Bigbang","SBS","","img/bigbang_sbs.jpg");
      news[5] = new News("EXO","Winner in MAMA 2016","South Korean idol groups Bangtan Boys (BTS), EXO and TWICE won big at the 2016 Mnet Asian Music Awards (MAMA) at AsiaWorld-Expo Arena in Hong Kong.K-pop boy band EXO won the Album of the Year award for the fourth consecutive year.","MAMA","EXO","BTS","img/exo_mama.jpg"); 
      news[6] = new News("Bigbang","New Album","BIGBANG 2016 news update – Fans and supporters of this South Korean boy band are in for a treat! After the release of their singles such as Loser, We Like 2 Party, If You, and a few more from their special project single album, MADE, it is reported that BIGBANG is “secretly recording” their new album.","Bigbang","Album","","img/bigbang.png"); 

      for(int i=0;i<n;i++){
        newsCollection.add(news[i]);
      }
      System.out.println(newsCollection.size());
      
    } 

  public void setUserNews(){
      for(News news:newsCollection){
        if(newsMap.containsKey(news.getFirstTopic())){
          HashMap<Integer, News> newNews = new HashMap<Integer, News>();
          newNews = newsMap.get(news.getFirstTopic());
          newNews.put(newNews.size(), news);
        }else{
          HashMap<Integer, News> newNews = new HashMap<Integer, News>();
          newNews.put(0, news);
          newsMap.put(news.getFirstTopic(), newNews);
        }
        
      }
      
  }
  
  public List<News> getAllNews() {
      return newsCollection;
    }
  public Map<String, HashMap<Integer, News>> getUserNews(){
      return newsMap;
  }
    // returns a single user by id
    public News getNews(String firstTitle,String firstTopic) throws Exception {
          Connection connection = null;
          News n = null;
          try {
              // TODO: SELECT user by userAccount from database.
              UUID newsId = UUID.randomUUID();
              Integer id = 1;
              n=newsMap.get(firstTopic).get(id);
              return newsId != null ? null : n;
              
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
   
    // creates a new news
    public News createNews(String firstTitle, String secondTitle,String content,String firstTopic,
            String secondTopic,String thirdTopic,String picUrl) throws Exception {
         
       News news = new News(firstTitle,secondTitle,content,firstTopic,secondTopic,thirdTopic,picUrl);
       newsCollection.add(news);
       String id=firstTitle+firstTopic;
       
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
         return news;
         
    }
   
    // delete news
    public void deleteNews(String firstTitle,String firstTopic) throws Exception {
      News s=getNews(firstTitle,firstTopic);
      if(s!=null)
         newsCollection.remove(s);
      else 
         System.out.println("Not existing");  
    }
}