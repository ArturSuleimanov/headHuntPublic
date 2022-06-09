<#import "../parts/common.ftlh" as prime/>
<@prime.page "Добавление сертификата">
    <div style="display: flex; justify-content: center;">
        <form style="max-width: 500px;" method="post" enctype="multipart/form-data"  onsubmit="return (validFile())">
            <div class="mb-3">
                <label for="exampleFormControlInput1" class="form-label">Название: </label>
                <input class="form-control ${(titleError??)?string('is-invalid', '')}" type="text" maxlength="255" name = "title" placeholder="Введите название вашего сертификата" required/>
                <#if titleError??>
                    <div>
                        ${titleError}
                    </div>
                </#if>
            </div>

            <div class="input-group mb-3">
                <label class="input-group-text" for="inputGroupFile02">Загрузите ваш сертификат </label>
                <input class="form-control" id="inputGroupFile02" type="file" name="file" required/>
                <p id="file-message" style="font-size: 15px;">Размер файла не должен превышать 5мб</p>
            </div>
            <div style="margin-bottom: 10px;">
                <#if captchaError??>
                    <div style="margin-bottom: 2px;" class="alert alert-danger" role="alert">
                        ${captchaError}
                    </div>
                </#if>
                <div class="g-recaptcha" data-sitekey="6LeNkjwgAAAAAF6FSMIg2D1aWC3QjjEa5VxrtDJk"></div>
            </div>

            <input type = "hidden" name = "_csrf" value="${_csrf.token}"/>
            <button class="btn btn-primary mb-3" type="submit">Добавить</button>
        </form>
    </div>
    <script>
        function validFile() {
            var uploadFile = document.getElementById('inputGroupFile02');
            if(uploadFile.files[0].size > 5242880) {
                document.getElementById('file-message').style.color = "#FA8072";
                return false;
            }
            return true;
        }
    </script>
</@prime.page>