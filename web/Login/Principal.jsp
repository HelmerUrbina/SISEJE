
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="../WEB-INF/jspf/aaadmin.jspf" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <title>Sistema de Sentencias Judidiciales del Ejercito</title>
        <link rel="icon" href="../favicon.ico" type="image/x-icon">
        <link href="../css/cyrillic-ext.css" rel="stylesheet" type="text/css"/>
        <link href="../css/fallback.css" rel="stylesheet" type="text/css"/>        
        <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="../plugins/node-waves/waves.css" rel="stylesheet" type="text/css"/>
        <link href="../plugins/animate-css/animate.css" rel="stylesheet" type="text/css"/>
        <link href="../plugins/sweetalert/sweetalert.css" rel="stylesheet" type="text/css"/>
        <link href="../css/style.css" rel="stylesheet"/>
        <link href="../css/themes/all-themes.css" rel="stylesheet" type="text/css"/>
        <script src="../plugins/jquery/jquery.min.js" type="text/javascript"></script>
        <script src="../plugins/bootstrap/js/bootstrap.js" type="text/javascript"></script>
        <script src="../plugins/bootstrap-select/js/bootstrap-select.js" type="text/javascript"></script>
        <script src="../plugins/jquery-slimscroll/jquery.slimscroll.js" type="text/javascript"></script>
        <script src="../plugins/node-waves/waves.js" type="text/javascript"></script>
        <script src="../plugins/sweetalert/sweetalert.min.js" type="text/javascript"></script>        
        <script src="../plugins/jquery-countto/jquery.countTo.js" type="text/javascript"></script>
        <script src="../javascript/admin.js" type="text/javascript"></script>
        <script src="../javascript/demo.js" type="text/javascript"></script>
    </head>
    <body class="theme-teal">
        <!-- Pagina de Carga (efecto) -->
        <%@include file="../Login/PaginaCarga.jspf" %>
        <!-- Muestra el contenido -->
        <section class="content">
            <div class="container-fluid">
                <%@include file="Presentacion.jspf"%>
            </div>
        </section>
    </body>
</html>
