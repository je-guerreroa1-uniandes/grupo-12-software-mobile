package co.edu.uniandes.vinylsg12.common.api.interfaces

import co.edu.uniandes.vinylsg12.common.api.models.Band

interface BandService {
    fun bands(onComplete: (resp: List<Band>)->Unit, onError: (error: Exception)->Unit)
}