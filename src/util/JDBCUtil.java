package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


	/*
	 * 1��������������Ŀ�������أ�����ֻ����һ�Σ�
	 * 2����������Connection
	 * 3���������ݿ��������
	 * 4��ִ��SQL����
	 * 5���ر�����
	 */

/*
 * ����Ա�������ݿ�
 */
public class JDBCUtil {
	private static String url="jdbc:mysql://localhost:3306/mytest";
	private static String username="root";
	private static String password="19980430";
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");         //����
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



