package exam01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UserDao {
	private ConnectionMaker connectionMaker;
	public UserDao() {
		connectionMaker=new DConnectionMaker();
	}
	public void add(User user) throws ClassNotFoundException, SQLException{
		Connection c=connectionMaker.makeConnection();
		
		PreparedStatement ps=c.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}

	public User get(String id) throws ClassNotFoundException, SQLException{
		Connection c=connectionMaker.makeConnection();
		
		PreparedStatement ps=c.prepareStatement("select * from users where id=?");
		ps.setString(1, id);
		
		ResultSet rs=ps.executeQuery();
		rs.next();
		User user=new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("pasword"));
		
		rs.close();
		ps.close();
		c.close();
		
		return user;
	}
	public abstract Connection getConnection() throws ClassNotFoundException, SQLException;
	//	{
	//	Class.forName("com.mysql.jdbc.Driver");
	//	Connection c=DriverManager.getConnection("jdbc:mysql://localhost/springbook","spring","book");
	//	return c;
	//	}
	
}

class NUserDao extends UserDao{
	public Connection getConnection() throws ClassNotFoundException, SQLException{
		
	}
}


class DUserDao extends UserDao{
	public Connection getConnection() throws ClassNotFoundException, SQLException{
		
	}
}
