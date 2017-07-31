<#macro layout>
<!DOCTYPE>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<#-- import css -->
<link rel="stylesheet" href="/plugins/bootstrap/css/bootstrap.min.css" type="text/css" />
<#-- import js -->
<script src="/js/jquery.min.js" type="text/javascript"></script>
<script src="/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<title>spring boot</title>
</head>
<body>
<div class="wrapper">
	<header>layout header info</header>
	<#nested>
	<footer>
   <strong>layout footer info</strong>
	</footer>
</div>
</body>
</html>
</#macro>
