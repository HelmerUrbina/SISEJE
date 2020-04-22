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
                $('#cbo_Estado').selectpicker('val', 'AC');
                BuscarDatos();
            });
            var codigo = null;
            var mode = null;
            var msg = null;
            var archivo = null;
            function BuscarDatos() {
                var periodo = $('#cbo_Periodo').val();
                var mes = $('#cbo_Mes').val();
                var estado = $('#cbo_Estado').val();
                $.ajax({
                    type: "GET",
                    url: "Decretos",
                    data: {mode: 'B', periodo: periodo, mes: mes, estado: estado},
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
            function NuevoRegistro(xNumero) {
                var strDate = date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear();
                mode = 'I';
                codigo = 0;
                $('#txt_MesaPartes').val(('000000' + xNumero).slice(-6));
                $('#cbo_TipoDecretos').selectpicker('deselectAll');
                $('#cbo_Seccion').selectpicker('deselectAll');
                $('#cbo_Seccion').selectpicker('val', 0);
                $('#cbo_Areas').selectpicker('deselectAll');
                $('#cbo_Areas').selectpicker('val', 0);
                $('#txt_Fecha').val(strDate);
                $('#cbo_Prioridad').selectpicker('val', '1');
                $('#txt_Observacion').val('');
                $('#cbo_TipoConceptos').selectpicker('deselectAll');
                $('#cbo_TipoConceptos').selectpicker('val', '0');
                $('#div_Prorrateo').prop('checked', false);
                $('#txt_Prorrateo').val('');
                $('#div_Prorrateo').removeAttr('disabled');
                $('#txt_Prorrateo').attr('disabled', 'disabled');
                $('#RegistrarDecretos .modal-content').removeAttr('class').addClass('modal-content modal-col-default');
                $('#RegistrarDecretos').modal('show');
            }
            function EditarRegistro(xNumero, xCodigo) {
                codigo = xCodigo;
                mode = 'U';
                var periodo = $('#cbo_Periodo').val();
                $.ajax({
                    type: "GET",
                    url: "Decretos",
                    data: {mode: mode, periodo: periodo, numero: xNumero, codigo: codigo},
                    success: function (data) {
                        var dato = data.trim().split("+++");
                        if (dato.length === 6) {
                            $('#div_Prorrateo').attr('disabled', 'disabled');
                            $('#txt_Prorrateo').attr('disabled', 'disabled');
                            $('#txt_Prorrateo').val('');
                            $('#cbo_Seccion').selectpicker('val', dato[0]);
                            cargarAreas();
                            $('#txt_MesaPartes').val(xNumero);
                            $('#txt_Fecha').val(dato[2]);
                            $("#txt_Observacion").val(dato[3]);
                            $('#cbo_Prioridad').selectpicker('val', dato[4]);
                            $.ajax({
                                type: "GET",
                                url: "Decretos",
                                data: {mode: 'T', periodo: periodo, numero: xNumero, codigo: codigo},
                                success: function (data) {
                                    var valor = data.trim().split("+++");
                                    var tipo = [];
                                    for (var i = 0; i < valor.length - 1; i++) {
                                        tipo.push(valor[i].trim());
                                    }
                                    $('#cbo_TipoDecretos').selectpicker('val', tipo);
                                    $.ajax({
                                        type: "GET",
                                        url: "Decretos",
                                        data: {mode: 'R', periodo: periodo, numero: xNumero, codigo: codigo},
                                        success: function (data) {
                                            var valor = data.trim().split("+++");
                                            var remu = [];
                                            for (var i = 0; i < valor.length - 1; i++) {
                                                remu.push(valor[i].trim());
                                            }
                                            $('#cbo_TipoConceptos').selectpicker('val', remu);                                           
                                            $.ajax({
                                                type: "GET",
                                                url: "Decretos",
                                                data: {mode: 'A', periodo: periodo, numero: xNumero, codigo: codigo},
                                                success: function (data) {                                                    
                                                    var valor = data.trim().split("+++");
                                                    var area = [];
                                                    for (var i = 0; i < valor.length - 1; i++) {
                                                        area.push(valor[i].trim());
                                                    }
                                                    $('#cbo_Areas').selectpicker('val', area);
                                                }
                                            });
                                        }
                                    });
                                }
                            });
                            $('#RegistrarDecretos .modal-content').removeAttr('class').addClass('modal-content modal-col-default');
                            $('#RegistrarDecretos').modal('show');
                        }
                    }
                });
            }
            function VerRegistro(xCodigo) {
                var periodo = $('#cbo_Periodo').val();
                $.ajax({
                    type: "GET",
                    url: "MesaPartes",
                    data: {mode: 'U', periodo: periodo, codigo: xCodigo},
                    success: function (data) {
                        var dato = data.trim().split("+++");
                        if (dato.length === 12) {
                            $('#txt_Numero').val(dato[0]);
                            $("#txt_FechaRecepcion").val(dato[1]);
                            $('#cbo_TipoDocumento').selectpicker('val', dato[2]);
                            $("#txt_NumeroDocumento").val(dato[3]);
                            $("#txt_Indicativo").val(dato[4]);
                            $("#txt_Asunto").val(dato[5]);
                            $("#txt_FechaDocumento").val(dato[6]);
                            $('#cbo_Prioridad').selectpicker('val', dato[7]);
                            $("#txt_Folios").val(dato[8]);
                            $('#cbo_Juzgados').selectpicker('val', dato[9]);
                            $("#txt_PostFirma").val(dato[10]);
                            archivo = dato[11];
                            $('#VerMesaPartes .modal-content').removeAttr('class').addClass('modal-content modal-col-default');
                            $('#VerMesaPartes').modal('show');
                        }
                    }
                });
            }
            function EliminarRegistro(xNumero, xCodigo) {
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
                    $('#txt_MesaPartes').val(xNumero);
                    codigo = xCodigo;
                    GuardarDatos();
                });
            }
            function GuardarDatos() {
                var periodo = $('#cbo_Periodo').val();
                var numero = $('#txt_MesaPartes').val();
                var fecha = $("#txt_Fecha").val();
                var seccion = $("#cbo_Seccion").val();
                var area = $("#cbo_Areas").val();
                var prioridad = $("#cbo_PrioridadDecreto").val();
                var decreto = $("#cbo_TipoDecretos").val();
                var prorrateo = $("#txt_Prorrateo").val();
                var observacion = $("#txt_Observacion").val();
                var conceptos = $("#cbo_TipoConceptos").val();
                $.ajax({
                    type: "POST",
                    url: "IduDecretos",
                    data: {mode: mode, codigo: codigo, numero: numero, periodo: periodo, fecha: fecha,
                        seccion: seccion, area: JSON.stringify(area), prorrateo: prorrateo, prioridad: prioridad,
                        decreto: JSON.stringify(decreto), observacion: observacion, conceptos: JSON.stringify(conceptos)},
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
                            $('#RegistrarDecretos').modal('hide');
                            BuscarDatos();
                        } else {
                            swal("Aviso del Sistema!", msg, "error");
                        }
                    }
                });
            }
            function pad(str, max) {
                str = str.toString();
                return str.length < max ? pad("0" + str, max) : str;
            }
            function fn_verDocumento() {
                var periodo = $('#cbo_Periodo').val();
                var numero = parseInt($('#txt_Numero').val());
                if (archivo !== null && archivo !== '') {
                    document.location.target = "_blank";
                    document.location.href = "Descarga?opcion=MesaParte&periodo=" + periodo + "&codigo=" + numero + "&documento=" + archivo;
                } else {
                    swal("Aviso del Sistema!", 'No se encuentra el Archivo!', "error");
                }
            }
            function cargarAreas() {
                var seccion = $("#cbo_Seccion").val();
                $.ajax({
                    type: "GET",
                    url: "Combos",
                    data: {accion: 'AREAS', codigo: seccion},
                    success: function (data) {
                        $('#cbo_Areas').empty();
                        $('#cbo_Areas').append(data);
                        $('#cbo_Areas').selectpicker('refresh');
                    }
                });
            }
            function fn_ActivaProrrateo() {
                if ($('#div_Prorrateo').is(':checked')) {
                    $('#txt_Prorrateo').removeAttr('disabled');
                    $('#txt_Prorrateo').focus();
                } else {
                    $('#txt_Prorrateo').val('');
                    $('#txt_Prorrateo').attr('disabled', 'disabled');
                }
            }
        </script>
    </head>
    <body class="theme-teal">
        <%@include file="../Login/PaginaCarga.jspf"%>
        <!-- Muestra el contenido -->
        <section class="content">
            <div class="container-fluid">
                <div class="block-header">
                    <h3><strong>Administración de Decretos de Documentos</strong></h3>
                </div>
                <!-- Tabs With Icon Title -->
                <div class="row clearfix">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="card">
                            <div class="header">
                                <div class="row clearfix">
                                    <div class="col-sm-2 form-control-label">
                                        <label for="TipoDocumento">Periodo</label>
                                    </div>
                                    <div class="col-sm-2">
                                        <select id="cbo_Periodo" class="form-control" onchange="javascript:BuscarDatos();">
                                            <c:forEach var="b" items="${objPeriodos}"> 
                                                <option value="${b.codigo}">${b.descripcion}</option>
                                            </c:forEach>
                                        </select> 
                                    </div>
                                    <div class="col-sm-1 form-control-label">
                                        <label for="numero">Mes</label>
                                    </div>
                                    <div class="col-sm-2">
                                        <select id="cbo_Mes" class="form-control" onchange="javascript:BuscarDatos();">
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
                                        <label for="numero">Estado</label>
                                    </div>
                                    <div class="col-sm-2">
                                        <select id="cbo_Estado" class="form-control" onchange="javascript:BuscarDatos();">
                                            <option value="AC">Pendiente</option>
                                            <option value="DE">Decretado</option>
                                        </select>
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
                                <!-- Tab panes -->
                                <div class="tab-content">
                                    <div role="tabpanel" class="tab-pane fade in active" id="ListarMesaPartes">
                                        <!-- Striped Rows -->
                                        <div class="row clearfix">
                                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                <div class="card">
                                                    <div class="header bg-teal">
                                                        <h2 class="col-md-10 col-sm-9 col-xs-8">
                                                            <strong>Listado de Decretos de Documentos</strong>
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
                                    <!-- Modal Dialogs ============================= -->
                                    <!-- Default Size -->
                                    <div class="modal fade" id="RegistrarDecretos" tabindex="-1" role="dialog">
                                        <div class="modal-dialog modal-lg" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h4 class="modal-title" id="defaultModalLabel">REGISTRO DE DECRETOS DE DOCUMENTOS</h4>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="row clearfix">
                                                        <div class="col-sm-2 form-control-label">
                                                            <label for="MesaPartes">Correlativo</label>
                                                        </div>
                                                        <div class="col-sm-2">
                                                            <div class="form-group">
                                                                <div class="form-line">
                                                                    <input type="text" id="txt_MesaPartes" class="form-control" maxlength="50" required disabled="disabled">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-2 form-control-label">
                                                            <label for="FechaRecepcion">Fecha</label>
                                                        </div>
                                                        <div class="col-sm-2">
                                                            <div class="form-group">
                                                                <div class="form-line">
                                                                    <input type="text" id="txt_Fecha" class="form-control date" disabled="disabled">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div> 
                                                    <div class="row clearfix">
                                                        <div class="col-sm-2 form-control-label">
                                                            <label for="Secciones">Sección</label>
                                                        </div>
                                                        <div class="col-sm-3">
                                                            <select id="cbo_Seccion" class="form-control selectpicker" onchange="javascript:cargarAreas();">
                                                                <option value="0">Seleccione</option>
                                                                <c:forEach var="e" items="${objSecciones}"> 
                                                                    <option value="${e.codigo}">${e.descripcion}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div class="col-sm-1 form-control-label">
                                                            <label for="Area">Area</label>
                                                        </div>
                                                        <div class="col-sm-3">
                                                            <select id="cbo_Areas" class="form-control selectpicker" multiple>
                                                                <option value="0">Seleccione</option>
                                                            </select>
                                                        </div>
                                                        <div class="col-sm-1 form-control-label">
                                                            <label for="Prioridad">Prioridad</label>
                                                        </div>
                                                        <div class="col-sm-2">
                                                            <select id="cbo_PrioridadDecreto" class="form-control">
                                                                <c:forEach var="f" items="${objPrioridad}">
                                                                    <option value="${f.codigo}">${f.descripcion}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="row clearfix">
                                                        <div class="col-sm-2 form-control-label">
                                                            <label for="Decreto">Decreto</label>
                                                        </div>
                                                        <div class="col-sm-10">
                                                            <select id="cbo_TipoDecretos" class="form-control selectpicker" multiple>
                                                                <c:forEach var="g" items="${objTipoDecretos}">
                                                                    <option value="${g.codigo}">${g.descripcion}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="row clearfix">
                                                        <div class="col-sm-2 form-control-label">
                                                            <label for="Observacion">Observación</label>
                                                        </div>
                                                        <div class="col-sm-10">
                                                            <div class="form-group">
                                                                <div class="form-line">
                                                                    <input style="text-transform: uppercase" type="text" id="txt_Observacion" class="form-control" maxlength="1000" required>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row clearfix">
                                                        <div class="col-sm-2 form-control-label">
                                                            <label for="Conceptos">Conceptos</label>
                                                        </div>
                                                        <div class="col-sm-10">
                                                            <select id="cbo_TipoConceptos" class="form-control selectpicker" multiple>
                                                                <c:forEach var="h" items="${objTipoRemuneracion}">
                                                                    <option value="${h.codigo}">${h.descripcion}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="row clearfix">
                                                        <div class="col-sm-3">
                                                            <input type="checkbox" id="div_Prorrateo" onclick="javascript: fn_ActivaProrrateo();" class="chk-col-blue"/><label for="div_Prorrateo"><strong>Prorrateo, Reintegro, Cambio Beneficiaria(o) : </strong></label> 
                                                        </div>
                                                        <div class="col-sm-2">
                                                            <div class="form-group">
                                                                <div class="form-line">
                                                                    <input type="number" id="txt_Prorrateo" class="form-control" maxlength="2" min="0" max="99"  >
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn bg-blue waves-effect btn-sm col-md-40" onclick="javascript:GuardarDatos();">
                                                        <i class="material-icons">save </i>
                                                        <span>Registrar</span>
                                                    </button>
                                                    <button type="button" class="btn bg-red waves-effect btn-sm col-md-50" data-dismiss="modal">
                                                        <i class="material-icons">cancel </i>
                                                        <span>Cancelar</span>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Default Size -->
                                    <div class="modal fade" id="VerMesaPartes" tabindex="-1" role="dialog">
                                        <div class="modal-dialog modal-lg" role="document">
                                            <div class="modal-content">
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
                                                                    <input type="date" id="txt_FechaRecepcion" class="form-control" disabled="disabled">
                                                                </div>
                                                            </div>
                                                        </div> 
                                                    </div>
                                                    <div class="row clearfix">
                                                        <div class="col-sm-2 form-control-label">
                                                            <label for="TipoDocumento">Tipo Doc.</label>
                                                        </div>
                                                        <div class="col-sm-3">
                                                            <select id="cbo_TipoDocumento" class="form-control" disabled="disabled">
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
                                                                    <input type="text" id="txt_NumeroDocumento" class="form-control" disabled="disabled">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-1 form-control-label">
                                                            <label for="Indicativo">Indicativo</label>
                                                        </div>
                                                        <div class="col-sm-2">
                                                            <div class="form-group">
                                                                <div class="form-line">
                                                                    <input style="text-transform: uppercase" type="text" id="txt_Indicativo" class="form-control" disabled="disabled">
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
                                                                    <input style="text-transform: uppercase" type="text" id="txt_Asunto" class="form-control" disabled="disabled">
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
                                                                    <input type="date" id="txt_FechaDocumento" class="form-control" placeholder="dd/mm/yyyy" disabled="disabled">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-1 form-control-label">
                                                            <label for="Prioridad">Prioridad</label>
                                                        </div>
                                                        <div class="col-sm-3">
                                                            <select id="cbo_Prioridad" class="form-control" disabled="disabled">
                                                                <c:forEach var="f" items="${objPrioridad}">
                                                                    <option value="${f.codigo}">${f.descripcion}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div class="col-sm-1 form-control-label">
                                                            <label for="folios">Folios</label>
                                                        </div>
                                                        <div class="col-sm-2">
                                                            <div class="form-group">
                                                                <div class="form-line">
                                                                    <input type="text" id="txt_Folios" class="form-control" disabled="disabled">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row clearfix">
                                                        <div class="col-sm-2 form-control-label">
                                                            <label for="juzgado">Juzgado</label>
                                                        </div>
                                                        <div class="col-sm-10">
                                                            <select id="cbo_Juzgados" name="cbo_Juzgados" class="form-control" disabled="disabled">
                                                                <option value="0">SELECCIONE</option>
                                                                <c:forEach var="g" items="${objJuzgados}">
                                                                    <option value="${g.codigo}">${g.descripcion}</option>
                                                                </c:forEach>
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
                                                                    <input style="text-transform: uppercase" type="text" id="txt_PostFirma" class="form-control" disabled="disabled">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn bg-green waves-effect btn-sm col-md-40" onclick="javascript:fn_verDocumento();">
                                                        <i class="material-icons">file_download</i>
                                                        <span>Ver Documento</span>
                                                    </button>
                                                    <button type="button" class="btn bg-primary waves-effect btn-sm col-md-50" data-dismiss="modal">
                                                        <i class="material-icons">cancel</i>
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