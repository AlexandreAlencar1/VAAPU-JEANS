function adicionarProduto() {
    var nome = document.getElementById('addItemForm').elements.nome.value;
    var tamanho = document.getElementById('addItemForm').elements.tamanho.value;
    var quantidade = document.getElementById('addItemForm').elements.quantidade.value;
    var categoria = document.getElementById('addItemForm').elements.categoria.value;
    var preco = document.getElementById('addItemForm').elements.preco.value;
    var imagem = document.getElementById('addItemForm').elements.imagem.value;

    var data = {
        nome: nome,
        tamanho: tamanho,
        quantidade: quantidade,
        categoria: categoria,
        preco: preco,
        imagem: imagem
    };

    fetch('http://localhost:8080/produto', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data),
        mode: 'no-cors' 
    })
        .then(response => {
            // A resposta não estará disponível para leitura no JavaScript devido ao modo 'no-cors'
            console.log('Requisição enviada com sucesso (sem erro de CORS)');
            // Chama a função para listar os produtos após adicionar um novo produto
        })
        .catch(error => {
            console.error('Erro durante a requisição POST:', error);
        });
}


