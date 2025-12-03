package com.java4.sd20302.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.java4.sd20302.entities.Video;
import com.java4.sd20302.response.VideoResponse;
import com.java4.sd20302.services.VideoServices;
import com.java4.sd20302.utils.Utils;

@WebServlet("/api/videos")
public class VideoListApi extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json");

		String userId = Utils.getCookieValue(Utils.COOKIE_KEY_USER_ID, req);

		List<Video> videos = VideoServices.getVideosByUserId(Integer.parseInt(userId));

//		danh sách video từ db 
//		Làm sao để chuyển danh sách video từ db sang danh sách video response????

		List<VideoResponse> videoResponseList = new ArrayList<VideoResponse>();

		for (Video video : videos) {
			VideoResponse videoResponse = new VideoResponse();
			videoResponse.setId(video.getId());
			videoResponse.setTitle(video.getTitle());
			videoResponse.setDesc(video.getDesc());
			videoResponse.setPoster(video.getPoster());
			videoResponse.setUrl(video.getUrl());
			videoResponse.setCreateAt(video.getCreateAt());
			videoResponse.setViewCount(video.getViewCount());
			videoResponse.setStatus(video.getStatus());
			videoResponse.setAuthName(video.getUser().getName());
			videoResponse.setCatName(video.getCategory().getName());

			videoResponseList.add(videoResponse);
		}

		Gson gson = new GsonBuilder().serializeNulls().create();

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		resp.getWriter().print(gson.toJson(videoResponseList));
	}
}
//{name: "Nguyen Van A", point: 10, subject: []}
// {key: value, key: value } => JSON Object

// [
//	{key: value, key: value},
//	{key: value, key: value},
//	] => JSON Array

// Bean Class => Xử lý dữ liệu ở form 
// Entity Class => Xử lý dữ liệu ở DB 
// Response/DTO Class => Trả dữ liệu về cho client ở dạng json

// AngularJS Ngưng từ 2019 => K18 
// Angular 