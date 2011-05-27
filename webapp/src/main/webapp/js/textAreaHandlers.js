var keyPressed = false;

function focusTextArea(id) {
    var element = document.getElementById(id);
    if (!keyPressed) {
        element.value = '';
    }
}

function blurTextArea(id, text) {
    var element = document.getElementById(id);
    if (element.value == '') {
        element.value = text;
        keyPressed = false;
    }
}

function keyUpTextArea() {
    keyPressed = true;
}

function isModified() {
    return keyPressed;
}