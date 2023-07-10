import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TutorialBottomSheetScreen() {
    val showModalBottomSheet = rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Button(onClick = {
            showModalBottomSheet.value = !showModalBottomSheet.value
        }) {
            Text(text = "Show List of Items")
        }
    }
    TutorialModalBottomSheet(showModalBottomSheet)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorialModalBottomSheet(showModalBottomSheet: MutableState<Boolean>) {
    val scope = rememberCoroutineScope()
    var skipPartially by remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = skipPartially)

    if (showModalBottomSheet.value)
        ModalBottomSheet(
            onDismissRequest = { showModalBottomSheet.value = false },
            sheetState = bottomSheetState,
        ) {
            Column(Modifier.fillMaxSize()) {

            }
        }
}