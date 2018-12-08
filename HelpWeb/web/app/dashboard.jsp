<%-- 
    Document   : dashboard
    Created on : 07/12/2018, 21:24:29
    Author     : Ricardo Guntzell
--%>

<%@page import="br.com.helpdesk.session.Sessao"%>
<%@page import="br.com.helpdesk.controller.DelegaUsuario"%>
<%@page import="br.com.helpdesk.model.usuario.Usuario"%>
<%
    Usuario usu = new Usuario(request.getParameter("usuario"), request.getParameter("senha"));
    DelegaUsuario delUsu = new DelegaUsuario();
    delUsu.acoes("logar", usu);
    //System.out.println(Sessao.getSessao().getUsuarioSessao().toString());

    System.out.println(request.getLocalName());
    System.out.println(request.getLocalPort());
    //System.out.println(Sessao.getSessao().getStatusLogin());


%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="">

        <title>Painel - Menu</title>

        <!-- Bootstrap CSS -->
        <link href="../source/framework/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="../source/framework/bootstrap/css/sidebar.css" rel="stylesheet">
        <!-- Fontawesom CSS -->
        <link rel="stylesheet" href="../source/css/fontawesom/web-fonts-with-css/css/fontawesome-all.min.css">
        <!-- DataTables CSS -->
        <link rel="stylesheet" type="text/css" href="../source/framework/datatables/datatables.min.css"/>
        <!-- JS jQuery-->
        <script type="text/javascript" src="../source/js/jquery.js"></script>
        <script type="text/javascript" src="../source/framework/bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="../source/framework/bootstrap/js/bootstrap-dialog.js"></script>
        <!-- JS -->
        <script type="text/javascript" src="../source/js/sidebar.js"></script>

    </head>

    <body>

        <%  if (Sessao.getSessao().getStatusLogin()) {
        %>

        <header>
            <div class="wrapper">
                <!-- Sidebar Holder -->
                <nav id="sidebar">
                    <div class="sidebar-header">
                        <a href="#" title=""><img class="img-fluid" src="../source/img/login-logo.png"></a>
                    </div>

                    <ul class="list-unstyled components">
                        <li>
                            <a href="#" title=""><i class="fa fa-pull-right fa-home fa-lg"></i>Painel</a>
                        </li>
                        <li class="active">
                            <a href="#homeSubmenu" data-toggle="collapse"><i class="fa fa-pull-right fa-edit fa-lg"></i>Cadastros</a>
                            <% if (Sessao.getSessao().getUsuarioSessao().getNivel().getForca() > 5) {%>
                            <ul class="collapse list-unstyled" id="homeSubmenu">
                                <li><a href="#" title="">Equipamentos</a></li>
                            </ul>
                            <% }%>
                        </li>
                        <li>
                            <a href="#" id="logout"><i class="fa fa-pull-right fa-sign-out-alt fa-lg"></i>Sair</a>
                        </li>
                    </ul>

                    <footer>
                        <p class="text-white text-center">Helpdesk - Pim <i class="fa fa-copyright"></i><p>             
                    </footer>

                </nav>

                <!-- Page Content Holder -->
                <div id="content">
                    <nav class="navbar navbar-default">
                        <div class="container-fluid">
                            <div class="navbar-header">
                                <button type="button" id="sidebarCollapse" class="btn btn-info navbar-btn">
                                    <i class="fas fa-bars"></i>
                                    Bem vindo, <%= usu.getUsuarioDescricao()%>!
                                </button>
                            </div>
                        </div>
                    </nav>
                </div>
            </div>
        </header>      
        <% } else {%>  
        <h3>Aguarde... Você será redirecionado!</h3>
        <script type="text/javascript">
            $(function () {
                var action = "http://localhost:8084/HelpWeb/index.html";

                //Intermediário para invocar a rotina que efetua o logout.
                Dialog({
                    title: 'Erro',
                    message: 'Ops.. Você não tem permissão!',
                    response: "danger"
                });

                setTimeout(function () {
                    window.location.href = action;
                }, 5000);
            });
        </script>
        <% }%>
    </body>
</html>


