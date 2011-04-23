function focusTextArea(id) {
    var element = document.getElementById(id);
    element.value = '';
}

function blurTextArea(id, text) {
    var element = document.getElementById(id);
    element.value = text;
}