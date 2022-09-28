function displayDiv() {
    var teamextension = document.getElementById('teamextension');
    var x = document.getElementById("assister");
    var count = document.getElementById("count_div");
    var value = document.getElementById("value_div");
    var condition = document.getElementById("condition_div");
    var modifier = document.getElementById("modifier_div");
    var object = document.getElementById("object");
    var subject = document.getElementById("subject");
    var pattern = document.getElementById("pattern");
    var type = document.getElementById("type");
    if ((teamextension.disabled === true && subject.value === "team" && type.value !== "type")
        ||(teamextension.value !== "teamextension" && teamextension.disabled === false && subject.value === "individual")) {
        x.style.display = "inline";

        document.getElementById('description_box').value = "You are modifying a " + document.getElementById('pattern').value + " metric for a " + document.getElementById('subject').value + ".\nPlease, complete the information below to update the metric.";

        if (pattern.value === "frequency") {
            object.value = "object";
            condition.style.display = "none";
            count.style.display = "block";
            value.style.display = "none";
            modifier.style.display = "none";
            object.options[3].disabled = true;
        } else if (pattern.value === "standard deviation") {
            object.value = "object";
            condition.style.display = "none";
            value.style.display = "block";
            count.style.display = "none";
            modifier.style.display = "inline";
            subject.value = "team";
            teamextension.disabled = true;
            teamextension.value = "teamextension";
            object.options[3].disabled = false;

        } else if (pattern.value === "percentage") {
            object.value = "object";
            condition.style.display = "inline";
            modifier.style.display = "inline";
            value.style.display = "none";
            count.style.display = "none";
            object.options[3].disabled = false;
        }
    }
    else {
        x.style.display = 'none';
    }
}

function type_enablement() {
    var pattern = document.getElementById('pattern');
    var type = document.getElementById('type');
    var subject = document.getElementById('subject');
    var teamextension = document.getElementById('teamextension');

    var modifier = document.getElementById('modifier');
    var modifier_attribute = document.getElementById('modifier_attribute');
    var condition = document.getElementById('condition');
    var condition_attribute = document.getElementById('condition_attribute');
    var name = document.getElementById('name_metric');
    var value = document.getElementById('value');
    var count = document.getElementById('count');
    var count_attribute = document.getElementById('count_attribute');
    var update = document.getElementById("update_button");
    var comments = document.getElementById("comments_metric");

    if(pattern.value !== "pattern") {
        type.disabled = false;
        if (pattern.value === "standard deviation") {
            subject.value = "team";
            subject.options[1].disabled = true;
            teamextension.disabled = true;
            teamextension.value = "teamextension";
        }
        else if (pattern.value !== "standard deviation") {
            subject.value = "subject";
            subject.options[1].disabled = false;
        }
    } else {
        type.value = "type";
        type.disabled = true;
        subject.value = "subject";
        subject.disabled = true;
        teamextension.value = "teamextension";
        document.getElementById('assister').style.display = 'none';
        teamextension.disabled = true;
        modifier.disabled = true;
        modifier.value = "modifier";
        modifier_attribute.disabled = true;
        modifier_attribute.value = "modifier_attribute";
        condition.disabled = true;
        condition.value = "condition";
        condition_attribute.disabled = true;
        condition_attribute.value = "condition_attribute";
        value.disabled = true;
        value.value = "value";
        name.disabled = true;
        name.value = "";
        count.disabled = true;
        count.value = "count"
        count_attribute.disabled = true;
        count_attribute.value = "count_attribute"
        update.disabled = true;
        comments.value = "";
        comments.disabled = true;
    }
}

function subject_enablement() {
    var type = document.getElementById('type');
    var subject = document.getElementById('subject');
    var teamextension = document.getElementById('teamextension');
    var pattern = document.getElementById("pattern");

    if(type.value !== "type") {
        subject.disabled = false;
        if (pattern.value === "standard deviation") {
            document.getElementById('assister').style.display = 'inline';
        }

    } else {
        subject.value = "subject";
        subject.disabled = true;
        teamextension.value = "teamextension";
        document.getElementById('assister').style.display = 'none';
        teamextension.disabled = true;
    }
}

function teamextension_enablement() {
    var subject = document.getElementById('subject');
    var teamextension = document.getElementById('teamextension');

    if(subject.value !== "subject") {
        if (subject.value === "team") {
            teamextension.disabled = true;
            teamextension.value = "teamextension";
            document.getElementById('assister').style.display = 'inline';
        }
        else if (subject.value === "individual") {
            teamextension.disabled = false;
        }
    } else {
        teamextension.value = "teamextension";
        document.getElementById('assister').style.display = 'none';
        teamextension.disabled = true;
    }
}

