/**
 * Copyright 2015-2025 FLY的狐狸(email:jflyfox@sina.com qq:369191470).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package com.pengji.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FileUtils {

	/**
	 * 读取文件，返回byte[] 如果不存在，返回null
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static byte[] read(String path) throws IOException {
		int base_size = 1024;
		File file = new File(path);
		// 不存在创建
		if (!file.exists()) {
			return null;
		}

		FileInputStream fis = new FileInputStream(file);
		int len = 0;
		byte[] dataByte = new byte[base_size];

		ByteArrayOutputStream out = new ByteArrayOutputStream(base_size);
		while ((len = fis.read(dataByte)) != -1) {
			out.write(dataByte, 0, len);
		}
		byte[] content = out.toByteArray();

		fis.close();
		out.close();

		// 没有读取到数据
		if (content.length == 0) {
			return null;
		}

		return content;
	}

	/**
	 * 写文件，如果存在，删除
	 * 
	 * @param path
	 * @param data
	 * @throws IOException
	 */
	public static void write(String path, byte[] data) throws IOException {
		File file = new File(path);
		// 不存在，创建
		if (!file.exists()) {
			file.createNewFile();
		}

		FileOutputStream fos = new FileOutputStream(file);
		fos.write(data);
		fos.flush();
		fos.close();
	}

	/**
	 * 查找当前文件下所有properties文件
	 * 
	 * @param baseDirName
	 *            查找的文件夹路径
	 */
	public static List<String> findFiles(String baseDirName) {
		List<String> files = new ArrayList<String>();
		// 判断目录是否存在
		File baseDir = new File(baseDirName);
		if (!baseDir.exists() || !baseDir.isDirectory()) {
			System.err.println("search error：" + baseDirName + "is not a dir！");
		} else {
			String[] filelist = baseDir.list();
			for (String fileName : filelist) {
				files.add(fileName);
			}
		}
		return files;
	}

	/**
	 * 查找当前文件下所有properties文件
	 * 
	 * @param baseDirName
	 *            查找的文件夹路径
	 */
	public static List<String> findFileNames(String baseDirName, FileFilter fileFilter) {
		List<String> files = new ArrayList<String>();
		// 判断目录是否存在
		File baseDir = new File(baseDirName);
		if (!baseDir.exists() || !baseDir.isDirectory()) {
			System.err.println("search error：" + baseDirName + "is not a dir！");
		} else {
			File[] filelist = baseDir.listFiles(fileFilter);
			for (File file : filelist) {
				if (file.isFile())
					files.add(file.getName());
			}
		}
		return files;
	}
	
	/**
	 * 根据时间创建 层级文件夹
	 * 返回路径   rootPath/year/month/day/
	 */
	public synchronized static String createTimeDirectory(String rootPath){
		Calendar date=Calendar.getInstance();		 
		String year  = rootPath + File.separator + date.get(Calendar.YEAR);
		String month = year + File.separator + (date.get(Calendar.MONTH)+1);
		String day   = month + File.separator + date.get(Calendar.DAY_OF_MONTH);
		File yearDirectory = new File(year);
		File monthDirectory = new File(month);
		File dayDirectory = new File(day);
        if(!dayDirectory.exists()){
            if(!monthDirectory.exists()){
                if(!yearDirectory.exists()){
                	yearDirectory.mkdir();
                }
            	monthDirectory.mkdir();
            }
        	dayDirectory.mkdir();
        }
		return File.separator + date.get(Calendar.YEAR) + File.separator+(date.get(Calendar.MONTH)+1)+File.separator+date.get(Calendar.DAY_OF_MONTH)+File.separator;
	}
	
	
	 public static boolean Move(File srcFile, String destPath)
	 {
	        // Destination directory
	        File dir = new File(destPath);
	       
	        // Move file to new directory
	        boolean success = srcFile.renameTo(new File(dir, srcFile.getName()));
	       
	        return success;
	}
	 
	 public static boolean Move(String srcFile, String destPath)
	 {
	        // File (or directory) to be moved
	        File file = new File(srcFile);
	       
	        // Destination directory
	        File dir = new File(destPath);
	       
	        // Move file to new directory
	        boolean success = file.renameTo(new File(dir, file.getName()));
	       
	        return success;
	    }
	 
	 public  static   void     Copy(String     oldPath,     String     newPath)    
	   {    
	          try     {    
	                  int     bytesum     =     0;    
	                  int     byteread     =     0;    
	                  File     oldfile     =     new     File(oldPath);    
	                  if     (oldfile.exists())     {      
	                          InputStream     inStream     =     new     FileInputStream(oldPath);     
	                          FileOutputStream     fs     =     new     FileOutputStream(newPath);    
	                          byte[]     buffer     =     new     byte[1444];    
	                          int     length;    
	                          while     (     (byteread     =     inStream.read(buffer))     !=     -1)     {    
	                                  bytesum     +=     byteread;        
	                                  System.out.println(bytesum);    
	                                  fs.write(buffer,     0,     byteread);    
	                          }    
	                          inStream.close();    
	                  }    
	          }    
	          catch     (Exception     e)     {    
	                  System.out.println( "error  ");    
	                  e.printStackTrace();    
	          }    
	    }     
	   public   static  void     Copy(File     oldfile,     String     newPath)    
	   {    
	          try     {    
	                  int     bytesum     =     0;    
	                  int     byteread     =     0;    
	                  //File     oldfile     =     new     File(oldPath);    
	                  if     (oldfile.exists())     {      
	                          InputStream     inStream     =     new     FileInputStream(oldfile);     
	                          FileOutputStream     fs     =     new     FileOutputStream(newPath);    
	                          byte[]     buffer     =     new     byte[1444];    
	                          while     (     (byteread     =     inStream.read(buffer))     !=     -1)     {    
	                                  bytesum     +=     byteread;        
	                                  System.out.println(bytesum);    
	                                  fs.write(buffer,     0,     byteread);    
	                          }    
	                          inStream.close();    
	                  }    
	          }    
	          catch     (Exception     e)     {    
	                  System.out.println( "error  ");    
	                  e.printStackTrace();    
	          }    
	    }   
	   

	
}
