function formSubmit(formId, submitForm) {
    var form = document.getElementById(formId);
    if (!submitForm) {
        form.reset();
    }
}