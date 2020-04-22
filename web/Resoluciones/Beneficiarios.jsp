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
        <script src="javascript/admin.js"></script>
        <script src="javascript/datatables.js"></script>
        <script src="javascript/pages/forms/form-validation.js"></script>
        <script src="javascript/pages/tables/jquery-datatable.js"></script>
        <script src="javascript/pages/ui/dialogs.js"></script>
        <script src="javascript/pages/ui/tooltips-popovers.js"></script>
        <!-- Demo Js -->
        <script src="javascript/demo.js"></script>
        <script src="javascript/script.js"></script>
        <script type="text/javascript">
            var codigo = null;
            var cuenta = null;
            var mode = null;
            fn_cargarBancos();
            function BuscarDatos() {
                var tipoDocumento = $('#cbo_TipoDocumentoBuscar').val();
                var numeroDocumento = $('#txt_NumeroDocumentoBuscar').val();
                $.ajax({
                    type: "GET",
                    url: "Beneficiarios",
                    data: {mode: 'G', tipoDocumento: tipoDocumento, codigo: numeroDocumento},
                    success: function (data) {
                        $('#DetalleBeneficiarios').empty();
                        $('#DetalleBeneficiarios').html(data);
                        $('#TablaResultados').DataTable();
                        $('[data-toggle="tooltip"]').tooltip();
                        $('a[href="#ListarBeneficiarios"]').trigger('click');
                    }
                });
            }
            function fn_DetalleRegistroBeneficiario(xCodigo) {
                codigo = xCodigo;
                $('a[href="#ListarCuentas"]').trigger('click');
                $.ajax({
                    type: "GET",
                    url: "Beneficiarios",
                    data: {mode: 'S', codigo: codigo},
                    success: function (data) {
                        $('#DetalleCuentas').empty();
                        $('#DetalleCuentas').html(data);
                        $('#TablaCuentas').DataTable();
                        $('[data-toggle="tooltip"]').tooltip();
                    }
                });
            }
            function NuevoRegistro() {
                mode = 'I';
                codigo = 0;
                $('#cbo_TipoDocumento').selectpicker('val', '1');
                $('#txt_NumeroDocumento').val('');
                $('#txt_Paterno').val('');
                $('#txt_Materno').val('');
                $('#txt_Nombres').val('');
                $('#txt_FechaNacimiento').val('');
                $('#txt_RazonSocial').val('');
                $('#cbo_TipoDocumento').removeAttr('disabled');
                $('#txt_NumeroDocumento').removeAttr('disabled');
                $('#RegistrarBeneficiario .modal-content').removeAttr('class').addClass('modal-content modal-col-default');
                $('#RegistrarBeneficiario').modal('show');
            }
            function fn_EditarRegistro(xCodigo) {
                mode = 'U';
                codigo = xCodigo;
                $('#txt_NumeroDocumento').val(xCodigo);
                $.ajax({
                    type: "GET",
                    url: "Beneficiarios",
                    data: {mode: mode, codigo: codigo},
                    success: function (data) {
                        var dato = data.trim().split("+++");
                        if (dato.length === 7) {
                            $('#cbo_TipoDocumento').selectpicker('val', dato[0]);
                            $('#txt_Paterno').val(dato[1]);
                            $('#txt_Materno').val(dato[2]);
                            $('#txt_Nombres').val(dato[3]);
                            $('#txt_FechaNacimiento').val(dato[4]);
                            $('#txt_RazonSocial').val(dato[5]);
                            $('#txt_RUC').val(dato[6]);
                            $('#cbo_TipoDocumento').attr('disabled', 'disabled');
                            $('#txt_NumeroDocumento').attr('disabled', 'disabled');
                            $('#RegistrarBeneficiario.modal-content').removeAttr('class').addClass('modal-content modal-col-default');
                            $('#RegistrarBeneficiario').modal('show');
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
                var tipoDocumento = $('#cbo_TipoDocumento').val();
                var paterno = $('#txt_Paterno').val();
                var materno = $('#txt_Materno').val();
                var nombres = $('#txt_Nombres').val();
                var fechaNacimiento = $('#txt_FechaNacimiento').val();
                var razonSocial = $('#txt_RazonSocial').val();
                var ruc = $('#txt_RUC').val();
                $.ajax({
                    type: "POST",
                    url: "IduBeneficiarios",
                    data: {mode: mode, codigo: codigo, tipoDocumento: tipoDocumento, paterno: paterno, materno: materno,
                        nombres: nombres, fechaNacimiento: fechaNacimiento, razonSocial: razonSocial, ruc: ruc},
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
                            $('#RegistrarBeneficiario').modal('hide');
                            BuscarDatos();
                        } else {
                            swal("Aviso del Sistema!", msg, "error");
                        }
                    }
                });
            }
            function fn_TipoDocumento() {
                var tipoDocumento = $('#cbo_TipoDocumento').val();
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
            }
            function fn_nuevoRegistroCuenta() {
                mode = 'I';
                cuenta = 0;
                $('#cbo_Banco').selectpicker('val', '0');
                $('#txt_NumeroCuenta').val('');
                $('#txt_CCI').val('');
                $('#RegistrarBeneficiarioCuenta .modal-content').removeAttr('class').addClass('modal-content modal-col-default');
                $('#RegistrarBeneficiarioCuenta').modal('show');
            }
            function fn_EditarRegistroCuenta(xCuenta) {
                mode = 'U';
                cuenta = xCuenta;
                $.ajax({
                    type: "GET",
                    url: "Beneficiarios",
                    data: {mode: 'C', codigo: codigo, cuenta: cuenta},
                    success: function (data) {
                        var dato = data.trim().split("+++");
                        if (dato.length === 3) {
                            $('#cbo_Banco').selectpicker('val', dato[0]);
                            $('#txt_NumeroCuenta').val(dato[1]);
                            $('#txt_CCI').val(dato[2]);
                            $('#RegistrarBeneficiarioCuenta.modal-content').removeAttr('class').addClass('modal-content modal-col-default');
                            $('#RegistrarBeneficiarioCuenta').modal('show');
                        }
                    }
                });
            }
            function fn_GuardarDatosCuenta() {
                var tipoDocumento = $('#cbo_TipoDocumentoBuscar').val();
                var banco = $('#cbo_Banco').val();
                var numeroCuenta = $('#txt_NumeroCuenta').val();
                var cci = $('#txt_CCI').val();
                $.ajax({
                    type: "POST",
                    url: "IduBeneficiariosCuenta",
                    data: {mode: mode, tipoDocumento: tipoDocumento, codigo: codigo, cuenta: cuenta, banco: banco, numeroCuenta: numeroCuenta, cci: cci},
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
                            $('#RegistrarBeneficiarioCuenta').modal('hide');
                            fn_DetalleRegistroBeneficiario(codigo);
                        } else {
                            swal("Aviso del Sistema!", msg, "error");
                        }
                    }
                });
            }
            function fn_cargarBancos() {
                $.ajax({
                    type: "GET",
                    url: "Combos",
                    data: {accion: 'BANCO'},
                    success: function (data) {
                        $('#cbo_Banco').empty();
                        $('#cbo_Banco').append(data);
                        $('#cbo_Banco').selectpicker('refresh');
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
                    <h3><strong>Administración de Beneficiarios</strong></h3>
                </div>
                <!-- Tabs With Icon Title -->
                <div class="row clearfix">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="card">
                            <div class="header">
                                <div class="row clearfix">
                                    <div class="col-sm-2 form-control-label">
                                        <label for="TipoDocumento">Tipo de Documento</label>
                                    </div>
                                    <div class="col-sm-2">
                                        <select id="cbo_TipoDocumentoBuscar" class="form-control">                                            
                                            <option value="0">Seleccione</option>
                                            <option value="1">D.N.I.</option>                                            
                                            <option value="2">Carnet Extranjeria</option>
                                        </select> 
                                    </div>
                                    <div class="col-sm-2 form-control-label">
                                        <label for="NumeroDocumento">Nro Documento</label>
                                    </div>
                                    <div class="col-sm-2">
                                        <div class="form-group">
                                            <div class="form-line">
                                                <input type="text" id="txt_NumeroDocumentoBuscar" name="txt_NumeroDocumentoBuscar" class="form-control" maxlength="11">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <button type="button" class="btn btn-primary waves-effect btn-sm col-md-1 col-sm-1 col-xs-1" onclick="javascript:BuscarDatos();">
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
                                        <a href="#ListarBeneficiarios" data-toggle="tab">
                                            <i class="material-icons">assignment_returned </i> Beneficiarios
                                        </a>
                                    </li>  
                                    <li role="presentation" id="resol" >
                                        <a href="#ListarCuentas" data-toggle="tab">
                                            <i class="material-icons">assignment_returned </i> Cuentas
                                        </a>
                                    </li>  
                                </ul>
                                <!-- Tab panes -->
                                <div class="tab-content">
                                    <div role="tabpanel" class="tab-pane fade in active" id="ListarBeneficiarios">
                                        <!-- Striped Rows -->
                                        <div class="row clearfix">
                                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                <div class="card">
                                                    <div class="header bg-teal">
                                                        <h2 class="col-md-10 col-sm-9 col-xs-8">
                                                            <strong>Listado de Beneficiarios</strong>
                                                        </h2>
                                                        <button type="button" class="btn btn-success waves-effect btn-sm col-md-2 col-sm-3 col-xs-4" onclick="javascript:NuevoRegistro();">
                                                            <i class="material-icons">add </i>
                                                            <span>Nuevo Registro</span>
                                                        </button>
                                                        <br>
                                                    </div>
                                                    <div class="body">
                                                        <div id="DetalleBeneficiarios">&nbsp;</div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- #END# Striped Rows -->
                                    </div>
                                    <!-- Lista Resoluciones-->
                                    <div role="tabpanel" class="tab-pane fade" id="ListarCuentas"> 
                                        <div class="row clearfix">
                                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                <div class="card">
                                                    <div class="header bg-blue-grey">
                                                        <h2 class="col-md-10 col-sm-9 col-xs-8">
                                                            <strong>Lista de Cuentas</strong>
                                                        </h2>
                                                        <button type="button" class="btn bg-light-green waves-effect btn-sm col-md-2 col-sm-3 col-xs-4" onclick="javascript: fn_nuevoRegistroCuenta();">
                                                            <i class="material-icons">add </i>
                                                            <span>Nueva Cuenta</span>
                                                        </button>
                                                        <br>
                                                    </div>
                                                    <div class="body">
                                                        <div class="body">
                                                            <div id="DetalleCuentas">&nbsp;</div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Modal Dialogs ===================== -->
                                    <!-- Default Size -->
                                    <div class="modal fade" id="RegistrarBeneficiario" tabindex="-1" role="dialog">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <form id="frm_Beneficiario" name="frm_Beneficiario" action="javascript:GuardarDatos();" method="post">
                                                    <div class="modal-header">
                                                        <h4 class="modal-title" id="defaultModalLabel">REGISTRO DE BENEFICIARIOS</h4>
                                                    </div>
                                                    <div class="modal-body">                                                        
                                                        <div class="row clearfix">
                                                            <div class="col-sm-3 form-control-label">
                                                                <label for="TipoDocumento">Tipo Doc.</label>
                                                            </div>
                                                            <div class="col-sm-3">
                                                                <select id="cbo_TipoDocumento" name="cbo_TipoDocumento" class="form-control" data-live-search="true" onchange="javascript: fn_TipoDocumento();">
                                                                    <option value="1" selected="">D.N.I.</option>                                                                    
                                                                    <option value="2">Carnet Extranjeria</option>
                                                                </select>
                                                            </div>
                                                            <div class="col-sm-2 form-control-label">
                                                                <label for="numero">Número</label>
                                                            </div>
                                                            <div class="col-sm-4">
                                                                <div class="form-group">
                                                                    <div class="form-line">
                                                                        <input type="text" id="txt_NumeroDocumento" name="txt_NumeroDocumento" class="form-control" maxlength="11" onKeyPress="ntab(event, 'txt_Paterno');" >
                                                                    </div>
                                                                </div>
                                                            </div>                                                      
                                                        </div>
                                                        <div class="row clearfix">
                                                            <div class="col-sm-3 form-control-label">
                                                                <label for="asunto">Ap. Paterno</label>
                                                            </div>
                                                            <div class="col-sm-9">
                                                                <div class="form-group">
                                                                    <div class="form-line">
                                                                        <input type="text" style="text-transform: uppercase" id="txt_Paterno" name="txt_Paterno" class="form-control" maxlength="100" onKeyPress="ntab(event, 'txt_Materno');" >
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row clearfix">
                                                            <div class="col-sm-3 form-control-label">
                                                                <label for="asunto">Ap. Materno</label>
                                                            </div>
                                                            <div class="col-sm-9">
                                                                <div class="form-group">
                                                                    <div class="form-line">
                                                                        <input type="text" style="text-transform: uppercase" id="txt_Materno" name="txt_Materno" class="form-control" maxlength="100" onKeyPress="ntab(event, 'txt_Nombres');" >
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row clearfix">
                                                            <div class="col-sm-3 form-control-label">
                                                                <label for="asunto">Nombres</label>
                                                            </div>
                                                            <div class="col-sm-9">
                                                                <div class="form-group">
                                                                    <div class="form-line">
                                                                        <input type="text" style="text-transform: uppercase" id="txt_Nombres" name="txt_Nombres" class="form-control" maxlength="100" onKeyPress="ntab(event, 'txt_FechaNacimiento');" >
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row clearfix">
                                                            <div class="col-sm-3 form-control-label">
                                                                <label for="FechaNacimiento">Fec. Nacimiento</label>
                                                            </div>
                                                            <div class="col-sm-4"> 
                                                                <div class="input-group">
                                                                    <span class="input-group-addon">
                                                                        <i class="material-icons">date_range</i>
                                                                    </span>
                                                                    <div class="form-line">
                                                                        <input type="date" id="txt_FechaNacimiento" class="form-control" placeholder="dd/mm/yyyy" maxlength="10" onKeyPress="ntab(event, 'txt_RUC');" >
                                                                    </div>
                                                                </div>
                                                            </div>                             
                                                        </div>
                                                        <div class="row clearfix">
                                                            <div class="col-sm-3 form-control-label">
                                                                <label for="RUC">R.U.C.</label>
                                                            </div>
                                                            <div class="col-sm-9">
                                                                <div class="form-group">
                                                                    <div class="form-line">
                                                                        <input type="text" id="txt_RUC" name="txt_RUC" class="form-control" maxlength="11" onKeyPress="ntab(event, 'txt_RazonSocial');" >
                                                                    </div>
                                                                </div>
                                                            </div>                                                            
                                                        </div> 
                                                        <div class="row clearfix">
                                                            <div class="col-sm-3 form-control-label">
                                                                <label for="RazonSocial">Razón Social</label>
                                                            </div>
                                                            <div class="col-sm-9">
                                                                <div class="form-group">
                                                                    <div class="form-line">
                                                                        <input type="text" style="text-transform: uppercase" id="txt_RazonSocial" name="txt_RazonSocial" class="form-control" maxlength="200" onKeyPress="ntab(event, 'txt_RazonSocial');" >
                                                                    </div>
                                                                </div>
                                                            </div>                                                            
                                                        </div> 
                                                    </div>
                                                    <div class="modal-footer ">
                                                        <button type="submit" class="btn bg-blue waves-effect btn-sm col-md-30" >
                                                            <i class="material-icons">save </i>
                                                            <span>Registrar</span>
                                                        </button>
                                                        <button type="button" class="btn bg-red waves-effect btn-sm col-md-40" data-dismiss="modal">
                                                            <i class="material-icons">cancel </i>
                                                            <span>Cancelar</span>
                                                        </button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Default Size -->
                                    <!-- Default Size -->
                                    <div class="modal fade" id="RegistrarBeneficiarioCuenta" tabindex="-1" role="dialog">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <form id="frm_BeneficiarioCuenta" name="frm_BeneficiarioCuenta" action="javascript: fn_GuardarDatosCuenta();" method="post">
                                                    <div class="modal-header">
                                                        <h4 class="modal-title" id="defaultModalLabel">REGISTRO DE CUENTAS</h4>
                                                    </div>
                                                    <div class="modal-body">                                                        
                                                        <div class="row clearfix">
                                                            <div class="col-sm-3 form-control-label">
                                                                <label for="Banco">Banco</label>
                                                            </div>
                                                            <div class="col-sm-9">
                                                                <select id="cbo_Banco" name="cbo_Banco" class="form-control" data-live-search="true">
                                                                    <option value="0" selected="">Seleccione</option>
                                                                </select>
                                                            </div>                                                                                                        
                                                        </div>
                                                        <div class="row clearfix">
                                                            <div class="col-sm-3 form-control-label">
                                                                <label for="NumeroCuenta">Nro. Cuenta</label>
                                                            </div>
                                                            <div class="col-sm-9">
                                                                <div class="form-group">
                                                                    <div class="form-line">
                                                                        <input type="text" id="txt_NumeroCuenta" name="txt_NumeroCuenta" class="form-control" maxlength="50" onKeyPress="ntab(event, 'txt_CCI');" required >
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div> 
                                                        <div class="row clearfix">
                                                            <div class="col-sm-3 form-control-label">
                                                                <label for="NumeroCCI">Nro. CCI</label>
                                                            </div>
                                                            <div class="col-sm-9">
                                                                <div class="form-group">
                                                                    <div class="form-line">
                                                                        <input type="text" id="txt_CCI" name="txt_CCI" class="form-control" maxlength="50" onKeyPress="ntab(event, 'txt_CCI');" >
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div> 
                                                    </div>
                                                    <div class="modal-footer ">
                                                        <button type="submit" class="btn bg-blue waves-effect btn-sm col-md-30" >
                                                            <i class="material-icons">save </i>
                                                            <span>Registrar</span>
                                                        </button>
                                                        <button type="button" class="btn bg-red waves-effect btn-sm col-md-40" data-dismiss="modal">
                                                            <i class="material-icons">cancel </i>
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