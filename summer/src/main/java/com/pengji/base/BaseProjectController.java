package com.pengji.base;


import com.jfinal.core.Controller;
import com.pengji.utils.Attr;
import com.pengji.utils.cache.Cache;
import com.pengji.utils.cache.CacheManager;

/**
 * 项目BaseControler
 *
 * @author flyfox
 * @date 2015-08-02
 *
 */
public abstract class BaseProjectController extends BaseController {

	public void renderAuto(String view) {
		String path = getAutoPath(view);

		super.render(path);
	}

	public void redirectAuto(String view) {
		String path = getAutoPath(view);

		super.redirect(path);
	}

	protected String getAutoPath(String view) {
		String path = view;

		if (!view.startsWith("/")) {
			path = "/" + path;
		}

		if (view.startsWith("/")) {
			path = "/" + path;
		}

		path = path.replace("//", "/");
		return path;
	}

/*	*//**
	 * 方法重写
	 *
	 * 2015年8月2日 下午3:17:29 flyfox 369191470@qq.com
	 *
	 * @return
	 *//*
	@SuppressWarnings("rawtypes")
	public SessionUser getSessionUser() {
		SysUser sysUser = getSessionAttr(Attr.SESSION_NAME);
		try {
			// 如果session没有，cookie有~那么就设置到Session
			if (sysUser == null) {
				String cookieContent = getCookie(Attr.SESSION_NAME);
				if (cookieContent != null) {
					String key = JFlyFoxUtils.cookieDecrypt(cookieContent);
					if (StrUtils.isNotEmpty(key) && key.split(",").length == 2) {
						String userid = key.split(",")[0];
						String password = key.split(",")[1];
						sysUser = SysUser.dao.findFirst( "select * from sys_user where id = ? and password = ? ", userid, password);
						setSessionUser(sysUser);
					}
				}
			}
		} catch (Exception e) {
			// 异常cookie重新登陆
			removeSessionAttr(Attr.SESSION_NAME);
			removeCookie(Attr.SESSION_NAME);
			log.error("cooke user异常:", e);
			return null;
		}
		return sysUser;
	}

	
	
	*//**
	 * 方法重写
	 *
	 * 2015年8月2日 下午3:17:29 flyfox 369191470@qq.com
	 *
	 * @return
	 *//*
	@SuppressWarnings("rawtypes")
	public SessionUser setSessionUser(SessionUser user) {
		
		setSessionAttr(Attr.SESSION_NAME, user);
		// 设置cookie，用id+password作为
		SysUser sysUser = (SysUser) user;
		String key = sysUser.getId() + "," + user.getStr("password");
		String cookieContent = JFlyFoxUtils.cookieEncrypt(key);
		setCookie(Attr.SESSION_NAME, cookieContent, 7 * 24 * 60 * 60);
		// 如果是管理员 设置菜单权限
		return user;
	}*/

	/**
	 * 方法重写
	 *
	 * 2015年8月2日 下午3:17:29 flyfox 369191470@qq.com
	 *
	 * @return
	 */
	public void removeSessionUser() {
		removeSessionAttr(Attr.SESSION_NAME);
		// 删除cookie
		removeCookie(Attr.SESSION_NAME);
	}

	/**
	 * 用户登录，登出记录
	 *
	 * 2015年10月16日 下午2:36:39 flyfox 369191470@qq.com
	 *
	 * @param user
	 * @param operType
	 */
/*	protected void saveLog(SysUser user, String operType) {
		try {
			String tableName = "sys_user";
			String updateId = user.getStr("update_id");
			String updateTime = user.getStr("update_time");
			String sql = "INSERT INTO `sys_log` ( `log_type`, `oper_object`, `oper_table`," //
					+ " `oper_id`, `oper_type`, `oper_remark`, `create_time`, `create_id`) " //
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			Db.update(sql, SysLog.TYPE_SYSTEM, SysLog.getTableRemark(tableName), tableName, //
					updateId, operType, "", updateTime, updateId);
		} catch (Exception e) {
			log.error("添加日志失败", e);
		}
	}*/

