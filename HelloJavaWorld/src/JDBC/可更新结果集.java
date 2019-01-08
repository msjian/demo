package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * �ɸ��µĽ����������ͨ��ֻ����Դ��һ�������Ҳ�ѯ�Ľ�������������������������ʧ�� 
 * �����������������ݿⶼ֧�֣�
 *
 * @author zhaohe
 *
 */
public class �ɸ��½���� {
	/**
	 * �鿴�Ƿ�֧��ĳЩ����
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void test() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		try (Connection con = DriverManager.getConnection("jdbc:mysql://172.16.11.7:3306/bdam_test?useSSL=false",
				"test", "test");) {
			// �鿴�Ƿ�֧��
			// false
			System.out.println(con.getMetaData().supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE));
			// true
			System.out.println(con.getMetaData().supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE));
			// true
			System.out.println(con.getMetaData().supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE));
		}

	}

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		try (Connection con = DriverManager.getConnection("jdbc:mysql://172.16.11.7:3306/bdam_test?useSSL=false",
				"test", "test");
				PreparedStatement sta =con.prepareStatement("select * from img_table",
						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = sta.executeQuery();) {
			// ��ָ���ƶ������һ��
			rs.last();
			int rowCount = rs.getRow();
			System.out.println("�ܹ���¼�� ��" + rowCount);
			for (int i = rowCount; i > 0; i--) {
				rs.absolute(i);
				System.out.println(rs.getString(1) + " ; " + rs.getString(2));
				// ���ݿ������еڶ��е�ֵ���ᱻ�޸�
				rs.updateString(2, "new " + rs.getString(2));
				// �ύ�޸�
				rs.updateRow();
			}

		}
	}
}
