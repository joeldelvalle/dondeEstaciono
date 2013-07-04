
/*
 *  Las siguientes funciones le dan funcionalidad al boton clean de los formularios
 *  y limpia los campos de sus valores al ser presionado.
 */
$(function() {
	$("#clearBtn").click(function() {
		clearFields($("#frm"));
	});
});

function clearFields(element) {
	$(element).find(':input').each(function() {
		switch(this.type) {
		case 'text':
		case 'textarea':
		case 'password':
		case 'select-one':
			$(this).val('');
			break;
		case 'checkbox':
		case 'radio':
			this.checked = false;
		}
	});
}