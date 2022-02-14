<div class="fieldcontain ${hasErrors(bean: cliente, field: 'nome', 'error')}">
    <label for="nome">
        <g:message code="cliente.nome.label" default="Nome" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="nome" value="${cliente.nome}" />
</div>
<div class="fieldcontain ${hasErrors(bean: cliente, field: 'email', 'error')}">
    <label for="email">
        <g:message code="cliente.email.label" default="Email" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="email" value="${cliente.email}" />
</div>
<div class="fieldcontain ${hasErrors(bean: cliente, field: 'cpfCnpj', 'error')}">
    <label for="cpfCnpj">
        <g:message code="cliente.cpfCnpj.label" default="CPF/CNPJ" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="cpfCnpj" value="${cliente.cpfCnpj}" />
</div>