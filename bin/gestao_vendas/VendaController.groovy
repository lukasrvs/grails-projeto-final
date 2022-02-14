package gestao_vendas

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON

class VendaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [vendaList: Venda.list(params), vendaTotal: Venda.count()]
    }

    def listVenda(int length, int start){
        params.max = length;
        params.offset = start;
        int iCol=0;
        String search = params.getAt("search[value]")?.toString()?.trim(), 
               orderColumn = params.getAt("order[0][column]");
        List dados = Venda.createCriteria().list(params) {
            if (search && !search.equals("")){
                or{
                    sqlRestriction("to_char(this_.valor_total, 'FM999G999G990D00') like ?", [search+"%"])
                    cliente { 
                        ilike("nome", "%"+search+"%")
                        
                    }
                }
            }
            
            if (orderColumn && params.getAt("columns["+orderColumn+"][data]")?.toString()!="")
                order(params.getAt("columns["+orderColumn+"][data]"),params.getAt("order[0][dir]"))
            else 
                order("id","desc")
        }
        def recordsTotal = Venda.count();
        def recordsFiltered = dados.totalCount;
        //CODIGO ABAIXO PERMITE A PESONALIZAÇÃO DO RETORNO
        dados = dados.collect {it -> return [
            id : it.id,
            cliente : it.cliente,
            valorTotal: formatNumber(number:it.valorTotal, format:"###,###,##0.00")
        ]}
        
        render contentType: "text/json", text: ["draw":params.draw,"recordsTotal":recordsTotal,"recordsFiltered":recordsFiltered,"data": dados ] as JSON;
    }

    def create() {
        def vendaInstance = new Venda(params)
        [vendaInstance: vendaInstance]
    }

    def save() {
        def vendaInstance = new Venda(params)
        if (!vendaInstance.save(flush: true)) {
            render(view: "create", model: [vendaInstance: vendaInstance])
            return
        }
        flash.message = message(code: 'default.created.message', args: [message(code: 'venda.label', default: 'Venda'), vendaInstance.id])
        redirect(action: "index", id: vendaInstance.id)
    }

    def edit(Long id) {
        def vendaInstance = Venda.get(id)
        if (!vendaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'venda.label', default: 'Venda'), id])
            redirect(action: "index")
            return
        }
        [vendaInstance: vendaInstance]
    }

    def update(Long id, Long version) {
        def vendaInstance = Venda.get(id)
        calcularValorTotalVenda(vendaInstance)
        if (!vendaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'venda.label', default: 'Venda'), id])
            redirect(action: "index")
            return
        }
        if (version != null) {
            if (vendaInstance.version > version) {
                venda.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'venda.label', default: 'Venda')] as Object[],
                          "Another user has updated this Venda while you were editing")
                render(view: "edit", model: [vendaInstance: vendaInstance])
                return
            }
        }
        vendaInstance.properties = params
        if (!vendaInstance.save(flush: true)) {
            render(view: "edit", model: [vendaInstance: vendaInstance])
            return
        }
        flash.message = message(code: 'default.updated.message', args: [message(code: 'venda.label', default: 'Venda'), vendaInstance.id])
        redirect(action: "index", id: vendaInstance.id)
    }

    def delete(Long id) {
        def venda = Venda.get(id)
        if (!venda) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'venda.label', default: 'Venda'), id])
            redirect(action: "index")
            return
        }
        try {
            venda.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'venda.label', default: 'Venda'), id])
            redirect(action: "index")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'venda.label', default: 'Venda'), id])
            redirect(action: "edit", id: id)
        }
    }
    // faz a adição de uma nova linha para um novo produto na lista de vendas
    def addProduto(){
        def vendaInstance = new Venda(params)
        if(vendaInstance.itensVenda == null){
            vendaInstance.itensVenda = []
        }
        vendaInstance.itensVenda.add(new VendaItem())
        render(template:"listaProdutos" , model: [vendaInstance: vendaInstance])
    }
    // remove um produto da lista de vendas
    def removeProduto(int id) {
        def vendaInstance = new Venda(params)
        vendaInstance.itensVenda.remove(id)
        render(template:"listaProdutos" , model: [vendaInstance: vendaInstance])
    }
    // atualiza valores das demais colunas do VendaItem de acordo com o valor padrao do Produto
    def atualizarValorPadrao(){
        def vendaInstance = new Venda(params)
        if(vendaInstance.itensVenda[params.id as int].produto){
            vendaInstance.itensVenda[params.id as int].valorUnitario=vendaInstance.itensVenda[params.id as int].produto.valorPadrao;
            vendaInstance.itensVenda[params.id as int].quantidade = 1;
            vendaInstance.itensVenda[params.id as int].desconto = 0;
            vendaInstance.itensVenda[params.id as int].valorTotalItem = ((vendaInstance.itensVenda[params.id as int].valorUnitario * vendaInstance.itensVenda[params.id as int].quantidade)-vendaInstance.itensVenda[params.id as int].desconto);
        }
        calcularValorTotalVenda(vendaInstance)
        render(template:"listaProdutos", model: [vendaInstance: vendaInstance, id: params.id])
    }

    def atualizarTotalItem() {
        def vendaInstance = new Venda(params)
        if(vendaInstance.itensVenda[params.id as int].produto){
            vendaInstance.itensVenda[params.id as int].valorTotalItem = ((vendaInstance.itensVenda[params.id as int].valorUnitario * vendaInstance.itensVenda[params.id as int].quantidade)-vendaInstance.itensVenda[params.id as int].desconto);
        }
        calcularValorTotalVenda(vendaInstance)
        render(template:"listaProdutos", model: [vendaInstance: vendaInstance, id: params.id])
    }

    def calcularValorTotalVenda(Venda vendaInstance) {
        vendaInstance.valorTotal = 0
        vendaInstance.itensVenda.each{
            if(it.valorTotalItem!= null){
                vendaInstance.valorTotal +=it.valorTotalItem 
            }else{
                it.valorUnitario = 0
                it.valorTotalItem = 0
                it.quantidade = 0
                it.desconto = 0
            }
        }
    }
}