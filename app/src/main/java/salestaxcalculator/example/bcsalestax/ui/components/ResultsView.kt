package salestaxcalculator.example.bcsalestax.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bcsalestax.R
import java.text.NumberFormat

@Composable
fun CustomTaxResultsView(
    taxAmount: Double?,
    totalAmount: Double?,
    itemAmount: Double?,
    @StringRes labelResId: Int,
    modifier: Modifier = Modifier
) {
    // ToDo: Add copy and paste to results view
    ElevatedCard(
        modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(),
        colors = CardDefaults.cardColors(),
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row {
                Text(text = "Results:", style = MaterialTheme.typography.headlineSmall)
            }
            //Item Amount
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.item_amount),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Left
                )
                Text(
                    text = NumberFormat.getCurrencyInstance().format(itemAmount),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Right
                )
            }
            //Show tax amount results
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.tax_amount),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Left
                )
                Text(
                    text = NumberFormat.getCurrencyInstance().format(taxAmount),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Right
                )
            }
            //Show Total amount with Taxes
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = labelResId),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Left
                )
                Text(
                    text = NumberFormat.getCurrencyInstance().format(totalAmount),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Right
                )
            }
        }
    }
}

@Composable
fun ProvincialResultsView(
    pst: Double?,
    gst: Double?,
    hst: Double?,
    amount: Double?,
    totalAmountText: String,
    totalAmount: Double?,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(),
        colors = CardDefaults.cardColors(),
        content = {
            Column(
                modifier = Modifier
                    .padding(8.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Row {
                    Text(
                        text = stringResource(R.string.results),
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
                // Item amount
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.item_amount),
                        fontSize = 18.sp,
                        textAlign = TextAlign.Left
                    )
                    Text(
                        text = NumberFormat.getCurrencyInstance().format(amount),
                        fontSize = 18.sp,
                        textAlign = TextAlign.Right
                    )
                }
                // Show PST results
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.pst_amount),
                        fontSize = 18.sp,
                        textAlign = TextAlign.Left
                    )
                    Text(
                        text = NumberFormat.getCurrencyInstance().format(pst),
                        fontSize = 18.sp,
                        textAlign = TextAlign.Right
                    )
                }
                // Show GST results
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.gst_amount),
                        fontSize = 18.sp,
                        textAlign = TextAlign.Left
                    )
                    Text(
                        text = NumberFormat.getCurrencyInstance().format(gst),
                        fontSize = 18.sp,
                        textAlign = TextAlign.Right
                    )
                }
                // Show HST results
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.hst_amount),
                        fontSize = 18.sp,
                        textAlign = TextAlign.Left
                    )
                    Text(
                        text = NumberFormat.getCurrencyInstance().format(hst),
                        fontSize = 18.sp,
                        textAlign = TextAlign.Right
                    )
                }
                //Show Total amount with Taxes - Total Amount With Tax:
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = totalAmountText,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Left
                    )
                    Text(
                        text = NumberFormat.getCurrencyInstance().format(totalAmount),
                        fontSize = 18.sp,
                        textAlign = TextAlign.Right
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun CardViews() {
    Column {
        // Tax Views
        CustomTaxResultsView(
            taxAmount = 10.00,
            totalAmount = 50.00,
            labelResId = R.string.total_amount_label,
            itemAmount = 50.00
        )
        Spacer(modifier = Modifier.height(10.dp))
        ProvincialResultsView(
            pst = 5.00,
            gst = 5.00,
            hst = 5.00,
            amount = 5.00,
            "Total Amount With Tax:",
            totalAmount = 50.00,
        )
        Spacer(modifier = Modifier.height(10.dp))
        CustomTaxResultsView(
            taxAmount = 10.00,
            totalAmount = 50.00,
            labelResId = R.string.total_amount_label,
            itemAmount = 50.00
        )
        Spacer(modifier = Modifier.height(10.dp))
        // Budget Views
        CustomTaxResultsView(
            totalAmount = 50.00,
            taxAmount = 10.00,
            labelResId = R.string.budget_amount_label,
            itemAmount = 50.00
        )
        Spacer(modifier = Modifier.height(10.dp))
        ProvincialResultsView(
            pst = 5.0,
            gst = 5.0,
            hst = 5.0,
            amount = 5.00,
            "Budget Amount Without Tax:",
            totalAmount = 50.0
        )
        Spacer(modifier = Modifier.height(10.dp))
        CustomTaxResultsView(
            taxAmount = 10.0,
            totalAmount = 50.0,
            labelResId = R.string.budget_amount_label,
            itemAmount = 50.00
        )
    }
}

