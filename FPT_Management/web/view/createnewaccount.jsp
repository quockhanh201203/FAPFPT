<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FPT Software</title>
        <link rel="shortcut icon" type="image/x-icon" href="img/logoweb.png">
        <!--<link rel="shortcut icon" type="image/x-icon" href="img/favicon.jpg">-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <!--<link rel="stylesheet" href="templates/css/fontawesome-all.min.css">-->
        <link rel="stylesheet" href="css/admin.css"/>
        <script src="https://kit.fontawesome.com/9a14d14ca3.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-2 side-bar">
                    <div class="top-fixed">
                        <div class="content"><i class="fas fa-home" style="font-size: 20px;"></i><a href="admin?cp=1">Home</a></div>
                    </div>
                    <div class="bottom bottom-fixed">
                        <div class="bottom bottom-fixed">
                            <a href="logout"><i class="fas fa-sign-out-alt" style="font-size: 22px; padding-top: 5px"> Logout</i></a>
                        </div>
                    </div>

                </div>
                <div class="col-lg-4" style="margin: 0px auto; padding-left: 50px">
                    <h1 style="margin: 25px 0px">Create An Account</h1>
                    <form action="addnewaccountbyadmin" method="POST">
                        <div class="form-info">
                            <div class="form-group" style="margin-right: 18px">
                                <label for="name" class="input-label" >First Name</label>
                                <input type="text" id="firstname" class="form-control" name="firstname" placeholder="" required="required" value="${fname}">
                            </div>
                            <div class="form-group" style="margin-left: 20px;">
                                <label for="name" class="input-label">Last Name</label>
                                <input type="text" id="lastname" class="form-control" name="lastname" placeholder="" required="required" value="${lname}">
                            </div>
                        </div>
                        <div class="form-group" style="margin-bottom: 0px" >
                            <label for="name" class="input-label">Username</label>
                            <input type="text" id="username" class="form-control" name="username" placeholder="" required="required" value="${user}">
                            <span style="color: red; font-size: 15px; padding-left: 5px">${errorUsername}</span>
                        </div>
                        <div class="name-input-style"  style="margin-bottom: 0px">
                            <div class="form-group" style="margin-right: 18px; margin-bottom: 0px">
                                <label for="phone" class="input-label">Phone Number</label>
                                <input type="text" id="number" class="form-control" name="phone" 
                                       placeholder="" minlength="10" maxlength="10" pattern="[0-9]{10}" 
                                       title="Please enter a 10-digit phone number" value="${phone}">
                                <span style="color: red; font-size: 15px; padding-left: 5px">${errorPhone}</span>
                            </div>
                            <div class="form-group" style="margin-left: 20px; margin-bottom: 0px">
                                <label for="address" class="input-label">Address</label>
                                <input type="text" id="address" class="form-control" name="address" value="${address}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="department" class="input-label" style="margin-bottom: 5px">Department</label>
                            <select class="form-control form-control-lg" name="department">
                                <optgroup label="Tokyo">
                                    <c:forEach var="department" items="${departments}">
                                        <c:if test="${department.departmentLocation == 'Tokyo'}">
                                            <option value="${department.departmentId}" <c:if test="${department.departmentId == departmentId}">selected</c:if>>${department.departmentName}</option>
                                        </c:if>
                                    </c:forEach>
                                </optgroup>
                                <optgroup label="Osaka">
                                    <c:forEach var="department" items="${departments}">
                                        <c:if test="${department.departmentLocation == 'Osaka'}">
                                            <option value="${department.departmentId}" <c:if test="${department.departmentId == departmentId}">selected</c:if>>${department.departmentName}</option>
                                        </c:if>
                                    </c:forEach>
                                </optgroup>
                                <optgroup label="Kyoto">
                                    <c:forEach var="department" items="${departments}">
                                        <c:if test="${department.departmentLocation == 'Kyoto'}">
                                            <option value="${department.departmentId}" <c:if test="${department.departmentId == departmentId}">selected</c:if>>${department.departmentName}</option>
                                        </c:if>
                                    </c:forEach>
                                </optgroup>
                                <optgroup label="Sapporo">
                                    <c:forEach var="department" items="${departments}">
                                        <c:if test="${department.departmentLocation == 'Sapporo'}">
                                            <option value="${department.departmentId}" <c:if test="${department.departmentId == departmentId}">selected</c:if>>${department.departmentName}</option>
                                        </c:if>
                                    </c:forEach>
                                </optgroup>
                                <optgroup label="Nagoya">
                                    <c:forEach var="department" items="${departments}">
                                        <c:if test="${department.departmentLocation == 'Nagoya'}">
                                            <option value="${department.departmentId}" <c:if test="${department.departmentId == departmentId}">selected</c:if>>${department.departmentName}</option>
                                        </c:if>
                                    </c:forEach>
                                </optgroup>
                                <optgroup label="Fukuoka">
                                    <c:forEach var="department" items="${departments}">
                                        <c:if test="${department.departmentLocation == 'Fukuoka'}">
                                            <option value="${department.departmentId}" <c:if test="${department.departmentId == departmentId}">selected</c:if>>${department.departmentName}</option>
                                        </c:if>
                                    </c:forEach>
                                </optgroup>
                                <optgroup label="Kobe">
                                    <c:forEach var="department" items="${departments}">
                                        <c:if test="${department.departmentLocation == 'Kobe'}">
                                            <option value="${department.departmentId}" <c:if test="${department.departmentId == departmentId}">selected</c:if>>${department.departmentName}</option>
                                        </c:if>
                                    </c:forEach>
                                </optgroup>
                                <optgroup label="Sendai">
                                    <c:forEach var="department" items="${departments}">
                                        <c:if test="${department.departmentLocation == 'Sendai'}">
                                            <option value="${department.departmentId}" <c:if test="${department.departmentId == departmentId}">selected</c:if>>${department.departmentName}</option>
                                        </c:if>
                                    </c:forEach>
                                </optgroup>
                                <optgroup label="Naha">
                                    <c:forEach var="department" items="${departments}">
                                        <c:if test="${department.departmentLocation == 'Naha'}">
                                            <option value="${department.departmentId}" <c:if test="${department.departmentId == departmentId}">selected</c:if>>${department.departmentName}</option>
                                        </c:if>
                                    </c:forEach>
                                </optgroup>
                                <optgroup label="Shizuoka">
                                    <c:forEach var="department" items="${departments}">
                                        <c:if test="${department.departmentLocation == 'Shizuoka'}">
                                            <option value="${department.departmentId}" <c:if test="${department.departmentId == departmentId}">selected</c:if>>${department.departmentName}</option>
                                        </c:if>
                                    </c:forEach>
                                </optgroup>
                            </select>
                        </div>
                        <div class="form-group">
                            <p for="role" class="input-label">Role</p>
                            <label>
                                <input type="radio" name="flag" value="1" required ${flag == 1 ? "checked" : ""}> 
                                <span style="color: #979696;
                                      margin-bottom: 10px;
                                      font-weight: 500; margin-right: 28px; cursor: pointer">Admin</span>
                            </label>

                            <label>
                                <input type="radio" name="flag" value="0" required ${flag == 0 ? "checked" : ""}> 
                                <span style="color: #979696;
                                      margin-bottom: 10px;
                                      font-weight: 500; margin-right: 80px; cursor: pointer">User</span>
                            </label>
                        </div>
                        <div class="form-group">
                            <label for="password" class="input-label">Password</label>
                            <input id="password" type="password" class="form-control" name="password" placeholder="Enter password" required="required" oninput="validatePassword()">
                        </div>

                        <div class="form-group">
                            <label for="password" class="input-label">Confirm Password</label>
                            <input id="re-password" type="password" class="form-control" name="confirmPassword" placeholder="Confirm password" required="required" oninput="validatePassword()">
                        </div>

                        <div class="form-group">
                            <button type="submit" class="btn btn-primary" style="padding: 10px 40px; margin-top: 5px">Add account</button>
                        </div>
                    </form>
                </div>
                <div class="col-lg-6 side-bar" style="background-color: white"></div>
            </div>
        </div>

    </body>
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            var password = document.getElementById("password");
            var confirmPassword = document.getElementById("re-password");

            function validatePassword() {
                if (password.value !== confirmPassword.value) {
                    confirmPassword.setCustomValidity("Passwords do not match");
                } else {
                    confirmPassword.setCustomValidity('');
                }
            }

            password.addEventListener('change', validatePassword);
            confirmPassword.addEventListener('keyup', validatePassword);
        });
    </script>

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
    crossorigin="anonymous"></script>
</html>
