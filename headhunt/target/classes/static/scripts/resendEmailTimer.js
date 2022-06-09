var flag = false;
function submitionTimer() {
if(!(document.getElementById("resend-message").innerHTML === "")){
    let a = 60;
    let element = document.getElementById("countdown");
    element.innerHTML = "Повторная отправка будет доступна через 0:60 секунд"
    let timer = setInterval(() => {
        a--;
        element.innerHTML = "Повторная отправка будет доступна через " + a+" секунд";
        if (a == 0) {
            clearInterval(timer);
            element.innerHTML = "";
            flag = true;
        }
    }, 1000);
} else {
    flag = true;
}

}
submitionTimer();
function submition() {
    return flag;
}


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


