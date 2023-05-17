package salestaxcalculator.example.bcsalestax.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

fun Menu(): List<Pair<ImageVector, String>> {
    return listOf(
        Icons.Outlined.Home to "Home",
        Icons.Outlined.Call to "Call",
        Icons.Outlined.Settings to "Settings"
    )
}

@Composable
fun CustomBottomNavBar(
    onClick: (String) -> Unit
) {

    var selectedNavIndex by remember { mutableStateOf(0) }
    val navigationIcons = Menu()

    NavigationBar(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
    ) {
        navigationIcons.forEachIndexed { index, items ->
            NavigationBarItem(
                selected = selectedNavIndex == index,
                onClick = {
                    selectedNavIndex = index
                    onClick(items.second)
                },
                label = {
                    Text(
                        text = items.second
                    )
                },
                icon = {
                    Icon(
                        imageVector = items.first,
                        contentDescription = "Home"
                    )
                }
            )
        }
    }
}