function modifier_enablement() {
    var pattern = document.getElementById("pattern");
    var object = document.getElementById("object");
    var modifier = document.getElementById("modifier");
    var modifier_attribute = document.getElementById("modifier_attribute");
    var condition = document.getElementById("condition");
    var condition_attribute = document.getElementById("condition_attribute");
    var name = document.getElementById("name_metric");
    var count = document.getElementById("count");
    var count_attribute = document.getElementById("count_attribute");
    var value = document.getElementById("value");
    var value_input = document.getElementById("value_input");
    var update = document.getElementById("update_button");
    var comments = document.getElementById("comments_metric");

    if(object.value !== "object") {
        modifier.disabled = false;
        if (modifier.value !== "none" && modifier.value !== "modifier") {
            modifier_attribute.disabled = false;
        }
        if (object.value === "commit") {
            modifier.options[1].disabled = false;
            modifier.options[2].disabled = true;
            modifier.options[3].disabled = false;
            modifier.options[4].disabled = true;
            modifier.options[0].display;
        }
        else if (object.value === "task" || object.value === "userstory") {
            modifier.options[1].disabled = false;
            modifier.options[2].disabled = false;
            modifier.options[3].disabled = true;
            modifier.options[4].disabled = false;
        }
    }
    else
    {
        modifier.value = "modifier";
        modifier_attribute.value = "modifier_attribute";
        modifier.disabled = true;
        modifier_attribute.disabled = true;
        condition.value = "condition";
        condition_attribute.value = "condition_attribute";
        condition.disabled = true;
        condition_attribute.disabled = true;
        count.disabled = true;
        count.value = "count";
        count_attribute.disabled = true;
        count_attribute.value = "count_attribute";
        value.disabled = true;
        value.value = "value";
        value_input.value = "";
        value_input.disabled = true;
        name.disabled = true;
        name.value = "";
        update.disabled = true;
        comments.value = "";
        comments.disabled = true;
    }

}

function modifier_expressions() {
    var modifier = document.getElementById("modifier");
    var modifier_attribute = document.getElementById("modifier_attribute");

    if (modifier.value !== "none" && modifier.value !== "modifier") {
        modifier_attribute.disabled = false;
        if (modifier.value === "state") {
            modifier_attribute.options[1].disabled = false;
            modifier_attribute.options[2].disabled = true;
            modifier_attribute.options[3].disabled = true;
            modifier_attribute.options[4].disabled = true;
        }
        else if (modifier.value === "sum") {
            modifier_attribute.options[1].disabled = true;
            modifier_attribute.options[2].disabled = false;
            modifier_attribute.options[3].disabled = true;
            modifier_attribute.options[4].disabled = true;
        }
        else if (modifier.value === "defined") {
            modifier_attribute.options[1].disabled = true;
            modifier_attribute.options[2].disabled = true;
            modifier_attribute.options[3].disabled = false;
            modifier_attribute.options[4].disabled = false;
        }
    }
    else {
        modifier_attribute.value = "modifier_attribute";
        modifier_attribute.disabled = true;
    }
}


function condition_enablement() {
    var object = document.getElementById("object");
    var condition = document.getElementById("condition");

    if (object.value !== "object") {
        if (object.value === "commit") {
            condition.options[1].disabled = true;
            condition.options[2].disabled = true;
            condition.options[3].disabled = false;
            condition.options[4].disabled = false;
            condition.options[5].disabled = false;
            condition.options[6].disabled = true;
        }
        else if (object.value === "task") {
            condition.options[1].disabled = false;
            condition.options[2].disabled = false;
            condition.options[3].disabled = true;
            condition.options[4].disabled = false;
            condition.options[5].disabled = false;
            condition.options[6].disabled = false;
        }
        else if (object.value === "userstory") {
            condition.options[1].disabled = false;
            condition.options[2].disabled = false;
            condition.options[3].disabled = false;
            condition.options[4].disabled = true;
            condition.options[5].disabled = false;
            condition.options[6].disabled = false;
        }
    }
}

