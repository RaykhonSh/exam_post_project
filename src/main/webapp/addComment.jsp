<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Add comment</title>
    <style>
        .btn-darkred {
            background-color: darkred;
            color: white;
            transition: background-color 0.3s ease;
        }
        .btn-darkred:hover {
            background-color: #a62e2b; /* Yengilroq qizil rang */
        }
    </style>
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow-lg">
                <div class="card-header bg-dark text-white">
                    <h5>Add Comment</h5>
                </div>
                <div class="card-body">
                    <%
                        int postId = Integer.parseInt(request.getParameter("postId"));
                    %>
                    <form action="/addComment" method="post">
                        <input type="hidden" name="postId" value="<%=postId%>">
                        <div class="form-group">
                            <input type="text" name="comment" class="form-control border-dark" placeholder="Comment">
                        </div>
                        <button class="btn btn-darkred text-white btn-block" >Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
