package com.amaker.entity;

/**
 * 
 * @author 郭宏志
 * 实体类对应数据库uploadfiletbl表
 */
public class UploadFile {

	private int id;			   // 编号
	private String uploadTime; // 上传时间
	private String fileDesc;   // 文件描述
	private String filePath;   // 文件保存路径

	public String getFileDesc() {
		return fileDesc;
	}

	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

}
