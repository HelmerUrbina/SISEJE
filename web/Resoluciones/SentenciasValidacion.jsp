<%-- 
    Document   : SentenciasValidacion
    Created on : 10/06/2020, 10:34:26 PM
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
                var personal = $('#cbo_Personal').val();
                var $contenidoAjax = $('#TablaResultados').html('<img src="../Imagenes/cargando.gif" height="800" width="500" />');
                $.ajax({
                    type: "GET",
                    url: "SentenciasMovimientoValidacion",
                    data: {mode: 'G', periodo: periodo, mes: mes, tipo: tipo, personal: personal},
                    success: function (data) {
                        $contenidoAjax.html(data);
                        $('[data-toggle="tooltip"]').tooltip();
                    }
                });
            }
            function fn_ExportarExcel() {
                var periodo = $('#cbo_Periodo').val();
                var mes = $('#cbo_Mes').val();
                var tipo = $('#cbo_Tipo').val();
                var personal = $('#cbo_Personal').val();
                var url = 'SentenciasMovimientoValidacion?mode=E&periodo=' + periodo + '&mes=' + mes + '&tipo=' + tipo + '&personal=' + personal;
                window.open(url, '_blank');
            }
            function fn_ImportarExcel() {
                flag = ".xlsx";
                $('#txt_Archivo').val('');
                $('#SubirArchivoSentenciasPlanilla .modal-content').removeAttr('class').addClass('modal-content modal-col-default');
                $('#SubirArchivoSentenciasPlanilla').modal('show');
            }
            function fn_SubirArchivo() {
                flag = fn_VerificaArchivo();
                if (flag !== '' || flag.length !== 0) {
                    swal("Aviso del Sistema", flag, "error");
                } else {
                    var msg = 'Debe Seleccionar un Archivo a subir\n Proceso cancelado!!!.';
                    var periodo = $('#cbo_Periodo').val();
                    var mes = $('#cbo_Mes').val();
                    var tipo = $('#cbo_Tipo').val();
                    var personal = $('#cbo_Personal').val();
                    var archivo = $("#txt_Archivo").val();
                    if (archivo !== '') {
                        var formData = new FormData(document.getElementById("frm_SentenciasPlanillaArchivo"));
                        formData.append("mode", 'S');
                        formData.append("periodo", periodo);
                        formData.append("mes", mes);
                        formData.append("tipo", tipo);
                        formData.append("personal", personal);
                        formData.append("flag", flag);
                        formData.append("archivo", archivo);
                        $.ajax({
                            type: "POST",
                            url: "IduResolucionesPlanillaExcel",
                            data: formData,
                            dataType: "html",
                            cache: false,
                            contentType: false,
                            processData: false,
                            success: function (data) {
                                msg = data;
                                if (msg === "GUARDO") {
                                    swal({
                                        title: "Aviso del Sistema!",
                                        text: "Datos Guardados con Exito",
                                        timer: 2000,
                                        showConfirmButton: false,
                                        imageUrl: "../Imagenes/thumbs-up.png"
                                    });
                                    $('#SubirArchivoSentenciasPlanilla').modal('hide');
                                } else {
                                    swal("Aviso del Sistema!", msg, "error");
                                }
                            }
                        });
                    } else {
                        swal("Aviso del Sistema!", msg, "error");
                    }
                }
            }
            function fn_VerificaArchivo() {
                var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.xlsx|.xls)$/;
                if (flag === ".xlsx") {
                    regex = /^([a-zA-Z0-9\s_\\.\-:])+(.xlsx|.xls)$/;
                    if (regex.test($("#txt_Archivo").val().toLowerCase())) {
                        if ($("#txt_Archivo").val().toLowerCase().indexOf(".xlsx") > 0) {
                            return '';
                        }
                    }
                    return "Por favor, cargue un archivo de Excel válido!";
                } else if (flag === ".xls") {
                    regex = /^([a-zA-Z0-9\s_\\.\-:])+(.xlsx|.xls)$/;
                    if (regex.test($("#txt_Archivo").val().toLowerCase())) {
                        if ($("#txt_Archivo").val().toLowerCase().indexOf(".xls") > 0) {
                            return '';
                        }
                    }
                    return "Por favor, cargue un archivo de Excel válido!";
                } else {
                    return "Por favor, carge un archivo válido!";
                }
            }
        </script>
    </head>
    <body class="theme-teal">
        <%@include file="../Login/PaginaCarga.jspf" %>
        <!-- Muestra el contenido -->
        <section class="content">
            <div class="container-fluid">
                <div class="block-header">
                    <h3><strong>Validación de Planillas MCPP</strong></h3>
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
                                        <label for="numero">Mes</label>
                                    </div>
                                    <div class="col-sm-2">
                                        <select id="cbo_Mes" class="form-control" onchange="javascript: fn_BuscarDatos();">
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
                                        <select id="cbo_Tipo" class="form-control" onchange="javascript: fn_BuscarDatos();">
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
                                        <label for="numero">Personal</label>
                                    </div>
                                    <div class="col-sm-2">
                                        <select id="cbo_Personal" class="form-control" onchange="javascript: fn_BuscarDatos();">
                                            <option value="0">Seleccione</option>
                                            <option value="1" selected="selected">ACTIVIDAD</option>
                                            <option value="2">PENSIONISTA</option>
                                        </select>
                                    </div>

                                </div>
                            </div>
                            <div class="body"> 
                                <!-- Tab panes -->
                                <div class="tab-content">
                                    <div role="tabpanel" class="tab-pane fade in active" id="ListarValidacionMCPP">
                                        <!-- Striped Rows -->
                                        <div class="row clearfix">
                                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                <div class="card">
                                                    <div class="header bg-teal">
                                                        <h2 class="col-md-10 col-sm-9 col-xs-8">
                                                            <strong>Listado de Registros</strong>
                                                        </h2>
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
                                    <!-- Modal Dialogs  -->
                                    <div class="modal fade" id="SubirArchivoSentenciasPlanilla" tabindex="-1" role="dialog">
                                        <div class="modal-dialog modal-lg" role="document">
                                            <div class="modal-content">
                                                <form id="frm_SentenciasPlanillaArchivo" name="frm_SentenciasPlanillaArchivo" enctype="multipart/form-data" action="javascript: fn_SubirArchivo();" method="post">
                                                    <div class="modal-header">
                                                        <h4 class="modal-title" id="defaultModalLabel">ADJUNTAR DOCUMENTO - SENTENCIAS PLANILLA MCPP</h4>
                                                    </div>
                                                    <div class="modal-body">
                                                        <div class="row clearfix">
                                                            <div class="col-sm-2 form-control-label">
                                                                <label for="archivo">Archivo</label>
                                                            </div>
                                                            <div class="col-sm-10">
                                                                <div class="form-group">
                                                                    <div class="form-line">
                                                                        <input type="file" name="txt_Archivo" id="txt_Archivo" class="form-control" maxlength="1000">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer ">
                                                        <button type="submit" class="btn bg-blue waves-effect btn-sm col-md-40" >
                                                            <i class="material-icons">save</i>
                                                            <span>Registrar</span>
                                                        </button>
                                                        <button type="button" class="btn bg-red waves-effect btn-sm col-md-50" data-dismiss="modal">
                                                            <i class="material-icons">cancel</i>
                                                            <span>Cancelar</span>
                                                        </button>
                                                    </div>
                                                </form>
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