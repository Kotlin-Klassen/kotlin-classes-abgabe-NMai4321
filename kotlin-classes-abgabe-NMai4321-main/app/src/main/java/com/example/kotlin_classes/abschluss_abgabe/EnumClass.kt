package com.example.kotlin_classes.abschluss_abgabe

enum class Genre(val description: String){
    FICTION("Fictional Stories"),
    NON_FICTION("Real Stories"),
    SCIENCE("Books about Science"),
    HISTORY("Books about History"),
    CHILDREN("Books for Children");

    fun printDescription() {
        println(description)
    }
}