package md.todo.compouse.ui.screens.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import md.todo.compouse.R
import md.todo.compouse.component.PriorityItem
import md.todo.compouse.data.models.Priority
import md.todo.compouse.ui.theme.*

@Composable
fun ListAppbar() {
    /*  DefaultListAppBar(
          onSearchClicked = {},
          onSortAction = {},
          onDeleteClick = {}
      )*/

    SearchAppBar(text = "", onTextChange = {}, onCloseClicked = { }) {

    }
}

@Composable
fun DefaultListAppBar(
    onSearchClicked: () -> Unit,
    onSortAction: (Priority) -> Unit,
    onDeleteClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Tasks",
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        actions = {
            ListAppBarActions(
                onSearchClicked = onSearchClicked,
                onSortAction = onSortAction,
                onDeleteClick = onDeleteClick
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor
    )
}

@Composable
fun ListAppBarActions(
    onSearchClicked: () -> Unit,
    onSortAction: (Priority) -> Unit,
    onDeleteClick: () -> Unit
) {
    SearchAction(onSearchClicked = onSearchClicked)
    SortAction(onSortAction = onSortAction)
    DeleteAllAction(onDeleteClick = onDeleteClick)
}

@Composable
fun SearchAction(onSearchClicked: () -> Unit) {
    IconButton(onClick = { onSearchClicked() }) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = stringResource(R.string.search_action_description),
            tint = MaterialTheme.colors.topAppBarContentColor
        )

    }
}

@Composable
fun SortAction(onSortAction: (Priority) -> Unit) {
    var expanded by remember {
        mutableStateOf(false)
    }
    IconButton(onClick = { expanded = true }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_filter_list),
            contentDescription = stringResource(
                R.string.sort_action_description
            ),
            tint = MaterialTheme.colors.topAppBarContentColor
        )

        DropdownMenu(expanded = expanded,
            onDismissRequest = { expanded = false }) {
            DropdownMenuItem(onClick = {
                expanded = false
                onSortAction(Priority.LOW)
            }
            ) {
                PriorityItem(priority = Priority.LOW)
            }

            DropdownMenuItem(onClick = {
                expanded = false
                onSortAction(Priority.MEDIUM)
            }
            ) {
                PriorityItem(priority = Priority.MEDIUM)
            }

            DropdownMenuItem(onClick = {
                expanded = false
                onSortAction(Priority.HIGH)
            }
            ) {
                PriorityItem(priority = Priority.HIGH)
            }
        }
    }
}

@Composable
fun DeleteAllAction(onDeleteClick: () -> Unit) {
    var expanded by remember {
        mutableStateOf(false)
    }
    IconButton(onClick = { expanded = true }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_more_vert),
            contentDescription = stringResource(
                R.string.delete_all_action
            ),
            tint = MaterialTheme.colors.topAppBarContentColor
        )

        DropdownMenu(expanded = expanded,
            onDismissRequest = { expanded = false }) {

            DropdownMenuItem(onClick = {
                expanded = false
                onDeleteClick()
            }
            ) {
                Text(
                    modifier = Modifier.padding(start = LARGE_PADDING),
                    text = stringResource(R.string.delete_all_action),
                    style = Typography.subtitle2
                )
            }
        }
    }
}

@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {


    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_APP_BAR_HEIGHT),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.topAppBarBackgroundColor
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    text = "Search", color = Color.White
                )
            },
            textStyle = TextStyle(
                color = MaterialTheme.colors.topAppBarContentColor,
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier.alpha(ContentAlpha.disabled),
                    onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search Icon",
                        tint = MaterialTheme.colors.topAppBarContentColor
                    )

                }
            },
            trailingIcon = {
                IconButton(onClick = { onCloseClicked() }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Close Icon",
                        tint = MaterialTheme.colors.topAppBarContentColor
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = MaterialTheme.colors.topAppBarContentColor,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent
            )
        )
    }
}

@Preview()
@Composable
fun DefaultListAppPreview() {
    DefaultListAppBar(onSearchClicked = {}, onSortAction = {}, onDeleteClick = {})
}

@Preview
@Composable
fun SearchBarPreview() {
    SearchAppBar(text = "", onTextChange = {}, onCloseClicked = { }) {

    }
}