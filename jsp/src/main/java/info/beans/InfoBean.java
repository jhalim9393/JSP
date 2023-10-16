package info.beans;

public class InfoBean {
	
	// 폼의 입력필드의 name 값과 동일
	private String name;
	private String id;
	
	
	public String getGender() {
		String gender = "";
		char sex = id.charAt(7);
		if(sex=='1' | sex=='3') {
			gender = "남성";
		} else {
			gender = "여성";
		}

		return gender;
	}
	
	// Getter, Setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	
}
