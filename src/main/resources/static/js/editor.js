
function add_new_metric() {
    window.open("http://localhost:8080/assister", "Metric Assister", "width=1200,height=600,scrollbars=NO");
}

function buildData() {
    const database = new DataStore('database.db');
}


/*function hide_and_appear() {
    var toggle = document.getElementById("hidden-content");
    //var button = document.getElementById("hide_button");
    if (toggle.style.display !== "none") {
        toggle.style.display = "none";
        //button.classList.toggle("fa-play");
    }
    else {
        toggle.style.display = "block";
        //button.classList.toggle('');
    }
}*/