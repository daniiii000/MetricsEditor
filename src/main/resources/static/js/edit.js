function displayDiv() {
    var teamextension = document.getElementById('teamextension');
    var x = document.getElementById("assister");
    var count = document.getElementById("count_div");
    var value = document.getElementById("value_div");
    var condition = document.getElementById("condition_div");
    var modifier = document.getElementById("modifier_div");
    var object = document.getElementById("object");

         document.getElementById('description_box').value = "You are modifying a " + document.getElementById('pattern').value + " metric for a " + document.getElementById('subject').value + ".\nPlease, change the information below to update the metric.";

        var pattern = document.getElementById('pattern').value;
        if (pattern === "frequency") {
            object.value = "object";
            condition.style.display = "none";
            count.style.display = "block";
            value.style.display = "none";
            modifier.style.display = "none";
        } else if (pattern === "standard deviation") {
            object.value = "object";
            condition.style.display = "none";
            value.style.display = "block";
            count.style.display = "none";
            modifier.style.display = "inline";

        } else if (pattern === "percentage") {
            object.value = "object";
            condition.style.display = "inline";
            modifier.style.display = "inline";
            value.style.display = "none";
            count.style.display = "none";
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

        type.value = "type";
        subject.value = "subject";
        teamextension.value = "teamextension";
        modifier.value = "modifier";
        modifier_attribute.value = "modifier_attribute";
        condition.value = "condition";
        condition_attribute.value = "condition_attribute";
        value.value = "value";
        name.value = "";
        count.value = "count"
        count_attribute.value = "count_attribute";
}

function subject_enablement() {
    var type = document.getElementById('type');
    var subject = document.getElementById('subject');
    var teamextension = document.getElementById('teamextension');

    if(type.value !== "type") {
        subject.disabled = false;

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
        teamextension.disabled = false;
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

    if(object.value !== "object") {
        modifier.disabled = false;
        if (modifier.value !== "none") {
            modifier_attribute.disabled = false;
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
    }

}

function modifier_expressions() {
    var modifier = document.getElementById("modifier");
    var modifier_attribute = document.getElementById("modifier_attribute");

    if (modifier.value !== "none" && modifier.value !== "modifier") {
        modifier_attribute.enabled = true;
    }
    else {
        modifier_attribute.value = "modifier_attribute";
        modifier_attribute.disabled = true;
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
    }
}

function count_changes() {
    var count = document.getElementById("count");
    var count_attribute = document.getElementById("count_attribute");
    var name = document.getElementById("name_metric");
    var update = document.getElementById("update_button");

    if(count.value !== "count" && count_attribute.value !== "count_attribute") {
        name.disabled = false;
    }
    else {
        name.disabled = true;
        name.value = "";
        update.disabled = true;
        count_attribute.value = "count_attribute";
    }
}

function condition_changes() {
    var condition = document.getElementById("condition");
    var condition_attribute = document.getElementById("condition_attribute");
    var name = document.getElementById("name_metric");
    var update = document.getElementById("update_button");

    if (condition.value !== "condition") {
        condition_attribute.disabled = false;
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
    }
}

function count_enablement() {

    var object = document.getElementById("object");
    var count = document.getElementById("count");
    var count_attribute = document.getElementById("count_attribute");
    var name = document.getElementById("name_metric");

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
    }
}

function button_enablement() {
    var update = document.getElementById("update_button");
    var name = document.getElementById("name_metric");
    if (name.value.length !== 0) {
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

    if(value.value !== "value") {
        value_input.disabled = false;
        name.disabled = false;
    }
    else
    {
        value_input.disabled = true;
        name.disabled = true;
        name.value = "";
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
    var condition = document.getElementById("condition");
    var condition_text = condition.options[condition.selectedIndex].text;
    var condition_attribute = document.getElementById("condition_attribute");
    var condition_attribute_text = condition_attribute.options[condition_attribute.selectedIndex].text;
    var name_text = document.getElementById("name_metric").value;

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


    var formData = new FormData();
    formData.append("pattern", pattern_text);
    formData.append("type", type_text);
    formData.append("subject", subject_text);
    formData.append("teamextension", teamextension_text);
    formData.append("object", object_text);
    formData.append("modifier", modifier_text);
    formData.append("modifier_attribute", modifier_attribute_text);
    formData.append("condition", condition_text);
    formData.append("condition_attribute", condition_attribute_text);
    formData.append("name", name_text);
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
