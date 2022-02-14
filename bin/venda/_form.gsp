

</br>
<div >
    <g:select 
        name="cliente" 
        class="form-select"
        value="${vendaInstance.cliente?.id}" 
        from="${gestao_vendas.Cliente.list()}"
        noSelection="['null': 'Selecionar cliente']" 
        optionKey="id" />
</div>
</br>
<div class="col-4">
    <label for="nome">Adicionar produto</label>
    <button 
        type="button" 
        class="btn btn-outline-primary" 
        id="novaLinha" 
        name="novaLinha"
        onClick="ajaxPost(this,'${createLink(action: 'addProduto')}','divListaProdutos', true, 1)"
        >
        +
        </button>
</div>
<div class="row">
    <div class="col-12" id="divTabela">
        <div class="m-4">
        <div id="divListaProdutos">
            <fieldset class="form">
                <g:render template="listaProdutos"/>
            </fieldset>
            </div>
        </div>
    </div>
</div>
<script>
    
</script>