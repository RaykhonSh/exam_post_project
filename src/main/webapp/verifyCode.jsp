<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Enter Code</title>
    <style>
        .code-input {
            width: 50px;
            height: 50px;
            font-size: 1.5rem;
            text-align: center;
            margin-right: 10px;
            border: 1px solid #ced4da;
            border-radius: 5px;
            -moz-appearance: textfield; /* Firefox */
        }
        .code-input::-webkit-outer-spin-button,
        .code-input::-webkit-inner-spin-button {
            -webkit-appearance: none; /* Chrome, Safari, Edge */
            margin: 0;
        }
        .code-input:focus {
            border-color: #80bdff;
            outline: none;
            box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
        }
    </style>
</head>
<body>
<%
    String code = request.getParameter("code");
%>
<div class="container mt-5">
    <div class="row">
        <div class="col-6 offset-3">
            <div class="card">
                <div class="card-header text-center bg-light font-weight-bold">
                    Enter Code
                </div>
                <div class="card-body text-center">
                    <p>Enter code from your email</p>
                    <form action="/verifyCode?code=<%=code%>" method="post">
                        <div class="d-flex justify-content-center">
                            <input type="text" maxlength="1" class="code-input" name="code1" id="input1"
                                   oninput="moveToNext(1)" onkeydown="moveToPrev(event, 1)" required>
                            <input type="text" maxlength="1" class="code-input" name="code2" id="input2"
                                   oninput="moveToNext(2)" onkeydown="moveToPrev(event, 2)" required>
                            <input type="text" maxlength="1" class="code-input" name="code3" id="input3"
                                   oninput="moveToNext(3)" onkeydown="moveToPrev(event, 3)" required>
                            <input type="text" maxlength="1" class="code-input" name="code4" id="input4"
                                   oninput="moveToNext(4)" onkeydown="moveToPrev(event, 4)" required>
                        </div>
                        <button type="submit" class="btn btn-danger btn-block mt-3">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function moveToNext(current) {
        const input = document.getElementById('input' + current);
        if (/^\d$/.test(input.value) && current < 4) { // Faqat raqam kiritishga ruxsat
            document.getElementById('input' + (current + 1)).focus();
        } else if (!/^\d$/.test(input.value)) { // Noto'g'ri kiritilsa
            input.value = ''; // Tozalash
        }
    }

    function moveToPrev(event, current) {
        if (event.key === "Backspace" && current > 1 && !document.getElementById('input' + current).value) {
            document.getElementById('input' + (current - 1)).focus();
        }
    }
</script>
</body>
</html>
