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

function hideshow(id) {
    var element = document.getElementById(id);
    if (counter % 2 == 0) {
        element.style.display = 'block';
    } else {
        element.style.display = 'none';
    }
    counter++;
}