package com.dt.taje.utils.zdry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dt.taje.db.ConnDataBase;
import com.dt.taje.utils.ComUtils;

public class ZdrykDownload {
	public static final String ITMEID_ZDRY = "1001";
	
	public static final String FILE_URL = "http://localhost:7001/taje/pages/kstg/demo/2016052716RY.TXT.RAR";
	public static void downLoad() throws Exception{
		ConnDataBase bean0 = new ConnDataBase();
		String FILEURL = "";
		String FILENAME = "";
		String FILEEXTENSION = "";
		String SAVEPATH = "";
		String sql = "";
		sql = "select * from tb_zdk_xzsz where itemid='"+ITMEID_ZDRY+"' ";
		List list = bean0.executeQuery(sql);
		Map map = (HashMap)list.get(0);
		FILEURL = map.get("FILEURL").toString();
		FILENAME = map.get("FILENAME").toString();
		FILEEXTENSION = map.get("FILEEXTENSION").toString();
		SAVEPATH = map.get("SAVEPATH").toString();
		
		String fileName=FILENAME+FILEEXTENSION;
		String savePath=SAVEPATH+File.separator+ComUtils.getTime(6);
		String fileUrl = FILEURL+"/"+fileName;
		DownloadUrlFile.downLoadFromUrl(fileUrl, fileName, savePath);
		
		
		String filepath = savePath+File.separator+fileName;
		ZipUtils.jieZip(filepath, savePath);
		
		String textFileName =fileName.replaceAll(".RAR", "");
		String textFilepath = savePath+File.separator+textFileName;
		SaveZdry(textFilepath, FILENAME);
	}
	public static void SaveZdry(String filepath,String FILENAME){
		String sql = "";
		String data = null;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			
			ConnDataBase bean0 = new ConnDataBase();
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filepath),Charset.forName("GBK")));
			
			conn = bean0.getConn();
			conn.setAutoCommit(false);
			while((data = br.readLine())!=null)
			{
				System.out.println(data); 
				String[] datas=data.split("<,>");
				if(datas!=null && datas.length>0){
					String ZID = datas[0];
					String RYXM = datas[1];
					String SEX = datas[2];
					String SFZH = datas[3];
					String GSDDM = datas[4];
					String LBDM = datas[5];
					String WZZD1 = datas[6];
					String WZZD2 = datas[7];
					String WZZD3 = datas[8];
					String WZZD4 = datas[9];
					String WZZD5 = datas[10];
					String WZZD6 = datas[11];
					String WZZD7 = datas[12];
					String CZCS = datas[13];
					String WZZD8 = datas[14];
					
					String YLZD1="0";
					
					
					
					sql = "delete from TB_ZDRYK where ZID='"+ZID+"'";
					ps = conn.prepareStatement(sql);
					ps.executeUpdate();
					if(ps!=null){
						ps.close();
					}
					
					if(WZZD8.equals("U")){

						sql = "insert into TB_ZDRYK (ZID, RYXM, SEX, SFZH, GSDDM, LBDM, WZZD1, WZZD2, WZZD3, WZZD4, WZZD5, WZZD6, WZZD7, CZCS, WZZD8,YLZD1,DRSJ) values(";
						sql+=" '"+ZID+"', ";
						sql+=" '"+RYXM+"', ";
						sql+=" '"+SEX+"', ";
						sql+=" '"+SFZH+"', ";
						sql+=" '"+GSDDM+"', ";
						sql+=" '"+LBDM+"', ";
						sql+=" '"+WZZD1+"', ";
						sql+=" '"+WZZD2+"', ";
						sql+=" '"+WZZD3+"', ";
						sql+=" '"+WZZD4+"', ";
						sql+=" '"+WZZD5+"', ";
						sql+=" '"+WZZD6+"', ";
						sql+=" '"+WZZD7+"', ";
						sql+=" '"+CZCS+"', ";
						sql+=" '"+WZZD8+"', ";
						sql+=" '"+YLZD1+"', ";
						sql+=" sysdate ";
						sql+=" ) ";
						
						ps = conn.prepareStatement(sql);
						ps.executeUpdate();
						if(ps!=null){
							ps.close();
						}
					}

				}
			}
			String nextFileName = getNextFileName(FILENAME);
			sql = "update tb_zdk_xzsz set filename='"+nextFileName+"',gxsj=sysdate where itemid='"+ITMEID_ZDRY+"' ";
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			if(ps!=null){
				ps.close();
			}
			
			conn.commit();
		} catch (Exception e) {
			// TODO: handle exception
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			try {
				if(ps!=null){
					ps.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	public static String getNextFileName(String curren){
		String nextFileName = "";
		ConnDataBase bean0 = new ConnDataBase();
		String sql = "";
		
		try {
			sql = "select to_char(to_date('"+curren+"','YYYYMMDDHH24')+1/24,'YYYYMMDDHH24') FILENAME from dual";
			List list = bean0.executeQuery(sql);
			if(list!=null && list.size()>0){
				Map map = (HashMap)list.get(0);
				nextFileName = map.get("FILENAME").toString();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nextFileName;
	}
	
	public static void main(String[] args){
		try {
			//downLoad();
			String filepath = "D:\\uploadfiles\\zdry\\20160531\\2016052716RY.TXT";
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filepath),Charset.forName("GBK")));
			
			String data = null;
			while((data = br.readLine())!=null)
			{
				System.out.println(data); 
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
