package co.edu.uniandes.vinylsg12.common.api.mock

import co.edu.uniandes.vinylsg12.common.api.interfaces.BandService
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class BandServiceTests {
    var sut: BandService? = null

    @Before
    fun setup() {
        sut = MockBandService()
    }

    @Test
    fun  bands_notEmpty() {
        Assert.assertNotNull(sut)
        val safeSut = requireNotNull(sut) { Assert.fail("sut cannot be null") }
        safeSut.bands(
            onComplete = { bands ->
                Assert.assertFalse(bands.isEmpty())
            },
            onError = { error ->
                Assert.assertNull(error)
            }
        )
    }
}