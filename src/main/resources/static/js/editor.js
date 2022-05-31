const query_template= `
 <div className="panel">
    <form className="box" action="#" th:action="@{/editor}" method="post" th:object="${query}">
        <label>Query name:</label>
        <input type="text" className="edit-name-query" th:field="*{queryname}"/><br/>
        <select className="metrics" th:field="*{metric}">
            <option th:each="p : ${metric}" th:value="${p}" th:text="${p}"/>
        </select>
        <textarea type="text" className="write-new-metric"> </textarea>
        <button type="button" className="add-metric-button"> &nbsp; <span> Create New Metric &nbsp; <i
            className="fa fa-pencil"></i>&nbsp; </span></button>
        <br>
          
            <input type="text" className="function-text"> <br>
    </form>
</div> `

function delete_query(){
    document.getElementById("panel").remove();
}

function add_query() {
    /*var new_query = document.createElement("panel2");
    var form = document.createElement(form);
    var label = document.createElement(label);
    label.innerHTML = "Query name";

    form.append(label);
    new_query.append(form);
    document.getElementById("panel").append(new_query);*/

}

var clicked = false;

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