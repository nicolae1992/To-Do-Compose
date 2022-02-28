package md.todo.compouse.ui.screens.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import md.todo.compouse.R
import md.todo.compouse.component.PriorityDropDown
import md.todo.compouse.data.models.Priority
import md.todo.compouse.ui.theme.LARGE_PADDING
import md.todo.compouse.ui.theme.MEDIUM_PADDING

@Composable
fun TaskContent(
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .padding(all = LARGE_PADDING)

    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = { onTitleChange(it) },
            label = { Text(text = stringResource(R.string.title_lable_task)) },
            textStyle = MaterialTheme.typography.body1,
            singleLine = true
        )

        Divider(modifier = Modifier.height(MEDIUM_PADDING), color = MaterialTheme.colors.background)

        PriorityDropDown(priority = priority, onPrioritySelect = onPrioritySelected)

        OutlinedTextField(
            modifier = Modifier.fillMaxSize(),
            value = description,
            onValueChange = { onDescriptionChange(it) },
            label = { Text(text = stringResource(R.string.title_label_task_description)) },
            textStyle = MaterialTheme.typography.body1
        )
    }
}

@Preview
@Composable
fun ContentPreview() {
    TaskContent(
        title = "",
        onTitleChange = {},
        description = "",
        onDescriptionChange = {},
        priority = Priority.LOW,
        onPrioritySelected = {}
    )
}