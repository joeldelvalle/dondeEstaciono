/**
 * agrga validacion de keyasciido generica
 * 
 */
function genericKeyascii(keyascii) {
	return (keyascii == 0 || keyascii == 8);
}

/**
 * 
 * metodo de validacion para que un campo de texto solo acepte numeros
 * 
 */
function onlyNumbers(event) {
	var keyascii = document.all ? keyascii = event.keyCode : keyascii = event.which;
	result = ( (keyascii > 47 && keyascii < 58) || genericKeyascii(keyascii));
	return result;
}



/**
 * 
 * metodo de validacion para que un campo de texto solo acepte valores decimales
 * 
 */
function onlyCurrency(event) {
	var keyascii = document.all ? keyascii = event.keyCode : keyascii = event.which;
	result = ( (keyascii > 47 && keyascii < 58) || keyascii == 46  || genericKeyascii(keyascii));
	return result;
}
