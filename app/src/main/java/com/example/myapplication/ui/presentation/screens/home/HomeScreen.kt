package com.example.myapplication.ui.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.presentation.components.TodoCard
import com.example.myapplication.ui.presentation.navigation.NavigationDestination
import com.example.myapplication.ui.theme.Poppins


object HomeDestination : NavigationDestination {
    override val route: String = "home"
    override val titleRes: Int = R.string.home_title
}


val Progress = listOf("In Progress", "Todo", "Complete")

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    onFabClick: () -> Unit,
) {
    val uiState by viewModel.homeUiState.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onFabClick
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(R.string.add_task)
                )
            }
        }
    ) { _ ->
        Column(
            modifier = modifier.padding(8.dp),
        ) {


            Text(
                text = "Your tasks",
                fontFamily = Poppins,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
            Row(
                modifier = Modifier.padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Progress.forEachIndexed { index, value ->
                    Button(
                        onClick = {}
                    ) {
                        Text(text = value)
                    }
                }
            }
            OutlinedTextField(
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = null)
                },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                placeholder = {
                    Text(
                        text = "Search...",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontStyle = FontStyle.Italic,
                            color = Color.Gray
                        )
                    )
                },
                value = uiState.searchText.value,
                onValueChange = {
                    uiState.searchText.value = it
                    if (it.length >= 2) {
                        viewModel.filterTask()
                    }
                    if (it.isEmpty()) viewModel.restoreInitialList()
                }
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                uiState.filteredTasklist.forEachIndexed { index, task ->
                    TodoCard(
                        task = task,
                        onMenuClick = {
                            viewModel.deleteTask(task)
                        }
                    )
                }
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview() {
////    HomeScreen()
//}