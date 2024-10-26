import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.example.agenda.api.HttpClient
import com.example.agenda.api.parseActivities
import com.example.agenda.ui.components.Agenda
import com.example.agenda.ui.components.ActivityCard
import kotlinx.coroutines.launch

@Composable
fun MainScreen(navController: NavController) {
    var list by remember {
        mutableStateOf(mutableListOf<Agenda>())
    }
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        val httpClient = HttpClient()
        coroutineScope.launch {
            val result = httpClient.get("/agenda")
            list = (result?.let { parseActivities(it) } ?: intArrayOf()) as MutableList<Agenda>
            Log.d("data", list.toString())
        }
    }
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(list) { activity ->
            ActivityCard(agenda = activity,navController=navController)
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {


    }
}