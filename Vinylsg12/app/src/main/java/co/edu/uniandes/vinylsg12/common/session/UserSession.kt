package co.edu.uniandes.vinylsg12.common.session

import co.edu.uniandes.vinylsg12.common.api.models.Collector

object UserSession {
    var collector: Collector? = null
    val isLogged: Boolean
        get() = collector != null
}