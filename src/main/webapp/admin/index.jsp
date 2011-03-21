<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="dataBase" class="com.openshop.controller.install.InstallController"/>
<html>
<head>
    <title>Admin-CP</title>
</head>
<body>
Checking installation...
<%
    if (dataBase.checkInstallation()) {
%>
<jsp:forward page="login.html"/>
<%
} else {
%>
<jsp:forward page="../install/stepLanguage.html"/>
<%
    }
%>
</body>
</html>