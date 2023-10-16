package customer;

import java.sql.*;

import member.beans.MemberException;


public class CusDAO {
	String driver 	= "com.mysql.cj.jdbc.Driver";
	String url		= "jdbc:mysql://localhost:3306/join";
	String user		= "harim";
	String pass		= "1234";
	
	private CusDAO() throws Exception {
		// 1. 드라이버 로딩
		Class.forName(driver);
	}
	
	static CusDAO dao = null;
	
	public static CusDAO getInstance() throws Exception {
		if( dao == null) {
			dao = new CusDAO();
		}
		return dao;
	}
	
	// 회원가입한 고객 정보 넘겨 받아 DB 입력
	public void insertCus(CusVO vo) throws Exception{
		
		// 2. 연결객체 얻어오기
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DriverManager.getConnection(url, user, pass);
			
			// 3. sql 문장
			String sql = "INSERT INTO customer(id, pass, name, phone_num, addr) "
							+ " VALUES(?, ?, ?, ?, ?)";
			
			// 4. 전송객체
			ps = con.prepareStatement(sql);
			
			// 5. 전송 (insert : executeUpdate() 이용)
			ps.setString(1, vo.getId());
			ps.setString(2, vo.getPass());
			ps.setString(3, vo.getName());
			ps.setString(4, vo.getPhone_num());
			ps.setString(5, vo.getAddr());
			
			ps.executeUpdate();
			
		} // end of try 
		
		finally {
			ps.close();
			con.close();
		}

	} // end of insertCus() 
	
	
	// 회원가입한 아이디, 비번을 join 테이블에 검색하여 해당 레코드 확인
	public boolean checkLogin(String cus_id, String cus_pass) throws Exception {
		boolean result = false;
		
		// 2. 연결객체 얻어오기
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
				
		try {
			con = DriverManager.getConnection(url, user, pass);
			
			// 3. sql 문장
			String sql = "SELECT id, pass From customer WHERE id = ? AND pass = ?";
			
			// 4. 전송객체
			ps = con.prepareStatement(sql);
			ps.setString(1, cus_id);
			ps.setString(2, cus_pass);
			
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
	
	// 아이디 중복확인
	public boolean isDuplicatedId( String userid ) throws MemberException
	{
		boolean flag = false;

		Connection con = null;
		PreparedStatement checkIdPrep = null;
		ResultSet rs = null;

		try{
			con	= DriverManager.getConnection( url, user, pass );
			String checkIdSql = "SELECT * FROM customer WHERE id=?";
			checkIdPrep = con.prepareStatement(checkIdSql);
			checkIdPrep.setString(1, userid );
			rs = checkIdPrep.executeQuery();
			if( rs.next()){ // 값이 있으면
				flag = true;
			}
		}catch( Exception ex ){
			throw new MemberException("멤버 입력시 오류  : " + ex.toString() );			
		}finally{
			if( rs != null ) { try{ rs.close(); } catch(SQLException ex){} }
			if( checkIdPrep != null ) { try{ checkIdPrep.close(); } catch(SQLException ex){} }
			if( con != null ) { try{ con.close(); } catch(SQLException ex){} }
		}
			
		return flag;
	}
	
}
