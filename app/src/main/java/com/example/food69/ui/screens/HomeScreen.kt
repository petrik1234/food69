package com.example.food69.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.food69.ui.components.AddFoodDialog
import com.example.food69.ui.components.FoodCard
import com.example.food69.ui.theme.*
import com.example.food69.viewmodel.FoodViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: FoodViewModel = viewModel()
) {
    var showDialog by remember { mutableStateOf(false) }
    val totalCalories by viewModel.totalCalories.collectAsState()
    val totalProteins by viewModel.totalProteins.collectAsState()
    val totalCarbs by viewModel.totalCarbs.collectAsState()
    val totalFats by viewModel.totalFats.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header с общей статистикой
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Сегодня съедено",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "$totalCalories ккал",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = Green
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    NutrientInfo("Белки", String.format("%.1f г", totalProteins), Blue)
                    NutrientInfo("Жиры", String.format("%.1f г", totalFats), Orange)
                    NutrientInfo("Углеводы", String.format("%.1f г", totalCarbs), Red)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Заголовок списка
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Продукты",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            FloatingActionButton(
                onClick = { showDialog = true },
                containerColor = Green
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Добавить продукт"
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Список продуктов
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(viewModel.foods) { food ->
                FoodCard(
                    food = food,
                    onDelete = { viewModel.removeFood(food.id) }
                )
            }
        }
    }

    // Диалог добавления продукта
    if (showDialog) {
        AddFoodDialog(
            onDismiss = { showDialog = false },
            onConfirm = { food ->
                viewModel.addFood(food)
                showDialog = false
            }
        )
    }
}

@Composable
fun NutrientInfo(
    label: String,
    value: String,
    color: androidx.compose.ui.graphics.Color
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = color
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}