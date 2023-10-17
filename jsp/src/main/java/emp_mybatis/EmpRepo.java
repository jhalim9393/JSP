package emp_mybatis; 

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class EmpRepo {
	private SqlSessionFactory getSqlSessionFactory() {
		
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		SqlSessionFactory sessFactory =  new SqlSessionFactoryBuilder().build(inputStream);
		return sessFactory;
	}
	
	//-------------------------------
	public List<EmpVO> selectEmp(){
		SqlSession sess = getSqlSessionFactory().openSession();
		try {
			return sess.selectList("EmpMapper.selectEmp");
		}finally {
			sess.close();
		}
		
	}
	
	public List<HashMap> selectEmpDept(){
		SqlSession sess = getSqlSessionFactory().openSession();
		try {
			
			List<HashMap> result = sess.selectList("EmpMapper.selectEmpDept");
			/*
			 * System.out.println("EmpRepo -> selectEmpDept() 함수 ");
			 * 
			 * for(HashMap map : result) { System.out.println(map); }
			 */
			return result;
		}finally {
			sess.close();
		}
		
	}
	
}
