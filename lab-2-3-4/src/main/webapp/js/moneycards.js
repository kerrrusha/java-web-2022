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
            '    <td>'+getPrettyMoney(element.balance)+'</td>' +
            '    <td>'+element.expirationDate+'</td>' +
            '</tr>';
    });
    return html;
}

function getPrettyMoney(totalCents) {
    let dollars = removeDigitsAfterComma(totalCents / 100);
    let cents = totalCents - dollars * 100;
    return dollars + "." + toCentsFormat(cents) + " $";
}

function toCentsFormat(int) {
    if (int < 10) {
        return "0" + int;
    }
    return "" + int;
}

function removeDigitsAfterComma(double) {
    return double | 0;
}