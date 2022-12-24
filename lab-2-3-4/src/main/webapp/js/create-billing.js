document.addEventListener("DOMContentLoaded", function() {
    updateButtonStatus();
});

function sendCreateNewBillingRequest(postUrl) {
    let cardNumber = $("#recipient-card-number").val();
    let moneyAmount = $("#money-amount").val();
    let fromMoneyAccountId = new URLSearchParams(window.location.search).get("fromMoneyAccountId");
    let data = "fromMoneyAccountId="+fromMoneyAccountId+"&toMoneyCardNumber="+cardNumber+"&moneyAmount="+moneyAmount;
    $.ajax({
        type: "POST",
        url: postUrl,
        data: data,
        success: function(response) {
            console.log(response);
            if (response["status"] === 200) {
                showSuccess(response["senderMoneyCard"]);
            } else {
                showErrors(response["errorPool"]);
            }
        },
        error: function (response) {
            console.log(response);
            showErrors(response["Something went wrong. Please try again later."]);
        }
    });
}

function updateButtonStatus() {
    hideResultBlock();

    let cardNumber = $("#recipient-card-number").val();
    let button = document.getElementById("postButton");

    let errors = [];
    if (cardNumberHasInvalidLength(cardNumber)) {
        errors.push("Card number must be 16 symbols length.");
    }
    if (cardNumberHasNonNumberSymbols(cardNumber)) {
        errors.push("Card number cannot contain non-digit symbols.");
    }
    showErrors(errors);
    button.disabled = errors.length > 0;
}

function cardNumberHasInvalidLength(number) {
    return number.length !== 16;
}

function cardNumberHasNonNumberSymbols(number) {
    const regex = new RegExp('[^0-9]');
    return regex.test(number);
}

function hideResultBlock() {
    $("#result").hide();
}

function showSuccess(moneyCard) {
    let resultBlock = $("#result");
    resultBlock.css('visibility', 'visible');
    resultBlock.show();
    resultBlock.html(createSuccessHtml(moneyCard));
}

function createSuccessHtml(moneyCard) {
    return '<div class="alert alert-success" role="alert">' +
        '  Billing was sent successfully! Your balance: ' + getPrettyMoney(moneyCard.balance) +
        '</div>';
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

function showErrors(errors) {
    let resultBlock = $("#result")
    resultBlock.css('visibility', 'visible');
    resultBlock.show();
    resultBlock.html(createErrorsHtmlList(errors));
}

function createErrorsHtmlList(errors) {
    let html = "<ul class='list-group'>";
    errors.forEach(error => {
        html += "<li class='list-group-item list-group-item-danger'>" + error + "</li>";
    });
    html += "</ul>";
    return html;
}