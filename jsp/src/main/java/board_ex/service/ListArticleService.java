package board_ex.service;

import java.util.List;

import board_ex.model.*;
import guest.model.MessageDao;
import guest.model.MessageException;

public class ListArticleService {
	
	//-------------------------------------------------------------------
	private int totalRecCount;		// 전체 레코드 수	
	private int pageTotalCount;		// 전체 페이지 수
	private int countPerPage = 5;	// 한페이지당 레코드 수
	
	private static ListArticleService instance;
	public static ListArticleService getInstance()  throws BoardException{
		if( instance == null )
		{
			instance = new ListArticleService();
		}
		return instance;
	}
	
	// 전체 메세지 레코드 수 얻어와서 전체 페이지 수 반환
	public int getTotalPage() throws BoardException {
		totalRecCount = BoardDao.getInstance().getTotalCount();
		
		pageTotalCount = totalRecCount / countPerPage;
		if(totalRecCount % countPerPage > 0) {
			pageTotalCount++;
		}
		
		return pageTotalCount;
	}
	
	
	public List <BoardVO> getArticleList(String pNum) throws BoardException
	{
		
		int pageNo = 1;
		if(pNum != null) {
			pageNo = Integer.parseInt(pNum);
		}
		int firstRow = (pageNo-1) * countPerPage;
		
		// 전체 레코드를 검색해 온다면 
		List <BoardVO> mList = BoardDao.getInstance().selectList(firstRow, countPerPage);	 		
		return mList;
	}
		
}
