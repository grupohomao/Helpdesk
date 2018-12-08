/*
 |-------------------------------------------------------------------|
 |      Bootstrap Confirm Dialog - Full				     |
 |	@description: Plugin para janelas de dialogos de confirmaçao |
 |	@author: Jayr Alencar					     |
 |	@uriauthor: www.jayralencar.com.br                           |
 |-------------------------------------------------------------------|
 
 */
function confirmDialog(dados) {
    var language_default = {
        title: "Confirmação",
        message: "Deseja confimar esta ação?",
        btn_dismiss: "Não",
        btn_confirm: "Sim"
    }

    if (dados.language) {
        if (typeof (dados.language) == 'object') {
            language_default = dados.language;
        } else if (typeof (dados.language) == 'string') {
            var retorno = $.ajax({
                url: dados.language,
                type: 'get',
                dataType: 'json',
                async: false,
                success: function () {

                }
            });
            if (retorno.status == 200) {
                alert('Aqui');
                language_default = JSON.parse(retorno.responseText);
            }
        }
    }

    var modal = $('<div/>', {class: 'modal fade'});
    var modal_dialog = $('<div/>', {class: 'modal-dialog'});
    var modal_content = $('<div/>', {class: 'modal-content'});

    var modal_header = $('<div/>', {class: 'modal-header'})
            .append('<h2 class="modal-title">' + ((dados.title) ? dados.title : language_default.title) + '</h2>')
            .append('<button type="button" class="btn btn-default" data-dismiss="modal">X</button>');

    var modal_body = $('<div/>', {class: 'modal-body'})
            .append('<h3>' + ((dados.message) ? dados.message : language_default.message) + '</h3>');
    var btn_dismiss = $('<button/>', {
        class: ' btn btn-danger',
        type: 'button',
        'data-dismiss': 'modal'
    }).html('<span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span> ' + language_default.btn_dismiss);
    var btn_confirm = $('<button/>', {
        class: ' btn btn-success',
        type: 'button'
    }).html('<span class="glyphicon glyphicon-ok-circle" aria-hidden="true"></span> ' + language_default.btn_confirm);


    var modal_footer = $('<div/>', {class: 'modal-footer'})
            .append(btn_dismiss)
            .append(btn_confirm)

    modal_content.append(modal_header).append(modal_body).append(modal_footer).appendTo(modal_dialog);

    modal.append(modal_dialog).modal('show');

    $(btn_confirm).click(function () {
        if (dados.onConfirm) {
            dados.onConfirm();
            modal.modal('hide');
        } else {
            alert('Você não definiu a função onConfirm');
        }
    });
    $(btn_dismiss).click(function () {
        if (dados.onDismiss) {
            dados.onDismiss();
        }
    });
}

/**
 * Uso da rotina:
 *
 confirmDialog({
 title: 'Error:',
 message: 'Selecione um estado primeiro!',
 onConfirm: function () {
 //alert('Confimou')
 },
 onDismiss: function () {
 //alert('Não')
 }
 });
 * */

function Dialog(dados) {
    var language_default = {
        title: "Default",
        message: "Deseja confimar esta ação?",
        btn_dismiss: "Não",
        btn_confirm: "Ok",
        response: "default"
    };
    var modal = $('<div/>', {class: 'modal fade'});
    var modal_dialog = $('<div/>', {class: 'modal-dialog'});
    var modal_content = $('<div/>', {class: 'modal-content'});

    if (dados.response === "warning") {
        dados.title = "Warning:";
        var modal_body = $('<div/>', {class: 'modal-body'})
                .append('<h3 class="alert alert-warning">' + ((dados.message) ? dados.message : language_default.message) + '</h3>');
    } else if (dados.response === "danger") {
        dados.title = dados.title;
        var modal_body = $('<div/>', {class: 'modal-body'})
                .append('<h4 class="alert alert-danger">' + ((dados.message) ? dados.message : language_default.message) + '</h4>');
    } else if (dados.response === "success") {
        dados.title = "Success:";
        var modal_body = $('<div/>', {class: 'modal-body'})
                .append('<h3 class="alert alert-success">' + ((dados.message) ? dados.message : language_default.message) + '</h3>');
    } else {
        dados.title = "Default:";
        var modal_body = $('<div/>', {class: 'modal-body'})
                .append('<h4>' + ((dados.message) ? dados.message : language_default.message) + '</h4>');
    }

    var modal_header = $('<div/>', {class: 'modal-header'})
            .append('<h2 class="modal-title">' + ((dados.title) ? dados.title : language_default.title) + '</h2>')
            .append('<button type="button" class="btn btn-default" data-dismiss="modal">X</button>');

    var btn_confirm = $('<button/>', {
        class: ' btn btn-success',
        type: 'button'
    }).html('<span </span> ' + language_default.btn_confirm);
    var modal_footer = $('<div/>', {class: 'modal-footer'})
            .append(btn_confirm);

    modal_content.append(modal_header).append(modal_body).append(modal_footer).appendTo(modal_dialog);
    modal.append(modal_dialog).modal('show');
    $(btn_confirm).click(function () {
        modal.modal('hide');
    });
}

