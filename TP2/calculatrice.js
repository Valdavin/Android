/////////// LIGNE 94 -> QUESTION 6
edition = false;
function rab() {
	document.getElementById("zone_affichage").value = "";
}

function calcul() {
	try {
		text = document.getElementById("zone_affichage");
		if (text.value != "") {
			text.value = eval(text.value);
		}
	} catch (e) {
		text.value = "ERR";
	}
	
	
}

function affiche(bouton) {
	text = document.getElementById("zone_affichage"); 
	text.value = text.value.concat(bouton.value);
}

function init() {
	type = document.getElementsByClassName("bouton_simple");
	for (var i of type) {
		i.setAttribute("onclick", "affiche(this)");
	}
}

function plusmoins() {
	text = document.getElementById("zone_affichage");
	if (text.value != "") {
		if (text.value.charAt(0) == "-") {
			text.value = text.value.slice(1,text.value.length);
		} else {
			if (complex(text) && !parentese(text)) {

				text.value = "(".concat(text.value.concat(")"));
			}
			
			text.value = "-".concat(text.value);
		}
	}







	function complex(text) {
		return (text.value.slice(1,text.value.length).includes("-")
				|| text.value.slice(1,text.value.length).includes("/")
				|| text.value.slice(1,text.value.length).includes("*")
				|| text.value.slice(1,text.value.length).includes("+"));
	}

	function parentese(text) {
		return (text.value.charAt(0) == "(" && text.value.endsWith(")"));
	}

}

function range_memory() {
	memory = document.getElementById("zone_affichage").value;
}

function affiche_memory() {
	if (memory != null) {
		text = document.getElementById("zone_affichage");
		text.value = text.value.concat(memory);
	}
	
}

function raz_memory() {
	if (memory != null) {
		memory = null;
	}
}

function mode_edition() {
	edition = false;
	touche_e = document.getElementById("E");
	touche_e.style.color = 'red';
	touche_e.setAttribute("onclick", "mode_calcul()");
	type = document.getElementsByClassName("bouton_libre");
	for (var i of type) {
		i.removeAttribute("onclick");
		i.setAttribute("ondblclick", "edit()");
		/////////////////
		// QUESTION 6
		////////////////
	}

	
}

function mode_calcul() {
	edition = true;
	touche_e = document.getElementById("E");
	touche_e.style.color = 'black';
	touche_e.setAttribute("onclick", "mode_edition()");

}

function edit() {

}