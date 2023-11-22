import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.polstat.digilib.R
import com.polstat.digilib.ui.screen.BookViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun BookCollectionScreen(viewModel: BookViewModel = viewModel()) {
    val books by viewModel.bookList.observeAsState(emptyList())
    var bookList by rememberSaveable { mutableStateOf(emptyList<Book>()) }

    var keyword by remember { mutableStateOf("") }
    LaunchedEffect(viewModel) {
        val dummyData = dummyData()
        viewModel.updateBookList(dummyData)
    }

    // Observe changes in the bookList and update the local state
    bookList = books

    Column {
        SearchBar(viewModel = viewModel, onSearch = { newKeyword ->
            viewModel.filterBooks(newKeyword)
        })
        BookList(books = books)
    }
}

@Composable
fun BookItem(book: Book){
    Row(
        modifier= Modifier.padding(8.dp)
    ) {
        Box{
            Image(
                painter = painterResource(id = R.drawable.book1),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(128.dp)
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
        ) {
            Text(
                text = book.title,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = book.description,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
data class Book(val imageResId: Int, val title: String, val description: String)
fun dummyData():List<Book>{
    val bookDummies = mutableListOf<Book>()
    for (i in 1..30) {
        val dummy = Book(
            imageResId = 1,
            title = "Android " + "Programming with Kotlin for Beginners - Edition $i",
            description = "Edition $i Android is " +
                    "the most popular mobile operating system in the world and Kotlin " +
                    "has been declared by Google as a first-class programming language " +
                    "to build Android apps. With the imminent arrival of the most " +
                    "anticipated Android update, Android 10 (Q), this book gets you " +
                    "started building apps compatible with the latest version of " +
                    "Android.")
        bookDummies.add(dummy)
    }
    return bookDummies
}

@Composable
fun BookList(books: List<Book>) {
    LazyColumn {
        items(books) { book ->
            BookItem(book)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(viewModel: BookViewModel, onSearch: (String) -> Unit) {
    val query by viewModel.query.observeAsState(TextFieldValue(""))

    TextField(
        value = query,
        onValueChange = {
            viewModel.updateQuery(it)
            onSearch(it.text)
        },
        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon") },
        placeholder = { Text("Enter keyword here...") },
        singleLine = true,
        maxLines = 1,
        modifier = Modifier.fillMaxWidth(),
        trailingIcon = {
            if (query.text.isNotEmpty()) {
                IconButton(onClick = {
                    viewModel.updateQuery(TextFieldValue(""))
                    onSearch("")
                }) {
                    Icon(imageVector = Icons.Rounded.Clear, contentDescription = "Clear Icon")
                }
            }
        }
    )
}


//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SearchBar(onSearch: (String) -> Unit) {
//    var query by remember { mutableStateOf(TextFieldValue("")) }
//    var showClearIcon by rememberSaveable{ mutableStateOf(false) }
//    if (query.text.isEmpty()) {
//        showClearIcon = false
//    } else if (query.text.isNotEmpty()) {
//        showClearIcon = true
//    }
//    TextField(
//        value = query,
//        onValueChange = { newQuery ->
//            query = newQuery
//            if (query.text.isNotEmpty()) {
//                onSearch(query.text)
//            }
//        },
//        leadingIcon = { Icon(imageVector = Icons.Default.Search,
//            contentDescription = "") },
//        placeholder = { Text("Enter keyword here...") },
//        singleLine = true,
//        maxLines = 1,
//        modifier = Modifier.fillMaxWidth(),
//        trailingIcon = {
//            if (showClearIcon) {
//                IconButton(onClick = { query =
//                    TextFieldValue("") }) {
//                    Icon(
//                        imageVector = Icons.Rounded.Clear,
//                        contentDescription = "Clear Icon"
//                    )
//                }
//            }
//        }
//    )
//}


@Preview
@Composable
fun PreviewBookCollectionScreen(){
    BookCollectionScreen()
}