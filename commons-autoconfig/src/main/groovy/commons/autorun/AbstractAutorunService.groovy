package commons.autorun

import groovy.util.logging.Slf4j
import org.springframework.boot.ApplicationRunner
import org.springframework.core.Ordered


/**
 * Classe que deve ser herdada, por outros 'Services', com a finalidade de implementar serviços auto-executáveis (no início da aplicação).
 * Esta classe também define constantes que auxiliam a implementação da ORDEM DE EXECUÇÃO pretendida.
 */
@Slf4j
abstract class AbstractAutorunService implements ApplicationRunner, Ordered {

    /**
     * Os serviços que executarão PRIMEIRO. Os serviços devem ACRESCENTAR sua ordem a partir deste valor no método getOrder.
     */
    protected static int LOW_LEVEL    = HIGHEST_PRECEDENCE;

    /**
     * Os serviços que executarão logo após LOW_LEVEL. Os serviços devem ACRESCENTAR sua ordem a partir deste valor no método getOrder.
     */
    protected static int MEDIUM_LEVEL = HIGHEST_PRECEDENCE + 20;

    /**
     * Os serviços que executarão logo após MEDIUM_LEVEL. Os serviços devem ACRESCENTAR sua ordem a partir deste valor no método getOrder.
     */
    protected static int HIGH_LEVEL   = HIGHEST_PRECEDENCE + 40;

    /**
     * Os serviços que executarão logo após HIGH_LEVEL. Os serviços devem ACRESCENTAR sua ordem a partir deste valor no método getOrder.
     */
    protected static int APP_LEVEL    = HIGHEST_PRECEDENCE + 50;

}

