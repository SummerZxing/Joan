package com.pengji.modules.upms.service;
import com.pengji.base.BaseService;
import com.pengji.modules.upms.model.SysDict;
/**
 * 数据字典主表
 * 
 * @author flyfox 2018-01-12
 */
public class DictService  extends BaseService<SysDict>{
	
	public static final DictService me = new DictService(new SysDict());
	
	public DictService(SysDict dao) {
		super(dao);
	}
	
}
