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
            var sentencia = '';
            var cip = '';
            var resolucion = '';
            var mode = null;
            var date = new Date();
            var pago = 0;
            var archivo = null;
            BuscarDatos();
            function BuscarDatos() {
                $.ajax({
                    type: "GET",
                    url: "SentenciasProcesar",
                    data: {mode: 'S'},
                    success: function (data) {
                        $('#DetalleSentencia').empty();
                        $('#DetalleSentencia').html(data);
                        $('#TablaSentencias').DataTable();
                        $('[data-toggle="tooltip"]').tooltip();
                        $('a[href="#ListarSentenciasJudiciales"]').trigger('click');
                    }
                });
            }
            function cargarDecretosMesaPartes() {
                var periodo = $("#cbo_Periodo").val();
                var area = '${area}'.trim();
                $.ajax({
                    type: "GET",
                    url: "Combos",
                    data: {accion: 'MESA_PARTES', codigo: periodo, area: area},
                    success: function (data) {
                        $('#cbo_MesaPartes').replaceWith(data);
                    }
                });
            }
            function cargarMesaPartes() {
                var periodo = $("#cbo_Periodo").val();
                var mesaPartes = $("#cbo_MesaPartes").val();
                $.ajax({
                    type: "GET",
                    url: "VerificaSentencias",
                    data: {mode: 'M', cip: periodo, sentencia: mesaPartes},
                    success: function (data) {
                        var dato = data.trim().split("+++");
                        if (dato.length === 6) {
                            $('#txt_NumeroOficio').val(dato[0]);
                            $('#txt_FechaOficio').val(dato[1]);
                            $('#cbo_Juzgados').selectpicker('val', dato[2]);
                            $('#txt_Juez').val(dato[3]);
                            archivo = dato[4];
                            $('#RegistrarResolucion .modal-content').removeAttr('class').addClass('modal-content modal-col-default');
                            $('#RegistrarResolucion').modal('show');
                        }
                    }
                });
            }
            function cargarJuzgados() {
                var tipoJuzgado = $("#cbo_TipoJuzgados").val();
                $.ajax({
                    type: "GET",
                    url: "Combos",
                    data: {accion: 'JUZGADO', codigo: tipoJuzgado},
                    success: function (data) {
                        $('#cbo_Juzgados').replaceWith(data);
                    }
                });
            }
            function EditarRegistroResolucion(xCIP, xSentencia, xResolucion) {
                $.ajax({
                    type: "GET",
                    url: "VerificaSentencias",
                    data: {mode: 'R', cip: xCIP, sentencia: xSentencia, resolucion: xResolucion},
                    success: function (data) {
                        var dato = data.trim().split("+++");
                        if (dato.length === 10) {
                            $('#cbo_Periodo').selectpicker('val', dato[0]);
                            //cargarDecretosMesaPartes();
                            $('#txt_NumeroOficio').val(dato[6]);
                            $('#txt_FechaOficio').val(dato[5]);
                            $('#txt_NumeroExpediente').val(dato[4]);
                            $('#txt_NumeroResolucion').val(dato[3]);
                            $('#txt_FechaExpediente').val(dato[7]);
                            $('#txt_Juez').val(dato[8]);
                            $('#cbo_Juzgados').selectpicker('val', dato[9]);
                            $('#cbo_MesaPartes').html("");
                            $('#cbo_MesaPartes').append("<option value=" + dato[1] + ">" + dato[2] + "</option>");
                            $('#cbo_MesaPartes').selectpicker('val', dato[1]);
                            $('#cbo_MesaPartes').selectpicker('refresh');
                            $('#RegistrarResolucion .modal-content').removeAttr('class').addClass('modal-content modal-col-default');
                            $('#RegistrarResolucion').modal('show');
                        }
                    }
                });
            }
            function verTipoImporte() {
                var tipoPago = $('#cbo_TipoPago').val();
                $('#txt_Total').val('');
                $('#txt_MontoTotal').attr('disabled', 'disabled');
                if (tipoPago === '0') {
                    $('#txt_Cuotas').val('');
                    $('#txt_MontoCuota').val('');
                    $('#txt_Porcentaje').val('');
                    $('#txt_Cuotas').attr('disabled', 'disabled');
                    $('#txt_MontoCuota').attr('disabled', 'disabled');
                    $('#txt_Porcentaje').attr('disabled', 'disabled');
                }
                if (tipoPago === '1' || tipoPago === '2') {
                    $('#txt_Cuotas').val('');
                    $('#txt_MontoCuota').val('');
                    $('#txt_Cuotas').attr('disabled', 'disabled');
                    $('#txt_MontoCuota').attr('disabled', 'disabled');
                    //$('#txt_Porcentaje').removeAttr('disabled');
                    $('#txt_Porcentaje').focus();
                }
                if (tipoPago === '3') {
                    $('#txt_Cuotas').val('');
                    $('#txt_Porcentaje').val('');
                    $('#txt_Porcentaje').attr('disabled', 'disabled');
                    $('#txt_Cuotas').attr('disabled', 'disabled');
                    //$('#txt_MontoCuota').removeAttr('disabled');
                    $('#txt_MontoCuota').focus();
                }
                if (tipoPago === '4') {
                    $('#txt_Porcentaje').val('');
                    $('#txt_Porcentaje').attr('disabled', 'disabled');
                    //$('#txt_Cuotas').removeAttr('disabled');
                    //$('#txt_MontoCuota').removeAttr('disabled');
                    $('#txt_Cuotas').focus();
                }
            }
            function CalculaTotal(xDetalle) {
                var tipoPago = $('#cbo_TipoPago').val();
                var monto = $('#txt_Monto_' + xDetalle).val();
                var porcentaje = 0;
                if (tipoPago === '1' || tipoPago === '2') {
                    porcentaje = parseFloat($('#txt_Porcentaje').val());
                    monto = parseFloat(monto);
                    if (porcentaje !== monto) {
                        swal("Aviso del Sistema!", 'El Porcentaje Ingresado no debe Coincidir con el Porcentaje Total', "error");
                        $('#txt_Monto_' + xDetalle).val(porcentaje);
                        $('#txt_Monto_' + xDetalle).focus();
                    }
                }
                if (tipoPago === '3' || tipoPago === '4') {
                    var total = 0.0;
                    var montoCuota = parseFloat($('#txt_MontoCuota').val());
                    $(document).ready(function () {
                        $("#frm_ResolucionDetalle").find(':input').each(function () {
                            var elemento = this;
                            if (elemento.id.substr(0, 10) === 'txt_Monto_') {
                                total += parseFloat(elemento.value);
                            }
                        });
                    });
                    if (total > montoCuota) {
                        swal("Aviso del Sistema!", 'El Monto de la Cuota Ingresado no debe Coincidir con el Monto de Cuota Total', "error");
                        $('#txt_Monto_' + xDetalle).focus();
                    }
                }
            }
            function RegistrarBeneficiario(xCip, xSentencia, xResolucion) {
                $('#cbo_TipoDocumento').selectpicker('val', '0');
                $('#txt_NumeroDocumento').val('');
                $('#txt_Paterno').val('');
                $('#txt_Materno').val('');
                $('#txt_Nombres').val('');
                $('#txt_FechaNacimiento').val('');
                $('#cbo_Tipo').selectpicker('val', '0');
                $('#cbo_Bancos').selectpicker('val', '0');
                $('#txt_Cuenta').val('');
                $('#txt_CCI').val('');
                $('#txt_RazonSocial').val('');
                $('#txt_NombreJuzgado').val('');
                $.ajax({
                    type: "GET",
                    url: "VerificaSentencias",
                    data: {mode: 'RB', cip: xCip, sentencia: xSentencia, resolucion: xResolucion},
                    success: function (data) {
                        var dato = data.trim().split("+++");
                        if (dato.length === 6) {
                            $('#cbo_TipoDocumento').selectpicker('val', dato[0]);
                            $('#txt_NumeroDocumento').val($.trim(dato[1]));
                            verPersonal();
                            $('#cbo_Tipo').selectpicker('val', dato[2]);
                            $('#cbo_Bancos').selectpicker('val', dato[3]);
                            $('#txt_Cuenta').val(dato[4]);
                            $('#txt_CCI').val(dato[5]);
                            fn_verTipoPago();
                            $('#RegistrarBeneficiario .modal-content').removeAttr('class').addClass('modal-content modal-col-default');
                            $('#RegistrarBeneficiario').modal('show');
                        }
                    }
                });
            }
            function DetalleResolucion(xCip, xSentencia, xResolucion, xTipoPago) {
                pago = xTipoPago;
                $.ajax({
                    type: "GET",
                    url: "VerificaSentencias",
                    data: {mode: 'TP', cip: xCip, sentencia: xSentencia, resolucion: xResolucion},
                    success: function (data) {
                        var dato = data.trim().split("+++");
                        if (dato.length === 6) {
                            $('#cbo_TipoPago').selectpicker('val', dato[0]);
                            $('#cbo_TipoMoneda').selectpicker('val', dato[1]);
                            verTipoImporte();
                            $('#txt_Cuotas').val(dato[2]);
                            $('#txt_MontoCuota').val(dato[3]);
                            $('#txt_Porcentaje').val(dato[4]);
                            $('#txt_Total').val(dato[5]);
                            $.ajax({
                                type: "GET",
                                url: "VerificaSentencias",
                                data: {mode: 'DE', cip: xCip, sentencia: xSentencia, resolucion: xResolucion},
                                success: function (data) {
                                    $('#TablaResolucionDetalle').html(data);
                                    $('#DetalleResolucion .modal-content').removeAttr('class').addClass('modal-content modal-col-default');
                                    $('#DetalleResolucion').modal('show');
                                    fn_RecorreDetalle();
                                }
                            });
                        }
                    }
                });
            }
            function ReporteResolucion(xCip, xSentencia, xResolucion) {
                var url = 'Reportes?reporte=RESO0001&cip=' + xCip + '&sentencia=' + xSentencia + "&codigo=" + xResolucion;
                window.open(url, '_blank');
            }
            function fn_verTipoPago() {
                var tipo = $('#cbo_Tipo').val();
                if (tipo === '1') {
                    $('#txt_NombreJuzgado').val('');
                }
                if (tipo === '2') {
                    $('#cbo_Bancos').selectpicker('val', '0');
                    $('#txt_Cuenta').val('');
                    $('#txt_CCI').val('');
                    cip = $('#txt_CIP').val();
                    $.ajax({
                        type: "GET",
                        url: "Sentencias",
                        data: {mode: 'J', cip: cip, sentencia: sentencia, resolucion: resolucion},
                        success: function (data) {
                            $('#txt_NombreJuzgado').val(data);
                        }
                    });
                }
                if (tipo === '3') {
                    $('#cbo_Bancos').selectpicker('val', '1');
                    $('#txt_Cuenta').val('');
                    $('#txt_CCI').val('');
                    $('#txt_NombreJuzgado').val('');
                }
            }
            function verPersonal() {
                var numeroDocumento = $('#txt_NumeroDocumento').val();
                if (numeroDocumento.length === 8) {
                    $.ajax({
                        type: "GET",
                        url: "Combos",
                        data: {accion: 'BENEFICIARIO', codigo: numeroDocumento},
                        success: function (data) {
                            var dato = data.trim().split("+++");
                            if (dato.length === 6) {
                                $('#txt_Paterno').val(dato[0]);
                                $('#txt_Materno').val(dato[1]);
                                $('#txt_Nombres').val(dato[2]);
                                $('#txt_FechaNacimiento').val(dato[3]);
                                $('#txt_RazonSocial').val(dato[4]);
                                $('#txt_RUC').val(dato[5]);
                            } else {
                                $('#txt_Paterno').val('');
                                $('#txt_Materno').val('');
                                $('#txt_Nombres').val('');
                                $('#txt_FechaNacimiento').val('');
                                $('#txt_RazonSocial').val('');
                                $('#txt_RUC').val('');
                                $('#txt_Paterno').focus();
                            }
                        }
                    });
                }                
            }
            ﻿function ntabBuscarPersonal(evt, obj) {
                var nav4 = window.Event ? true : false;
                var key = nav4 ? evt.which : evt.keyCode;
                if (key === 13) {
                    BuscarPersonal();
                }
                if (key === 34)
                    return (key === 0);
                if (key === 37)
                    return (key === 0);
            }
            function fn_VerTipoDocumento() {
                var tipoDocumento = $('#cbo_TipoDocumento').val();
                //CUANDO NO SE SELECCIONA EL TIPO DE DOCUMENTO
                $('#txt_NumeroDocumento').val('');
                $('#txt_Paterno').val('');
                $('#txt_Materno').val('');
                $('#txt_Nombres').val('');
                $('#txt_FechaNacimiento').val('');
                $('#txt_RazonSocial').val('');
                if (tipoDocumento === '0') {
                    $('#txt_Paterno').removeAttr('disabled');
                    $('#txt_Materno').removeAttr('disabled');
                    $('#txt_Nombres').removeAttr('disabled');
                    $('#txt_FechaNacimiento').removeAttr('disabled');
                    $('#txt_RazonSocial').removeAttr('disabled');
                }
                //PARA DNI Y CARNET EXTRANJERIA
                if (tipoDocumento === '1' || tipoDocumento === '3') {
                    $('#txt_Paterno').removeAttr('disabled');
                    $('#txt_Materno').removeAttr('disabled');
                    $('#txt_Nombres').removeAttr('disabled');
                    $('#txt_FechaNacimiento').removeAttr('disabled');
                    $('#txt_RazonSocial').attr('disabled', 'disabled');
                }
                //PARA RUC
                if (tipoDocumento === '2') {
                    $('#txt_Paterno').attr('disabled', 'disabled');
                    $('#txt_Materno').attr('disabled', 'disabled');
                    $('#txt_Nombres').attr('disabled', 'disabled');
                    $('#txt_FechaNacimiento').attr('disabled', 'disabled');
                    $('#txt_RazonSocial').removeAttr('disabled');
                }
                $('#txt_NumeroDocumento').focus();
            }
            function fn_verDocumento() {
                var periodo = $('#cbo_Periodo').val();
                var numero = $("#cbo_MesaPartes").val();
                if (archivo !== null && archivo !== '') {
                    document.location.target = "_blank";
                    document.location.href = "Descarga?opcion=MesaParte&periodo=" + periodo + "&codigo=" + numero + "&documento=" + archivo;
                } else {
                    swal("Aviso del Sistema!", 'No se encuentra el Archivo!', "error");
                }
            }
            function fn_ActivaDetalle(xDetalle) {
                var tipoPago = $('#cbo_TipoPago').val();
                var porcentaje = parseFloat($('#txt_Porcentaje').val());
                if (tipoPago === null || tipoPago === 'null' || tipoPago === '0') {
                    swal("Aviso del Sistema!", 'Debe Seleccionar el Tipo de Pago!', "error");
                    $('#div_checkbox_' + xDetalle).removeAttr('checked');
                } else {
                    if ($('#div_checkbox_' + xDetalle).is(':checked')) {
                        //   $('#txt_Monto_' + xDetalle).removeAttr('disabled');
                        if (tipoPago === '1' || tipoPago === '2') {
                            $('#txt_Monto_' + xDetalle).val(porcentaje);
                        }
                        $('#txt_Monto_' + xDetalle).focus();
                    } else {
                        $('#txt_Monto_' + xDetalle).val('0.0');
                        $('#txt_Monto_' + xDetalle).attr('disabled', 'disabled');
                    }
                }
            }
            function fn_RecorreDetalle() {
                $("#frm_ResolucionDetalle").find(':input').each(function () {
                    var elemento = this;
                    if (elemento.id.substr(0, 10) === 'txt_Monto_') {
                        if (parseFloat(elemento.value) > 0) {
                            $('#div_checkbox_' + elemento.id.substr(10, 2)).attr('checked', true);
                        }
                    }
                });
            }
            function CerrarResolucion(xCip, xSentencia, xResolucion) {
                swal({
                    title: "¿Estás seguro?",
                    text: "¡Deseas Cerrar este registro!",
                    type: "info",
                    showCancelButton: true,
                    confirmButtonColor: "#00BCD4",
                    confirmButtonText: "Sí, Cerrar!",
                    closeOnConfirm: false
                }, function () {
                    var cip = $('#txt_CIP').val();
                    $('#cbo_Periodo').selectpicker('val', );
                    $('#cbo_MesaPartes').val();
                    $.ajax({
                        type: "POST",
                        url: "IduVerificaSentencia",
                        data: {mode: 'C', cip: xCip, sentencia: xSentencia, resolucion: xResolucion},
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
                                BuscarDatos();
                            } else {
                                swal("Aviso del Sistema!", msg, "error");
                            }
                        }
                    });
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
                    <h3><strong>Sentencias Judiciales sin Verificar</strong></h3>
                </div>
                <!-- Tabs With Icon Title -->
                <div class="row clearfix">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="card">
                            <div class="body"> 
                                <!-- Nav tabs -->
                                <ul class="nav nav-tabs" role="tablist">
                                    <li role="presentation" class="active" id="sente">
                                        <a href="#ListarSentenciasJudiciales" data-toggle="tab">
                                            <i class="material-icons">assignment_returned</i> Sentencias Judiciales
                                        </a>
                                    </li>
                                </ul>
                                <!-- Tab panes -->
                                <div class="tab-content">
                                    <!-- Lista ListarSentenciasJudiciales-->
                                    <div role="tabpanel" class="tab-pane fade in active" id="ListarSentenciasJudiciales">
                                        <!-- Striped Rows -->
                                        <div class="row clearfix">
                                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                <div class="card">
                                                    <div class="header bg-teal">
                                                        <h2 class="col-md-10 col-sm-9 col-xs-8">
                                                            <strong>Listado de Sentencias Judiciales</strong>
                                                        </h2>                                                        
                                                        <br>
                                                    </div>
                                                    <div class="body">
                                                        <div id="DetalleSentencia"></div>                                                            
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- #END# Striped Rows -->
                                    </div>                                    
                                    <!-- Modal Dialogs ===================================== -->
                                    <!-- Default Size -->
                                    <div class="modal fade" id="BuscarPersonal" tabindex="-1" role="dialog">
                                        <div class="modal-dialog modal-lg" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h4 class="modal-title" id="defaultModalLabel">CONSULTA DE PERSONAL</h4>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="row clearfix">
                                                        <div class="col-sm-3 form-control-label">
                                                            <label for="Apellidos">Apellidos y Nombres</label>
                                                        </div>
                                                        <div class="col-sm-4">
                                                            <div class="form-group">
                                                                <div class="form-line">
                                                                    <input type="text" id="txt_ApellidosBuscar" class="form-control" maxlength="100" onKeyPress="ntabBuscarPersonal(event, 'txt_ApellidosBuscar');" >
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-1 form-control-label">
                                                            <label for="CIP">CIP</label>
                                                        </div>
                                                        <div class="col-sm-2">
                                                            <div class="form-group">
                                                                <div class="form-line">
                                                                    <input type="text" id="txt_CIPBuscar" class="form-control" maxlength="10" onKeyPress="ntabBuscarPersonal(event, 'txt_CIPBuscar');">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <button type="button" class="btn btn-primary waves-effect btn-sm col-md-1 col-sm-1 col-xs-1" onclick="javascript:BuscarPersonal();">
                                                            <i class="material-icons">search</i>
                                                            <span>&nbsp;</span>
                                                        </button> 
                                                    </div>
                                                    <div class="row clearfix">
                                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                            <div class="card">
                                                                <div class="header bg-teal">
                                                                    <h2 class="col-md-10 col-sm-2 col-xs-2">
                                                                        <strong>Listado de Personal</strong>
                                                                    </h2>
                                                                    <br>
                                                                </div>
                                                                <div class="body">
                                                                    <div id="DetallePersonal"></div>   
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="modal-footer ">
                                                    <button type="button" class="btn bg-success waves-effect btn-sm col-md-40" onclick="javascript:SeleccionaPersonal();">
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
                                    <!-- Default Size -->
                                    <div class="modal fade" id="RegistrarSentencia" tabindex="-1" role="dialog">
                                        <div class="modal-dialog modal-lg" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h4 class="modal-title" id="defaultModalLabel">REGISTRO DE SENTENCIAS JUDICIALES</h4>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="row clearfix">
                                                        <div class="col-sm-1 form-control-label">
                                                            <label for="Motivo">Motivo</label>
                                                        </div>
                                                        <div class="col-sm-10">
                                                            <div class="form-group">
                                                                <div class="form-line">
                                                                    <input type="text" id="txt_Motivo" class="form-control" maxlength="50" disabled="disabled">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row clearfix">
                                                        <div class="col-sm-1 form-control-label">
                                                            <label for="Fecha">Fecha </label>
                                                        </div>
                                                        <div class="col-sm-3">
                                                            <div class="input-group">
                                                                <span class="input-group-addon">
                                                                    <i class="material-icons">date_range</i>
                                                                </span>
                                                                <div class="form-line">
                                                                    <input type="text" id="txt_FechaInicio" class="datepicker form-control" placeholder="dd/mm/yyyy" maxlength="10" data-language="es">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="modal-footer ">
                                                    <button type="button" class="btn bg-red waves-effect btn-sm col-md-50" data-dismiss="modal">
                                                        <i class="material-icons">close</i>
                                                        <span>Cerrar</span>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Default Size -->
                                    <div class="modal fade" id="RegistrarResolucion" tabindex="-1" role="dialog">
                                        <div class="modal-dialog modal-lg" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h4 class="modal-title" id="defaultModalLabel">REGISTRO DE SENTENCIAS JUDICIALES</h4>
                                                </div>
                                                <div class="modal-body">  
                                                    <div class="row clearfix">
                                                        <h4 class="title bg-light-blue">
                                                            <strong>Registro de Mesa de Partes</strong>
                                                        </h4>
                                                    </div>
                                                    <div class="row clearfix">
                                                        <div class="col-sm-1 form-control-label">
                                                            <label for="Periodo">Periodo</label>
                                                        </div>
                                                        <div class="col-sm-2">
                                                            <select id="cbo_Periodo" class="form-control" onchange="javascript: cargarDecretosMesaPartes();" disabled="disabled">
                                                                <option value="0">Seleccione</option>
                                                                <c:forEach var="a" items="${objPeriodos}">
                                                                    <option value="${a.codigo}">${a.descripcion}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div class="col-sm-2 form-control-label">
                                                            <label for="Numero">Nro. Mesa Partes</label>
                                                        </div>
                                                        <div class="col-sm-6">
                                                            <div id="cbo_MesaPartes"></div>
                                                        </div>
                                                    </div>
                                                    <div class="row clearfix">
                                                        <div class="col-sm-1 form-control-label">
                                                            <label for="NroOficio">Oficio</label>
                                                        </div>
                                                        <div class="col-sm-3">
                                                            <div class="form-group">
                                                                <div class="form-line">
                                                                    <input type="text" id="txt_NumeroOficio" class="form-control" maxlength="50" disabled="disabled">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-1 form-control-label">
                                                            <label for="Fecha">Fecha </label>
                                                        </div>
                                                        <div class="col-sm-2">
                                                            <div class="input-group">
                                                                <span class="input-group-addon">
                                                                    <i class="material-icons">date_range</i>
                                                                </span>
                                                                <div class="form-line">
                                                                    <input type="date" id="txt_FechaOficio" class="form-control" placeholder="dd/mm/yyyy" maxlength="10" disabled="disabled">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-3 form-control-label">
                                                            <a href="javascript:fn_verDocumento();"> Documento</a>
                                                        </div>
                                                    </div> 
                                                    <div class="row clearfix">
                                                        <div class="col-sm-1 form-control-label">
                                                            <label for="juzgado">Juzgado</label>
                                                        </div>
                                                        <div class="col-sm-10">
                                                            <select id="cbo_Juzgados" class="form-control" data-live-search="true" disabled="disabled">
                                                                <option value="0">Seleccione</option>
                                                                <c:forEach var="f" items="${objJuzgados}">
                                                                    <option value="${f.codigo}">${f.descripcion}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="row clearfix">
                                                        <div class="col-sm-1 form-control-label">
                                                            <label for="asunto">Juez</label>
                                                        </div>
                                                        <div class="col-sm-10">
                                                            <div class="form-group">
                                                                <div class="form-line">
                                                                    <input type="text" id="txt_Juez" class="form-control" maxlength="1000" disabled="disabled">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row clearfix">
                                                        <h4 class="title bg-light-blue">
                                                            <strong>Datos de la Resolución</strong>
                                                        </h4>
                                                    </div>
                                                    <div class="row clearfix">
                                                        <div class="col-sm-1 form-control-label">
                                                            <label for="Expediente">Expediente </label>
                                                        </div>
                                                        <div class="col-sm-4">
                                                            <div class="form-group">
                                                                <div class="form-line">
                                                                    <input type="text" id="txt_NumeroExpediente" class="form-control" maxlength="50" disabled="disabled">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-1 form-control-label">
                                                            <label for="Fecha">Fecha </label>
                                                        </div>
                                                        <div class="col-sm-3">
                                                            <div class="input-group">
                                                                <span class="input-group-addon">
                                                                    <i class="material-icons">date_range</i>
                                                                </span>
                                                                <div class="form-line">
                                                                    <input type="date" id="txt_FechaExpediente" class="form-control" placeholder="dd/mm/yyyy" maxlength="10" disabled="disabled">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="modal-footer ">
                                                    <button type="button" class="btn bg-red waves-effect btn-sm col-md-50" data-dismiss="modal">
                                                        <i class="material-icons">close</i>
                                                        <span>Cerrar</span>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Default Size -->
                                    <div class="modal fade" id="RegistrarBeneficiario" tabindex="-1" role="dialog">
                                        <div class="modal-dialog modal-lg" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h4 class="modal-title" id="defaultModalLabel">REGISTRO DE BENEFICIARIO</h4>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="row clearfix">
                                                        <h5 class="title bg-light-blue">
                                                            <strong>Datos del Beneficiario(a)</strong>
                                                        </h5>
                                                    </div>
                                                    <div class="row clearfix">
                                                        <div class="col-sm-2 form-control-label">
                                                            <label for="TipoDocumento">Documento</label>
                                                        </div>
                                                        <div class="col-sm-4">
                                                            <select id="cbo_TipoDocumento" class="form-control" onchange="fn_VerTipoDocumento();" disabled="disabled">
                                                                <option value="0">Seleccione</option>
                                                                <option value="1">D.N.I.</option>
                                                                <option value="2">Carnet Extranjeria</option>
                                                            </select>
                                                        </div>
                                                        <div class="col-sm-2 form-control-label">
                                                            <label for="NumeroDocumento">Número</label>
                                                        </div>
                                                        <div class="col-sm-4">
                                                            <div class="form-group">
                                                                <div class="form-line">
                                                                    <input type="text" id="txt_NumeroDocumento" class="form-control" maxlength="11" onchange="javascript: verPersonal();" disabled="disabled">
                                                                </div>
                                                            </div>
                                                        </div>  
                                                    </div>
                                                    <div class="row clearfix">
                                                        <div class="col-sm-2 form-control-label">
                                                            <label for="Paterno">Ap. Paterno</label>
                                                        </div>
                                                        <div class="col-sm-4">
                                                            <div class="form-group">
                                                                <div class="form-line">
                                                                    <input type="text" id="txt_Paterno" class="form-control" maxlength="50" disabled="disabled">
                                                                </div>
                                                            </div>
                                                        </div> 
                                                        <div class="col-sm-2 form-control-label">
                                                            <label for="Materno">Ap. Materno</label>
                                                        </div>
                                                        <div class="col-sm-4">
                                                            <div class="form-group">
                                                                <div class="form-line">
                                                                    <input type="text" id="txt_Materno" class="form-control" maxlength="50" disabled="disabled">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row clearfix">
                                                        <div class="col-sm-2 form-control-label">
                                                            <label for="Nombres">Nombres</label>
                                                        </div>
                                                        <div class="col-sm-4">
                                                            <div class="form-group">
                                                                <div class="form-line">
                                                                    <input type="text" id="txt_Nombres" class="form-control" maxlength="50" disabled="disabled">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-2 form-control-label">
                                                            <label for="FechaNacimiento">Fec. Nacimiento </label>
                                                        </div>
                                                        <div class="col-sm-4">
                                                            <div class="input-group">
                                                                <span class="input-group-addon">
                                                                    <i class="material-icons">date_range</i>
                                                                </span>
                                                                <div class="form-line">
                                                                    <input type="date" id="txt_FechaNacimiento" class="form-control" placeholder="dd/mm/yyyy" maxlength="10" disabled="disabled">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row clearfix">
                                                        <div class="col-sm-2 form-control-label">
                                                            <label for="RazonSocial">Razón Social</label>
                                                        </div>
                                                        <div class="col-sm-4">
                                                            <div class="form-group">
                                                                <div class="form-line">
                                                                    <input style="text-transform: uppercase" type="text" id="txt_RazonSocial" class="form-control" maxlength="200" disabled="disabled">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-2 form-control-label">
                                                            <label for="RUC">R.U.C.</label>
                                                        </div>
                                                        <div class="col-sm-4">
                                                            <div class="form-group">
                                                                <div class="form-line">
                                                                    <input type="text" id="txt_RUC" class="form-control" maxlength="11" disabled="disabled">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row clearfix">
                                                        <h5 class="title bg-light-blue">
                                                            <strong>Datos para el Pago</strong>
                                                        </h5>
                                                    </div>
                                                    <div class="row clearfix">
                                                        <div class="col-sm-2 form-control-label">
                                                            <label for="Tipo">Tipo</label>
                                                        </div>
                                                        <div class="col-sm-4">
                                                            <select id="cbo_Tipo" class="form-control" onchange="javascript:fn_verTipoPago();" disabled="disabled">
                                                                <option value="0">Seleccione</option>
                                                                <option value="1">Beneficiario</option>
                                                                <option value="2">Juzgado</option>
                                                                <option value="3">Telegiro</option>
                                                            </select>
                                                        </div>
                                                        <div class="col-sm-1 form-control-label">
                                                            <label for="Banco">Banco</label>
                                                        </div>
                                                        <div class="col-sm-5">
                                                            <select id="cbo_Bancos" class="form-control" disabled="disabled">
                                                                <option value="0">Seleccione</option>
                                                                <c:forEach var="j" items="${objBancos}">
                                                                    <option value="${j.codigo}">${j.descripcion}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="row clearfix">
                                                        <div class="col-sm-2 form-control-label">
                                                            <label for="Cuenta">Nro Cuenta</label>
                                                        </div>
                                                        <div class="col-sm-4">
                                                            <div class="form-group">
                                                                <div class="form-line">
                                                                    <input type="text" id="txt_Cuenta" class="form-control" maxlength="1000" disabled="disabled">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-1 form-control-label">
                                                            <label for="Cuenta">C.C.I.</label>
                                                        </div>
                                                        <div class="col-sm-5">
                                                            <div class="form-group">
                                                                <div class="form-line">
                                                                    <input type="text" id="txt_CCI" class="form-control" maxlength="1000" disabled="disabled">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row clearfix">
                                                        <div class="col-sm-2 form-control-label">
                                                            <label for="Cuenta">Juzgado</label>
                                                        </div>
                                                        <div class="col-sm-10">
                                                            <div class="form-group">
                                                                <div class="form-line">
                                                                    <input type="text" id="txt_NombreJuzgado" class="form-control" disabled="disabled">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="modal-footer ">
                                                    <button type="button" class="btn bg-red waves-effect btn-sm col-md-50" data-dismiss="modal">
                                                        <i class="material-icons">close</i>
                                                        <span>Cerrar</span>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Default Size -->
                                    <div class="modal fade" id="DetalleResolucion" tabindex="-1" role="dialog">
                                        <div class="modal-dialog modal-lg" role="document">
                                            <div class="modal-content">
                                                <form id="frm_ResolucionDetalle" name="frm_ResolucionDetalle" action="javascript: GuardarResolucionDetalle();"  method="post">
                                                    <div class="modal-body">
                                                        <div class="row clearfix">
                                                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                                <div class="card">
                                                                    <div class="header bg-teal">
                                                                        <h2 class="col-md-10 col-sm-2 col-xs-2">
                                                                            <strong>Datos del Descuento</strong>
                                                                        </h2>
                                                                    </div><br>
                                                                    <input type="checkbox" onclick=""/>
                                                                    <div class="row clearfix">
                                                                        <div class="col-sm-2 form-control-label">
                                                                            <label for="TipoPago">Tipo de Pago</label>
                                                                        </div>
                                                                        <div class="col-sm-2">
                                                                            <select id="cbo_TipoPago" class="form-control" onchange="javascript: verTipoImporte();" disabled="disabled">
                                                                                <option value="0">Seleccione</option>
                                                                                <option value="2">Porcentaje Liquido</option>
                                                                                <option value="3">Fijo</option>
                                                                                <option value="4">Cuotas</option>
                                                                            </select>
                                                                        </div>
                                                                        <div class="col-sm-1 form-control-label">
                                                                            <label for="Porcentaje">%</label>
                                                                        </div>
                                                                        <div class="col-sm-2">
                                                                            <div class="form-group">
                                                                                <div class="form-line">
                                                                                    <input type="text" id="txt_Porcentaje" class="form-control" maxlength="2" disabled="disabled">
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-sm-1 form-control-label">
                                                                            <label for="TipoMoneda">Moneda</label>
                                                                        </div>
                                                                        <div class="col-sm-3">
                                                                            <select id="cbo_TipoMoneda" class="form-control" disabled="disabled">
                                                                                <option value="0">Seleccione</option>
                                                                                <option value="01" selected="selected">Soles</option>
                                                                                <option value="02">Dolares Americanos</option>
                                                                            </select>
                                                                        </div>
                                                                    </div> 
                                                                    <div class="row clearfix">
                                                                        <div class="col-sm-2 form-control-label">
                                                                            <label for="Cuotas">Cuotas</label>
                                                                        </div>
                                                                        <div class="col-sm-1">
                                                                            <div class="form-group">
                                                                                <div class="form-line">
                                                                                    <input type="text" id="txt_Cuotas" class="form-control" maxlength="5" disabled="disabled">
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-sm-1 form-control-label">
                                                                            <label for="Cuotas">Monto</label>
                                                                        </div>
                                                                        <div class="col-sm-2">
                                                                            <div class="form-group">
                                                                                <div class="form-line">
                                                                                    <input type="text" id="txt_MontoCuota" class="form-control" maxlength="9" disabled="disabled">
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-sm-1 form-control-label">
                                                                            <label for="Total">Total</label>
                                                                        </div>
                                                                        <div class="col-sm-2">
                                                                            <div class="form-group">
                                                                                <div class="form-line">
                                                                                    <input type="text" id="txt_Total" class="form-control" maxlength="15" disabled="disabled">
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="header bg-teal">
                                                                        <h2 class="col-md-10 col-sm-9 col-xs-4">
                                                                            <strong>Listado de Conceptos Percibidos</strong>
                                                                        </h2>
                                                                        <br>
                                                                    </div>
                                                                    <div class="body table-responsive js-sweetalert">
                                                                        <div id="TablaResolucionDetalle">&nbsp;</div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer ">
                                                        <button type="button" class="btn bg-red waves-effect btn-sm col-md-50" data-dismiss="modal">
                                                            <i class="material-icons">close</i>
                                                            <span>Cerrar</span>
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
