<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" type="image/x-icon" href="img/logoweb.png">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="css/home.css">
        <style>
            .fs-5,
            .fs-8 {
                display: block;
            }
        </style>
        <title>FPT Software</title>
    </head>

    <body>
        <%@include file="/components/headerAndSideBar.jsp"%>
        <!-- menu chinh -->
        <div class="body-content">
            <div class="container mt-5" style="padding: 10px 50px">
                <div class="row mt-3" style="padding-bottom: 20px;">
                    <div class="col-md-3 text-center">
                        <img src="img/changePassBackground.jpg" class="img-fluid rounded-circle" alt="Avatar">
                    </div>
                    <div class="col-md-7" style="padding-top: 40px; padding-right: 20px;">
                        <div class="row mb-3">
                            <div class="col-md-6">
                                <strong>Account:</strong> ${currentUser.getUserName()}
                            </div>
                            <div class="col-md-6">
                                <strong>Work Email:</strong> ${currentUser.getUserName()}@fpt.com
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-md-6">
                                <strong>Home Town:</strong> ${currentUser.getStaff().getHomeTown()}
                            </div>
                            <div class="col-md-6">
                                <strong>Mobile Phone:</strong> ${currentUser.getStaff().getPhoneNumber()}
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-md-6">
                                <strong>Department:</strong> ${currentUser.getStaff().getDepartment().getDepartmentName()}
                            </div>
                            <div class="col-md-6">
                                <strong>Address:</strong> ${currentUser.getStaff().getDepartment().getDepartmentLocation()}
                            </div>
                        </div>
                    </div>

                </div>
                <ul class="nav nav-tabs mt-4" id="myTab" role="tablist">
                    <li class="nav-item" role="presentation">
                        <a class="nav-link active" id="personal-info-tab" data-bs-toggle="tab" href="#personal-info"
                           role="tab" aria-controls="personal-info" aria-selected="true">Personal Info</a>
                    </li>
                    <li class="nav-item" role="presentation">
                        <a class="nav-link" id="contract-info-tab" data-bs-toggle="tab" href="#contract-info" role="tab"
                           aria-controls="contract-info" aria-selected="false">Contract Info</a>
                    </li>
                    <li class="nav-item" role="presentation">
                        <a class="nav-link" id="relationship-info-tab" data-bs-toggle="tab" href="#relationship-info"
                           role="tab" aria-controls="relationship-info" aria-selected="false">Relationship Info</a>
                    </li>
                    <li class="nav-item" role="presentation">
                        <a class="nav-link" id="income-tab" data-bs-toggle="tab" href="#income" role="tab"
                           aria-controls="income" aria-selected="false">Income</a>
                    </li>
                </ul>
                <div class="tab-content mt-4" id="myTabContent">
                    <div class="tab-pane fade show active" id="personal-info" role="tabpanel"
                         aria-labelledby="personal-info-tab">
                        <div class="row">
                            <div class="col-md-6">
                                <h5>Basic Information</h5>
                                <table class="table">
                                    <tbody>
                                        <tr>
                                            <td><strong>First Name:</strong> ${currentUser.getStaff().getFirstName()} </td>
                                            <td><strong>Last Name:</strong> ${currentUser.getStaff().getLastName()}</td>
                                        </tr>
                                        <tr>
                                            <td><strong>Gender:</strong> ${currentUser.getStaff().isGender() ? "Male" : "Female"}</td>
                                            <td><strong>Date of Birth:</strong> ${currentUser.getStaff().getBirthDate()}</td>
                                        </tr>
                                        <tr>
                                            <td><strong>Home Town:</strong> ${currentUser.getStaff().getHomeTown()}</td>
                                            <td><strong>Phone Number:</strong> ${currentUser.getStaff().getPhoneNumber()}</td>
                                        </tr>
                                    </tbody>
                                </table>
                                <div class="row">
                                    <div class="col-12 text-end">
                                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#editModal">
                                            Edit Information
                                        </button>
                                        <button type="button" class="btn btn-danger">
                                            <a href="changepassword" style="text-decoration: none; color: white">Change Password</a>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <h5>Contact Information</h5>
                                <table class="table">
                                    <tbody>
                                        <tr>
                                            <td>Mobile Phone</td>
                                            <td>Phone Number 1 (at your homeland)</td>
                                        </tr>
                                        <tr>
                                            <td>Home Phone</td>
                                            <td>Relationship of Phone Number 1</td>
                                        </tr>
                                        <tr>
                                            <td>Personal Email</td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>Current Address</td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>Permanent Address</td>
                                            <td></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="contract-info" role="tabpanel" aria-labelledby="contract-info-tab">
                        <!-- Contract Info Content Here -->
                    </div>
                    <div class="tab-pane fade" id="relationship-info" role="tabpanel"
                         aria-labelledby="relationship-info-tab">
                        <!-- Relationship Info Content Here -->
                    </div>
                    <div class="tab-pane fade" id="income" role="tabpanel" aria-labelledby="income-tab">
                        <!-- Income Content Here -->
                    </div>
                    <!--                    <div class="tab-pane fade" id="changepassword" role="tabpanel" aria-labelledby="changepassword-tab">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <form id="changeInfoForm">
                                                        <input type="hidden" class="form-control" name="id" value="${currentUser.getStaff().getStaffId()}">
                                                        <input type="hidden" class="form-control" name="type" value="1">
                                                        <div class="mb-3 row">
                                                            <div class="col-md-12">
                                                                <label for="account" class="form-label">Old password</label>
                                                                <input type="password" class="form-control" name="currentPassword" required />
                                                            </div>
                                                        </div>
                                                        <div class="mb-3 row">
                                                            <div class="col-md-12">
                                                                <label for="account" class="form-label">New password</label>
                                                                <input id="password" type="password" class="form-control" name="newPassword" oninput="validatePassword()" required />
                                                            </div>
                                                        </div>
                                                        <div class="mb-3 row">
                                                            <div class="col-md-12">
                                                                <label for="account" class="form-label">Confirm new password</label>
                                                                <input id="re-password" type="password" class="form-control" oninput="validatePassword()" required />
                                                            </div>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-danger" id="saveChangesBtn">Save changes</button>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>-->

                </div>
            </div>
        </div>

        <!-- pop-up -->
        <!-- Modal -->
        <div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editModalLabel">Edit Information</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="home" method="post" id="changeInfoForm">
                            <input type="hidden" class="form-control" name="employeeID" value="${currentUser.getStaff().getStaffId()}">
                            <div class="mb-3 row">
                                <div class="col-md-6">
                                    <label for="account" class="form-label">First Name</label>
                                    <input type="text" class="form-control" name="firstName" value="${currentUser.getStaff().getFirstName()}">
                                </div>
                                <div class="col-md-6">
                                    <label for="mobilePhone" class="form-label">Last Name</label>
                                    <input type="text" class="form-control" name="lastName" value="${currentUser.getStaff().getLastName()}">
                                </div>
                            </div>

                            <div class="mb-3 row">
                                <div class="col-md-6">
                                    <label for="account" class="form-label">Gender</label>
                                    <select name="gender" class="form-select" aria-label="Default select example">
                                        <option value="1" <c:if test="${currentUser.getStaff().isGender()}">selected=""</c:if>>Male</option>
                                        <option value="0" <c:if test="${!currentUser.getStaff().isGender()}">selected=""</c:if>>Female</option>
                                        </select>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="workEmail" class="form-label">Date of Birth</label>
                                        <input type="date" class="form-control" name="dateOfBirth" value="${currentUser.getStaff().getBirthDate()}">
                                </div>
                            </div>
                            <div class="mb-3 row">
                                <div class="col-md-6">
                                    <label for="department" class="form-label">Home Town</label>
                                    <input type="text" class="form-control" name="homeTown" value="${currentUser.getStaff().getHomeTown()}">
                                </div>
                                <div class="col-md-6">
                                    <label for="address" class="form-label">Phone Number</label>
                                    <input type="text" class="form-control" name="phoneNumber" value="${currentUser.getStaff().getPhoneNumber()}" readonly="">
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary" form="changeInfoForm">Save changes</button>
                    </div>
                </div>
            </div>
        </div>



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
            $(document).ready(function () {
                $('#saveChangesBtn').click(function (event) {
                    event.preventDefault();

                    var formData = $('#changeInfoForm').serialize();

                    $.ajax({
                        type: 'POST',
                        url: 'changepassword', // Đường dẫn đến Servlet xử lý
                        data: formData,
                        success: function (response) {
                            if (response.trim() === 'success') {
                                // Đổi mật khẩu thành công
                                alert('Password changed successfully!');
                                window.location.href = 'login'; // Chuyển hướng đến trang login
                            } else {
                                // Hiển thị thông báo lỗi
                                $('#errorCurrentPassword').text('Current password is incorrect!');
                            }
                        },
                        error: function () {
                            alert('Error occurred while processing your request.');
                        }
                    });
                });
            });
        </script>

        <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
    </body>
</html>