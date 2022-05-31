var clicked = false;

function delete_query(){
    document.getElementById("panel").remove();
    clicked = false;

}
function add_query() {
    var temp = document.getElementsByTagName("template")[0];
    var clon = temp.content.cloneNode(true);
    document.getElementById("content").appendChild(clon);

}

function add_new_metric() {
    if (!clicked) {
        clicked = true;
        var new_metric = document.createElement("div");

        var textarea = document.createElement("textarea")
        textarea.style.marginTop = "10px";
        textarea.style.width = "1000px";
        textarea.style.height = "80px";

        new_metric.appendChild(textarea);
        document.getElementById("metrics").appendChild(new_metric);
    }
}

function hide_and_appear() {
    var toggle = document.getElementById("hidden-content");
    if (toggle.style.display !== "none") {
        toggle.style.display = "none";
    }
    else {
        toggle.style.display = "block";
    }
}