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
        <script type="text/javascript">
            var codigo = null;
            var mode = null;
            function NuevoRegistro() {
                mode = 'I';
                codigo = 0;
                $('#cbo_Modulos').selectpicker('val', '0');
                $('#cbo_Modulos').removeAttr('disabled');
                $('#txt_Nombre').val('');
                $('#txt_Servlet').val('');
                $('#txt_Modo').val('');
                $('#cbo_Estado').selectpicker('val', 'AC');
                $('#RegistrarMenu .modal-content').removeAttr('class').addClass('modal-content modal-col-default');
                $('#RegistrarMenu').modal('show');
            }
            function EditarRegistro(xCodigo) {
                mode = 'U';
                codigo = xCodigo;
                $.ajax({
                    type: "GET",
                    url: "Menu",
                    data: {mode: mode, codigo: codigo},
                    success: function (data) {
                        var dato = data.trim().split("+++");
                        if (dato.length === 5) {
                            $('#cbo_Modulos').selectpicker('val', dato[0]);
                            $('#cbo_Modulos').attr('disabled', 'disabled');
                            $('#txt_Nombre').val(dato[1]);
                            $('#txt_Servlet').val(dato[2]);
                            $('#txt_Modo').val(dato[3]);
                            $('#cbo_Estado').selectpicker('val', dato[4]);
                            $('#RegistrarMenu .modal-content').removeAttr('class').addClass('modal-content modal-col-default');
                            $('#RegistrarMenu').modal('show');
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
                var modulo = $('#cbo_Modulos').val();
                var nombre = $('#txt_Nombre').val();
                var servlet = $('#txt_Servlet').val();
                var modo = $('#txt_Modo').val();
                var estado = $("#cbo_Estado").val();
                $.ajax({
                    type: "POST",
                    url: "IduMenu",
                    data: {mode: mode, codigo: codigo, modulo: modulo, nombre: nombre, servlet: servlet, modo: modo, estado: estado},
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
                            $('#RegistrarMenu').modal('hide');
                            Refrescar();
                        } else {
                            swal("Aviso del Sistema!", msg, "error");
                        }
                    }
                });
            }
            function Refrescar() {
                window.location = "Menu?mode=menu";
            }
        </script>
    </head>
    <body class="theme-teal">
        <%@include file="../Login/PaginaCarga.jspf" %>
        <!-- Muestra el contenido -->
        <section class="content">
            <div class="container-fluid">
                <div class="block-header">
                    <h3><strong>Administración de Menú</strong></h3>
                </div>
                <!-- Tabs With Icon Title -->
                <div class="row clearfix">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="card">
                            <div class="body"> 
                                <!-- Tab panes -->
                                <div class="tab-content">
                                    <div role="tabpanel" class="tab-pane fade in active" id="ListarMenu">
                                        <!-- Striped Rows -->
                                        <div class="row clearfix">
                                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                <div class="card">
                                                    <div class="header bg-teal">
                                                        <h2 class="col-md-10 col-sm-9 col-xs-8">
                                                            <strong>Listado de Menú</strong>
                                                        </h2>
                                                        <button type="button" class="btn btn-success waves-effect btn-sm col-md-2 col-sm-3 col-xs-4" onclick="javascript:NuevoRegistro();">
                                                            <i class="material-icons">add </i>
                                                            <span>Nuevo Registro</span>
                                                        </button>
                                                        <br>
                                                    </div>
                                                    <div class="body">
                                                        <table id="TablaResultados">
                                                            <thead>
                                                                <tr>
                                                                    <th style="text-align: center">MÓDULO</th>
                                                                    <th style="text-align: center">MENÚ</th>
                                                                    <th style="text-align: center">SERVLET</th>
                                                                    <th style="text-align: center">MODO</th>
                                                                    <th style="text-align: center">ESTADO</th>
                                                                    <th style="text-align: center">ACCIÓN</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <c:forEach var="d" items="${objMenus}">
                                                                    <tr>
                                                                        <td style="text-align: center">${d.modulo}</td>
                                                                        <td style="text-align: center">${d.nombre}</td>
                                                                        <td style="text-align: center">${d.servlet}</td>
                                                                        <td style="text-align: center">${d.modo}</td>
                                                                        <td style="text-align: center">
                                                                            <c:choose>
                                                                                <c:when test="${d.estado=='ACTIVO'}">
                                                                                    <span class="badge bg-green">${d.estado}</span>
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <span class="badge bg-red">${d.estado}</span>
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </td>
                                                                        <td style="text-align: center">
                                                                            <button type="button" class="btn btn-primary btn-xs waves-effect" data-toggle="tooltip" data-placement="bottom" title="Editar Menú" onclick="javascript: EditarRegistro('${d.codigo}');">
                                                                                <i class="material-icons">mode_edit</i>
                                                                            </button>
                                                                            <button type="button" class="btn bg-pink btn-xs waves-effect" data-toggle="tooltip" data-placement="bottom" title="Anular Menú" onclick="javascript: EliminarRegistro('${d.codigo}');">
                                                                                <i class="material-icons">delete</i>
                                                                            </button>
                                                                        </td>
                                                                    </tr>
                                                                </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- #END# Striped Rows -->
                                    </div>
                                    <!-- Modal Dialogs  -->
                                    <!-- Default Size -->
                                    <div class="modal fade" id="RegistrarMenu" tabindex="-1" role="dialog">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h4 class="modal-title" id="defaultModalLabel"> REGISTRO DE MENÚ</h4>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="row clearfix">
                                                        <div class="col-sm-2 form-control-label">
                                                            <label for="Modulos">Módulos</label>
                                                        </div>
                                                        <div class="col-sm-6">
                                                            <select id="cbo_Modulos" class="form-control">
                                                                <option value="0">Seleccione</option>
                                                                <c:forEach var="c" items="${objModulos}">
                                                                    <option value="${c.codigo}">${c.descripcion}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="row clearfix">
                                                        <div class="col-sm-2 form-control-label">
                                                            <label for="Nombre">Nombre</label>
                                                        </div>
                                                        <div class="col-sm-10">
                                                            <div class="form-group">
                                                                <div class="form-line">
                                                                    <input type="text" id="txt_Nombre" class="form-control" maxlength="50" required>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row clearfix">
                                                        <div class="col-sm-2 form-control-label">
                                                            <label for="Servlet">Servlet</label>
                                                        </div>
                                                        <div class="col-sm-10">
                                                            <div class="form-group">
                                                                <div class="form-line">
                                                                    <input type="text" id="txt_Servlet" class="form-control" maxlength="200" required>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row clearfix">
                                                        <div class="col-sm-2 form-control-label">
                                                            <label for="Modo">Modo</label>
                                                        </div>
                                                        <div class="col-sm-10">
                                                            <div class="form-group">
                                                                <div class="form-line">
                                                                    <input type="text" id="txt_Modo" class="form-control" maxlength="50" required>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row clearfix">
                                                        <div class="col-sm-2 form-control-label">
                                                            <label for="estado">Estado</label>
                                                        </div>
                                                        <div class="col-sm-6">
                                                            <select id="cbo_Estado" class="form-control">
                                                                <option value="AC">ACTIVO</option>
                                                                <option value="IN">INACTIVO</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="modal-footer ">
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
<script type="text/javascript">
    $('#TablaResultados').DataTable();
</script>