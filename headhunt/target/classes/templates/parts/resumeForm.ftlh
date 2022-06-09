<#macro resumeForm buttonValue path>
    <#include "security.ftlh">

    <div style="display: flex; justify-content: center;">
        <form style="max-width: 500px;" action="${path}" method="post" enctype="multipart/form-data"  onsubmit="return (validPhone()&&validBirthdate()&&validEmail()&&validFile())">
            <div class="mb-3">
                <label for="exampleFormControlInput1" class="form-label">Имя: </label>
                <input class="form-control ${(firstnameError??)?string('is-invalid', '')}" type="text" maxlength="55" name = "firstname" value="${user.firstname}" placeholder="Введите ваше имя" required/>
                <#if firstnameError??>
                    <div class ="server-error">
                        ${firstnameError}
                    </div>
                </#if>
            </div>

            <div class="mb-3">
                <label for="exampleFormControlInput1"  maxlength="55" class="form-label">Фамилия: </label>
                <input class="form-control ${(lastnameError??)?string('is-invalid', '')}" type="text" name = "lastname" value="${user.lastname}" placeholder="Введите вашу фамилию" required/>
                <#if lastnameError??>
                    <div class ="server-error">
                        ${lastnameError}
                    </div>
                </#if>
            </div>

            <div class="mb-3">
                <label  maxlength="55" for="exampleFormControlInput1"class="form-label">Email: </label>
                <input class="form-control ${(emailError??)?string('is-invalid', '')}"  id = "email" type = "text" name = "email" value="${user.email}" placeholder="Введите ваш email" required/>
                <p id="email-message" style="color: #FA8072; font-size: 15px;"></p>
                <#if emailError??>
                    <div class ="server-error">
                        ${emailError}
                    </div>
                </#if>
            </div>

            <div class="mb-3">
                <label for="exampleFormControlInput1" class="form-label">Номер телефона: </label>
                <input maxlength="55" class="form-control ${(mobileNumberError??)?string('is-invalid', '')}"  id = "phone" type = "number" name = "mobileNumber" placeholder="8 999 1234567"/>
                <p id="phone-message" style="color: #FA8072; font-size: 15px;"></p>
                <#if mobileNumberError??>
                    <div class ="server-error">
                        ${mobileNumberError}
                    </div>
                </#if>
            </div>


            <div class="mb-3">
                <label for="exampleFormControlInput1" class="form-label">Дата рождения: </label>
                <input class="form-control" type="date" name = "birthday" id="cur-date"  required/>
                <p id="birthdate-message" style="color: #FA8072; font-size: 15px;"></p>
            </div>



            <div class="mb-3">
                <label for="exampleFormControlInput1" class="form-label">Профессия: </label>
                <input  maxlength="55" class="form-control ${(professionError??)?string('is-invalid', '')}" type="text" name = "profession" placeholder="Укажите желаемую профессию" required/>
                <#if professionError??>
                    <div class ="server-error">
                        ${professionError}
                    </div>
                </#if>
            </div>

            <div class="input-group mb-3">
                <label class="input-group-text" for="inputGroupFile02">Загрузите ваше фото</label>
                <input class="form-control" id="inputGroupFile02" type="file" name="file"/>
                <p id="file-message" style="font-size: 15px;">Размер файла не должен превышать 5мб</p>
            </div>

            <div class="mb-3">
                <label for="exampleFormControlInput1" class="form-label ${(aboutMeError??)?string('is-invalid', '')}">Расскажите о себе: </label>
                <textarea maxlength="1048" class="form-control" id="exampleFormControlTextarea1" rows="3" name="aboutMe" placeholder="О себе..."></textarea>
                <#if aboutMeError??>
                    <div class ="server-error">
                        ${aboutMeError}
                    </div>
                </#if>
            </div>


            <div class="mb-3">
                <label for="exampleFormControlInput1" class="form-label">Чем вы увлекаетесь: </label>
                <textarea maxlength="255" class="form-control ${(hobbyError??)?string('is-invalid', '')}" id="exampleFormControlTextarea1" rows="3" name="hobby"  placeholder="Ваши увлечения..."></textarea>
                <#if hobbyError??>
                    <div class ="server-error">
                        ${hobbyError}
                    </div>
                </#if>
            </div>

            <div class="mb-3">
                <label for="exampleFormControlInput1" class="form-label">Ваше образование: </label>
                <textarea maxlength="1048" class="form-control ${(educationError??)?string('is-invalid', '')}" id="exampleFormControlTextarea1" rows="3" name="education" placeholder="Ваше образование..."></textarea>
                <#if educationError??>
                    <div class ="server-error">
                        ${educationError}
                    </div>
                </#if>
            </div>

            <div class="mb-3">
                <label for="exampleFormControlInput1" class="form-label">Ваш опыт работы: </label>
                <textarea maxlength="1048" class="form-control ${(workingExperienceError??)?string('is-invalid', '')}" id="exampleFormControlTextarea1" rows="3" name="workingExperience"  placeholder="Опыт работы..."></textarea>
                <#if workingExperienceError??>
                    <div class ="server-error">
                        ${workingExperienceError}
                    </div>
                </#if>
            </div>

            <div class="mb-3">
                <label for="exampleFormControlInput1" class="form-label">Ваши навыки: </label>
                <textarea maxlength="1048" class="form-control  ${(skillsError??)?string('is-invalid', '')}" id="exampleFormControlTextarea1" rows="3" name="skills"  placeholder="Ваши навыки..."></textarea>
                <#if skillsError??>
                    <div class ="server-error">
                        ${skillsError}
                    </div>
                </#if>
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
    <script src="/static/scripts/resumeValidation.js"></script>

    <script>
        document.getElementById('cur-date').valueAsDate = new Date();
    </script>
</#macro>