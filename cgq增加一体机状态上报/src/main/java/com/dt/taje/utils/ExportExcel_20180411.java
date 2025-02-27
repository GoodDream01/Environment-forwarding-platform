package com.dt.taje.utils;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dt.taje.utils.ui.JSON;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExportExcel_20180411 {
	
	public static void Export(HttpServletRequest request, HttpServletResponse response) {

		try {
			PrintWriter out = response.getWriter();
			String fname = request.getParameter("filename");
			response.reset();// 清空输出流

			response.setCharacterEncoding("UTF-8");// 设置相应内容的编码格式
			String zfname = java.net.URLEncoder.encode(fname, "UTF-8");
			response.setHeader("Content-Disposition",
					"attachment;filename=" + new String(zfname.getBytes("UTF-8"), "GBK") + ".xls");
			response.setContentType("application/ms-excel");// 定义输出类型
			
			String columnsJson = request.getParameter("columns");
			String rowsJson = request.getParameter("rows");
			ArrayList rows = (ArrayList) JSON.Decode(rowsJson);
			ArrayList columns = (ArrayList) JSON.Decode(columnsJson);
			

			// 获得开始时间
			// long start = System.currentTimeMillis();
			
			String table = ExportTable(rows, columns,fname);
			System.out.println(table);
			out.write(table);
			
			// long end = System.currentTimeMillis();
			// System.out.println("----完成该操作共用的时间是:"+(end-start)/1000);
		} catch (Exception e) {
			// System.out.println("---出现异常---");
			e.printStackTrace();
		}
	}
	
    public static String ExportTable(ArrayList rows, ArrayList columns,String fname)
    {

    	ArrayList columnsBottom = getColumnsBottom(columns);
        ArrayList columnsTable = getColumnsTable(columns);

        StringBuilder sb = new StringBuilder();
        //data = ds.DataSetName + "\n";


        //data += tb.TableName + "\n";
        sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
        sb.append("<table cellspacing=\"0\" cellpadding=\"0\" rules=\"all\" border=\"1\" style=\"font-size:8pt;\">");
        sb.append("<caption align=\"center\" ><span style=\"font-weight:bold;\">"+fname+"</span></caption>");
        //写出列名
        if(columnsTable!=null && columnsTable.size()>0){
	        for (int i = 0; i < columnsTable.size(); i++)
	        {
	            ArrayList columnsRow = (ArrayList)columnsTable.get(i);
	            sb.append("<tr style=\"font-weight: bold; word-wrap:break-word;\">");
	            if(columnsRow!=null && columnsRow.size()>0){
		            for (int j=0;j<columnsRow.size();j++)
		            {
		            	HashMap column = (HashMap)columnsRow.get(j);
		            	String colspan = column.get("colspan").toString();
		            	String rowspan = column.get("rowspan").toString();
		            	String header = "";
		            	String width = "";
		            	if(column.get("header")!=null){
		            		header = column.get("header").toString();
		            	}
		            	if(column.get("width")!=null){
		            		width = column.get("width").toString();
		            		if(!width.equals("")){
		            			width="width=\""+width+"\"";
		            		}
		            	}
		            	
		                sb.append("<td "+width+"  colspan=" + colspan + " rowspan=" + rowspan + " align=\"center\">" + header + "</td>");
		            }
	            }
	            sb.append("</tr>");
	        }
        }
        //写出数据
        if(rows!=null && rows.size()>0){
	        for (int i=0;i<rows.size();i++)
	        {
	        	HashMap row = (HashMap)rows.get(i);
	            sb.append("<tr style=\"word-wrap:break-word;\">");
	            if(columnsBottom!=null && columnsBottom.size()>0){
		            for (int j=0;j<columnsBottom.size();j++)
		            {
		            	HashMap column = (HashMap)columnsBottom.get(j);
		            	String cellId = column.get("cellId").toString();
		            	String colspan = "1";
		            	String rowspan = "1";
		            	String style = "";
		                String value = "";
		                if(row.get(cellId)!=null)
		                {
		                	value = row.get(cellId).toString();
/*			            	colspan = row.get("colspan").toString();
			            	rowspan = row.get("rowspan").toString();*/
		                	
		                	HashMap spanObj = (HashMap)row.get(cellId+"_spanObj");
		                	colspan = spanObj.get("colspan").toString();
			            	rowspan = spanObj.get("rowspan").toString();
			            	style = spanObj.get("style").toString();
			            	if(style.indexOf("display:none")!=-1||style.indexOf("display: none")!=-1){
			            		continue;
			            	}
		                	
		                }


		            	
		                sb.append("<td  colspan=" + colspan + " rowspan=" + rowspan + " style='" + style + "' >" + value + "</td>");
		            }
	            }
	            sb.append("</tr>"); 
	        }
        }
        sb.append("</table>");


        return sb.toString();
    }
    
    public static ArrayList getColumnsBottom(ArrayList columns)
    {
        ArrayList columnsBottom = new ArrayList();

        for (int i = 0; i < columns.size(); i++)
        {
            HashMap column = (HashMap)columns.get(i);

            if (column.get("columns") != null)
            {
                ArrayList childColumns = (ArrayList)column.get("columns");
                columnsBottom.addAll(getColumnsBottom(childColumns));
            }
            else
            {
                columnsBottom.add(column);
            }

        }
        return columnsBottom;
    }
    
    public static ArrayList getColumnsTable(ArrayList columns)
    {
        ArrayList table = new ArrayList();

        getColumnsRows(columns, 0, table);

        return table;

    }
    
    public static void getColumnsRows(ArrayList columns, int level, ArrayList table)
    {
        ArrayList row = null;
        if (table.size() > level)
        {
            row = (ArrayList)table.get(level);
        }
        else
        {
            row = new ArrayList();
            table.add(row);
        }

        if(columns!=null && columns.size()>0){
	        for (int i = 0; i < columns.size(); i++)
	        {
	
	            HashMap column = (HashMap)columns.get(i);
	            ArrayList childColumns = (ArrayList)column.get("columns");
	
	            row.add(column);
	
	            if (childColumns != null)
	            {
	
	                getColumnsRows(childColumns, level + 1, table);
	            }
	
	        }
        }
    }
}
