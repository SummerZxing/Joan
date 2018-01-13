package com.pengji.modules.upms.model;
import java.util.Date;
import com.pengji.base.BaseProjectModel;
import com.pengji.component.annotation.ModelBind;

@ModelBind(table = "sys_role_menu" , key = "id")
public class SysRoleMenu extends BaseProjectModel<SysRoleMenu> {

	private static final long serialVersionUID = 1L;
	public static final SysRoleMenu dao = new SysRoleMenu();
    
    public SysRoleMenu setId(Integer value) {
        set("id", value);
        return this;
    }

	public Integer getId() {
		return get("id");
	}
	
    public SysRoleMenu setRoleid(Integer value) {
        set("roleid", value);
        return this;
    }

	public Integer getRoleid() {
		return get("roleid");
	}
	
    public SysRoleMenu setMenuid(Integer value) {
        set("menuid", value);
        return this;
    }

	public Integer getMenuid() {
		return get("menuid");
	}
	
	
	@Override
	public String toString() {
		String log = ""; 
		log += "[id:" + getId() + "]";
		log += "[roleid:" + getRoleid() + "]";
		log += "[menuid:" + getMenuid() + "]";
		return log;
	}
}