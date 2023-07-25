package salestaxcalculator.example.bcsalestax.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

/**
 * 🚀 A Jetpack Compose Android Library to create a dropdown menu that is searchable.
 * @param modifier a modifier for this SearchableExpandedDropDownMenu and its children
 * @param listOfItems A list of objects that you want to display as a dropdown
 * @param enable controls the enabled state of the OutlinedTextField. When false, the text field will be neither editable nor focusable, the input of the text field will not be selectable, visually text field will appear in the disabled UI state
 * @param placeholder the optional placeholder to be displayed when the text field is in focus and
 * the input text is empty. The default text style for internal [Text] is [Typography]
 * @param openedIcon the Icon displayed when the dropdown is opened. Default Icon is [Icons.Outlined.KeyboardArrowUp]
 * @param closedIcon Icon displayed when the dropdown is closed. Default Icon is [Icons.Outlined.KeyboardArrowDown]
 * @param parentTextFieldCornerRadius : Defines the radius of the enclosing OutlinedTextField. Default Radius is 12.dp
 * @param colors [TextFieldColors] that will be used to resolve color of the text and content
 * (including label, placeholder, leading and trailing icons, border) for this text field in
 * different states. See [TextFieldDefaults.outlinedTextFieldColors]
 * @param onDropDownItemSelected Returns the item that was selected from the dropdown
 * @param dropdownItem Provide a composable that will be used to populate the dropdown and that takes a type i.e String,Int or even a custom type
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> SearchableExpandedDropDownMenu(
    modifier: Modifier = Modifier,
    listOfItems: List<T>, enable: Boolean = true,
    readOnly: Boolean = true, placeholder: String = "Select Option",
    openedIcon: ImageVector = Icons.Outlined.KeyboardArrowUp,
    closedIcon: ImageVector = Icons.Outlined.KeyboardArrowDown,
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(),
    onDropDownItemSelected: (T) -> Unit = {},
    dropdownItem: @Composable (T) -> Unit,
    isError: Boolean = false
) {
    var selectedOptionText by rememberSaveable { mutableStateOf("") }
    var searchedOption by rememberSaveable { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var filteredItems = mutableListOf<T>()

    val itemHeights = remember { mutableStateMapOf<Int, Int>() }
    val baseHeight = 530.dp
    val density = LocalDensity.current

    val maxHeight = remember(itemHeights.toMap()) {
        if (itemHeights.keys.toSet() != listOfItems.indices.toSet()) {
            // if we don't have all heights calculated yet, return default value
            return@remember baseHeight
        }
        val baseHeightInt = with(density) { baseHeight.toPx().toInt() }

        // top+bottom system padding
        var sum = with(density) { DropdownMenuVerticalPadding.toPx().toInt() } * 2
        for ((_, itemSize) in itemHeights.toSortedMap()) {
            sum += itemSize
            if (sum >= baseHeightInt) {
                return@remember with(density) { (sum - itemSize / 2).toDp() }
            }
        }
        // all items fit into base height
        baseHeight
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = modifier
                .clickable {
                    expanded = !expanded
                },
            colors = colors,
            value = selectedOptionText,
            readOnly = readOnly,
            enabled = enable,
            onValueChange = { selectedOptionText = it },
            placeholder = {
                Text(text = placeholder)
            },
            trailingIcon = {
                IconToggleButton(
                    checked = expanded,
                    onCheckedChange = {
                        expanded = it
                    }
                ) {
                    if (expanded) Icon(
                        imageVector = closedIcon,
                        contentDescription = null
                    ) else Icon(
                        imageVector = openedIcon,
                        contentDescription = null
                    )
                }
            },
            shape = CircleShape,
            isError = isError,
            interactionSource = remember { MutableInteractionSource() }
                .also { interactionSource ->
                    LaunchedEffect(interactionSource) {
                        interactionSource.interactions.collect {
                            if (it is PressInteraction.Release) {
                                expanded  = !expanded
                            }
                        }
                    }
                }
        )
        if (expanded) {
            DropdownMenu(
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .requiredSizeIn(maxHeight = maxHeight),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedTextField(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        value = searchedOption,
                        onValueChange = { item ->
                            searchedOption = item
                            filteredItems = listOfItems.filter {
                                it.toString().contains(
                                    searchedOption,
                                    ignoreCase = true
                                )
                            }.toMutableList()
                        },
                        leadingIcon = {
                            Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
                        },
                        placeholder = {
                            Text(text = "Search")
                        },
                        shape = CircleShape
                    )

                    val items = if (filteredItems.isEmpty()) {
                        listOfItems
                    } else {
                        filteredItems
                    }

                    items.forEach { selectedItem ->
                        DropdownMenuItem(
                            onClick = {
                                selectedOptionText = selectedItem.toString()
                                onDropDownItemSelected(selectedItem)
                                searchedOption = ""
                                expanded = false
                            },
                            text = {
                                dropdownItem(selectedItem)
                            },
                            colors = MenuDefaults.itemColors()
                        )
                    }
                }
            }
        }
    }
}

private val DropdownMenuVerticalPadding = 8.dp