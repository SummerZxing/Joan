package com.pengji.component.interceptor;


import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Action;
import com.jfinal.core.Controller;
import com.jfinal.core.JFinal;
import com.jfinal.log.Log;
import com.pengji.base.BaseController;
import com.pengji.base.BaseForm;
import com.pengji.utils.DateUtils;


/**
 * 公共属性拦截器
 * 
 * 
 */
public class CommonInterceptor implements Interceptor {
	
	protected static final Log log = Log.getLog(CommonInterceptor.class);
	
	private static int maxOutputLengthOfParaValue = 512;
	
	public void intercept(Invocation ai) {
		Controller controller = ai.getController();
		
		// 设置公共属性
		if (controller instanceof BaseController) {
			BaseForm form = ((BaseController) controller).getModelByForm(BaseForm.class);
			controller.setAttr("form", form);
		}
		HttpServletResponse response = controller.getResponse();
		//解决跨域问题
		setHeader(response);
		/**日志输出 ，暂时屏蔽*/
		//String target = ai.getActionKey();
		//String[] urlPara = {null};
		//Action action = JFinal.me().getAction(target, urlPara);
		//log.info(report(target,controller,action));
		ai.invoke();
	}
	
	
	public static final void setHeader(HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.211:8189");  
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");  
		response.setHeader("Access-Control-Max-Age", "0");  
		response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");  
		response.setHeader("Access-Control-Allow-Credentials", "true");  
		response.setHeader("XDomainRequestAllowed","1");  
	}
	
	/**
	 * Report the action
	 */
	public static final String report(String target, Controller controller, Action action) {
		StringBuilder sb = new StringBuilder("\n ---request--- ").append(DateUtils.getNow(DateUtils.DEFAULT_REGEX_YYYY_MM_DD_HH_MIN_SS)).append("----------------\n");
		sb.append("Url         : ").append(controller.getRequest().getMethod()).append(" ").append(target).append("\n");
		//sb.append("Method      : ").append(action.getMethodName()).append("\n");
		String urlParas = controller.getPara();
		if (urlParas != null) {
			sb.append("UrlPara     : ").append(urlParas).append("\n");
		}
		
		// print all parameters
		HttpServletRequest request = controller.getRequest();
		Enumeration<String> e = request.getParameterNames();
		if (e.hasMoreElements()) {
			sb.append("Parameter   : ");
			while (e.hasMoreElements()) {
				String name = e.nextElement();
				String[] values = request.getParameterValues(name);
				if (values.length == 1) {
					sb.append(name).append("=");
					if (values[0] != null && values[0].length() > maxOutputLengthOfParaValue) {
						sb.append(values[0].substring(0, maxOutputLengthOfParaValue)).append("...");
					} else {
						sb.append(values[0]);
					}
				}
				else {
					sb.append(name).append("[]={");
					for (int i=0; i<values.length; i++) {
						if (i > 0)
							sb.append(",");
						sb.append(values[i]);
					}
					sb.append("}");
				}
				sb.append("  ");
			}
			sb.append("\n");
		}
/*		Class<? extends Controller> cc = action.getControllerClass();
		sb.append("Controller  : ").append(cc.getName()).append(".(").append(cc.getSimpleName()).append(".java:1)\n");
		Interceptor[] inters = action.getInterceptors();
		if (inters.length > 0) {
			sb.append("Interceptor : ");
			for (int i=0; i<inters.length; i++) {
				if (i > 0)
					sb.append("\n              ");
				Interceptor inter = inters[i];
				Class<? extends Interceptor> ic = inter.getClass();
				sb.append(ic.getName()).append(".(").append(ic.getSimpleName()).append(".java:1)");
			}
			sb.append("\n");
		}*/
		sb.append("--------------------------------------------------------------------------------\n");
		return sb.toString();
	}

}
