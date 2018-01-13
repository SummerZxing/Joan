package com.pengji.modules.upms.controller;

import com.jfinal.plugin.activerecord.Page;
import com.pengji.base.BaseProjectController;
import com.pengji.component.annotation.ControllerBind;
import com.pengji.modules.upms.model.SysRoleMenu;
import com.pengji.modules.upms.service.RolemenuService;
import com.pengji.utils.SQLUtils;
import com.pengji.utils.StrUtils;




/**
 * 角色和菜单关联
 * 
 * @author flyfox 2018-01-12
 */
@ControllerBind(controllerKey = "/upms/rolemenu")
public class RolemenuController extends BaseProjectController {

	//private static final Log log = Log.getLog(RolemenuController.class);
	
	private static RolemenuService srv = RolemenuService.me;
	
	private static final String path = "/pages/modules/upms/rolemenu/rolemenu_";

	public void index() {
		list();
	}
	
	public void list() {
		SysRoleMenu model = getModelByAttr(SysRoleMenu.class);
		
		SQLUtils sql = new SQLUtils(" from sys_role_menu t where 1=1 ");
		if (model.getAttrValues().length != 0) {
			sql.setAlias("t");
			// 查询条件
		}
		// 排序
		String orderBy = getBaseForm().getOrderBy();
		if (StrUtils.isEmpty(orderBy)) {
			sql.append(" order by t.id desc ");
		} else {
			sql.append(" order by ").append(orderBy);
		}
		
		Page<SysRoleMenu> page = srv.paginate(getParaToInt("pageNo", 1), 40,"select * ",sql);

		// 下拉框
		setAttr("page", page);
		setAttr("attr", model);
		render(path + "list.html");
	}

	public void add() {
		render(path + "add.html");
	}

	public void view() {
		SysRoleMenu model = srv.findById(getParaToInt());
		setAttr("model", model);
		render(path + "view.html");
	}

	public void delete() {
		srv.delete(getParaToInt());
		list();
	}

	public void edit() {
		SysRoleMenu model = srv.findById(getParaToInt());
		setAttr("model", model);
		render(path + "edit.html");
	}

	public void save() {
		Integer pid = getParaToInt();
		SysRoleMenu model = getModel(SysRoleMenu.class);
		if (pid != null && pid > 0) { // 更新
			srv.update(model);
		} else { // 新增
			srv.save(model);
		}
		renderMessage("保存成功");
	}
}
