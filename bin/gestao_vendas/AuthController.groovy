package gestao_vendas

import org.springframework.dao.DataIntegrityViolationException
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class AuthController {

    AuthService authService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        verificarSession()
    }

    def login(){
        flash.message = message(code: '')
        session.usuario = false
        verificarSession()
    }

    def register(){
        flash.message = message(code: '')
        if(session.usuario == false){
            [usuario: new Usuario(params)] 
            render(view:"register")
        }else{
            redirect(uri: '/')
        }
    }

    def encerrar(){
        session.usuario = false
        redirect(view:'login')
    }
    // verifica se existe um usuário logado no sistema
    def verificarSession(){
        if(session.usuario == false || session == null){ 
            render(view:"login")
        }else{
            session.usuario = true
            redirect(uri: '/')
        }
    }

    def acessar(){
        def usuario = params.usuario
        def senha = params.senha

        if (usuario != null) {
            Usuario usuarioInstance = Usuario.findByUsuario(usuario);
            if (usuarioInstance != null) {
                if (usuarioInstance.senha == senha) {
                    session.usuario = usuarioInstance
                    redirect(uri: '/')
                } else {
                    flash.message = message(code: 'senha incorreta')
                }
            }
        }
        if(!Usuario.findByUsuario(usuario)){
            flash.message = message(code: 'usuario inexistente')
        }
        render(view:"login")
    }

    def salvar(){ // Usuario usuarioInstance
        def usuario = new Usuario(params)
        if(!usuario.save(flush: true)){
            render(view:"register", model: [usuario: usuario])
            if(Usuario.findByUsuario(params.usuario)){
                flash.message = message(code: 'usuario ja cadastrado, tente novamente')
            }
            if(params.nome.size()>255){
                flash.message = message(code: 'Nome pode ter no maximo 255 caracteres\n')
                if(params.usuario.size()>50 && params.senha.size()>50){
                    flash.message = message(code: 'Nome pode ter no maximo 255 caracteres,\nUsuario pode ter no maximo 50 caracteres,\nSenha pode ter no maximo 50 caracteres')
                }
                if(params.usuario.size()>50){
                    flash.message = message(code: 'Nome pode ter no maximo 255 caracteres,\nUsuario pode ter no maximo 50 caracteres')
                }
                if(params.senha.size()>50){
                    flash.message = message(code: 'Nome pode ter no maximo 255 caracteres,\nSenha pode ter no maximo 50 caracteres')
                }
            }
            if(params.usuario.size()>50){
                flash.message = message(code: 'Usuario pode ter no maximo 50 caracteres\n')
                if(params.nome.size()>255 && params.senha.size()>50){
                    flash.message = message(code: 'Nome pode ter no maximo 255 caracteres,\nUsuario pode ter no maximo 50 caracteres,\nSenha pode ter no maximo 50 caracteres')
                }
                if(params.nome.size()>50){
                    flash.message = message(code: 'Nome pode ter no maximo 50 caracteres,\nUsuario pode ter no maximo 50 caracteres')
                }
                if(params.senha.size()>50){
                    flash.message = message(code: 'Usuario pode ter no maximo 50 caracteres,\nSenha pode ter no maximo 50 caracteres')
                }
            }
            if(params.senha.size()>50){
                flash.message = message(code: 'Senha pode ter no maximo 50 caracteres')
                if(params.usuario.size()>50 && params.nome.size()>255){
                    flash.message = message(code: 'Nome pode ter no maximo 255 caracteres,\nUsuario pode ter no maximo 50 caracteres,\nSenha pode ter no maximo 50 caracteres')
                }
                if(params.usuario.size()>50){
                    flash.message = message(code: 'Usuario pode ter no maximo 50 caracteres,\nSenha pode ter no maximo 50 caracteres')
                }
                if(params.nome.size()>255){
                    flash.message = message(code: 'Nome pode ter no maximo 255 caracteres,\nSenha pode ter no maximo 50 caracteres')
                }
            }
            return
        }
        render(view:'login')
        flash.message = message(code: 'usuario cadastrado com sucesso')
        // def usuario = params.usuario
        // if (usuarioInstance == null) {
        //     notFound()
        //     return
        // }
        // if(params != null){
        //     def buscarUsuario = Usuario.findByUsuario(usuario)
        //     if(buscarUsuario!=null ){
        //         render(view: 'register')
        //         flash.message = message(code: 'usuario ja cadastrado, tente novamente')
        //     }else{
        //         try {
        //             usuarioInstance.save(flush: true)
        //             
        //             render(view:'login')
                    
        //         } catch (ValidationException e) {
        //             respond usuarioInstance.errors, view:'register'
        //             return
        //         }
        //     }
        // }else {
        //     render(view: 'register')
        //     flash.message = message(code: '*preencha todos os campos')
        // }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'auth.label', default: 'Auth'), params.id])
                redirect action: "login", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
