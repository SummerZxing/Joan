package com.pengji;

import com.jfinal.core.JFinal;


public class Main {
	public static void main(String[] args) {
        //jboot端口号配置
		JFinal.start("src/main/webapp",80, "/",
				 5);
	}
}

