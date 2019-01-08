package JDBC.RowSet;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class RowSetTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		RowSetTest rowSetTest = new RowSetTest();
		// rowSetTest.testCachedRowSet1();
		// rowSetTest.testCachedRowSet2(1, 2);
		rowSetTest.testJdbcRowSet();
	}

	public void testJdbcRowSet() throws SQLException {
		RowSetFactory factory = RowSetProvider.newFactory();
		try (JdbcRowSet js = factory.createJdbcRowSet()) {
			js.setUrl("jdbc:mysql://172.16.11.7:3306/bdam_test?useSSL=false");
			js.setUsername("test");
			js.setPassword("test");
			js.setCommand("select * from img_table");
			js.execute();
			ResultSet rs = js.getStatement().getResultSet();
			while (rs.next()) {
				Blob data = rs.getBlob(3);
				System.out.println(rs.getString(1) + rs.getString(2) + data);
			}
		}

	}

	/**
	 * ���߸��£�������;���ԶϿ���ָֻ���һ��ҳ�飩
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void testCachedRowSet1() throws ClassNotFoundException, SQLException {
		CachedRowSet cachedRowSet = this.fillCachedRowSetMethod(2, 2);
		// Moves the cursor to the end of this ResultSet object, just after the
		// last row. This method has no effect if the result set contains no
		// rows.
		// cachedRowSet.afterLast();cachedRowSet.previous()�����ʹ�����������cachedRowSet.next()���������
		cachedRowSet.afterLast();
		while (cachedRowSet.previous()) {
			System.out.println(cachedRowSet.getString(1) + " " + cachedRowSet.getString(2));
			cachedRowSet.updateString(2, cachedRowSet.getString(2) + "new");
			cachedRowSet.updateRow();
		}
		// ������ͬ������ʵ���ݿ�
		Connection conn = DriverManager.getConnection("jdbc:mysql://172.16.11.7:3306/bdam_test?useSSL=false", "test",
				"test");
		conn.setAutoCommit(false);// һ��Ҫ����
		cachedRowSet.acceptChanges(conn); // ����֮ǰ������Դ�Ѷϣ����Ҫ�����вΰ汾��
	}

	/**
	 * ������ҳ����;���Ӳ��ܶϿ�����Ϊ�ڴ���ֻ�ܴ洢һҳ��ѭ������ÿҳ��¼ʱ���ڴ��е�ҳ�鲻���滻�����߾��Ҳ����ˡ��������ĳһҳ�ǿ������ߵģ�
	 * 
	 * @param pageSize
	 * @param page
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void testCachedRowSet2(int pageSize, int page) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		// �ر�������Դ
		Connection conn = DriverManager.getConnection("jdbc:mysql://172.16.11.7:3306/bdam_test?useSSL=false", "test",
				"test");
		ResultSet rs = conn.createStatement().executeQuery("select * from img_table");
		RowSetFactory factory = RowSetProvider.newFactory();
		CachedRowSet cachedRowSet = factory.createCachedRowSet();
		/*
		 * ����ÿ�λ�ȡ��cache��¼���ǣ���¼��ʼλ�ã���¼��ʼλ��+1����¼��ʼλ��+2��...����¼��ʼλ��+pageSize-1
		 */
		cachedRowSet.setPageSize(pageSize);// ���û�����С
		cachedRowSet.populate(rs, (page - 1) * pageSize + 1);// ���ü�¼��ʼλ�ã�λ�ô�1��ʼ������0
		// Moves the cursor to the end of this ResultSet object, just after the
		// last row. This method has no effect if the result set contains no
		// rows.
		// cachedRowSet.afterLast();cachedRowSet.previous()�����ʹ�����������cachedRowSet.next()���������
		// �������ǰҳ
		cachedRowSet.afterLast();
		while (cachedRowSet.previous()) {
			System.out.println(cachedRowSet.getString(1) + " " + cachedRowSet.getString(2));
			cachedRowSet.updateString(2, cachedRowSet.getString(2) + "new");
			cachedRowSet.updateRow();
		}
		// Ȼ����һҳ
		int i = 0;
		while (cachedRowSet.nextPage()) {
			i++;
			System.out.println("====��" + i + "ҳ====================");
			while (cachedRowSet.next()) {
				System.out.println(cachedRowSet.getString(1) + " " + cachedRowSet.getString(2));
				cachedRowSet.updateString(2, cachedRowSet.getString(2) + "new");
				cachedRowSet.updateRow();
			}
		}
	}

	/**
	 * ����CachedRowSet
	 * 
	 * @param pageSize
	 * @param page
	 * @return
	 * @throws ClassNotFoundException
	 */
	public CachedRowSet fillCachedRowSetMethod(int pageSize, int page) throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		try (// �ر�������Դ
				Connection conn = DriverManager.getConnection("jdbc:mysql://172.16.11.7:3306/bdam_test?useSSL=false",
						"test", "test");
				ResultSet rs = conn.createStatement().executeQuery("select * from img_table");) {
			RowSetFactory factory = RowSetProvider.newFactory();
			CachedRowSet cache = factory.createCachedRowSet();
			/*
			 * ����ÿ�λ�ȡ��cache��¼���ǣ���¼��ʼλ�ã���¼��ʼλ��+1����¼��ʼλ��+2��...����¼��ʼλ��+pageSize-1
			 */
			cache.setPageSize(pageSize);// ���û�����С
			cache.populate(rs, (page - 1) * pageSize + 1);// ���ü�¼��ʼλ�ã�λ�ô�1��ʼ������0
			return cache;// �������ص�RowSet��Ȼ���ã�˵�������߻�����
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
