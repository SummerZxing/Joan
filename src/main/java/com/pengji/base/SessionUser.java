package com.pengji.base;

import com.jfinal.plugin.activerecord.Model;

public class SessionUser<M extends Model<M>> extends Model<M> {

	private static final long serialVersionUID = 1L;
	
	public String getUserName() {
		return getStr("loginName");
	}
	
	public String getTheme() {
		return getStr("theme");
	}

}
