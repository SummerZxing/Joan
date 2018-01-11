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
package com.pengji.config.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.pengji.config.ConfigConfig;
import com.pengji.config.ConfigManager;
import com.pengji.config.PropInfoMap;
import com.pengji.utils.BaseUtil;
import com.pengji.utils.StringUtils;

/**
 * 配置文件的Controller，用于给其他应用提供分布式配置读取功能
 */
@Clear
@Before(ConfigInterceptor.class)
public class ConfigController extends Controller {


    ConfigConfig config = BaseUtil.config(ConfigConfig.class);


    public void index() {
        String id = getPara();
        if (StringUtils.isBlank(id)) {
            renderJson(ConfigManager.me().getPropInfoMap());
            return;
        } else {
            PropInfoMap propInfos = ConfigManager.me().getPropInfoMap();
            for (PropInfoMap.Entry<String, PropInfoMap.PropInfo> entry : propInfos.entrySet()) {
                if (id.equals(entry.getKey())) {
                    renderJson(PropInfoMap.create(entry.getKey(), entry.getValue()));
                    return;
                }
            }
        }
        renderJson("{}");
    }


    /**
     * 列出本地目录下的文件信息
     */
    public void list() {
        List<HashMap<String, String>> props = new ArrayList<>();
        PropInfoMap propInfos = ConfigManager.me().getPropInfoMap();
        for (PropInfoMap.Entry<String, PropInfoMap.PropInfo> entry : propInfos.entrySet()) {
            HashMap<String, String> prop = new HashMap<>();
            prop.put("id", entry.getKey());
            prop.put("version", entry.getValue().getVersion());
            props.add(prop);
        }
        renderJson(props.toArray());
    }
}
