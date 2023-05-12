package co.edu.uniandes.vinylsg12.common.api.repositories

import co.edu.uniandes.vinylsg12.common.api.mock.MockAlbumService
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class AlbumRepositoryTests {
    var sut: AlbumRepository? = null

    @Before
    fun setUp() {
        val albumService = MockAlbumService()
        sut = AlbumRepository(albumService)
    }

    @Test
    fun getAlbums_isNotEmpty() {
        Assert.assertNotNull(sut)
        val safeSut = requireNotNull(sut) { Assert.fail("sut cannot be null") }
        safeSut.refreshData(
            callback = { albums ->
                Assert.assertNotNull(albums.isEmpty())
            },
            onError = { error ->
                Assert.assertNotNull(error)
            }
        )
    }
}