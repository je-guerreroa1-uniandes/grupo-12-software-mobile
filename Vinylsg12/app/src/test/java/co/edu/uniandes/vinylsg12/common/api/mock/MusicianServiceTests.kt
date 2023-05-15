package co.edu.uniandes.vinylsg12.common.api.mock

import co.edu.uniandes.vinylsg12.common.api.interfaces.MusicianService
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MusicianServiceTests {
    private var sut: MusicianService? = null

    @Before
    fun setup() {
        sut = MockMusicianService()
    }

    @Test
    fun musicians_AreNotEmpty() {
        assertNotNull(sut)
        val safeSut = requireNotNull(sut) { fail("sut cannot be null") }
        safeSut.musicians(
            onComplete = { musicians ->
                assertFalse(musicians.isEmpty())
            },
            onError = { error ->
                assertNull(error)
            }
        )
    }
}