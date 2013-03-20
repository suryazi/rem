<%@ page import="org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes" %>
<!doctype html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title><g:layoutTitle default="${meta(name: 'app.name')}"/></title>
		<meta name="description" content="">
		<meta name="author" content="">

		<meta name="viewport" content="initial-scale = 1.0">

		<!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
		<!--[if lt IE 9]>
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->

		<r:require modules="scaffolding"/>

		<!-- Le fav and touch icons -->
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="72x72" href="${resource(dir: 'images', file: 'apple-touch-icon-72x72.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-114x114.png')}">

		<g:layoutHead/>
		<r:layoutResources/>
	</head>

	<body>
          
          <div class="container">
            <div class="masthead">
              <h3 class="muted text-info">Real Estate Management</h3>
              <div class="navbar navbar-fixed-top">
                <div class="navbar-inner">
                  <div class="container">
                    <ul class="nav nav-pills">
                      <li class="active"><a href="#">Home</a></li>
                      <li><a href="#">Projects</a></li>
                      <li><a href="#">Services</a></li>
                      <li><a href="#">Downloads</a></li>
                      <li><a href="#">About</a></li>
                      <li><a href="#">Contact</a></li>
                    </ul>
                  </div>
                </div>
              </div><!-- /.navbar -->
            </div>
          </div>
		
          <div class="container-fluid">
                  <g:layoutBody/>

                  <hr>

                  <footer>
                    <rem:copyright startYear="2012">Al Woroud Real Estate Company.</rem:copyright>
                  </footer>
          </div>

          <r:layoutResources/>

	</body>
</html>