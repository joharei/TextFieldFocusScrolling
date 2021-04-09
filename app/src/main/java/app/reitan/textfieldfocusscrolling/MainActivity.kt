package app.reitan.textfieldfocusscrolling

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.reitan.textfieldfocusscrolling.ui.theme.TextFieldFocusScrollingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TextFieldFocusScrollingTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    TextFields()
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextFields() {
    val (textField1, textField2, textField3) = FocusRequester.createRefs()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        TextField(
            modifier = Modifier
                .focusRequester(textField1)
                .focusOrder { next = textField2 }
                .fillMaxWidth(),
            value = "",
            onValueChange = {},
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        )
        TextField(
            modifier = Modifier
                .focusRequester(textField2)
                .focusOrder { next = textField3 }
                .padding(top = 100.dp)
                .fillMaxWidth(),
            value = "",
            onValueChange = {},
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        )
        TextField(
            modifier = Modifier
                .focusRequester(textField3)
                .focusOrder { previous = textField2 }
                .padding(top = 100.dp)
                .fillMaxWidth(),
            value = "",
            onValueChange = {},
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Previous),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TextFieldFocusScrollingTheme {
        TextFields()
    }
}