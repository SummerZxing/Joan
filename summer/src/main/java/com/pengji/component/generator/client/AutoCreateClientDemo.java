package com.pengji.component.generator.client;

import java.sql.SQLException;
import java.util.Map;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.pengji.component.generator.autocreate.template.CRUD;
import com.pengji.component.generator.autocreate.util.AutoCreate;
import com.pengji.component.generator.autocreate.util.DbUtils;
import com.pengji.utils.StrUtils;

public class AutoCreateClientDemo {

	public static void main(String[] args) throws Exception {
		run();
	}


	protected static void run() throws SQLException, Exception {
		init();
		String module = "admin";
		String packagePath = "com.channelsoft.datacenter.module.admin";
//		 String tables = "sys_config,sys_department,sys_dict,sys_dict_detail,sys_log,sys_menu,sys_role,sys_role_menu,sys_user,sys_user_role";
//		String tables = "tb_contact";
//		String tables = "sys_city_info,tb_data_quality,tb_enterprise,tb_outfile,tb_require,tb_require_area,tb_rule,tb_rule_quality";
		String tables = "sys_user";

		Map<String, CRUD> crudMap = null;
		if (StrUtils.isEmpty(tables) || "all".equalsIgnoreCase(tables)) {
			crudMap = DbUtils.getCRUDMap();
		} else {
			String[] tableArray = tables.split(",");
			crudMap = DbUtils.getCRUDMap(tableArray);
		}
		System.out.println(crudMap);
		System.setProperty("user.dir","D:\\workspace\\datacenter\\wyx_component\\autocreate");
		new AutoCreate().setTemplatePath("/autopath/template/project/soft_admin/").setPackagePath(packagePath).setModule(module).setCrudMap(crudMap).create();
	}

	public static void init() {

		String jdbcUrl = "jdbc:mysql://192.168.1.174:3306/gtcms?characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
		String user = "root";
		String password = "pengji2017";
		String driverClass = "com.mysql.jdbc.Driver";

		System.out.println("####jdbcUrlRead:" + jdbcUrl);
		System.out.println("####user:" + user);
		System.out.println("####password:" + password.trim());
		System.out.println("####driverClass:" + driverClass);

		C3p0Plugin c3p0Plugin = new C3p0Plugin(jdbcUrl, user, password.trim(), driverClass);

		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		c3p0Plugin.start();
		arp.start();
	}

}
