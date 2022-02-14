package gestao_vendas

class RoleInterceptor {
    public RoleInterceptor(){
        matchAll().excludes(controller: 'auth')
                  .excludes(controller:'auth', action: 'acessar')
                  .excludes(controller: 'auth', action: 'encerrar')
    }
    boolean before() { 
        // if the user has not been authenticated,
        // redirect to authenticate the user...
        if(!session.usuario) {
            // log a message
            log.debug 'Redirecting to login page'
            redirect controller: 'auth', action: 'login'
            return false
        }else{
            true
        }
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}