package board_mybatis.service;

import board_mybatis.model.*;

public class ModifyArticleService {
	
	private static ModifyArticleService instance;
	public static ModifyArticleService getInstance()  throws BoardException{
		if( instance == null )
		{
			instance = new ModifyArticleService();
		}
		return instance;
	}
	
	BoardRepository repo = new BoardRepository();
	
	public int update( BoardVO rec ) throws BoardException{
	
		return repo.updateBoard(rec);
		
	}
}
