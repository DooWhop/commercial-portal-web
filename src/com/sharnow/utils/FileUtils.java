package com.sharnow.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
	static int countFiles = 0;
	static int countFolders = 0;
	
	 public static File[] searchFiles(File folder, final String... keyWords) {// 递归查找包含关键字的文件
		
	        File[] subFolders = folder.listFiles(new FileFilter() {// 运用内部匿名类获得文件
	        	
	            public boolean accept(File pathname) {// 实现FileFilter类的accept方法
	             	
	                if (pathname.isFile()){// 如果是文件
	                    countFiles++;
	                    }else{
	                    // 如果是目录
	                    countFolders++;
	                    }
	                if (pathname.isDirectory()){                       
	                    return true;
	                    }
	                for (String keyWord : keyWords) {//遍历含keyWord的文件
	                	String lowerPath = pathname.getName().toLowerCase();
	                	String lowerKwd = keyWord.toLowerCase();
	                	if(pathname.isFile() && lowerPath.contains(lowerKwd) && !(lowerPath.contains("svn"))){ 
	                		return true; 
	                	}               	
					}           
	                return false;
	            }
	        });
	 
	        List<File> result = new ArrayList<File>();// 声明一个集合
	        for (int i = 0; i < subFolders.length; i++) {// 循环显示文件夹或文件
	            if (subFolders[i].isFile()) {// 如果是文件则将文件添加到结果列表中
	                result.add(subFolders[i]);
	            } else {// 如果是文件夹，则递归调用本方法，然后把所有的文件加到结果列表中
	                File[] foldResult = searchFiles(subFolders[i], keyWords);
	                for (int j = 0; j < foldResult.length; j++) {// 循环显示文件
	                    result.add(foldResult[j]);// 文件保存到集合中
	                }
	            }
	        }
	 
	        File files[] = new File[result.size()];// 声明文件数组，长度为集合的长度
	        result.toArray(files);// 集合数组化
	        return files;
	    }

	 public static void writeFiles(String content, File file) throws IOException{
		 FileOutputStream fos = new FileOutputStream(file, false); 
		 OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");  
		 try {
			osw.write(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
         osw.close();
	 }
	 
	 public static File getRootPath() throws IOException{
		 return new File("").getCanonicalFile();
	 }
	 
	 
	 
	 
	    public static void main(String[] args) throws IOException {
	    	//getClass().getClassLoader().getResource("/").getPath();
	    	File f[] = null;
	    	System.out.println(new File("").getCanonicalFile());
			try {
				f = FileUtils.searchFiles(new File("").getCanonicalFile(), "HTML");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	for (File file : f) {
				System.out.println(file.getAbsolutePath());
			}
			
		}
}
