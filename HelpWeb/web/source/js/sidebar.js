$(function () {

    //Efeito de esconder o menu lateral.
    $('#sidebarCollapse').on('click', function () {
        $('#sidebar').toggleClass('active');
    });
    
    var action = "http://localhost:8084/HelpWeb/index.html";
    
    //Intermediário para invocar a rotina que efetua o logout.
    $("#logout").on('click', function () {
        confirmDialog({
            title: 'Warning:',
            message: 'Tem certeza que desaja sair?',
            onConfirm: function () {
                window.location.href = action;
            },
            onDismiss: function () {
                //Nada!
            }
        });
    });
});