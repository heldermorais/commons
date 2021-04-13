import app.only.springsec.CustomUserDetailService
import app.only.springsec.SecUserPasswordEncoderListener
import app.only.springsec.SecUsuarioPasswordEncoderListener
// Place your Spring DSL code here
beans = {
    secUserPasswordEncoderListener(SecUserPasswordEncoderListener)
    //secUsuarioPasswordEncoderListener(SecUsuarioPasswordEncoderListener)
    userDetailsService(CustomUserDetailService)
}
