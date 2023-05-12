package co.edu.uniandes.vinylsg12.common.api.mock

import co.edu.uniandes.vinylsg12.common.api.interfaces.CollectorService
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CollectorServiceTests {
    var sut: CollectorService? = null

    @Before
    fun setUp() {
        sut = MockCollectorService()
    }

    @Test
    fun collections_AreNotEmpty() {
        Assert.assertNotNull(sut)
        val safeSut = requireNotNull(sut) { Assert.fail("sut cannot be null") }
        safeSut.collectors(
            onComplete = { collectors ->
                Assert.assertFalse(collectors.isEmpty())
            },
            onError = { error ->
                Assert.assertNull(error)
            }
        )
    }

    @Test
    fun collector_isNotEmpty() {
        val safeSut = requireNotNull(sut) { Assert.fail("sut cannot be null") }
        safeSut.collector(1,
            onComplete = { album ->
                Assert.assertNotNull(album)
            },
            onError = { error ->
                Assert.assertNotNull(error)
            }
        )
    }


    @Test
    fun saveCollector_responseIsCorrect() {
        val safeSut = requireNotNull(sut) { Assert.fail("sut cannot be null") }
        safeSut.create("name", "3103000088", "john@example.com",
            onComplete = { collector ->
                Assert.assertNotNull(collector)
            },
            onError = { error ->
                Assert.assertNotNull(error)
            }
        )
    }
}