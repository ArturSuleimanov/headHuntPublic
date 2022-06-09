<#import "like.ftlh" as like/>

<#macro resumes resume >
    <div class="user-post-border mb-2 mt-2">
        <@like.like resume/>
        <a class="user-post" href="/resume/${resume.id}">
            <div class="user-post">
                <img src="/img/${resume.filename}" class="img-fluid rounded-start"  style="object-fit:cover; width:250px; height:250px; border-radius: 5px;"/>
                <div class=name-surname>
                    <h5>${resume.profession}</h5>
                    <h6>${resume.firstname} ${resume.lastname}</h6>
                    <p>${resume.birthday?string('dd MMMM yyyy')}г.</p>
                    <p>${resume.mobileNumber}</p>
                    <p>${resume.email}</p>
                    <p><small class="text-muted" style="color: darkgray;">Обновлено: ${resume.dateUpdate?string('dd MMMM yyyy')}г. в ${resume.dateUpdate?time}</small></p>
                </div>
            </div>
        </a>
    </div>
</#macro>