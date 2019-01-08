package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

/**
 * BEGIN; INSERT INTO img_table(img_name,img_data) VALUES('img01',null);
 * #��ǰ���ڿ��Բ鵽������¼���������������ڲ�ѯ����������¼�����ﻹû���ύ
 * SELECT * FROM img_table; 
 * rollback;
 * #��ǰ���ڲ鲻��������¼����Ϊ�����Ѿ��ع�
 *  SELECT * FROM img_table;
 * #================================================================= B
 * EGIN;
 * INSERT INTO img_table(img_name,img_data) VALUES('img01',null); 
 * SAVEPOINT a;
 * INSERT INTO img_table(img_name,img_data) VALUES('img02',null); 
 * ROLLBACK to a;
 * #ִ��DDL������ʽ�ύ���� 
 * ALTER TABLE img_table MODIFY COLUMN img_name VARCHAR(10);
 * #�ύ���� 
 * COMMIT;
 * ע�⣺�������δ�����SQLException�쳣�ǣ�������Զ��ع�����������Ѿ������쳣����Ҫ���쳣������ʽ�ع�����
 * @author zhaohe
 *
 */
public class TransactionRollbackTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		try(Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test2","root","root");){
			//�ر��Զ��ύ����������
			con.setAutoCommit(false);
			Savepoint point=null;
			try(Statement st=con.createStatement()){
				st.execute("INSERT INTO img_table(img_id,img_name,img_data) VALUES(3,'img01',null); ");
				point=con.setSavepoint();
				st.execute("INSERT INTO img_table(img_id,img_name,img_data) VALUES(4,'img01',null); ");
				st.execute("INSERT INTO img_table(img_id,img_name,img_data) VALUES(4,'img01',null); ");
			} catch(SQLException e){
				con.rollback(point);
			}
			con.commit();
		}
	}
}
