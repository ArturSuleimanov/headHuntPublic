<#--assign lets us send many variables into template-->
<#assign
known = Session.SPRING_SECURITY_CONTEXT??
<#--checking if this object excists, in this example we can say that user is authorized-->
>
<#if known>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        <#--here we get user so that we can use methods which defined in User.java-->
        name = user.getUsername()
        user = user
        isAdmin = user.isAdmin()
        id = user.getId()
    >
<#else>
    <#assign
        name="unknown"
        isAdmin = false
    >
</#if>
