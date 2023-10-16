package emp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmpDAO {
		
	String driver 	= "com.mysql.cj.jdbc.Driver";
	String url		= "jdbc:mysql://localhost:3306/basic";
	String user		= "scott";
	String pass		= "tiger";
	
	
	private EmpDAO() throws Exception {
		// 1. 드라이버 로딩
		Class.forName(driver);
	}
	
	static EmpDAO dao = null;
	public static EmpDAO getInstance() throws Exception{
		if( dao == null) {
			dao = new EmpDAO();
		}
		return dao;
	}
	
	
	/*
	 * 메소드명 : checkLogin
	 * 인자값 : 사용자가 입력한 이름, 사번 
	 * 리턴값 : boolean
	 * 역할 : 사용자 입력한 이름과 사번을 emp 테이블에 검색하여 해당 레코드가 있으면 true를 return / 없으면 false
	 * */
	public boolean checkLogin(String ename, String empno) throws Exception {
		boolean result = false;
		
		// 2. 연결객체 얻어오기
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
				
		try {
			con = DriverManager.getConnection(url, user, pass);
			
			// 3. sql 문장
			String sql = "SELECT empno, ename From emp WHERE ename = ? AND empno = ?";
			
			// 4. 전송객체
			ps = con.prepareStatement(sql);
			ps.setString(1, ename);
			ps.setInt(2, Integer.parseInt(empno));
			
			// 5. 전송 (select : executeQuery() 이용)
			rs = ps.executeQuery();
			
			// 6. 결과 받아와서 레코드 있는지 확인
			if(rs.next()) {
				result = true; // 일치하는 레코드 있을 때
			}
			
		} // end of try 
			
			finally {
				rs.close();
				ps.close();
				con.close();
			}
	
	return result;
}
	
	
	
	
	// 사원 정보를 넘겨 받아서 데이터베이스 입력
	public void insertEmp(EmpVO vo) throws Exception{
		
		// 2. 연결객체 얻어오기
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DriverManager.getConnection(url, user, pass);
			
			// 3. sql 문장
			String sql = "INSERT INTO emp(empno, ename, job, sal, deptno) "
							+ " VALUES(?, ?, ?, ?, ?)";
			
			// 4. 전송객체
			ps = con.prepareStatement(sql);
			
			// 5. 전송 (insert : executeUpdate() 이용)
			ps.setInt(1, vo.getEmpno());
			ps.setString(2, vo.getEname());
			ps.setString(3, vo.getJob());
			ps.setInt(4, vo.getSal());
			ps.setInt(5, vo.getDeptno());
			
			ps.executeUpdate();
			
		} // end of try 
		
		finally {
			ps.close();
			con.close();
		}

	}
	
	
	public List<EmpVO> selectAll() throws Exception {
		List<EmpVO> list = new ArrayList<EmpVO>();
	
		// 2. 연결객체 얻어오기
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DriverManager.getConnection(url, user, pass);
			
			// 3. sql 문장
			String sql = "SELECT empno, ename, job, sal, deptno From emp";
			
			// 4. 전송객체
			ps = con.prepareStatement(sql);
			
			// 5. 전송 (select : executeQuery() 이용)
			ResultSet rs = ps.executeQuery();
			// 6. 결과 받아 list 변수에 저장
			while(rs.next()) {
				EmpVO vo = new EmpVO();
				
				vo.setEmpno(rs.getInt("empno"));
				vo.setEname(rs.getString("ename"));
				vo.setJob(rs.getString("job"));
				vo.setSal(rs.getInt("sal"));
				vo.setDeptno(rs.getInt("deptno"));
				
				list.add(vo);
			} // end of while
			
		} // end of try 
		
		finally {
			ps.close();
			con.close();
		}
		
		return list;
	} // end of selectAll
	
} // end of class
