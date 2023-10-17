package board_mybatis.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class BoardRepository {
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
	
	// 게시글 입력
	public int insertBoard(BoardVO rec) {
		SqlSession sess = getSqlSessionFactory().openSession(); // Connection 하나 가져오기
		
		try {
			int result = sess.insert("BoardMapper.insertBoard", rec);
			sess.commit(); // 
			return result;
			
		} finally {
			sess.close();
		}
	
	}

	// 게시글 전체 목록 보기
	public List<BoardVO> getBoardList(){
		// SqlSession == Connection (연결객체)
		SqlSession sess = getSqlSessionFactory().openSession(); // Connection 하나 가져오기
		
		try {
			List<BoardVO> list = sess.selectList("BoardMapper.selectBoard"); // 변수 한번만 쓰여서 지정 안하고 return으로 바로 넘겨도 됨
			System.out.println("getBoardList(): " + list.size());
		return list;
//		return sess.selectList("");
		
		} finally {
			sess.close();
		}
	}

	// 게시글 번호에 따른 상세내용 보기
	public BoardVO getArticleById(int article_id){
		// SqlSession == Connection (연결객체)
		SqlSession sess = getSqlSessionFactory().openSession(); 
		
		try {
			
			return sess.selectOne("BoardMapper.selectBoardByPk", article_id);
			
		}finally {
			sess.close();
		}
	
	}

	// 게시글 번호에 따른 삭제
	public int deleteBoard(BoardVO rec) {
		// SqlSession == Connection (연결객체)
		SqlSession sess = getSqlSessionFactory().openSession(); 
		
		try {
			int result = sess.delete("BoardMapper.deleteBoard", rec);
			sess.commit(); 
			return result;
			
		} finally {
			sess.close();
		}
	}
	
	// 비밀번호 받고 제목, 내용 수정
	public int updateBoard(BoardVO rec) {
		SqlSession sess = getSqlSessionFactory().openSession(); // Connection 하나 가져오기
		
		try {
			int result = sess.update("BoardMapper.updateBoard", rec);
			sess.commit(); 
			return result;
			
		} finally {
			sess.close();
		}
	
	}
	
}
