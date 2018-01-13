package com.pengji.modules.upms.service;
import com.pengji.base.BaseService;
import com.pengji.modules.upms.model.SysConfig;
/**
 * 系统配置表
 * 
 * @author flyfox 2018-01-12
 */
public class ConfigService  extends BaseService<SysConfig>{
	
	public static final ConfigService me = new ConfigService(new SysConfig());
	
	public ConfigService(SysConfig dao) {
		super(dao);
	}
	
}
