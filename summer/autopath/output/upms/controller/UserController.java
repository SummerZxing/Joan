package com.pengji.modules.upms.controller;

import com.jfinal.plugin.activerecord.Page;
import com.pengji.base.BaseProjectController;
import com.pengji.component.annotation.ControllerBind;
import com.pengji.modules.upms.model.SysUser;
import com.pengji.modules.upms.service.UserService;
import com.pengji.utils.SQLUtils;
import com.pengji.utils.StrUtils;




/**
 * 用户
 * 
 * @author flyfox 2018-01-12
 */
@ControllerBind(controllerKey = "/upms/user")
public class UserController extends BaseProjectController {

	//private static final Log log = Log.getLog(UserController.class);
	
	private static UserService srv = UserService.me;
	
	private static final String path = "/pages/modules/upms/user/user_";

	public void index() {
		list();
	}
	
	public void list() {
		SysUser model = getModelByAttr(SysUser.class);
		
		SQLUtils sql = new SQLUtils(" from sys_user t where 1=1 ");
		if (model.getAttrValues().length != 0) {
			sql.setAlias("t");
			// 查询条件
			sql.whereEquals("username", model.getUsername()); 
			sql.whereEquals("departid", model.getDepartid()); 
			sql.whereEquals("usertype", model.getUsertype()); 
		}
		// 排序
		String orderBy = getBaseForm().getOrderBy();
		if (StrUtils.isEmpty(orderBy)) {
			sql.append(" order by t.userid desc ");
		} else {
			sql.append(" order by ").append(orderBy);
		}
		
		Page<SysUser> page = srv.paginate(getParaToInt("pageNo", 1), 40,"select * ",sql);

		// 下拉框
		setAttr("page", page);
		setAttr("attr", model);
		render(path + "list.html");
	}

	public void add() {
		render(path + "add.html");
	}

	public void view() {
		SysUser model = srv.findById(getParaToInt());
		setAttr("model", model);
		render(path + "view.html");
	}

	public void delete() {
		srv.delete(getParaToInt());
		list();
	}

	public void edit() {
		SysUser model = srv.findById(getParaToInt());
		setAttr("model", model);
		render(path + "edit.html");
	}

	public void save() {
		Integer pid = getParaToInt();
		SysUser model = getModel(SysUser.class);
		if (pid != null && pid > 0) { // 更新
			srv.update(model);
		} else { // 新增
			srv.save(model);
		}
		renderMessage("保存成功");
	}
}
