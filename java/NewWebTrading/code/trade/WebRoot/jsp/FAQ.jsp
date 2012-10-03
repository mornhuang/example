<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title><bean:message key="company.name"/></title>
    <link rel="Stylesheet" type="text/css" href="../Style/blue/core.css" />
    <link rel="Stylesheet" type="text/css" href="../Style/blue/${sessionScope['org.apache.struts.action.LOCALE']}/style.css" />
    <script src="../Script/jquery-1.4.4.min.js" type="text/javascript"></script>
    <script src="../Script/shieldingMouse.js" type="text/javascript"></script>
    <script src="../Script/jquery-ui.custom.min.js" type="text/javascript"></script>
    <script src="../Script/jquery.cookie.js" type="text/javascript"></script>
    <script src="../Script/taifook.layout.js" type="text/javascript"></script>
    <script src="../Script/jselect.js" type="text/javascript"></script>
</head>
<body>
    <div class="text-page">
        <h1>
            <bean:message key="label.faq.problem.title"/></h1>
        <ul class="form-number-ul">
            <li><span class="number-yellow">1</span><a href="#q1"><bean:message key="label.faq.problem.row1"/></a></li>
            <li><span class="number-yellow">2</span><a href="#q2"><bean:message key="label.faq.problem.row2"/></a></li>
            <li><span class="number-yellow">3</span><a href="#q3"><bean:message key="label.faq.problem.row3"/></a></li>
            <li><span class="number-yellow">4</span><a href="#q4"><bean:message key="label.faq.problem.row4"/></a></li>
            <li><span class="number-yellow">5</span><a href="#q5"><bean:message key="label.faq.problem.row5"/></a></li>
        </ul>
        <h2 class="question" id="q1">
            <bean:message key="label.faq.question1"/></h2>
        <div class="answer">
            <p>
               <bean:message key="label.faq.answer1"/></p>
        </div>
        <h2 class="question" id="q2">
            <bean:message key="label.faq.question2"/></h2>
        <div class="answer">
            <p>
               <bean:message key="label.faq.answer2a"/></p>
            <p>
                <bean:message key="label.faq.answer2b"/></p>
        </div>
        <h2 class="question" id="q3">
            <bean:message key="label.faq.question3"/></h2>
        <div class="answer">
            <p>
                <bean:message key="label.faq.answer3"/></p>
        </div>
        <h2 class="question" id="q4">
            <bean:message key="label.faq.question4"/></h2>
        <div class="answer">
            <p>
                <bean:message key="label.faq.answer4"/></p>
            <ul class="form-number-ul">
                <li><span class="number">1</span><bean:message key="label.faq.answer4.row1"/></li>
                <li><span class="number">2</span><bean:message key="label.faq.answer4.row2"/></li>
                <li><span class="number">3</span><bean:message key="label.faq.answer4.row3"/></li>
                <li><span class="number">4</span><bean:message key="label.faq.answer4.row4"/></li>
                <li><span class="number">5</span><bean:message key="label.faq.answer4.row5"/></li>
            </ul>
        </div>
        <h2 class="question" id="q5">
           <bean:message key="label.faq.question5"/></h2>
        <div class="answer">
            <p>
                <bean:message key="label.faq.answer5"/></p>
        </div>
    </div>
</body>
</html>
