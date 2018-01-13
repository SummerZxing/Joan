package com.pengji.modules.upms.service;
import com.pengji.base.BaseService;
import com.pengji.modules.upms.model.SysUserRole;
/**
 * 用户和角色关联
 * 
 * @author flyfox 2018-01-12
 */
public class UserroleService  extends BaseService<SysUserRole>{
	
	public static final UserroleService me = new UserroleService(new SysUserRole());
	
	public UserroleService(SysUserRole dao) {
		super(dao);
	}
	
}
