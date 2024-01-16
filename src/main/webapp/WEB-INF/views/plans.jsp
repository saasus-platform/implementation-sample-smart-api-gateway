<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <body>
        <h1>Pricing Plans</h1>
        
        <c:forEach var="pricingPlan" items="${sessionScope.pricingPlans.pricingPlans}">
            <h2>Plan name: ${pricingPlan.name}</h2>
            <p>Plan displayName: ${pricingPlan.displayName}</p>
            <p>plan description: ${pricingPlan.description}</p>
            <p>plan Used: ${pricingPlan.used}</p>
    
            <h3>Pricing Menus:</h3>
    
            <c:forEach var="pricingMenu" items="${pricingPlan.pricingMenus}">
                <h4>Menu name: ${pricingMenu.name}</h4>
                <p>Menu displayName: ${pricingMenu.displayName}</p>
                <p>Menu description: ${pricingMenu.description}</p>
                <p>Menu Used: ${pricingMenu.used}</p>
    
                <h5>Units:</h5>
    
                <c:forEach var="unit" items="${pricingMenu.units}">
                    <p>Unit unitAmount: ${unit.actualInstance.unitAmount}</p>
                    <p>Unit recurringInterval: ${unit.actualInstance.recurringInterval}</p>
                    <p>Unit name: ${unit.actualInstance.name}</p>
                    <p>Unit displayName: ${unit.actualInstance.displayName}</p>
                    <p>Unit description: ${unit.actualInstance.description}</p>
                    <p>Unit Type: ${unit.actualInstance.type}</p>
                    <p>Unit Currency: ${unit.actualInstance.currency}</p>
                    <p>Unit ID: ${unit.actualInstance.id}</p>
                    <p>Unit Used: ${unit.actualInstance.used}</p>
                </c:forEach>
    
                <p>Menu ID: ${pricingMenu.id}</p>
            </c:forEach>
    
            <p>Plan ID: ${pricingPlan.id}</p>
        </c:forEach>
    </body>
</html>