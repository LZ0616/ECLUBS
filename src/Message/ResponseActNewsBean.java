package Message;

public class ResponseActNewsBean {
	private int id;
	private int cid;
	private String content;
	private int flag;
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
	public String getContent(){
		return content;
	}
	public void setContent(String content){
		this.content=content;
	}
	public int getFlag(){
		return flag;
	}
	public void setFlag(int flag){
		this.flag=flag;
	}
}
