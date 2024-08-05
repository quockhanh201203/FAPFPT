<%-- 
    Document   : admin
    Created on : Jul 4, 2024, 9:18:03 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SCN | Admin</title>
        <link rel="shortcut icon" type="image/x-icon" href="img/favicon.jpg">
        <link rel="stylesheet" href="css/bootstrap.min.css">
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
                        <a href="logout"><i class="fas fa-sign-out-alt" style="font-size: 22px; padding-top: 5px"> Logout</i></a>
                    </div>

                </div>
                <div class="col-lg-10">
                    <div class="header">
                        <div>
                            <button class="btn btn-primary" style="margin-bottom: 5px"><a href="addnewaccountbyadmin" style="text-decoration: none; color: white">New User <i class="fas fa-user-friends"></i></a></button>
                        </div>
                        <div>
                            <form action="admin" method="GET">
                                <input type="hidden" name="cp" value="1">
                                <input class="search-input" type="text" name="key" placeholder="Search username or email..." />
                                <button type="submit" class="search-button" onclick="return validateSearch()">
                                    <i class="fas fa-search search-icon"></i>
                                </button>
                            </form>
                        </div> 

                    </div>

                    <c:if test="${empty accList}">
                        <div class="alert alert-info" style="margin-top: 5px; margin-bottom: 2px">No accounts were found with the words '${searchKey}'</div>
                        <div style="display: flex; justify-content: center">
                            <div><img src="img/notfoundaccount.jpg" alt="alt" height="400px"/></div>  
                        </div>
                        <h2 style="text-align: center; font-size: 30px;
                            font-weight: bold;
                            margin-bottom: 40px;">Opp ! Not found this account...</h2> 
                        <a href="admin" class="centered-button">Back to account list</a>
                    </c:if>

                    <c:if test="${not empty accList}">
                        <table id="accountTable" class="table align-middle mb-0 bg-white">
                            <thead class="bg-light">
                                <tr>
                                    <th>Account</th>
                                    <th>Role</th>
                                    <th>Department</th>
                                    <th>Status</th>
                                    <th>Operating Date</th>
                                    <th>Information</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${accList}" var="a">
                                    <tr>
                                        <td>
                                            <div class="d-flex align-items-center">
                                                <div>
                                                    <p class="fw-bold mb-1">${a.getUserName()}</p>
                                                    <p class="text-muted mb-0">${a.getStaff().getFirstName()} ${a.getStaff().getLastName()}</p>
                                                </div>
                                            </div>
                                        </td>
                                        <td>
                                            <c:if test="${a.flag == 1}">
                                                <button class="btn btn-warning">Admin</button>
                                            </c:if>
                                            <c:if test="${a.flag == 0}">
                                                <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#popup-updateRole-${a.accountId}">User</button>
                                            </c:if>
                                        </td>
                                        <td>
                                            <div class="d-flex align-items-center">
                                                <div>
                                                    <p class="fw-bold mb-1">${a.getStaff().getDepartment().getDepartmentName()}</p>
                                                    <p class="text-muted mb-0">${a.getStaff().getDepartment().getDepartmentLocation()}</p>
                                                </div>
                                            </div>
                                        </td>
                                        <td>
                                            <c:if test="${a.flag != 1}">
                                                <c:choose>
                                                    <c:when test="${a.ban == 1}">
                                                        <button class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#popup-updateStatus-${a.accountId}">Ban</button>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#popup-updateStatus-${a.accountId}">Active</button>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:if>
                                            <c:if test="${a.flag == 1}">
                                                <c:choose>
                                                    <c:when test="${a.ban == 1}">
                                                        <button class="btn btn-danger">Ban</button>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <button class="btn btn-success">Active</button>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:if>
                                        </td>
                                        <td>${a.timeLogin.toString().substring(0,16)}</td>
                                        <c:choose>
                                            <c:when test="${a.flag == 0}">
                                                <td>
                                                    <button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#popup-updateInformation-${a.accountId}">
                                                        Edit
                                                    </button>
                                                </td>
                                            </c:when>
                                            <c:otherwise>
                                                <td></td>
                                            </c:otherwise>
                                        </c:choose>
                                    </tr>
                                    <!-- popup update role -->
                                <div class="modal fade" id="popup-updateRole-${a.accountId}" aria-hidden="true"
                                     aria-labelledby="exampleModalToggleLabel" tabindex="-1">
                                    <div class="modal-dialog modal-dialog-centered" style="max-width: 400px">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title fs-5" id="exampleModalToggleLabel">Update role</h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                        aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <form action="admin" method="post">
                                                    <input type="hidden" name="cp" value="${sessionScope.page.getCurrentPage()}"/>    
                                                    <input type="hidden" name="accountId" value="${a.accountId}"/> 
                                                    <input type="hidden" name="key" value="${searchKey}"/>
                                                    <input type="hidden" name="type" value="0">
                                                    <div class="form-group">
                                                        <label for="role_admin" class="input-label" style="font-weight: bold ; margin-right: 30px">
                                                            <input type="radio" id="role_admin" name="flag" ${a.flag == 1 ? 'checked' : ''} value="1" /> 
                                                            Admin
                                                        </label>
                                                        <label for="role_user" class="input-label" style="font-weight: bold">
                                                            <input type="radio" id="role_user" name="flag" ${a.flag == 0 ? 'checked' : ''} value="0" /> 
                                                            User
                                                        </label>
                                                    </div>
                                                    <button type="submit" class="btn btn-primary" style="float: right">Update</button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- popup update status -->
                                <div class="modal fade" id="popup-updateStatus-${a.accountId}" aria-hidden="true"
                                     aria-labelledby="exampleModalToggleLabel" tabindex="-1">
                                    <div class="modal-dialog modal-dialog-centered" style="max-width: 400px">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title fs-5" id="exampleModalToggleLabel">Update status</h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                        aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <form action="admin" method="post"> 
                                                    <input type="hidden" name="cp" value="${sessionScope.page.getCurrentPage()}"/>    
                                                    <input type="hidden" name="accountId" value="${a.accountId}"/> 
                                                    <input type="hidden" name="key" value="${searchKey}"/>
                                                    <input type="hidden" name="type" value="1">
                                                    <div class="form-group">
                                                        <label for="active" class="input-label" style="font-weight: bold; margin-right: 30px;">
                                                            <input type="radio" name="ban" ${a.ban == 0 ? 'checked' : ''} value="0" /> 
                                                            Active
                                                        </label>
                                                        <label for="ban" class="input-label" style="font-weight: bold;">
                                                            <input type="radio" name="ban" ${a.ban == 1 ? 'checked' : ''} value="1" /> 
                                                            Ban
                                                        </label>
                                                    </div>
                                                    <button type="submit" class="btn btn-primary" style="float: right">Update</button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- pop-up edit information -->
                                <div class="modal fade" id="popup-updateInformation-${a.accountId}" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="editModalLabel">Edit Information</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <form action="admin" method="post">
                                                    <input type="hidden" name="cp" value="${sessionScope.page.getCurrentPage()}"/>    
                                                    <input type="hidden" name="accountId" value="${a.getStaff().getStaffId()}"/> 
                                                    <input type="hidden" name="key" value="${searchKey}"/>
                                                    <input type="hidden" name="type" value="2">
                                                    <div class="mb-3 row">
                                                        <div class="col-md-6">
                                                            <label for="account" class="form-label">First Name</label>
                                                            <input type="text" class="form-control" name="firstName" value="${a.getStaff().getFirstName()}">
                                                        </div>
                                                        <div class="col-md-6">
                                                            <label for="mobilePhone" class="form-label">Last Name</label>
                                                            <input type="text" class="form-control" name="lastName" value="${a.getStaff().getLastName()}">
                                                        </div>
                                                    </div>
                                                    <div class="mb-3 row">
                                                        <div class="col-md-6">
                                                            <label for="account" class="form-label">Gender</label>
                                                            <select name="gender" class="form-select" aria-label="Default select example">
                                                                <option value="1" <c:if test="${a.getStaff().isGender()}">selected=""</c:if>>Male</option>
                                                                <option value="0" <c:if test="${!a.getStaff().isGender()}">selected=""</c:if>>Female</option>
                                                                </select>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <label for="workEmail" class="form-label">Date of Birth</label>
                                                                <input type="date" class="form-control" name="dob" value="${a.getStaff().getBirthDate()}" />
                                                        </div>
                                                    </div>
                                                    <div class="mb-3 row">
                                                        <div class="col-md-6">
                                                            <label for="homeTown" class="form-label">Home Town</label>
                                                            <input type="text" class="form-control" name="homeTown" value="${a.getStaff().getHomeTown()}" />
                                                        </div>
                                                        <div class="col-md-6">
                                                            <label for="phoneNumber" class="form-label">Phone Number</label>
                                                            <input type="text" class="form-control" name="phone" value="${a.getStaff().getPhoneNumber()}" />
                                                        </div>
                                                    </div>
                                                    <div class="mb-3 row">
                                                        <div class="col-md-6">
                                                            <label for="department" class="form-label">Department Location</label>
                                                            <select class="form-control form-control-lg" name="department">
                                                                <optgroup label="Tokyo">
                                                                    <c:forEach var="department" items="${departments}">
                                                                        <c:if test="${department.departmentLocation == 'Tokyo'}">
                                                                            <option value="${department.departmentId}" <c:if test="${department.departmentId == a.getStaff().getDepartment().getDepartmentId()}">selected</c:if>>${department.departmentName}</option>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </optgroup>
                                                                <optgroup label="Osaka">
                                                                    <c:forEach var="department" items="${departments}">
                                                                        <c:if test="${department.departmentLocation == 'Osaka'}">
                                                                            <option value="${department.departmentId}" <c:if test="${department.departmentId == a.getStaff().getDepartment().getDepartmentId()}">selected</c:if>>${department.departmentName}</option>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </optgroup>
                                                                <optgroup label="Kyoto">
                                                                    <c:forEach var="department" items="${departments}">
                                                                        <c:if test="${department.departmentLocation == 'Kyoto'}">
                                                                            <option value="${department.departmentId}" <c:if test="${department.departmentId == a.getStaff().getDepartment().getDepartmentId()}">selected</c:if>>${department.departmentName}</option>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </optgroup>
                                                                <optgroup label="Sapporo">
                                                                    <c:forEach var="department" items="${departments}">
                                                                        <c:if test="${department.departmentLocation == 'Sapporo'}">
                                                                            <option value="${department.departmentId}" <c:if test="${department.departmentId == a.getStaff().getDepartment().getDepartmentId()}">selected</c:if>>${department.departmentName}</option>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </optgroup>
                                                                <optgroup label="Nagoya">
                                                                    <c:forEach var="department" items="${departments}">
                                                                        <c:if test="${department.departmentLocation == 'Nagoya'}">
                                                                            <option value="${department.departmentId}" <c:if test="${department.departmentId == a.getStaff().getDepartment().getDepartmentId()}">selected</c:if>>${department.departmentName}</option>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </optgroup>
                                                                <optgroup label="Fukuoka">
                                                                    <c:forEach var="department" items="${departments}">
                                                                        <c:if test="${department.departmentLocation == 'Fukuoka'}">
                                                                            <option value="${department.departmentId}" <c:if test="${department.departmentId == a.getStaff().getDepartment().getDepartmentId()}">selected</c:if>>${department.departmentName}</option>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </optgroup>
                                                                <optgroup label="Kobe">
                                                                    <c:forEach var="department" items="${departments}">
                                                                        <c:if test="${department.departmentLocation == 'Kobe'}">
                                                                            <option value="${department.departmentId}" <c:if test="${department.departmentId == a.getStaff().getDepartment().getDepartmentId()}">selected</c:if>>${department.departmentName}</option>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </optgroup>
                                                                <optgroup label="Sendai">
                                                                    <c:forEach var="department" items="${departments}">
                                                                        <c:if test="${department.departmentLocation == 'Sendai'}">
                                                                            <option value="${department.departmentId}" <c:if test="${department.departmentId == a.getStaff().getDepartment().getDepartmentId()}">selected</c:if>>${department.departmentName}</option>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </optgroup>
                                                                <optgroup label="Naha">
                                                                    <c:forEach var="department" items="${departments}">
                                                                        <c:if test="${department.departmentLocation == 'Naha'}">
                                                                            <option value="${department.departmentId}" <c:if test="${department.departmentId == a.getStaff().getDepartment().getDepartmentId()}">selected</c:if>>${department.departmentName}</option>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </optgroup>
                                                                <optgroup label="Shizuoka">
                                                                    <c:forEach var="department" items="${departments}">
                                                                        <c:if test="${department.departmentLocation == 'Shizuoka'}">
                                                                            <option value="${department.departmentId}" <c:if test="${department.departmentId == a.getStaff().getDepartment().getDepartmentId()}">selected</c:if>>${department.departmentName}</option>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </optgroup>
                                                            </select>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <label for="Salary Level" class="form-label">Salary Level</label>
                                                            <select name="salary" class="form-control form-control-lg">
                                                                <c:forEach var="salary" items="${salaryLevels}">
                                                                    <option value="${salary.salaryLevelId}" <c:if test="${salary.salaryLevelId == a.getStaff().getSalaryLevel().getSalaryLevelId()}">selected</c:if>>${salary.salaryLevelName}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                        <button type="submit" class="btn btn-primary">Save changes</button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            </tbody>
                        </table>
                        <!--Paging -->
                        <%@include file="/components/pagination.jsp" %>
                    </c:if>

                </div>
            </div>
        </div>
        <!-- Bootstrap js -->
        <script src="js/bootstrap.min.js"></script>
        <script>
                                    function validateSearch() {
                                        var keyInput = document.getElementsByName('key')[0];
                                        var key = keyInput.value.trim();
                                        if (key === '') {
                                            event.preventDefault();
                                        }
                                    }
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
    </body>
</html>
