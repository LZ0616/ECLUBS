package Student;

import java.util.List;



public class StudentSer {
	
	StudentDAO stuDao = new StudentDAO(); 
	
	public List<SelfStuBean> getStuList(){
		return stuDao.getStuList();
	} 

	public List<SelfStuBean> getStuByFuzzy(String keyword){
		return stuDao.getStuByFuzzy(keyword);
	}
	
	public List<SelfStuBean> getStuByAccount(String account){
		return stuDao.getStuByAccount(account);
	}
	
	public List<SelfStuBean> getClubMember(String cname){
		return stuDao.getClubMember(cname);
	} 
	
	public SelfStuBean alterStuByAccount(String account){
		return stuDao.alterStuByAccount(account);
	}
	
	public void updateStu(SelfStuBean stu) {
		stuDao.updateStu(stu);
	}
	public void deleteStu(String account) {
		stuDao.deleteStu(account);
	}
	
	public void outStu(int cid, String account){
		stuDao.outStu(cid, account);
	}
}
