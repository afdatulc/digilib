package com.polstat.digilib.ui.screen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.polstat.digilib.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen() {
    val image = painterResource(R.drawable.bg)
    var nameState by remember { mutableStateOf(TextFieldValue("")) }
    var nimState by remember { mutableStateOf(TextFieldValue("")) }
    var kelasState by remember { mutableStateOf(TextFieldValue("")) }
    var emailState by remember { mutableStateOf(TextFieldValue("")) }
    var passwordState by remember {
        mutableStateOf(TextFieldValue("")) }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Gambar sebagai background yang mengisi seluruh layar
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Registrasi Akun",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            TextField(
                value = nameState,
                onValueChange = { nameState = it },
                label = { Text("Nama") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1F))
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = nimState,
                onValueChange = { nimState = it },
                label = { Text("NIM") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1F))
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = kelasState,
                onValueChange = { kelasState = it },
                label = { Text("Kelas") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1F))
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = emailState,
                onValueChange = { emailState = it },
                label = { Text("Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1F))
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = passwordState,
                onValueChange = { passwordState = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { /* TODO: Implement registration functionality */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Register")
            }
        }
    }
}
@Preview
@Composable
fun PreviewRegistScreen() {
    RegisterScreen()
}