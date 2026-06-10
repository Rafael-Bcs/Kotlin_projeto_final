package com.example.appclientepedido_vesp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appclientepedido_vesp.model.Pedido
import com.example.appclientepedido_vesp.service.RetrofitAppApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PedidoViewModel : ViewModel() {

    val api = RetrofitAppApi.apiService

    private val _pedidos = MutableStateFlow<List<Pedido>>(emptyList())
    val pedidos: StateFlow<List<Pedido>> = _pedidos

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        carregarPedidos()
    }

    fun carregarPedidos(){
        viewModelScope.launch {
            try {
                _pedidos.value = api.getPedidos()
            } catch (e: Exception){
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

}