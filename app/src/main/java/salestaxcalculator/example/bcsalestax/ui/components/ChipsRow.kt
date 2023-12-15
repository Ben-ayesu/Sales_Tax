import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChipsRow(
    chips: List<String>,
    modifier: Modifier = Modifier,
    selectedChip: (String) = chips[0],
    onChipSelected: (String) -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        chips.forEach { text ->
            FilterChip(
                selected = (text == selectedChip),
                onClick = { onChipSelected(text) },
                label = { Text(text) }
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

