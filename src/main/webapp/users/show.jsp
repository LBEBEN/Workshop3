<%--
  Created by IntelliJ IDEA.
  User: ben10
  Date: 19.04.2022
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/header.jsp" %>
<%-- Menu górne --%>
<div class="container-fluid">
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">UsersCRUD</h1>
        <a href="<c:url value="/user/add"/>" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
            <i class="fas fa-download fa-sm text-white-50"></i> Dodaj użytkownika</a>
    </div>
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary"> Szczegóły użytkownika </h6>
        </div>
<%-- Wyświetlanie szczegółów użytkownika --%>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table">
                    <tbody>
                    <tr>    <th>ID </th>    <th>:</th>       <th>${user.id}</th> </tr>
                    <tr>    <th>Nazwa użytkownika </th>    <th>:</th>       <th>${user.userName}</th> </tr>
                    <tr>    <th>Email </th>    <th>:</th>       <th>${user.email}</th> </tr>
                    </tbody>
                </table>
        </div>
    </div>
</div>

<%@ include file="/footer.jsp" %>
