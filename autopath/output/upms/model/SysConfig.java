package com.pengji.modules.upms.model;
import java.util.Date;
import com.pengji.base.BaseProjectModel;
import com.pengji.component.annotation.ModelBind;

@ModelBind(table = "sys_config" , key = "id")
public class SysConfig extends BaseProjectModel<SysConfig> {

	private static final long serialVersionUID = 1L;
	public static final SysConfig dao = new SysConfig();
    
    public SysConfig setId(Integer value) {
        set("id", value);
        return this;
    }

	public Integer getId() {
		return get("id");
	}
	
    public SysConfig setName(String value) {
        set("name", value);
        return this;
    }

	public String getName() {
		return get("name");
	}
	
    public SysConfig setKey(String value) {
        set("key", value);
        return this;
    }

	public String getKey() {
		return get("key");
	}
	
    public SysConfig setValue(String value) {
        set("value", value);
        return this;
    }

	public String getValue() {
		return get("value");
	}
	
    public SysConfig setCode(String value) {
        set("code", value);
        return this;
    }

	public String getCode() {
		return get("code");
	}
	
    public SysConfig setType(Integer value) {
        set("type", value);
        return this;
    }

	public Integer getType() {
		return get("type");
	}
	
    public SysConfig setSort(Integer value) {
        set("sort", value);
        return this;
    }

	public Integer getSort() {
		return get("sort");
	}
	
    public SysConfig setUpdateTime(String value) {
        set("updateTime", value);
        return this;
    }

	public String getUpdateTime() {
		return get("updateTime");
	}
	
    public SysConfig setUpdateId(Integer value) {
        set("updateId", value);
        return this;
    }

	public Integer getUpdateId() {
		return get("updateId");
	}
	
    public SysConfig setCreateTime(String value) {
        set("createTime", value);
        return this;
    }

	public String getCreateTime() {
		return get("createTime");
	}
	
    public SysConfig setCreateId(Integer value) {
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
		log += "[name:" + getName() + "]";
		log += "[key:" + getKey() + "]";
		log += "[value:" + getValue() + "]";
		log += "[code:" + getCode() + "]";
		log += "[type:" + getType() + "]";
		log += "[sort:" + getSort() + "]";
		log += "[updateTime:" + getUpdateTime() + "]";
		log += "[updateId:" + getUpdateId() + "]";
		log += "[createTime:" + getCreateTime() + "]";
		log += "[createId:" + getCreateId() + "]";
		return log;
	}
}