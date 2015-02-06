<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>미래식당 관리자</title>
  <link href="/css/plugins/bootstrap.min.css" rel="stylesheet">
  <link href="/css/login.css" rel="stylesheet">

</head>

<body>

<div class="container">

  <form class="form-signin" name="form" method="post" action="/admin/loginProcess">
    <h2 class="form-signin-heading">미래식당 관리자</h2>
    <label for="inputId" class="sr-only">ID</label>
    <input type="text" id="inputId" class="form-control" name="id" placeholder="ID" required autofocus>
    <label for="inputPassword" class="sr-only">Password</label>
    <input type="password" id="inputPassword" class="form-control" name="pw" placeholder="Password" required>
    <%--<div class="checkbox">--%>
      <%--<label>--%>
        <%--<input type="checkbox" value="remember-me"> Remember me--%>
      <%--</label>--%>
    <%--</div>--%>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button>
  </form>

</div>

</body>
</html>

