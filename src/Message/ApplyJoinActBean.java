package Message;

public class ApplyJoinActBean {
	private int id;
	private String account;
	private int cid;
	private int aid;
	private String sname;
	private String atitle;
	private String time;
	private String aclub;
	
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id=id;
	}
	public String getAccount(){
		return account;
	}
	public void setAccount(String account){
		this.account=account;
	}
	public int getCid(){
		return cid;
	}
	public void setCid(int cid){
		this.cid=cid;
	}
	public int getAid(){
		return aid;
	}
	public void setAid(int aid){
		this.aid=aid;
	}
	public String getSname(){
		return sname;
	}
	public void setSname(String sname){
		this.sname=sname;
	}
	public String getAtitle(){
		return atitle;
	}
	public void setAtitle(String atitle){
		this.atitle=atitle;
	}
	public String getTime(){
		return time;
	}
	public void setTime(String time){
		this.time=time;
	}
	public String getAclub(){
		return aclub;
	}
	public void setAclub(String aclub){
		this.aclub=aclub;
	}
	
}
