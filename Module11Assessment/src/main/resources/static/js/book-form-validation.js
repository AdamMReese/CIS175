$(document).ready(function() {
	$('form').submit(function(event) {
		var isValid = true;
		var errorMessages = [];

		// Reset errors
		$('.form-group').removeClass('shake-error');
		$('.form-group input').css('border', 'none');

		// Validate Title
		var title = $('#title').val();
		if (title === "") {
			isValid = false;
			$('#title').closest('.form-group').addClass('shake-error');
			$('#title').css('border', '2px solid red');
			errorMessages.push('Title is required.');
		}

		// Validate Author
		var author = $('#author').val();
		if (author === "") {
			isValid = false;
			$('#author').closest('.form-group').addClass('shake-error');
			$('#author').css('border', '2px solid red');
			errorMessages.push('Author is required.');
		}

		// Validate ISBN
		var isbn = $('#isbn').val().replace(/-/g, '');  // Remove hyphens for validation
		var isbnPattern10 = /^\d{10}$/;
		var isbnPattern13 = /^\d{13}$/;
		if (isbn !== "" && !isbnPattern10.test(isbn) && !isbnPattern13.test(isbn)) {
			isValid = false;
			$('#isbn').closest('.form-group').addClass('shake-error');
			$('#isbn').css('border', '2px solid red');
			errorMessages.push('ISBN must be 10 or 13 digits.');
		} else if (isbn !== "") {
			// Format ISBN
			if (isbn.length === 10) {
				isbn = isbn.replace(/(\d{1})(\d{3})(\d{5})(\d{1})/, '$1-$2-$3-$4');
			} else if (isbn.length === 13) {
				isbn = isbn.replace(/(\d{3})(\d{1})(\d{4})(\d{6})(\d{1})/, '$1-$2-$3-$4-$5');
			}
			$('#isbn').val(isbn);  // Set the formatted ISBN back to the input field
		}

		// Validate Publish Year
		var publishYear = $('#publish_date').val();
		var yearPattern = /^\d{4}$/;
		if (publishYear !== "" && !yearPattern.test(publishYear)) {
			isValid = false;
			$('#publish_date').closest('.form-group').addClass('shake-error');
			$('#publish_date').css('border', '2px solid red');
			errorMessages.push('Publish Year must be four digits if provided.');
		}

		if (!isValid) {
			event.preventDefault();  // prevent form submission

			// Display SweetAlert2 error message
			Swal.fire({
				icon: 'error',
				title: 'Form Submission Error',
				html: errorMessages.join('<br/>'),  // Join error messages with line breaks
			});
		}
	});

	// Reset error on input
	$('input').on('input', function() {
		$(this).closest('.form-group').removeClass('shake-error');
		$(this).css('border', 'none');
	});
});
