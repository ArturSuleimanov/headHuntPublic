<#macro search path>
<form  id="search" method="get" action="${path}">
        <div style="display: flex; justify-content: space-between;" >
            <div style="width: 80%;">
                <input type = "text" placeholder="Поиск" name = "filter" value="${filter}" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"/>
            </div>
            <#-- don't need token here because we need it only in post requests-->
            <div style="width: calc(20% - 10px);">
                <button style="width: 100%;" class="btn btn-secondary" id = search-button type = submit>Найти</button>
            </div>
        </div>
    </form>
</#macro>