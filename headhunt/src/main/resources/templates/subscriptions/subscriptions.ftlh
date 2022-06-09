<#import "../parts/common.ftlh" as prime>

<@prime.page "${type} ${curUser.username}">
    <h1 style="color: darkslategray;">${curUser.username}</h1>
    <h6>${type}</h6>
    <ul class="list-group">
        <#list users as user>
        <li class="list-group-item">
            <a href="/user/profile/${user.id}">${user.firstname} ${user.lastname}</a>
        </li>
        <#else>
            <div style="display: flex; justify-content: center; width: 100%">Вы ещё ни на кого не подписаны</div>
        </#list>
    </ul>
</@prime.page>