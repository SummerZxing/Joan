package com.pengji.modules.upms.service;
import com.pengji.base.BaseService;
import com.pengji.modules.upms.model.SysLog;
/**
 * 日志
 * 
 * @author flyfox 2018-01-12
 */
public class LogService  extends BaseService<SysLog>{
	
	public static final LogService me = new LogService(new SysLog());
	
	public LogService(SysLog dao) {
		super(dao);
	}
	
}
