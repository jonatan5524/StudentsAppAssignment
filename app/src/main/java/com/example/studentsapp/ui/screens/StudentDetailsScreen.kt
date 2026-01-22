package com.example.studentsapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentsapp.R
import com.example.studentsapp.model.Student

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentDetailsScreen(student: Student, onEdit: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Student Details") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = android.R.drawable.ic_menu_myplaces),
                contentDescription = "Student Avatar",
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Name: ${student.name}", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "ID: ${student.id}", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Phone: ${student.phone}", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Address: ${student.address}", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = student.isChecked,
                    onCheckedChange = null,
                    enabled = false
                )
                Text(text = "Checked")
            }
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = onEdit) {
                Text("Edit")
            }
        }
    }
}
