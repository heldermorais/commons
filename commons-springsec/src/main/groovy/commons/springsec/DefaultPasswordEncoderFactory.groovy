package commons.springsec

import groovy.util.logging.Slf4j
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.DelegatingPasswordEncoder
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder


@Slf4j
class DefaultPasswordEncoderFactory {


    @SuppressWarnings("deprecation")
    DelegatingPasswordEncoder buildPasswordEncoder() {

        log.debug "executing  DefaultPasswordEncoderFactory.buildPasswordEncoder()..."

        String encodingId = "MD5";
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put(encodingId, new BCryptPasswordEncoder());
        encoders.put("ldap", new org.springframework.security.crypto.password.LdapShaPasswordEncoder());
        encoders.put("MD4", new org.springframework.security.crypto.password.Md4PasswordEncoder());

        //This will be the default password encoder when no 'prefix' matches
        def md5PasswordEncoder = new MessageDigestPasswordEncoder("MD5")
        encoders.put("MD5", md5PasswordEncoder);

        encoders.put("noop", org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance());
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());
        encoders.put("SHA-1", new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("SHA-1"));
        encoders.put("SHA-256", new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("SHA-256"));

        DelegatingPasswordEncoder delegatingPasswordEncoder = new DelegatingPasswordEncoder(encodingId, encoders);
        delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(md5PasswordEncoder);

        log.debug "executing  DefaultPasswordEncoderFactory.buildPasswordEncoder()...Done."
        return delegatingPasswordEncoder;
    }

}
