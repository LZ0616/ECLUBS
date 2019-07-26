package Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exper.DBHelper;

public class StudentDAO {
	Connection connection = null;	
	PreparedStatement statement = null;
	ResultSet set = null;

	public List<SelfStuBean> getStuList(){
		List<SelfStuBean> listStu = new ArrayList<SelfStuBean>();
		connection = new DBHelper().getConn();
		String sql ="select * from student";
		try {
			statement=connection.prepareStatement(sql);
			set =statement.executeQuery();	
			while (set.next()) {	
				SelfStuBean st = new SelfStuBean();
				st.setId(set.getInt("id"));
				st.setAccount(set.getString("account"));
				st.setSname(set.getString("sname"));
				st.setSno(set.getInt("sno"));
				st.setSex(set.getString("sex"));
				st.setAge(set.getInt("age"));
				st.setDep(set.getString("dep"));
				st.setPhone(set.getString("phone"));
				listStu.add(st);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				set.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listStu;
	}

	public List<SelfStuBean> getStuByFuzzy(String keyword){
		SelfStuBean st = null;
		List<SelfStuBean> listStu = new ArrayList<SelfStuBean>();
		connection = new DBHelper().getConn();
		String sql ="select * from student where account like ? or sno like ? or sname like ?";
		try {
			statement=connection.prepareStatement(sql);
			statement.setString(1,"%"+keyword+"%") ;
			statement.setString(2,"%"+keyword+"%") ; 
			statement.setString(3,"%"+keyword+"%") ;
			set =statement.executeQuery();
			while (set.next()) {
				st = new SelfStuBean();
				st.setId(set.getInt("id"));
				st.setAccount(set.getString("account"));
				st.setSname(set.getString("sname"));
				st.setSno(set.getInt("sno"));
				st.setSex(set.getString("sex"));
				st.setAge(set.getInt("age"));
				st.setDep(set.getString("dep"));
				st.setPhone(set.getString("phone"));
				listStu.add(st);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				set.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listStu;
	}
	
	public List<SelfStuBean> getClubMember(String cname){
		SelfStuBean st = null;
		List<SelfStuBean> listStu = new ArrayList<SelfStuBean>();
		connection = new DBHelper().getConn();
		String sql ="select * from club_member where cname=? and pos='社长'";
		try {
			statement=connection.prepareStatement(sql);
			statement.setString(1,cname);
			set =statement.executeQuery();
			while (set.next()) {
				String account = set.getString("account");
				String pos = set.getString("pos");
				String sql1 ="select * from student where account=?";
				try{
					statement=connection.prepareStatement(sql1);
					statement.setString(1,account);
					ResultSet set1 =statement.executeQuery();
				
					while(set1.next()){
						st = new SelfStuBean();
						st.setId(set1.getInt("id"));
						st.setAccount(set1.getString("account"));
						st.setSno(set1.getInt("sno"));
						st.setSname(set1.getString("sname"));
						st.setSex(set1.getString("sex"));
						st.setAge(set1.getInt("age"));
						st.setDep(set1.getString("dep"));
						st.setPhone(set1.getString("phone"));
						st.setPos(pos);
						listStu.add(st);
					}
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sql ="select * from club_member where cname=? and pos!='社长' and pos!='成员'";
		try {
			statement=connection.prepareStatement(sql);
			statement.setString(1,cname);
			set =statement.executeQuery();
			while (set.next()) {
				String account = set.getString("account");
				String pos = set.getString("pos");
				String sql1 ="select * from student where account=?";
				try{
					statement=connection.prepareStatement(sql1);
					statement.setString(1,account);
					ResultSet set1 =statement.executeQuery();
				
					while(set1.next()){
						st = new SelfStuBean();
						st.setId(set1.getInt("id"));
						st.setAccount(set1.getString("account"));
						st.setSno(set1.getInt("sno"));
						st.setSname(set1.getString("sname"));
						st.setSex(set1.getString("sex"));
						st.setAge(set1.getInt("age"));
						st.setDep(set1.getString("dep"));
						st.setPhone(set1.getString("phone"));
						st.setPos(pos);
						listStu.add(st);
					}
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sql ="select * from club_member where cname=? and pos='成员'";
		try {
			statement=connection.prepareStatement(sql);
			statement.setString(1,cname);
			set =statement.executeQuery();
			while (set.next()) {
				String account = set.getString("account");
				String pos = set.getString("pos");
				String sql1 ="select * from student where account=?";
				try{
					statement=connection.prepareStatement(sql1);
					statement.setString(1,account);
					ResultSet set1 =statement.executeQuery();
				
					while(set1.next()){
						st = new SelfStuBean();
						st.setId(set1.getInt("id"));
						st.setAccount(set1.getString("account"));
						st.setSno(set1.getInt("sno"));
						st.setSname(set1.getString("sname"));
						st.setSex(set1.getString("sex"));
						st.setAge(set1.getInt("age"));
						st.setDep(set1.getString("dep"));
						st.setPhone(set1.getString("phone"));
						st.setPos(pos);
						listStu.add(st);
					}
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			try {
				set.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listStu;
	}
	
	public List<SelfStuBean> getStuByAccount(String account){
		SelfStuBean st = null;
		List<SelfStuBean> listStu = new ArrayList<SelfStuBean>();
		connection = new DBHelper().getConn();
		String sql ="select * from student where account=?";
		try {
			statement=connection.prepareStatement(sql);
			statement.setString(1, account);
			set =statement.executeQuery();
			while (set.next()) {
				st = new SelfStuBean();
				st.setId(set.getInt("id"));
				st.setAccount(set.getString("account"));
				st.setPassword(set.getString("password"));
				st.setSname(set.getString("sname"));
				st.setSno(set.getInt("sno"));
				st.setSex(set.getString("sex"));
				st.setAge(set.getInt("age"));
				st.setDep(set.getString("dep"));
				st.setPhone(set.getString("phone"));
				listStu.add(st);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				set.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listStu;
	}
	
	public SelfStuBean alterStuByAccount(String account){
		SelfStuBean st =null;
		connection = new DBHelper().getConn();
		String sql ="select * from student where account=?";
		try {
			statement=connection.prepareStatement(sql);
			statement.setString(1, account);
			set =statement.executeQuery();
			while (set.next()) {
				st = new SelfStuBean();
				st.setId(set.getInt("id"));
				st.setAccount(set.getString("account"));
				st.setPassword(set.getString("password"));
				st.setSname(set.getString("sname"));
				st.setSno(set.getInt("sno"));
				st.setSex(set.getString("sex"));
				st.setAge(set.getInt("age"));
				st.setDep(set.getString("dep"));
				st.setPhone(set.getString("phone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				set.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return st;
	}

	public void updateStu(SelfStuBean stu) {
		connection = new DBHelper().getConn();
		String sql ="update student set sname=?,password=?,age=?,dep=?,phone=? where account=?";
		try {
			statement=connection.prepareStatement(sql);
			statement.setString(1, stu.getSname());
			statement.setString(2, stu.getPassword());
			statement.setInt(3, stu.getAge());
			statement.setString(4, stu.getDep());
			statement.setString(5, stu.getPhone());
			statement.setString(6, stu.getAccount());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sql ="update club_member set sname=? where account=?";
		try {
			statement=connection.prepareStatement(sql);
			statement.setString(1, stu.getSname());
			statement.setString(2, stu.getAccount());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteStu(String account) {
		connection = new DBHelper().getConn();
		try {
			String sql = "select id from student where account=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, account);
			ResultSet set = statement.executeQuery();
			
			int id = 0;
			if(set.next()){
				id = set.getInt("id");
			}
			
			sql ="delete from student where account=?";
			statement=connection.prepareStatement(sql);
			statement.setString(1, account);
			statement.executeUpdate();
			
			sql = "update student set id=id-1 where id>?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeUpdate();
			
			sql = "select id from club_member where account=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, account);
			set = statement.executeQuery();
			
			if(set.next()){
				id = set.getInt("id");
			}
			
			sql ="delete from club_member where account=?";
			statement=connection.prepareStatement(sql);
			statement.setString(1, account);
			statement.executeUpdate();
			
			sql = "update club_member set id=id-1 where id>?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	public void outStu(int cid, String account){
		connection = new DBHelper().getConn();
		try {
			String sql = "select id from club_member where account=? and cid=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, account);
			statement.setInt(2, cid);
			ResultSet set = statement.executeQuery();
			
			int id = 0;
			if(set.next()){
				id = set.getInt("id");
			}
			
			sql ="delete from club_member where id=?";
			statement=connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeUpdate();
			
			sql = "update club_member set id=id-1 where id>?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
