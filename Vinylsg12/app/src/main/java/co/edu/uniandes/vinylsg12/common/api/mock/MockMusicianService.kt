package co.edu.uniandes.vinylsg12.common.api.mock

import co.edu.uniandes.vinylsg12.common.api.interfaces.MusicianService
import co.edu.uniandes.vinylsg12.common.api.models.Album
import co.edu.uniandes.vinylsg12.common.api.models.Musician
import com.google.gson.Gson
import java.lang.Exception

class MockMusicianService: MusicianService{
    private val musiciansJson = """
        [
            {
                "id": 100,
                "name": "Rubén Blades Bellido de Luna",
                "image": "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bb/Ruben_Blades_by_Gage_Skidmore.jpg/800px-Ruben_Blades_by_Gage_Skidmore.jpg",
                "description": "Es un cantante, compositor, músico, actor, abogado, político y activista panameño. Ha desarrollado gran parte de su carrera artística en la ciudad de Nueva York.",
                "birthDate": "1948-07-16T00:00:00.000Z",
                "albums": [
                    {
                        "id": 101,
                        "name": "Poeta del pueblo",
                        "cover": "https://cdn.shopify.com/s/files/1/0275/3095/products/image_4931268b-7acf-4702-9c55-b2b3a03ed999_1024x1024.jpg",
                        "releaseDate": "1984-08-01T00:00:00.000Z",
                        "description": "Recopilación de 27 composiciones del cosmos Blades que los bailadores y melómanos han hecho suyas en estos 40 años de presencia de los ritmos y concordias afrocaribeños en múltiples escenarios internacionales. Grabaciones de Blades para la Fania con las orquestas de Pete Rodríguez, Ray Barreto, Fania All Stars y, sobre todo, los grandes éxitos con la Banda de Willie Colón",
                        "genre": "Salsa",
                        "recordLabel": "Elektra"
                    },
                    {
                        "id": 100,
                        "name": "Buscando América",
                        "cover": "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
                        "releaseDate": "1984-08-01T00:00:00.000Z",
                        "description": "Buscando América es el primer álbum de la banda de Rubén Blades y Seis del Solar lanzado en 1984. La producción, bajo el sello Elektra, fusiona diferentes ritmos musicales tales como la salsa, reggae, rock, y el jazz latino. El disco fue grabado en Eurosound Studios en Nueva York entre mayo y agosto de 1983.",
                        "genre": "Salsa",
                        "recordLabel": "Elektra"
                    }
                ],
                "performerPrizes": [
                    {
                        "id": 100,
                        "premiationDate": "1978-12-10T00:00:00.000Z"
                    }
                ]
            },
            {
                "id": 11,
                "name": "Jon Secada",
                "image": "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8d/Jon_Secada_Headshot.jpg/800px-Jon_Secada_Headshot.jpg",
                "description": "Juan Francisco Secada Ramírez (born October 4, 1961), better known as Jon Secada, is a Cuban-born American[1][2] singer. He has won two Grammy Awards and sold 15 million records,[3] making him one of the best-selling Latin music artists. His music fuses funk, soul music, pop, and Latin percussion.",
                "birthDate": "1961-10-10T05:00:00.000Z",
                "albums": [],
                "performerPrizes": []
            },
            {
                "id": 10,
                "name": "Rubén Blades Bellido de Luna",
                "image": "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bb/Ruben_Blades_by_Gage_Skidmore.jpg/800px-Ruben_Blades_by_Gage_Skidmore.jpg",
                "description": "político y activista panameño. Ha desarrollado gran parte de su carrera artística en la ciudad de Nueva York.",
                "birthDate": "1948-07-16T00:00:00.000Z",
                "albums": [],
                "performerPrizes": []
            },
            {
                "id": 2,
                "name": "Ana gabriel",
                "image": "https://upload.wikimedia.org/wikipedia/commons/d/db/Ana_Gabriel.jpg",
                "description": "Es un cantante, compositor, músico, actor, abogado, político y activista panameño. Ha desarrollado gran parte de su carrera artística en la ciudad de Nueva York.",
                "birthDate": "1948-07-16T05:00:00.000Z",
                "albums": [],
                "performerPrizes": []
            },
            {
                "id": 5,
                "name": "Shakira",
                "image": "https://upload.wikimedia.org/wikipedia/commons/1/1d/Shakira_for_VOGUE_in_2021_2.png",
                "description": "es una cantautora, bailarina, actriz y empresaria colombiana.",
                "birthDate": "1977-02-02T05:00:00.000Z",
                "albums": [],
                "performerPrizes": []
            },
            {
                "id": 8,
                "name": "juan perez",
                "image": "http:sasas",
                "description": "puebaaaa",
                "birthDate": "2021-03-02T12:22:33.000Z",
                "albums": [],
                "performerPrizes": []
            },
            {
                "id": 6,
                "name": "Jon Secada",
                "image": "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8d/Jon_Secada_Headshot.jpg/800px-Jon_Secada_Headshot.jpg",
                "description": "Juan Francisco Secada Ramírez (born October 4, 1961), better known as Jon Secada, is a Cuban-born American[1][2] singer. He has won two Grammy Awards and sold 15 million records,[3] making him one of the best-selling Latin music artists. His music fuses funk, soul music, pop, and Latin percussion.",
                "birthDate": "1961-10-10T05:00:00.000Z",
                "albums": [],
                "performerPrizes": []
            },
            {
                "id": 4,
                "name": "Vicente Garcia",
                "image": "https://upload.wikimedia.org/wikipedia/commons/thumb/1/14/Vicente_Garc%C3%ADa_%28Perfil%29.jpg/800px-Vicente_Garc%C3%ADa_%28Perfil%29.jpg",
                "description": "Es un compositor, músico y cantante dominicano con una sensibilidad especial hacia la música dominicana y del Caribe.",
                "birthDate": "1983-03-30T05:00:00.000Z",
                "albums": [],
                "performerPrizes": []
            },
            {
                "id": 3,
                "name": "Bad Bunny",
                "image": "https://upload.wikimedia.org/wikipedia/commons/b/b1/Bad_Bunny_2019_by_Glenn_Francis_%28cropped%29.jpg",
                "description": "Es un rapero, cantante, compositor y productor puertorriqueño.",
                "birthDate": "1994-03-10T05:00:00.000Z",
                "albums": [],
                "performerPrizes": []
            },
            {
                "id": 9,
                "name": "juan perez",
                "image": "http:sasas",
                "description": "puebaaaa",
                "birthDate": "2021-03-02T12:22:33.000Z",
                "albums": [],
                "performerPrizes": []
            },
            {
                "id": 7,
                "name": "Karol G",
                "image": "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cb/Karol_G_en_2018.jpg/800px-Karol_G_en_2018.jpg",
                "description": "Es una cantante y compositora colombiana. Se la describe predominantemente como una artista de reguetón y trap latino.",
                "birthDate": "1991-02-14T05:00:00.000Z",
                "albums": [],
                "performerPrizes": []
            }
        ]
    """.trimIndent()

    val musicianJson = """   
        {
            "id": 1,
            "name": "Rubén Blades Bellido de Luna",
            "image": "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bb/Ruben_Blades_by_Gage_Skidmore.jpg/800px-Ruben_Blades_by_Gage_Skidmore.jpg",
            "description": "Es un cantante, compositor, músico, actor, abogado, político y activista panameño. Ha desarrollado gran parte de su carrera artística en la ciudad de Nueva York.",
            "birthDate": "1948-07-16T05:00:00.000Z",
            "albums": [],
            "performerPrizes": []
        }
    """.trimIndent()

    override fun musicians(
        onComplete: (resp: List<Musician>) -> Unit,
        onError: (error: Exception) -> Unit
    ) {
        try {
            val musicians = Gson().fromJson(musiciansJson, Array<Musician>::class.java).toList()
            onComplete(musicians)
        } catch (e: Exception) {
            onError(e)
        }
    }

    override fun musician(
        id: Int,
        onComplete: (resp: Musician?) -> Unit,
        onError: (error: Exception) -> Unit
    ) {
        try {
            val musician = Gson().fromJson(musiciansJson, Musician::class.java)
            onComplete(musician)
        } catch (e: Exception) {
            onError(e)
        }
    }

}