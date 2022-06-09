<#import "../parts/common.ftlh" as prime/>
<#import "../parts/login.ftlh" as regForm/>

<@prime.page "Смена пароля">

    <div style="display: flex; justify-content: center;">

        <div>
            <form style="max-width: 300px;" method="post" onsubmit="return (checkPassword())">

                <h4 class="mt-3" style="color: #0d6efd;">Изменение пароля</h4>
                <p  style="color: #3CB371; font-size: 15px;">${message2?ifExists}</p>
                <input class="form-control" type="hidden" name="username" value="default"/>
                <input class="form-control" type="hidden" name="firstname" value="default"/>
                <input class="form-control" type="hidden" name="lastname" value="default"/>
                <input class="form-control" type="hidden" name="email" value="some@some.com"/>

                <div class="mb-3">
                    <label for="exampleFormControlInput1">Старый пароль: </label><input maxlength="55" class="form-control" type="password" placeholder="Old password" name="oldPassword"/>
                </div>
                <p  style="color: #FA8072; font-size: 15px;">${message1?ifExists}</p>


                <div class="mb-3">
                    <label for="exampleFormControlInput1">Новый пароль: </label><input maxlength="55" class="form-control ${(passwordError??)?string('is-invalid', '')}" type="password" placeholder="New password" id="password" name="password"/>
                    <#if passwordError??>
                        <div class ="server-error">
                            ${passwordError}
                        </div>
                    </#if>
                </div>

                <div class="mb-3">
                    <label for="exampleFormControlInput1">Повторите пароль: </label><input  maxlength="55" class="form-control" placeholder="Confirm password" type="password" id="confirm-password" name="confirm-password"/>
                </div>
                <p id="password-message" style="color: #FA8072; font-size: 15px;"></p>
                <input type = "hidden" name = "_csrf" value="${_csrf.token}"/>
                <div><input  class="btn btn-primary mb-3" type="submit" value="Сохранить"/></div>

            </form>
        </div>
    </div>
    <script src="/static/scripts/checkPassword.js"></script>

</@prime.page>