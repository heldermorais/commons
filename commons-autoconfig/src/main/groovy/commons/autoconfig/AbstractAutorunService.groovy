package commons.autoconfig

import groovy.util.logging.Slf4j
import org.springframework.boot.ApplicationRunner
import org.springframework.core.Ordered


@Slf4j
abstract class AbstractAutorunService implements ApplicationRunner, Ordered {

    protected static int LOW_LEVEL    = HIGHEST_PRECEDENCE;
    protected static int MEDIUM_LEVEL = HIGHEST_PRECEDENCE + 20;
    protected static int HIGH_LEVEL   = HIGHEST_PRECEDENCE + 40;

    protected static int APP_LEVEL    = HIGHEST_PRECEDENCE + 50;

}

