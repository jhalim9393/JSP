package board_mybatis.service;

import java.util.List;

import board_mybatis.model.BoardException;
import board_mybatis.model.BoardVO;
import board_mybatis.model.BoardRepository;

public class ViewArticleService {
	private static ViewArticleService instance;
	public static ViewArticleService getInstance()  throws BoardException{
		if( instance == null )
		{
			instance = new ViewArticleService();
		}
		return instance;
	}
	
	BoardRepository repo = new BoardRepository();

	public BoardVO getArticleById(String article_id){
		
		int aId = Integer.parseInt(article_id);
		
		return repo.getArticleById(aId);
	}	
}
