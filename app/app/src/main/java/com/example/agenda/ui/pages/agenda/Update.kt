package com.example.agenda.ui.pages.agenda
import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.agenda.api.HttpClient
import com.example.agenda.api.parseActivity
import com.example.agenda.ui.components.Agenda
import com.example.agenda.ui.theme.Purple40
import com.google.gson.Gson
import kotlinx.coroutines.launch
import java.util.Calendar


@Composable
fun UpdateAgenda(id: String?,navController: NavController) {

    var date by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf(TextFieldValue("")) }
    var activityDescription by remember { mutableStateOf(TextFieldValue("")) }

    var agenda by remember {
        mutableStateOf<Agenda?>(null)
    }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        val httpClient = HttpClient()
        coroutineScope.launch {
            val result = httpClient.get("/agenda/${id}")
            agenda = result?.let { parseActivity(it) }
                date= agenda!!.date
                subject= TextFieldValue(agenda!!.subject)
                activityDescription= TextFieldValue(agenda!!.activity)

        }
    }


    val calendar = Calendar.getInstance()
    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            date = String.format("%02d/%02d/%d", dayOfMonth, month + 1, year)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para abrir el calendario de selección de fecha
        OutlinedButton(
            onClick = { datePickerDialog.show() },
            modifier = Modifier.fillMaxWidth().height(60.dp),
            shape = RoundedCornerShape(5.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = if (date.isNotEmpty()) " $date" else "Fecha",
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = subject,
            onValueChange = { subject = it },
            label = { Text(
                text="Asunto",
                color = Color.Black,
                fontWeight = FontWeight.Normal,
            ) },
            modifier = Modifier.fillMaxWidth().height(60.dp),
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = activityDescription,
            onValueChange = { activityDescription = it },
            label = {  Text(
                text="Actividad",
                color = Color.Black,
                fontWeight = FontWeight.Normal,
            ) },
            modifier = Modifier.fillMaxWidth().height(60.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val newActivity = Agenda(
                    id = agenda?.id,
                    date = date,
                    subject = subject.text,
                    activity = activityDescription.text
                )
                val gson = Gson()
                val json = gson.toJson(newActivity)
                val httpClient = HttpClient()
                coroutineScope.launch {
                     httpClient.put("/agenda",json)
                    navController.navigate(route = Screen.Main.route)
                }
            },
            shape = RoundedCornerShape(5.dp),
            colors = ButtonColors(containerColor = Purple40, contentColor = Color.White, disabledContentColor = Color.White, disabledContainerColor = Purple40 ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }
    }
}


