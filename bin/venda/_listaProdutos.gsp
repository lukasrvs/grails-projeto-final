<div class="fieldcontain"> 
    <table id="table-itensVendas" class="table-itensVendas">
        <thead>
            <tr>
                <th>Produto</th>
                <th>Valor Padrão R$</th>
                <th>Quantidade</th>
                <th>Desconto</th>
                <th>Valor Total Item R$</th>
                <th>Remover Item</th>
            </tr>
        </thead>
        <tbody>
            <g:each status="i" in="${vendaInstance.itensVenda}" var="itemVenda">
                <tr>
                    <td>
                        <g:select 
                            name="itensVenda[${i}].produto.id" 
                            value="${itemVenda?.produto?.id}" 
                            from="${gestao_vendas.Produto.list()}" 
                            optionKey="id"
                            noSelection="['null': 'Selecionar produto']"
                            onChange="ajaxPost(this, '${createLink(action: 'atualizarValorPadrao', params: [id: i])}', 'divListaProdutos', true, 1)" required="true"/>
                    </td>
                    <td>
                        <div id="divValorPadrao${i}">
                            <g:field 
                                name="itensVenda[${i}].valorUnitario" 
                                value="${formatNumber(number: (itemVenda?.valorUnitario!=null?itemVenda?.valorUnitario: 0.0), format: '###,###,##0.00')}"
                                onkeyup="mascaraNumero(this);"
                                readonly="readonly"
                                onChange="ajaxPost(this, '${createLink(action: 'atualizarTotalItem', params:[id: i])}', 'divListaProdutos', true, 1)"/>
                        </div>
                    </td>
                    <td>
                        <div id="divQuantidade${i}">
                            <g:field 
                                name="itensVenda[${i}].quantidade" 
                                value="${formatNumber(number: (itemVenda?.quantidade!=null?itemVenda?.quantidade: 0.0), format: '###,###,##0.00')}"
                                onkeyup="mascaraNumero(this);"
                                min="1"
                                onChange="ajaxPost(this,'${createLink(action: 'atualizarTotalItem', params:[id: i])}', 'divListaProdutos', true, 1)"/>
                        </div>
                    </td>
                    <td>
                        <div id="divDesconto${i}">
                            <g:field 
                                name="itensVenda[${i}].desconto" 
                                value="${formatNumber(number: (itemVenda?.desconto!=null?itemVenda?.desconto: 0.0), format: '###,###,##0.00')}"
                                onkeyup="mascaraNumero(this);"
                                onChange="ajaxPost(this,'${createLink(action: 'atualizarTotalItem', params:[id: i])}', 'divListaProdutos', true, 1)"/>
                        </div>
                    </td>
                    <td>
                        <div id="divValorTotalItem${i}">
                            <g:field 
                                name="itensVenda[${i}].valorTotalItem" 
                                value="${formatNumber(number: (itemVenda?.valorTotalItem!=null?itemVenda?.valorTotalItem: 0.0), format: '###,###,##0.00')}"
                                onkeyup="mascaraNumero(this);"
                                id="itensVenda[${i}].valorTotalItem"
                                readonly="readonly"/>
                        </div>
                    </td>
                    <td style="text-align: center; vertical-align: middle;">
                        <button 
                            type="button" 
                            class="btn btn-outline-danger" 
                            id="excluirLinha" 
                            name="excluirLinha"
                            onClick="ajaxPost(this,'${createLink(action:  'removeProduto' , params:[id: i])}','divListaProdutos', true, 1)"
                            >x
                        </button>
                    </td>
                </tr>
            </g:each>        
        </tbody>
    </table>
    <div class="divValorTotal">
        <label for="valorTotal">Total R$: </label>
        <g:textField 
            id="valorTotal" 
            name="valorTotal" 
            value="${formatNumber(number: vendaInstance?.valorTotal, format: '###,###,##0.00')}" 
            onkeyup="mascaraNumero(this);" 
            readonly="readonly"/>
    </div>
</div>