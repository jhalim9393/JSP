package mybatis.guest.session;

import java.io.*;
import java.util.*;

import mybatis.guest.model.Comment;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

public class CommentRepository 
{
	//	private final String namespace = "CommentMapper";

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

	/*	+ JDBC 연동
			- Connection ( 연결 객체 )
		+ Mybatis 연동
			- SqlSession ( 연결 객체 )*/
	public void insertComment(Comment vo) {
		SqlSession sess = getSqlSessionFactory().openSession();
		
		int result = sess.insert("CommentMapper.insertComment", vo);
		
		if(result == 1) {
			sess.commit();
		}
		else {
			sess.rollback();
		}
		sess.close(); // 닫는 것이 아니라 연결객체 반환
	} // end of insertComment()
	
	
	public List<Comment> selectComment() {
		// Connection 얻어오기
		SqlSession sess = getSqlSessionFactory().openSession();
		
		try {
			// 레코드 하나 가져올거면 selectOne , 여러개면 selectList
			return sess.selectList("CommentMapper.selectComment");
			
		} finally {
			sess.close();
		}
		
	} // end of selectComment()
	
	
	public Comment selectCommentByPK(long commentNo) {
		// Connection 얻어오기
		SqlSession sess = getSqlSessionFactory().openSession();
		
		try {
			Map map = new HashMap();
			map.put("commentNo", commentNo);
			
			
			// 레코드 하나 가져올거면 selectOne , 여러개면 selectList
			// 해시 맵을 넘겨주기
			return sess.selectOne("CommentMapper.selectComment" , map);
			
		} finally {
			sess.close();
		}
	}
	

}