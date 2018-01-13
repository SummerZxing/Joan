package com.pengji.modules.upms.model;
import java.util.Date;
import com.pengji.base.BaseProjectModel;
import com.pengji.component.annotation.ModelBind;

@ModelBind(table = "sys_department" , key = "id")
public class SysDepartment extends BaseProjectModel<SysDepartment> {

	private static final long serialVersionUID = 1L;
	public static final SysDepartment dao = new SysDepartment();
    
    public SysDepartment setId(Integer value) {
        set("id", value);
        return this;
    }

	public Integer getId() {
		return get("id");
	}
	
    public SysDepartment setParentId(Integer value) {
        set("parentId", value);
        return this;
    }

	public Integer getParentId() {
		return get("parentId");
	}
	
    public SysDepartment setName(String value) {
        set("name", value);
        return this;
    }

	public String getName() {
		return get("name");
	}
	
    public SysDepartment setCode(String value) {
        set("code", value);
        return this;
    }

	public String getCode() {
		return get("code");
	}
	
    public SysDepartment setSort(Integer value) {
        set("sort", value);
        return this;
    }

	public Integer getSort() {
		return get("sort");
	}
	
    public SysDepartment setLinkman(String value) {
        set("linkman", value);
        return this;
    }

	public String getLinkman() {
		return get("linkman");
	}
	
    public SysDepartment setLinkmanNo(String value) {
        set("linkmanNo", value);
        return this;
    }

	public String getLinkmanNo() {
		return get("linkmanNo");
	}
	
    public SysDepartment setRemark(String value) {
        set("remark", value);
        return this;
    }

	public String getRemark() {
		return get("remark");
	}
	
    public SysDepartment setUpdateTime(String value) {
        set("updateTime", value);
        return this;
    }

	public String getUpdateTime() {
		return get("updateTime");
	}
	
    public SysDepartment setUpdateId(Integer value) {
        set("updateId", value);
        return this;
    }

	public Integer getUpdateId() {
		return get("updateId");
	}
	
    public SysDepartment setCreateTime(String value) {
        set("createTime", value);
        return this;
    }

	public String getCreateTime() {
		return get("createTime");
	}
	
    public SysDepartment setCreateId(Integer value) {
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
		log += "[parentId:" + getParentId() + "]";
		log += "[name:" + getName() + "]";
		log += "[code:" + getCode() + "]";
		log += "[sort:" + getSort() + "]";
		log += "[linkman:" + getLinkman() + "]";
		log += "[linkmanNo:" + getLinkmanNo() + "]";
		log += "[remark:" + getRemark() + "]";
		log += "[updateTime:" + getUpdateTime() + "]";
		log += "[updateId:" + getUpdateId() + "]";
		log += "[createTime:" + getCreateTime() + "]";
		log += "[createId:" + getCreateId() + "]";
		return log;
	}
}