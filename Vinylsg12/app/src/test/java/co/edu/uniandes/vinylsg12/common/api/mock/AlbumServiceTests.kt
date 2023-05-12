package co.edu.uniandes.vinylsg12.common.api.mock

import co.edu.uniandes.vinylsg12.common.api.interfaces.AlbumService
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class AlbumServiceTests {
    var sut: AlbumService? = null

    @Before
    fun setUp() {
        sut = MockAlbumService()
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun getAlbums_isNotEmpty() {
        assertNotNull(sut)
        val safeSut = requireNotNull(sut) { fail("sut cannot be null") }
        safeSut.albums(
            onComplete = { albums ->
                assertFalse(albums.isEmpty())
            },
            onError = { error ->
                assertNull(error)
            }
        )
    }

    @Test
    fun getAlbum_isNotEmpty() {
        val safeSut = requireNotNull(sut) { fail("sut cannot be null") }
        safeSut.album(1,
            onComplete = { album ->
                assertNotNull(album)
            },
            onError = { error ->
                assertNotNull(error)
            }
        )
    }
}