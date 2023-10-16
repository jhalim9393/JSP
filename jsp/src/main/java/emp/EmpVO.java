package emp;

import lombok.Data;

//@Setter
//@Getter
//@NoArgsConstructor		// 인자 없는 생성자
//@ToString

@Data // 위의 네개 다 포함
public class EmpVO {
		private int empno;
		private String ename;
		private String job;
		private int sal;
		private int deptno;
}
