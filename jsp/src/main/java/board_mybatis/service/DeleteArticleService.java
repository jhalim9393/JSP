package board_mybatis.service;

import board_mybatis.model.*;

public class DeleteArticleService {
	
	private static DeleteArticleService instance;
	public static DeleteArticleService getInstance()  throws BoardException{
		if( instance == null )
		{
			instance = new DeleteArticleService();
		}
		return instance;
	}
	
	BoardRepository repo = new BoardRepository();
	
	public int delete( BoardVO rec) throws BoardException{
		
		return repo.deleteBoard(rec);
		
	}
}
