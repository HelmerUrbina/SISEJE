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
        <script src="javascript/script.js" type="text/javascript"></script>
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
                $('#cbo_Mes').selectpicker('val', pad(date.getMonth() + 1, 2));
            });
            function fn_verReporte() {
                var msg = "";
                var periodo = $('#cbo_Periodo').val();
                var mes = $('#cbo_Mes').val();
                var tipo = $('#cbo_Tipo').val();
                var codigo = $('#cbo_Conceptos').val();
                var codigo2 = $('#cbo_TipoPersonal').val();
                var reporte = $('input:radio[name=reporte]:checked').val();
                if (reporte === 'RESO0007') {
                    var url = 'Reportes?reporte=' + reporte + '&periodo=' + periodo + '&mes=' + mes + '&tipo=' + tipo + '&codigo=' + $('#cbo_TipoPersonal').val();
                    window.open(url, '_blank');
                } else if (reporte === 'RESO0008') {
                    var url = 'Reportes?reporte=' + reporte + '&periodo=' + periodo + '&mes=' + mes + '&tipo=' + tipo + '&codigo=' + $('#cbo_TipoPersonal').val() + '&codigo2=' + $('#cbo_Planilla').val();
                    window.open(url, '_blank');
                    var url = 'Reportes?reporte=RESO0009&periodo=' + periodo + '&mes=' + mes + '&tipo=' + tipo + '&codigo=' + $('#cbo_TipoPersonal').val() + '&codigo2=' + $('#cbo_Planilla').val();
                    window.open(url, '_blank');
                    var url = 'Reportes?reporte=RESO0021&periodo=' + periodo + '&mes=' + mes + '&tipo=' + tipo + '&codigo=' + $('#cbo_TipoPersonal').val() + '&codigo2=' + $('#cbo_Planilla').val();
                    window.open(url, '_blank');
                    var url = 'Reportes?reporte=RESO0010&periodo=' + periodo + '&mes=' + mes + '&tipo=' + tipo + '&codigo=' + $('#cbo_TipoPersonal').val() + '&codigo2=' + $('#cbo_Planilla').val();
                    window.open(url, '_blank');
                    var url = 'Reportes?reporte=RESO0011&periodo=' + periodo + '&mes=' + mes + '&tipo=' + tipo + '&codigo=' + $('#cbo_TipoPersonal').val() + '&codigo2=' + $('#cbo_Planilla').val();
                    window.open(url, '_blank');
                } else if (reporte === 'RESO0009') {
                    var url = 'Reportes?reporte=RESO0014&periodo=' + periodo + '&mes=' + mes + '&tipo=' + tipo + '&codigo=' + $('#cbo_TipoPersonal').val() + '&codigo2=' + $('#cbo_PlanillaMCPP').val();
                    window.open(url, '_blank');
                    var url = 'Reportes?reporte=RESO0012&periodo=' + periodo + '&mes=' + mes + '&tipo=' + tipo + '&codigo=' + $('#cbo_TipoPersonal').val() + '&codigo2=' + $('#cbo_PlanillaMCPP').val();
                    window.open(url, '_blank');
                } else if (reporte === 'RESO0010') {
                    var url = 'Reportes?reporte=RESO0014&periodo=' + periodo + '&mes=' + mes + '&tipo=' + tipo + '&codigo=' + $('#cbo_TipoPersonal').val() + '&codigo2=' + $('#cbo_PlanillaMCPP').val();
                    window.open(url, '_blank');
                    var url = 'Reportes?reporte=RESO0013&periodo=' + periodo + '&mes=' + mes + '&tipo=' + tipo + '&codigo=' + $('#cbo_TipoPersonal').val() + '&codigo2=' + $('#cbo_PlanillaMCPP').val();
                    window.open(url, '_blank');
                } else if (reporte === 'RESO0011') {
                    var url = 'Reportes?reporte=RESO0016&periodo=' + periodo + '&mes=' + mes + '&tipo=' + tipo + '&codigo=' + $('#cbo_TipoPersonal').val();
                    window.open(url, '_blank');
                    var url = 'Reportes?reporte=RESO0015&periodo=' + periodo + '&mes=' + mes + '&tipo=' + tipo + '&codigo=' + $('#cbo_TipoPersonal').val();
                    window.open(url, '_blank');
                } else if (reporte === 'RESO0012') {
                    var url = 'Reportes?reporte=RESO0017&periodo=' + periodo + '&mes=' + mes + '&tipo=' + tipo + '&codigo=' + $('#cbo_TipoPersonal').val() + '&codigo2=' + $('#cbo_PlanillaMCPP').val();
                    window.open(url, '_blank');
                    var url = 'Reportes?reporte=RESO0018&periodo=' + periodo + '&mes=' + mes + '&tipo=' + tipo + '&codigo=' + $('#cbo_TipoPersonal').val() + '&codigo2=' + $('#cbo_PlanillaMCPP').val();
                    window.open(url, '_blank');
                }else if (reporte === 'RESO0013') {
                    var url = 'Reportes?reporte=RESO0020&periodo=' + periodo + '&mes=' + mes + '&tipo=' + tipo + '&codigo=' + $('#cbo_TipoPersonal').val() + '&codigo2=' + $('#cbo_Planilla').val();
                    window.open(url, '_blank');
                    var url = 'Reportes?reporte=RESO0019&periodo=' + periodo + '&mes=' + mes + '&tipo=' + tipo + '&codigo=' + $('#cbo_TipoPersonal').val() + '&codigo2=' + $('#cbo_Planilla').val();
                    window.open(url, '_blank');
                } else {
                    var url = 'Reportes?reporte=' + reporte + '&periodo=' + periodo + '&mes=' + mes + '&tipo=' + tipo + '&codigo=' + codigo + '&codigo2=' + codigo2;
                    window.open(url, '_blank');
                }
            }
            function fn_cargarConceptos() {
                var periodo = $('#cbo_Periodo').val();
                var mes = $('#cbo_Mes').val();
                var tipo = $('#cbo_Tipo').val();
                $.ajax({
                    type: "GET",
                    url: "Combos",
                    data: {accion: 'CONCEPTO_PLANILLA', periodo: periodo, mes: mes, tipo: tipo},
                    success: function (data) {
                        $('#cbo_Conceptos').empty();
                        $('#cbo_Conceptos').append('<option value="00">TODOS</option>');
                        $('#cbo_Conceptos').append(data);
                        $('#cbo_Conceptos').selectpicker('refresh');
                    }
                });
            }
            function fn_cargarPlanilla() {
                var periodo = $('#cbo_Periodo').val();
                var mes = $('#cbo_Mes').val();
                var tipo = $('#cbo_Tipo').val();
                var tipoPersonal = $('#cbo_TipoPersonal').val();
                $.ajax({
                    type: "GET",
                    url: "Combos",
                    data: {accion: 'TIPO_PERSONAL_PLANILLA', periodo: periodo, mes: mes, tipo: tipo, tipoPersonal: tipoPersonal},
                    success: function (data) {
                        $('#cbo_Planilla').empty();
                        //$('#cbo_Planilla').append('<option value="0">Se</option>');
                        $('#cbo_Planilla').append(data);
                        $('#cbo_Planilla').selectpicker('refresh');
                        fn_cargarPlanillaMCPP();
                    }
                });
            }
            function fn_cargarPlanillaMCPP() {
                var periodo = $('#cbo_Periodo').val();
                var mes = $('#cbo_Mes').val();
                var tipo = $('#cbo_Tipo').val();
                var tipoPersonal = $('#cbo_TipoPersonal').val();
                $.ajax({
                    type: "GET",
                    url: "Combos",
                    data: {accion: 'TIPO_PERSONAL_PLANILLA_MCPP', periodo: periodo, mes: mes, tipo: tipo, tipoPersonal: tipoPersonal},
                    success: function (data) {
                        $('#cbo_PlanillaMCPP').empty();
                        //$('#cbo_Planilla').append('<option value="0">Se</option>');
                        $('#cbo_PlanillaMCPP').append(data);
                        $('#cbo_PlanillaMCPP').selectpicker('refresh');
                    }
                });
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
                                        <select id="cbo_Periodo" class="form-control" onchange="javascript: fn_cargarConceptos();">
                                            <option value="0">Seleccione</option>
                                            <c:forEach var="a" items="${objPeriodos}">
                                                <option value="${a.codigo}">${a.descripcion}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-sm-1 form-control-label">
                                        <label for="numero">Mes</label>
                                    </div>
                                    <div class="col-sm-2">
                                        <select id="cbo_Mes" class="form-control" onchange="javascript: fn_cargarConceptos();">
                                            <option value="01">Enero</option>
                                            <option value="02">Febrero</option>
                                            <option value="03">Marzo</option>
                                            <option value="04">Abril</option>
                                            <option value="05">Mayo</option>
                                            <option value="06">Junio</option>
                                            <option value="07">Julio</option>
                                            <option value="08">Agosto</option>
                                            <option value="09">Setiembre</option>
                                            <option value="10">Octubre</option>
                                            <option value="11">Noviembre</option>
                                            <option value="12">Diciembre</option>
                                        </select>
                                    </div>
                                    <div class="col-sm-1 form-control-label">
                                        <label for="Tipo">Tipo</label>
                                    </div>
                                    <div class="col-sm-3">
                                        <select id="cbo_Tipo" class="form-control" onchange="javascript: fn_cargarConceptos();">
                                            <option value="0">Seleccione</option>
                                            <c:forEach var="b" items="${objTipo}">
                                                <option value="${b.codigo}">${b.descripcion}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="row clearfix">
                                    <div class="col-sm-1 form-control-label">
                                        <label for="Concepto">Concepto</label>
                                    </div>
                                    <div class="col-sm-3">
                                        <select id="cbo_Conceptos" class="form-control">
                                            <option value="00">TODOS</option>
                                        </select>
                                    </div>
                                    <div class="col-sm-1 form-control-label">
                                        <label for="Personal">Personal</label>
                                    </div>
                                    <div class="col-sm-3">
                                        <select id="cbo_TipoPersonal" class="form-control" onchange="javascript: fn_cargarPlanilla();">
                                            <option value="0">Seleccione</option>
                                            <option value="1">Actividad</option>
                                            <option value="2">Pensionista</option>
                                        </select>
                                    </div>
                                    <div class="col-sm-1 form-control-label">
                                        <label for="Planilla">Planilla</label>
                                    </div>
                                    <div class="col-sm-3">
                                        <select id="cbo_Planilla" class="form-control">
                                            <option value="0">Seleccione</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row clearfix">
                                    <div class="col-sm-1 form-control-label">
                                        <label for="PlanillaMCPP">MCPP</label>
                                    </div>
                                    <div class="col-sm-2">
                                        <select id="cbo_PlanillaMCPP" class="form-control">
                                            <option value="0">Seleccione</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="body"> 
                                <!-- Tab panes -->
                                <div class="tab-content">
                                    <div role="tabpanel" class="tab-pane fade in active" id="ListarReportes">
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
                                                                        <input name="reporte" type="radio" id="RESO0002" class="with-gap" value="RESO0002"/>
                                                                        <label for="RESO0002">Asignaciones Judiciales</label>
                                                                    </button>
                                                                    <button type="button" class="list-group-item">
                                                                        <input name="reporte" type="radio" id="RESO0003" class="with-gap" value="RESO0003"/>
                                                                        <label for="RESO0003">Asignaciones Judiciales (Resumen)</label>
                                                                    </button>
                                                                    <button type="button" class="list-group-item">
                                                                        <input name="reporte" type="radio" id="RESO0004" class="with-gap" value="RESO0004"/>
                                                                        <label for="RESO0004">Relación de Beneficiarios</label>
                                                                    </button>
                                                                    <button type="button" class="list-group-item">
                                                                        <input name="reporte" type="radio" id="RESO0005" class="with-gap" value="RESO0005"/>
                                                                        <label for="RESO0005">Relación por Conceptos</label>
                                                                    </button>
                                                                    <button type="button" class="list-group-item">
                                                                        <input name="reporte" type="radio" id="RESO0006" class="with-gap" value="RESO0006"/>
                                                                        <label for="RESO0006">Relación por Banco</label>
                                                                    </button>
                                                                    <button type="button" class="list-group-item">
                                                                        <input name="reporte" type="radio" id="RESO0007" class="with-gap" value="RESO0007"/>
                                                                        <label for="RESO0007">Cuadro de Descuento - Pensionista</label>
                                                                    </button>
                                                                    <button type="button" class="list-group-item">
                                                                        <input name="reporte" type="radio" id="RESO0008" class="with-gap" value="RESO0008"/>
                                                                        <label for="RESO0008">Cuadro de Descuento (Detallado - Pensionista)</label>
                                                                    </button>
                                                                    <button type="button" class="list-group-item">
                                                                        <input name="reporte" type="radio" id="RESO0013" class="with-gap" value="RESO0013"/>
                                                                        <label for="RESO0013">Cuadro de Beneficiario - Pensionista</label>
                                                                    </button>
                                                                    <button type="button" class="list-group-item">
                                                                        <input name="reporte" type="radio" id="RESO0009" class="with-gap" value="RESO0009"/>
                                                                        <label for="RESO0009">Cuadro por Cuentas - Actividad</label>
                                                                    </button>
                                                                    <button type="button" class="list-group-item">
                                                                        <input name="reporte" type="radio" id="RESO0010" class="with-gap" value="RESO0010"/>
                                                                        <label for="RESO0010">Cuadro por Telegiro - Actividad</label>
                                                                    </button>
                                                                    <button type="button" class="list-group-item">
                                                                        <input name="reporte" type="radio" id="RESO0011" class="with-gap" value="RESO0011"/>
                                                                        <label for="RESO0011">Cuadro por Juzgado - Actividad</label>
                                                                    </button>
                                                                    <button type="button" class="list-group-item">
                                                                        <input name="reporte" type="radio" id="RESO0012" class="with-gap" value="RESO0012"/>
                                                                        <label for="RESO0012">Cuadro Responsabilidad - Actividad</label>
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