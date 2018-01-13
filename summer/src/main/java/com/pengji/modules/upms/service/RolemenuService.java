package com.pengji.modules.upms.service;
import com.pengji.base.BaseService;
import com.pengji.modules.upms.model.SysRoleMenu;
/**
 * 角色和菜单关联
 * 
 * @author flyfox 2018-01-12
 */
public class RolemenuService  extends BaseService<SysRoleMenu>{
	
	public static final RolemenuService me = new RolemenuService(new SysRoleMenu());
	
	public RolemenuService(SysRoleMenu dao) {
		super(dao);
	}
	
}
