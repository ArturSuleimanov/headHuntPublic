function validBirthdate() {
    var longAgo = new Date("1922-01-01");
    var currentDate = new Date();
    var dateElem = document.getElementById('cur-date');
    var inputDate = new Date(dateElem.valueAsNumber);
    if(inputDate > currentDate || inputDate<longAgo) {
        document.getElementById('birthdate-message').innerHTML = "Указана некорректная дата рождения";
        return false;
    } else {document.getElementById('birthdate-message').innerHTML = "";}
    return true;
};
function validPhone() {
    var re = /^\d[\d\(\)\ -]{4,14}\d$/;
    var myPhone = document.getElementById('phone').value;
    var valid = re.test(myPhone);
    var output = "";
    if (!valid) output = 'Номер телефона введен неверно';
    document.getElementById('phone-message').innerHTML = output;
    return valid;
}
function validEmail() {
    var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,5})$/;
    var address = document.getElementById('email').value;
    var valid = reg.test(address);
    var output = "";
    if (!valid) output = "Введите корректный email";
    document.getElementById('email-message').innerHTML = output;
    return valid;
}
function validFile() {
    var uploadFile = document.getElementById('inputGroupFile02');
    if(uploadFile.files[0].size > 5242880) {
        document.getElementById('file-message').style.color = "#FA8072";
        return false;
    }
    return true;
}