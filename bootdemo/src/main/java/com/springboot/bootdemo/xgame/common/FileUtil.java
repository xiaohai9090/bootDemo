package com.springboot.bootdemo.xgame.common;

import org.apache.log4j.Logger;

import java.io.*;

public class FileUtil {
	private static Logger logger = Logger.getLogger(FileUtil.class);

	// 文件路径
	private static final String PROPERTY_FILE;
	static {
		PROPERTY_FILE = replaceSpace(Thread.currentThread()
				.getContextClassLoader().getResource("").getPath()
				+ "id.txt");
	}

	public static int readWriteData() {
		BufferedReader reader = null;
		BufferedWriter writer = null;

		try {
			reader = new BufferedReader(new FileReader(new File(PROPERTY_FILE)));
			String ID = reader.readLine();
			reader.close();
			int newID = Integer.parseInt( ID.trim()) + 1 ; // id+1
			writer = new BufferedWriter(new FileWriter(new File(PROPERTY_FILE)));
			writer.write(newID + "");
			writer.close();
			return newID;
		} catch (Exception e) {
			logger.error("txt.exception", e);
			return 0;
		} finally {
			if (reader != null)
				reader = null;
			if (writer != null)
				writer = null;
		}
	}

	public static String replaceSpace(String path) {
		return path.replace("%20", " ");
	}
	
	//读取文件内容
	public static String ReadFileContent(String path)throws Exception{    
		try
		{
			StringBuilder str = new StringBuilder();
			
			//文件绝对路径改成你自己的文件路径    
			FileReader fr=new FileReader(path);     
			//可以换成工程目录下的其他文本文件      
			BufferedReader br=new BufferedReader(fr);    
			String s;
			while((s= br.readLine())!=null){                
				str.append(s);   
			} 
			br.close();  
			return str.toString();
		}
		catch(Exception ex)
		{
			System.out.print("授权文件不存在");
			throw new Exception("授权文件不存在");
		}

	}
}
