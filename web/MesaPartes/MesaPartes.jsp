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
                $('#txt_Dia').val(date.getDate());
                fn_BuscarDatos();
            });
            var codigo = null;
            var mode = null;
            function fn_BuscarDatos() {
                var periodo = $('#cbo_Periodo').val();
                var mes = $('#cbo_Mes').val();
                var dia = $('#txt_Dia').val();
                $.ajax({
                    type: "GET",
                    url: "MesaPartes",
                    data: {mode: 'B', periodo: periodo, mes: mes, dia: dia},
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
            function NuevoRegistro() {
                var periodo = $('#cbo_Periodo').val();
                mode = 'I';
                codigo = 0;
                $('#txt_Numero').val('');
                $('#cbo_TipoDocumento').selectpicker('val', '1');
                $("#txt_NumeroDocumento").val('');
                $('#txt_Indicativo').val('');
                $("#txt_Asunto").val('');
                $("#txt_FechaDocumento").val('');
                $('#cbo_Prioridad').selectpicker('val', '1');
                $('#cbo_TipoJuzgados').selectpicker('val', '0');
                $('#cbo_Departamento').selectpicker('val', '15');
                $('#cbo_Juzgados').selectpicker('val', '0');
                $("#txt_Folios").val('0');
                $("#txt_PostFirma").val('');
                $("#txt_FechaRecepcion").val('');
                $.ajax({
                    type: "GET",
                    url: "MesaPartes",
                    data: {mode: mode, periodo: periodo, codigo: codigo},
                    success: function (data) {
                        $('#txt_Numero').val(data);
                        var strDate = date.getFullYear() + "-" + pad(date.getMonth() + 1, 2) + "-" + pad(date.getDate(), 2);
                        $('#txt_FechaRecepcion').val(strDate);
                        $('#RegistrarMesaPartes .modal-content').removeAttr('class').addClass('modal-content modal-col-default');
                        $('#RegistrarMesaPartes').modal('show');
                        cargarJuzgados();
                        $("#txt_Numero:focus");
                        $('#txt_Numero').focus();
                    }
                });
            }
            function EditarRegistro(xCodigo) {
                var periodo = $('#cbo_Periodo').val();
                mode = 'U';
                codigo = xCodigo;
                $.ajax({
                    type: "GET",
                    url: "MesaPartes",
                    data: {mode: mode, periodo: periodo, codigo: codigo},
                    success: function (data) {
                        var dato = data.trim().split("+++");
                        if (dato.length === 15) {
                            $('#cbo_TipoJuzgados').selectpicker('val', dato[9]);
                            $('#cbo_Departamento').selectpicker('val', dato[10]);
                            $('#txt_Numero').val(dato[0]);
                            $("#txt_FechaRecepcion").val(dato[1]);
                            $('#cbo_TipoDocumento').selectpicker('val', dato[2]);
                            $("#txt_NumeroDocumento").val(dato[3]);
                            $("#txt_Indicativo").val(dato[4]);
                            $("#txt_Asunto").val(dato[5]);
                            $("#txt_FechaDocumento").val(dato[6]);
                            $('#cbo_Prioridad').selectpicker('val', dato[7]);
                            $("#txt_Folios").val(dato[8]);
                            $('#cbo_Juzgados').append("<option value=" + dato[11] + ">" + dato[12] + "</option>");
                            $('#cbo_Juzgados').selectpicker('val', dato[11]);
                            $('#cbo_Juzgados').selectpicker('refresh');
                            $("#txt_PostFirma").val(dato[13]);
                            $('#RegistrarMesaPartes .modal-content').removeAttr('class').addClass('modal-content modal-col-default');
                            $('#RegistrarMesaPartes').modal('show');
                        }
                    }
                });
            }
            function EliminarRegistro(xCodigo) {
                swal({
                    title: "¿Estás seguro?",
                    text: "¡Deseas Anular este registro!",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "Sí, anular!",
                    closeOnConfirm: false
                }, function () {
                    mode = 'D';
                    codigo = xCodigo;
                    GuardarDatos();
                });
            }
            function GuardarDatos() {
                var periodo = $('#cbo_Periodo').val();
                var fechaRecepcion = $("#txt_FechaRecepcion").val();
                var tipoDocumento = $("#cbo_TipoDocumento").val();
                var numero = $("#txt_Numero").val();
                var numeroDocumento = $("#txt_NumeroDocumento").val();
                var indicativo = $("#txt_Indicativo").val();
                var asunto = $("#txt_Asunto").val();
                var fechaDocumento = $("#txt_FechaDocumento").val();
                var prioridad = $("#cbo_Prioridad").val();
                var folios = $("#txt_Folios").val();
                var tipoJuzgado = $("#cbo_TipoJuzgados").val();
                var departamento = $("#cbo_Departamento").val();
                var juzgado = $("#cbo_Juzgados").val();
                var firma = $("#txt_PostFirma").val();
                $.ajax({
                    type: "POST",
                    url: "IduMesaPartes",
                    data: {mode: mode, periodo: periodo, tipo: 'I', codigo: codigo, fechaRecepcion: fechaRecepcion,
                        tipoDocumento: tipoDocumento, numero: numero, numeroDocumento: numeroDocumento, indicativo: indicativo,
                        asunto: asunto, fechaDocumento: fechaDocumento, prioridad: prioridad, folios: folios, tipoJuzgado: tipoJuzgado,
                        departamento: departamento, juzgado: juzgado, firma: firma},
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
                            $('#RegistrarMesaPartes').modal('hide');
                            fn_BuscarDatos();
                        } else {
                            swal("Aviso del Sistema!", msg, "error");
                        }
                    }
                });
            }
            function SubirArchivo(xCodigo) {
                codigo = xCodigo;
                $('#txt_NumeroArchivo').val(pad(xCodigo, 6));
                $('#txt_Archivo').val('');
                $('#SubirArchivoMesaPartes .modal-content').removeAttr('class').addClass('modal-content modal-col-default');
                $('#SubirArchivoMesaPartes').modal('show');
            }
            function GuardarArchivo() {
                var msg = 'Debe Seleccionar un Archivo a subir\n Proceso cancelado!!!.';
                var periodo = $('#cbo_Periodo').val();
                var archivo = $("#txt_Archivo").val();
                if (archivo !== '') {
                    var formData = new FormData(document.getElementById("frm_MesaParteArchivo"));
                    formData.append("mode", 'A');
                    formData.append("periodo", periodo);
                    formData.append("tipo", 'I');
                    formData.append("codigo", codigo);
                    formData.append("archivo", archivo);
                    $.ajax({
                        type: "POST",
                        url: "IduMesaPartes",
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
                                $('#SubirArchivoMesaPartes').modal('hide');
                                fn_BuscarDatos();
                            } else {
                                swal("Aviso del Sistema!", msg, "error");
                            }
                        }
                    });
                } else {
                    swal("Aviso del Sistema!", msg, "error");
                }
            }
            function pad(str, max) {
                str = str.toString();
                return str.length < max ? pad("0" + str, max) : str;
            }
            function fn_Reporte() {
                var periodo = $('#cbo_Periodo').val();
                var mes = $('#cbo_Mes').val();
                var dia = $('#txt_Dia').val();
                var url = 'Reportes?reporte=MPAR0001&periodo=' + periodo + '&mes=' + mes + "&codigo=" + dia;
                window.open(url, '_blank');
            }
            function cargarJuzgados() {
                var tipoJuzgado = $("#cbo_TipoJuzgados").val();
                var departamento = $("#cbo_Departamento").val();
                $.ajax({
                    type: "GET",
                    url: "Combos",
                    data: {accion: 'JUZGADO_DEPARTAMENTO', tipoJuzgado: tipoJuzgado, departamento: departamento},
                    success: function (data) {
                        $('#cbo_Juzgados').empty();
                        $('#cbo_Juzgados').append(data);
                        $('#cbo_Juzgados').selectpicker('refresh');
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
                    <h3><strong>Administración de Mesa de Partes</strong></h3>
                </div>
                <!-- Tabs With Icon Title -->
                <div class="row clearfix">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="card">
                            <div class="header">
                                <div class="row clearfix">
                                    <div class="col-sm-1 form-control-label">
                                        <label for="TipoDocumento">Periodo</label>
                                    </div>
                                    <div class="col-sm-2">
                                        <select id="cbo_Periodo" class="form-control" onchange="javascript: fn_BuscarDatos();">
                                            <c:forEach var="b" items="${objPeriodos}"> 
                                                <option value="${b.codigo}">${b.descripcion}</option>
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
                                        <label for="Dia">Día : </label>
                                    </div>
                                    <div class="col-sm-1">
                                        <div class="form-group">
                                            <div class="form-line">
                                                <input type="text" id="txt_Dia" class="form-control" maxlength="02" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <button type="button" class="btn btn-primary waves-effect"  onclick="javascript:fn_BuscarDatos();">
                                            <i class="material-icons">search</i>
                                            <span>Buscar</span>
                                        </button> &nbsp;
                                        <button type="button" class="btn bg-grey waves-effect" onclick="javascript:fn_Reporte();">
                                            <i class="material-icons">print</i>
                                            <span>Reporte</span>
                                        </button>
                                        <div>&nbsp;</div>
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
                                                        <button type="button" class="btn btn-success waves-effect btn-sm col-md-2 col-sm-3 col-xs-4"  onclick="javascript:NuevoRegistro();">
                                                            <i class="material-icons">add </i>
                                                            <span>Nuevo Registro</span>
                                                        </button>
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
                                    <!-- Modal Dialogs ===================== -->
                                    <!-- Default Size -->
                                    <div class="modal fade" id="RegistrarMesaPartes" tabindex="-1" role="dialog">
                                        <div class="modal-dialog modal-lg" role="document">
                                            <div class="modal-content">
                                                <form id="frm_MesaParte" name="frm_MesaParte" action="javascript:GuardarDatos();" method="post">
                                                    <div class="modal-header">
                                                        <h4 class="modal-title" id="defaultModalLabel">REGISTRO DE MESA DE PARTES</h4>
                                                    </div>
                                                    <div class="modal-body">
                                                        <div class="row clearfix">
                                                            <div class="col-sm-2 form-control-label">
                                                                <label for="Correlativo">Correlativo</label>
                                                            </div>
                                                            <div class="col-sm-2">
                                                                <div class="form-group">
                                                                    <div class="form-line">
                                                                        <input type="text" id="txt_Numero" class="form-control" disabled="disabled">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-sm-3 form-control-label">
                                                                <label for="FechaRecepcion">Fecha Recepción</label>
                                                            </div>
                                                            <div class="col-sm-3">
                                                                <div class="form-group">
                                                                    <div class="form-line">
                                                                        <input type="date" id="txt_FechaRecepcion" class="form-control" placeholder="DD/MM/YYYY" disabled="disabled">
                                                                    </div>
                                                                </div>
                                                            </div> 
                                                        </div>
                                                        <div class="row clearfix">
                                                            <div class="col-sm-2 form-control-label">
                                                                <label for="TipoDocumento">Tipo Doc.</label>
                                                            </div>
                                                            <div class="col-sm-3">
                                                                <select id="cbo_TipoDocumento" name="cbo_TipoDocumento" class="form-control" data-live-search="true" onchange="javascript: ntab(event, 'txt_NumeroDocumento');">
                                                                    <c:forEach var="e" items="${objTipoDocumento}">
                                                                        <option value="${e.codigo}">${e.descripcion}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                            <div class="col-sm-1 form-control-label">
                                                                <label for="numero">Número</label>
                                                            </div>
                                                            <div class="col-sm-2">
                                                                <div class="form-group">
                                                                    <div class="form-line">
                                                                        <input style="text-transform: uppercase" type="text" id="txt_NumeroDocumento" name="txt_NumeroDocumento" class="form-control" maxlength="50" onKeyPress="ntab(event, 'txt_Indicativo');" required>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-sm-1 form-control-label">
                                                                <label for="indicativo">Indicativo</label>
                                                            </div>
                                                            <div class="col-sm-2">
                                                                <div class="form-group">
                                                                    <div class="form-line">
                                                                        <input style="text-transform: uppercase" type="text" id="txt_Indicativo" name="txt_Indicativo" class="form-control" maxlength="50" onKeyPress="ntab(event, 'txt_Asunto');" required>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row clearfix">
                                                            <div class="col-sm-2 form-control-label">
                                                                <label for="asunto">Asunto</label>
                                                            </div>
                                                            <div class="col-sm-10">
                                                                <div class="form-group">
                                                                    <div class="form-line">
                                                                        <input style="text-transform: uppercase" type="text" id="txt_Asunto" name="txt_Asunto" class="form-control" maxlength="1000" onKeyPress="ntab(event, 'txt_FechaDocumento');" required>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row clearfix">
                                                            <div class="col-sm-2 form-control-label">
                                                                <label for="FechaDocumento">Fecha Doc.</label>
                                                            </div>
                                                            <div class="col-sm-3"> 
                                                                <div class="input-group">
                                                                    <span class="input-group-addon">
                                                                        <i class="material-icons">date_range</i>
                                                                    </span>
                                                                    <div class="form-line">
                                                                        <input type="date" id="txt_FechaDocumento" class="form-control" placeholder="DD/MM/YYYY" maxlength="10" onKeyPress="ntab(event, 'txt_Folios');" required>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-sm-1 form-control-label">
                                                                <label for="Prioridad">Prioridad</label>
                                                            </div>
                                                            <div class="col-sm-3">
                                                                <div class="form-group">
                                                                    <select id="cbo_Prioridad" name="cbo_Prioridad" class="form-control">
                                                                        <c:forEach var="f" items="${objPrioridad}"> 
                                                                            <option value="${f.codigo}">${f.descripcion}</option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                            <div class="col-sm-1 form-control-label">
                                                                <label for="folios">Folios</label>
                                                            </div>
                                                            <div class="col-sm-2">
                                                                <div class="form-group">
                                                                    <div class="form-line">
                                                                        <input type="text" id="txt_Folios" class="form-control" maxlength="20" onKeyPress="ntab(event, 'cbo_TipoJuzgados');" required>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row clearfix">
                                                            <div class="col-sm-2 form-control-label">
                                                                <label for="tipoJuzgado">Tipo Juzgado</label>
                                                            </div>
                                                            <div class="col-sm-3">
                                                                <select id="cbo_TipoJuzgados" name="cbo_TipoJuzgados" class="form-control" onchange="javascript: cargarJuzgados();" required>
                                                                    <c:forEach var="g" items="${objTipoJuzgados}">
                                                                        <option  value="${g.codigo}">${g.descripcion}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                            <div class="col-sm-2 form-control-label">
                                                                <label for="lugar">Departamento</label>
                                                            </div>
                                                            <div class="col-sm-3">
                                                                <select id="cbo_Departamento" name="cbo_Departamento" class="form-control" onchange="javascript: cargarJuzgados();" required>
                                                                    <c:forEach var="h" items="${objDepartamento}">
                                                                        <option  value="${h.codigo}">${h.descripcion}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="row clearfix">
                                                            <div class="col-sm-2 form-control-label">
                                                                <label for="juzgado">Juzgado</label>
                                                            </div>
                                                            <div class="col-sm-10">
                                                                <select id="cbo_Juzgados" name="cbo_Juzgados" class="form-control" data-live-search="true" onchange="javascript: ntab(event, 'txt_PostFirma');" required>
                                                                    <option value="0">SELECCIONE</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="row clearfix">
                                                            <div class="col-sm-2 form-control-label">
                                                                <label for="asunto">Post Firma</label>
                                                            </div>
                                                            <div class="col-sm-10">
                                                                <div class="form-group">
                                                                    <div class="form-line">
                                                                        <input style="text-transform: uppercase" type="text" id="txt_PostFirma" class="form-control" maxlength="1000" onKeyPress="ntab(event, 'txt_PostFirma');" required>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer ">
                                                        <button type="submit" class="btn bg-blue waves-effect btn-sm col-md-40" >
                                                            <i class="material-icons">save </i>
                                                            <span>Registrar</span>
                                                        </button>
                                                        <button type="button" class="btn bg-red waves-effect btn-sm col-md-50" data-dismiss="modal">
                                                            <i class="material-icons">cancel </i>
                                                            <span>Cancelar</span>
                                                        </button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Default Size -->
                                    <div class="modal fade" id="SubirArchivoMesaPartes" tabindex="-1" role="dialog">
                                        <div class="modal-dialog modal-lg" role="document">
                                            <div class="modal-content">
                                                <form id="frm_MesaParteArchivo" name="frm_MesaParteArchivo" enctype="multipart/form-data" action="javascript:GuardarArchivo();" method="post">
                                                    <div class="modal-header">
                                                        <h4 class="modal-title" id="defaultModalLabel">ADJUNTAR DOCUMENTO - MESA DE PARTES</h4>
                                                    </div>
                                                    <div class="modal-body">
                                                        <div class="row clearfix">
                                                            <div class="col-sm-2 form-control-label">
                                                                <label for="CorrelativoArchivo">Correlativo</label>
                                                            </div>
                                                            <div class="col-sm-2">
                                                                <div class="form-group">
                                                                    <div class="form-line">
                                                                        <input type="text" id="txt_NumeroArchivo" class="form-control" disabled="disabled">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row clearfix">
                                                            <div class="col-sm-2 form-control-label">
                                                                <label for="asunto">Archivo</label>
                                                            </div>
                                                            <div class="col-sm-10">
                                                                <div class="form-group">
                                                                    <div class="form-line">
                                                                        <input type="file" name="txt_Archivo" id="txt_Archivo" class="form-control" maxlength="1000" required>
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