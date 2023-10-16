package board_ex.model;



import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import guest.model.Message;
import guest.model.MessageException;

public class BoardDao
{
	
	// Single Pattern 
	private static BoardDao instance;
	
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
	
	
	private Connection	 		con;	
	
	//--------------------------------------------
	//#####	 객체 생성하는 메소드 
	public static BoardDao	getInstance() throws BoardException
	{
		if( instance == null )
		{
			instance = new BoardDao();
		}
		return instance;
	}
	
	private BoardDao() throws BoardException
	{
	
		try{
			
			/********************************************
			1. 오라클 드라이버를 로딩
				( DBCP 연결하면 삭제할 부분 )
		*/
			Class.forName( dbDriver );	
		}catch( Exception ex ){
			throw new BoardException("DB 연결시 오류  : " + ex.toString() );	
		}
		
	}
	
	/************************************************
	 * 함수명 : insert
	 * 역할 :	게시판에 글을 입력시 DB에 저장하는 메소드 
	 * 인자 :	BoardVO
	 * 리턴값 : 입력한 행수를 받아서 리턴
	*/
	public int insert( BoardVO rec ) throws BoardException
	{

		ResultSet rs = null;
		Statement stmt	= null;
		PreparedStatement ps = null;
		try{

			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
			
			//* sql 문장 만들기
			String putQuery		= " INSERT INTO board_ex(title, writer, content, regdate, cnt, pass) "
											+ " VALUES(?,?,?,now(),0,?)";  

			ps		= con.prepareStatement( putQuery );
			//* sql 문장의 ? 지정하기
			ps.setString(1, rec.getTitle());
			ps.setString(2, rec.getWriter());
			ps.setString(3, rec.getContent());
			ps.setString(4, rec.getPass());
			
	
			int insertedCount = ps.executeUpdate();			

			return insertedCount;
		
		}catch( Exception ex ){
			throw new BoardException("게시판 ) DB에 입력시 오류  : " + ex.toString() );	
		} finally{
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( stmt != null ) { try{ stmt.close(); } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}
		
	}

	//--------------------------------------------
	//#####	 자동 증가 수인 Id의 최대 값 가져오기
	/*
	 * 	함수명 : getMaxId
	 * 역    할 : 입력한 후에 자동 증가 수 값 얻어오기
	 */
	public int getMaxId() {
		int result = 0;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		BoardVO rec = new BoardVO();
		
		try {
			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
			String sql = "SELECT max(seq) max FROM board_ex";
			
			// * 전송객체 얻어오기
			ps = con.prepareStatement( sql );
		
			// * 전송하기
			rs = ps.executeQuery();

			if(rs.next()) {
				result = rs.getInt("max");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		} // end of finally
		return result;

	}
	
	
	
	
	/************************************************
	 * 함수명 : selectList
	 * 역할 :	전체 레코드를 검색하는 함수
	 * 인자 :	없음
	 * 리턴값 : 테이블의 한 레코드를 BoardVO 지정하고 그것을 ArrayList에 추가한 값
	*/

	public List<BoardVO> selectList() throws BoardException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<BoardVO> mList = new ArrayList<BoardVO>();
		boolean isEmpty = true;
		
		try{

			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
			
			// * sql 문장만들기
			String select_entire = "SELECT * FROM board_ex";
			
			// * 전송객체 얻어오기
			ps = con.prepareStatement( select_entire );
			// * 전송하기
			rs = ps.executeQuery();
			// * 결과 받아 List<BoardVO> 변수 mList에 지정하기
			while(rs.next()) {
				isEmpty = false;
				BoardVO vo = new BoardVO();
				
				vo.setSeq(rs.getInt("seq"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setContent(rs.getString("content"));
				vo.setRegdate(rs.getString("regdate"));
				vo.setCnt(rs.getInt("cnt"));
				
				mList.add(vo);
			} // end of while

			
			if( isEmpty ) return Collections.emptyList();
			
			return mList;
		} // end of try
		catch( Exception ex ){
			throw new BoardException("게시판 ) DB에 목록 검색시 오류  : " + ex.toString() );	
		} finally{
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		} // end of finally
	} // end of  selectList()
	
	
	/* -------------------------------------------------------
	 * 현재 페이지에 보여울 메세지 목록  얻어올 때
	 * 오라클 : 몇번째부터 몇번째까지 선택? firstRow ~ endRow
	 * mysql : 몇번째부터 시작해서 몇개를 선택?
	 */
	public List<BoardVO> selectList(int offset, int limit) throws BoardException
	{
		Connection	 		con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<BoardVO> mList = new ArrayList<BoardVO>();
		boolean isEmpty = true;
		
		try{
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			
			String selectSql = " SELECT b.* "
					+ " FROM ( SELECT * FROM board_ex "
					+ " ORDER BY seq DESC ) b "
					+ " LIMIT ?, ? ";
			
			ps = con.prepareStatement(selectSql);
			ps.setInt(1, offset);
			ps.setInt(2, limit);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				isEmpty = false;
				// 각각의 컬럼값을 얻어와서 VO에 저장
				BoardVO vo = new BoardVO();
				
				vo.setSeq(rs.getInt("seq"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setContent(rs.getString("content"));
				vo.setRegdate(rs.getString("regdate"));
				vo.setCnt(rs.getInt("cnt"));
				
				mList.add(vo);
			} // end of while

			if( isEmpty ) return Collections.emptyList();
			
			return mList;
			
		}catch( Exception ex ){
			throw new BoardException("방명록 ) DB에 목록 검색시 오류  : " + ex.toString() );	
		} finally{
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}		
	}
	
	
	
	/* -------------------------------------------------------
	 * 메세지 전체 레코드 수를 검색
	 */
	
	public int getTotalCount() throws BoardException{
		Connection	 		con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;

		try{
			// 2.
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			
			// 3.
			String sql = "SELECT count(*) cnt FROM board_ex";
			
			// 4.
			ps = con.prepareStatement(sql);
			
			// 5.
			rs = ps.executeQuery();
			
			// 6.
			if(rs.next()) {
				count = rs.getInt("cnt");
			}
			
			return  count;
			
		}catch( Exception ex ){
			throw new BoardException("방명록 ) DB에 목록 검색시 오류  : " + ex.toString() );	
		} finally{
			// 7.
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}			
	}
	
	
	
	//--------------------------------------------
	//#####	 게시글번호에 의한 레코드 검색하는 함수
	public BoardVO selectById(int seq) throws BoardException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		BoardVO rec = new BoardVO();
		
		try{

			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
			
			// * sql 문장만들기
			String select_entire = "SELECT * FROM board_ex WHERE seq = ?";
			
			// * 전송객체 얻어오기
			ps = con.prepareStatement( select_entire );
			
			ps.setInt(1, seq);
			// * 전송하기
			rs = ps.executeQuery();
			
			// * 결과 받아 BoardVO변수 rec에 지정하기
			if(rs.next()) {
				rec.setSeq(rs.getInt("seq"));
			    rec.setTitle(rs.getString("title"));
			    rec.setWriter(rs.getString("writer"));
			    rec.setContent(rs.getString("content"));
			    rec.setRegdate(rs.getString("regdate"));
			    rec.setCnt(rs.getInt("cnt"));
			    rec.setPass(rs.getString("pass"));
			}
			
			return rec;
			
		}catch( Exception ex ){
			throw new BoardException("게시판 ) DB에 글번호에 의한 레코드 검색시 오류  : " + ex.toString() );	
		} finally{
			if( rs   != null ) { try{ rs.close();  } catch(SQLException ex){} }
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}		
	}

	//--------------------------------------------
	//#####	 게시글 보여줄 때 조회수 1 증가
	public void increaseReadCount( int seq ) throws BoardException
	{

		PreparedStatement ps = null;
		try{

			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
			// SQL 문장 만들기
	        String increaseQuery = "UPDATE board_ex SET cnt = cnt + 1 WHERE seq = ?";

	        // 전송객체 얻어오기
	        ps = con.prepareStatement(increaseQuery);

	        ps.setInt(1, seq);
	        
	        // 전송하기
	        ps.executeUpdate();
			
		}catch( Exception ex ){
			throw new BoardException("게시판 ) 게시글 볼 때 조회수 증가시 오류  : " + ex.toString() );	
		} finally{
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}
		
	}
	//--------------------------------------------
	//#####	 게시글 수정할 때
	public int update( BoardVO rec ) throws BoardException
	{

		PreparedStatement ps = null;
		try{

			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
			// SQL 문장 만들기
	        String modifyQuery = "UPDATE board_ex SET title = ?, content = ?  WHERE pass = ? AND seq = ?";

	        // 전송객체 얻어오기
	        ps = con.prepareStatement(modifyQuery);

	        ps.setString(1, rec.getTitle());
	        ps.setString(2, rec.getContent());
	        ps.setString(3, rec.getPass());
	        ps.setInt(4, rec.getSeq());

	        
			return ps.executeUpdate();
		
		}catch( Exception ex ){
			throw new BoardException("게시판 ) 게시글 수정시 오류  : " + ex.toString() );	
		} finally{
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}
		
	}
	
	
	//--------------------------------------------
	//#####	 게시글 삭제할 때
	public int delete( int seq, String pass ) throws BoardException
	{

		PreparedStatement ps = null;
		try{

			con	= DriverManager.getConnection( dbUrl, dbUser, dbPass );
		
			// SQL 문장 만들기
	        String deleteQuery = "DELETE FROM board_ex WHERE seq = ? AND pass = ?";

	        // 전송객체 얻어오기
	        ps = con.prepareStatement(deleteQuery);

	        ps.setInt(1, seq);
	        ps.setString(2, pass);

			return ps.executeUpdate();
			
		}catch( Exception ex ){
			throw new BoardException("게시판 ) 게시글 수정시 오류  : " + ex.toString() );	
		} finally{
			if( ps   != null ) { try{ ps.close();  } catch(SQLException ex){} }
			if( con  != null ) { try{ con.close(); } catch(SQLException ex){} }
		}
		
	}
	

	
	
	
	
}