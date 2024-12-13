package gonzo.modern.finalproj.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import gonzo.modern.finalproj.data.UserManager
import gonzo.modern.finalproj.model.ClassWithStudents
import gonzo.modern.finalproj.ui.composables.AttendanceSheet
import gonzo.modern.finalproj.ui.composables.ClassList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    username: String,
    userManager: UserManager
) {
    var selectedClass by remember { mutableStateOf<ClassWithStudents?>(null) }
    var classes by remember { mutableStateOf(listOf<ClassWithStudents>()) }
    
    // Load classes when screen is first composed
    LaunchedEffect(Unit) {
        classes = userManager.getClassesForUser(username)
    }

    // Save classes whenever they change
    LaunchedEffect(classes) {
        userManager.saveClassesForUser(username, classes)
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(selectedClass?.className ?: "Attendance") },
                navigationIcon = {
                    if (selectedClass != null) {
                        IconButton(onClick = { selectedClass = null }) {
                            Text("←")
                        }
                    }
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            if (selectedClass == null) {
                ClassList(
                    classes = classes,
                    onClassesUpdated = { classes = it },
                    onClassSelected = { selectedClass = it }
                )
            } else {
                AttendanceSheet(
                    classWithStudents = selectedClass!!,
                    onClassUpdated = { updated -> 
                        selectedClass = updated
                        classes = classes.map { 
                            if (it.className == updated.className) updated else it 
                        }
                    },
                    onSaveAndExit = { selectedClass = null }
                )
            }
        }
    }
} 