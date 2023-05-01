package co.edu.uniandes.vinylsg12.common.api.mock

import co.edu.uniandes.vinylsg12.common.api.interfaces.AlbumService
import co.edu.uniandes.vinylsg12.common.api.models.Album
import com.google.gson.Gson
import java.io.InputStreamReader

class MockAlbumService(): AlbumService{
    override fun getAlbums(
        onComplete: (resp: List<Album>) -> Unit,
        onError: (error: Exception) -> Unit
    ) {
        try {
            val inputStream = javaClass.classLoader?.getResourceAsStream("/json/getAlbums.json")
//            val inputStream = javaClass.getResourceAsStream("/json/getAlbums.json")
            val jsonString = inputStream!!.bufferedReader().use { it.readText() }
            val albums = Gson().fromJson(jsonString, Array<Album>::class.java).toList()
            onComplete(albums)
        } catch (e: Exception) {
            onError(e)
        }
    }

    private fun loadJsonFromAsset(fileName: String): String {
        return this::class.java.getResourceAsStream(fileName)?.use {
            InputStreamReader(it).use { reader ->
                reader.readText()
            }
        } ?: throw IllegalArgumentException("File not found: $fileName")
    }
}