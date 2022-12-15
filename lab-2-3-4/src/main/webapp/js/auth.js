function processLogin(loginUrl, afterRegisterUrl) {
	hideErrorsBlock();

	let phone = $("#phone").val();
	let password = $("#password").val();

	let errors = [];
	if (!validatePhone(phone)) {
		errors.push("Phone must be in format like '+380123456789'.");
	}
	if (!validatePassword(password)) {
		errors.push("Password must be at least 3 characters long and must not contain spaces.");
	}

	if (errors.length !== 0) {
		showErrors(errors);
		return;
	}

	phone = phone.replace("+", "%2B")

	let data = "phone="+phone+"&password="+password;
	$.ajax({
	  type: "POST",
	  url: loginUrl,
	  data: data,
	  success: function(response) {
		  console.log(response);
		  if (response["status"] === 200) {
			  window.location.replace(afterRegisterUrl);
		  } else {
			  showErrors(response["errorPool"]);
		  }
	  },
	  error: function (response) {
	      console.log(response);
	  }
	});
}

function hideErrorsBlock() {
	$(".errors-block").hide();
}

function showErrors(errors) {
	let errorsBlock = $(".errors-block")
	errorsBlock.css('visibility', 'visible');
	errorsBlock.show();
	errorsBlock.html(createErrorsHtmlList(errors));
}

function createErrorsHtmlList(errors) {
	let html = "<ul class='list-group'>";
	errors.forEach(error => {
		html += "<li class='list-group-item list-group-item-danger'>" + error + "</li>";
	});
	html += "</ul>";
	return html;
}

function isCharNumber(c) {
  return c >= '0' && c <= '9';
}

function validatePhone(phone) {
	phone = phone.trim()
	if (phone.length !== 13) {
		return false;
	}
	if (phone.charAt(0) !== '+') {
		return false;
	}
	for (let i = 1; i < phone.length; i++) {
		if (!isCharNumber(phone.charAt(i))) {
			return false;
		}
	}
	return true;
}

function validateName(name) {
	return name.trim().length >= 3 && !name.includes(" ");
}

function validatePassword(password) {
	return password.length >= 3 && !password.includes(" ");
}

function processRegister(registerUrl, afterRegisterUrl) {
	hideErrorsBlock();

	let firstName = $("#firstName").val();
	let lastName = $("#lastName").val();
	let phone = $("#phone").val();
	let password = $("#password").val();
	let passwordRepeat = $("#passwordRepeat").val();

	let errors = [];
	if (!validateName(firstName)) {
		errors.push("First name must be 3 symbols at least.");
	}
	if (!validateName(lastName)) {
		errors.push("Last name must be 3 symbols at least.");
	}
	if (!validatePhone(phone)) {
		errors.push("Phone must be in format like '+380123456789'.");
	}
	if (!validatePassword(password) || !validatePassword(passwordRepeat)) {
		errors.push("Password must be at least 3 characters long and must not contain spaces.");
	}
	if (password !== passwordRepeat) {
		errors.push("Passwords don't match.");
	}

	if (errors.length !== 0) {
		showErrors(errors);
		return;
	}

	phone = phone.replace("+", "%2B")

	let data = "firstName="+firstName+"&lastName="+lastName+"&phone="+phone+"&password="+password+"&passwordRepeat="+passwordRepeat;
	$.ajax({
		type: "POST",
		url: registerUrl,
		data: data,
		success: function(response) {
			console.log(response);
			if (response["status"] === 200) {
				window.location.replace(afterRegisterUrl);
			} else {
				showErrors(response["errorPool"]);
			}
		},
		error: function (response) {
			console.log(response);
		}
	});
}
