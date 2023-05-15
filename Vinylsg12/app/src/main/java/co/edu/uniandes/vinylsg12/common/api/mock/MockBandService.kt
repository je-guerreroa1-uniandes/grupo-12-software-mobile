package co.edu.uniandes.vinylsg12.common.api.mock

import co.edu.uniandes.vinylsg12.common.api.interfaces.BandService
import co.edu.uniandes.vinylsg12.common.api.models.Band
import com.google.gson.Gson

class MockBandService: BandService {
    private val bandsJson = """
        [
            {
                "id": 101,
                "name": "Queen",
                "image": "https://pm1.narvii.com/6724/a8b29909071e9d08517b40c748b6689649372852v2_hq.jpg",
                "description": "Queen es una banda británica de rock formada en 1970 en Londres por el cantante Freddie Mercury, el guitarrista Brian May, el baterista Roger Taylor y el bajista John Deacon. Si bien el grupo ha presentado bajas de dos de sus miembros (Mercury, fallecido en 1991, y Deacon, retirado en 1997), los integrantes restantes, May y Taylor, continúan trabajando bajo el nombre Queen, por lo que la banda aún se considera activa.",
                "creationDate": "1970-01-01T00:00:00.000Z",
                "albums": [
                    {
                        "id": 103,
                        "name": "A Day at the Races",
                        "cover": "https://www.udiscovermusic.com/wp-content/uploads/2019/11/a-day-at-the-races.jpg",
                        "releaseDate": "1976-12-10T00:00:00.000Z",
                        "description": "El álbum fue grabado en los Estudios Sarm West, The Manor and Wessex en Inglaterra y con el ingeniero Mike Stone. El título del álbum es una referencia directa al anterior, A Night at the Opera. Ambos álbumes están titulados como películas de los hermanos Marx.",
                        "genre": "Rock",
                        "recordLabel": "EMI"
                    },
                    {
                        "id": 102,
                        "name": "A Night at the Opera",
                        "cover": "https://upload.wikimedia.org/wikipedia/en/4/4d/Queen_A_Night_At_The_Opera.png",
                        "releaseDate": "1975-11-21T00:00:00.000Z",
                        "description": "Es el cuarto álbum de estudio de la banda británica de rock Queen, publicado originalmente en 1975. Coproducido por Roy Thomas Baker y Queen, A Night at the Opera fue, en el tiempo de su lanzamiento, la producción más cara realizada.1​ Un éxito comercial, el álbum fue votado por el público y citado por publicaciones musicales como uno de los mejores trabajos de Queen y de la historia del rock.",
                        "genre": "Rock",
                        "recordLabel": "EMI"
                    }
                ],
                "musicians": [],
                "performerPrizes": [
                    {
                        "id": 101,
                        "premiationDate": "1980-12-10T00:00:00.000Z"
                    }
                ]
            }
        ]
    """.trimIndent()

    override fun bands(
        onComplete: (resp: List<Band>) -> Unit,
        onError: (error: Exception) -> Unit
    ) {
        try {
            val bands = Gson().fromJson(bandsJson, Array<Band>::class.java).toList()
            onComplete(bands)
        } catch (error: java.lang.Exception) {
            onError(error)
        }
    }
}