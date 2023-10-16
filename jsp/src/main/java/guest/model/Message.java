package guest.model;

import lombok.Data;

@Data
public class Message {
	private 		int 		 messageId;		// 메세지번호
	private 		String 	 guestName;		// 작성자명
	private		String 	 password;		// 비밀번호
	private		String	 message;			// 메세지
	
}