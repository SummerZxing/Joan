package com.pengji.modules.upms.model;
import java.util.Date;
import com.pengji.base.BaseProjectModel;
import com.pengji.component.annotation.ModelBind;

@ModelBind(table = "sys_dict" , key = "dict_id")
public class SysDict extends BaseProjectModel<SysDict> {

	private static final long serialVersionUID = 1L;
	public static final SysDict dao = new SysDict();
    
    public SysDict setDictId(Integer value) {
        set("dictId", value);
        return this;
    }

	public Integer getDictId() {
		return get("dictId");
	}
	
    public SysDict setDictName(String value) {
        set("dictName", value);
        return this;
    }

	public String getDictName() {
		return get("dictName");
	}
	
    public SysDict setDictType(String value) {
        set("dictType", value);
        return this;
    }

	public String getDictType() {
		return get("dictType");
	}
	
    public SysDict setDictRemark(String value) {
        set("dictRemark", value);
        return this;
    }

	public String getDictRemark() {
		return get("dictRemark");
	}
	
	
	@Override
	public String toString() {
		String log = ""; 
		log += "[dictId:" + getDictId() + "]";
		log += "[dictName:" + getDictName() + "]";
		log += "[dictType:" + getDictType() + "]";
		log += "[dictRemark:" + getDictRemark() + "]";
		return log;
	}
}