package kr.ac.kumoh.ce.prof01.jet18database

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch
import org.json.JSONArray

data class Song(
    val id: Int,
    val title: String,
    val singer: String,
)
class SongViewModel : ViewModel() {
    private val supabase = createSupabaseClient(
        supabaseUrl = SupabaseConnection.URL,
        supabaseKey = SupabaseConnection.KEY,
    ) {
        install(Postgrest)
    }

    private val _songs = mutableStateListOf<Song>()
    val songs: List<Song>
        get() = _songs

    init {
        viewModelScope.launch {
            val response = supabase.from("song").select()
            //Log.i("ViewModel", response.data)
            val jsonArray = JSONArray(response.data)

            _songs.clear()

            for (i in 0 until jsonArray.length()) {
                val song = jsonArray.getJSONObject(i)
                _songs.add(
                    Song(
                        song.getInt("id"),
                        song.getString("title"),
                        song.getString("singer"),
                    )
                )
            }
        }
    }
}