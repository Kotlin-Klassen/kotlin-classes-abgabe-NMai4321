package com.example.kotlin_classes.abschluss_abgabe

sealed class BookStatus {

    abstract fun status(): String

    object Available : BookStatus() {
        override fun status() = "The book is available"
    }
    data class CheckedOut(val dueDate: String) : BookStatus() {
        override fun status() = "The book is checked out. It will be due at $dueDate"
    }
    data class Reserved(val reservedBy: String) : BookStatus() {
        override fun status() = "The book is reserved by $reservedBy"
    }

    fun printBookStatus(bookStatus: BookStatus) {
        when (bookStatus) {
            is Available -> println("Das Buch ist verfügbar.\n")
            is CheckedOut -> println("Das Buch ist ausgeliehen, mit einem Rückgabedatum von: ${bookStatus.dueDate} Stunden. \n")
            is Reserved -> println("Das Buch ist reserviert von:${bookStatus.reservedBy} \n")
        }
    }
}