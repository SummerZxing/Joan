package com.pengji.modules.upms.service;
import com.pengji.base.BaseService;
import com.pengji.modules.upms.model.SysDictDetail;
/**
 * 数据字典
 * 
 * @author flyfox 2018-01-12
 */
public class DictdetailService  extends BaseService<SysDictDetail>{
	
	public static final DictdetailService me = new DictdetailService(new SysDictDetail());
	
	public DictdetailService(SysDictDetail dao) {
		super(dao);
	}
	
}
