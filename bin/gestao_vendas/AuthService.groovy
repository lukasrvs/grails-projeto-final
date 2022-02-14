package gestao_vendas

import grails.gorm.services.Service

@Service(Auth)
interface AuthService {

    Auth get(Serializable id)

    List<Auth> list(Map args)

    Long count()

    void delete(Serializable id)

    Auth save(Auth auth)

}