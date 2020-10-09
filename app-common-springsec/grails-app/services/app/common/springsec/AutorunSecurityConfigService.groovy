package app.common.springsec

import common.autorun.Autorun
import common.autorun.GenericAutorunService
import common.security.springsec.SecRole
import common.security.springsec.SecUser
import common.security.springsec.SecUserSecRole
import grails.gorm.transactions.Transactional
import org.apache.commons.chain.Context

@Transactional
@Autorun
class AutorunSecurityConfigService extends GenericAutorunService {

    @Override
    boolean execute(Context context) throws Exception {

        SecUser user1 = new SecUser ( username: 'user1', password: 'abc123')
        user1.save(flush: true, failOnError: true)

        SecUser adminUser = new SecUser ( username: 'admin', password: 'abc123')
        adminUser.save(flush: true, failOnError: true)

        SecRole role1 = new SecRole(authority: 'ROLE_USER')
        role1.save(flush: true, failOnError: true)

        SecRole adminRole = new SecRole(authority: 'ROLE_ADMIN')
        adminRole.save(flush: true, failOnError: true)

        SecUserSecRole.create(user1,role1,true)

        SecUserSecRole.create(adminUser,role1,true)
        SecUserSecRole.create(adminUser,adminRole,true)

        return false
    }

    @Override
    int getOrder() {
        return 0
    }
}
