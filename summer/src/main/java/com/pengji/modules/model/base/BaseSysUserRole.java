package com.pengji.modules.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseSysUserRole<M extends BaseSysUserRole<M>> extends Model<M> implements IBean {

	public M setUserId(java.lang.Integer userId) {
		set("userId", userId);
		return (M)this;
	}

	public java.lang.Integer getUserId() {
		return getInt("userId");
	}

	public M setRoleId(java.lang.Integer roleId) {
		set("roleId", roleId);
		return (M)this;
	}

	public java.lang.Integer getRoleId() {
		return getInt("roleId");
	}

}