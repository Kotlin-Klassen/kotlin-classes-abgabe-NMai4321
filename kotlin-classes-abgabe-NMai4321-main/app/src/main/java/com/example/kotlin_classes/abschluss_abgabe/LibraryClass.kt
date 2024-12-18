package com.example.kotlin_classes.abschluss_abgabe

class LibraryClass {
    private val books = mutableListOf<Book>()

    class LibraryAddress(
        private val street: String,
        private val city: String,
        private val zipCode: String
    ) {
        fun printAddress() {
            println("Library Address: $street, $city, $zipCode")
        }
    }

    inner class LibraryMember(val name: String, val memberID: String) {
        fun checkoutBook(book: Book, dueDate: String) {
            if (book.status is BookStatus.Available) {
                book.status = BookStatus.CheckedOut(dueDate)
                println("$name checked out '${book.title}'.")
            } else {
                println("Cannot checkout: '${book.title}'. It is currently ${book.status.status()}.")
            }
        }

        fun reserveBook(book: Book) {
            if (book.status is BookStatus.Available) {
                book.status = BookStatus.Reserved(name)
                println("$name reserved '${book.title}'.")
            } else {
                println("Cannot reserve: '${book.title}'. It is currently ${book.status.status()}.")
            }
        }
    }

    fun addBook(book: Book) {
        books.add(book)
        println("Added book: '${book.title}' by ${book.author}.")
    }

    fun searchBook(title: String? = null, author: String? = null): List<Book> {
        return books.filter {
            (title == null || it.title.equals(title, ignoreCase = true)) &&
                    (author == null || it.author.equals(author, ignoreCase = true))
        }
    }

    fun displayBookStatuses() {
        books.forEach { book ->
            println("'${book.title}' by ${book.author}: ${book.status.status()}")
        }
    }
}

fun main() {
    println()
    val library = LibraryClass()

    val address = LibraryClass.LibraryAddress("Berlin Str 123", "Berlin", "46293")
    address.printAddress()

    println()
    val book1 = Book("Harry Potter", "Joanne K. Rowling", Genre.FICTION, BookStatus.Available)
    val book2 = Book("A Brief History of Time", "Stephen Hawking", Genre.SCIENCE, BookStatus.Available)
    val book3 = Book("Der kleine Prinz", "Antoine de Saint-Exupéry", Genre.CHILDREN, BookStatus.Available)
    library.addBook(book1)
    library.addBook(book2)
    library.addBook(book3)

    println()
    val member = library.LibraryMember("Tim", "01")

    val bookToCheckout = library.searchBook(title = "Harry Potter").firstOrNull()
    if (bookToCheckout != null) {
        member.checkoutBook(bookToCheckout, "2024-12-22")
    }

    val bookToReserve = library.searchBook(title = "Der kleine Prinz").firstOrNull()
    if (bookToReserve != null) {
        member.reserveBook(bookToReserve)
    }

    println("\nCurrent Book Statuses:")
    library.displayBookStatuses()
}