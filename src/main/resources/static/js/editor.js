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

/*function add_query() {
    document.getElementById('panel').insertAdjacentHTML('afterend',
        <div id="panel2">
            <form className="box" action="#" th:action="@{/editor}" method="post" th:object="${query}">
                <label>Query name:</label>
                <input type="text" className="edit-name-query" th:field="*{queryname}"/><br/>
                <select className="metrics" th:field="*{metric}">
                    <option th:each="p : ${metric}" th:value="${p}" th:text="${p}"/>
                </select>
            </form>
        </div>);
}*/