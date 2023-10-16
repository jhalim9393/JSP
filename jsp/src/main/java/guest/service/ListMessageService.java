package guest.service;

import guest.model.Message;
import guest.model.MessageDao;
import guest.model.MessageException;

import java.util.List;

public class ListMessageService {

	//-------------------------------------------------------------------
	private int totalRecCount;		// 전체 레코드 수	
	private int pageTotalCount;		// 전체 페이지 수
	private int countPerPage = 3;	// 한페이지당 레코드 수
	
	private static ListMessageService instance;
	
	public static ListMessageService	getInstance() throws MessageException
	{
		if( instance == null )
		{
			instance = new ListMessageService();
		}
		return instance;
	}
	
	// 전체 메세지 레코드 수 얻어와서 전체 페이지 수 반환
	public int getTotalPage() throws MessageException {
		totalRecCount = MessageDao.getInstance().getTotalCount();
		
		/* 전체 레코드 수     전체 페이지 수
		 * 			7							3   7 % 3 1 
		 * 			8							3  8 % 3 2 
		 * 			9							3  9 % 3 0
		 * 			10							4  10 / 3 = 3.* 10 % 3 = 1 
		 * 
		 * 			1	
		*/
		
		
		pageTotalCount = totalRecCount / countPerPage;
		if(totalRecCount % countPerPage > 0) {
			pageTotalCount++;
		}
		
		return pageTotalCount;
	}
	
	
	
	private ListMessageService()
	{
		
		
	}
	
	public List <Message> getMessageList(String pNum) throws MessageException
	{
		/*
		 *  페이지 번호			첫번째레코드 번호
		 * 			1								0			3 * 1-1
		 * 			2								3			3 * 2-1
		 * 			3								6			3 * 3-1
		 *	참고 countPerPage = 3 
		 * */
		
		int pageNo = 1;
		if(pNum != null) {
			pageNo = Integer.parseInt(pNum);
		}
		int firstRow = (pageNo-1) * countPerPage;

		
		
		
		// 전체 레코드를 검색해 온다면
		List <Message> mList = MessageDao.getInstance().selectList(firstRow, countPerPage);			
		return mList;
	}
	
	
}
