import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.agenda.api.HttpClient
import com.example.agenda.api.parseActivities
import com.example.agenda.api.parseActivity
import com.example.agenda.ui.components.Agenda
import com.example.agenda.ui.theme.Pink40
import com.example.agenda.ui.theme.Purple40
import com.example.agenda.ui.theme.Purple80
import kotlinx.coroutines.launch

@Composable
fun ViewAgenda(id: String?,navController: NavController) {
    var agenda by remember {
        mutableStateOf<Agenda?>(null)
    }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        val httpClient = HttpClient()
        coroutineScope.launch {
            val result = httpClient.get("/agenda/${id}")
            agenda = result?.let { parseActivity(it) }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(onClick = {                navController.navigate(route = Screen.Edit.route + "?id=${id}")
            }) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Editar",
                    tint = MaterialTheme.colors.primary
                )
            }
            IconButton(onClick = {
                val httpClient = HttpClient()
                coroutineScope.launch {
                    httpClient.delete("/agenda/${id}")
                    navController.navigate(route = Screen.Main.route)
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar",
                    tint = Color.Red
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            elevation = 4.dp
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Fecha:",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold,
                    color = Purple40
                )
                agenda?.let {
                    Text(
                        text = it.date,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Asunto:",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold,
                    color = Purple40
                )
                agenda?.let {
                    Text(
                        text = it.subject,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Actividad:",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold,
                    color = Purple40
                )
                agenda?.let {
                    Text(
                        text = it.activity,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }

    }
}
