package com.pengji.modules.upms.controller;

import com.jfinal.plugin.activerecord.Page;
import com.pengji.base.BaseProjectController;
import com.pengji.component.annotation.ControllerBind;
import com.pengji.modules.upms.model.SysDepartment;
import com.pengji.modules.upms.service.DepartmentService;
import com.pengji.utils.SQLUtils;
import com.pengji.utils.StrUtils;




/**
 * 部门
 * 
 * @author flyfox 2018-01-12
 */
@ControllerBind(controllerKey = "/upms/department")
public class DepartmentController extends BaseProjectController {

	//private static final Log log = Log.getLog(DepartmentController.class);
	
	private static DepartmentService srv = DepartmentService.me;
	
	private static final String path = "/pages/modules/upms/department/department_";

	public void index() {
		list();
	}
	
	public void list() {
		SysDepartment model = getModelByAttr(SysDepartment.class);
		
		SQLUtils sql = new SQLUtils(" from sys_department t where 1=1 ");
		if (model.getAttrValues().length != 0) {
			sql.setAlias("t");
			// 查询条件
			sql.whereEquals("name", model.getName()); 
		}
		// 排序
		String orderBy = getBaseForm().getOrderBy();
		if (StrUtils.isEmpty(orderBy)) {
			sql.append(" order by t.id desc ");
		} else {
			sql.append(" order by ").append(orderBy);
		}
		
		Page<SysDepartment> page = srv.paginate(getParaToInt("p", 1), 40,"select * ",sql);

		// 下拉框
		setAttr("page", page);
		setAttr("attr", model);
		render(path + "list.html");
	}

	public void add() {
		render(path + "add.html");
	}

	public void view() {
		SysDepartment model = srv.findById(getParaToInt());
		setAttr("model", model);
		render(path + "view.html");
	}

	public void delete() {
		srv.delete(getParaToInt());
		list();
	}

	public void edit() {
		SysDepartment model = srv.findById(getParaToInt());
		setAttr("model", model);
		render(path + "edit.html");
	}

	public void save() {
		Integer pid = getParaToInt();
		SysDepartment model = getModel(SysDepartment.class);
		if (pid != null && pid > 0) { // 更新
			srv.update(model);
		} else { // 新增
			srv.save(model);
		}
		renderMessage("保存成功");
	}
}
