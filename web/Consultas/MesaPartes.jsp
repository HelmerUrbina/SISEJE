
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="../WEB-INF/jspf/aaadmin.jspf" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <title>Sistema de Sentencias Judiciales del Ejercito</title>
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
        <!-- Colorpicker Css -->
        <link href="plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.css" rel="stylesheet" />
        <!-- Bootstrap Spinner Css -->
        <link href="plugins/jquery-spinner/css/bootstrap-spinner.css" rel="stylesheet">
        <!-- Bootstrap Tagsinput Css -->
        <link href="plugins/bootstrap-tagsinput/bootstrap-tagsinput.css" rel="stylesheet">
        <!-- Bootstrap Select Css -->
        <link href="plugins/bootstrap-select/css/bootstrap-select.min.css" rel="stylesheet" />
        <!-- noUISlider Css -->
        <link href="plugins/nouislider/nouislider.min.css" rel="stylesheet" type="text/css"/>
        <!-- Bootstrap Tagsinput Css -->
        <link href="plugins/bootstrap-tagsinput/bootstrap-tagsinput.css" rel="stylesheet">
        <!-- Sweet Alert Css -->
        <link href="plugins/sweetalert/sweetalert.css" rel="stylesheet" />
        <!-- Bootstrap Select Css (Para usar los select) -->
        <link href="plugins/bootstrap-select/css/bootstrap-select.css" rel="stylesheet" />
        <!-- Bootstrap Material Datetime Picker Css -->
        <link href="plugins/bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.css" rel="stylesheet" />
        <link href="plugins/bootstrap-datepicker/css/bootstrap-datepicker.css" rel="stylesheet" type="text/css"/>
        <!-- Wait Me Css -->
        <link href="plugins/waitme/waitMe.css" rel="stylesheet" />
        <!-- Custom Css -->
        <link href="css/style.css" rel="stylesheet">
        <!-- JQuery DataTable Css -->
        <link href="plugins/jquery-datatable/skin/bootstrap/css/dataTables.bootstrap.css" rel="stylesheet">
        <!-- Escoge el color de los temas (Todos) -->
        <link href="css/themes/all-themes.css" rel="stylesheet" />
        <link href="css/datatables.css" rel="stylesheet">
        <link href="plugins/sweetalert/sweetalert.css" rel="stylesheet" type="text/css"/>
        <script src="javascript/jquery.js" type="text/javascript"></script>
        <!-- Jquery Core Js -->
        <script src="plugins/jquery/jquery.min.js"></script>
        <!-- Bootstrap Core Js -->
        <script src="plugins/bootstrap/js/bootstrap.js"></script>
        <!-- Bootstrap Notify Plugin Js -->
        <script src="plugins/bootstrap-notify/bootstrap-notify.js"></script>
        <!-- Select Plugin Js -->
        <script src="plugins/bootstrap-select/js/bootstrap-select.min.js"></script>
        <!-- Slimscroll Plugin Js -->
        <script src="plugins/jquery-slimscroll/jquery.slimscroll.js"></script>
        <!-- Sweet Alert Plugin Js -->
        <script src="plugins/sweetalert/sweetalert.min.js"></script>
        <!-- Jquery Validation Plugin Css -->
        <script src="plugins/jquery-validation/jquery.validate.js"></script>
        <!-- Input Mask Plugin Js -->
        <script src="plugins/jquery-inputmask/jquery.inputmask.bundle.js"></script>
        <!-- Multi Select Plugin Js -->
        <script src="plugins/multi-select/js/jquery.multi-select.js"></script>
        <!-- Bootstrap Colorpicker Js -->
        <script src="plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
        <!-- Dropzone Plugin Js -->
        <script src="plugins/dropzone/dropzone.js"></script>
        <!-- Jquery Spinner Plugin Js -->
        <script src="plugins/jquery-spinner/js/jquery.spinner.js"></script>
        <!-- Bootstrap Tags Input Plugin Js -->
        <script src="plugins/bootstrap-tagsinput/bootstrap-tagsinput.js"></script>
        <!-- Waves Effect Plugin Js -->
        <script src="plugins/node-waves/waves.js"></script>
        <!-- Autosize Plugin Js -->
        <script src="plugins/autosize/autosize.js"></script>
        <!-- Moment Plugin Js -->
        <script src="plugins/momentjs/moment.js"></script>
        <!-- Bootstrap Material Datetime Picker Plugin Js -->
        <script src="plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js"></script>
        <!-- Jquery DataTable Plugin Js -->
        <!-- Bootstrap Datepicker Plugin Js -->
        <script src="plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
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
        <script src="javascript/datatables.js"></script>
        <script src="javascript/admin.js"></script>
        <script src="javascript/pages/forms/form-validation.js"></script>
        <script src="javascript/pages/tables/jquery-datatable.js"></script>
        <script src="javascript/pages/ui/dialogs.js"></script>
        <script src="javascript/pages/ui/tooltips-popovers.js"></script>
        <script src="javascript/pages/forms/basic-form-elements.js" type="text/javascript"></script>
        <!-- Demo Js -->
        <script src="javascript/script.js" type="text/javascript"></script>
        <script src="javascript/demo.js"></script>
        <script type="text/javascript">
            var date = new Date();
            $(document).ready(function () {
                $('#cbo_Periodo').selectpicker('val', date.getFullYear());
                $('#cbo_Mes').selectpicker('val', pad(date.getMonth() + 1, 2));
                fn_BuscarDatos();
            });
            var codigo = null;
            var mode = null;
            function fn_BuscarDatos() {
                var periodo = $('#cbo_Periodo').val();
                var mes = $('#cbo_Mes').val();
                var estado = $('#cbo_Estado').val();
                $.ajax({
                    type: "GET",
                    url: "MesaPartes",
                    data: {mode: 'C', periodo: periodo, mes: mes, estado: estado},
                    success: function (data) {
                        $('#DetalleMesaPartes').empty();
                        $('#DetalleMesaPartes').html(data);
                        $('#TablaResultados').DataTable({
                            "order": [[0, "desc"]]
                        });
                        $('[data-toggle="tooltip"]').tooltip();
                    }
                });
            }
            function fn_Descargar(codigo, archivo) {
                var periodo = $('#cbo_Periodo').val();
                if (archivo !== null && archivo !== '') {
                    document.location.target = "_blank";
                    document.location.href = "Descarga?opcion=MesaParte&periodo=" + periodo + "&codigo=" + codigo + "&documento=" + archivo;
                } else {
                    swal("Aviso del Sistema!", 'No se encuentra el Archivo!', "error");
                }
            }
            function Refrescar() {
                window.location = "Consultas?mode=mesaPartes";
            }
            function fn_Seguimiento(codigo) {
                var periodo = $('#cbo_Periodo').val();
                var mes = $('#cbo_Mes').val();
                $.ajax({
                    type: "GET",
                    url: "Decretos",
                    data: {mode: 'S', periodo: periodo, mes: mes, numero: codigo},
                    success: function (data) {
                        $('#TablaDecretos').html(data);
                    }
                });
                $('#SeguimientoDecreto .modal-content').removeAttr('class').addClass('modal-content modal-col-default');
                $('#SeguimientoDecreto').modal('show');
            }
        </script>
    </head>
    <body class="theme-teal">
        <%@include file="../Login/PaginaCarga.jspf" %>
        <!-- Muestra el contenido -->
        <section class="content">
            <div class="container-fluid">
                <div class="block-header">
                    <h3><strong>Consulta de Mesa de Partes</strong></h3>
                </div>
                <!-- Tabs With Icon Title -->
                <div class="row clearfix">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="card">
                            <div class="header">
                                <div class="row clearfix">
                                    <div class="col-sm-2 form-control-label">
                                        <label for="Periodo">Periodo</label>
                                    </div>
                                    <div class="col-sm-2">
                                        <select id="cbo_Periodo" class="form-control" onchange="javascript: fn_BuscarDatos();">
                                            <option value="0000">Todos</option>
                                            <c:forEach var="a" items="${objPeriodos}">
                                                <option value="${a.codigo}">${a.descripcion}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-sm-1 form-control-label" >
                                        <label for="Mes">Mes</label>
                                    </div>
                                    <div class="col-sm-2">
                                        <select id="cbo_Mes" class="form-control" onchange="javascript: fn_BuscarDatos();">
                                            <option value="00">Todos</option>
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
                                    <div class="col-sm-1 form-control-label" >
                                        <label for="Mes">Estado</label>
                                    </div>
                                    <div class="col-sm-2">
                                        <select id="cbo_Estado" class="form-control" onchange="javascript: fn_BuscarDatos();">
                                            <option value="00">Todos</option>
                                            <option value="AC">ACTIVOS</option>
                                            <option value="IN">INACTIVOS</option>
                                            <option value="DE">DECRETADO</option>
                                            <option value="RS">RESPONDIDO</option>
                                            <option value="GE">GENERADO</option>
                                            <option value="SD">SUB DECRETADO</option>
                                        </select>
                                    </div> 
                                    <div class="col-sm-2 form-control-label">
                                        &nbsp;
                                    </div>
                                    <div>
                                        <button type="button" class="btn btn-primary waves-effect btn-sm col-md-1 col-sm-1 col-xs-1" onclick="javascript:fn_BuscarDatos();">
                                            <i class="material-icons">search</i>
                                            <span>Buscar</span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="body"> 
                                <!-- Tab panes -->
                                <div class="tab-content">
                                    <div role="tabpanel" class="tab-pane fade in active" id="ListarMesaPartes">
                                        <!-- Striped Rows -->
                                        <div class="row clearfix">
                                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                <div class="card">
                                                    <div class="header bg-teal">
                                                        <h2 class="col-md-10 col-sm-9 col-xs-8">
                                                            <strong>Listado de Mesa de Partes</strong>
                                                        </h2>
                                                        <br>
                                                    </div>
                                                    <div class="body">
                                                        <div id="DetalleMesaPartes"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- #END# Striped Rows -->
                                    </div>
                                    <!-- Modal Dialogs  -->
                                    <!-- Default Size -->
                                    <div class="modal fade" id="SeguimientoDecreto" tabindex="-1" role="dialog">
                                        <div class="modal-dialog modal-lg" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h4 class="modal-title" id="defaultModalLabel">SEGUIMIENTO DE DOCUMENTOS</h4>
                                                </div>
                                                <div class="body table-responsive js-sweetalert">
                                                    <div id="TablaDecretos">&nbsp;</div>
                                                </div>
                                                <div class="modal-footer ">
                                                    <button type="button" class="btn bg-red waves-effect btn-sm col-md-50" data-dismiss="modal">
                                                        <i class="material-icons">cancel </i>
                                                        <span>Cerrar</span>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
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