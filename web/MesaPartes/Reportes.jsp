<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="../WEB-INF/jspf/aaadmin.jspf" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <title>Sistema de Sentencias Judidiciales del Ejercito</title>
        <link rel="icon" href="favicon.ico" type="image/x-icon">        
        <!-- Fuentes de Google -->
        <link href="css/cyrillic-ext.css" rel="stylesheet" type="text/css"/>
        <link href="css/fallback.css" rel="stylesheet" type="text/css"/>
        <!-- Bootstrap Core Css -->
        <link href="plugins/bootstrap/css/bootstrap.css" rel="stylesheet">
        <!-- Waves Effect Css -->
        <link href="plugins/node-waves/waves.css" rel="stylesheet" />
        <!-- Animation Css -->
        <link href="plugins/animate-css/animate.css" rel="stylesheet" />
        <!-- Sweet Alert Css -->
        <link href="plugins/sweetalert/sweetalert.css" rel="stylesheet" />
        <!-- Bootstrap Select Css (Para usar los select) -->
        <link href="plugins/bootstrap-select/css/bootstrap-select.css" rel="stylesheet" />
        <!-- Custom Css -->
        <link href="css/style.css" rel="stylesheet">
        <!-- JQuery DataTable Css -->
        <link href="plugins/jquery-datatable/skin/bootstrap/css/dataTables.bootstrap.css" rel="stylesheet">
        <!-- Escoge el color de los temas (Todos) -->
        <link href="css/themes/all-themes.css" rel="stylesheet" />       
        <link href="plugins/sweetalert/sweetalert.css" rel="stylesheet" type="text/css"/>
        <script src="javascript/jquery.js" type="text/javascript"></script>
        <!-- Jquery Core Js -->
        <script src="plugins/jquery/jquery.min.js"></script>
        <!-- Bootstrap Core Js -->
        <script src="plugins/bootstrap/js/bootstrap.js"></script>
        <!-- Bootstrap Notify Plugin Js -->
        <script src="plugins/bootstrap-notify/bootstrap-notify.js"></script>
        <!-- Select Plugin Js -->
        <script src="plugins/bootstrap-select/js/bootstrap-select.js"></script>
        <!-- Slimscroll Plugin Js -->
        <script src="plugins/jquery-slimscroll/jquery.slimscroll.js"></script>
        <!-- Select Plugin Js -->
        <script src="plugins/bootstrap-select/js/bootstrap-select.js"></script>
        <!-- Waves Effect Plugin Js -->
        <script src="plugins/node-waves/waves.js"></script>
        <!-- Sweet Alert Plugin Js -->
        <script src="plugins/sweetalert/sweetalert.min.js"></script>
        <!-- Jquery Validation Plugin Css -->
        <script src="plugins/jquery-validation/jquery.validate.js"></script>
        <!-- Jquery DataTable Plugin Js -->
        <script src="plugins/jquery-datatable/jquery.dataTables.js"></script>
        <script src="plugins/jquery-datatable/skin/bootstrap/js/dataTables.bootstrap.js"></script>
        <script src="plugins/jquery-datatable/extensions/export/dataTables.buttons.min.js"></script>
        <script src="plugins/jquery-datatable/extensions/export/buttons.flash.min.js"></script>
        <script src="plugins/jquery-datatable/extensions/export/jszip.min.js"></script>
        <script src="plugins/jquery-datatable/extensions/export/pdfmake.min.js"></script>
        <script src="plugins/jquery-datatable/extensions/export/vfs_fonts.js"></script>
        <script src="plugins/jquery-datatable/extensions/export/buttons.html5.min.js"></script>
        <script src="plugins/jquery-datatable/extensions/export/buttons.print.min.js"></script>
        <!-- Custom Js -->
        <script src="javascript/admin.js"></script>
        <script src="javascript/pages/forms/form-validation.js"></script>
        <script src="javascript/pages/tables/jquery-datatable.js"></script>
        <script src="javascript/pages/ui/dialogs.js"></script>
        <!-- Demo Js -->
        <script src="javascript/demo.js"></script>
        <script type="text/javascript">
            var date = new Date();
            var archivo = null;
            var flag = null;
            var codigo = null;
            var mode = null;
            $(document).ready(function () {
                $('#cbo_Periodo').selectpicker('val', date.getFullYear());
            });
            function fn_verReporte() {
                var msg = "";
                var periodo = $('#cbo_Periodo').val();
                var tipo = $('#cbo_Tipo').val();
                var codigo = $('#txt_FechaInicio').val();
                var codigo2 = $('#txt_FechaFinal').val();
                var reporte = $('input:radio[name=reporte]:checked').val();
                var url = 'Reportes?reporte=' + reporte + '&periodo=' + periodo + '&tipo=' + tipo + '&codigo=' + codigo + '&codigo2=' + codigo2;
                window.open(url, '_blank');
            }
        </script>
    </head>
    <body class="theme-teal">
        <%@include file="../Login/PaginaCarga.jspf" %>
        <!-- Muestra el contenido -->
        <section class="content">
            <div class="container-fluid">
                <div class="block-header">
                    <h3><strong>Reportes</strong></h3>
                </div>
                <!-- Tabs With Icon Title -->
                <div class="row clearfix">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="card">
                            <div class="header">
                                <div class="row clearfix">
                                    <div class="col-sm-1 form-control-label">
                                        <label for="Periodo">Periodo</label>
                                    </div>
                                    <div class="col-sm-2">
                                        <select id="cbo_Periodo" class="form-control" onchange="javascript: fn_BuscarDatos();">
                                            <option value="0">Seleccione</option>
                                            <c:forEach var="a" items="${objPeriodos}">
                                                <option value="${a.codigo}">${a.descripcion}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-sm-1 form-control-label">
                                        <label for="Tipo">Tipo</label>
                                    </div>
                                    <div class="col-sm-3">
                                        <select id="cbo_Tipo" class="form-control" onchange="javascript: fn_BuscarDatos();">
                                            <option value="0">Seleccione</option>
                                            <c:forEach var="b" items="${objTipo}">
                                                <option value="${b.codigo}">${b.descripcion}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="row clearfix">
                                    <div class="col-sm-1 form-control-label">
                                        <label for="FechaInicio">Del : </label>
                                    </div>
                                    <div class="col-sm-2"> 
                                        <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="material-icons">date_range</i>
                                            </span>
                                            <div class="form-line">
                                                <input type="date" id="txt_FechaInicio" class="form-control" placeholder="DD/MM/YYYY" maxlength="10">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-1 form-control-label">
                                        <label for="FechaFinal">Al : </label>
                                    </div>
                                    <div class="col-sm-2"> 
                                        <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="material-icons">date_range</i>
                                            </span>
                                            <div class="form-line">
                                                <input type="date" id="txt_FechaFinal" class="form-control" placeholder="DD/MM/YYYY" maxlength="10">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="body"> 
                                <!-- Tab panes -->
                                <div class="tab-content">
                                    <div role="tabpanel" class="tab-pane fade in active" id="ListarPrioridad">
                                        <!-- Striped Rows -->
                                        <div class="row clearfix">
                                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                <div class="card">
                                                    <div class="header bg-teal">
                                                        <h2 class="col-md-10 col-sm-8 col-xs-8">
                                                            <strong>Listado de Reportes</strong>
                                                        </h2>
                                                        <button type="button" class="btn btn-primary waves-effect btn-sm col-md-2 col-sm-2 col-xs-2" onclick="javascript:fn_verReporte();">
                                                            <i class="material-icons">print</i>
                                                            <span>Reportar</span>
                                                        </button>
                                                        <br>
                                                    </div>
                                                    <!-- Button Items -->
                                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                        <div class="card">
                                                            <div class="list-group">
                                                                <div class="demo-radio-button">
                                                                    <button type="button" class="list-group-item">
                                                                        <input name="reporte" type="radio" id="MPAR0002" class="with-gap" value="MPAR0002"/>
                                                                        <label for="MPAR0002">Documentos Decretados</label>
                                                                    </button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- #END# Button Items -->
                                                </div>
                                            </div>
                                        </div>
                                        <!-- #END# Striped Rows -->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- #END# Tabs With Icon Title -->
                </div>
            </div>
        </section>
    </body>
</html>