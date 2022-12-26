document.addEventListener("DOMContentLoaded", function() {
    loadData();
});

var billingElements = [];

function loadData() {
    $.ajax({
        type: "POST",
        url: window.location.href,
        success: function(response) {
            console.log(response);
            billingElements = response;
            updateSortingAndFillTable();
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function sortById(order) {
    if (order === 'asc') {
        billingElements = billingElements.sort((a, b) => a.billingId > b.billingId ? 1 : -1);
    } else {
        billingElements = billingElements.sort((a, b) => a.billingId < b.billingId ? 1 : -1);
    }
}

function sortByDate(order) {
    if (order === 'asc') {
        billingElements = billingElements.sort((a, b) => a.createdTime.toLowerCase() > b.createdTime.toLowerCase() ? 1 : -1);
    } else {
        billingElements = billingElements.sort((a, b) => a.createdTime.toLowerCase() < b.createdTime.toLowerCase() ? 1 : -1);
    }
}

function updateSortingAndFillTable() {
    let sortType = $("#sortType").find(":selected").val();
    switch (sortType) {
        case 'id-asc':
            sortById("asc");
            break;
        case 'id-desc':
            sortById("desc");
            break;
        case 'date-asc':
            sortByDate("asc");
            break;
        case 'date-desc':
            sortByDate("desc");
            break;
        default:
            sortByDate("desc");
    }
    fillTable();
}

function fillTable() {
    let tableBody = $("#billingsTableBody");
    tableBody.html(mapElementsToHtml(billingElements));
}

function mapElementsToHtml(elements) {
    let html = '';
    elements.forEach(element => {
        html += '<tr>' +
            '        <th scope="row">'+element.billingId+'</th>' +
            '    <td>'+getPrettyMoney(element.moneyAmount)+'</td>' +
            '    <td>'+element.fromCardNumber+'</td>' +
            '    <td>'+element.toCardNumber+'</td>' +
            '    <td>'+element.statusName+'</td>' +
            '    <td>'+element.createdTime+'</td>' +
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