package JDBC;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class MetaDataTest {
	static Connection conn = null;
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://172.16.11.7:3306/bdam_test?useSSL=false", "test", "test");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testResultSetMetaData() throws SQLException {
		PreparedStatement pst = conn.prepareStatement("select * from img_table where img_id=?");
		pst.setInt(1, 1);
		ResultSet rs = pst.executeQuery();
		ResultSetMetaData meta = rs.getMetaData();
		if (rs.next()) {
			Blob data = rs.getBlob(3);
			System.out.println(rs.getString(1) + rs.getString(2) + data);
		}
		for (int i = 1; i <= meta.getColumnCount(); i++) {
			System.out.println(meta.getColumnName(i));
			System.out.println(meta.getColumnType(i));// java.sql.Types
		}
	}

	public static void testSystemTables() {
		// MYSQLʹ��information_schema���ݿⱣ��ϵͳ��
		// tables
		// schemata:���ݿ�
		// views\columns\triggers\routines(�洢���̺ͺ���)\key_column_usage(Լ������Ϣ)\table_constraints\statistics(������Ϣ)
	}

	public static void testDataBaseMetaData() throws SQLException {
		DatabaseMetaData db = conn.getMetaData();
		ResultSet rs = db.getTableTypes();
		System.out.println("==========Mysql֧�ֵı����ͣ�");
		printResultSet(rs);
		rs = db.getTables(null, null, "%", new String[] { "TABLE" });
		System.out.println("==========��ȡȫ�����ݿ��");
		printResultSet(rs);
		rs = db.getPrimaryKeys(null, null, "img_table");
		System.out.println("==========��ȡ���ݿ���������");
		printResultSet(rs);
		rs = db.getProcedures(null, null, "%");
		System.out.println("==========��ȡ���ݿ��ȫ���洢���̣�");
		printResultSet(rs);
		rs = db.getCrossReference(null, null, "a", null, null, "b");
		System.out.println("==========��ȡa��b������Լ����");
		printResultSet(rs);
		rs = db.getColumns(null, null, "img_table", "%");
		System.out.println("==========��ȡĳ�ű��������У�");
		printResultSet(rs);
	}

	private static void printResultSet(ResultSet rs) throws SQLException {
		ResultSetMetaData mata = rs.getMetaData();
		for (int i = 0; i < mata.getColumnCount(); i++) {
			System.out.println("������" + mata.getColumnName(i + 1));
		}
		while (rs.next()) {
			for (int i = 0; i < mata.getColumnCount(); i++) {
				System.out.println("==" + rs.getObject(i + 1));
			}
		}
		rs.close();
	}

	public static void main(String[] args) throws SQLException {
		MetaDataTest.testResultSetMetaData();
	}
}
