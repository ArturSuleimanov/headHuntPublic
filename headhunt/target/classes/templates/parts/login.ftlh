<#macro login path1 path2 buttonValue redir title isRegisterForm color>
<#-- path is an argument-->
    <div style="display: flex; justify-content: center;">
        <form style="max-width: 300px;" action="${path1}" method="post" onsubmit="return (checkPassword())">
            <h4 class="mt-3" style="color: #0d6efd;">${title}</h4>
            <p style="color: ${color}" class>${message?ifExists}</p>
            <div class="mb-3">
                <label for="exampleFormControlInput1">Логин: </label><input  maxlength="55" class="form-control ${(usernameError??)?string('is-invalid', '')}" placeholder="Login" value="${username?ifExists}" type="text" name="username" required/>
                <#if usernameError??>
                    <div class ="server-error">
                        ${usernameError}
                    </div>
                </#if>
            </div>
            <#if isRegisterForm>
                <div class="mb-3">
                    <label for="exampleFormControlInput1">Имя: </label><input class="form-control form-control ${(firstnameError??)?string('is-invalid', '')}" type="text" name="firstname" value="${firstname?ifExists}" placeholder="Имя" required/>
                    <#if usernameError??>
                        <div class ="server-error">
                            ${firstnameError}
                        </div>
                    </#if>
                </div>
                <div class="mb-3">
                    <label for="exampleFormControlInput1">Фамилия: </label><input class="form-control ${(lastnameError??)?string('is-invalid', '')}" type="text" name="lastname" value="${lastname?ifExists}" placeholder="Фамилия" required/>
                    <#if lastnameError??>
                        <div class ="server-error">
                            ${lastnameError}
                        </div>
                    </#if>
                </div>
            </#if>
            <div class="mb-3">
                <label for="exampleFormControlInput1">Пароль: </label><input  placeholder="Password" maxlength="55" class="form-control ${(passwordError??)?string('is-invalid', '')}" type="password" id="password" name="password" required/>
                <#if passwordError??>
                    <div class ="server-error">
                        ${passwordError}
                    </div>
                </#if>
            </div>
            <#if isRegisterForm>
            <div class="mb-3">
                <label for="exampleFormControlInput1">Повторите пароль: </label><input placeholder="Pepeat password"  maxlength="55" class="form-control" type="password" id="confirm-password" name="confirm-password" required/>
            </div>
                <p id="password-message" style="color: #FA8072; font-size: 15px;"></p>
            </#if>
            <#if RequestParameters.error??>
                <p style="color: #FA8072; font-size: 15px;">Введён неверный логин или пароль</p>
            </#if>
            <#if isRegisterForm>
                <div class="mb-3">
                    <label for="exampleFormControlInput1">Email: </label><input class="form-control ${(emailError??)?string('is-invalid', '')}" type="email" name="email" value="${mail?ifExists}" maxlength="55" placeholder="some@some.com"/>
                    <#if emailError??>
                        <div class ="server-error">
                            ${emailError}
                        </div>
                    </#if>
                </div>
                <div style="margin-bottom: 10px;">
                    <#if captchaError??>
                        <div style="margin-bottom: 2px;" class="alert alert-danger" role="alert">
                            ${captchaError}
                        </div>
                    </#if>
                    <div class="g-recaptcha" data-sitekey="6LeNkjwgAAAAAF6FSMIg2D1aWC3QjjEa5VxrtDJk"></div>
                </div>
            </#if>

            <input type = "hidden" name = "_csrf" value="${_csrf.token}"/>
            <div><input  class="btn btn-primary mb-3" type="submit" value="${buttonValue}"/></div>
            <div style ="display: flex; justify-content: space-between;">
            <a href="${path2}">${redir}</a>
            <#if !isRegisterForm>
                <a href="/user/reset-password">Забыли пароль?</a>
            </#if>
            </div>

                <p id="resend-message">${resendMessage?ifExists}</p>

            <p style="color: darkgray;" id = countdown></p>
        </form>
    </div>
        <script src="/static/scripts/resendEmailTimer.js"></script>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type = "hidden" name = "_csrf" value="${_csrf.token}"/>
        <input  class="btn btn-primary" type="submit" value="Выйти" style="border: none;"/>
    </form>
</#macro>