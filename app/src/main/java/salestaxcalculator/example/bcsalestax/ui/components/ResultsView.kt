package salestaxcalculator.example.bcsalestax.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat

@Composable
fun CustomTaxResultsView(
    taxAmount: Double?,
    totalAmount: Double?,
    totalAmountText: String,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(),
        colors = CardDefaults.cardColors(),
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            //Show tax amount results
            Text(
                text = "Tax Amount: ${NumberFormat.getCurrencyInstance().format(taxAmount)}",
                modifier
                    .fillMaxWidth(),
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )
            //Show Total amount with Taxes
            Text(
                text = "$totalAmountText ${
                    NumberFormat.getCurrencyInstance().format(totalAmount)
                }",
                modifier
                    .fillMaxWidth(),
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ProvincialResultsView(
    pst: Double?,
    gst: Double?,
    hst: Double?,
    totalAmountText: String,
    totalAmount: Double?,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(),
        colors = CardDefaults.cardColors(),
        content = {
            // Show PST results
            Text(
                text = "PST Amount: ${NumberFormat.getCurrencyInstance().format(pst)}",
                modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )
            // Show GST results
            Text(
                text = "GST Amount: ${NumberFormat.getCurrencyInstance().format(gst)}",
                modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )
            // Show HST results
            Text(
                text = "HST Amount: ${NumberFormat.getCurrencyInstance().format(hst)}",
                modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )
            //Show Total amount with Taxes - Total Amount With Tax:

            Text(
                text = "$totalAmountText ${
                    NumberFormat.getCurrencyInstance().format(totalAmount)
                }",
                modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp),
                fontSize = 25.sp,
                textAlign = TextAlign.Center

            )
        }
    )
}
@Preview
@Composable
fun CardViews() {
    Column {
        // Tax Views
        CustomTaxResultsView(taxAmount = 10.00, totalAmount = 50.00, "Total Amount With Tax:")
        ProvincialResultsView(
            pst = 5.00,
            gst = 5.00,
            hst = 5.00,
            "Total Amount With Tax:",
            totalAmount = 50.00
        )
        CustomTaxResultsView(taxAmount = 10.00, totalAmount = 50.00, "Total Amount With Tax:")

        // Budget Views
        CustomTaxResultsView(
            totalAmount = 50.00,
            taxAmount = 10.00,
            totalAmountText = "Budget Amount Without Tax:"
        )
        ProvincialResultsView(
            pst = 5.0,
            gst = 5.0,
            hst = 5.0,
            "Budget Amount Without Tax:",
            totalAmount = 50.0
        )
        CustomTaxResultsView(taxAmount = 10.0, totalAmount = 50.0, "Budget Amount Without Tax:")
    }

}
