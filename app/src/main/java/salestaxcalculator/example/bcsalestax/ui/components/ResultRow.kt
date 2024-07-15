package salestaxcalculator.example.bcsalestax.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import java.text.NumberFormat

@Composable
fun ResultRow(
    label: String,
    value: Double?,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = 18.sp,
            textAlign = TextAlign.Left
        )
        Text(
            text = NumberFormat.getCurrencyInstance().format(value),
            fontSize = 18.sp,
            textAlign = TextAlign.Right
        )
    }
}

@Preview
@Composable
private fun ResultsRow() {
    ResultRow("Tax Rate", 10.0)
}