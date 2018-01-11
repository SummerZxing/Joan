package com.pengji.modules.user;
import java.util.Date;
import com.pengji.base.BaseProjectModel;
import com.pengji.component.annotation.ModelBind;

@ModelBind(table = "sys_user")
public class SysUser extends BaseProjectModel<SysUser> {

	private static final long serialVersionUID = 1L;
	public static final SysUser dao = new SysUser();
    
    public SysUser setId(Integer value) {
        set("id", value);
        return this;
    }

	public Integer getId() {
		return get("id");
	}
	
    public SysUser setSubjectId(Integer value) {
        set("subjectId", value);
        return this;
    }

	public Integer getSubjectId() {
		return get("subjectId");
	}
	
    public SysUser setGradeId(Integer value) {
        set("gradeId", value);
        return this;
    }

	public Integer getGradeId() {
		return get("gradeId");
	}
	
    public SysUser setLoginName(String value) {
        set("loginName", value);
        return this;
    }

	public String getLoginName() {
		return get("loginName");
	}
	
    public SysUser setPassword(String value) {
        set("password", value);
        return this;
    }

	public String getPassword() {
		return get("password");
	}
	
    public SysUser setNo(String value) {
        set("no", value);
        return this;
    }

	public String getNo() {
		return get("no");
	}
	
    public SysUser setName(String value) {
        set("name", value);
        return this;
    }

	public String getName() {
		return get("name");
	}
	
    public SysUser setPhone(String value) {
        set("phone", value);
        return this;
    }

	public String getPhone() {
		return get("phone");
	}
	
    public SysUser setUserType(String value) {
        set("userType", value);
        return this;
    }

	public String getUserType() {
		return get("userType");
	}
	
    public SysUser setPhoto(String value) {
        set("photo", value);
        return this;
    }

	public String getPhoto() {
		return get("photo");
	}
	
    public SysUser setLoginIp(String value) {
        set("loginIp", value);
        return this;
    }

	public String getLoginIp() {
		return get("loginIp");
	}
	
    public SysUser setLoginDate(Date value) {
        set("loginDate", value);
        return this;
    }

	public Date getLoginDate() {
		return get("loginDate");
	}
	
    public SysUser setLoginFlag(String value) {
        set("loginFlag", value);
        return this;
    }

	public String getLoginFlag() {
		return get("loginFlag");
	}
	
    public SysUser setCreateBy(Integer value) {
        set("createBy", value);
        return this;
    }

	public Integer getCreateBy() {
		return get("createBy");
	}
	
    public SysUser setCreateDate(Date value) {
        set("createDate", value);
        return this;
    }

	public Date getCreateDate() {
		return get("createDate");
	}
	
    public SysUser setUpdateBy(Integer value) {
        set("updateBy", value);
        return this;
    }

	public Integer getUpdateBy() {
		return get("updateBy");
	}
	
    public SysUser setUpdateDate(Date value) {
        set("updateDate", value);
        return this;
    }

	public Date getUpdateDate() {
		return get("updateDate");
	}
	
    public SysUser setRemarks(String value) {
        set("remarks", value);
        return this;
    }

	public String getRemarks() {
		return get("remarks");
	}
	
    public SysUser setDelFlag(String value) {
        set("delFlag", value);
        return this;
    }

	public String getDelFlag() {
		return get("delFlag");
	}
	
	
	@Override
	public String toString() {
		String log = ""; 
		log += "[id:" + getId() + "]";
		log += "[subjectId:" + getSubjectId() + "]";
		log += "[gradeId:" + getGradeId() + "]";
		log += "[loginName:" + getLoginName() + "]";
		log += "[password:" + getPassword() + "]";
		log += "[no:" + getNo() + "]";
		log += "[name:" + getName() + "]";
		log += "[phone:" + getPhone() + "]";
		log += "[userType:" + getUserType() + "]";
		log += "[photo:" + getPhoto() + "]";
		log += "[loginIp:" + getLoginIp() + "]";
		log += "[loginDate:" + getLoginDate() + "]";
		log += "[loginFlag:" + getLoginFlag() + "]";
		log += "[createBy:" + getCreateBy() + "]";
		log += "[createDate:" + getCreateDate() + "]";
		log += "[updateBy:" + getUpdateBy() + "]";
		log += "[updateDate:" + getUpdateDate() + "]";
		log += "[remarks:" + getRemarks() + "]";
		log += "[delFlag:" + getDelFlag() + "]";
		return log;
	}
}