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
        <link href="css/datatables.css" rel="stylesheet"/>
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
        <script src="javascript/datatables.js"></script>
        <script src="javascript/admin.js"></script>
        <script src="javascript/pages/forms/form-validation.js"></script>
        <script src="javascript/pages/tables/jquery-datatable.js"></script>
        <script src="javascript/pages/ui/tooltips-popovers.js"></script>
        <script src="javascript/pages/ui/dialogs.js"></script>
        <!-- Demo Js -->
        <script src="javascript/demo.js"></script>  
        <script src="javascript/pages/forms/basic-form-elements.js" type="text/javascript"></script>
        <script src="javascript/script.js"></script>
        <script type="text/javascript">
            var sentencia = '';
            var dni = '';
            var resolucion = '';
            var mode = null;
            var date = new Date();
            var pago = 0;
            var archivo = null;
            function NuevaBusqueda() {
                $('#txt_ApellidosBuscar').val('');
                $('#txt_DNIBuscar').val('');
                $('#DetalleBeneficiario').html("");
                $("#txt_ApellidosBuscar").focus();
                $('#BuscarBeneficiario .modal-content').removeAttr('class').addClass('modal-content modal-col-default');
                $('#BuscarBeneficiario').modal('show');
                $("#txt_ApellidosBuscar").focus();
            }
            function BuscarBeneficiario() {
                var personal = $('#txt_ApellidosBuscar').val();
                dni = $('#txt_DNIBuscar').val();
                $.ajax({
                    type: "GET",
                    url: "ConsultaBeneficiario",
                    data: {mode: 'B', personal: personal, dni: dni},
                    success: function (data) {
                        $('#DetalleBeneficiario').empty();
                        $('#DetalleBeneficiario').html(data);
                        $('#TablaBuscarBeneficiario').DataTable();
                    }
                });
            }
            function SeleccionaPersonal() {
                dni = $('input:radio[name=div_dni]:checked').val();
                $.ajax({
                    type: "GET",
                    url: "ConsultaBeneficiario",
                    data: {mode: 'A', dni: dni},
                    success: function (data) {
                        var dato = data.trim().split("+++");
                        if (dato.length === 2) {
                            $('#txt_DNI').val(dato[0]);
                            $("#txt_Beneficiario").val(dato[1]);
                            $('#BuscarBeneficiario .modal-content').removeAttr('class').addClass('modal-content modal-col-default');
                            $('#BuscarBeneficiario').modal('hide');
                            fn_BuscarDatos();
                        }
                    }
                });
            }
            function fn_BuscarDatos() {
                dni = $('#txt_DNI').val();
                if (dni !== '' || dni.length !== 0) {
                    $.ajax({
                        type: "GET",
                        url: "ConsultaBeneficiario",
                        data: {mode: 'G', dni: dni},
                        success: function (data) {
                            $('#DetalleSentencia').empty();
                            $('#DetalleSentencia').html(data);
                            $('#TablaSentencias').DataTable({
                                "order": [[5, "desc"], [0, "asc"], [1, "asc"]]
                            });
                            $('[data-toggle="tooltip"]').tooltip();
                            $('a[href="#ListarSentenciasJudiciales"]').trigger('click');
                        }
                    });
                } else {
                    swal("Aviso del Sistema!", 'Debe Seleccionar una Personal', "error");
                }
            }
            function fn_VerResolucion(xCIP, xSentencia, xResolucion) {
                $('a[href="#ListarPagosResoluciones"]').trigger('click');
                dni = $('#txt_DNI').val();
                $.ajax({
                    type: "GET",
                    url: "ConsultaBeneficiario",
                    data: {mode: 'S', dni: dni, cip: xCIP, sentencia: xSentencia, resolucion: xResolucion},
                    success: function (data) {
                        $('#DetallePagoResolucion').empty();
                        $('#DetallePagoResolucion').html(data);
                        $('#TablaPagosResolucion').DataTable({
                            "order": [[0, "desc"], [1, "desc"], [2, "asc"]]
                        });
                        $('[data-toggle="tooltip"]').tooltip();
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
                    $('#txt_Porcentaje').focus();
                }
                if (tipoPago === '3') {
                    $('#txt_Cuotas').val('');
                    $('#txt_Porcentaje').val('');
                    $('#txt_Porcentaje').attr('disabled', 'disabled');
                    $('#txt_Cuotas').attr('disabled', 'disabled');
                    $('#txt_MontoCuota').focus();
                }
                if (tipoPago === '4') {
                    $('#txt_Porcentaje').val('');
                    $('#txt_Porcentaje').attr('disabled', 'disabled');
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
            function fn_DetalleResolucion(xCip, xSentencia, xResolucion, xTipo) {                
                $.ajax({
                    type: "GET",
                    url: "ConsultaBeneficiario",
                    data: {mode: 'TP', cip: xCip, sentencia: xSentencia, resolucion: xResolucion, tipo: xTipo},
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
                            fn_calculaMontoTotal();
                            $.ajax({
                                type: "GET",
                                url: "ConsultaBeneficiario",
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
            function fn_verTipoPago() {
                var tipo = $('#cbo_Tipo').val();
                if (tipo === '1') {
                    $('#cbo_Bancos').removeAttr('disabled');
                    $('#txt_Cuenta').removeAttr('disabled');
                    $('#txt_CCI').removeAttr('disabled');
                    $('#txt_NombreJuzgado').val('');
                }
                if (tipo === '2') {
                    $('#cbo_Bancos').selectpicker('val', '0');
                    $('#cbo_Bancos').attr('disabled', 'disabled');
                    $('#txt_Cuenta').attr('disabled', 'disabled');
                    $('#txt_CCI').attr('disabled', 'disabled');
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
                    $('#cbo_Bancos').attr('disabled', 'disabled');
                    $('#txt_Cuenta').attr('disabled', 'disabled');
                    $('#txt_CCI').attr('disabled', 'disabled');
                    $('#txt_Cuenta').val('');
                    $('#txt_CCI').val('');
                    $('#txt_NombreJuzgado').val('');
                }
            }
            ﻿function ntabBuscarBeneficiario(evt, obj) {
                var nav4 = window.Event ? true : false;
                var key = nav4 ? evt.which : evt.keyCode;
                if (key === 13) {
                    BuscarBeneficiario();
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
            function fn_calculaMontoTotal() {
                var cuota = $('#txt_Cuotas').val();
                var monto = $('#txt_MontoCuota').val();
                var total = parseFloat(monto) * parseFloat(cuota);
                $('#txt_Total').val(total);
            }
            function fn_ActivaDetalle(xDetalle) {
                alert(xDetalle);
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
                            // $('#' + elemento.id).removeAttr('disabled');
                            $('#div_checkbox_' + elemento.id.substr(10, 2)).attr('checked', true);
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
                    <h3><strong>Consulta de Resoluciones por Beneficiario</strong></h3>
                </div>
                <!-- Tabs With Icon Title -->
                <div class="row clearfix">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="card">
                            <div class="header">
                                <div class="row clearfix">
                                    <div class="col-sm-1 form-control-label">
                                        <label for="DNI">DNI : </label>
                                    </div>
                                    <div class="col-sm-2">
                                        <div class="form-group">
                                            <div class="form-line">
                                                <input type="text" id="txt_DNI" class="form-control" disabled="disabled">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <button type="button" class="btn btn-light waves-effect btn-sm col-md-2 col-sm-1 col-xs-1" onclick="javascript:NuevaBusqueda();">
                                            <i class="material-icons">person_add</i>
                                            <span>Beneficiario</span>
                                        </button>
                                    </div>
                                    <div class="col-sm-2 form-control-label">
                                        <label for="Beneficiario">Beneficiario(a) : </label>
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="form-group">
                                            <div class="form-line">
                                                <input type="text" id="txt_Beneficiario" class="form-control" disabled="disabled">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <button type="button" class="btn btn-primary waves-effect btn-sm col-md-1 col-sm-1 col-xs-1" onclick="javascript: fn_BuscarDatos();">
                                            <i class="material-icons">search</i>
                                            <span>Buscar</span>
                                        </button>
                                    </div>                                    
                                </div>                                
                            </div>
                            <div class="body"> 
                                <!-- Nav tabs -->
                                <ul class="nav nav-tabs" role="tablist">
                                    <li role="presentation" class="active" id="sente">
                                        <a href="#ListarSentenciasJudiciales" data-toggle="tab">
                                            <i class="material-icons">assignment_returned </i> Sentencias Judiciales
                                        </a>
                                    </li>  
                                    <li role="presentation" id="resol" >
                                        <a href="#ListarPagosResoluciones" data-toggle="tab">
                                            <i class="material-icons">assignment_returned </i> Pagos Gestionados
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
                                    <!-- Lista Resoluciones-->
                                    <div role="tabpanel" class="tab-pane fade" id="ListarPagosResoluciones"> 
                                        <div class="row clearfix">
                                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                <div class="card">
                                                    <div class="header bg-blue-grey">
                                                        <h2 class="col-md-10 col-sm-9 col-xs-8">
                                                            <strong>Listado de Pagos Gestionados</strong>
                                                        </h2>                                                        
                                                        <br>
                                                    </div>
                                                    <div class="body">
                                                        <div class="body">
                                                            <div id="DetallePagoResolucion"></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Modal Dialogs ===================================== -->
                                    <!-- Default Size -->
                                    <div class="modal fade" id="BuscarBeneficiario" tabindex="-1" role="dialog">
                                        <div class="modal-dialog modal-lg" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h4 class="modal-title" id="defaultModalLabel">CONSULTA DE BENEFICIARIO</h4>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="row clearfix">
                                                        <div class="col-sm-3 form-control-label">
                                                            <label for="Apellidos">Apellidos y Nombres</label>
                                                        </div>
                                                        <div class="col-sm-4">
                                                            <div class="form-group">
                                                                <div class="form-line">
                                                                    <input type="text" style="text-transform: uppercase" id="txt_ApellidosBuscar" class="form-control" maxlength="100" onKeyPress="ntabBuscarBeneficiario(event, 'txt_ApellidosBuscar');" >
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-1 form-control-label">
                                                            <label for="dni">DNI</label>
                                                        </div>
                                                        <div class="col-sm-2">
                                                            <div class="form-group">
                                                                <div class="form-line">
                                                                    <input type="text" id="txt_DNIBuscar" class="form-control" maxlength="10" onKeyPress="ntabBuscarBeneficiario(event, 'txt_DNIBuscar');">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <button type="button" class="btn btn-primary waves-effect btn-sm col-md-1 col-sm-1 col-xs-1" onclick="javascript:BuscarBeneficiario();">
                                                            <i class="material-icons">search</i>
                                                            <span>&nbsp;</span>
                                                        </button> 
                                                    </div>
                                                    <div class="row clearfix">
                                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                            <div class="card">
                                                                <div class="header bg-teal">
                                                                    <h2 class="col-md-10 col-sm-2 col-xs-2">
                                                                        <strong>Listado de Beneficiario</strong>
                                                                    </h2>
                                                                    <br>
                                                                </div>
                                                                <div class="body">
                                                                    <div id="DetalleBeneficiario"></div>   
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
                                                                        <div class="col-sm-3">
                                                                            <select id="cbo_TipoPago" class="form-control" onchange="javascript: verTipoImporte();" disabled="disabled">
                                                                                <option value="0">Seleccione</option>
                                                                                <option value="1">Porcentaje Bruto</option>
                                                                                <option value="2">Porcentaje Liquido</option>
                                                                                <option value="3">Fijo</option>
                                                                                <option value="4">Cuotas</option>
                                                                            </select>
                                                                        </div>
                                                                        <div class="col-sm-1 form-control-label">
                                                                            <label for="Porcentaje">%</label>
                                                                        </div>
                                                                        <div class="col-sm-1">
                                                                            <div class="form-group">
                                                                                <div class="form-line">
                                                                                    <input type="text" id="txt_Porcentaje" class="form-control" maxlength="2" disabled="disabled">
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div> 
                                                                    <div class="row clearfix">
                                                                        <div class="col-sm-2 form-control-label">
                                                                            <label for="Cuotas">Cuotas</label>
                                                                        </div>
                                                                        <div class="col-sm-1">
                                                                            <div class="form-group">
                                                                                <div class="form-line">
                                                                                    <input type="text" id="txt_Cuotas" class="form-control" onkeyup="javascript: fn_calculaMontoTotal();" maxlength="5" disabled="disabled">
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-sm-1 form-control-label">
                                                                            <label for="Cuotas">Monto</label>
                                                                        </div>
                                                                        <div class="col-sm-2">
                                                                            <div class="form-group">
                                                                                <div class="form-line">
                                                                                    <input type="text" id="txt_MontoCuota" class="form-control" onkeyup="javascript: fn_calculaMontoTotal();" maxlength="7" disabled="disabled">
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