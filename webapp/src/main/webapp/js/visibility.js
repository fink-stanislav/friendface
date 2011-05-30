function showElement(id) {
    var element = document.getElementById(id);
    element.style.display = 'block';
    return true;
}

function hideElement(id) {
    var element = document.getElementById(id);
    element.style.display = 'none';
    return true;
}

var counter = 0;

function hideShow(id) {
    var element = document.getElementById(id);
    if (element.style.display == 'none' || element.style.display == '') {
        element.style.display = 'block';
    } else {
        element.style.display = 'none';
    }
    counter++;

    var arguments = hideShow.arguments;
    if (arguments.length > 1) {
        for (var i = 1; i < arguments.length; i++) {
            element = document.getElementById(arguments[i]);
            element.style.display = 'none';
        }
    }
}