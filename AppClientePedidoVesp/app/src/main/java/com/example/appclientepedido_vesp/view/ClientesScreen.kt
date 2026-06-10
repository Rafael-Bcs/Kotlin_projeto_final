package com.example.appclientepedido_vesp.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appclientepedido_vesp.viewmodel.ClienteViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.text.font.FontWeight


@Composable
fun ClientesScreen(navController: NavController ,viewModel: ClienteViewModel = viewModel()) {

    var nomeInput by remember { mutableStateOf("") }

    var telefoneInput by remember { mutableStateOf("") }

    val isLoading by viewModel.isLoading.collectAsState()

    val clientes by viewModel.clientes.collectAsState()

    var contador = clientes.size

    var clienteEditando by remember { mutableStateOf<Int?>(null) }

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
                    contentDescription = "voltar"
                )
            }

            Text(
                text = "clientes",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            text = "gerenciamento de clientes",
            fontSize = 20.sp,
            modifier = Modifier.padding(horizontal = 15.dp)
        )

        //Formulário para incluir cliente

        OutlinedTextField(
            value = nomeInput,
            onValueChange = { nomeInput = it},
            label = {Text("Nome do Cliente")},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = telefoneInput,
            onValueChange = { telefoneInput = it },
            label = {Text("Telefone")},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = {
                if (nomeInput.isNotBlank() && telefoneInput.isNotBlank()) {

                    if (clienteEditando == null) {
                        contador += 1
                        viewModel.adicionarCliente(
                            contador,
                            nomeInput,
                            telefoneInput
                        )
                    } else {

                        viewModel.alterarCliente(
                            id = clienteEditando!!,
                            idcliente = clienteEditando!!,
                            nome = nomeInput,
                            telefone = telefoneInput
                        )

                        clienteEditando = null
                    }

                    nomeInput = ""
                    telefoneInput = ""
                }
            }
        ) {
            Text(
                if (clienteEditando == null)
                    "Adicionar Cliente"
                else
                    "Salvar Alteração"
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        if (isLoading)
        {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

        }else{
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ){
                items(clientes){
                    clientes  ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    )
                    {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text("${clientes.idCliente}", fontSize = 15.sp)
                                Text(clientes.telefone, fontSize = 15.sp)
                                Text(clientes.nome, fontSize = 15.sp)
                            }
                            IconButton(onClick = {viewModel.excluirCliente(clientes.idCliente)}) {
                                    Icon(
                                        Icons.Default.Delete,

                                        contentDescription = "Excluir cliente"
                                    )
                            }
                            IconButton(
                                onClick = {

                                    clienteEditando = clientes.idCliente

                                    nomeInput = clientes.nome
                                    telefoneInput = clientes.telefone

                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Editar cliente"
                                )
                            }
                        }
                    }
                }

            }
        }


    }
}