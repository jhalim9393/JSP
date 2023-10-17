package board_mybatis.service;

import java.text.DecimalFormat;
import java.util.List;

import board_mybatis.model.BoardException;
import board_mybatis.model.BoardVO;
import board_mybatis.model.BoardRepository;

public class WriteArticleService {
	
	private static WriteArticleService instance;
	public static WriteArticleService getInstance()  throws BoardException{
		if( instance == null )
		{
			instance = new WriteArticleService();
		}
		return instance;
	}
	
	BoardRepository repo = new BoardRepository();
	
	public int write( BoardVO rec ) throws BoardException{
		
		// DB에 insert
		return repo.insertBoard(rec);
		
	}
	
	

}
