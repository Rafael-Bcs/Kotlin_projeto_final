package com.example.appclientepedido_vesp.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appclientepedido_vesp.viewmodel.PedidoViewModel

@Composable
fun PedidosScreen(
    navController: NavController,
    viewModel: PedidoViewModel = viewModel()
) {

    val pedidos by viewModel.pedidos.collectAsState()

    val isLoading by viewModel.isLoading.collectAsState()

    Column {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 28.dp, bottom = 28.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Voltar"
                )
            }

            Text(
                text = "Pedidos",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            text = "Lista de Pedidos",
            fontSize = 20.sp,
            modifier = Modifier.padding(horizontal = 15.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        if (isLoading) {

            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

        } else {

            LazyColumn(
                modifier = Modifier.padding(horizontal = 15.dp)
            ) {

                items(pedidos) { pedido ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 2.dp
                        )
                    ) {

                        Column(
                            modifier = Modifier.padding(15.dp)
                        ) {

                            Text(
                                text = "Pedido #${pedido.idPedido}",
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "Data: ${pedido.dataPedido}"
                            )

                            Text(
                                text = "Valor Total: R$ ${pedido.valorTotal}"
                            )

                            Text(
                                text = "Cliente ID: ${pedido.idCliente}"
                            )
                        }
                    }
                }
            }
        }
    }
}