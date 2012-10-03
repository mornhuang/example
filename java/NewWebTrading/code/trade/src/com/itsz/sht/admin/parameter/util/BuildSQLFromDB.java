package com.itsz.sht.admin.parameter.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.itsz.sht.admin.service.ParameterMgrService;
import com.taifook.adminportal.model.CsParameter;

public class BuildSQLFromDB {

	private void build(){
		ParameterMgrService service=ParameterMgrService.getInstance();		
		List paramList=service.findByAllParameter();
		BufferedWriter bfw=null;
		if(paramList.size()>0){
			File paramFile=new File("conf/production/parameter.sql");
			if(paramFile.exists()){
				paramFile.delete();
			}
			try{
				bfw=new BufferedWriter(new FileWriter(paramFile,false));
				StringBuffer sql=new StringBuffer("");
				String updateTime=new SimpleDateFormat("yyyy-mm-dd").format(new Date());
				Iterator it=paramList.iterator();
				int count=0;
				sql.append("SET ESCAPE \\\r\n");
				while(it.hasNext()){
					CsParameter param=(CsParameter)it.next();
					if(param.getKey()==null){
						param.setKey("");
					}
					if(param.getValue()==null){
						param.setValue("");
					}
					if(param.getDescription()==null){
						param.setDescription("");
					}
					
					if(param.getDataType()==null){
						param.setDataType("");
					}
					sql
					.append("INSERT INTO CS_PARAMETER (key_pk, value, classid, readonly, description, update_Time, datatype) VALUES ("
							+ "'"
							+ param.getKey().trim().replaceAll("&","\\\\&").replaceAll("'","''")
							+ "', "
							+ "'"
							+ param.getValue().trim().replaceAll("&","\\\\&").replaceAll("'","''")
							+ "', "
							+ "'"
							+ param.getClassid()
							+ "', "
							+ 0
							+ ", "
							+ "'"
							+ param.getDescription().trim().replaceAll("&","\\\\&").replaceAll("'","''")
							+ "', "
							+ "sysdate" 
							+", "
							+ "'"
							+param.getDataType().trim().replaceAll("&","\\\\&").replaceAll("'","''")
							+ "'"
							+");\r\n");
					
					count++;
				
					if(count % 10==0){
						//sql.append("commit;\r\n");
						bfw.write(sql.toString());
						bfw.flush();
//						System.out.println(sql);
						sql=new StringBuffer("");
					}									
				}
				
				if(!sql.toString().equals("")){
					//sql.append("commit;\r\n");
					bfw.write(sql.toString());
					bfw.flush();
					System.out.println(sql);
				}				
				System.out.println("build sql success from database, total count: "+count);
				
			}catch(Exception e){
//				e.printStackTrace();
				System.out.println("build sql fail from database, exception: "+e.getMessage());
			}finally{
				if(bfw!=null){
					try{
					bfw.close();
					}catch(Exception e){
						System.out.println("build sql exception for closeing,"+e.getMessage());
					}
				}
			}
		}else{
			System.out.println("none paramater in database!");
		}
		
	}
	
	public static void main(String[] args) {
		try {
			BuildSQLFromDB a = new BuildSQLFromDB();
			a.build();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
