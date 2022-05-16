function disableFunc() {
    document.getElementById("number2").disabled = false;
    document.getElementById("number3").disabled = false;
    document.getElementById("number1").disabled = true;
}

function disableFunc2() {
    document.getElementById("number1").disabled = false;
    document.getElementById("number2").disabled = true;
    document.getElementById("number3").disabled = true;
}

function visSkur() {

    var x = document.getElementById("skur");
    if (x.style.display === "block") {
        x.style.display = "none";
        document.getElementById("shedLength").setAttribute("disabled", "true");
        document.getElementById("shedWidth").setAttribute("disabled", "true");
    } else {
        x.style.display = "block";
        document.getElementById("shedLength").removeAttribute("disabled");
        document.getElementById("shedWidth").removeAttribute("disabled");
    }

}