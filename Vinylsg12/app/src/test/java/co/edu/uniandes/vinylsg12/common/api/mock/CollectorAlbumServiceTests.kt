package co.edu.uniandes.vinylsg12.common.api.mock

import co.edu.uniandes.vinylsg12.common.api.interfaces.CollectorAlbumService
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CollectorAlbumServiceTests {

    var sut: CollectorAlbumService? = null

    @Before
    fun setUp() {
        sut = MockCollectorAlbumService()
    }

    @Test
    fun collectorAlbums_AreNotEmpty() {
        Assert.assertNotNull(sut)
        val safeSut = requireNotNull(sut) { Assert.fail("sut cannot be null") }
        safeSut.albums(100,
            onComplete = { albums ->
                Assert.assertFalse(albums.isEmpty())
            },
            onError = { error ->
                Assert.assertNull(error)
            }
        )
    }

    @Test
    fun collectorAlbum_isNotEmpty() {
        val safeSut = requireNotNull(sut) { Assert.fail("sut cannot be null") }
        safeSut.saveAlbum(1, 100,
            onComplete = { album ->
                Assert.assertNotNull(album)
            },
            onError = { error ->
                Assert.assertNotNull(error)
            }
        )
    }
}