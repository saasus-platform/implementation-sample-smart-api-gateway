<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
    <c:forEach var="tenant" items="${sessionScope.userInfo.tenants}">
        <input type="hidden" id="tenantId" value="${tenant.id}">
    </c:forEach>

    <script type="text/javascript">
        var tenantId = document.getElementById("tenantId").value;

        function redirectToTenantPage() {
            window.location.href = "http://localhost:9000/sampleImplement/tenants?tenantId=" + tenantId;
        }

        function redirectToPlansPage() {
            window.location.href = "http://localhost:9000/sampleImplement/plans";
        }

    </script>


    <h1> ID Token </h1>    
    <p> ID Token Key: ${sessionScope.idToken} </p>
    
    <h1> User Information </h1>
    <p> ID: ${sessionScope.userInfo.id} </p>
    <p> Email: ${sessionScope.userInfo.email} </p>

    <h2> Tenants </h2>
    <c:forEach var="tenant" items="${sessionScope.userInfo.tenants}">
        <h3> Tenant ID: ${tenant.id} </h3>
        <p> Name: ${tenant.name} </p>

        <h3> Available Environments </h3>
        <c:forEach var="env" items="${tenant.envs}">
            <p> ID: ${env.id} </p>
            <p> Name: ${env.name} </p>
        </c:forEach>
    </c:forEach>

    <button id="t_button" onclick="redirectToTenantPage()">Go to Tenant Page</button>
    <button id="p_button" onclick="redirectToPlansPage()">Go to Plans Page</button>

</body>
</html>