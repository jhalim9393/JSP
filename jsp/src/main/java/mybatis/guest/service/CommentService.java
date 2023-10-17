package mybatis.guest.service;

import java.util.List;

import mybatis.guest.model.Comment;
import mybatis.guest.session.CommentRepository;

public class CommentService {
	
	private static CommentService service;
	
	private CommentService() {}
	public static CommentService getInstance(){
		if( service == null) service = new CommentService();
		return service;
	}
	
	private CommentRepository repo = new CommentRepository();
	
	
	// Model ( DAO -> Repository )
	public void insertComment(Comment vo) {
		repo.insertComment(vo);
	}
	
	
	public List<Comment> selectComment(String searchKey, String searchWord) {
		System.out.println(searchKey + "/" + searchWord); //  ( 보내는 데이터 )
		
		List<Comment> list = repo.selectComment(searchKey, searchWord); // Repository
		System.out.println("CommentService.selectComment : " + list.size());  // Service ( 받는 데이터 )
		return list;
	}
	
	public Comment selectCommentByPK(long commentNo) {
		
		return repo.selectCommentByPK(commentNo);
	}
	
	
}