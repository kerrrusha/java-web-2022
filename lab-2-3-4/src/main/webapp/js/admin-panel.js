document.addEventListener("DOMContentLoaded", function() {
    updateButtonStatus();
});

function updateButtonStatus() {
    updateMoneyAccountButtonStatus();
    updateClientButtonStatus();
}

function updateMoneyAccountButtonStatus() {
    hideMoneyAccountResultBlock();

    let moneyAccountId = $("#money-account-id").val();
    let blockButton = document.getElementById("money-account-block-button");
    let unblockButton = document.getElementById("money-account-unblock-button");

    let errors = [];
    if (containsNonDigitSymbols(moneyAccountId)) {
        errors.push("Money account ID cannot contain non-digit symbols.");
    }
    if (idIsInvalid(moneyAccountId)) {
        errors.push("Money account ID cannot be less than 1.");
    }
    showMoneyAccountErrors(errors);
    blockButton.disabled = errors.length > 0;
    unblockButton.disabled = errors.length > 0;
}

function hideMoneyAccountResultBlock() {
    $("#money-account-result").hide();
}

function containsNonDigitSymbols(value) {
    const regex = new RegExp('[^0-9]');
    return regex.test(value);
}

function idIsInvalid(id) {
    return id <= 0;
}

function showMoneyAccountErrors(errors) {
    let resultBlock = $("#money-account-result")
    resultBlock.css('visibility', 'visible');
    resultBlock.show();
    resultBlock.html(createErrorsHtmlList(errors));
}

function updateClientButtonStatus() {
    hideClientResultBlock();

    let clientId = $("#client-id").val();
    let blockButton = document.getElementById("client-block-button");
    let unblockButton = document.getElementById("client-unblock-button");

    let errors = [];
    if (containsNonDigitSymbols(clientId)) {
        errors.push("Client ID cannot contain non-digit symbols.");
    }
    if (idIsInvalid(clientId)) {
        errors.push("Client ID cannot be less than 1.");
    }
    showClientErrors(errors);
    blockButton.disabled = errors.length > 0;
    unblockButton.disabled = errors.length > 0;
}

function hideClientResultBlock() {
    $("#client-result").hide();
}

function showClientErrors(errors) {
    let resultBlock = $("#client-result")
    resultBlock.css('visibility', 'visible');
    resultBlock.show();
    resultBlock.html(createErrorsHtmlList(errors));
}

function sendBlockMoneyAccountRequest(postUrl) {
    let moneyAccountId = $("#money-account-id").val();
    let data = "moneyAccountId="+moneyAccountId;
    $.ajax({
        type: "POST",
        url: postUrl,
        data: data,
        success: function(response) {
            console.log(response);
            if (response["status"] === 200) {
                showBlockMoneyAccountSuccess(response["moneyAccount"]);
            } else {
                showMoneyAccountErrors(response["errorPool"]);
            }
        }
    });
}

function sendUnblockMoneyAccountRequest(postUrl) {
    let moneyAccountId = $("#money-account-id").val();
    let data = "moneyAccountId="+moneyAccountId;
    $.ajax({
        type: "POST",
        url: postUrl,
        data: data,
        success: function(response) {
            console.log(response);
            if (response["status"] === 200) {
                showUnblockMoneyAccountSuccess(response["moneyAccount"]);
            } else {
                showMoneyAccountErrors(response["errorPool"]);
            }
        }
    });
}

function showUnblockMoneyAccountSuccess(moneyAccount) {
    let resultBlock = $("#money-account-result");
    resultBlock.css('visibility', 'visible');
    resultBlock.show();
    resultBlock.html(createUnblockMoneyAccountSuccessHtml(moneyAccount));
}

function showBlockMoneyAccountSuccess(moneyAccount) {
    let resultBlock = $("#money-account-result");
    resultBlock.css('visibility', 'visible');
    resultBlock.show();
    resultBlock.html(createBlockMoneyAccountSuccessHtml(moneyAccount));
}

function createBlockMoneyAccountSuccessHtml(blockedMoneyAccount) {
    return '<div class="alert alert-success" role="alert">' +
        'Money account <span class="fw-bold">'+blockedMoneyAccount.name+'</span> was blocked successfully.' +
        '</div>';
}

function createUnblockMoneyAccountSuccessHtml(unblockedMoneyAccount) {
    return '<div class="alert alert-success" role="alert">' +
        'Money account <span class="fw-bold">'+unblockedMoneyAccount.name+'</span> was unblocked successfully.' +
        '</div>';
}

function sendBlockClientRequest(postUrl) {
    let clientId = $("#client-id").val();
    let data = "clientId="+clientId;
    $.ajax({
        type: "POST",
        url: postUrl,
        data: data,
        success: function(response) {
            console.log(response);
            if (response["status"] === 200) {
                showBlockClientSuccess(response["client"]);
            } else {
                showClientErrors(response["errorPool"]);
            }
        }
    });
}

function sendUnblockClientRequest(postUrl) {
    let clientId = $("#client-id").val();
    let data = "clientId="+clientId;
    $.ajax({
        type: "POST",
        url: postUrl,
        data: data,
        success: function(response) {
            console.log(response);
            if (response["status"] === 200) {
                showUnblockClientSuccess(response["client"]);
            } else {
                showClientErrors(response["errorPool"]);
            }
        }
    });
}

function showUnblockClientSuccess(client) {
    let resultBlock = $("#client-result");
    resultBlock.css('visibility', 'visible');
    resultBlock.show();
    resultBlock.html(createUnblockClientSuccessHtml(client));
}

function showBlockClientSuccess(client) {
    let resultBlock = $("#client-result");
    resultBlock.css('visibility', 'visible');
    resultBlock.show();
    resultBlock.html(createBlockClientSuccessHtml(client));
}

function createBlockClientSuccessHtml(blockedClient) {
    return '<div class="alert alert-success" role="alert">' +
        'Client <span class="fw-bold">'+ blockedClient.first_name + ' ' + blockedClient.last_name +'</span> was blocked successfully.' +
        '</div>';
}

function createUnblockClientSuccessHtml(unblockedClient) {
    return '<div class="alert alert-success" role="alert">' +
        'Client <span class="fw-bold">'+ unblockedClient.first_name + ' ' + unblockedClient.last_name +'</span> was unblocked successfully.' +
        '</div>';
}

function createErrorsHtmlList(errors) {
    let html = "<ul class='list-group'>";
    errors.forEach(error => {
        html += "<li class='list-group-item list-group-item-danger'>" + error + "</li>";
    });
    html += "</ul>";
    return html;
}