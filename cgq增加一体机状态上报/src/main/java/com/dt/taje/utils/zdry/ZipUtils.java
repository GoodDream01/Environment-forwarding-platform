package com.dt.taje.utils.zdry;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipUtils {
	
	public static boolean jieZip(String filepath,String savePath) throws Exception{
	    boolean flag = false;	
	    long startTime=System.currentTimeMillis();  

        ZipInputStream Zin=new ZipInputStream(new FileInputStream(filepath));//输入源zip路径  
        BufferedInputStream Bin=new BufferedInputStream(Zin);  
        String Parent=savePath; //输出路径（文件夹目录）  
        File Fout=null;  
        ZipEntry entry;  

        while((entry = Zin.getNextEntry())!=null && !entry.isDirectory()){  
            Fout=new File(Parent,entry.getName());  
            if(!Fout.exists()){  
                (new File(Fout.getParent())).mkdirs();  
            }  
            FileOutputStream out=new FileOutputStream(Fout);  
            BufferedOutputStream Bout=new BufferedOutputStream(out);  
            int b;  
            while((b=Bin.read())!=-1){  
                Bout.write(b);  
            }  
            Bout.close();  
            out.close();  
            System.out.println(Fout+"解压成功");      
        }  
        Bin.close();  
        Zin.close();  

        flag = true;
        
        long endTime=System.currentTimeMillis();  
        System.out.println("耗费时间： "+(endTime-startTime)+" ms");  
        
        return flag;
	}
}
