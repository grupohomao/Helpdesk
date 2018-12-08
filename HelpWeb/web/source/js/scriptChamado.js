$(function () {
    $('#chamado').dataTable({
        //"paging": false,
        //"searching": false,

        //"info": false,

        "lengthMenu": [[20, -1], [20, "Todos"]],
//                    dom: 'Blfrtip',
//                    buttons: [{
//                            title: 'Relatório de Memorandos',
//                            extend: 'pdfHtml5',
//                            text: '<i class="text-danger fa fa-file-pdf-o"></i>',
//                            titleAttr: 'PDF',
//                            orientation: 'landscape',
//                            pageSize: 'A4',
//                            className: 'btn btn-sm btn-default text-red pull-right',
//                            exportOptions: {
//                                columns: [0, 1, 2, 3, 4]
//                            }
//                        }
//                    ],
        "language": {
            "sEmptyTable": "Nenhum registro encontrado",
            "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
            "sInfoEmpty": "Mostrando 0 até 0 de 0 registros",
            "sInfoFiltered": "(Filtrados de _MAX_ registros)",
            "sInfoPostFix": "",
            "sInfoThousands": ".",
            "sLengthMenu": "_MENU_ resultados por página",
            "sLoadingRecords": "Carregando...",
            "sProcessing": "Processando...",
            "sZeroRecords": "Nenhum registro encontrado",
            "sSearch": "Pesquisar",
            "oPaginate": {
                "sNext": "Próximo",
                "sPrevious": "Anterior",
                "sFirst": "Primeiro",
                "sLast": "Último"
            },
            "oAria": {
                "sSortAscending": ": Ordenar colunas de forma ascendente",
                "sSortDescending": ": Ordenar colunas de forma descendente"
            }
        }
    });
});

