package co.edu.uniandes.vinylsg12.common.api.mock

import co.edu.uniandes.vinylsg12.common.api.interfaces.CollectorAlbumService
import co.edu.uniandes.vinylsg12.common.api.models.CollectedAlbum
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MockCollectorAlbumService: CollectorAlbumService {
    private val collectorAlbumsJson = """
        [
            {
                "id": 100,
                "price": 35,
                "status": "Active",
                "album": {
                    "id": 100,
                    "name": "Buscando América",
                    "cover": "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
                    "releaseDate": "1984-08-01T00:00:00.000Z",
                    "description": "Buscando América es el primer álbum de la banda de Rubén Blades y Seis del Solar lanzado en 1984. La producción, bajo el sello Elektra, fusiona diferentes ritmos musicales tales como la salsa, reggae, rock, y el jazz latino. El disco fue grabado en Eurosound Studios en Nueva York entre mayo y agosto de 1983.",
                    "genre": "Salsa",
                    "recordLabel": "Elektra"
                },
                "collector": {
                    "id": 100,
                    "name": "Manolo Bellon",
                    "telephone": "3502457896",
                    "email": "manollo@caracol.com.co"
                }
            }
        ]
    """.trimIndent()

    private val collectedAlbumJson = """
        {
          "price": 25000,
          "status": "Active",
          "album": {
              "id": 7,
              "name": "Buscando América",
             "cover": "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
              "releaseDate": "1984-08-01T05:00:00.000Z",
              "description": "Buscando América es el primer álbum de la banda de Rubén Blades y Seis del Solar lanzado en 1984. La producción, bajo el sello Elektra, fusiona diferentes ritmos musicales tales como la salsa, reggae, rock, y el jazz latino. El disco fue grabado en Eurosound Studios en Nueva York entre mayo y agosto de 1983.",
              "genre": "Salsa",
              "recordLabel": "Elektra"
          },
          "collector": {
              "id": 3,
              "name": "Jaime Andrés Monsalve",
              "telephone": "3102178976",
              "email": "j.monsalve@gmail.com"
          },
            "id": 1
        }
    """.trimIndent()

    override fun albums(
        collectorId: Int,
        onComplete: (resp: List<CollectedAlbum>) -> Unit,
        onError: (error: Exception) -> Unit
    ) {
        try {
            val collectedAlbum = Gson().fromJson(collectorAlbumsJson, Array<CollectedAlbum>::class.java).toList()
            onComplete(collectedAlbum)
        } catch (e: Exception) {
            onError(e)
        }
    }

    override fun add(
        albumId: Int,
        collectorId: Int,
        price: Int,
        status: String,
        onComplete: (resp: CollectedAlbum?) -> Unit,
        onError: (error: Exception) -> Unit
    ) {
        try {
            val collectedAlbumType = object : TypeToken<CollectedAlbum>() {}.type
            val collectedAlbum = Gson().fromJson<CollectedAlbum>(collectedAlbumJson, collectedAlbumType)
            onComplete(collectedAlbum)
        } catch (e: Exception) {
            onError(e)
        }
    }
}