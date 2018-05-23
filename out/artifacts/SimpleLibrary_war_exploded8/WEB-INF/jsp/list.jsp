<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2018/4/14
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<head>
    <title>图书列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <link href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap-theme.min.css" rel="stylesheet">

</head>
<body>
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading" text-center>
            <h2>图书列表</h2>

        </div>
        <form name="firstForm" action="<%=request.getContextPath()%>/books/search" method="get">
            <div class="panel-heanding">
                <table class="table table-bookname">
                    <thead>
                    <tr>
                        <th width="90" align="lift">
                            图书名称:
                        </th>
                        <th width="150" align="lift">
                            <input type="text" name="name" class="allInput" value="${name}"
                            placeholder="输入检索名称"/>
                        </th>
                        <th>
                            <input type="submit" id="tabSub" value="检索"/>
                        </th>
                    </tr>
                    </thead>
                </table>
            </div>
        </form>
        <div class="table table-hover">
            <table class="table table-hover">
            <thead>
            <tr>
                <th>图书ID</th>
                <th>图书名称</th>
                <th>馆藏数量</th>
                <th>详细</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="sk">
                <tr>
                    <td>${sk.book_id}</td>
                    <td>${sk.name}</td>
                    <td>${sk.number}</td>
                    <td><a class="btn btn-info" href="${sk.book_id}/detail" target="_blank">详细</a></td>
                </tr>
            </c:forEach>
            </tbody>
            </table>
        </div>
    </div>
</div>
<script src="http://apps:bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
<script src="http://apps.bdimg.com/libs/bootstrap/3.0.0/js/bootstrap.min.js"></script>

</body>
</html>
