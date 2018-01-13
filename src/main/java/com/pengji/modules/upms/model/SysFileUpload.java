package com.pengji.modules.upms.model;
import java.util.Date;
import com.pengji.base.BaseProjectModel;
import com.pengji.component.annotation.ModelBind;

@ModelBind(table = "sys_file_upload" , key = "id")
public class SysFileUpload extends BaseProjectModel<SysFileUpload> {

	private static final long serialVersionUID = 1L;
	public static final SysFileUpload dao = new SysFileUpload();
    
    public SysFileUpload setId(Integer value) {
        set("id", value);
        return this;
    }

	public Integer getId() {
		return get("id");
	}
	
    public SysFileUpload setName(String value) {
        set("name", value);
        return this;
    }

	public String getName() {
		return get("name");
	}
	
    public SysFileUpload setPath(String value) {
        set("path", value);
        return this;
    }

	public String getPath() {
		return get("path");
	}
	
    public SysFileUpload setFactpath(String value) {
        set("factpath", value);
        return this;
    }

	public String getFactpath() {
		return get("factpath");
	}
	
    public SysFileUpload setExt(String value) {
        set("ext", value);
        return this;
    }

	public String getExt() {
		return get("ext");
	}
	
    public SysFileUpload setOriginalname(String value) {
        set("originalname", value);
        return this;
    }

	public String getOriginalname() {
		return get("originalname");
	}
	
    public SysFileUpload setType(Integer value) {
        set("type", value);
        return this;
    }

	public Integer getType() {
		return get("type");
	}
	
    public SysFileUpload setSize(Integer value) {
        set("size", value);
        return this;
    }

	public Integer getSize() {
		return get("size");
	}
	
    public SysFileUpload setRemark(String value) {
        set("remark", value);
        return this;
    }

	public String getRemark() {
		return get("remark");
	}
	
    public SysFileUpload setBusinessType(Integer value) {
        set("businessType", value);
        return this;
    }

	public Integer getBusinessType() {
		return get("businessType");
	}
	
    public SysFileUpload setUpdateTime(String value) {
        set("updateTime", value);
        return this;
    }

	public String getUpdateTime() {
		return get("updateTime");
	}
	
    public SysFileUpload setUpdateId(Integer value) {
        set("updateId", value);
        return this;
    }

	public Integer getUpdateId() {
		return get("updateId");
	}
	
    public SysFileUpload setCreateTime(String value) {
        set("createTime", value);
        return this;
    }

	public String getCreateTime() {
		return get("createTime");
	}
	
    public SysFileUpload setCreateId(Integer value) {
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
		log += "[path:" + getPath() + "]";
		log += "[factpath:" + getFactpath() + "]";
		log += "[ext:" + getExt() + "]";
		log += "[originalname:" + getOriginalname() + "]";
		log += "[type:" + getType() + "]";
		log += "[size:" + getSize() + "]";
		log += "[remark:" + getRemark() + "]";
		log += "[businessType:" + getBusinessType() + "]";
		log += "[updateTime:" + getUpdateTime() + "]";
		log += "[updateId:" + getUpdateId() + "]";
		log += "[createTime:" + getCreateTime() + "]";
		log += "[createId:" + getCreateId() + "]";
		return log;
	}
}