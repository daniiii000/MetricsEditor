function displayDiv() {
    var x = document.getElementById("assister");
    var count = document.getElementById("count_div");
    var value = document.getElementById("value_div");
    x.style.display = "block";

    //var index = document.getElementById('subject');
   //if (index.value === "team") {
        document.getElementById('description_box').value = "You are creating a " + document.getElementById('pattern').value + " metric for a "+ document.getElementById('subject').value + ".\nPlease, complete the information below to generate the whole metric.";
   /* }
    if (index.value === "individual") {
        document.getElementById('description_box').value = "You are creating a " + document.getElementById('pattern').value + " metric for an "+ document.getElementById('subject').value + "\nPlease, complete the information below to generate the whole metric";
    }*/
    var pattern = document.getElementById('pattern').value;
    if (pattern === "frequency") {
        count.style.display = "block";
        value.style.display = "none";
    }
    else if (pattern === "standard deviation") {
        value.style.display = "block";
        count.style.display = "none";
    }
    else if (pattern === "percentage") {
        value.style.display = "none";
        count.style.display = "none";
    }

}

function modifier_expressions() {
    var modifier_value = document.getElementById("modifier").value;
    var modifier_attribute = document.getElementById("modifier_attribute");
    var label = document.getElementById("modifier_attribute_label");
    if (modifier_value !== "none" && modifier_value !== "modifier") {
        modifier_attribute.style.display = "inline";
        label.style.display = "inline";
    }
    else {
        modifier_attribute.style.display = "none";
        label.style.display = "none";
    }
}

function value_expressions() {
    var value = document.getElementById("value").value;
    var value_text = document.getElementById("value_input");
    if (value !== "value") {
        value_text.value += value;
    }
    else value_text.value = "";
}

function next() {

}

function back() {
//  volver a la sección de configuración anterior.
}

 function generate_metric() {
//    guardar la configuración, añadirla a la lista de métricas y generar los ficheros correspondientes*/

 }