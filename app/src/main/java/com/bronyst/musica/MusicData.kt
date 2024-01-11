package com.bronyst.musica

data class MusicData(
    val `data`: List<Data>,
    val next: String,
    val total: Int
)