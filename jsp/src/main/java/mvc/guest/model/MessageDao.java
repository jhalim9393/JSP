package mvc.guest.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessageDao {

	// Single Pattern 
	private static MessageDao instance;
	
	// DB 연결시  관한 변수 
	// [ 오라클 ]
	//private static final String 		dbDriver	=	"oracle.jdbc.driver.OracleDriver";
	//private static final String		dbUrl		=	"jdbc:oracle:thin:@127.0.0.1:1521:orcl";
	//private static final String		dbUser		=	"scott";
	//private static final String		dbPass		=	"tiger";
	
	// [ MySQL / MariaDB ]
	private static final String 	dbDriver	=	"com.mysql.cj.jdbc.Driver";
	private static final String		dbUrl		=	"jdbc:mysql://localhost:3306/join";
	private static final String		dbUser		=	"harim";
	private static final String		dbPass		=	"1234";
	
	
	//--------------------------------------------
	//#####	 객체 생성하는 메소드 
	public static MessageDao	getInstance() throws MessageException
	{
		if( instance == null )
		{
			instance = new MessageDao();
		}
		return instance;
	}
	
	private MessageDao() throws MessageException
	{
	
		try{
			
			/********************************************
				1. 드라이버를 로딩					
			*/
			Class.forName(dbDriver);
		}catch( Exception ex ){
			throw new MessageException("방명록 ) DB 연결시 오류  : " + ex.toString() );	
		}
		
	}
	
	
	/*
	 * 메세지를 입력하는 함수
	 */
	public void insert(Message rec) throws MessageException
	{
		Connection	 		con = null;
		PreparedStatement ps = null;
		try{

			// 1. 연결객체(Connection) 얻어오기
			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );

			// 2. sql 문장 만들기
			String sql = "INSERT INTO "
					+ " GuestTB(GUEST_NAME, PASSWORD, MESSAGE)  "
					+ " VALUES(?,?,?)";
			// 3. 전송객체 얻어오기
			ps		= con.prepareStatement( sql );
			
			ps.setString	( 1, rec.getGuestName()	);
			ps.setString	( 2, rec.getPassword()	);
			ps.setString	( 3, rec.getMessage()	);
			
			// 4. 전송하기
			ps.executeUpdate();
		
				
		}catch( Exception ex ){
			throw new MessageException("방명록 ) DB에 입력시 오류  : " + ex.toString() );	
		} finally{
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}
	
	}
	
	/*
	 * 메세지 목록 전체를 얻어올 때
	 */
	public List<Message> selectList() throws MessageException
	{
		Connection	 		con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Message> mList = new ArrayList<Message>();
		boolean isEmpty = true;
		
		try{
			
			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
			String sql		= "SELECT * FROM guestTB order by message_id desc";  
			ps		= con.prepareStatement( sql );
			rs = ps.executeQuery();
			while( rs.next()) {
				isEmpty = false;
				// 각각의 컬럼값을 얻어와서 Message에 저장
				Message m = new Message();
				m.setMessageId( rs.getInt("MESSAGE_ID"));
				m.setGuestName( rs.getString("GUEST_NAME"));
				m.setMessage(   rs.getString("MESSAGE"));
				
				
				// ArrayList에 Message 객체를 저장
				mList.add(m);
			}
			
			if( isEmpty ) return Collections.emptyList();
			
			return mList;
		}catch( Exception ex ){
			throw new MessageException("방명록 ) DB에 목록 검색시 오류  : " + ex.toString() );	
		} finally{
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}		
	}
	

	/* -------------------------------------------------------
	 * 현재 페이지에 보여울 메세지 목록  얻어올 때
	 */
	public List<Message> selectList(int offset, int limit) throws MessageException
	{
		Connection	 		con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Message> mList = new ArrayList<Message>();
		boolean isEmpty = true;
		
		try{
			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
			String sql		= ""
					+ " SELECT g.*"
					+ " FROM (  SELECT * FROM guesttb "
					+ "		ORDER BY message_id DESC ) g "
					+ " LIMIT ?, ?";  
			ps		= con.prepareStatement( sql );
			ps.setInt(1, offset);
			ps.setInt(2, limit);
			rs = ps.executeQuery();
			while( rs.next()) {
				isEmpty = false;
				// 각각의 컬럼값을 얻어와서 Message에 저장
				Message m = new Message();
				m.setMessageId( rs.getInt("MESSAGE_ID"));
				m.setGuestName( rs.getString("GUEST_NAME"));
				m.setMessage(   rs.getString("MESSAGE"));
				
				
				// ArrayList에 Message 객체를 저장
				mList.add(m);
			}

			
			if( isEmpty ) return Collections.emptyList();
			
			return mList;
		}catch( Exception ex ){
			throw new MessageException("방명록 ) DB에 목록 검색시 오류  : " + ex.toString() );	
		} finally{
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}		
	}
	
	
	
	/* -------------------------------------------------------
	 * 메세지 전체 레코드 수를 검색
	 */
	
	public int getTotalCount() throws MessageException{
		Connection	 		con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;

		try{
			// 2.
			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
			// 3.
			String sql = "SELECT  count(*) cnt  FROM guesttb";
			// 4.
			ps		= con.prepareStatement( sql );
			// 5. 
			rs = ps.executeQuery();
			// 6.
			if( rs.next()) {
				count = rs.getInt("CNT");
			}
			
			return  count;
			
		}catch( Exception ex ){
			throw new MessageException("방명록 ) DB에 목록 검색시 오류  : " + ex.toString() );	
		} finally{
			// 7.
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}			
	}
	
	/*
	 * 메세지 번호와 비밀번호에 의해 메세지 삭제
	 */
	public int delete( int messageId, String password ) throws MessageException
	{
		int result = 0;
		Connection	 	 con = null;
		PreparedStatement ps = null;
		try{
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			
			String deleteSql = "DELETE FROM GuestTB WHERE MESSAGE_ID = ? AND PASSWORD = ?";
			
			ps = con.prepareStatement(deleteSql);
			
			ps.setInt(1, messageId);
			ps.setString(2, password);
			
			result = ps.executeUpdate();
			
			
			return result;
		}catch( Exception ex ){
			throw new MessageException("방명록 ) DB에 삭제시 오류  : " + ex.toString() );	
		} finally{
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}		
	}
}
