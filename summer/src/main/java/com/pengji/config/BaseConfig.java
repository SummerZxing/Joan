package com.pengji.config;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.beetl.core.GroupTemplate;
import org.beetl.ext.jfinal3.JFinal3BeetlRenderFactory;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.Const;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.log.Log4jLogFactory;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.SqlReporter;
import com.jfinal.plugin.activerecord.dialect.OracleDialect;
import com.jfinal.plugin.activerecord.dialect.PostgreSqlDialect;
import com.jfinal.plugin.activerecord.dialect.Sqlite3Dialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.pengji.component.annotation.AutoBindModels;
import com.pengji.component.annotation.AutoBindRoutes;
import com.pengji.component.beelt.BeeltFunctions;
import com.pengji.component.handler.BasePathHandler;
import com.pengji.component.handler.CurrentPathHandler;
import com.pengji.component.handler.HtmlHandler;
import com.pengji.component.swagger.SwaggerConfig;
import com.pengji.component.swagger.SwaggerManager;
import com.pengji.component.swagger.SwaggerUI;
import com.pengji.modules.model._MappingKit;
import com.pengji.utils.StrUtils;


public class BaseConfig extends JFinalConfig{
	
	private static final String CONFIG_WEB_ROOT = "{webroot}";
	
	private static Field requestField;  
	  
	private static Field parametersParsedField;  
	  
	private static Field coyoteRequestField;  
	  
	private static Field parametersField;  
	  
	private static Field hashTabArrField ; 
	
	@Override
	public void configConstant(Constants me) {
		
		 
		PropKit.use("a_little_config.txt");                // 加载少量必要配置，随后可用PropKit.get(...)获取值           
		me.setDevMode(isDevMode());
		me.setViewType(ViewType.JSP); // 设置视图类型为Jsp，否则默认为FreeMarker
		me.setLogFactory(new Log4jLogFactory());
		me.setError401View(Config.getStr("PAGES.401"));
		me.setError403View(Config.getStr("PAGES.403"));
		me.setError404View(Config.getStr("PAGES.404"));
		me.setError500View(Config.getStr("PAGES.500"));
		   //默认10M,此处设置为最大100M
	    me.setMaxPostSize(10*Const.DEFAULT_MAX_POST_SIZE);
	    
        JFinal3BeetlRenderFactory rf = new JFinal3BeetlRenderFactory();
        rf.config();
        me.setRenderFactory(rf);
    	// 获取GroupTemplate ,可以设置共享变量等操作
        GroupTemplate groupTemplate = rf.groupTemplate;
        groupTemplate.registerFunctionPackage("flyfox", BeeltFunctions.class);
/*		groupTemplate.registerFunctionPackage("strutil", BeetlStrUtils.class);
		groupTemplate.registerFunctionPackage("flyfox", BeeltFunctions.class);
		groupTemplate.registerFunctionPackage("temp", TemplateService.class);
		groupTemplate.registerFunctionPackage("tempImage", TemplateImageService.class);
		groupTemplate.registerFunctionPackage("tempVideo", TemplateVideoService.class);*/
		// 开启日志
		Config.setStartArg("jboot.swagger.path", "/swaggerui");
		Config.setStartArg("jboot.swagger.title", "API接口文档");
		Config.setStartArg("jboot.swagger.description", "");
		Config.setStartArg("jboot.swagger.version", "1.0");
		Config.setStartArg("jboot.swagger.termsOfService", "http://jboot.io");
		Config.setStartArg("jboot.swagger.contact", "email:summer_Zxing@163.com;qq:172608872");
		Config.setStartArg("jboot.swagger.host", "127.0.0.1");
		
		SqlReporter.setLog(true);
	}

	@Override
	public void configRoute(Routes me) {
		AutoBindRoutes autoBindRoutes = new AutoBindRoutes();
		autoBindRoutes.getRouteItemList();
		me.add(autoBindRoutes);
		// 添加swagger模块
        SwaggerConfig swaggerConfig = Config.config(SwaggerConfig.class);
        if (swaggerConfig.isConfigOk()) {
        	me.add(swaggerConfig.getPath(), SwaggerUI.class, swaggerConfig.getPath());
        }
    	for (Routes.Route route : autoBindRoutes.getRouteItemList()) {
           	Config.setMapping(route.getControllerKey(), route.getControllerClass());
    	}
        for (Routes.Route route : me.getRouteItemList()) {
        	Config.setMapping(route.getControllerKey(), route.getControllerClass());
        }
        // 初始化  SwaggerManager
       	SwaggerManager.me();
	}

