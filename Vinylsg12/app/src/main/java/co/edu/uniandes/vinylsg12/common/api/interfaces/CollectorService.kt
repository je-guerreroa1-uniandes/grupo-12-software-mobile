package co.edu.uniandes.vinylsg12.common.api.interfaces

import co.edu.uniandes.vinylsg12.common.api.models.Collector

interface CollectorService {

    fun collectors(onComplete:(resp:List<Collector>)->Unit, onError: (error: Exception)->Unit)
    fun collector(id: Int, onComplete:(resp: Collector?)->Unit, onError: (error: Exception)->Unit)
    fun create(name: String, telephone: String, email: String, onComplete: (resp: Collector?) -> Unit, onError: (error: Exception) -> Unit)
}