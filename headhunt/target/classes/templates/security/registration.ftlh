<#import "../parts/common.ftlh" as prime/>
<#import "../parts/login.ftlh" as regForm/>

<@prime.page "Регистрация">
    <@regForm.login  "/registration" "/login" "Зарегистрироваться" "Авторизация" "Регистрация" true "#FA8072"/>
</@prime.page>