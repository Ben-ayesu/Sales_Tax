package salestaxcalculator.example.bcsalestax.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@ExperimentalMaterial3Api
@Composable
fun TextField(
    value: String?,
    label: String,
    leadingIcon: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier
) {
    var showError by remember { mutableStateOf(false) }

    if (value != null) {
        OutlinedTextField(
            value = value,
            onValueChange = {
                showError = it.isEmpty()
                onValueChange(it)
            },
            modifier
                .fillMaxWidth(),
            label = { Text(text = label) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Previous
            ),
            isError = showError,
            maxLines = 1,
            leadingIcon = { Text(text = leadingIcon) },
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
    if (showError) {
        Text(
            text = "This field is required",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.labelSmall
        )
    }
}
