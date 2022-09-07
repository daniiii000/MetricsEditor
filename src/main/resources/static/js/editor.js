
function buildData() {
    const database = new DataStore('database.db');
}

function search_Metric() {
    var table = document.getElementById('mytable');
    var filter_value = document.getElementById('search_metric').value.toLowerCase();
    for (var i = 1; i < table.rows.length; i++) {
        var cellsOfRow = table.rows[i].getElementsByTagName('td');
        var found = false;
        for (var j = 0; j < cellsOfRow.length && !found; j++) {
            var compareWith = cellsOfRow[j].innerHTML.toLowerCase();
            if (filter_value.length === 0 || (compareWith.indexOf(filter_value) > -1)) {
                found = true;
            }
        }
        if (found) {
            table.rows[i].style.display = '';
        } else {
            table.rows[i].style.display = 'none';
        }
    }
}

function sortTable(n) {
    var shouldSwitch, i, x, y, rows, table, dir, switching, switchcount = 0;

    table = document.getElementById("myTable");
    switching = true;

    dir = "asc";

    while (switching) {
        switching = false;
        rows = table.rows;

        for (i = 1; i < (rows.length - 1); i++) {
            shouldSwitch = false;
            x = rows[i].getElementsByTagName("td")[n];
            y = rows[i + 1].getElementsByTagName("td")[n];

            if (dir === "asc") {
                if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                    shouldSwitch= true;
                    break;
                }
            } else if (dir === "desc") {
                if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                    shouldSwitch = true;
                    break;
                }
            }
        }
        if (shouldSwitch) {
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
            switchcount ++;
        } else {
            if (switchcount === 0 && dir === "asc") {
                dir = "desc";
                switching = true;
            }
        }
    }
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