package com.java4.sd20302.response;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VideoResponse {
	private int id;
	private String title;
	private String desc;
	private String poster;
	private String url;
	private Date createAt;
	private int viewCount;
	private String status;
	private String authName;
	private String catName;

	public void setStatus(int status) {
		switch (status) {
		case 1:
			this.status = "Chờ duyệt";
			break;
		case 2:
			this.status = "Ẩn";
			break;
		case 3:
			this.status = "Từ chối";
			break;
		case 4:
			this.status = "Đã duyệt";
			break;
		default:
			this.status = "Chờ duyệt";
			break;
		}
	}
}

//class UserResponse {
//	int id;
//	String name;
//	String phone;
//	int role;
//}