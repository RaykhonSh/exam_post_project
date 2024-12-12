<%@ page import="uz.pdp.exam_post_project.repo.PostRepo" %>
<%@ page import="uz.pdp.exam_post_project.entity.Post" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="uz.pdp.exam_post_project.repo.UserRoleRepo" %>
<%@ page import="uz.pdp.exam_post_project.entity.Users" %>
<%@ page import="uz.pdp.exam_post_project.entity.UserRole" %>
<%@ page import="uz.pdp.exam_post_project.servlet.LoginServlet" %>
<%@ page import="java.util.Optional" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 09/12/24
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Post</title>
</head>
<body>
<%
    List<Post> allPosts = PostRepo.getAllPosts();
    allPosts.sort((o1, o2) -> o2.getCreatedAt().compareTo(o1.getCreatedAt()));

    Optional<Users> optionalUsers=  LoginServlet.currentUser(request);
    if (optionalUsers.isEmpty()){
        response.sendRedirect("/login.jsp");
        return;
    }

    Users users = optionalUsers.get();
    request.getSession().setAttribute("currentUser",users);

    List<UserRole> userRoles=UserRole.findAll();
    UserRole userRole1 = userRoles.stream().filter(userRole -> userRole.getRole().getRole().equals("ADMIN")).findFirst().orElseThrow();
%>
<div class="container mt-5">
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-header d-flex justify-content-between align-items-center bg-secondary text-light">
                    <h3 class="mb-0">Posts</h3>
                    <div>
                        <%
                            if (users.getId().equals(userRole1.getUser().getId())){
                                %>
                        <form action="addPost.jsp" class="d-inline">
                            <button class="btn btn-outline-light btn-sm">Add Post</button>
                        </form>
                        <%
                            }
                        %>
                        <form action="login.jsp" class="d-inline">
                            <button class="btn btn-outline-light btn-sm">Admin</button>
                        </form>
                    </div>
                </div>
                <div class="card-body">
                    <% if (allPosts.isEmpty()) { %>
                    <div class="text-center text-muted">
                        <h5>No posts available</h5>
                    </div>
                    <% } else { %>
                    <% for (Post post : allPosts) { %>
                    <div class="row mb-4 p-3 border rounded bg-white shadow-sm">
                        <div class="col-md-4">
                            <img src="/file?id=<%=post.getId()%>" alt="Image not found" class="img-fluid rounded" style="height: 200px; object-fit: cover;">
                        </div>
                        <div class="col-md-8">
                            <h4 class="text-dark"><%=post.getTitle()%></h4>
                            <p class="text-muted"><%=post.getDescription()%></p>
                            <div class="d-flex justify-content-end">
                                <form action="comments.jsp" method="post" class="d-inline">
                                    <input type="hidden" name="userId" value="<%=users.getId()%>">
                                    <input type="hidden" name="postId" value="<%=post.getId()%>">
                                    <button class="btn btn-outline-dark btn-sm">Comments 💬</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <% } %>
                    <% } %>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
