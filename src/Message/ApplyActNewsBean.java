package Message;

public class ApplyActNewsBean {
	private int id;
	private int cid;
	private String cname;
	private String master;
	private String title;
	private String content;
	private String time;
	private String filename;
	private String img;
	
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getCid(){
		return cid;
	}
	public void setCid(int cid){
		this.cid=cid;
	}
	public String getCname(){
		return cname;
	}
	public void setCname(String cname){
		this.cname=cname;
	}
	public String getMaster(){
		return master;
	}
	public void setMaster(String master){
		this.master=master;
	}
	public String getTitle(){
		return title;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getContent(){
		return content;
	}
	public void setContent(String content){
		this.content=content;
	}
	public String getTime(){
		return time;
	}
	public void setTime(String time){
		this.time=time;
	}
	public String getFilename(){
		return filename;
	}
	public void setFilename(String filename){
		this.filename=filename;
	}
	public String getImg(){
		return img;
	}
	public void setImg(String img){
		this.img=img;
	}
	
}
