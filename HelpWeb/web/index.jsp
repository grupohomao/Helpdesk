<%-- 
    Document   : index
    Created on : 08/12/2018, 17:13:58
    Author     : Ricardo Guntzell
--%>
<%@page import="br.com.helpdesk.session.Sessao"%>
<%
    if(Sessao.getSessao().getStatusLogin()){
        Sessao.getSessao().setStatusLogin(false);
    }    
    System.out.println(Sessao.getSessao().getStatusLogin());
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="source/img/helpdesk-ico.ico">

        <title>Helpdesk - Login</title>

        <!-- Bootstrap -->
        <link href="source/framework/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!-- Bootstrap-Template -->
        <link href="source/framework/bootstrap/css/floating-labels.css" rel="stylesheet">
        <!-- Fontawesom CSS -->
        <link rel="stylesheet" href="source/css/fontawesom/web-fonts-with-css/css/fontawesome-all.min.css">

        <!-- JS -->
        <script type="text/javascript" src="source/js/jquery.js"></script>
        <script type="text/javascript" src="source/framework/bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="source/framework/bootstrap/js/bootstrap-dialog.js"></script>
        <script type="text/javascript" src="source/js/scriptUser.js"></script>
    </head>

    <body>
        <form method="POST" class="form-signin frmLogin" action="app/dashboard.jsp">
            <div class="text-center mb-4">
                <img class="mb-4" src="source/img/login-logo.png" alt="" height="150">
            </div>

            <div class="form-group">
                <input type="text" name="txtAction" value="login" hidden>
                <div class="input-group mb-1">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon1"><i class="fa fa-user-circle"></i></span>
                    </div>
                    <input type="text" name="usuario" class="form-control" placeholder="Seu login..." required autofocus>
                </div>
                <div class="input-group mb-1">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon1"><i class="fa fa-unlock-alt"></i></span>
                    </div>
                    <input type="password" name="senha" class="form-control" placeholder="Sua senha..." required>
                </div>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit" id="btnLogar">Logar</button>
            <p class="mt-5 mb-3 text-muted text-center">&copy; Helpdesk-PIM</p>
        </form>
    </body>
</html>

