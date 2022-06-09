function checkPassword() {
    document.getElementById('password-message').innerHTML = "";
    let password = document.getElementById("password").value;

    let confirmPassword = document.getElementById("confirm-password").value;
    if(password !== confirmPassword) {
        document.getElementById('password-message').innerHTML = "Введённые пароли не совпадают";
        return false;
    };

    if(password.length <8) {
        document.getElementById('password-message').innerHTML = "Пароль должен содержать не менее 8 символов";
        return false;
    }

    if(!(/[a-z]/.test(password) && /[A-Z]/.test(password) && /[1-9]/.test(password))) {
        document.getElementById('password-message').innerHTML = "Пароль должен содержать цифру, латинскую букву, заглавную латинскую букву";
        return false;
    }

    if(/[\s]/.test(password)) {
        document.getElementById('password-message').innerHTML = "Пароль не может содержать пробельные символы";
        return false;
    }

    if(!/^[a-zA-Z0-9]+$/.test(password)){
        document.getElementById('password-message').innerHTML = "Пароль должен содержать только латинские буквы и цифры";
        return false;
    }
    return true;



}