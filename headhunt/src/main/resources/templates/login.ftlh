<#import "parts/common.ftlh" as prime/>
<#import "parts/login.ftlh" as logForm/>

<@prime.page "Логин">
    <@logForm.login "/login" "/registration" "Войти" "Регистрация" "Авторизация" false "#3CB371"/>
<#-- 'login' is an argument wich goes to parts/login.ftlh-->
</@prime.page>