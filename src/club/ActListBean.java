package club;

public class ActListBean {
	private int id;
	private String cname;
	private String master;
	private String title;
	private String content;
	private String time;
	private String filename;
	
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id=id;
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
	public void setContent (String content){
		this.content=content;
	}
	public String getTime(){
		return time;
	}
	public void setTime(String time){
		this.time=time;
	}
	public String getFilename(){
		return time;
	}
	public void setFilename(String filename){
		this.filename=filename;
	}
}
