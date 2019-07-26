package Message;

public class ApplyCreateClubBean {
	private int id;
	private String account;
	private String cname;
	private String master;
	private String intro;
	private String rules;
	private String time;
	private String filename;
	private String img;
	
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
	public String getIntro(){
		return intro;
	}
	public void setIntro(String intro){
		this.intro=intro;
	}
	public String getRules(){
		return rules;
	}
	public void setRules(String rules){
		this.rules=rules;
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
