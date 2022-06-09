<#include "security.ftlh">

    <div id ="side-bar" class="side-bar" onclick="scrollUp()">
        <div>
        <#if known>
            <a class="nav-link" href="/chosen">Избранное</a>
            <hr/>
            <a class="nav-link" href="/user/my-subscriptions">Обновления</a>
            <hr/>
            <a class="nav-link" href="/user/subscriptions/${id}">Мои подписки</a>
            <hr/>
            <a class="nav-link" href="/user/edit-profile">Редактировать профиль</a>
            <hr/>
            <a class="nav-link" href="/add-resume">Добавить резюме</a>
            <hr/>
            <a class="nav-link" href="/add-certificate">Добавить сертификат</a>
            <hr/>
            <a class="nav-link" href="/certificates/${id}">Мои сертификаты</a>

            <#if isAdmin>
                <hr/>
                <a class="nav-link" href="/user">Список пользователей</a>
            </#if>
        </#if>
        </div>
        <script src="/static/scripts/sidebar.js"></script>

    </div>
    <script>
        function scrollUp() {
            window.scrollTo(0, 0);
        }
    </script>

