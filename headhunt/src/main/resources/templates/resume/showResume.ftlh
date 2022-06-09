<#import "../parts/common.ftlh" as prime/>
<#include "../parts/security.ftlh">
<#import "../parts/submitWindow.ftlh" as submitWindow/>
<#import "../parts/like.ftlh" as like/>

<@prime.page "Резюме">

    <@submitWindow.submitWindow "резюме" "/delete-resume/${resume.id}"/>


        <div class="container">
            <div class="row row-cols-1 row-cols-sm-2">

                <div class ="col" style="padding-bottom: 15px;">
                    <div class="card" style="display: flex; align-items: center;">
                        <img src="/img/${resume.filename}" class="img-fluid rounded-start"  style="object-fit:cover; width:250px; height:250px; border-radius: 5px;"/>
                    </div>
                </div>

                <div class ="col" style="padding-bottom: 15px;">
                    <div class="card ">
                        <h5>${resume.profession}</h5>
                        <a href="/user/profile/${resume.author.id}"><h6>${resume.firstname} ${resume.lastname}</h6></a>
                        <p>Дата рождения: ${resume.birthday?string('dd MMMM yyyy')}г.</p>
                        <p>${resume.mobileNumber}</p>
                        <p>Email: ${resume.email}</p>
                        <@like.like resume/>
                    </div>
                </div>
            </div>


            <#if resume.aboutMe??>
                <div class="row">
                    <div class ="col" style="padding-bottom: 15px;">
                        <div class="card">
                            <h5>О себе:</h5>
                            <p>${resume.aboutMe}</p>
                        </div>
                    </div>
                </div>
            </#if>

            <#if resume.education??>
            <div class="row row-cols-1 row-cols-sm-2">
                <div class ="col" style="padding-bottom: 15px;">
                    <div class="card">
                        <h5>Образование:</h5>
                        <p>${resume.education}</p>
                    </div>
                </div>
                </#if>
                <#if resume.workingExperience??>
                    <div class ="col" style="padding-bottom: 15px;">
                        <div class="card">
                            <h5>Опыт работы:</h5>
                            <p>${resume.workingExperience}</p>
                        </div>
                    </div>

                </#if>
                <#if resume.skills??>

                    <div class ="col" style="padding-bottom: 15px;">
                        <div class="card">
                            <h5>Навыки:</h5>
                            <p>${resume.skills}</p>
                        </div>
                    </div>
                </#if>
                <#if resume.hobby??>
                <div class ="col" style="padding-bottom: 15px;">
                    <div class="card">
                        <h5>Хобби:</h5>
                        <p>${resume.hobby}</p>
                    </div>
                </div>
            </div>
            </#if>
            <#if resume.author.id == user.getId()>
                <p style="display: flex; justify-content: flex-end;">
                    <small class="text-muted" style="color: darkgray; padding: 0">Обновлено: ${resume.dateUpdate?string('dd MMMM yyyy')}г. в ${resume.dateUpdate?time} <a href="/resume-update/${resume.id}">Обновить</a></small>

                </p>
                <div  class="mb-3" style="display: flex;  justify-content: flex-end; align-items: center">
                    <a href = "/resume-edit/${resume.id}" style="margin-right: 10px;">Изменить резюме</a>
                    <input class="btn btn-danger" id = "delete-button" type="submit" value="Удалить" style="border: none;">
                </div>
            </#if>
        </div>

    </div>
    </div>
    <script src="/static/scripts/submitWindow.js"></script>
</@prime.page>