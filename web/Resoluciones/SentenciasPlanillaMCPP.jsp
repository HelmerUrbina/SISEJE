<%-- 
    Document   : SentenciasPlanillaMCPP
    Created on : 26/07/2020, 10:07:30 PM
    Author     : H-URBINA-M
--%>

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
            var archivo = null;
            var flag = null;
            var codigo = null;
            var mode = null;
            $(document).ready(function () {
                $('#cbo_Periodo').selectpicker('val', date.getFullYear());
                $('#cbo_Mes').selectpicker('val', pad(date.getMonth() + 1, 2));
            });
            function fn_BuscarDatos() {
                var periodo = $('#cbo_Periodo').val();
                var mes = $('#cbo_Mes').val();
                var tipo = $('#cbo_Tipo').val();
                var concepto = $('#cbo_Conceptos').val();
                var numero = $('#txt_Codigo').val();
                var $contenidoAjax = $('#TablaResultados').html('<img src="../Imagenes/cargando.gif" height="800" width="500" />');
                $.ajax({
                    type: "GET",
                    url: "SentenciasPlanillaMCPP",
                    data: {mode: 'G', periodo: periodo, mes: mes, tipo: tipo, concepto: concepto, numero: numero},
                    success: function (data) {
                        $contenidoAjax.html(data);
                        $('[data-toggle="tooltip"]').tooltip();
                    }
                });
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
                        $('#cbo_Conceptos').append(data);
                        $('#cbo_Conceptos').selectpicker('refresh');
                    }
                });
            }
            function fn_EliminarRegistro(cip, sentencia, resolucion) {
                var periodo = $('#cbo_Periodo').val();
                var mes = $('#cbo_Mes').val();
                var tipo = $('#cbo_Tipo').val();
                var concepto = $('#cbo_Conceptos').val();
                var lista = new Array();
                lista.push(cip + "---" + sentencia + "---" + resolucion);
                $.ajax({
                    type: "GET",
                    url: "IduResolucionesPlanillas",
                    data: {periodo: periodo, mes: mes, tipo: tipo, concepto: concepto, planilla: 0, lista: JSON.stringify(lista)},
                    success: function (data) {
                        if (data === 'GUARDO') {
                            swal({
                                title: "Aviso del Sistema!",
                                text: "Datos Guardados con Exito",
                                timer: 2000,
                                showConfirmButton: false,
                                imageUrl: "../Imagenes/thumbs-up.png"
                            });
                            fn_BuscarDatos();
                        } else {
                            swal("Aviso del Sistema!", data, "error");
                        }
                    }
                });
            }
            function fn_AñadirMovimiento() {
                var periodo = $('#cbo_Periodo').val();
                var mes = $('#cbo_Mes').val();
                var tipo = $('#cbo_Tipo').val();
                var concepto = $('#cbo_Conceptos').val();
                var numero = $('#txt_Codigo').val();
                if (concepto !== '0' && numero !== '0') {
                    $('#BuscarSentenciaMovimiento .modal-content').removeAttr('class').addClass('modal-content modal-col-default');
                    $('#BuscarSentenciaMovimiento').modal('show');
                    $('#DetalleSentenciaMovimiento').empty();
                    $.ajax({
                        type: "GET",
                        url: "SentenciasPlanillaMCPP",
                        data: {mode: 'B', periodo: periodo, mes: mes, tipo: tipo, concepto: concepto, numero: 0},
                        success: function (data) {
                            $('#DetalleSentenciaMovimiento').html(data);
                            $('#TablaBuscarPersonal').DataTable();
                        }
                    });
                } else {
                    swal("Aviso del Sistema!", "No se puede realizar esta operación", "error");
                }
            }
            function fn_AnadirSentencias() {
                var periodo = $('#cbo_Periodo').val();
                var mes = $('#cbo_Mes').val();
                var tipo = $('#cbo_Tipo').val();
                var concepto = $('#cbo_Conceptos').val();
                var numero = $('#txt_Codigo').val();
                var lista = new Array();
                $("input[type=checkbox]:checked").each(function () {
                    lista.push($(this).val());
                });
                $.ajax({
                    type: "GET",
                    url: "IduResolucionesPlanillas",
                    data: {periodo: periodo, mes: mes, tipo: tipo, concepto: concepto, planilla: numero, lista: JSON.stringify(lista)},
                    success: function (data) {
                        if (data === 'GUARDO') {
                            swal({
                                title: "Aviso del Sistema!",
                                text: "Datos Guardados con Exito",
                                timer: 2000,
                                showConfirmButton: false,
                                imageUrl: "../Imagenes/thumbs-up.png"
                            });
                            $('#BuscarSentenciaMovimiento .modal-content').removeAttr('class').addClass('modal-content modal-col-default');
                            $('#BuscarSentenciaMovimiento').modal('hide');
                            fn_BuscarDatos();
                        } else {
                            swal("Aviso del Sistema!", data, "error");
                        }
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
                    <h3><strong>Registro de Planilla MCPP</strong></h3>
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
                                        <select id="cbo_Periodo" class="form-control" >
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
                                        <select id="cbo_Mes" class="form-control" >
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
                                    <div>
                                        <button type="button" class="btn btn-primary waves-effect btn-sm col-md-1 col-sm-1 col-xs-1" onclick="javascript: fn_BuscarDatos();">
                                            <i class="material-icons">search</i>
                                            <span>Buscar</span>
                                        </button>
                                    </div>
                                </div>
                                <div class="row clearfix">
                                    <div class="col-sm-1 form-control-label">
                                        <label for="Concepto">Concepto</label>
                                    </div>
                                    <div class="col-sm-5">
                                        <select id="cbo_Conceptos" class="form-control" >
                                            <option value="0">Seleccione</option>
                                        </select>
                                    </div>
                                    <div class="col-sm-1 form-control-label">
                                        <label for="Planilla">Planilla</label>
                                    </div>
                                    <div class="col-sm-2">
                                        <div class="form-group">
                                            <div class="form-line">
                                                <input type="number" id="txt_Codigo" class="form-control" value="0" onchange="javascript: fn_BuscarDatos();">
                                            </div>
                                        </div>
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
                                                            <strong>Listado de Planilla MCPP</strong>
                                                        </h2>
                                                        <button type="button" class="btn btn-success waves-effect btn-sm col-md-2 col-sm-3 col-xs-4" onclick="javascript: fn_AñadirMovimiento();">
                                                            <i class="material-icons">add </i>
                                                            <span>Añadir</span>
                                                        </button>
                                                        <br>
                                                    </div>
                                                    <div class="body">
                                                        <div id="TablaResultados">&nbsp;</div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- #END# Striped Rows -->
                                    </div>
                                </div>
                                <!-- Modal Dialogs ===================================== -->
                                <!-- Default Size -->
                                <div class="modal fade" id="BuscarSentenciaMovimiento" tabindex="-1" role="dialog">
                                    <div class="modal-dialog modal-lg" role="document">
                                        <div class="modal-content">
                                            <div class="modal-body">
                                                <div class="row clearfix">
                                                    <div class="col-lg-14 col-md-14 col-sm-14 col-xs-14">
                                                        <div class="card">
                                                            <div class="header bg-teal">
                                                                <h2 class="col-md-10 col-sm-2 col-xs-2">
                                                                    <strong>Listado de Sentencias Movimiento</strong>
                                                                </h2>
                                                                <br>
                                                            </div>
                                                            <div class="body">
                                                                <div id="DetalleSentenciaMovimiento"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="modal-footer ">
                                                <button type="button" class="btn bg-success waves-effect btn-sm col-md-40" onclick="javascript: fn_AnadirSentencias();">
                                                    <i class="material-icons">check_circle</i>
                                                    <span>Seleccionar</span>
                                                </button>&nbsp;
                                                <button type="button" class="btn bg-orange waves-effect btn-sm col-md-50" data-dismiss="modal">
                                                    <i class="material-icons">cancel </i>
                                                    <span>Cancelar</span>
                                                </button>
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
