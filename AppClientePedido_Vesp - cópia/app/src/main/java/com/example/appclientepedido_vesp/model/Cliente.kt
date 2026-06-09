package com.example.appclientepedido_vesp.model

import com.google.gson.annotations.SerializedName

data class Cliente(

    @SerializedName("idCliente")
    val idCliente: Int,

    @SerializedName("nome")
    val nome: String,

    @SerializedName("telefone")
    val telefone: String
)
