<%--
  Created by IntelliJ IDEA.
  User: darm
  Date: 2/27/15
  Time: 10:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Bootstrap 101 Template</title>

  <!-- Bootstrap -->
  <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="bootstrap/datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
  <link href="bootstrap/css/myStyle.css" rel="stylesheet" media="screen">

</head>
<body>
  <div class="container">

    <%--Header--%>
    <div class="center-block">
      <div class="jumbotron">
        <div class="text-center">
          <h2>Add Bug Record here!</h2>
        </div>
      </div>
    </div>

    <%--Pick Bug time--%>
    <div class="row">
      <div class="form-group col-md-6 col-md-offset-3">
        <label for="dtp_input1" class="col-md-3 control-label">Bug Time: </label>
        <div class="input-group date form_datetime col-md-6" data-date="1979-09-16T05:25:07Z" data-date-format="dd MM yyyy - HH:ii p" data-link-field="dtp_input1">
          <input class="form-control" size="20" type="text" value="" readonly>
          <%--<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>--%>
          <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
        </div>
        <input type="hidden" id="dtp_input1" value="" /><br/>
      </div>
    </div>


    <%--Pick Importance--%>
    <div class="row">
      <div class="form-group col-md-6 col-md-offset-3">
        <label class="col-md-3 control-label">Importance: </label>
        <div class="input-group col-md-6">
          <select id="importance_picker" class="form-control">
            <option value="Trivial">Trivial</option>
            <option value="Warning">Warning</option>
            <option value="Error">Error</option>
            <option value="Critical">Critical</option>
          </select>
        </div>
      </div>
    </div>


    <%--Add Source--%>
    <div class="row">
      <div class="form-group col-md-6 col-md-offset-3">
        <label class="col-md-3 control-label">Source: </label>
        <div  class="input-group col-md-6">
          <input id="source_input" type="text" class="form-control">
        </div>
      </div>
    </div>


    <%--Add Message  --%>
    <div class="row">
      <div class="form-group col-md-6 col-md-offset-3">
        <label class="col-md-3 control-label">Message: </label>
        <div class="input-group col-md-6">
          <input id="message_input" type="text" class="form-control">
        </div>
      </div>
    </div>


    <%--Submit Button--%>
    <button id="submit_btn" class="btn center-block" type="submit">Add Bug!</button>

  </div><%--container--%>



  <!-- Include all compiled plugins (below), or include individual files as needed -->
  <script src="bootstrap/js/jquery-1.11.2.min.js"></script>
  <script src="bootstrap/js/bootstrap.min.js"></script>
  <script src="bootstrap/datetimepicker/js/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>

  <script type="text/javascript">
    $('.form_datetime').datetimepicker({
      //language:  'fr',
      format: "yyyy-MM-dd HH:mm:ss",
      weekStart: 1,
      todayBtn:  1,
      autoclose: 1,
      todayHighlight: 1,
      startView: 2,
      forceParse: 0,
      showMeridian: 1

    });


    $('#submit_btn').on('click' , function() {
      var dateStr = $('.form_datetime').datetimepicker('getDate');
      alert(dateStr);
      var importanceStr = $('#importance_picker option:selected').val();
      var sourceStr = $('#source_input').val();
      var messageStr = $('#message_input').val();

      $.post("http://localhost:8090/addBug.do", {date: dateStr, importance: importanceStr,
                                                source: sourceStr, message: messageStr});
//      var request = new XMLHttpRequest();
//      request.open("POST", "http://localhost:8090/mock.do?stringParameter=" + dateStr);
//      request.send();

    });



  </script>

</body>
</html>
