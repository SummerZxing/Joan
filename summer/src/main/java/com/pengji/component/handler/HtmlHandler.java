package com.pengji.component.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.handler.Handler;
import com.pengji.config.Config;
import com.pengji.utils.StrUtils;

/**
 * 路径伪静态化
 * 
 * 2015年5月5日 下午5:07:20 flyfox 330627517@qq.com
 */
public class HtmlHandler extends Handler {

	@Override
	public void handle(String s, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			boolean[] booleans) {

		/**
		 * 不是静态文件，才进行处理
		 */
		if (!isStatic(s)) {
			if (s.lastIndexOf(".html") != -1) {
				s = s.substring(0, s.indexOf(".html"));
			} else if (s.lastIndexOf(".htm") != -1) {
				s = s.substring(0, s.indexOf(".htm"));
			}
		}
		next.handle(s, httpServletRequest, httpServletResponse, booleans);
	}

	/**
	 * 判断是否是静态文件
	 * 
	 * 2015年6月6日 上午2:37:48 flyfox 330627517@qq.com
	 * 
	 * @param s
	 * @return
	 */
	private boolean isStatic(String s) {
		String suff = Config.getStr("HTMLHANDLER.SUFF");
		if (StrUtils.isEmpty(suff)) {
			return false;
		}
		String[] suffs = suff.split(",");
		for (String suffStr : suffs) {
			if (s.indexOf(suffStr) >= 0) {
				return true;
			}
		}

		return false;
	}
}
