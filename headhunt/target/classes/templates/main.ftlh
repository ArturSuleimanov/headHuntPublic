<#import "parts/common.ftlh" as prime/>
<#import "parts/pager.ftlh" as p/>
<#import "parts/resumes.ftlh" as res/>
<#import "parts/searchForm.ftlh" as search/>


<@prime.page "Главная">
    <@search.search "/"/>
    <@p.pager url page/>
    <div style="display: flex; flex-direction: column; align-items: center;">
        <#list page.content as resume>
            <@res.resumes resume/>
        <#else>
            <p class="mt-3" style="font-weight:normal">По запросу <span style="font-weight: bold">${filter}</span> не найдено ни одной записи</p>
        </#list>
    </div>
    <@p.pager url page/>
</@prime.page>