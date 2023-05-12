package co.edu.uniandes.vinylsg12.common.api.mock

import co.edu.uniandes.vinylsg12.common.api.interfaces.AlbumService
import co.edu.uniandes.vinylsg12.common.api.models.Album
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MockAlbumService : AlbumService {
    private val albumsJson = """
        [
    {
        "id": 100,
        "name": "Buscando América",
        "cover": "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
        "releaseDate": "1984-08-01T00:00:00.000Z",
        "description": "Buscando América es el primer álbum de la banda de Rubén Blades y Seis del Solar lanzado en 1984. La producción, bajo el sello Elektra, fusiona diferentes ritmos musicales tales como la salsa, reggae, rock, y el jazz latino. El disco fue grabado en Eurosound Studios en Nueva York entre mayo y agosto de 1983.",
        "genre": "Salsa",
        "recordLabel": "Elektra",
        "tracks": [
            {
                "id": 100,
                "name": "Decisiones",
                "duration": "5:05"
            },
            {
                "id": 101,
                "name": "Desapariciones",
                "duration": "6:29"
            }
        ],
        "performers": [
            {
                "id": 100,
                "name": "Rubén Blades Bellido de Luna",
                "image": "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bb/Ruben_Blades_by_Gage_Skidmore.jpg/800px-Ruben_Blades_by_Gage_Skidmore.jpg",
                "description": "Es un cantante, compositor, músico, actor, abogado, político y activista panameño. Ha desarrollado gran parte de su carrera artística en la ciudad de Nueva York.",
                "birthDate": "1948-07-16T00:00:00.000Z"
            }
        ],
        "comments": [
            {
                "id": 100,
                "description": "The most relevant album of Ruben Blades",
                "rating": 5
            }
        ]
    },
    {
        "id": 101,
        "name": "Poeta del pueblo",
        "cover": "https://cdn.shopify.com/s/files/1/0275/3095/products/image_4931268b-7acf-4702-9c55-b2b3a03ed999_1024x1024.jpg",
        "releaseDate": "1984-08-01T00:00:00.000Z",
        "description": "Recopilación de 27 composiciones del cosmos Blades que los bailadores y melómanos han hecho suyas en estos 40 años de presencia de los ritmos y concordias afrocaribeños en múltiples escenarios internacionales. Grabaciones de Blades para la Fania con las orquestas de Pete Rodríguez, Ray Barreto, Fania All Stars y, sobre todo, los grandes éxitos con la Banda de Willie Colón",
        "genre": "Salsa",
        "recordLabel": "Elektra",
        "tracks": [],
        "performers": [
            {
                "id": 100,
                "name": "Rubén Blades Bellido de Luna",
                "image": "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bb/Ruben_Blades_by_Gage_Skidmore.jpg/800px-Ruben_Blades_by_Gage_Skidmore.jpg",
                "description": "Es un cantante, compositor, músico, actor, abogado, político y activista panameño. Ha desarrollado gran parte de su carrera artística en la ciudad de Nueva York.",
                "birthDate": "1948-07-16T00:00:00.000Z"
            }
        ],
        "comments": []
    },
    {
        "id": 102,
        "name": "A Night at the Opera",
        "cover": "https://upload.wikimedia.org/wikipedia/en/4/4d/Queen_A_Night_At_The_Opera.png",
        "releaseDate": "1975-11-21T00:00:00.000Z",
        "description": "Es el cuarto álbum de estudio de la banda británica de rock Queen, publicado originalmente en 1975. Coproducido por Roy Thomas Baker y Queen, A Night at the Opera fue, en el tiempo de su lanzamiento, la producción más cara realizada.1​ Un éxito comercial, el álbum fue votado por el público y citado por publicaciones musicales como uno de los mejores trabajos de Queen y de la historia del rock.",
        "genre": "Rock",
        "recordLabel": "EMI",
        "tracks": [],
        "performers": [
            {
                "id": 101,
                "name": "Queen",
                "image": "https://pm1.narvii.com/6724/a8b29909071e9d08517b40c748b6689649372852v2_hq.jpg",
                "description": "Queen es una banda británica de rock formada en 1970 en Londres por el cantante Freddie Mercury, el guitarrista Brian May, el baterista Roger Taylor y el bajista John Deacon. Si bien el grupo ha presentado bajas de dos de sus miembros (Mercury, fallecido en 1991, y Deacon, retirado en 1997), los integrantes restantes, May y Taylor, continúan trabajando bajo el nombre Queen, por lo que la banda aún se considera activa.",
                "creationDate": "1970-01-01T00:00:00.000Z"
            }
        ],
        "comments": [
            {
                "id": 101,
                "description": "I love this album of Queen",
                "rating": 5
            }
        ]
    },
    {
        "id": 103,
        "name": "A Day at the Races",
        "cover": "https://www.udiscovermusic.com/wp-content/uploads/2019/11/a-day-at-the-races.jpg",
        "releaseDate": "1976-12-10T00:00:00.000Z",
        "description": "El álbum fue grabado en los Estudios Sarm West, The Manor and Wessex en Inglaterra y con el ingeniero Mike Stone. El título del álbum es una referencia directa al anterior, A Night at the Opera. Ambos álbumes están titulados como películas de los hermanos Marx.",
        "genre": "Rock",
        "recordLabel": "EMI",
        "tracks": [],
        "performers": [
            {
                "id": 101,
                "name": "Queen",
                "image": "https://pm1.narvii.com/6724/a8b29909071e9d08517b40c748b6689649372852v2_hq.jpg",
                "description": "Queen es una banda británica de rock formada en 1970 en Londres por el cantante Freddie Mercury, el guitarrista Brian May, el baterista Roger Taylor y el bajista John Deacon. Si bien el grupo ha presentado bajas de dos de sus miembros (Mercury, fallecido en 1991, y Deacon, retirado en 1997), los integrantes restantes, May y Taylor, continúan trabajando bajo el nombre Queen, por lo que la banda aún se considera activa.",
                "creationDate": "1970-01-01T00:00:00.000Z"
            }
        ],
        "comments": []
    }
]
    """.trimIndent()

    private val albumJson = """
        {
        "id": 103,
        "name": "A Day at the Races",
        "cover": "https://www.udiscovermusic.com/wp-content/uploads/2019/11/a-day-at-the-races.jpg",
        "releaseDate": "1976-12-10T00:00:00.000Z",
        "description": "El álbum fue grabado en los Estudios Sarm West, The Manor and Wessex en Inglaterra y con el ingeniero Mike Stone. El título del álbum es una referencia directa al anterior, A Night at the Opera. Ambos álbumes están titulados como películas de los hermanos Marx.",
        "genre": "Rock",
        "recordLabel": "EMI",
        "tracks": [],
        "performers": [
            {
                "id": 101,
                "name": "Queen",
                "image": "https://pm1.narvii.com/6724/a8b29909071e9d08517b40c748b6689649372852v2_hq.jpg",
                "description": "Queen es una banda británica de rock formada en 1970 en Londres por el cantante Freddie Mercury, el guitarrista Brian May, el baterista Roger Taylor y el bajista John Deacon. Si bien el grupo ha presentado bajas de dos de sus miembros (Mercury, fallecido en 1991, y Deacon, retirado en 1997), los integrantes restantes, May y Taylor, continúan trabajando bajo el nombre Queen, por lo que la banda aún se considera activa.",
                "creationDate": "1970-01-01T00:00:00.000Z"
            }
        ],
        "comments": []
    }
        """.trimIndent()

    override fun albums(
        onComplete: (resp: List<Album>) -> Unit,
        onError: (error: Exception) -> Unit
    ) {
        try {
            val albums = Gson().fromJson(albumsJson, Array<Album>::class.java).toList()
            onComplete(albums)
        } catch (e: Exception) {
            onError(e)
        }
    }

    override fun album(
        id: Int,
        onComplete: (resp: Album?) -> Unit,
        onError: (error: Exception) -> Unit
    ) {
        try {
            val albumType = object : TypeToken<Album>() {}.type
            val album = Gson().fromJson<Album>(albumJson, albumType)
            onComplete(album)
        } catch (e: Exception) {
            onError(e)
        }
    }
}