<html>
<head>
  <meta name="layout" content="bootstraplogin">
  <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
  <title>Login</title>
</head>
<body>
  <div class="container">
    
        <g:if test="${flash.message}">
          <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
        </g:if>
      
        <form class="form-signin" action ="signIn">
          <h2 class="form-signin-heading text-info">Please sign in</h2>
          <input type="text" class="input-block-level" placeholder="User name" name="username" value="${username}">
          <input type="password" class="input-block-level" placeholder="Password" name="password" value="">
          <label class="checkbox ">
            <input type="checkbox" name="rememberMe" value="${rememberMe}"> Remember me
          </label>
          <button class="btn btn-large btn-primary" type="submit">Sign in</button>
        </form>

  </div>
</body>
</html>
