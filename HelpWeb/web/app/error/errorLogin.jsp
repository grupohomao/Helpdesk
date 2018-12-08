<%-- 
    Document   : errorLogin
    Created on : 08/12/2018, 16:06:46
    Author     : rrica
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- Bootstrap CSS -->
        <link href="../../source/framework/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="../../source/framework/bootstrap/css/sidebar.css" rel="stylesheet">
        <!-- Fontawesom CSS -->
        <link rel="stylesheet" href="../../source/css/fontawesom/web-fonts-with-css/css/fontawesome-all.min.css">
        <!-- DataTables CSS -->
        <link rel="stylesheet" type="text/css" href="../../source/framework/datatables/datatables.min.css"/>
        <!-- JS jQuery-->
        <script type="text/javascript" src="../../source/js/jquery.js"></script>
        <script type="text/javascript" src="../../source/framework/bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="../../source/framework/bootstrap/js/bootstrap-dialog.js"></script>
        <!-- JS -->
        <script type="text/javascript">
            $(function () {
                var action = "http://localhost:8084/HelpWeb/index.jsp";

                //Intermediário para invocar a rotina que efetua o logout.
                Dialog({
                    title: 'Erro',
                    message: 'Você não tem permissão!',
                    response: "danger"
                });

                setTimeout(function () {
                    window.location.href = action;
                }, 3500);
            });
        </script>
    </head>
    <body>
    </body>
</html>
