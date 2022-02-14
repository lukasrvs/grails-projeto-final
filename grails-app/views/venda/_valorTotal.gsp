<div class="fieldcontain ${hasErrors(bean: venda, field: 'valorTotal', 'error')}">
    <label for="valorTotal">
        <g:message code="venda.valorPadrao.label" default="Valor Total R$" />
    </label>
    <g:field name="valorTotal" value="${formatNumber(number: venda.valorTotal, format: '###,###,##0.00')}" onkeyup="mascaraNumero(this);" />
</div>