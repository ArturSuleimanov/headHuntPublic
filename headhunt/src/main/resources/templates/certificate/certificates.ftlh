<#import "../parts/common.ftlh" as prime/>
<#include "../parts/security.ftlh">

<@prime.page "Сертификаты ${name}">
    <div style="display: flex; flex-direction: column; align-items: center;">
        <#list certificates as certificate>
            <div class="submit-window-shadow" id="submit-window-${certificate.id}">
                <form  class="card submit-card" method="post" action="/delete-certificate/${certificate.id}">
                    <h5 class="card-title">Удаление сертификата</h5>
                    <p class="card-text">Вы уверены, что хотите удалить сертификат?</p>
                    <input type = "hidden" name = "_csrf" value="${_csrf.token}"/>
                    <div class="submit-buttons">
                        <a href="#" class="btn btn-primary" id="hide-button" style="margin-right: 10px;">Нет</a>
                        <input class="btn btn-danger" type="submit" value="Удалить" style="border: none;"/>
                    </div>
                </form>
            </div>
            <div class="card mb-3 mt-3">
                <img style="width: 100%;" src="/img/${certificate.filename}" class="card-img-top"/>
                <div class="card-body">
                    <h5 class="card-title">${certificate.title}</h5>
                    <#if id == certificate.author.id>
                        <div style="display: flex; justify-content: flex-end; align-items: center;">
                        <a style="margin-right: 10px;" href="/edit-certificate/${certificate.id}">Редактировать</a>
                            <input class="btn btn-danger" id = "delete-button-${certificate.id}" type="submit" value="Удалить" style="border: none;"/>
                        </div>
                        <script>
                            console.log(document.getElementById("delete-button-${certificate.id}"));
                            document.getElementById("delete-button-${certificate.id}").addEventListener("click", () => {
                                console.log("ldfjalsdjfl")
                                document.getElementById("submit-window-${certificate.id}").style.display = "flex";
                            });
                            document.getElementById("hide-button").addEventListener("click", () => {
                                document.getElementById("submit-window-${certificate.id}").style.display = "none";
                            });
                        </script>
                    </#if>
                </div>
            </div>
        <#else>
            <p class="mt-3" style="font-weight:normal">Не загружено ни одного сертификата</p>
        </#list>
    </div>


</@prime.page>