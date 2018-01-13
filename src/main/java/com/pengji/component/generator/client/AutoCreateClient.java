package com.pengji.component.generator.client;

import java.sql.SQLException;
import java.util.Map;

import com.pengji.component.generator.autocreate.template.CRUD;
import com.pengji.component.generator.autocreate.util.AutoCreate;
import com.pengji.component.generator.autocreate.util.DbUtils;
import com.pengji.config.Config;
import com.pengji.utils.StrUtils;

public class AutoCreateClient {

	public static void main(String[] args) throws Exception {
		run();
	}

	protected static void run() throws SQLException, Exception {
		DbUtils.init();

		String selected = Config.getStr("template.selected");
		String tables = Config.getStr("template.tables");
		Map<String, CRUD> crudMap = null;
		if (StrUtils.isEmpty(tables) || "all".equalsIgnoreCase(tables)) {
			crudMap = DbUtils.getCRUDMap();
		} else {
			String[] tableArray = tables.split(",");
			crudMap = DbUtils.getCRUDMap(tableArray);
		}

		new AutoCreate().setTemplatePath(Config.getStr(selected)).setCrudMap(crudMap).create();
	}

}
