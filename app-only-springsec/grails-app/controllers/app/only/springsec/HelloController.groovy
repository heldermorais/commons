package app.only.springsec

import org.springframework.security.crypto.password.DelegatingPasswordEncoder
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

class HelloController {

    def index() {
        String senha = 'abc123'
        PasswordEncoder md5 = createDelegatingPasswordEncoder()
        String senhaEncoded = md5.encode(senha)

        Boolean matches = md5.matches(senha,"e99a18c428cb38d5f260853678922e03")

        render "senha: (${matches}) >> [${senha}] => [${senhaEncoded}] !"
    }

    static PasswordEncoder createDelegatingPasswordEncoder() {
        String encodingId = "MD5";
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        //encoders.put(encodingId, new BCryptPasswordEncoder());
        //encoders.put("ldap", new org.springframework.security.crypto.password.LdapShaPasswordEncoder());
        //encoders.put("MD4", new org.springframework.security.crypto.password.Md4PasswordEncoder());

        PasswordEncoder md5Encoder = new MessageDigestPasswordEncoder("MD5")
        //md5Encoder.setencodeHashAsBase64 = true
        encoders.put("MD5", md5Encoder);
        //encoders.put("noop", org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance());
        //encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        //encoders.put("scrypt", new SCryptPasswordEncoder());
        //encoders.put("SHA-1", new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("SHA-1"));
        //encoders.put("SHA-256", new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("SHA-256"));

        DelegatingPasswordEncoder delegatingPasswordEncoder = new DelegatingPasswordEncoder(encodingId, encoders);
        delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(md5Encoder);
        return delegatingPasswordEncoder;
    }

}
