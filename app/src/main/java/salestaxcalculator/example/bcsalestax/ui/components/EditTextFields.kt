package salestaxcalculator.example.bcsalestax.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@ExperimentalMaterial3Api
@Composable
fun EditTaxRate(
    value: String?,
    onValueChange: (String) -> Unit
) {
    if (value != null) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            label = { Text(text = "Enter Tax Rate") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Previous
            ),
            maxLines = 1,
            leadingIcon = { Text(text = "%") },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    modifier = Modifier
                        .clickable { onValueChange("") }
                )
            },
            shape = CircleShape
        )
    }
}

@ExperimentalMaterial3Api
@Composable
fun EditItemNumberField(
    value: String?,
    onValueChange: (String) -> Unit
) {
    if (value != null) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            label = { Text("Enter the Item Price") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            maxLines = 1,
            leadingIcon = { Text(text = "$") },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    modifier = Modifier
                        .clickable { onValueChange("") }
                )
            },
            shape = CircleShape
        )
    }
}

@ExperimentalMaterial3Api
@Composable
fun EditBudgetRate(
    value: String?,
    onValueChange: (String) -> Unit
) {
    if (value != null) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            label = { Text(text = "Enter Your Budget") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Previous
            ),
            maxLines = 1,
            leadingIcon = { Text(text = "$") },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    modifier = Modifier
                        .clickable { onValueChange("") }
                )
            },
            shape = CircleShape
        )
    }
}
