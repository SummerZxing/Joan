package com.pengji.modules.upms.service;
import com.pengji.base.BaseService;
import com.pengji.modules.upms.model.SysFileUpload;
/**
 * 文件上传记录
 * 
 * @author flyfox 2018-01-12
 */
public class FileuploadService  extends BaseService<SysFileUpload>{
	
	public static final FileuploadService me = new FileuploadService(new SysFileUpload());
	
	public FileuploadService(SysFileUpload dao) {
		super(dao);
	}
	
}
