package hr.unidu.kz.primjerliste

import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(val ime: String, val tip: String, val slika: Int)