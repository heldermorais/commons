package app.commons

import common.autoconfig.Autorun
import common.springsec.SecGroup
import common.springsec.SecRequestmap
import common.springsec.SecUser
import common.springsec.SecUserSecGroup
import common.springsec.SpringsecCommonConfigService
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService
import org.apache.commons.chain.Command
import org.apache.commons.chain.Context

@Transactional
@Autorun
class ConfigRequestmapService implements Command {

    SpringSecurityService springSecurityService
    SpringsecCommonConfigService springsecCommonConfigService
    @Override
    boolean execute(Context context) throws Exception {

        log.info("Bootstrap - BEGIN")
        springsecCommonConfigService.execute(context)

        new SecRequestmap(url: '/home/**',    configAttribute: 'ROLE_ADMIN').save(flush: true)

        SecGroup group_users = SecGroup.get(2)

        SecUser plainUser = SecUser.get(100)
        if(plainUser == null){
            plainUser = new SecUser(username: 'user1', password: 'user1')
            plainUser.save(flush: true, failOnError: true)
            SecUserSecGroup.create(plainUser, group_users)
        }

        log.info("Bootstrap - END")
        springSecurityService.clearCachedRequestmaps()

        return false
    }
}
