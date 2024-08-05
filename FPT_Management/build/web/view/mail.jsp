<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" type="image/x-icon" href="img/logoweb.png">
        <title>Outlook-like Interface</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/chosen/1.8.7/chosen.min.css" rel="stylesheet">
        <style>
            body {
                font-family: Arial, sans-serif;


                .sidebar {
                    height: 100vh;
                    background-color: #f8f9fa;
                    padding-top: 20px;
                    border-right: 1px solid #ddd;
                }

                .content {
                    padding: 20px;
                }

                .list-group-item {
                    cursor: pointer;
                }

                .email-content {
                    display: none;
                }

                .email-content.active {
                    display: block;
                }

                .nav-link.active {
                    font-weight: bold;
                }

                .email-list-item {
                    border-bottom: 1px solid #ddd;
                }

                .email-list-item p {
                    margin: 0;
                }

                .email-list-item small {
                    display: block;
                    color: gray;
                }

                .email-details {
                    border-bottom: 1px solid #ddd;
                    padding-bottom: 15px;
                    margin-bottom: 15px;
                }

                .reply-section {
                    margin-top: 20px;
                }
            </style>
        </head>

        <body>
            <%@include file="/components/headerAndSideBar.jsp"%>
            <div style="position: relative;
                 z-index: -2">
                     <div style="position: absolute;
                     width: 80vw;
                     left: 20vw;
                     top: 10vh">
                         <div class="container-fluid">
                             <div class="row">
                                 <nav class="col-md-3 col-lg-3 sidebar" style="position: relative;
                                 padding-top: 20px">
                                     <button style="position: absolute;
                                     right: 10px;
                                     top: 10px;
                                     border: 2px solid #00000038" type="button" class="btn btn-utline-light me-2"  id="link" data-bs-target="#sendMail" data-bs-toggle="modal">Send <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-send" viewBox="0 0 16 16">
                                         <path d="M15.854.146a.5.5 0 0 1 .11.54l-5.819 14.547a.75.75 0 0 1-1.329.124l-3.178-4.995L.643 7.184a.75.75 0 0 1 .124-1.33L15.314.037a.5.5 0 0 1 .54.11ZM6.636 10.07l2.761 4.338L14.13 2.576zm6.787-8.201L1.591 6.602l4.339 2.76z"/>
                                         </svg></button>
                                     <div class="mb-3">

                                     </div>
                                     <div class="mb-3">
                                         <h3 style="color: red">${errMsg}</h3>
                                         <label for="startDate">Start Date:</label>
                                         <input type="date" class="form-control" id="startDate">
                                     </div>
                                     <div class="mb-3">
                                         <label for="endDate">End Date:</label>
                                         <input type="date" class="form-control" id="endDate">
                                     </div>
                                     <ul class="nav nav-tabs" id="inboxTabs" role="tablist">
                                         <li class="nav-item">
                                             <a class="nav-link active" id="focused-tab" data-toggle="tab" href="#focused" role="tab">Receive</a>
                                         </li>
                                         <li class="nav-item">
                                             <a class="nav-link" id="other-tab" data-toggle="tab" href="#other" role="tab">Send</a>
                                         </li>
                                     </ul>
                                     <div class="tab-content" id="inboxTabsContent">
                                         <div class="tab-pane fade show active" id="focused" role="tabpanel">
                                             <div class="list-group" id="emailList">
                                                 <c:forEach items="${listReceiveMail}" var="mail" varStatus="status">
                                                     <a class="list-group-item list-group-item-action email-list-item" data-target="#emailreceive${status.index}"
                                                        data-date="${mail.getMail().getDate()}" style="position: relative">
                                                         <c:choose>
                                                             <c:when test="${mail.getMail().getMailType().getMailTypeId() == 1}">
                                                                 <button class=" bg-primary text-white" style="position: absolute;
                                                                 right: 10px;
                                                                 border: none">Information</button>
                                                             </c:when>
                                                             <c:when test="${mail.getMail().getMailType().getMailTypeId() == 2}">
                                                                 <button class=" bg-danger text-white" style="position: absolute;
                                                                 right: 10px;
                                                                 border: none">Alert</button>
                                                             </c:when>
                                                             <c:when test="${mail.getMail().getMailType().getMailTypeId() == 3}">
                                                                 <button class=" bg-danger text-white" style="position: absolute;
                                                                 right: 10px;
                                                                 border: none">Danger</button>
                                                             </c:when>
                                                             <c:when test="${mail.getMail().getMailType().getMailTypeId() == 4}">
                                                                 <button class=" bg-warning text-white" style="position: absolute;
                                                                 right: 10px;
                                                                 border: none">Warning</button>
                                                             </c:when>
                                                             <c:when test="${mail.getMail().getMailType().getMailTypeId() == 5}">
                                                                 <button class=" bg-dark text-white" style="position: absolute;
                                                                 right: 10px;
                                                                 border: none">Congratulations</button>
                                                             </c:when>
                                                             <c:when test="${mail.getMail().getMailType().getMailTypeId() == 6}">
                                                                 <button class=" bg-info text-white" style="position: absolute;
                                                                 right: 10px;
                                                                 border: none">Success</button>
                                                             </c:when>
                                                         </c:choose>
                                                         <p>${mail.getMail().getTitle()}</p>
                                                         <small>${mail.getMail().getDate().toString().substring(5, 7)}/${mail.getMail().getDate().toString().substring(8, 10)} - ${mail.getMail().getTime()}</small>
                                                     </a>

                                                 </c:forEach>

                                             </div>
                                         </div>
                                         <div class="tab-pane fade" id="other" role="tabpanel">
                                             <div class="list-group" id="emailListOther">
                                                 <c:forEach items="${listSendMail}" var="mail" varStatus="status">
                                                     <a class="list-group-item list-group-item-action email-list-item" data-target="#emailsend${status.index}"
                                                        data-date="${mail.getMail().getDate()}" style="position: relative">
                                                         <c:choose>
                                                             <c:when test="${mail.getMail().getMailType().getMailTypeId() == 1}">
                                                                 <button class=" bg-primary text-white" style="position: absolute;
                                                                 right: 10px;
                                                                 border: none">Information</button>
                                                             </c:when>
                                                             <c:when test="${mail.getMail().getMailType().getMailTypeId() == 2}">
                                                                 <button class=" bg-danger text-white" style="position: absolute;
                                                                 right: 10px;
                                                                 border: none">Alert</button>
                                                             </c:when>
                                                             <c:when test="${mail.getMail().getMailType().getMailTypeId() == 3}">
                                                                 <button class=" bg-danger text-white" style="position: absolute;
                                                                 right: 10px;
                                                                 border: none">Danger</button>
                                                             </c:when>
                                                             <c:when test="${mail.getMail().getMailType().getMailTypeId() == 4}">
                                                                 <button class=" bg-warning text-white" style="position: absolute;
                                                                 right: 10px;
                                                                 border: none">Warning</button>
                                                             </c:when>
                                                             <c:when test="${mail.getMail().getMailType().getMailTypeId() == 5}">
                                                                 <button class=" bg-dark text-white" style="position: absolute;
                                                                 right: 10px;
                                                                 border: none">Congratulations</button>
                                                             </c:when>
                                                             <c:when test="${mail.getMail().getMailType().getMailTypeId() == 6}">
                                                                 <button class=" bg-info text-white" style="position: absolute;
                                                                 right: 10px;
                                                                 border: none">Success</button>
                                                             </c:when>
                                                         </c:choose>
                                                         <p>${mail.getMail().getTitle()}</p>
                                                         <small>${mail.getMail().getDate().toString().substring(5, 7)}/${mail.getMail().getDate().toString().substring(8, 10)} - ${mail.getMail().getTime()}</small>
                                                     </a>
                                                 </c:forEach>
                                             </div>
                                         </div>
                                     </div>
                                 </nav>
                                 <main class="col-md-9 ml-sm-auto col-lg-9 content">
                                     <c:forEach items="${listReceiveMail}" var="mail" varStatus="status">
                                         <div id="emailreceive${status.index}" class="email-content">
                                             <div class="email-details">
                                                 <h3><strong>${mail.getMail().getTitle()}</strong></h3>
                                                 <c:choose>
                                                     <c:when test="${mail.getMail().getMailType().getMailTypeId() == 1}">
                                                         <label class=" bg-primary text-white" style="padding: 5px 10px;
                                                         margin-bottom: 10px">Information</label>
                                                     </c:when>
                                                     <c:when test="${mail.getMail().getMailType().getMailTypeId() == 2}">
                                                         <label class=" bg-danger text-white" style="padding: 5px 10px;
                                                         margin-bottom: 10px">Alert</label>
                                                     </c:when>
                                                     <c:when test="${mail.getMail().getMailType().getMailTypeId() == 3}">
                                                         <label class=" bg-danger text-white" style="padding: 5px 10px;
                                                         margin-bottom: 10px">Danger</label>
                                                     </c:when>
                                                     <c:when test="${mail.getMail().getMailType().getMailTypeId() == 4}">
                                                         <label class=" bg-warning text-white" style="padding: 5px 10px;
                                                         margin-bottom: 10px">Warning</label>
                                                     </c:when>
                                                     <c:when test="${mail.getMail().getMailType().getMailTypeId() == 5}">
                                                         <label class=" bg-dark text-white" style="padding: 5px 10px;
                                                         margin-bottom: 10px">Congratulations</label>
                                                     </c:when>
                                                     <c:when test="${mail.getMail().getMailType().getMailTypeId() == 6}">
                                                         <label class=" bg-info text-white" style="padding: 5px 10px;
                                                         margin-bottom: 10px">Success</label>
                                                     </c:when>
                                                 </c:choose>
                                                 <p>${mail.getMail().getDate()}(${mail.getMail().getTime()})</p>
                                                 <p><strong>From:</strong> ${mail.getFromStaff().getAccount().getUserName()}</p>
                                                 <p><strong>To:</strong> ${mail.getToStaff().getAccount().getUserName()}</p>
                                             </div>
                                             <p>${mail.getMail().getDescription()}</p>
                                             <div class="reply-section">
                                                 <h5>Reply</h5>
                                                 <form action="mail" method="post">
                                                     <textarea class="form-control mb-2" rows="3" placeholder="Type your reply here..." name="description" style="width: 30vw"></textarea>
                                                     <input type="file" class="form-control-file mb-2" name="img">
                                                     <button class="btn btn-primary">Send</button>
                                                 </form>
                                             </div>
                                         </div>
                                     </c:forEach>

                                     <c:forEach items="${listSendMail}" var="mail" varStatus="status">
                                         <div id="emailsend${status.index}" class="email-content">
                                             <div class="email-details">
                                                 <h3><strong>${mail.getMail().getTitle()}</strong></h3>
                                                 <c:choose>
                                                     <c:when test="${mail.getMail().getMailType().getMailTypeId() == 1}">
                                                         <label class=" bg-primary text-white" style="padding: 5px 10px;
                                                         margin-bottom: 10px">Information</label>
                                                     </c:when>
                                                     <c:when test="${mail.getMail().getMailType().getMailTypeId() == 2}">
                                                         <label class=" bg-danger text-white" style="padding: 5px 10px;
                                                         margin-bottom: 10px">Alert</label>
                                                     </c:when>
                                                     <c:when test="${mail.getMail().getMailType().getMailTypeId() == 3}">
                                                         <label class=" bg-danger text-white" style="padding: 5px 10px;
                                                         margin-bottom: 10px">Danger</label>
                                                     </c:when>
                                                     <c:when test="${mail.getMail().getMailType().getMailTypeId() == 4}">
                                                         <label class=" bg-warning text-white" style="padding: 5px 10px;
                                                         margin-bottom: 10px">Warning</label>
                                                     </c:when>
                                                     <c:when test="${mail.getMail().getMailType().getMailTypeId() == 5}">
                                                         <label class=" bg-dark text-white" style="padding: 5px 10px;
                                                         margin-bottom: 10px">Congratulations</label>
                                                     </c:when>
                                                     <c:when test="${mail.getMail().getMailType().getMailTypeId() == 6}">
                                                         <label class=" bg-info text-white" style="padding: 5px 10px;
                                                         margin-bottom: 10px">Success</label>
                                                     </c:when>
                                                 </c:choose>
                                                 <p>${mail.getMail().getDate()}(${mail.getMail().getTime()})</p>
                                                 <p><strong>From:</strong> ${mail.getFromStaff().getAccount().getUserName()}</p>
                                                 <p><strong>To:</strong> ${mail.getToStaff().getAccount().getUserName()}</p>
                                             </div>
                                             <p>${mail.getMail().getDescription()}</p>

                                         </div>
                                     </c:forEach>



                                 </main>
                             </div>
                         </div>
                     </div>
                 </div>


                 <!--pop-up sendMail-->
                 <form action="mail" method="post">
                     <div class="modal fade" id="sendMail" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
                          aria-labelledby="staticBackdropLabel" aria-hidden="true">
                         <div class="modal-dialog modal-dialog-centered">
                             <div class="modal-content">
                                 <div class="modal-header">
                                     <h5 class="modal-title">Send Mail</h5>
                                     <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                 </div>

                                 <div class="modal-body event-create-pop-up" style="margin: 0px;">
                                 <div class="mb-3 text-end">
                                     <a data-bs-target="#tagmember" data-bs-toggle="modal" style="cursor: pointer;
                                        color: blue;">
                                         <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-fill-add" viewBox="0 0 16 16">
                                         <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0m-2-6a3 3 0 1 1-6 0 3 3 0 0 1 6 0"/>
                                         <path d="M2 13c0 1 1 1 1 1h5.256A4.5 4.5 0 0 1 8 12.5a4.5 4.5 0 0 1 1.544-3.393Q8.844 9.002 8 9c-5 0-6 3-6 4"/>
                                         </svg>
                                         Choose person
                                     </a>
                                 </div>
                                 <div class="mb-3">
                                     <label for="mailType" class="form-label">Type</label>
                                     <select name="typeMailId" class="form-select" id="mailType">
                                         <c:forEach items="${listMailType}" var="typeMailId">
                                             <option value="${typeMailId.getMailTypeId()}">
                                                 ${typeMailId.getMailTypeName()}
                                             </option>
                                         </c:forEach>
                                     </select>
                                 </div>
                                 <div class="mb-3">
                                     <label for="title_event" class="form-label">Title</label>
                                     <input type="text" name="title" class="form-control" id="title_event" required=""/>
                                 </div>
                                 <div class="mb-3">
                                     <label for="file" class="form-label">Upload File</label>
                                     <input type="file" name="file" class="form-control" id="file"/>
                                 </div>
                                 <div class="mb-3">
                                     <label for="description" class="form-label">Description</label>
                                     <textarea class="form-control" name="description" id="description" rows="5" required=""></textarea>
                                 </div>
                                 <div class="d-flex justify-content-end">
                                     <button type="reset" class="btn btn-secondary me-2">Reset</button>
                                     <button type="submit" class="btn btn-primary">Create</button>
                                 </div>
                             </div>
                         </div>
                     </div>
                 </div>

                 <!--Tag member-->
                 <div class="modal fade" id="tagmember" aria-hidden="true" aria-labelledby="exampleModalToggleLabel2" tabindex="-1">
                     <div class="modal-dialog modal-dialog-centered">
                         <div class="modal-content">
                             <div class="modal-header">
                                 <h1 class="modal-title fs-5" id="exampleModalToggleLabel2">Choose person</h1>
                                 <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                             </div>
                             <div class="modal-body">
                                 <label>Choose persons you want to send mail</label>
                                 <div>
                                     <select name="tagPerson" class="anyuser" multiple style="width: 100%;">
                                         <c:forEach items="${listAccount}" var="acc">
                                             <option value="${acc.getStaff().getStaffId()}">
                                                 ${acc.getUserName()}
                                             </option> 
                                         </c:forEach>
                                     </select>
                                 </div>
                             </div>
                             <div class="modal-footer">
                                 <a class="btn btn-primary" data-bs-target="#sendMail" data-bs-toggle="modal">Back to send mail</a>
                             </div>
                         </div>
                     </div>
                 </div>
            </form>


            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.11.6/umd/popper.min.js"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0/js/bootstrap.bundle.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
                    integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/chosen/1.8.7/chosen.jquery.min.js"></script>
            <script>
                //user choose
                $(document).ready(function () {
                    $('.anyuser').chosen({width: '100%'});

                    $('#tagmember').on('shown.bs.modal', function () {
                        $(this).find('.anyuser').chosen('destroy').chosen();
                    });

                    $(window).on('load', function () {
                        var chosenContainers = document.querySelectorAll('.chosen-container.chosen-container-multi');

                        chosenContainers.forEach(function (container) {
                            container.style.width = '100%';
                        });
                    });
                });

                // filter date
                $(document).ready(function () {
                    $('.email-list-item').on('click', function () {
                        var target = $(this).data('target');
                        $('.email-content').removeClass('active');
                        $(target).addClass('active');
                    });

                    $('#startDate, #endDate').on('change', function () {
                        var startDate = $('#startDate').val();
                        var endDate = $('#endDate').val();

                        if (!startDate && !endDate) {
                            $('.list-group-item').show();
                        } else {
                            $('.list-group-item').each(function () {
                                var emailDate = $(this).data('date');
                                if (startDate && new Date(emailDate) < new Date(startDate)) {
                                    $(this).hide();
                                } else if (endDate && new Date(emailDate) > new Date(endDate)) {
                                    $(this).hide();
                                } else {
                                    $(this).show();
                                }
                            });
                        }
                    });
                });
            </script>
        </body>

    </html>
