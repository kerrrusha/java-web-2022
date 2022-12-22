function loadData() {
    $.ajax({
        type: "POST",
        url: window.location.href,
        success: function(response) {
            console.log(response);
            fillTable(response);
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function fillTable(elements) {
    let tableBody = $("#moneyCardsTableBody");
    tableBody.html(mapElementsToHtml(elements));
}

function mapElementsToHtml(elements) {
    let html = '';
    elements.forEach(element => {
        html += '<tr>' +
            '        <th scope="row">'+element.moneyAccountId+'</th>' +
            '    <td>'+element.moneyAccountName+'</td>' +
            '    <td>'+element.moneyCardNumber+'</td>' +
            '    <td>'+element.balance+'</td>' +
            '    <td>'+element.expirationDate+'</td>' +
            '</tr>';
    });
    return html;
}
