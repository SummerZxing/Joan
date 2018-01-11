package com.pengji.component.interceptor;



import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.pengji.base.ApiResp;
import com.pengji.base.BaseProjectController;
import com.pengji.base.SessionUser;
import com.pengji.base.constant.ApiConstant;
import com.pengji.component.annotation.IgnoreSecurity;
import com.pengji.utils.EmptyUtil;
import com.pengji.utils.UserUtils;

public class LoginInterceptor  implements Interceptor {

	@Override
	public void intercept(Invocation ai) {
		//判断目标方法是否需要登录
		if(ai.getMethod().isAnnotationPresent(IgnoreSecurity.class)){
			ai.invoke();
		}else{
			BaseProjectController controller = (BaseProjectController) ai.getController();
			SessionUser sessionUser = controller.getSessionUser();
			if(!EmptyUtil.isNullOrEmpty(sessionUser)){
				UserUtils.set("user", sessionUser);
				ai.invoke();
			}else{
				controller.renderJson(ApiResp.error(ApiConstant.CODE_LOGIN_VALID_ERROR, "用户未登录！"));
			}
		}
	}

}
