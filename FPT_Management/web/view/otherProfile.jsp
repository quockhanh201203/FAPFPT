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
                                <strong>Employee ID:</strong> ${currentUser.getStaff().getStaffId()}
                            </div>
                            <div class="col-md-6">
                                <strong>Mobile Phone:</strong> ${currentUser.getStaff().getPhoneNumber()}
                            </div>
                        </div>
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
                </div>
            </div>
        </div>

        


    </body>



    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
    crossorigin="anonymous"></script>

</body>