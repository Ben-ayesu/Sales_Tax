package salestaxcalculator.example.bcsalestax.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import salestaxcalculator.example.bcsalestax.ui.screens.MainViewModel

// Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
@Composable
fun SelectRow(
    radioOptions: List<String>,
    modifier: Modifier,
    selectedOption: (String) = radioOptions[0],
    onOptionSelected: (String) -> Unit
) {
    Column(
        modifier.selectableGroup(),
        Arrangement.spacedBy(12.dp)
    ) {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = null
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SeleectRowPreview(){
    val viewModel = MainViewModel()

    val selectedOptionState = remember { mutableStateOf(viewModel.selectedOptions) }

    val onOptionSelected: (String) -> Unit = { selectedOption ->
        selectedOptionState.value = selectedOption
    }
    SelectRow(radioOptions = listOf("Custom Tax", "Provincial Tax", "Budget"), modifier = Modifier
        .padding(top = 16.dp, bottom = 12.dp),
        selectedOptionState.value,
        onOptionSelected)
}