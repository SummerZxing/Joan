package com.pengji.modules.user;
import com.pengji.base.BaseService;

/**
 * 用户表
 * 
 * @author flyfox 2018-01-11
 */
public class UserService  extends BaseService<SysUser>{
	
	public static final UserService me = new UserService(new SysUser());
	
	public UserService(SysUser dao) {
		super(dao);
	}
	
}
