package com.pengji.modules.upms.service;
import com.pengji.base.BaseService;
import com.pengji.modules.upms.model.SysMenu;
/**
 * 菜单
 * 
 * @author flyfox 2018-01-12
 */
public class MenuService  extends BaseService<SysMenu>{
	
	public static final MenuService me = new MenuService(new SysMenu());
	
	public MenuService(SysMenu dao) {
		super(dao);
	}
	
}
