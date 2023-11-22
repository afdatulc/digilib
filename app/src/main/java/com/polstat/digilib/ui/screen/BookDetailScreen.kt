// ... (imports dan bagian lainnya)

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.polstat.digilib.R

@Composable
fun BookDetailScreen(book: Book) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = book.imageResId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = book.title,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = book.description,
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(modifier = Modifier.weight(1f)) // Spacer untuk memberikan ruang di bagian bawah

        Button(
            onClick = { /* TODO: Implement registration functionality */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Kembali")
        }
    }
}

@Composable
fun BookDetailScreenPreview() {
    val sampleBook = Book(
        imageResId= R.drawable.book1,
        title = "Android Programming with Kotlin for Beginners",
        description = "Android is the most popular mobile operating system in the world and Kotlin has been declared by Google as a first-class programming language to build Android apps. With the imminent arrival of the most anticipated Android update, Android 10 (Q), this book gets you started building apps compatible with the latest version of Android."
    )

    BookDetailScreen(book = sampleBook)
}

@Preview
@Composable
fun BookDetailScreenPreviewPreview() {
    BookDetailScreenPreview()
}