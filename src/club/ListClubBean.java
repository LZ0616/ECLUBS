package club;

public class ListClubBean {
	private int id;
	private String cname;
	private String master;
	private String time;
	private String intro;
	private String rules;
	private int sum_member;
	private int sum_act;
	private String filename;
	
	public int getId(){
		return id;
	}
	public String getCname(){
		return cname;
	}
	public String getMaster(){
		return master;
	}
	public String getTime(){
		return time;
	}
	public String getIntro(){
		return intro;
	}
	public String getRules(){
		return rules;
	}
	
	public void setId(int id){
		this.id=id;
	}
	public void setCname(String cname){
		this.cname=cname;
	}
	public void setMaster(String master){
		this.master=master;
	}
	public void setTime(String time){
		this.time=time;
	}
	public void setIntro(String intro){
		this.intro=intro;
	}
	public void setRules(String rules){
		this.rules=rules;
	}
	public void setFilename(String filename){
		this.filename = filename ;
	}
	public String getFilename(){
		return filename ;
	}
	public int getSum_member(){
		return sum_member;
	}
	public int getSum_act(){
		return sum_act;
	}
	public void setSum_member(int sum_member){
		this.sum_member=sum_member;
	}
	public void setSum_act(int sum_act){
		this.sum_act=sum_act;
	}
}
