<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" type="image/x-icon" href="img/logoweb.png">
        <title>FPT Software</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <style>
            .card {
                /* max-width: 800px; */
                max-width: 60vw;
                margin: 20px auto;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            .profile-img {
                width: 120px;
                height: 120px;
                border-radius: 50%;
                object-fit: cover;
            }
        </style>
    </head>

    <body>
        <%@include file="/components/headerAndSideBar.jsp"%>
        <div style="position: relative; z-index: -3">
            <div style="position: absolute; left: 25vw; top: 10vh;">
                <c:forEach items="${listacc}" var="acc">
                    <div class="card">
                        <div class="card-body">
                            <div class="row align-items-center">
                                <div class="col-auto">
                                    <img src="img/changePassBackground.jpg" alt="Profile Image" class="profile-img">
                                </div>
                                <div class="col text-right">
                                    <h5 class="card-title mb-0">${acc.getUserName()}</h5>
                                    <p class="card-text mb-0">${acc.getStaff().getPhoneNumber()}</p>
                                    <p class="card-text">This is a stranger! Let's create connection together.</p>
                                    <div class="mt-3">
                                        <form action="otherprofile" method="post">
                                            <input type="hidden" name="accId" value="${acc.getAccountId()}"/>
                                            <button class="btn btn-primary">Profile</button>
                                            <a href="#" class="btn btn-primary">Chat</a>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>


        <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0/js/bootstrap.bundle.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
    </body>

</html>
