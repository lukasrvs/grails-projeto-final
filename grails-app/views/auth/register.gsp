<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>Register</title>
<!------ Include the above in your HEAD tag ---------->
<g:set var="entityName" value="${message(code: 'usuario.label', default: 'Usuario')}" />
<div class="Container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header bg-success text-white font-weight-bold">Registrar nova conta</div>
                <div class="card-body bg-light">
                    <%-- começa o form --%>
                    <g:form action="salvar">
                        <div class="form-group row">
                            <label for="nome" class="col-md-4 col-form-label text-md-right">Nome</label>
                            <div class="col-md-6">
                                <g:field type="text" id="login" class="nome" name="nome" placeholder="nome" required="true"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="nome" class="col-md-4 col-form-label text-md-right">Usuario</label>
                            <div class="col-md-6">
                                <g:field type="text" id="login" class="usuario" name="usuario" placeholder="usuario" required="true"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="nome" class="col-md-4 col-form-label text-md-right">Senha</label>
                            <div class="col-md-6">
                                <g:field type="password" id="password" class="senha" name="senha" placeholder="senha" required="true"/>
                            </div>
                        </div>
                        <div class="form-group row mb-0">
                            <div class="col-md-8 offset-md-5">
                                <g:submitButton name="register" class="salvar" value="Register" />
                            </div>
                            <g:if test="${flash.message}">
                            <div class="col-md-8 offset-md-4">
                                <div class="text-danger font-weight-bold">
                                    <div class="message" role="status">${flash.message}</div>
                                </div>
                            </div>
                            </g:if>
                            <div class="col-md-8 offset-md-4">
                                <g:link class="login" action="login">Prosseguir para tela de login</g:link>
                            </div>
                        </div>
                    </g:form>
                </div>
            </div>
        </div>
    </div>
</div>
