package salestaxcalculator.example.bcsalestax.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
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
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        modifier
            .fillMaxWidth()
            .padding(16.dp),
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
                    .padding(top = 16.dp),
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )
            //Show Total amount with Taxes
            Text(
                text = "Total Amount With Tax: ${
                    NumberFormat.getCurrencyInstance().format(totalAmount)
                }",
                modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                fontSize = 25.sp,
                textAlign = TextAlign.Center

            )
        }
    )
}

@Composable
fun ProvincialTaxResultsView(
    pst: Double?,
    gst: Double?,
    hst: Double?,
    totalAmount: Double?,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 100.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        content = {
            // Show PST results
            Text(
                text = "PST Amount: ${NumberFormat.getCurrencyInstance().format(pst)}",
                modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
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
            //Show Total amount with Taxes
            Text(
                text = "Total Amount With Tax: ${
                    NumberFormat.getCurrencyInstance().format(totalAmount)
                }",
                modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 16.dp),
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
    OutlinedCard(
        modifier
            .fillMaxWidth()
            .padding(16.dp),
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
                    .padding(top = 16.dp),
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )
            //Show Total amount with Taxes
            Text(
                text = "Total Amount With Tax: ${
                    NumberFormat.getCurrencyInstance().format(totalAmount)
                }",
                modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                fontSize = 25.sp,
                textAlign = TextAlign.Center

            )
        }
    )
}

@Composable
fun BudgetCustomResultsView(
    budgetAmount: Double?,
    taxAmount: Double?,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 100.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        content = {
            // Show Total amount without Taxes
            Text(
                text = "Budget Amount Without Tax: ${
                    NumberFormat.getCurrencyInstance().format(budgetAmount)
                }",
                modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )
            // Show tax amount results
            Text(
                text = "Tax Amount: ${NumberFormat.getCurrencyInstance().format(taxAmount)}",
                modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )
        }
    )
}

@Composable
fun ProvincialBudgetResultsView(
    pst: Double?,
    gst: Double?,
    hst: Double?,
    budgetAmount: Double?,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 100.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        content = {
            //Show budget amount without Taxes
            Text(
                text = "Budget Amount Without Tax: ${
                    NumberFormat.getCurrencyInstance().format(budgetAmount)
                }",
                modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                fontSize = 25.sp,
                textAlign = TextAlign.Center

            )
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
                    .padding(top = 8.dp, bottom = 16.dp),
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )
        }
    )
}

@Composable
fun StateBudgetsResultsView(
    taxAmount: Double?,
    budgetAmount: Double?,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 100.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        content = {
            //Show Total amount with Taxes
            Text(
                text = "Budget Amount Without Tax: ${
                    NumberFormat.getCurrencyInstance().format(budgetAmount)
                }",
                modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                fontSize = 25.sp,
                textAlign = TextAlign.Center

            )
            //Show tax amount results
            Text(
                text = "Tax Amount: ${NumberFormat.getCurrencyInstance().format(taxAmount)}",
                modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
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
        CustomTaxResultsView(taxAmount = 10.00, totalAmount = 50.00)
        ProvincialTaxResultsView(pst = 5.00, gst = 5.00, hst = 5.00, totalAmount = 50.00)
        StateTaxResultsView(taxAmount = 10.00, totalAmount = 50.00)

        // Budget Views
        BudgetCustomResultsView(budgetAmount = 50.00, taxAmount = 10.00)
        ProvincialBudgetResultsView(pst = 5.0, gst = 5.0, hst = 5.0, budgetAmount = 50.0)
        StateBudgetsResultsView(taxAmount = 10.0, budgetAmount = 50.0)
    }

}
