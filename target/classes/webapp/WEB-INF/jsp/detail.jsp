<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2018/4/15
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html; charset=UTF-8" language="java" %>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<link href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
<link href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap-theme.min.css" rel="stylesheet">


<!DOCTYPE html>
<html>
<head>
    <title>预约详情页</title>

</head>
<body>
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <h2>图书详情</h2>
        </div>
        <div class="panel-body">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>图书ID</th>
                    <th>图书名称</th>
                    <th>图书简介</th>
                    <th>剩余数量</th>
                    <th>预约数量</th>

                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${book.book_id}</td>
                    <td>${book.name}</td>
                    <td>${book.introd}</td>
                    <td>${book.number }</td>
                    <td>1</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="panel-body text-center">
            <h2 class="text-danger">
                <!--用来展示预约控件-->
                <span class="glyphicon" id="appoint-box"></span> <!--在js里面调用这个id还可以动态显示一些其他东西，例如动态时间等（需要插件）-->
                <span class="glyphicon" id="like1"></span>

                <button id="like5">${book.book_like }</button>



                <span class="glyphicon"><a class="btn btn-primary btn-lg" href="/books/appoint?studentId=${cookie['studentId'].value}" target="_blank">查看我的已预约书籍</a></span>





            </h2>           <!--如何获取该页面弹出层输入的学生ID， 传给上面的url-->
        </div>
    </div>
    <div >

          <a href="#reply-area">回复</a>

    </div>
    <div id="reply-area" class="post-reply-textarea">
        <div style="width:3000px;margin:10px 20px">
            <form action="reply.do" method="post" enctype="multipart/form-data">
                <input type="hidden" name="bid" value="${book.book_id}">
                <textarea name="content" id="textarea" style="height:200px;max-height:1000px;"></textarea>
                <button class="reply-button">回帖</button>
            </form>
        </div>
    </div>

</div>
<!--  登录弹出层 输入电话   -->
<div id="varifyModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title text-center">
                    <span class="glyphicon glyphicon-studentId"> </span>请输入学号和密码:
                </h3>
            </div>

            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-8 col-xs-offset-2">
                        <input type="text" name="studentId" id="studentIdKey"
                               placeholder="填写学号^o^" class="form-control">
                    </div>
                    <div class="col-xs-8 col-xs-offset-2">
                        <input type="password" name="password" id="passwordKey"
                               placeholder="输入密码^o^" class="form-control">
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <!--  验证信息 -->
                <span id="studentMessage" class="glyphicon"> </span>
                <button type="button" id="studentBtn" class="btn btn-success">
                    <span class="glyphicon glyphicon-student"></span>
                    Submit
                </button>
            </div>
        </div>
    </div>
</div>

</body>



<script src="/resources/script/bookappointment.js" ></script>
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>

<script src="https://cdn.bootcss.com/jquery-cookie/1.4.0/jquery.cookie.js"></script>
<script src="http://cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.min.js"></script>
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>


<script type="text/javascript">
$(function () {




    bookappointment.detail.init({
        book_id:${book.book_id}

    });

})

</script>
</html>
