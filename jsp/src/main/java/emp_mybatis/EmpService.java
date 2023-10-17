package emp_mybatis;
 
import java.util.HashMap;
import java.util.List;

public class EmpService {
	private static EmpService service;
	
	private EmpService() {}
	public static EmpService getInstance(){
		if( service == null) service = new EmpService();
		return service;
	}
	
	// Model == Repository
	private EmpRepo repo =	new EmpRepo();

	public List<EmpVO> selectEmp(){
		return repo.selectEmp();
	}
	
	public List<HashMap> selectEmpDept(){
		return repo.selectEmpDept();
	}
}
