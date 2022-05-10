document.getElementById("number1").disabled = true;

function disableFunc() {
    document.getElementById("number2").disabled = false;
    document.getElementById("number1").disabled = true;
}

function disableFunc2() {
    document.getElementById("number1").disabled = false;
    document.getElementById("number2").disabled = true;
}

var f = document.getElementById("skur")
f.style.display = "none";

function hideSkur() {

    var x = document.getElementById("skur");
    if (x.style.display === "block") {
        x.style.display = "none";
    } else {
        x.style.display = "block";
    }

}