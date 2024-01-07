package com.example.myapplication.ui.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.data.model.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoCard(
    modifier: Modifier = Modifier,
    task: Task,
    onMenuClick: () -> Unit,
    onCardClick: () -> Unit = {}
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        onClick = onCardClick,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "${task.id}",
                        style = MaterialTheme.typography.labelLarge.copy(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.W400
                        )
                    )
                    Text(
                        text = task.title,
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp
                        )
                    )
                }
                IconButton(
                    onClick = onMenuClick
                ) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = null)
                }
            }
            Text(
//                text = "Progress",
                text = task.description,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(vertical = 2.dp)
            )
            LinearProgressIndicator(progress = 0.6f)
            Row(
                modifier = modifier,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IntervalRow(
                    imageVector = Icons.Outlined.CalendarMonth,
                    date = task.startDate
                )
                IntervalRow(
                    imageVector = Icons.Outlined.Flag,
                    date = task.endDate
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoCardPreview() {
//    TodoCard()
}