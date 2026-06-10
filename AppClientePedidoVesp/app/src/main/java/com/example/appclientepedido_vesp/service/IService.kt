package com.example.appclientepedido_vesp.service

import com.example.appclientepedido_vesp.model.Cliente
import com.example.appclientepedido_vesp.model.Pedido
import retrofit2.http.*


interface IService {

    @GET("clientes")
    suspend fun getClientes(): List<Cliente>

    @GET("clientes/{id}")
    suspend fun getClientePorId(id: Int): Cliente

    @POST("clientes")
    suspend fun criarCliente(@Body cliente: Cliente): Cliente

    @PUT("clientes/{id}")
    suspend fun atualizarCliente(@Path("id") id: Int, @Body cliente: Cliente): Cliente

    @DELETE("clientes/{id}")
    suspend fun deletarCliente(@Path("id") id: Int)


    @GET("pedidos")
    suspend fun getPedidos(): List<Pedido>

    @GET("pedidos/{id}")
    suspend fun getPedidosPorId(id: Int): Pedido

    @POST("pedidos")
    suspend fun criarPedido(@Body pedido: Pedido): Pedido

    @PUT("pedidos/{id}")
    suspend fun atualizarPedido(@Path("id") id: Int, @Body pedido: Pedido): Pedido

    @DELETE("pedidos/{id}")
    suspend fun deletarPedido(@Path("id") id: Int)



}