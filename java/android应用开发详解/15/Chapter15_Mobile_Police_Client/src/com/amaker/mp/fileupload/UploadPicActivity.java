package com.amaker.mp.fileupload;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.amaker.mp.R;
import com.amaker.mp.R.id;
import com.amaker.mp.R.layout;
import com.amaker.util.HttpUtil;

public class UploadPicActivity extends Activity {

	private Button submitBtn, selectBtn;
	private EditText myEditText;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.upload_pic);

		myEditText = (EditText) findViewById(R.id.picPathEditText);

		submitBtn = (Button) findViewById(R.id.submitPicButton);
		selectBtn = (Button) findViewById(R.id.selectButton);

		selectBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(UploadPicActivity.this,FileExplorerActivity.class);
				startActivityForResult(intent, 1);
			}
		});
		
		submitBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String fileName = myEditText.getText().toString();
				if(validate(fileName)){
					showAlert("无效图片文件！");
				}else{
					if(uploadFile(fileName).equals("1")){
						showAlert("上传成功！");
					}else{
						showAlert("上传失败！");
					}
				}
			}
		});
	}
	
	
	private String uploadFile(String fileName){
		String urlStr = HttpUtil.BASE_URL+"servlet/UploadPicServlet";
		String newName="image.jpg";
		String end = "\r\n";
	    String twoHyphens = "--";
	    String boundary = "*****";
	    try
	    {
	      URL url =new URL(urlStr);
	      HttpURLConnection con=(HttpURLConnection)url.openConnection();
	      /* 允许Input、Output，不使用Cache */
	      con.setDoInput(true);
	      con.setDoOutput(true);
	      con.setUseCaches(false);
	      /* 设置传送的method=POST */
	      con.setRequestMethod("POST");
	      /* setRequestProperty */
	      con.setRequestProperty("Connection", "Keep-Alive");
	      con.setRequestProperty("Charset", "UTF-8");
	      con.setRequestProperty("Content-Type",
	                         "multipart/form-data;boundary="+boundary);
	      
	      
	      /* 设置DataOutputStream */
	      DataOutputStream ds = 
	        new DataOutputStream(con.getOutputStream());
	      ds.writeBytes(twoHyphens + boundary + end);
	      ds.writeBytes("Content-Disposition: form-data; " +
	                    "name=\"file1\";filename=\"" +
	                    newName +"\"" + end);
	      ds.writeBytes(end);   

	      /* 取得文件的FileInputStream */
	      FileInputStream fStream = new FileInputStream(fileName);
	      /* 设置每次写入1024bytes */
	      int bufferSize = 1024;
	      byte[] buffer = new byte[bufferSize];

	      int length = -1;
	      /* 从文件读取数据至缓冲区 */
	      while((length = fStream.read(buffer)) != -1)
	      {
	        /* 将资料写入DataOutputStream中 */
	        ds.write(buffer, 0, length);
	      }
	      ds.writeBytes(end);
	      ds.writeBytes(twoHyphens + boundary + twoHyphens + end);

	      /* close streams */
	      fStream.close();
	      ds.flush();

	      /* 取得Response内容 */
	      InputStream is = con.getInputStream();
	      int ch;
	      StringBuffer b =new StringBuffer();
	      while( ( ch = is.read() ) != -1 )
	      {
	        b.append( (char)ch );
	      }
	    
	      ds.close();
	      
	      return b.toString().trim();
	    }
	    catch(Exception e)
	    {
	     // showAlert(""+e);
	      return ""+e;
	    }
	    
	   // return null;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode==1){
			String path = data.getStringExtra("filePath");
			myEditText.setText(path);
		}
	}
	
	private boolean validate(String fileName){
		int idx = fileName.indexOf(".");
		String subfix = fileName.substring(idx);
		if(fileName.equals(""))return false;
		if(!subfix.equals("jpg")||!subfix.equals("png")||!subfix.equals("jpeg")){
			return false;
		}else{
			return true;
		}
	}
	
	private void showAlert(String msg){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(msg)
		       .setCancelable(false)
		       .setPositiveButton("确定", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
	}

}