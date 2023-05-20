package salestaxcalculator.example.bcsalestax.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat

@Composable
fun CustomTaxResultsView(
    taxAmount: Double?,
    totalAmount: Double?,
    modifier: Modifier = Modifier
) {
    Card(
        modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(
                elevation = 25.dp,
                ambientColor = MaterialTheme.colorScheme.onPrimary,
                spotColor = MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(10.dp)
            ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 100.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        content = {
            //Show tax amount results
            Text(
                text = "Tax Amount: ${NumberFormat.getCurrencyInstance().format(taxAmount)}",
                modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )
            //Show Total amount with Taxes
            Text(
                text = "Total Amount: ${NumberFormat.getCurrencyInstance().format(totalAmount)}",
                modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                fontSize = 25.sp,
                textAlign = TextAlign.Center

            )
        }
    )
}

@Composable
fun ProvincialTaxResultsView(
    PST: Double?,
    GST: Double?,
    HST: Double?,
    totalAmount: Double?,
    modifier: Modifier = Modifier
) {
    Card(
        modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(
                elevation = 25.dp,
                ambientColor = MaterialTheme.colorScheme.onPrimary,
                spotColor = MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(10.dp)
            ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 100.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        content = {
            // Show PST results
            Text(
                text = "PST Amount: ${NumberFormat.getCurrencyInstance().format(PST)}",
                modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )
            // Show GST results
            Text(
                text = "GST Amount: ${NumberFormat.getCurrencyInstance().format(GST)}",
                modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )
            // Show HST results
            Text(
                text = "HST Amount: ${NumberFormat.getCurrencyInstance().format(HST)}",
                modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )
            //Show Total amount with Taxes
            Text(
                text = "Total Amount: ${NumberFormat.getCurrencyInstance().format(totalAmount)}",
                modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp),
                fontSize = 25.sp,
                textAlign = TextAlign.Center

            )
        }
    )
}

@Composable
fun StateTaxResultsView(
    taxAmount: Double?,
    totalAmount: Double?,
    modifier: Modifier = Modifier
) {
    Card(
        modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(
                elevation = 25.dp,
                ambientColor = MaterialTheme.colorScheme.onPrimary,
                spotColor = MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(10.dp)
            ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 100.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        content = {
            //Show tax amount results
            Text(
                text = "Tax Amount: ${NumberFormat.getCurrencyInstance().format(taxAmount)}",
                modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )
            //Show Total amount with Taxes
            Text(
                text = "Total Amount: ${NumberFormat.getCurrencyInstance().format(totalAmount)}",
                modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                fontSize = 25.sp,
                textAlign = TextAlign.Center

            )
        }
    )
}

@Composable
fun BudgetResultsScreen(
    taxAmount: Double?,
    budgetAmount: Double?,
    modifier: Modifier = Modifier
) {
    Card(
        modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(
                elevation = 25.dp,
                ambientColor = MaterialTheme.colorScheme.onPrimary,
                spotColor = MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(10.dp)
            ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 100.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        content = {
            //Show budget amount results
            Text(
                text = "Tax Amount: ${NumberFormat.getCurrencyInstance().format(budgetAmount)}",
                modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )
            //Show Total amount with Taxes
            Text(
                text = "Max Item Amount: ${NumberFormat.getCurrencyInstance().format(taxAmount)}",
                modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                fontSize = 25.sp,
                textAlign = TextAlign.Center

            )
        }
    )
}
