package Student;

public class ClubMemberBean {
	private int id;
	private int cid;
	private String cname;
	private String master;
	private String account;
	private String sname;
	private String pos;
	private String date;
	
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
	public String getAccount(){
		return account;
	}
	public void setAccount(String account){
		this.account=account;
	}
	public String getSname(){
		return sname;
	}
	public void setSname(String sname){
		this.sname=sname;
	}
	public String getPos(){
		return pos;
	}
	public void setPos(String pos){
		this.pos=pos;
	}
	public String getDate(){
		return date;
	}
	public void setDate(String date){
		this.date=date;
	}
}
