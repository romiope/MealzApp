package com.ramiras.mealzapp.ui.details

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.ramiras.model.response.MealResponse
import java.lang.Float.min

@Composable
fun MealDetailsScreen(meal: MealResponse?) {
//    var profilePictureState by remember { mutableStateOf(MealProfilePictureState.Normal) }
//    val transition = updateTransition(targetState = profilePictureState, label = "")
//    val imageSize by transition.animateDp(targetValueByState = { it.size }, label = "")
//    val color by transition.animateColor(targetValueByState = { it.color }, label = "")
//    val borderWidth by transition.animateDp(targetValueByState = { it.borderWith }, label = "")
    val scrollState = rememberLazyListState()
    val offset = min(
        1f,
        1 - (scrollState.firstVisibleItemScrollOffset / 600f + scrollState.firstVisibleItemIndex)
    )
    val size by animateDpAsState(targetValue = max(100.dp, 140.dp * offset))

    Surface(
        color = MaterialTheme.colors.background
    ) {
        Column {
            Surface(elevation = 4.dp) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Card(
                        modifier = Modifier.padding(16.dp),
                        shape = CircleShape,
                        border = BorderStroke(
                            width = 2.dp,
                            color = Color.Green
                        )
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(meal?.imageUrl)
                                    .transformations(CircleCropTransformation()).build(),
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .size(size),
                        )
                    }
                    Text(
                        text = meal?.name ?: "default name",
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
            Button(
                onClick = {
//                    profilePictureState =
//                        if (profilePictureState == MealProfilePictureState.Normal) MealProfilePictureState.Expanded
//                        else MealProfilePictureState.Normal
                }, modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Change state of meal profile picture")
            }
            val dummyContentList = (0..100).map { it.toString() }
            LazyColumn(state = scrollState) {
                items(dummyContentList) { dummyItems ->
                    Text(text = dummyItems, modifier = Modifier.padding(24.dp))
                }
            }
        }
    }
}

enum class MealProfilePictureState(val color: Color, val size: Dp, val borderWith: Dp) {
    Normal(Color.Green, 120.dp, 8.dp),
    Expanded(Color.Magenta, 200.dp, 24.dp),
}
