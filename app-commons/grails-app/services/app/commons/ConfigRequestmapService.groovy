package app.commons

import common.autorun.Autorun
import common.autorun.GenericAutorunService
import common.springsec.SecGroup
import common.springsec.SecRequestmap
import common.springsec.SecUser
import common.springsec.SecUserSecGroup
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService
import groovy.util.logging.Slf4j
import org.apache.commons.chain.Context
import org.springframework.core.Ordered

@Transactional
@Slf4j
@Autorun
class ConfigRequestmapService extends GenericAutorunService {

    SpringSecurityService springSecurityService
//    SpringsecCommonConfigService springsecCommonConfigService

    @Override
    boolean execute(Context context) throws Exception {

        log.info("on App - ConfigRequestmapService - BEGIN")
//        springsecCommonConfigService.execute(context)

        new SecRequestmap(url: '/home/**',    configAttribute: 'ROLE_ADMIN').save(flush: true)

        SecGroup group_users = SecGroup.get(2)

        SecUser plainUser = SecUser.get(100)
        if(plainUser == null){
            plainUser = new SecUser(username: 'user1', password: 'user1')
            plainUser.save(flush: true, failOnError: true)
            SecUserSecGroup.create(plainUser, group_users)
        }

        log.info("on App - ConfigRequestmapService - END")
        springSecurityService.clearCachedRequestmaps()

        return false
    }

    @Override
    int getOrder() {
        return Ordered.LOWEST_PRECEDENCE + 20000
    }
}
