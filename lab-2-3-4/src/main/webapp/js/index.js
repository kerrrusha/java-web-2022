document.addEventListener("DOMContentLoaded", function() {
    loadData();
});

function loadData() {
    $.ajax({
        type: "POST",
        url: window.location.href + "moneycards",
        success: function(response) {
            console.log(response);
            fillCardList(response);
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function fillCardList(elements) {
    let cardUl = $("#card-list");
    cardUl.html(mapElementsToListElements(elements));
}

function getColorFromSecret(secret) {
    const MAX_VALUE = 255;

    let r = (parseInt(secret.at(0)) / 10) * MAX_VALUE;
    let g = (parseInt(secret.at(1)) / 10) * MAX_VALUE;
    let b = (parseInt(secret.at(2)) / 10) * MAX_VALUE;

    return 'rgb(' + r + ', ' + g + ', ' + b + ')';
}

function mapElementsToListElements(elements) {
    let html = '';
    let billingBaseUrl = $("#create-billing-base-url").text();
    let replenishmentBaseUrl = $("#replenishment-base-url").text();
    elements.forEach(element => {
        html += '<li class="border d-flex flex-row justify-content-around align-items-center p-3" style="align-content: normal;">' +
            '                <div class="d-flex flex-column align-items-center justify-content-start">' +
            '                    <h4>'+element.moneyAccountName+' card</h4>' +
            '                    <p class="display-6">'+getPrettyMoney(element.balance)+'</p>' +
            '                    <p class="text-muted">Card opened: '+element.openedDate+'</p>' +
            '                </div>' +
            '                <div class="rounded border border-dark d-flex flex-column" style="background-color: '+getColorFromSecret(element.secret)+'!important; width: 220px; height: 125px;">' +
            '                    <div class="d-flex flex-row justify-content-around mt-1">' +
            '                        <p class="oncard secret">'+element.secret+'</p>' +
            '                        <p class="oncard expiration-date">'+element.expirationDate+'</p>' +
            '                    </div>' +
            '                    <p class="oncard number text-center mt-3">'+element.moneyCardNumber+'</p>' +
            '                </div>' +
            '                <div class="d-flex flex-column align-items-center justify-content-start">' +
            '                    <a href="'+billingBaseUrl+element.moneyAccountId+'">Create billing</a>' +
            '                    <a href="'+replenishmentBaseUrl+element.moneyAccountId+'">Replenishment</a>' +
            '                    <a href="">Block this card</a>' +
            '                </div>' +
            '            </li>';
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