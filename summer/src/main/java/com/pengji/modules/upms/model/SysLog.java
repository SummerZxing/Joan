package com.pengji.modules.upms.model;
import java.util.Date;
import com.pengji.base.BaseProjectModel;
import com.pengji.component.annotation.ModelBind;

@ModelBind(table = "sys_log" , key = "id")
public class SysLog extends BaseProjectModel<SysLog> {

	private static final long serialVersionUID = 1L;
	public static final SysLog dao = new SysLog();
    
    public SysLog setId(Integer value) {
        set("id", value);
        return this;
    }

	public Integer getId() {
		return get("id");
	}
	
    public SysLog setLogType(Integer value) {
        set("logType", value);
        return this;
    }

	public Integer getLogType() {
		return get("logType");
	}
	
    public SysLog setOperObject(String value) {
        set("operObject", value);
        return this;
    }

	public String getOperObject() {
		return get("operObject");
	}
	
    public SysLog setOperTable(String value) {
        set("operTable", value);
        return this;
    }

	public String getOperTable() {
		return get("operTable");
	}
	
    public SysLog setOperId(Integer value) {
        set("operId", value);
        return this;
    }

	public Integer getOperId() {
		return get("operId");
	}
	
    public SysLog setOperType(String value) {
        set("operType", value);
        return this;
    }

	public String getOperType() {
		return get("operType");
	}
	
    public SysLog setOperRemark(String value) {
        set("operRemark", value);
        return this;
    }

	public String getOperRemark() {
		return get("operRemark");
	}
	
    public SysLog setCreateTime(String value) {
        set("createTime", value);
        return this;
    }

	public String getCreateTime() {
		return get("createTime");
	}
	
    public SysLog setCreateId(Integer value) {
        set("createId", value);
        return this;
    }

	public Integer getCreateId() {
		return get("createId");
	}
	
	
	@Override
	public String toString() {
		String log = ""; 
		log += "[id:" + getId() + "]";
		log += "[logType:" + getLogType() + "]";
		log += "[operObject:" + getOperObject() + "]";
		log += "[operTable:" + getOperTable() + "]";
		log += "[operId:" + getOperId() + "]";
		log += "[operType:" + getOperType() + "]";
		log += "[operRemark:" + getOperRemark() + "]";
		log += "[createTime:" + getCreateTime() + "]";
		log += "[createId:" + getCreateId() + "]";
		return log;
	}
}