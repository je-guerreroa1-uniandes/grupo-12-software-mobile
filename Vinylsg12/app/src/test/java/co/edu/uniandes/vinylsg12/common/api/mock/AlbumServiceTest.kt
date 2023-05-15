package co.edu.uniandes.vinylsg12.common.api.mock

import android.content.Context
import android.os.Bundle
import androidx.test.platform.app.InstrumentationRegistry
import co.edu.uniandes.vinylsg12.common.api.interfaces.AlbumService
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class AlbumServiceTest {
    var sut: AlbumService? = null
    private lateinit var context: Context

    @Before
    fun setUp() {
        sut = MockAlbumService()
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

//    @Test
//    fun getAlbums_isNotEmpty() {
//        assertNotNull(sut)
//        sut!!.getAlbums(
//            onComplete = { albums ->
//                assertFalse(albums.isEmpty())
//            },
//            onError = { error ->
//                assertNull(error)
//            }
//        )
//    }
}