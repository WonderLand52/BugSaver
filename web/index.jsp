<%--
  Created by IntelliJ IDEA.
  User: darm
  Date: 2/27/15
  Time: 10:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>BugSaver</title>

  <!-- Bootstrap -->
  <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="bootstrap/tables/dataTables.bootstrap.css" rel="stylesheet">
</head>
<body>
<div class="container theme-showcase" role="main">
  <nav class="navbar navbar-default">
    <div class="container-fluid">

      <!-- Brand and toggle get grouped for better mobile display -->
      <div class="navbar-header">
        <a class="navbar-brand" href="index.jsp">Bug Saver</a>
      </div>

      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav navbar-right">
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
              Pages to display<span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
              <li><a href="showBug.do?recordsCount=10">10</a></li>
              <li><a href="showBug.do?recordsCount=20">20</a></li>
              <li><a href="showBug.do?recordsCount=30">30</a></li>
            </ul>
          </li>
        </ul>
      </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
  </nav>

  <div class="jumbotron">
    <h1 class="text-center">Bugs</h1>
    <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
      <thead>
        <tr>
          <th>Date</th>
          <th>Importance</th>
          <th>Source</th>
          <th>Message</th>
        </tr>
      </thead>

      <tfoot>
        <tr>
          <th>Date</th>
          <th>Importance</th>
          <th>Source</th>
          <th>Message</th>
        </tr>
      </tfoot>
      <tbody>
        <c:forEach var="record" items="${records.toList()}">
          <tr>
            <td>${record.getTime().getTime()}</td>
            <td>${record.getImportance()}</td>
            <td>${record.getSource()}</td>
            <td>${record.getMessage()}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>
</div>


  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <!-- Include all compiled plugins (below), or include individual files as needed -->
  <script src="bootstrap/js/bootstrap.min.js"></script>
  <script src="bootstrap/tables/jquery.dataTables.min.js"></script>
  <script src="bootstrap/tables/dataTables.bootstrap.js"></script>
  <script>
    $(document).ready(function() {
      $('#example').dataTable();
    } );
  </script>
</body>
</html>
