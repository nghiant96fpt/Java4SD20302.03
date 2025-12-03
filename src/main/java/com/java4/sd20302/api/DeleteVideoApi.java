package com.java4.sd20302.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.java4.sd20302.response.VideoDeleteResponse;
import com.java4.sd20302.services.VideoServices;
import com.java4.sd20302.utils.Utils;

@WebServlet("/api/video-delete")
public class DeleteVideoApi extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Lấy video id từ client 
		VideoDeleteResponse response = new VideoDeleteResponse();
		Gson gson = new GsonBuilder().serializeNulls().create();

		String videoId = req.getParameter("videoId");
//		TH 1: không có id => lỗi
//		TH 2: có id nhưng không phải số => lỗi
//		TH 3: số <= 0 => lỗi

		if (videoId == null) {
			response.setMessage("Video ID bắt buộc phải có");
			response.setStatus(false);
			resp.getWriter().print(gson.toJson(response));
			return;
		}

		try {
			int id = Integer.parseInt(videoId);

			if (id <= 0) {
				response.setMessage("Video ID phải lớn hơn 0");
				response.setStatus(false);
				resp.getWriter().print(gson.toJson(response));
				return;
			}

		} catch (Exception e) {
			response.setMessage("Video ID phải là số");
			response.setStatus(false);
			resp.getWriter().print(gson.toJson(response));
			return;
		}

		String userId = Utils.getCookieValue(Utils.COOKIE_KEY_USER_ID, req);
//		User ID kiểm tra các TH như video id

		if (userId == null) {
			response.setMessage("User ID bắt buộc phải có");
			response.setStatus(false);
			resp.getWriter().print(gson.toJson(response));
			return;
		}

		try {
			int id = Integer.parseInt(userId);

			if (id <= 0) {
				response.setMessage("User ID phải lớn hơn 0");
				response.setStatus(false);
				resp.getWriter().print(gson.toJson(response));
				return;
			}

		} catch (Exception e) {
			response.setMessage("User ID phải là số");
			response.setStatus(false);
			resp.getWriter().print(gson.toJson(response));
			return;
		}

		String errDelete = VideoServices.deleteVideo(Integer.parseInt(videoId), Integer.parseInt(userId));
		if (errDelete != null) {
			response.setMessage("Có lỗi trong quá trình xoá video");
			response.setStatus(false);
			resp.getWriter().print(gson.toJson(response));
			return;
		}

		response.setMessage("Xoá video thành công");
		response.setStatus(true);
		resp.getWriter().print(gson.toJson(response));
	}
}
