package com.pengji.modules.upms.service;
import com.pengji.base.BaseService;
import com.pengji.modules.upms.model.SysRole;
/**
 * 角色
 * 
 * @author flyfox 2018-01-12
 */
public class RoleService  extends BaseService<SysRole>{
	
	public static final RoleService me = new RoleService(new SysRole());
	
	public RoleService(SysRole dao) {
		super(dao);
	}
	
}
