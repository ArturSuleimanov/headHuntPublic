<#import "../parts/common.ftlh" as prime/>
<@prime.page "Изменение сертификата">
    <div style="display: flex; justify-content: center;">
        <form style="max-width: 500px;" action="/edit-certificate/${certificate.id}" method="post">
            <div class="mb-3">
                <label for="exampleFormControlInput1" class="form-label">Название: </label>
                <input class="form-control  ${(titleError??)?string('is-invalid', '')}" type="text" maxlength="255" name = "title" value="${certificate.title}" placeholder="Введите название вашего сертификата" required/>
                <#if titleError??>
                    <div>
                        ${titleError}
                    </div>
                </#if>
            </div>

            <input type = "hidden" name = "_csrf" value="${_csrf.token}"/>
            <button class="btn btn-primary mb-3" type="submit">Сохранить</button>
        </form>
    </div>
</@prime.page>