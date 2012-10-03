package cn.itsz.newsim.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class InitCreateDBServlet extends HttpServlet {

	
	@Override
	public void init() throws ServletException {
		super.init();
		//创建数据库方法
		createDB();
	}



	public void createDB(){
		try{
//			System.out.println("create DB start!");
			CreateTableDao dao=new CreateTableDao();
			dao.CreatTable();
//			System.out.println("create DB success!");
		}catch(Exception e){
			e.printStackTrace();
//			System.out.println("create DB fail!");
		}
		
	}

}
