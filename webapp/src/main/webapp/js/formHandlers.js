function formSubmit(formId, submitForm) {
    var form = document.getElementById(formId);
    if (!submitForm) {
        form.reset();
    }
}

function submitForm(formId) {
    var form = document.getElementById(formId);
    form.submit();
}