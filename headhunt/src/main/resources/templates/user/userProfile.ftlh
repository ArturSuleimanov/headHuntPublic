<#import "../parts/common.ftlh" as prime/>
<#include "../parts/security.ftlh">
<#import "../parts/resumes.ftlh" as res>

<@prime.page "Профиль ${name}">

    <div class="container">
        <h1 style="color: darkslategray;">${curUser.firstname} ${curUser.lastname}</h1>
        <h5><a href="/certificates/${curUser.id}">Сертификаты</a></h5>
        <#if !isCurrentUser>
            <#if isSubscriber>
                <a class="btn btn-info mb-2" href = "/user/unsubscribe/${curUser.id}">Отписаться</a>
            <#else>
                <a class="btn btn-info mb-3" href = "/user/subscribe/${curUser.id}">Подписаться</a>
            </#if>
        </#if>
        <div class="row">
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title"><h4>Подписки</h4></div>
                        <h3 class="card-text">
                            <a href="/user/subscriptions/${curUser.id}">${subscriptionsCount}</a>
                        </h3>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title"><h4>Подписчики</h4></div>
                        <h3 class="card-text">
                            <a href="/user/subscribers/${curUser.id}">${subscribersCount}</a>
                        </h3>
                    </div>
                </div>
            </div>
        </div>
    </div>
<div style="display: flex; flex-direction: column; align-items: center;">

        <#list resumes as resume>
            <@res.resumes resume/>
        <#else>
            <p class="mt-3" style="font-weight:normal">Этот пользователь не выставлял своё резюме</p>

        </#list>
    </div>
</@prime.page>