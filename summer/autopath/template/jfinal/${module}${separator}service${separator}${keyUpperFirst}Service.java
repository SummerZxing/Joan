package @{packagePath}.@{module}.service;
import com.pengji.base.BaseService;
import com.pengji.modules.@{module}.model.@{crud.table.className};
/**
 * @{crud.table.remarks}
 * 
 * @author flyfox @{now}
 */
public class @{strutils.toUpperCaseFirst(crud.urlKey)}Service  extends BaseService<@{crud.table.className}>{
	
	public static final @{strutils.toUpperCaseFirst(crud.urlKey)}Service me = new @{strutils.toUpperCaseFirst(crud.urlKey)}Service(new @{crud.table.className}());
	
	public @{strutils.toUpperCaseFirst(crud.urlKey)}Service(@{crud.table.className} dao) {
		super(dao);
	}
	
}
