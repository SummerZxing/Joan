package @{packagePath}.@{module}.controller;

import com.jfinal.plugin.activerecord.Page;
import com.pengji.base.BaseProjectController;
import com.pengji.component.annotation.ControllerBind;
import com.pengji.modules.upms.model.@{crud.table.className};
import com.pengji.modules.upms.service.@{strutils.toUpperCaseFirst(crud.urlKey)}Service;
import com.pengji.utils.SQLUtils;
import com.pengji.utils.StrUtils;




/**
 * @{crud.table.remarks}
 * 
 * @author flyfox @{now}
 */
@ControllerBind(controllerKey = "/@{module}/@{crud.urlKey}")
public class @{strutils.toUpperCaseFirst(crud.urlKey)}Controller extends BaseProjectController {

	//private static final Log log = Log.getLog(@{strutils.toUpperCaseFirst(crud.urlKey)}Controller.class);
	
	private static @{strutils.toUpperCaseFirst(crud.urlKey)}Service srv = @{strutils.toUpperCaseFirst(crud.urlKey)}Service.me;
	
	private static final String path = "/pages/modules/@{module}/@{crud.urlKey}/@{crud.urlKey}_";

	public void index() {
		list();
	}
	
	public void list() {
		@{crud.table.className} model = getModelByAttr(@{crud.table.className}.class);
		
		SQLUtils sql = new SQLUtils(" from @{crud.table.tableName} t where 1=1 ");
		if (model.getAttrValues().length != 0) {
			sql.setAlias("t");
			// 查询条件
			# for(entry in crud.searchMap){ #
				#
				var key = entry.value.key;
				var name = 'attr.'+entry.value.key;
				var data = flyfox.dataList(entry.value.formTypeData); 
				#
			sql.whereEquals("@{entry.value.key}", model.get@{strutils.toUpperCaseFirst(entry.value.key)}()); 
			#  }  #
		}
		// 排序
		String orderBy = getBaseForm().getOrderBy();
		if (StrUtils.isEmpty(orderBy)) {
			sql.append(" order by t.@{crud.primaryKey} desc ");
		} else {
			sql.append(" order by ").append(orderBy);
		}
		
		Page<@{crud.table.className}> page = srv.paginate(getParaToInt("pageNo", 1), 40,"select * ",sql);

		// 下拉框
		setAttr("page", page);
		setAttr("attr", model);
		render(path + "list.html");
	}

	public void add() {
		render(path + "add.html");
	}

	public void view() {
		@{crud.table.className} model = srv.findById(getParaToInt());
		setAttr("model", model);
		render(path + "view.html");
	}

	public void delete() {
		srv.delete(getParaToInt());
		list();
	}

	public void edit() {
		@{crud.table.className} model = srv.findById(getParaToInt());
		setAttr("model", model);
		render(path + "edit.html");
	}

	public void save() {
		Integer pid = getParaToInt();
		@{crud.table.className} model = getModel(@{crud.table.className}.class);
		if (pid != null && pid > 0) { // 更新
			srv.update(model);
		} else { // 新增
			srv.save(model);
		}
		renderMessage("保存成功");
	}
}
