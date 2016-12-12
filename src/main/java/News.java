import java.util.Date;
import java.util.UUID;

public class News {

    private String id;
    private String date;
    private String firstTitle;
    private String secondTitle;
    private String content;
    private String firstTopic;
    private String secondTopic;
    private String thirdTopic;
    private String picUrl;
    
    News(){}
    News(String firstTitle, String secondTitle,String content,String firstTopic,
          String secondTopic,String thirdTopic,String picUrl){
      UUID uuid = UUID.randomUUID();  
      String id = uuid.toString();
      Date date = new Date();
      this.id = id;
      this.date = date.toString(); 
      this.firstTitle = firstTitle;
      this.secondTitle=secondTitle;
      this.content=content;
      this.firstTopic=firstTopic;
      this.secondTopic=secondTopic;
      this.thirdTopic=thirdTopic;
      this.picUrl=picUrl;
    }
    News(String firstTitle, String secondTitle,String content,String firstTopic){
      UUID uuid = UUID.randomUUID();  
      String id = uuid.toString();
      Date date = new Date();
      
      this.id = id;
      this.id = id;
      this.date = date.toString(); 
      this.firstTitle = firstTitle;
      this.secondTitle=secondTitle;
      this.content=content;
      this.firstTopic=firstTopic;
      this.secondTopic="";
      this.thirdTopic="";
    }
    
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getFirstTitle() {
		return firstTitle;
	}
	public void setFirstTitle(String firstTitle) {
		this.firstTitle = firstTitle;
	}
	public String getSecondTitle() {
		return secondTitle;
	}
	public void setSecondTitle(String secondTitle) {
		this.secondTitle = secondTitle;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFirstTopic() {
		return firstTopic;
	}
	public void setFirstTopic(String firstTopic) {
		this.firstTopic = firstTopic;
	}
	public String getSecondTopic() {
		return secondTopic;
	}
	public void setSecondTopic(String secondTopic) {
		this.secondTopic = secondTopic;
	}
	public String getThirdTopic() {
		return thirdTopic;
	}
	public void setThirdTopic(String thirdTopic) {
		this.thirdTopic = thirdTopic;
	}
	//id
    public void setid(String id){
        this.id=id;
    }
    public String getid(){
        return this.id;
    }
    public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
}

