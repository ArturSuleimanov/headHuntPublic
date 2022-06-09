<#import "../parts/common.ftlh" as prime/>
<#import "../parts/login.ftlh" as logoutForm/>

<#import "../parts/resumeForm.ftlh" as resForm/>

<@prime.page "Добавление резюме">
    <@resForm.resumeForm "Добавить" "/add-resume"/>
</@prime.page>