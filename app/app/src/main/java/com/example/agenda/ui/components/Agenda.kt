package com.example.agenda.ui.components

import Screen
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.agenda.ui.theme.Pink40

data class Agenda(
    val id: Int?,
    val date: String,
    val subject: String,
    val activity: String
)


@Composable
fun ActivityCard(agenda: Agenda,navController: NavController) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .background(Pink40, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
            .clickable {
                navController.navigate(route = Screen.View.route + "?id=${agenda.id}")
            }
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("Fecha: ") }
                append(agenda.date)
            },
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("Asunto: ") }
                append(agenda.subject)
            },
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("Actividad: ") }
                append(agenda.activity)
            },
            style = MaterialTheme.typography.body1
        )
    }
}
