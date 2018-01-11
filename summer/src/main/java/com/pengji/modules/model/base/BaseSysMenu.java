package com.pengji.modules.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseSysMenu<M extends BaseSysMenu<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}

	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setParentId(java.lang.Integer parentId) {
		set("parentId", parentId);
		return (M)this;
	}

	public java.lang.Integer getParentId() {
		return getInt("parentId");
	}

	public M setParentIds(java.lang.String parentIds) {
		set("parentIds", parentIds);
		return (M)this;
	}

	public java.lang.String getParentIds() {
		return getStr("parentIds");
	}

	public M setName(java.lang.String name) {
		set("name", name);
		return (M)this;
	}

	public java.lang.String getName() {
		return getStr("name");
	}

	public M setSort(java.lang.Integer sort) {
		set("sort", sort);
		return (M)this;
	}

	public java.lang.Integer getSort() {
		return getInt("sort");
	}

	public M setHref(java.lang.String href) {
		set("href", href);
		return (M)this;
	}

	public java.lang.String getHref() {
		return getStr("href");
	}

	public M setTarget(java.lang.String target) {
		set("target", target);
		return (M)this;
	}

	public java.lang.String getTarget() {
		return getStr("target");
	}

	public M setIcon(java.lang.String icon) {
		set("icon", icon);
		return (M)this;
	}

	public java.lang.String getIcon() {
		return getStr("icon");
	}

	public M setIsShow(java.lang.String isShow) {
		set("isShow", isShow);
		return (M)this;
	}

	public java.lang.String getIsShow() {
		return getStr("isShow");
	}

	public M setPermission(java.lang.String permission) {
		set("permission", permission);
		return (M)this;
	}

	public java.lang.String getPermission() {
		return getStr("permission");
	}

	public M setCreateBy(java.lang.Integer createBy) {
		set("createBy", createBy);
		return (M)this;
	}

	public java.lang.Integer getCreateBy() {
		return getInt("createBy");
	}

	public M setCreateDate(java.util.Date createDate) {
		set("createDate", createDate);
		return (M)this;
	}

	public java.util.Date getCreateDate() {
		return get("createDate");
	}

	public M setUpdateBy(java.lang.Integer updateBy) {
		set("updateBy", updateBy);
		return (M)this;
	}

	public java.lang.Integer getUpdateBy() {
		return getInt("updateBy");
	}

	public M setUpdateDate(java.util.Date updateDate) {
		set("updateDate", updateDate);
		return (M)this;
	}

	public java.util.Date getUpdateDate() {
		return get("updateDate");
	}

	public M setRemarks(java.lang.String remarks) {
		set("remarks", remarks);
		return (M)this;
	}

	public java.lang.String getRemarks() {
		return getStr("remarks");
	}

	public M setDelFlag(java.lang.String delFlag) {
		set("delFlag", delFlag);
		return (M)this;
	}

	public java.lang.String getDelFlag() {
		return getStr("delFlag");
	}

}