<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 10/12/24
  Time: 08:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <title>Add post</title>
</head>
<body>
<div class="container mt-5">
  <div class="row">
    <div class="col-8 offset-2">
      <div class="card shadow-sm">
        <div class="card-header d-flex justify-content-between align-items-center bg-secondary text-light">
          <h3 class="mb-0">Add Post</h3>
          <a href="post.jsp" class="btn btn-outline-light btn-sm">Back to Posts</a>
        </div>
        <div class="card-body">
          <form action="/addPost" method="post" enctype="multipart/form-data">
            <div class="form-group">
              <label for="title" class="text-dark font-weight-bold">Title</label>
              <input type="text" class="form-control" id="title" name="title" placeholder="Enter title" required>
            </div>
            <div class="form-group">
              <label for="description" class="text-dark font-weight-bold">Description</label>
              <textarea class="form-control" id="description" name="description" rows="3" placeholder="Enter description" required></textarea>
            </div>
            <div class="form-group">
              <label for="photo" class="text-dark font-weight-bold">Photo</label>
              <input type="file" class="form-control-file" id="photo" name="photo" required>
            </div>
            <div class="d-flex justify-content-end">
              <button type="submit" class="btn btn-sm text-white" style="background-color: darkred;">Submit</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
