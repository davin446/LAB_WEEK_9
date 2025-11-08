package com.example.lab_week_9

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.lab_week_9.ui.theme.LAB_WEEK_9Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LAB_WEEK_9Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Home()
                }
            }
        }
    }
}

// Data class untuk menampung nama
data class Student(
    var name: String
)

@Composable
fun Home() {
    // Daftar Student yang bisa berubah
    val students = remember { mutableStateListOf<Student>() }

    // Menyimpan input user
    var inputText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Masukkan Nama Mahasiswa")

        // Input Field
        TextField(
            value = inputText,
            onValueChange = { inputText = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text("Student Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Tombol Submit
        Button(onClick = {
            if (inputText.isNotBlank()) {
                students.add(Student(inputText))
                inputText = "" // kosongkan field setelah submit
            }
        }) {
            Text(text = "Submit")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Menampilkan daftar siswa
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(students) { student ->
                Text(text = student.name)
            }
        }
    }
}
