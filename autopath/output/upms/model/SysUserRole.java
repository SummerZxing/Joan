package com.pengji.modules.upms.model;
import java.util.Date;
import com.pengji.base.BaseProjectModel;
import com.pengji.component.annotation.ModelBind;

@ModelBind(table = "sys_user_role" , key = "id")
public class SysUserRole extends BaseProjectModel<SysUserRole> {

	private static final long serialVersionUID = 1L;
	public static final SysUserRole dao = new SysUserRole();
    
    public SysUserRole setId(Integer value) {
        set("id", value);
        return this;
    }

	public Integer getId() {
		return get("id");
	}
	
    public SysUserRole setUserid(Integer value) {
        set("userid", value);
        return this;
    }

	public Integer getUserid() {
		return get("userid");
	}
	
    public SysUserRole setRoleid(Integer value) {
        set("roleid", value);
        return this;
    }

	public Integer getRoleid() {
		return get("roleid");
	}
	
	
	@Override
	public String toString() {
		String log = ""; 
		log += "[id:" + getId() + "]";
		log += "[userid:" + getUserid() + "]";
		log += "[roleid:" + getRoleid() + "]";
		return log;
	}
}