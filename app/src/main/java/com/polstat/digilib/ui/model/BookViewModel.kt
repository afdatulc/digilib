package com.polstat.digilib.ui.screen

import Book
import android.util.Log
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BookViewModel : ViewModel() {
    private val _originalBookList = MutableLiveData<List<Book>>() // Simpan daftar buku asli
    private val _bookList = MutableLiveData<List<Book>>()
    val bookList: LiveData<List<Book>> get() = _bookList

    // Fungsi untuk memperbarui data buku
    fun updateBookList(newBookList: List<Book>) {
        _originalBookList.value = newBookList // Simpan daftar buku asli
        if (_bookList.value.isNullOrEmpty()) {
            _bookList.value = newBookList // Inisialisasi _bookList jika kosong
        }

    }

    // Fungsi untuk memfilter dan memperbarui daftar buku berdasarkan kata kunci
    fun filterBooks(keyword: String) {
        val originalList = _originalBookList.value ?: emptyList()

        val filteredList = if (keyword.isNotEmpty()) {
            originalList.filter { book ->
                book.title.lowercase().contains(keyword.lowercase())
            }
        } else {
            originalList
        }
        _bookList.value = filteredList
    }

    private val _query = MutableLiveData<TextFieldValue>(TextFieldValue(""))
    val query: LiveData<TextFieldValue> get() = _query

    // Fungsi untuk mengubah nilai query
    fun updateQuery(newQuery: TextFieldValue) {
        _query.value = newQuery
    }

}