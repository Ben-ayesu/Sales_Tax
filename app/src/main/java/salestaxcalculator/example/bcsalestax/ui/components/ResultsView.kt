package salestaxcalculator.example.bcsalestax.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat

@Composable
fun ResultsView(
    taxAmount: Double?,
    totalAmount: Double?
) {
    //Show tax amount results
    Text(
        text = "Tax Amount: ${NumberFormat.getCurrencyInstance().format(taxAmount)}",
        Modifier
            .padding(8.dp),
        fontSize = 25.sp
    )

    //Show Total amount with Taxes
    Text(
        text = "Total Amount: ${NumberFormat.getCurrencyInstance().format(totalAmount)}",
        fontSize = 25.sp
    )
}