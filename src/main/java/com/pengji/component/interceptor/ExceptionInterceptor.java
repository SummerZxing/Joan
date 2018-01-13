
package com.pengji.component.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.pengji.base.ApiResp;
import com.pengji.base.constant.ApiConstant;

/**
 * 设置用户session公共属性
 * 
 * 2014年8月9日 下午11:18:02 flyfox 330627517@qq.com
 */
public class ExceptionInterceptor implements Interceptor {

	private final static Log log = Log.getLog(ExceptionInterceptor.class);

	public void intercept(Invocation ai) {

		try {
			ai.invoke();
		} catch (Exception e) {
			log.error("异常：", e);
			Controller controller = ai.getController();
			controller.renderJson(ApiResp.error(ApiConstant.CODE_METHOD_HANDLER_ERROR, e.toString()));
		}

	}
}
