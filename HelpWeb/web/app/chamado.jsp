<%-- 
    Document   : chamado
    Created on : 07/12/2018, 21:24:29
    Author     : Ricardo Guntzell
--%>

<%@page import="br.com.helpdesk.model.Usuario"%>
<%@page import="br.com.helpdesk.model.RelatorioChamado"%>
<%@page import="br.com.helpdesk.controller.DelegaRelatorioChamado"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.helpdesk.session.Sessao"%>
<%@page import="br.com.helpdesk.controller.DelegaUsuario"%>

<%
    Usuario usu = null;
    String usuDesc = null;
    String acao = "listar";
    ArrayList<RelatorioChamado> listaChamados = null;
    DelegaRelatorioChamado delRelCha = null;

    //!request.getParameter("usuario").isEmpty() && !request.getParameter("senha").isEmpty()
    if (!Sessao.getSessao().getStatusLogin()) {
        usu = new Usuario(request.getParameter("usuario"), request.getParameter("senha"));
        usuDesc = usu.getUsuarioDescricao();

        DelegaUsuario delUsu = new DelegaUsuario();
        delUsu.acoes("logar", usu);

        System.out.println(Sessao.getSessao().getUsuarioSessao().toString());
    } else {
        usuDesc = Sessao.getSessao().getUsuarioSessao().getUsuarioDescricao();
        System.out.println(Sessao.getSessao().getUsuarioSessao().toString());
    }

    String base = "http://localhost";
    String porta = ":" + String.valueOf(request.getLocalPort());
    String pathAtual = request.getRequestURI();
    String urlAtual = base + porta + pathAtual;
    String path = base + porta + "/HelpWeb/app/";

    String[] arrPage = pathAtual.split("/");
    String paginaAtual = arrPage[arrPage.length - 1];

    delRelCha = new DelegaRelatorioChamado();
    listaChamados = delRelCha.acoes(acao);
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="../source/img/helpdesk-ico.ico">

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
        <!-- DataTables JS -->
        <script type="text/javascript" src="../source/framework/datatables/datatables.min.js"></script>

        <script type="text/javascript" src="../source/js/sidebar.js"></script>
        <script type="text/javascript" src="../source/js/scriptChamado.js"></script>

    </head>

    <body>
        <%  if (Sessao.getSessao().getStatusLogin()) {%>
        <header>
            <div class="wrapper">
                <!-- Sidebar Holder -->
                <nav id="sidebar">
                    <div class="sidebar-header">
                        <a href="<%= path%>dashboard.jsp" title="logo"><img class="img-fluid" src="../source/img/login-logo.jpg"></a>
                    </div>

                    <ul class="list-unstyled components">
                        <li class="active">
                            <a href="<%= path%>dashboard.jsp" title="Dashboard"><i class="fa fa-pull-right fa-home fa-lg"></i>Painel</a>
                        </li>
                        <li>
                            <a href="#homeSubmenu" title="Cadastros" data-toggle="collapse"><i class="fa fa-pull-right fa-edit fa-lg"></i>Cadastros</a>
                            <% if (Sessao.getSessao().getUsuarioSessao().getNivel().getForca() > 5) {%>
                            <ul class="collapse list-unstyled" id="homeSubmenu">
                                <li><a href="#" title="">Equipamentos</a></li>
                            </ul>
                            <% }%>
                        </li>
                        <li>
                            <a href="#pageSubmenu" title="Chamados" data-toggle="collapse"><i class="fa fa-pull-right fa-file-archive fa-lg"></i>Chamados</a>
                            <ul class="collapse list-unstyled" id="pageSubmenu">
                                <li><a href="<%= path%>chamado.jsp" title="">Relatório</a></li>
                            </ul>
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
                                    Bem vindo, <%= usuDesc%>!
                                </button>
                            </div>
                        </div>
                    </nav>
                    <main>
                        <div id="main" class="container-fluid">

                            <h3>Chamados</h3>

                            <div id="list" class="row">
                                <div class="table-responsive">
                                    <table id="chamado" class="table table-dark table-condensed">
                                        <thead>
                                            <tr>
                                                <th class="text-center">Código do chamado</th>
                                                <th class="text-center">Cliente</th>
                                                <th class="text-center">Responsável</th>
                                                <th class="text-center">Situação</th>
                                                <th class="text-center">Ocorrido</th>
                                                <th class="text-center">Última atualização</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% for (RelatorioChamado chamado : listaChamados) {%>
                                            <tr>
                                                <td class="text-center"><%= chamado.getId()%></td>
                                                <td class="text-center"><%= chamado.getChamadoCliente()%></td>
                                                <td class="text-center"><%= chamado.getChamadoFuncionario()%></td>
                                                <td class="text-center"><%= chamado.getChamadoSituacao()%></td>
                                                <td class="text-center"><%= chamado.getChamadoDescricao()%></td>
                                                <td class="text-center"><%= chamado.getChamadoData()%></td>
                                            </tr>
                                            <%}%>
                                        </tbody>
                                    </table>
                                </div>
                            </div> <!-- /#list -->
                        </div> <!-- /#main -->
                    </main>
                </div>
            </div>
        </header>      
        <% } else {%>  
        <h3>Aguarde... Você será redirecionado!</h3>
        <script type="text/javascript">
            $(function () {
                var action = "http://localhost:8084/HelpWeb/index.jsp";

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


