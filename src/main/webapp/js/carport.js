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
        document.getElementsByName("shedLength")[0].disabled = true;
        document.getElementsByName("shedWidth")[0].disabled = true;
    } else {
        x.style.display = "block";
        document.getElementsByName("shedLength")[0].disabled = false;
        document.getElementsByName("shedWidth")[0].disabled = false;
    }

}