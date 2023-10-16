package mybatis.guest.model;

import lombok.Data;

@Data 
public class Comment {
		
	private long commentNo;
	private String userId;
	private String commentContent;
	private String regDate;
	
}