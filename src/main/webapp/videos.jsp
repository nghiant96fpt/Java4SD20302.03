<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</head>
</head>
<body>
	<div class="container">
		<a class="btn btn-primary mt-3 mb-3" href="${pageContext.request.contextPath}/editer/video-form">
			Thêm video
		</a>
		<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">ID</th>
		      <th scope="col">Tiêu đề</th>
		      <th scope="col">Ảnh</th>
		      <th scope="col">URL</th>
		      <th scope="col">Tác giả</th>
		      <th scope="col">Danh mục</th>
		      <th scope="col">Trạng thái</th>
		      <th scope="col">Hành động</th>
		    </tr>
		  </thead>
		  <tbody id="bodyTableVideo" data-context-path="${pageContext.request.contextPath}">
		  	
		  </tbody>
		</table>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
	<script>
	/* Khi khởi động trang lên cần load 2 API
		- User Info (hiển thị tên và avatar ở nav bar)
		- Video list (hiển thị danh sách video)
		- Muốn gọi trong cùng 1 func 
	*/
		/* async function getData(){
			try{
				const bodyTable = document.getElementById("bodyTableVideo");
				
				// const userInfo = await axios.get(bodyTable.getAttribute("data-context-path") + "/user-info");
				
				console.log("start call api");
				
				const data = await axios.get(bodyTable.getAttribute("data-context-path") + "/api/videos");
				
				console.log(data);
				
				const data1 = await axios.get(bodyTable.getAttribute("data-context-path") + "/api/videos");
				console.log(data1);
				
				const data2 = await axios.get(bodyTable.getAttribute("data-context-path") + "/api/videos");
				console.log(data2);
				
				// let html = "";
				
				// data.data.forEach(element => {
					//html += "<tr>" +
				      //"<th scope='col'>" + element.id + "</th>" +
				      //"<td>" + element.title + "</td>" +
				      //"<td>" + element.poster + "</td>" +
				      //"<td>" + element.url + "</td>" +
				      //"<td>" + element.authName + "</td>" +
				      //"<td>" + element.catName + "</td>" +
				      //"<td>" + element.status + "</td>" +
				      //"<td>Hành động</td>" +
		    		//"</tr>";
				//});
				
				// Duyệt qua tất cả phần tử trong mảng data.data
				// Chuyển giá trị của mảng hiện tại thành 1 mảng mới
				// data.data.map 
				const arrayHtml = data.data.map((element) => {
					return "<tr>" +
				      "<th scope='col'>" + element.id + "</th>" +
				      "<td>" + element.title + "</td>" +
				      "<td>" + element.poster + "</td>" +
				      "<td>" + element.url + "</td>" +
				      "<td>" + element.authName + "</td>" +
				      "<td>" + element.catName + "</td>" +
				      "<td>" + element.status + "</td>" +
				      "<td>Hành động</td>" +
		    		"</tr>";
				});
				bodyTable.innerHTML = arrayHtml.join("");
				
				console.log("end call api");
				
			}catch(error){
				console.log(error);
			}
		} */
		
		function getData(){
			const bodyTable = document.getElementById("bodyTableVideo");
			console.log("start call api");
			
			axios.get(bodyTable.getAttribute("data-context-path") + "/api/videos")
			.then((res)=>{
				console.log(res);
			})
			.catch((error)=>{
				console.log(error);
			})
			
			axios.get(bodyTable.getAttribute("data-context-path") + "/api/videos")
			.then((res)=>{
				console.log(res);
			})
			.catch((error)=>{
				console.log(error);
			})
			
			axios.get(bodyTable.getAttribute("data-context-path") + "/api/videos")
			.then((res)=>{
				console.log(res);
				const arrayHtml = res.data.map((element) => {
					return "<tr>" +
				      "<th scope='col'>" + element.id + "</th>" +
				      "<td>" + element.title + "</td>" +
				      "<td>" + element.poster + "</td>" +
				      "<td>" + element.url + "</td>" +
				      "<td>" + element.authName + "</td>" +
				      "<td>" + element.catName + "</td>" +
				      "<td>" + element.status + "</td>" +
				      "<td>Hành động</td>" +
		    		"</tr>";
				});
				bodyTable.innerHTML = arrayHtml.join("");
			})
			.catch((error)=>{
				console.log(error);
			})
			console.log("end call api");
		}
		
		getData();
		
/* 		function *getData(){
			yield
		} */
	</script>
</body>
</html>