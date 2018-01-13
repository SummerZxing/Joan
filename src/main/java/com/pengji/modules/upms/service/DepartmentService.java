package com.pengji.modules.upms.service;
import com.pengji.base.BaseService;
import com.pengji.modules.upms.model.SysDepartment;
/**
 * 部门
 * 
 * @author flyfox 2018-01-12
 */
public class DepartmentService  extends BaseService<SysDepartment>{
	
	public static final DepartmentService me = new DepartmentService(new SysDepartment());
	
	public DepartmentService(SysDepartment dao) {
		super(dao);
	}
	
}
