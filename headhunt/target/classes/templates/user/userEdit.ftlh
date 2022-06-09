<#import "../parts/common.ftlh" as prime/>

<@prime.page "Изменение пользователя">

    <div style="display: flex; justify-content: center;">

        <form style="max-width: 300px;" method="post" action="/user" >
            <h5>Изменение пользователя:</h5>
            <label for="exampleFormControlInput1">Username: </label><input  class="form-control"  type="text" name = "username" value="${user.username}"/>
            <div style="display: flex; justify-content: flex-start; gap: 10px; margin-top: 10px;">
                <label for="exampleFormControlInput1">Роли: </label>
            <#list roles as role>
                <div>
                    <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}/> ${role}</label>
                </div>
            </#list>
            </div>
            <input type = "hidden" value="${user.id}" name="userId"/>
            <input type = "hidden" name = "_csrf" value="${_csrf.token}"/>
            <div style="margin-top: 10px;"><input  class="btn btn-primary mb-3" type="submit" value="Сохранить"/></div>
        </form>
    </div>
</@prime.page>