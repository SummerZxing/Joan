/**
 * Copyright (c) 2015-2017, Michael Yang 杨福海 (fuhai999@gmail.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 *  http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.swagger;

import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;
import com.pengji.component.annotation.ControllerBind;
import com.pengji.component.swagger.annotation.SwaggerAPI;
import com.pengji.component.swagger.annotation.SwaggerAPIs;
import com.pengji.component.swagger.annotation.SwaggerParam;

/**
 * @author Michael Yang 杨福海 （fuhai999@gmail.com）
 * @version V1.0
 * @Package swagger
 */
@SwaggerAPIs(name = "测试接口", description = "这个接口集合的描述")
@ControllerBind(controllerKey = "/swaggerTest")
public class MySwaggerTestController extends Controller {


    @SwaggerAPI(description = "测试description描述", summary = "测试summary", operationId = "testOnly",
            params = {@SwaggerParam(name = "name", description = "请输入账号名称")}
    )
    public void index() {
        renderJson(Ret.ok("k1", "v1").set("name", getPara("name")));
    }


    @SwaggerAPI(description = "进行用户登录操作", summary = "用户登录API", method = "post",
            params = {
                    @SwaggerParam(name = "name", description = "请输入账号名称"),
                    @SwaggerParam(name = "pwd", description = "请输入密码", definition = "MySwaggerPeople")
            }
    )
    public void login() {
        renderJson(Ret.ok("k2", "vv").set("name", getPara("name")));
    }
    
    
}
