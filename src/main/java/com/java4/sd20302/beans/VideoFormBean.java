package com.java4.sd20302.beans;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Part;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Tạo các biến để nhận giá trị từ form
// Thực hiện kiểm tra các giá trị từ form nếu có 
// - Tiêu đề không được bỏ rỗng isBlank 
// - Mô tả phải có tối thiểu 10 từ
// - URL phải đúng định dạng link embed từ youtube 
// - Hình ảnh bắt buộc phải có và không được lớn hơn 20MB
// - Danh mục bắt buộc chọn
// - Trạng thái bắt buộc chọn

// - **Chuẩn hoá dữ liệu tiêu đề và mô tả để xử lý đồng nhất dữ liệu
// khi insert vào db
// "     Tiêu      đề  lỚp    sd20302      "
// => "Tiêu đề lỚp sd20302"

// Có thể xử lý ở Getter hoặc Setter đều được (Chỉ thực hiện 1 trong 2 cách)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VideoFormBean {
	private String title;
	private String desc;
	private String url;
	private Part image;
	private int category;
	private int status;

	public Map<String, String> getErrors() {
		Map<String, String> map = new HashMap<String, String>();

		return map;
	}

	public void setTitle(String title) {
//		Xử lý tiêu đề khi user nhập vào input 
//		Tách chuỗi => Array => Join Array => Chuỗi

//		String titleTrim = title.trim();
//		String[] arrTitle = titleTrim.split("//s+");
//
//		this.title = String.join(" ", arrTitle);

		this.title = String.join(" ", title.trim().split("\\s+"));
	}

	public void setDesc(String desc) {
		this.desc = String.join(" ", desc.trim().split("\\s+"));
	}

}
// 1 => Hiển thị
// 2 => Ẩn
// 3 => Từ chối
// 4 => Duyệt 
// 1 và 2 editer được sửa
// 3 và 4 thì admin được cập nhật 

// Nếu trạng thái đang là 1
// - Editer (Chờ duyệt)
// - Admin (Chờ duyệt)
// - User hoặc trang chủ (Không thấy bài viết)

// Nếu trạng thái là 2
// - Editer (Bài viết ẩn)
// - Admin || User || Trang chủ (Không thấy bài viết)

// Nếu trạng thái là 3
// - Editer (Từ chối bài viết)
// - Admin (Danh sách bài viết từ chối)
// - User || Trang chủ (Không thấy bài viết)

// Nếu trạng thái là 4
// - Editer (Đã duyệt)
// - Admin (Đã duyệt)
// - User || Trang chủ (thấy bài viết )
