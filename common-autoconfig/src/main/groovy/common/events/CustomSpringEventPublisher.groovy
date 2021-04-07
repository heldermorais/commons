package common.events

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher

@Slf4j
class CustomSpringEventPublisher {

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    void publishCustomEvent(final String message) {
        log.debug("Publishing custom event. ");
        CustomEvent customSpringEvent = new CustomEvent(this, message);
        applicationEventPublisher.publishEvent(customSpringEvent);
    }

}