	@Override
	public void configEngine(Engine me) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configPlugin(Plugins me) {
		// 配置C3p0数据库连接池插件
		C3p0Plugin c3p0Plugin = null;

		String db_type = Config.getStr("db_type") + ".";

		String webRoot = PathKit.getWebRootPath();
		String DBPath = webRoot + "\\WEB-INF\\";
		DBPath = StrUtils.replace(DBPath, "\\", "/");
		String jdbcUrl = Config.getStr(db_type + "jdbcUrl");
		if (db_type.startsWith("sqlite")) {
			jdbcUrl = StrUtils.replaceOnce(jdbcUrl, CONFIG_WEB_ROOT, DBPath);
		}

		c3p0Plugin = new C3p0Plugin( //
				jdbcUrl, Config.getStr(db_type + "user"), //
				Config.getStr(db_type + "password").trim(), //
				Config.getStr(db_type + "driverClass"));

		me.add(c3p0Plugin);

		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		me.add(arp);
		if (isDevMode()) {
			arp.setShowSql(true);
		}

		// 数据库类型
		if (db_type.startsWith("postgre")) {
			arp.setDialect(new PostgreSqlDialect());
		} else if (db_type.startsWith("sqlite")) {
			arp.setDialect(new Sqlite3Dialect());
		} else if (db_type.startsWith("oracle")) {
			arp.setDialect(new OracleDialect());
			arp.setContainerFactory(new CaseInsensitiveContainerFactory(true));
		}
		//new AutoBindModels(arp);
		// 所有映射在 MappingKit 中自动化搞定
		_MappingKit.mapping(arp);
		me.add(arp);
	}

	@Override
	public void configInterceptor(Interceptors me) {
/*		me.add(new ExceptionInterceptor());
		me.add(new LoginInterceptor());
		me.add(new CommonInterceptor());*/
	}

	@Override
	public void configHandler(Handlers me) {
		me.add(new HtmlHandler());
		// 全路径获取
		me.add(new BasePathHandler(Config.getStr("PATH.BASE_PATH")));
		// 根目录获取
		me.add(new ContextPathHandler(Config.getStr("PATH.CONTEXT_PATH")));
		// 当前获取
		me.add(new CurrentPathHandler(Config.getStr("PATH.CURRENT_PATH")));
	}

	private boolean isDevMode() {
		return Config.getToBoolean("CONSTANTS.DEV_MODE");
	}

	@Override
	public void afterJFinalStart() {
		super.afterJFinalStart();
		System.out.println("##################################");
		System.out.println("############start##########");
		System.out.println("##################################");
	}

	@Override
	public void beforeJFinalStop() {
		System.out.println("##################################");
		System.out.println("############shutdown##########");
		System.out.println("##################################");
		super.beforeJFinalStop();
	}
	
	@SuppressWarnings("unchecked")  
    public static Map<String , String[]> getRequestMapByTomcat(ServletRequest request) {  
        try {  
            Object innerRequest = requestField.get(request);  
            parametersParsedField.setBoolean(innerRequest, true);  
            Object coyoteRequestObject = coyoteRequestField.get(innerRequest);  
            Object parameterObject = parametersField.get(coyoteRequestObject);  
            return (Map<String,String[]>)hashTabArrField.get(parameterObject);  
        } catch (IllegalAccessException e) {  
            e.printStackTrace();  
            return Collections.emptyMap();  
        }  
    }  
    
	@SuppressWarnings("unchecked")  
    public static Map<String , String[]> getRequestMapByJetty(ServletRequest request) {  
        try {  
            Object parameterObject = parametersField.get(request);  
            return (Map<String,String[]>)parameterObject;  
        } catch (IllegalAccessException e) {  
            e.printStackTrace();  
            return Collections.emptyMap();  
        }  
    }  
    
    @SuppressWarnings("unused")
	private void initFieldByTomcat(){
		try {  
			Class clazz = Class.forName("org.apache.catalina.connector.RequestFacade"); 
            requestField = clazz.getDeclaredField("request");  
            requestField.setAccessible(true);  
  
  
            parametersParsedField = requestField.getType().getDeclaredField("parametersParsed");  
            parametersParsedField.setAccessible(true);  
  
  
            coyoteRequestField = requestField.getType().getDeclaredField("coyoteRequest");  
            coyoteRequestField.setAccessible(true);  
  
  
            parametersField = coyoteRequestField.getType().getDeclaredField("parameters");  
            parametersField.setAccessible(true);  
  
  
            hashTabArrField = parametersField.getType().getDeclaredField("paramHashStringArray");  
            hashTabArrField.setAccessible(true); 
			

        } catch (Exception e) {  
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.  
        }  
    }
    
    @SuppressWarnings("unused")
	private void initFieldByJetty(){
		try {  
		//jetty下	
		    Class clazz = Class.forName("org.eclipse.jetty.server.Request"); 
		    
/*		    requestField = clazz.getDeclaredField("request");  
            requestField.setAccessible(true);  */
  
  
 /*           parametersParsedField = requestField.getType().getDeclaredField("parametersParsed");  
            parametersParsedField.setAccessible(true);  
  
  
            coyoteRequestField = requestField.getType().getDeclaredField("coyoteRequest");  
            coyoteRequestField.setAccessible(true);  */
		    
		   // parametersField = clazz.getDeclaredField("_parameters");  
		    parametersField = org.eclipse.jetty.server.Request.class.getDeclaredField("_serverName");  
  //          parametersField = coyoteRequestField.getType().getDeclaredField("_parameters");  
            parametersField.setAccessible(true);  
/*  
  
            hashTabArrField = parametersField.getType().getDeclaredField("paramHashStringArray");  
            hashTabArrField.setAccessible(true); */
        } catch (Exception e) {  
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.  
        }  
    }
    
    public static void main(String[] args) {
	 
		try {
			   Class clazz = Class.forName("org.eclipse.jetty.server.Request");
			  parametersField = clazz.getDeclaredField("_parameters");  
			  System.out.println(parametersField);
			  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	  
	    
	}
}
