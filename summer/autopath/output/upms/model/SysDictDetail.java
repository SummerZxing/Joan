package com.pengji.modules.upms.model;
import java.util.Date;
import com.pengji.base.BaseProjectModel;
import com.pengji.component.annotation.ModelBind;

@ModelBind(table = "sys_dict_detail" , key = "detail_id")
public class SysDictDetail extends BaseProjectModel<SysDictDetail> {

	private static final long serialVersionUID = 1L;
	public static final SysDictDetail dao = new SysDictDetail();
    
    public SysDictDetail setDetailId(Integer value) {
        set("detailId", value);
        return this;
    }

	public Integer getDetailId() {
		return get("detailId");
	}
	
    public SysDictDetail setDictType(String value) {
        set("dictType", value);
        return this;
    }

	public String getDictType() {
		return get("dictType");
	}
	
    public SysDictDetail setDetailName(String value) {
        set("detailName", value);
        return this;
    }

	public String getDetailName() {
		return get("detailName");
	}
	
    public SysDictDetail setDetailCode(String value) {
        set("detailCode", value);
        return this;
    }

	public String getDetailCode() {
		return get("detailCode");
	}
	
    public SysDictDetail setDetailSort(String value) {
        set("detailSort", value);
        return this;
    }

	public String getDetailSort() {
		return get("detailSort");
	}
	
    public SysDictDetail setDetailType(String value) {
        set("detailType", value);
        return this;
    }

	public String getDetailType() {
		return get("detailType");
	}
	
    public SysDictDetail setDetailState(String value) {
        set("detailState", value);
        return this;
    }

	public String getDetailState() {
		return get("detailState");
	}
	
    public SysDictDetail setDetailContent(String value) {
        set("detailContent", value);
        return this;
    }

	public String getDetailContent() {
		return get("detailContent");
	}
	
    public SysDictDetail setDetailRemark(String value) {
        set("detailRemark", value);
        return this;
    }

	public String getDetailRemark() {
		return get("detailRemark");
	}
	
    public SysDictDetail setCreateTime(String value) {
        set("createTime", value);
        return this;
    }

	public String getCreateTime() {
		return get("createTime");
	}
	
    public SysDictDetail setCreateId(Integer value) {
        set("createId", value);
        return this;
    }

	public Integer getCreateId() {
		return get("createId");
	}
	
	
	@Override
	public String toString() {
		String log = ""; 
		log += "[detailId:" + getDetailId() + "]";
		log += "[dictType:" + getDictType() + "]";
		log += "[detailName:" + getDetailName() + "]";
		log += "[detailCode:" + getDetailCode() + "]";
		log += "[detailSort:" + getDetailSort() + "]";
		log += "[detailType:" + getDetailType() + "]";
		log += "[detailState:" + getDetailState() + "]";
		log += "[detailContent:" + getDetailContent() + "]";
		log += "[detailRemark:" + getDetailRemark() + "]";
		log += "[createTime:" + getCreateTime() + "]";
		log += "[createId:" + getCreateId() + "]";
		return log;
	}
}