function cond_val_enablement() {
    var modifier = document.getElementById("modifier");
    var modifier_attribute = document.getElementById("modifier_attribute");
    var condition = document.getElementById("condition");
    var condition_attribute = document.getElementById("condition_attribute");
    var value = document.getElementById("value");
    var name = document.getElementById("name_metric");
    var update = document.getElementById("update_button");
    var comments = document.getElementById("comments_metric");

    if(modifier.value !== "modifier") {
        condition.disabled = false;
        value.disabled = false;
        if (modifier.value === "none") {
            modifier_attribute.disabled = true;
            modifier_attribute.value = "modifier_attribute";
        }
        else {
            modifier_attribute.disabled = false;
        }
    }
    else {
        condition.value = "condition";
        condition_attribute.value = "condition_attribute";
        condition.disabled = true;
        condition_attribute.disabled = true;
        value.value = "value";
        value.disabled = true;
        name.disabled = true;
        name.value = "";
        update.disabled = true;
        comments.value = "";
        comments.disabled = true;
    }
}

function count_changes() {
    var count = document.getElementById("count");
    var count_attribute = document.getElementById("count_attribute");
    var name = document.getElementById("name_metric");
    var update = document.getElementById("update_button");
    var comments = document.getElementById("comments_metric");

    if(count.value !== "count" && count_attribute.value !== "count_attribute") {
        name.disabled = false;
    }
    else {
        name.disabled = true;
        name.value = "";
        update.disabled = true;
        count_attribute.value = "count_attribute";
        comments.value = "";
        comments.disabled = true;
    }
}

function condition_changes() {
    var condition = document.getElementById("condition");
    var condition_attribute = document.getElementById("condition_attribute");
    var name = document.getElementById("name_metric");
    var update = document.getElementById("update_button");
    var comments = document.getElementById("comments_metric");

    if (condition.value !== "condition") {
        condition_attribute.disabled = false;
        if (condition.value === "defined") {
            condition_attribute.options[1].disabled = false;
            condition_attribute.options[2].disabled = false;
            condition_attribute.options[3].disabled = true;
            condition_attribute.options[4].disabled = true;
            condition_attribute.options[5].disabled = true;
            condition_attribute.options[6].disabled = true;
            condition_attribute.options[7].disabled = true;
            condition_attribute.options[8].disabled = true;
        }
        else if (condition.value === "card") {
            condition_attribute.options[1].disabled = true;
            condition_attribute.options[2].disabled = true;
            condition_attribute.options[3].disabled = true;
            condition_attribute.options[4].disabled = true;
            condition_attribute.options[5].disabled = false;
            condition_attribute.options[6].disabled = true;
            condition_attribute.options[7].disabled = true;
            condition_attribute.options[8].disabled = true;
        }
        else if (condition.value === "apply") {
            condition_attribute.options[1].disabled = true;
            condition_attribute.options[2].disabled = true;
            condition_attribute.options[3].disabled = true;
            condition_attribute.options[4].disabled = true;
            condition_attribute.options[5].disabled = true;
            condition_attribute.options[6].disabled = false;
            condition_attribute.options[7].disabled = false;
            condition_attribute.options[8].disabled = true;
        }
        else if (condition.value === "self" || condition.value === "not_defined") {
            condition_attribute.options[1].disabled = true;
            condition_attribute.options[2].disabled = true;
            condition_attribute.options[3].disabled = false;
            condition_attribute.options[4].disabled = false;
            condition_attribute.options[5].disabled = true;
            condition_attribute.options[6].disabled = true;
            condition_attribute.options[7].disabled = true;
            condition_attribute.options[8].disabled = true;
        }
        else if (condition.value === "state") {
            condition_attribute.options[1].disabled = true;
            condition_attribute.options[2].disabled = true;
            condition_attribute.options[3].disabled = true;
            condition_attribute.options[4].disabled = true;
            condition_attribute.options[5].disabled = true;
            condition_attribute.options[6].disabled = true;
            condition_attribute.options[7].disabled = true;
            condition_attribute.options[8].disabled = false;
        }

        if (condition_attribute.value !== "condition_attribute") {
            name.disabled = false;
        } else {
            condition_attribute.value = "condition_attribute";
        }
    }
    else {
        name.disabled = true;
        name.value = "";
        update.disabled = true;
        condition_attribute.value = "condition_attribute";
        condition_attribute.disabled = true;
        comments.value = "";
        comments.disabled = true;
    }
}

function count_enablement() {

    var object = document.getElementById("object");
    var count = document.getElementById("count");
    var count_attribute = document.getElementById("count_attribute");
    var name = document.getElementById("name_metric");
    var comments = document.getElementById("comments_metric");

    if(object.value !== "object") {
        count.disabled = false;
        count_attribute.disabled = false;
    }
    else
    {
        count.disabled = true;
        count.value = "count";
        count_attribute.disabled = true;
        count_attribute.value = "count_attribute";
        name.disabled = true;
        name.value = "";
        comments.value = "";
        comments.disabled = true;
    }
}

