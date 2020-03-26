package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


	/*
	 * 1、加载驱动（项目启动加载，而且只加载一次）
	 * 2、创建连接Connection
	 * 3、创建数据库操作对象
	 * 4、执行SQL语言
	 * 5、关闭连接
	 */

/*
 * 管理员连接数据库
 */
public class JDBCUtil {
	private static String url="jdbc:mysql://localhost:3306/mytest";
	private static String username="root";
	private static String password="19980430";
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");         //反射
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}          
	}
	public static Connection getConnection() throws SQLException{
			Connection conn=DriverManager.getConnection(url, username, password);
			return conn;
	}
}



