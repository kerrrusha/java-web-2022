function sendOpenNewCardRequest(postUrl) {
    let moneyAccountName = $("#moneyAccountName").val();
    let data = "moneyAccountName="+moneyAccountName;
    $.ajax({
        type: "POST",
        url: postUrl,
        data: data,
        success: function(response) {
            console.log(response);
            if (response["status"] === 200) {
                showSuccess(response["moneyCard"]);
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

    let moneyAccountName = $("#moneyAccountName").val();
    let button = document.getElementById("postButton");

    let errors = [];
    if (nameHasInvalidLength(moneyAccountName)) {
        errors.push("Card name must be at least 3 symbols length.");
    }
    if (nameHasSpaces(moneyAccountName)) {
        errors.push("Name cannot contain spaces.");
    }
    if (nameHasNonLetterSymbols(moneyAccountName)) {
        errors.push("Card name should consist only of english letters.");
    }
    showErrors(errors);
    button.disabled = errors.length > 0;
}

function nameHasInvalidLength(name) {
    return name.length < 3;
}

function nameHasSpaces(name) {
    return name.includes(" ");
}

function nameHasNonLetterSymbols(name) {
    const regex = new RegExp('[^a-zA-Z ]');
    return regex.test(name);
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
        '  Your money card was created successfully!' +
        '</div>' +
        '<hr>' +
        '<h3 class="mt-3">Card details:</h3>' +
        '   <table class="table">' +
        '      <tbody>' +
        '      <tr>' +
        '        <th scope="row">Money account id: </th>' +
        '        <td>'+moneyCard.money_account_id+'</td>' +
        '      </tr>' +
        '      <tr>' +
        '        <th scope="row">Card number: </th>' +
        '        <td>'+moneyCard.number+'</td>' +
        '      </tr>' +
        '      <tr>' +
        '        <th scope="row">Expiration date: </th>' +
        '        <td>'+moneyCard.expiration_date+'</td>' +
        '      </tr>' +
        '      <tr>' +
        '        <th scope="row">Secret: </th>' +
        '        <td>'+moneyCard.secret+'</td>' +
        '      </tr>' +
        '      </tbody>' +
        '    </table>';
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