<#macro like resume>
    <a class="like" href="/resume/${resume.id}/like">
        <#if resume.meLiked>
            <i class="fa-solid fa-star"></i>
        <#else>
            <i class="fa-regular fa-star"></i>
        </#if>
        ${resume.likes}
    </a>
</#macro>