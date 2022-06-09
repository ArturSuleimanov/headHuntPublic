<#macro submitWindow object postPath>
<div class="submit-window-shadow" id="submit-window">
    <form  class="card submit-card" method="post" action="${postPath}">
        <h5 class="card-title">Удаление ${object}</h5>
        <p class="card-text">Вы уверены, что хотите удалить ${object}?</p>
        <input type = "hidden" name = "_csrf" value="${_csrf.token}"/>
        <div class="submit-buttons">
            <a href="#" class="btn btn-primary" id="hide-button" style="margin-right: 10px;">Нет</a>
            <input class="btn btn-danger" type="submit" value="Удалить" style="border: none;"/>
        </div>
    </form>
</div>

</#macro>