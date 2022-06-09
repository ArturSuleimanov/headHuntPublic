<#include "security.ftlh">
<#import "login.ftlh" as logoutForm/>

<div class = 'main-head bg-light'>
    <div class="header">
        <div style="display: flex; align-items: center; gap:10px;" class="left" >
            <a style="font-size: 20px;" class="navbar-brand mb-0 h1" href="/">headHunt</a>
        </div>

        <div  style="display: flex; align-items: center; gap:10px;" class="log">
            <#if known>
                <a href="/user/profile/${id}" style="text-decoration: none; margin-right: 5px;" class="navbar-text">${user.username}</a>
                <button id = "falling-nav-button" class="falling-button" style="color: darkgrey;" type="button"><span class="navbar-toggler-icon">&equiv;</span></button>
                <@logoutForm.logout/>
            </#if>
        </div>
    </div>
</div>


