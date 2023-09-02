import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun CustomToast(
  message: String,
  backgroundColor: Color = Color.Gray,
  textColor: Color = Color.White
) {
  var visible by remember { mutableStateOf(true) }
  val scale by animateFloatAsState(
    targetValue = if (visible) 1f else 0.8f,
    animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy), label = ""
  )

  Box(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp)
      .graphicsLayer(scaleX = scale, scaleY = scale)
      .background(backgroundColor)
      .padding(16.dp),
    contentAlignment = Alignment.Center
  ) {
    Text(
      text = message,
      color = textColor,
      fontSize = 16.sp,
      fontWeight = FontWeight.Bold
    )
  }

  LaunchedEffect(visible) {
    if (visible) {
      delay(2000) // Toast duration
      visible = false
    }
  }
}