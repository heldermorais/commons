package commons.database.utils.dao

import grails.core.GrailsApplication
import grails.core.support.GrailsApplicationAware
import grails.gorm.transactions.Transactional
import groovy.util.logging.Slf4j
import org.springframework.core.ResolvableType


@Slf4j
trait IGenericDAO<T> implements GrailsApplicationAware {



    /**
     *
     */
    GrailsApplication grailsApplication

    /**
     *
     */
    Class<T> referencedDomainClass


    /**
     *
     * @param grailsApplication
     */
    @Override
    void setGrailsApplication(GrailsApplication grailsApplication) {

        this.grailsApplication = grailsApplication

        log.debug ( "Inferring domainClass for DAO ${this.getClass().getName()} ..." )

        ResolvableType t = ResolvableType.forClass(getClass());
        //def genClazz = t.getInterfaces() // Integer

        for (ResolvableType r in t.getInterfaces()){
            //println(r)
            def resolved = r.resolve()
            //println(resolved.getName())
            if(r.resolve().getName().equals("commons.database.utils.dao.IGenericDAO")){
                def targetClass = r.getGeneric(0).resolve()
                //println(targetClass)

                this.referencedDomainClass = grailsApplication.getDomainClass( targetClass.getName() ).clazz

                log.debug ( "FOUND domainClass for DAO ${this.referencedDomainClass.getName()} ..." )
            }
        }


    }





    /**
     *
     * @param id
     * @return
     */
    T getById(Serializable id){
        return this.referencedDomainClass.get(id)
    }



    /**
     *
     * @param args
     * @return
     */
    List<T> list(Map args){
        return this.referencedDomainClass.list(args)
    }



    /**
     *
     * @param id
     */
    void delete(Serializable id){
        T target = this.referencedDomainClass.get(id)
        if(target != null){
            target.delete()
        }
    }



    /**
     *
     * @param target
     * @return
     */
    T save(T target){

        if(target.getClass().isAssignableFrom(this.referencedDomainClass)){
            return target.save()
        }else{
            throw new RuntimeException("Wrong EntityType.")
        }

    }

}