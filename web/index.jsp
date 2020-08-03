
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="WEB-INF/jspf/browser.jspf" %>
<%    session.removeAttribute("objUsuario" + request.getSession().getId());
    session.removeAttribute("ID");
    session.removeAttribute("autorizacion");
    session.invalidate();
%>
<html >
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <title>Sistema de Sentencias Judiciales - Ejercito Peruano</title>
        <link rel="icon" href="favicon.ico" type="image/x-icon">
        <link href="css/util.css" rel="stylesheet" type="text/css"/>
        <link href="css/main_login.css" rel="stylesheet" type="text/css"/>
        <link href="css/fallback.css" rel="stylesheet" type="text/css"/>
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="fonts/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <link href="plugins/sweetalert/sweetalert.css" rel="stylesheet" type="text/css"/>
        <link href="css/style.css" rel="stylesheet">
        <script src="javascript/jquery.js" type="text/javascript"></script>
        <script src="vendor/jquery/jquery-3.2.1.min.js"></script>
        <script src="vendor/bootstrap/js/popper.min.js" type="text/javascript"></script>
        <script src="vendor/bootstrap/js/bootstrap.min.js" type="text/javascript"></script> 
        <script src="javascript/pages/forms/form-validation.js"></script>
        <script src="plugins/jquery-validation/jquery.validate.js"></script>
        <script src="plugins/sweetalert/sweetalert.min.js" type="text/javascript"></script>
        <script>
            function fn_login() {
                var usuario = $("#txt_Usuario").val();
                var password = $("#txt_Password").val();
                $.ajax({
                    type: "POST",
                    url: "VerificaUsuario",
                    data: {accion: "LOGIN", usuario: usuario, password: password},
                    success: function (data) {
                        var result = data.substr(0, 5);
                        if (result === "Login") {
                            window.location = data;
                        } else {
                            swal("Aviso del Sistema!", data, "error");
                        }
                    }
                });
            }
            function fn_VentanaRecuperarPassword() {
                $('#RecuperarPassword .modal-content').removeAttr('class').addClass('modal-content modal-col-default');
                $('#RecuperarPassword').modal('show');
            }
            function RecuperarPassword() {
                var usuario = $("#txt_UsuarioRecupera").val();
                var correo = $("#txt_Correo").val();
                $.ajax({
                    type: "POST",
                    url: "RecuperarPassword",
                    data: {usuario: usuario, correo: correo},
                    success: function (data) {
                        var result = data.substr(0, 1);
                        var mensaje = data.substr(1);
                        if (result === 'V') {
                            swal("Aviso del Sistema!", mensaje, "success");
                        } else {
                            swal("Aviso del Sistema!", mensaje, "error");
                        }
                        $('#RecuperarPassword').modal('hide');
                    }
                });
            }
        </script>
    </head>
    <body>
        <div class="limiter">
            <div class="container-login100">
                <div class="svg">
                    <object>
                        <embed src="dclogo.svg" width="200%" height="200%">
                    </object>
                </div>
                <div class="wrap-login100">
                    <form class="login100-form validate-form" action="javascript: fn_login();" method="post" id="frm_Login">
                        <span class="login100-form-title p-b-15">
                            <img src="Imagenes/logo_ep_web_b.png" height="150" width="130"/>
                        </span>	
                        <span class="login100-form-title p-b-30"/>
                        MODULO DE SENTENCIAS JUDICIALES<br>DEL EJÉRCITO
                        </span>
                        <div class="wrap-input100 validate-input" data-validate ="Ingrese Usuario">
                            <input class="input100" type="text" name="txt_Usuario" id="txt_Usuario" required="required">
                            <span class="focus-input100" data-placeholder="Usuario"></span>
                        </div>  
                        <div class="wrap-input100 validate-input" data-validate="Ingrese Clave">
                            <input class="input100" type="password" name="txt_Password" id="txt_Password" minlength="3" required="required">
                            <span class="focus-input100" data-placeholder="Contraseña"></span>
                        </div>
                        <div class="container-login100-form-btn">
                            <div class="wrap-login100-form-btn">
                                <div class="login100-form-bgbtn"></div>
                                <button class="btn-lg btn-success btn-block" type="submit">
                                    Ingresar
                                </button>
                            </div>
                        </div>
                        <br>
                        <div class="text-center p-t-30">
                            <span class="txt1">
                                SISEJE
                            </span>
                            <br>
                            <span class="txt1">
                                2018 ® Derechos Reservados
                            </span>
                            <br>
                        </div>
                    </form>
                </div>
            </div>
            <div class="ac_footer">
                <a href="javascript:fn_VentanaRecuperarPassword();">[ Recuperar Contraseña ]</a>
            </div>
        </div>
        <div class="modal fade" id="RecuperarPassword" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="defaultModalLabel">RECUPERAR PASSWORD</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row clearfix">
                            <div class="col-sm-2 form-control-label">
                                <label for="usuario">Usuario</label>
                            </div>
                            <div class="col-sm-10">
                                <div class="form-group">
                                    <div class="form-line">
                                        <input type="text" id="txt_UsuarioRecupera" class="form-control" maxlength="200" required>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row clearfix">
                            <div class="col-sm-2 form-control-label">
                                <label for="direccion">Correo</label>
                            </div>
                            <div class="col-sm-10">
                                <div class="form-group">
                                    <div class="form-line">
                                        <input type="email" id="txt_Correo" class="form-control" maxlength="200" required>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer ">
                        <button type="button" class="btn bg-blue waves-effect btn-sm col-md-40" onclick="javascript:RecuperarPassword();">
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
    </body>
    <script src="javascript/main.js" type="text/javascript"></script>
</html>