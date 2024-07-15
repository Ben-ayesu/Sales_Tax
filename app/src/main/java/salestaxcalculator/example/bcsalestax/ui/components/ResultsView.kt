package salestaxcalculator.example.bcsalestax.ui.components

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

@Composable
fun CustomTaxResultsView(
    taxAmount: Double?,
    totalAmount: Double?,
    itemAmount: Double?,
    taxRate: Double?,
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
            Text(text = "Results:", style = MaterialTheme.typography.headlineSmall)
            ResultRow(stringResource(R.string.item_amount), itemAmount)
            // Tax Rate
            Row( modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = stringResource(id = R.string.tax_rate),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Left
                )
                Text(
                    text = "${taxRate ?: 0.0}%",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Right
                )
            }
            ResultRow(stringResource(R.string.tax_amount), taxAmount)
            ResultRow(stringResource(R.string.total_amount), totalAmount)
        }
    }
}

@Composable
fun ProvincialResultsView(
    pst: Double?,
    gst: Double?,
    hst: Double?,
    amount: Double?,
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
                ResultRow(stringResource(R.string.item_amount), amount)
                ResultRow(stringResource(R.string.pst_amount), pst)
                ResultRow(stringResource(R.string.gst_amount), gst)
                ResultRow(stringResource(R.string.hst_amount), hst)
                ResultRow(stringResource(R.string.total_amount_with_tax), totalAmount)
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
            itemAmount = 50.00,
            taxRate = 10.00
        )
        Spacer(modifier = Modifier.height(10.dp))
        ProvincialResultsView(
            pst = 5.00,
            gst = 5.00,
            hst = 5.00,
            amount = 5.00,
            totalAmount = 50.00,
        )
        Spacer(modifier = Modifier.height(10.dp))
        CustomTaxResultsView(
            taxAmount = 10.00,
            totalAmount = 50.00,
            itemAmount = 50.00,
            taxRate = 10.00
        )
        Spacer(modifier = Modifier.height(10.dp))
        // Budget Views
        CustomTaxResultsView(
            taxAmount = 10.00,
            totalAmount = 50.00,
            itemAmount = 50.00,
            taxRate = 10.00
        )
        Spacer(modifier = Modifier.height(10.dp))
        ProvincialResultsView(
            pst = 5.0,
            gst = 5.0,
            hst = 5.0,
            amount = 5.00,
            totalAmount = 50.0
        )
        Spacer(modifier = Modifier.height(10.dp))
        CustomTaxResultsView(
            taxAmount = 10.0,
            totalAmount = 50.0,
            itemAmount = 50.00,
            taxRate = 10.0
        )
    }
}

