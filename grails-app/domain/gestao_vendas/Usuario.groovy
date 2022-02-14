package gestao_vendas
class Usuario{
    String nome
    String usuario
    String senha
    static constraints = {
        nome(nullable: false, blank: false, maxSize: 255)
        senha(nullable: false, blank: false, maxSize: 50, password: true)
        usuario(nullable: false, blank: false, maxSize: 50, unique: true)
    }
    @Override
    public String toString(){
        return usuario;
    }
}