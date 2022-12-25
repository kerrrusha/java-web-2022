document.addEventListener("DOMContentLoaded", function() {
    loadData();
});

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

var moneyCardElements = [];

function sortById(order) {
    let sorted;
    if (order === 'asc') {
        sorted = moneyCardElements.sort((a, b) => a.moneyAccountId > b.moneyAccountId ? 1 : -1);
    } else {
        sorted = moneyCardElements.sort((a, b) => a.moneyAccountId < b.moneyAccountId ? 1 : -1);
    }
    fillTable(sorted);
}

function sortByName(order) {
    let sorted;
    if (order === 'asc') {
        sorted = moneyCardElements.sort((a, b) => a.moneyAccountName.toLowerCase() > b.moneyAccountName.toLowerCase() ? 1 : -1);
    } else {
        sorted = moneyCardElements.sort((a, b) => a.moneyAccountName.toLowerCase() < b.moneyAccountName.toLowerCase() ? 1 : -1);
    }
    fillTable(sorted);
}

function sortByBalance(order) {
    let sorted;
    if (order === 'asc') {
        sorted = moneyCardElements.sort((a, b) => a.balance > b.balance ? 1 : -1);
    } else {
        sorted = moneyCardElements.sort((a, b) => a.balance < b.balance ? 1 : -1);
    }
    fillTable(sorted);
}

function updateSorting() {
    let sortType = $("#sortType").find(":selected").val();
    switch (sortType) {
        case 'id-asc':
            sortById("asc");
            break;
        case 'id-desc':
            sortById("desc");
            break;
        case 'name-asc':
            sortByName("asc");
            break;
        case 'name-desc':
            sortByName("desc");
            break;
        case 'balance-asc':
            sortByBalance("asc");
            break;
        case 'balance-desc':
            sortByBalance("desc");
            break;
        default:
            sortById("asc");
    }
}

function fillTable(elements) {
    moneyCardElements = elements;
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