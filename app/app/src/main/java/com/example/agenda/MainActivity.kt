package com.example.agenda

import NavigationStack
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.agenda.ui.components.Agenda
import com.example.agenda.ui.theme.AgendaTheme
import com.example.agenda.ui.theme.Pink40
import com.example.agenda.ui.theme.Purple40
import com.example.agenda.ui.theme.Purple80


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldMain()
        }
    }
}

@Composable
fun FloatButton(navController: NavHostController) {
        FloatingActionButton(
            containerColor = Pink40,
            onClick = {
            navController.navigate("create")
        }) {
            Text(
                text = "+",
                fontWeight = FontWeight.Light,
                fontSize = 30.sp
            )
        }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldMain(){
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                colors= TopAppBarColors(containerColor = Purple40, titleContentColor = Color.White, scrolledContainerColor = Purple40, actionIconContentColor = Color.White, navigationIconContentColor = Purple40),
                title = { Text(text = "Agendas") }
            )
        },
        content = { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                color = Color.White
            ) {
                NavigationStack(navController)
            }
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.White,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(
                        onClick = {
                            navController.navigate(route = Screen.Main.route)
                        }) {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            imageVector = Icons.Default.Home,
                            contentDescription = "Editar",
                            tint = Purple40
                        )
                    }
                }
            }
        },
        floatingActionButton = { FloatButton(navController) }
    )

}


