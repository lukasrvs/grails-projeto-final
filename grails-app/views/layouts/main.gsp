<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>Gestão de Vendas</title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:stylesheet src="bootstrap.css"/>
    <asset:stylesheet src="grails.css"/>
    <asset:stylesheet src="main.css"/>
    <asset:stylesheet src="mobile.css"/>
    <asset:stylesheet src="application.css"/>
    <g:layoutHead/>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark navbar-static-top" role="navigation">
    <a class="navbar-brand" href="/#"><asset:image src="grails.svg" alt="Grails Logo"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" aria-expanded="false" style="height: 0.8px;" id="navbarContent">
        <ul class="nav navbar-nav ml-auto">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Gerenciamento<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li class="controller">
                        <g:link controller="cliente" action="index">Clientes</g:link>
                    </li>
                    <li class="controller">
                        <g:link controller="produto" action="index">Produtos</g:link>
                    </li>
                    <li class="controller">
                        <g:link controller="venda" action="index">Vendas</g:link>
                    </li>
                    <li class="controller">
                        <g:link controller="usuario" action="index">Usuarios</g:link>
                    </li>
                </ul>
                <li class="encerrar">
                    <br/>
                        <g:link controller="auth" action="encerrar">Encerrar</g:link>
                </li>
            </li>
        </ul>
    </div>
</nav>
<g:layoutBody/>
<div class="footer " role="contentinfo">  
</div>
<div id="spinner" class="spinner" style="display:none;">
    <g:message code="spinner.alt" default="Loading&hellip;"/>
</div>
<asset:javascript src="jquery-3.3.1.min.js"/>
<asset:javascript src="bootstrap.js"/>
<asset:javascript src="popper.min"/>
<asset:javascript src="application.js"/>
<asset:javascript src="ajaxPost.js"/>
<g:ifPageProperty name="page.jsEspecifico">
        <g:pageProperty name="page.jsEspecifico"/>
</g:ifPageProperty>
</body>
</html>
