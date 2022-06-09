<#import "../parts/common.ftlh" as prime/>
<#import "../parts/login.ftlh" as regForm/>

<@prime.page "Изменение профиля">

    <div style="display: flex; justify-content: center;">

        <div>
            <form style="max-width: 300px;" method="post" onsubmit="return (checkPassword())">

                <h4 class="mt-3" style="color: #0d6efd;">Изменение профиля</h4>


                <div class="mb-3">
                    <label for="exampleFormControlInput1">Имя: </label><input class="form-control ${(firstnameError??)?string('is-invalid', '')}" type="text" name="firstname" value="${firstname}" placeholder="Имя" required/>
                    <#if firstnameError??>
                        <div class ="server-error">
                            ${firstnameError}
                        </div>
                    </#if>
                </div>
                <div class="mb-3">
                    <label for="exampleFormControlInput1">Фамилия: </label><input class="form-control ${(lastnameError??)?string('is-invalid', '')}" type="text" name="lastname" value="${lastname}" placeholder="Фамилия" required/>
                    <#if lastnameError??>
                        <div class ="server-error">
                            ${lastnameError}
                        </div>
                    </#if>
                </div>
                <div class="mb-3">
                    <label for="exampleFormControlInput1">Email: </label><input class="form-control ${(emailError??)?string('is-invalid', '')}" type="email" name="email" value="${mail?ifExists}" maxlength="55" placeholder="some@some.com"/>
                    <#if emailError??>
                        <div class ="server-error">
                            ${emailError}
                        </div>
                    </#if>
                </div>
                <input type="hidden" name="username" value="default"/>
                <input type="hidden" name="password" value="default"/>
                <input type = "hidden" name = "_csrf" value="${_csrf.token}"/>
                <div><input  class="btn btn-primary mb-3" type="submit" value="Сохранить"/></div>

            </form>
            <div  class="mb-3" style="display: flex; justify-content: space-between; align-items: center">
                <a href = "/user/change-password" style="margin-right: 10px;">Изменить пароль</a>
                <a href = "/user/delete-user" style="color: #dc3545;">Удалить пользователя</a>
            </div>
        </div>
    </div>


</@prime.page>