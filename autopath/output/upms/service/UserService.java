package com.pengji.modules.upms.service;
import com.pengji.base.BaseService;
import com.pengji.modules.upms.model.SysUser;
/**
 * 用户
 * 
 * @author flyfox 2018-01-12
 */
public class UserService  extends BaseService<SysUser>{
	
	public static final UserService me = new UserService(new SysUser());
	
	public UserService(SysUser dao) {
		super(dao);
	}
	
}
