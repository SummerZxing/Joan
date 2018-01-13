# Joan 
当前版本:1.0<br/>
让项目结束于数据库，数据字典设计完成之后 ，所有 后台代码一键生成 ，接口，接口文档一键生成，开发变的简单，上班只剩下聊天打屁。。。 期待大家加入

#相关技术
* 核心框架：jfinal
* 任务调度：(待定)
* 消息推送：(待定)
* 安全框架： (待定)
* 富文本：Ueditor(待集成)
* 微服务：dubbo (待集成)
* 数据库连接池：C3P0
* 日志管理：SLF4J 1.7.7、Log4j
* JS框架：jQuery 2.1.4
* CSS框架：Bootstrap 3
* 模板渲染：beelt
* api接口：swaggerUi
* 加解密技术：AES Base64 加密
* 代码生成模块

# 项目现状及项目规划
目前仅支持 小型项目 高效开发 ，
后期 集成 各模块 ，消息推送，分布式 ， 微服务 以做到支持海量用户 为目标

# 项目启动
 wei.xml 中配置了启动配置类 BaseConfig.java ， 所有相关配置 在 BaseConfig.java 中修改即可<br/>
 启动方式，建议 maven build   tomcat:run (Main.java 启动也可以，这种启动可能会样式加载不到)<br/><br/>

#文件说明 
* doc  项目文档文件夹，里面有数据库文件
* src/main maven结构的项目源代码  
* java/com/pengji java源代码文件
			base  项目通用的一些类
			component 项目成分
				beelt beelt项目 ， 自定义functions
				generator 代码生成
				handler 
				interceptor 
				log  日志相关
				swagger swaggerUi模块
			config  项目的一些配置类
			exception 异常
			modules  模块相关代码
			utils 	项目的一些工具类	
* resources 项目的资源配置文件
* webapp<br/>
   	 META-INF <br/>
	 WEB-INF <br/>
	 	 pages <br/>
			includes include文件
			modules <br/> 项目模块页面文件
				upms <br/> upms模块页面文件
			template <br/> 通用页面 head menu 等 .	
*   target  临时文件，可以删除让maven自动生成
* README.md 项目说明文档
* pom.xml maven的依赖配置文件

# swaggerUi
1. config.properties 下 swagger //相关配置
2. webapp/static/swageerui/  //静态资源
3. com.pengji.component.swagger*   //swaggerUi 模块
4. BaseConfig.java 配置加载，即可使用

# 代码生成 模块（generator）
1. template.properties //相关配置 ,配置你要生成的模块 ， 表  ，包名 ，模板地址， 代码生成地址
2. com.pengji.component.generator*  //generator模块代码
3. autopath                         //模板以及输出目录
4. AutoCreateClient.java      //main方法启动即可自动生成

# bean，controller扫描
1. config.properties 中 attr.modules 配置 模块 ， 多个以逗号 “,”隔开 
2. 项目启动时配置会自动扫描  com.pengji.modules.*.model 下  带有ModelBind 注解 的 bean, com.pengji.modules.*.controller 下 以Controller为结尾 或 带有 ControllerBind  注解 的 类 

