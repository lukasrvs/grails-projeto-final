package gestao_vendas
class VendaItem{
    Produto produto
    BigDecimal valorUnitario
    BigDecimal quantidade
    BigDecimal desconto
    BigDecimal valorTotalItem
    static constraints = {
        produto(nullable:false)
        valorUnitario(nullable:false)
        quantidade(min:0.0,nullable:false)
        desconto(min:0.0,nullable:true)
        valorTotalItem(min:0.0, nullable:false)
    }
    static mapping = {
        id generator: 'sequence', params: [sequence: 'sequence_venda_item']
    }
}