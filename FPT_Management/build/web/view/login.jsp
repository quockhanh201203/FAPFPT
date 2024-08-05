<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>FPT Software</title>
        <link rel="shortcut icon" type="image/x-icon" href="img/logoweb.png">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/login.css">
    </head>

    <body>
        <div class="left">
            <div class="draggable-box" id="draggable">
                <img src="img/logo.png" alt="FPT Software">
                <form id="loginForm" action="login" method="post">
                    <h7 id="errMsg" style="color: red; margin-bottom: 10px"></h7>
                    <div class="form-group">
                        <input type="text" class="form-control" name="username" id="username" placeholder="Enter username" required="">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="password" id="password" placeholder="Enter password" required="">
                    </div>

                    <button type="button" id="loginButton" class="btn btn-primary btn-block mt-4">Login</button>
                    <div class="forgot-password">
                        <a href="forgotpassword">Forgot Password</a>
                    </div>
                </form>
            </div>
        </div>
        <div class="right">
            <img src="img/loginBackground.png" alt="">
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>

        <script>
            $(document).ready(function () {
                $('#loginButton').click(function () {
                    var username = $('#username').val();
                    var password = $('#password').val();

                    $.ajax({
                        type: 'POST',
                        url: 'login',
                        data: {
                            username: username,
                            password: password
                        },
                        success: function (response) {
                            if (response.trim() === 'success') {
                                window.location.href = 'home';
                            } else if (response.trim() === 'successAdmin') {
                                window.location.href = 'admin?cp=1';
                            } else if (response.trim() === 'errorLogin') {
                                $('#errMsg').text('Account is banned');
                            } else {
                                $('#errMsg').text('Wrong username or password');
                            }
                        },
                        error: function () {
                            $('#errMsg').text('Error occurred during login');
                        }
                    });
                });
            });

            $('#username, #password').on('input', function () {
                $('#errMsg').text(''); // Xóa nội dung của thông báo lỗi
            });
        </script>
    </body>

</html>
