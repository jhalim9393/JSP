package member.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDao {

	
	// DB 연결시  관한 변수 
	private static final String 		dbDriver	=	"com.mysql.cj.jdbc.Driver";
	private static final String		dbUrl		=	"jdbc:mysql://localhost:3306/join";
	private static final String		dbUser		=	"harim";
	private static final String		dbPass		=	"1234";
	
	
	private Connection	 con;
	
	private static MemberDao memberDao;
	
	public static MemberDao getInstance() throws MemberException
	{
		if( memberDao == null )
		{
			memberDao =  new MemberDao(); // 싱글톤 패턴
		}
		return memberDao;
	}
	

	private MemberDao() throws MemberException
	{
			
		try{
			
			/********************************************
				1. 드라이버를 로딩
				
			*/
			Class.forName( dbDriver );	
		
		}catch( Exception ex ){
			throw new MemberException("DB 연결시 오류  : " + ex.toString() );	
			// 일부러 예외 발생시킴
		}
	}
	
	/*******************************************
	 * * 회원관리테이블 MEMBERTEST 에  회원정보를 입력하는 함수
	 * @param rec
	 * @throws MemberException
	 */
	public void insertMember( Member rec ) throws MemberException
	{
		PreparedStatement insertPrep = null;
		try {
			
			 // 0. 연결 객체 얻어오기
			 // 1. sql 문장 만들기 ( insert문 )
			 // 2. sql 전송 객체 만들기
			 // 3. sql 전송	
			 // 4. 닫기
			
			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );					
			 
			String insertSql = "INSERT INTO memberTest VALUES(?,?,?,?,?)";
			insertPrep = con.prepareStatement(insertSql);
			insertPrep.setString(1, rec.getId());
			insertPrep.setString(2, rec.getPassword());
			insertPrep.setString(3, rec.getName());
			insertPrep.setString(4, rec.getTel());
			insertPrep.setString(5, rec.getAddr());
			insertPrep.executeUpdate();
			insertPrep.close();
		} catch ( Exception ex ){
			throw new MemberException("멤버 입력시 오류  : " + ex.toString() );			
		} finally{
			if( insertPrep != null ) { try{ insertPrep.close(); } catch(SQLException ex){} }
			if( con != null ) { try{ con.close(); } catch(SQLException ex){} }
		}
	}
	
	/**********************************************************
	 * * 회원관리테이블 MEMBERTEST에서 기존의 id값과 중복되는지 확인하는 함수
	 */
	public boolean isDuplicatedId( String id ) throws MemberException
	{
		boolean flag = false;

		PreparedStatement checkIdPrep = null;
		ResultSet rs = null;

		try{
			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
			String checkIdSql = "SELECT * FROM membertest WHERE ID=?";
			checkIdPrep = con.prepareStatement(checkIdSql);
			checkIdPrep.setString(1, id );
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
