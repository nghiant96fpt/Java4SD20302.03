package com.java4.sd20302.controllers;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.java4.sd20302.beans.VideoFormBean;
import com.java4.sd20302.entities.Category;
import com.java4.sd20302.entities.Video;
import com.java4.sd20302.services.CategoryServices;
import com.java4.sd20302.services.VideoServices;
import com.java4.sd20302.utils.Utils;

@MultipartConfig
@WebServlet("/editer/video-form")
public class VideoFormController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		lấy danh sách danh mục ở db hiển thị lên giao diện 

		List<Category> categories = CategoryServices.getAll();
		req.setAttribute("categories", categories);

		req.getRequestDispatcher("/video-form.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Category> categories = CategoryServices.getAll();
		req.setAttribute("categories", categories);

		try {

			VideoFormBean bean = new VideoFormBean();

			BeanUtils.populate(bean, req.getParameterMap());

			bean.setImage(req.getPart("image"));

			req.setAttribute("bean", bean);

			if (bean.getErrors().isEmpty()) {
//				Lưu ảnh vào project => Viết trong Utils 

				String imageName = Utils.uploadImage(req, bean.getImage());

				if (imageName != null) {
//					Convert Bean sang Entity
					Video video = new Video();
					video.setTitle(bean.getTitle());
					video.setDesc(bean.getDesc());
					video.setPoster(imageName);
					video.setUrl(bean.getUrl());
					Calendar calendar = Calendar.getInstance();
//					yyyy-[m]m-[d]d
					String date = String.format("%s-%s-%s", calendar.get(Calendar.YEAR),
							calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
					video.setCreateAt(Date.valueOf(date));
					video.setViewCount(0);
					video.setStatus(bean.getStatus());

					String errorSave = VideoServices.addVideo(video,
							Integer.parseInt(Utils.getCookieValue(Utils.COOKIE_KEY_USER_ID, req)), bean.getCategory());

					if (errorSave != null) {
						System.out.println(errorSave);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		req.getRequestDispatcher("/video-form.jsp").forward(req, resp);
	}
}
// Restful API => JSON => Java 6 => >90% các hệ thống
// SOAP API => XML