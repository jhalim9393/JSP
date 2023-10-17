package board_mybatis.service;

import java.util.List;

import board_mybatis.model.BoardException;
import board_mybatis.model.BoardVO;
import board_mybatis.model.BoardRepository;

public class ListArticleService {
	private static ListArticleService instance;
	public static ListArticleService getInstance()  throws BoardException{
		if( instance == null )
		{
			instance = new ListArticleService();
		}
		return instance;
	}
	
	BoardRepository repo = new BoardRepository();

	public List<BoardVO> getArticleList(){
		return repo.getBoardList();
	}	
}
