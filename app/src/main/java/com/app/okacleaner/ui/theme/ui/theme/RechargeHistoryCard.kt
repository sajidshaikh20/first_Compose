import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun RechargeHistoryCard(index: Int,img: Int, title: String, date: String, desc: String, modifier: Modifier,) {

    val context = LocalContext.current

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White, //Card background color
        ),
        border = BorderStroke(
            color = Color.Gray,
            width = 1.dp
        ),
        modifier = modifier
            .padding(8.dp)
            .fillMaxSize()
            .clickable {
                Toast
                    .makeText(context, "Card clicked! $index", Toast.LENGTH_SHORT)
                    .show()
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(5.dp)
        ) {
            Image(
                painter = painterResource(id = img),
                contentDescription = "",
                modifier = Modifier.weight(.2f)
            )
            Column(
                modifier = Modifier.padding(
                    start = 6.dp,
                    bottom = 6.dp
                ).weight(.8f)
            )
            {
                Text(
                    title,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    date,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(1.dp))
                Text(
                    desc,
                    color = Color.Gray

                )
            }
        }
    }


}