<%@ page import="uz.pdp.exam_post_project.repo.PostRepo" %>
<%@ page import="uz.pdp.exam_post_project.entity.Post" %>
<%@ page import="java.util.List" %>
<%@ page import="uz.pdp.exam_post_project.repo.CommentRepo" %>
<%@ page import="uz.pdp.exam_post_project.entity.Comment" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 10/12/24
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <title>Comments</title>
</head>
<body>
<%
  int postId = Integer.parseInt(request.getParameter("postId"));
  List<Post> allPosts = PostRepo.getAllPosts();
  Post post1 = allPosts.stream().filter(post -> post.getId().equals(postId)).findFirst().orElseThrow();
  List<Comment> allComments = CommentRepo.getAllComments();
  allComments.sort((o1, o2) -> o2.getCreatedAt().compareTo(o1.getCreatedAt()));
  List<Comment> postComments = allComments.stream().filter(comment -> comment.getPost().getId().equals(postId)).toList();
%>
<div class="container mt-5">
  <div class="row">
    <div class="col-8 offset-2">
      <div class="card shadow-sm">
        <div class="card-header d-flex justify-content-between align-items-center bg-secondary text-light">
          <h3 class="mb-0">Comments</h3>
          <div>
            <form action="addComment.jsp" class="d-inline">
              <input type="hidden" name="postId" value="<%=post1.getId()%>">
              <button class="btn btn-sm btn-warning">Add Comment</button>
            </form>
            <a href="post.jsp" class="btn btn-sm btn-outline-light">Back to Posts</a>
          </div>
        </div>
        <div class="card-body">
          <div class="mb-4">
            <img src="/file?id=<%=post1.getId()%>" alt="Rasm topilmadi" class="img-thumbnail" width="100" height="100">
            <h4 class="mt-3"><%=post1.getTitle()%></h4>
            <p class="text-muted"><%=post1.getDescription()%></p>
          </div>
          <% if (postComments.isEmpty()) { %>
          <p class="text-muted text-center">No comments available.</p>
          <% } else { %>
          <ul class="list-group">
            <% for (Comment postComment : postComments) { %>
            <li class="list-group-item">
              <h6 class="font-weight-bold mb-1"><%=postComment.getUsers().getEmail()%></h6>
              <p class="mb-1"><%=postComment.getComment()%></p>
              <small class="text-muted">Posted on: <%=postComment.getCreatedAt()%></small>
            </li>
            <% } %>
          </ul>
          <% } %>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
