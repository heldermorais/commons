package app.only.springsec

class BootStrap {

    SpsecdbService spsecdbService
    def init = { servletContext ->
        spsecdbService.execute()
    }
    def destroy = {
    }
}
