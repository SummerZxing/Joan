package com.pengji.component.swagger;

import com.pengji.config.annotation.PropertieConfig;
import com.pengji.utils.StringUtils;

@PropertieConfig(prefix = "swagger")
public class SwaggerConfig {

    private String path;

    private String title;
    private String description;
    private String version;
    private String termsOfService;
    private String host;

    // 多个联系方式用分号；隔开  例如：jboot.swagger.contact=email:summer_Zxing@163.com;qq:172608872
    private String contact;
    
    // 模板地址
    private String tplPath;
    
    

    public String getTplPath() {
		return tplPath;
	}

	public void setTplPath(String tplPath) {
		this.tplPath = tplPath;
	}

	public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isConfigOk() {
        return StringUtils.isNotBlank(path);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTermsOfService() {
        return termsOfService;
    }

    public void setTermsOfService(String termsOfService) {
        this.termsOfService = termsOfService;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}



