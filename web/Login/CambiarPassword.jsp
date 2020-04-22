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
        <!-- Demo Js -->
        <script src="javascript/demo.js"></script>
        <script type="text/javascript">
            function GuardarDatos() {
                var passwordAnterior = $("#txt_PasswordAnterior").val();
                var passwordNuevo = $("#txt_PasswordNuevo").val();
                var confirmaPassword = $("#txt_ConfirmaPassword").val();
                if (passwordNuevo === confirmaPassword) {
                    $.ajax({
                        type: "POST",
                        url: "../CambiarPassword",
                        data: {mode: 'P', passwordAnterior: passwordAnterior, passwordNuevo: passwordNuevo},
                        success: function (data) {
                            msg = data;
                            if (msg === "GUARDO") {
                                swal({
                                    title: "Aviso del Sistema!",
                                    text: "Datos Guardados con Exito",
                                    timer: 2000,
                                    showConfirmButton: false,
                                    imageUrl: "/SISEJE/Imagenes/thumbs-up.png"
                                });
                                $('#RegistrarModulos').modal('hide');
                                SalirPassword();
                            } else {
                                swal("Aviso del Sistema!", msg, "error");
                            }
                        }
                    });
                } else {
                    swal("Aviso del Sistema!", 'No coinciden los Password. Revise!!!', "error");
                }
            }
            function SalirPassword() {
                window.location = "../Login/Principal.jsp";
            }
        </script>
    </head>
    <body class="theme-teal">
        <%@include file="../Login/PaginaCarga.jspf" %>
        <!-- Muestra el contenido -->
        <section class="content">
            <div class="container-fluid">
                <div class="block-header">
                    <h3><strong>Cambiar Password</strong></h3>
                </div>
                <!-- Tabs With Icon Title -->
                <div class="row clearfix">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="card">
                            <div class="body"> 
                                <!-- Tab panes -->
                                <div class="tab-content">
                                    <div role="tabpanel" class="tab-pane fade in active" id="ListarModulos">
                                        <!-- Striped Rows -->
                                        <div class="modal-content">                                            
                                            <div class="modal-body">
                                                <div class="row clearfix">
                                                    <div class="col-sm-2 form-control-label">
                                                        <label for="USuario">Usuario</label>
                                                    </div>
                                                    <div class="col-sm-10">
                                                        <div class="form-group">
                                                            <div class="form-line">
                                                                <input type="text" id="txt_Usuario" class="form-control" value="${objBnUsuario.usuario}" disabled="disabled">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row clearfix">
                                                    <div class="col-sm-2 form-control-label">
                                                        <label for="Usuario">Apellidos y Nombres</label>
                                                    </div>
                                                    <div class="col-sm-10">
                                                        <div class="form-group">
                                                            <div class="form-line">
                                                                <input type="text" id="txt_Apellidos" class="form-control" disabled="disabled" value="${objBnUsuario.paterno} ${objBnUsuario.materno}, ${objBnUsuario.nombres}">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row clearfix">
                                                    <div class="col-sm-2 form-control-label">
                                                        <label for="Usuario">Password Anterior</label>
                                                    </div>
                                                    <div class="col-sm-8">
                                                        <div class="form-group">
                                                            <div class="form-line">
                                                                <input type="password" id="txt_PasswordAnterior" class="form-control" maxlength="20" required="required" >
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>                                                
                                                <div class="row clearfix">
                                                    <div class="col-sm-2 form-control-label">
                                                        <label for="Password">Nuevo Password</label>
                                                    </div>
                                                    <div class="col-sm-8">
                                                        <div class="form-group">
                                                            <div class="form-line">
                                                                <input type="password" id="txt_PasswordNuevo" class="form-control" minlength="4" maxlength="20" required="required">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row clearfix">
                                                    <div class="col-sm-2 form-control-label">
                                                        <label for="ConfirmaPassword">Confirma Password</label>
                                                    </div>
                                                    <div class="col-sm-8">
                                                        <div class="form-group">
                                                            <div class="form-line">
                                                                <input type="password" id="txt_ConfirmaPassword" class="form-control" minlength="4" maxlength="20" required="required">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>                                                
                                            </div>
                                            <div class="modal-footer ">
                                                <button type="button" class="btn bg-blue waves-effect btn-sm col-md-40" onclick="javascript:GuardarDatos();">
                                                    <i class="material-icons">save </i>
                                                    <span>Registrar</span>
                                                </button>
                                                <button type="button" class="btn bg-red waves-effect btn-sm col-md-50" onclick="javascript: SalirPassword();">
                                                    <i class="material-icons">cancel </i>
                                                    <span>Cancelar</span>
                                                </button>
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