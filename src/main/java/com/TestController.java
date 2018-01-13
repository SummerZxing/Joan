package com;

import javax.servlet.http.HttpSession;

import com.jfinal.core.Controller;
import com.swagger.MySwaggerPeople;

public class TestController  extends Controller{
	
	public void index(){
		  setAttr("title", "修改文章");
		  HttpSession session = getSession(true);
		  session.setAttribute("user", "张兴");
		  MySwaggerPeople mySwaggerPeople = new MySwaggerPeople();
		  mySwaggerPeople.setName("summer");
		  session.setAttribute("mySwaggerPeople", mySwaggerPeople);
		  session.setAttribute("theme", "flat-ui");
		  render("/pages/template/modify.html");
	}
}
