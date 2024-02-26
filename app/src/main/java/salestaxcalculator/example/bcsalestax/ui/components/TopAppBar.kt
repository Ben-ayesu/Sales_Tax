package salestaxcalculator.example.bcsalestax.ui.components

import ModalBottomSheet
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import salestaxcalculator.example.bcsalestax.ui.screens.SalesTaxViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    title: String,
    viewModel: SalesTaxViewModel
) {
    val showModalBottomSheet = rememberSaveable { mutableStateOf(viewModel.bottomSheetState) }

    if (showModalBottomSheet.value) {
        ModalBottomSheet(showModalBottomSheet, viewModel)
    }

    androidx.compose.material3.TopAppBar(
        title = {
            Text(
                modifier = Modifier
                    .padding(top = 16.dp),
                text = title
            )
        },
        actions = {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Bottom,
            ) {
                BadgedBox(
                    modifier = Modifier
                        .offset(x = (-24).dp, y = 8.dp),
                    badge = {
                        Badge {
                            Text(
                                text = "${viewModel.itemList.size}",
                            )
                        }
                    }
                ) {
                    IconButton(
                        onClick = {
                            showModalBottomSheet.value = !showModalBottomSheet.value
                        },
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.List,
                            contentDescription = "Favorite"
                        )
                    }
                }
            }
        }
    )
}