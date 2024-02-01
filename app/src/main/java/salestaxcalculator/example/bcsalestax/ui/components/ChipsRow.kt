import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ChipsRow(
    chips: List<String>,
    modifier: Modifier = Modifier,
    selectedChip: (String) = chips[0],
    onChipSelected: (String) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        chips.forEach { chip ->
            ElevatedFilterChip(
                selected = (chip == selectedChip),
                onClick = {
                    onChipSelected(chip)
                },
                label = { Text(chip) },
                leadingIcon = {
                    if (chip == selectedChip) {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "Done icon",
                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                        )
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun ChipsRowPreview() {
    ChipsRow(
        chips = listOf(
            "\uD83C\uDF0E Custom",
            "\uD83C\uDDE8\uD83C\uDDE6 Canada",
            "\uD83C\uDDFA\uD83C\uDDF8 Usa"
        ),
    ) {
    }
}

