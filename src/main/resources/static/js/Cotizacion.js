const formulario = document.getElementById('formulario');
const inputs = document.querySelectorAll('#formulario input');

const expresiones = {
	Usuario: /^[a-zA-Z0-9\_\-]{4,16}$/, // Letras, numeros, guion y guion_bajo
	Respuesta: /^\d{7,14}$/, // 7 a 14 numeros.
    fecha: /^\d{4}-\d{2}-\d{2}$/, // Formato de fecha yyyy-mm-dd
    Descriocion: /^[a-zA-ZÀ-ÿ0-9.,;:_\s]{1,500}$/ // 7 a 14 numeros.
}

const campos = {
	Usuario: false,
    fecha:false,
	Respuesta: false,
	Descriocion: false
}

const validarFormulario = (e) => {
	switch (e.target.name) {
		case "Usuario":
			validarCampo(expresiones.Usuario, e.target, 'Usuario');
		break;

        case "Respuesta":
			validarCampo(expresiones.Respuesta, e.target, 'Respuesta');
		break;

        case "fecha":
			validarCampo(expresiones.fecha, e.target, 'fecha');
		break;

        case "Descriocion":
            validarCampo(expresiones.Descriocion, e.target, 'Descriocion');
		break;
	}
}

const validarCampo = (expresion, input, campo) => {
	if(expresion.test(input.value)){
		document.getElementById(`grupo__${campo}`).classList.remove('formulario__grupo-incorrecto');
		document.getElementById(`grupo__${campo}`).classList.add('formulario__grupo-correcto');
		document.querySelector(`#grupo__${campo} i`).classList.add('fa-check-circle');
		document.querySelector(`#grupo__${campo} i`).classList.remove('fa-times-circle');
		document.querySelector(`#grupo__${campo} .formulario__input-error`).classList.remove('formulario__input-error-activo');
		campos[campo] = true;
	} else {
		document.getElementById(`grupo__${campo}`).classList.add('formulario__grupo-incorrecto');
		document.getElementById(`grupo__${campo}`).classList.remove('formulario__grupo-correcto');
		document.querySelector(`#grupo__${campo} i`).classList.add('fa-times-circle');
		document.querySelector(`#grupo__${campo} i`).classList.remove('fa-check-circle');
		document.querySelector(`#grupo__${campo} .formulario__input-error`).classList.add('formulario__input-error-activo');
		campos[campo] = false;
	}
}


inputs.forEach((input) => {
	input.addEventListener('keyup', validarFormulario);
	input.addEventListener('blur', validarFormulario);
});
//parte del campo del texto mas grande
Descriocion.addEventListener('keyup', validarFormulario);
Descriocion.addEventListener('blur', validarFormulario);

formulario.addEventListener('submit', (e) => {


	if( campos.Usuario && campos.Respuesta && campos.Descriocion && campos.fecha){

		document.getElementById('formulario__mensaje-exito').classList.add('formulario__mensaje-exito-activo');
		setTimeout(() => {
			document.getElementById('formulario__mensaje-exito').classList.remove('formulario__mensaje-exito-activo');
		}, 3000);

		document.querySelectorAll('.formulario__grupo-correcto').forEach((icono) => {
			icono.classList.remove('formulario__grupo-correcto');
		});
	} else {
	    e.preventDefault();
		document.getElementById('formulario__mensaje').classList.add('formulario__mensaje-activo');
        setTimeout(() => {
			document.getElementById('formulario__mensaje').classList.remove('formulario__mensaje-activo');
		}, 2000);
	}
});