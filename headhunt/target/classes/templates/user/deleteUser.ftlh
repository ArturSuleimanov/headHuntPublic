<#import "../parts/common.ftlh" as prime/>


<@prime.page "Удаление пользователя">
    <div style="display: flex; justify-content: center;">
        <form style="max-width: 300px;"  method="post">
            <h4 class="mt-3" style="color: #0d6efd;">Удаление пользователя</h4>
            <div class="mb-3">
                <label for="exampleFormControlInput1">Введите пароль для подтверждения: </label><input maxlength="55" class="form-control" type="password" placeholder="Пароль" id="password" name="password"/>
            </div>
            <p  style="color: #FA8072; font-size: 15px;">${message?ifExists}</p>
            <input type = "hidden" name = "_csrf" value="${_csrf.token}"/>
            <div><input  class="btn btn-danger mb-3" type="submit" value="Удалить"/></div>
        </form>
    </div>
</@prime.page>