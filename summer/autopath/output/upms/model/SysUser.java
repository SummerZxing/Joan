package com.pengji.modules.upms.model;
import java.util.Date;
import com.pengji.base.BaseProjectModel;
import com.pengji.component.annotation.ModelBind;

@ModelBind(table = "sys_user" , key = "userid")
public class SysUser extends BaseProjectModel<SysUser> {

	private static final long serialVersionUID = 1L;
	public static final SysUser dao = new SysUser();
    
    public SysUser setUserid(Integer value) {
        set("userid", value);
        return this;
    }

	public Integer getUserid() {
		return get("userid");
	}
	
    public SysUser setUsername(String value) {
        set("username", value);
        return this;
    }

	public String getUsername() {
		return get("username");
	}
	
    public SysUser setPassword(String value) {
        set("password", value);
        return this;
    }

	public String getPassword() {
		return get("password");
	}
	
    public SysUser setRealname(String value) {
        set("realname", value);
        return this;
    }

	public String getRealname() {
		return get("realname");
	}
	
    public SysUser setDepartid(Integer value) {
        set("departid", value);
        return this;
    }

	public Integer getDepartid() {
		return get("departid");
	}
	
    public SysUser setUsertype(Integer value) {
        set("usertype", value);
        return this;
    }

	public Integer getUsertype() {
		return get("usertype");
	}
	
    public SysUser setState(Integer value) {
        set("state", value);
        return this;
    }

	public Integer getState() {
		return get("state");
	}
	
    public SysUser setThirdid(String value) {
        set("thirdid", value);
        return this;
    }

	public String getThirdid() {
		return get("thirdid");
	}
	
    public SysUser setEndtime(Date value) {
        set("endtime", value);
        return this;
    }

	public Date getEndtime() {
		return get("endtime");
	}
	
    public SysUser setEmail(String value) {
        set("email", value);
        return this;
    }

	public String getEmail() {
		return get("email");
	}
	
    public SysUser setTel(String value) {
        set("tel", value);
        return this;
    }

	public String getTel() {
		return get("tel");
	}
	
    public SysUser setAddress(String value) {
        set("address", value);
        return this;
    }

	public String getAddress() {
		return get("address");
	}
	
    public SysUser setTitleUrl(String value) {
        set("titleUrl", value);
        return this;
    }

	public String getTitleUrl() {
		return get("titleUrl");
	}
	
    public SysUser setRemark(String value) {
        set("remark", value);
        return this;
    }

	public String getRemark() {
		return get("remark");
	}
	
    public SysUser setTheme(String value) {
        set("theme", value);
        return this;
    }

	public String getTheme() {
		return get("theme");
	}
	
    public SysUser setCreateTime(Date value) {
        set("createTime", value);
        return this;
    }

	public Date getCreateTime() {
		return get("createTime");
	}
	
    public SysUser setCreateId(Integer value) {
        set("createId", value);
        return this;
    }

	public Integer getCreateId() {
		return get("createId");
	}
	
	
	@Override
	public String toString() {
		String log = ""; 
		log += "[userid:" + getUserid() + "]";
		log += "[username:" + getUsername() + "]";
		log += "[password:" + getPassword() + "]";
		log += "[realname:" + getRealname() + "]";
		log += "[departid:" + getDepartid() + "]";
		log += "[usertype:" + getUsertype() + "]";
		log += "[state:" + getState() + "]";
		log += "[thirdid:" + getThirdid() + "]";
		log += "[endtime:" + getEndtime() + "]";
		log += "[email:" + getEmail() + "]";
		log += "[tel:" + getTel() + "]";
		log += "[address:" + getAddress() + "]";
		log += "[titleUrl:" + getTitleUrl() + "]";
		log += "[remark:" + getRemark() + "]";
		log += "[theme:" + getTheme() + "]";
		log += "[createTime:" + getCreateTime() + "]";
		log += "[createId:" + getCreateId() + "]";
		return log;
	}
}