	// ///////////////////栏目处理////////////
	// 获取用户设置的SITE对象，设置默认
/*	public SessionSite getSessionSite() {
		SessionSite sessionSite = getSessionAttr(SiteConstant.getSessionSite());
		// 获取用户设置的SITE对象，设置默认
		if (sessionSite == null) {
			sessionSite = new SessionSite();
			TbSite defaultSite = new SiteService().getDefaultSite();
			sessionSite.setModel(defaultSite);
			sessionSite.setSiteId(defaultSite.getId());
			setSessionAttr(SiteConstant.getSessionSite(), sessionSite);
		}
		return sessionSite;
	}

	public SessionSite setSessionSite(SessionSite sessionSite) {
		setSessionAttr(SiteConstant.getSessionSite(), sessionSite);
		return sessionSite;
	}

	@SuppressWarnings("rawtypes")
	public TbSite getBackSite() {
		SessionUser user = getSessionUser();
		if (user == null) {
			return null;
		}

		TbSite site = new SiteService().getSite(user.getBackSiteId());
		return site;
	}

	public String selectFolder(Integer selected) {
		return new FolderService().selectFolder(selected, getSessionUser().getBackSiteId());
	}

	public String selectFolder(Integer selected, Integer selfId) {
		return new FolderService().selectFolder(selected, selfId, getSessionUser().getBackSiteId());
	}
*/
	/**
	 * 公共文章查询sql
	 *
	 * 2016年3月19日 下午7:03:11 flyfox 369191470@qq.com
	 *
	 * @return
	 */
/*	public String getPublicWhere() {
		return " t.status =  " + JFlyFoxUtils.STATUS_SHOW //
				+ " and t.approve_status = " + ArticleConstant.APPROVE_STATUS_PASS // 审核通过
				+ " and t.type in (11,12) " // 查询状态为显示，类型是预览和正常的文章
		;
	}*/

	Cache cache = CacheManager.get("JFLYFOX_SESSION");

	public Controller setSessionAttrCache(String key, Object value) {
		String id = getSession().getId();
		cache.add(key + "_" + id, value);
		return this;
	}

	public <T> T getSessionAttrCache(String key) {
		String id = getSession().getId();
		return cache.get(key + "_" + id);
	}

	public Controller removeSessionAttrCache(String key) {
		String id = getSession().getId();
		cache.remove(key + "_" + id);
		return this;
	}
	
	public String getPara(String name) {
/*		System.out.println(getRequest().getContentType());
		String readData = HttpKit.readData(getRequest());
		System.out.println(readData);*/
/*		if(getRequest().getContentType().contains("application/json") || getRequest().getContentType().contains("text/json")){
			String json = getPara();
			System.out.println(json);
		}*/
		return super.getPara(name);
	}
	
	public String getRealPath(){
		return getRequest().getSession().getServletContext().getRealPath("/");
	}
	
	
	/**
	 * 是否是管理员
	 *
	 * 2017年1月21日 下午11:55:16 flyfox 369191470@qq.com
	 *
	 * @param user
	 * @return
	 */
/*	@SuppressWarnings("rawtypes")
	public boolean isAdmin(SessionUser user) {
		return user.getInt("usertype") == 1;
	}*/

	/**
	 * 文件上传处理
	 *
	 * 2017年4月5日 上午4:36:20 flyfox 369191470@qq.com
	 *
	 * @param site
	 * @param uploadFile
	 * @param appendPath
	 * @return
	 */
/*	public String uploadHandler(TbSite site, File uploadFile, String appendPath) {
		String fileUrl = "";
		String projectStorePath = FileUploadUtils.getUploadPath(site, appendPath);
		FileUploadBean uploadBean = new FileUploadService().uploadHandle(projectStorePath, uploadFile, getSessionUser()
				.getUserid());
		if (uploadBean != null) {
			fileUrl = projectStorePath + File.separator + uploadBean.getName();
		}
		return FileUploadUtils.rebuild(fileUrl);
	}*/
}
