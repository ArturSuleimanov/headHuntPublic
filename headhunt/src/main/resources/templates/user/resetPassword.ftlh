<#import "../parts/common.ftlh" as prime/>
<#import "../parts/login.ftlh" as regForm/>

<@prime.page "Сброс пароля">

    <div style="display: flex; justify-content: center;">
        <form style="max-width: 300px;" method="post">
            <h4 class="mt-3" style="color: #0d6efd;">Сброс пароля</h4>
            <div class="mb-3">
                <label for="exampleFormControlInput1">Логин: </label><input class="form-control" type="text" name="username"  placeholder="Введите ваш логин" required/>
            </div>
            <p id="password-message" style="color: #FA8072; font-size: 15px;">${message!""}</p>
            <input type = "hidden" name = "_csrf" value="${_csrf.token}"/>
            <div><input  class="btn btn-primary mb-3" type="submit" value="Сброс"/></div>
        </form>
    </div>

</@prime.page>