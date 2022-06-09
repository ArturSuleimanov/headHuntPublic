<#import "../parts/common.ftlh" as prime/>
<#import "../parts/searchForm.ftlh" as search/>
<#import "../parts/pager.ftlh" as p/>

<@prime.page "Список пользователей">
    <h5>Список пользователей:</h5>
    <@search.search "/user"/>
    <@p.pager url usersPage/>
    <table class="table table-striped table-hover table-bordered mt-3">
        <thead>
        <tr>
            <th>id</th>
            <th>Username</th>
            <th>Роли</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list usersPage.content as user>
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td><#list user.roles as role>${role}<#sep>, </#list></td> <#-- here you can see separator which divides roles with ", "-->
                <td><a href = "/user/${user.id}">Изменить</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@prime.page>