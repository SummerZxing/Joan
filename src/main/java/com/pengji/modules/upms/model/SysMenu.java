package com.pengji.modules.upms.model;
import java.util.Date;
import com.pengji.base.BaseProjectModel;
import com.pengji.component.annotation.ModelBind;

@ModelBind(table = "sys_menu" , key = "id")
public class SysMenu extends BaseProjectModel<SysMenu> {

	private static final long serialVersionUID = 1L;
	public static final SysMenu dao = new SysMenu();
    
    public SysMenu setId(Integer value) {
        set("id", value);
        return this;
    }

	public Integer getId() {
		return get("id");
	}
	
    public SysMenu setParentid(Integer value) {
        set("parentid", value);
        return this;
    }

	public Integer getParentid() {
		return get("parentid");
	}
	
    public SysMenu setName(String value) {
        set("name", value);
        return this;
    }

	public String getName() {
		return get("name");
	}
	
    public SysMenu setUrlkey(String value) {
        set("urlkey", value);
        return this;
    }

	public String getUrlkey() {
		return get("urlkey");
	}
	
    public SysMenu setUrl(String value) {
        set("url", value);
        return this;
    }

	public String getUrl() {
		return get("url");
	}
	
    public SysMenu setStatus(Integer value) {
        set("status", value);
        return this;
    }

	public Integer getStatus() {
		return get("status");
	}
	
    public SysMenu setType(Integer value) {
        set("type", value);
        return this;
    }

	public Integer getType() {
		return get("type");
	}
	
    public SysMenu setSort(Integer value) {
        set("sort", value);
        return this;
    }

	public Integer getSort() {
		return get("sort");
	}
	
    public SysMenu setLevel(Integer value) {
        set("level", value);
        return this;
    }

	public Integer getLevel() {
		return get("level");
	}
	
    public SysMenu setCreateTime(String value) {
        set("createTime", value);
        return this;
    }

	public String getCreateTime() {
		return get("createTime");
	}
	
    public SysMenu setCreateId(Integer value) {
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
		log += "[parentid:" + getParentid() + "]";
		log += "[name:" + getName() + "]";
		log += "[urlkey:" + getUrlkey() + "]";
		log += "[url:" + getUrl() + "]";
		log += "[status:" + getStatus() + "]";
		log += "[type:" + getType() + "]";
		log += "[sort:" + getSort() + "]";
		log += "[level:" + getLevel() + "]";
		log += "[createTime:" + getCreateTime() + "]";
		log += "[createId:" + getCreateId() + "]";
		return log;
	}
}