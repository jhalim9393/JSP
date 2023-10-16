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
	
	
	public List<Comment> selectComment() {

		return repo.selectComment();
	}
	
	public Comment selectCommentByPK(long commentNo) {
		
		return repo.selectCommentByPK(commentNo);
	}
	
	
}