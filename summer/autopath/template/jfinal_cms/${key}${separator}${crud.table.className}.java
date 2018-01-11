package @{packagePath}.@{crud.urlKey};
import java.util.Date;
import com.pengji.base.BaseProjectModel;
import com.pengji.component.annotation.ModelBind;

@ModelBind(table = "@{crud.table.tableName}")
public class @{crud.table.className} extends BaseProjectModel<@{crud.table.className}> {

	private static final long serialVersionUID = 1L;
	public static final @{crud.table.className} dao = new @{crud.table.className}();
    
    # for(column in crud.table.columns){ #
    public @{crud.table.className} set@{strutils.toUpperCaseFirst(column.columnJavaName)}(@{column.javaType} value) {
        set("@{strutils.toLowerCaseFirst(column.columnJavaName)}", value);
        return this;
    }

	public @{column.javaType} get@{strutils.toUpperCaseFirst(column.columnJavaName)}() {
		return get("@{strutils.toLowerCaseFirst(column.columnJavaName)}");
	}
	
	# } #
	
	@Override
	public String toString() {
		String log = ""; 
	# for(column in crud.table.columns){ #
		log += "[@{strutils.toLowerCaseFirst(column.columnJavaName)}:" + get@{strutils.toUpperCaseFirst(column.columnJavaName)}() + "]";
	# } #
		return log;
	}
}