function comments_enablement() {
    var update = document.getElementById("update_button");
    var name = document.getElementById("name_metric");
    var comments = document.getElementById("comments_metric");
    if (name.value.length !== 0) {
        comments.disabled = false;
    }
    else {
        comments.disabled = true;
        update.disabled = true;
    }
}

function button_enablement() {
    var update = document.getElementById("update_button");
    var comments = document.getElementById("comments_metric");
    if (comments.value.length !== 0) {
        update.disabled = false;
    }
    else update.disabled = true;
}

function value_expressions() {
    var value = document.getElementById("value").value;
    var value_text = document.getElementById("value_input");
    var update = document.getElementById("update_button");
    if (value !== "value") {
        value_text.value += value;
    }
    else {
        value_text.value = "";
        update.disabled = true;
    }
}

function value_changes() {
    var value = document.getElementById("value");
    var value_input = document.getElementById("value_input");
    var name = document.getElementById("name_metric");
    var description = document.getElementById("comments_metric");

    if(value.value !== "value") {
        value_input.disabled = false;
        name.disabled = false;
    }
    else
    {
        value_input.disabled = true;
        name.disabled = true;
        name.value = "";
        description.value = "";
        description.disabled = true;
    }
}

function update() {
    var pattern = document.getElementById("pattern");
    var pattern_text = pattern.options[pattern.selectedIndex].text;
    var type = document.getElementById("type");
    var type_text = type.options[type.selectedIndex].text;
    var subject = document.getElementById("subject");
    var subject_text = subject.options[subject.selectedIndex].text;
    var teamextension = document.getElementById("teamextension");
    var teamextension_text = teamextension.options[teamextension.selectedIndex].text;

    var object = document.getElementById("object");
    var object_text = object.options[object.selectedIndex].text;
    var modifier = document.getElementById("modifier");
    var modifier_text = modifier.options[modifier.selectedIndex].text;
    var modifier_attribute = document.getElementById("modifier_attribute");
    var modifier_attribute_text = modifier_attribute.options[modifier_attribute.selectedIndex].text;
    if (modifier_attribute_text === "Select an option") {
        modifier_attribute_text = "-";
    }
    var condition = document.getElementById("condition");
    var condition_text = condition.options[condition.selectedIndex].text;
    var condition_attribute = document.getElementById("condition_attribute");
    var condition_attribute_text = condition_attribute.options[condition_attribute.selectedIndex].text;
    var value_text = document.getElementById("value_input").value;
    var count = document.getElementById("count");
    var count_text = count.options[count.selectedIndex].text;
    var count_attribute = document.getElementById("count_attribute");
    var count_attribute_text = count_attribute.options[count_attribute.selectedIndex].text;
    var name_text = document.getElementById("name_metric").value;
    var description_text = document.getElementById("comments_metric").value;

    console.log(pattern_text);
    console.log(type_text);
    console.log(subject_text);
    console.log(teamextension_text);
    console.log(object_text);
    console.log(modifier_text);
    console.log(modifier_attribute_text);
    console.log(condition_text);
    console.log(condition_attribute_text);
    console.log(name_text);
    console.log(value_text);
    console.log(count_text);
    console.log(count_attribute_text);
    console.log(description_text);


    var formData = new FormData();
    formData.append("pattern", pattern_text);
    formData.append("type", type_text);
    formData.append("subject", subject_text);
    formData.append("teamextension", teamextension_text);
    formData.append("object", object_text);
    formData.append("name", name_text);
    formData.append("description", description_text);
    if (pattern_text === "Percentage") {
        formData.append("modifier", modifier_text);
        formData.append("modifier_attribute", modifier_attribute_text);
        formData.append("condition", condition_text);
        formData.append("condition_attribute", condition_attribute_text);
    }
    else if (pattern_text === "Standard Deviation") {
        formData.append("modifier", modifier_text);
        formData.append("modifier_attribute", modifier_attribute_text);
        formData.append("value", value_text);
    }
    else if (pattern_text === "Frequency") {
        formData.append("count", count_text);
        formData.append("count_attribute", count_attribute_text);
    }
    jQuery.ajax({
        url: "/update/{id}",
        data: formData,
        type: "PUT",
        async: true,
        contentType: false,
        processData: false,
        //ToDo: the service produces more than one error, the current message does not fit all of them
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus);
            console.log(errorThrown);
        },
        success: function() {
            location.href = "/metrics";
        }
    });
}
