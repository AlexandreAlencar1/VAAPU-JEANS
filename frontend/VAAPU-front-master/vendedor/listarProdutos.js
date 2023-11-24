function listarProdutos() {
    fetch('http://localhost:8080/produtos')
        .then(response => response.json())
        .then(resultado => exibirProdutos(resultado))
        .catch(error => alert('Erro ao listar produtos. ' + error.message));
}

function exibirProdutos(produtos) {
    var resultadoDiv = document.getElementById('resultado');
    resultadoDiv.innerHTML = '<h2>Produtos:</h2>';
    for (var i = 0; i < produtos.length; i++) {
        var produto = produtos[i];
        resultadoDiv.innerHTML += '<p>ID: ' + produto.id + ', Nome: ' + produto.nome + ', Tamanho: ' + produto.tamanho + ', Quantidade: ' + produto.quantidade + ', Categoria: ' + produto.categoria + ', Preço: ' + produto.preco + ', Imagem: ' + produto.imagem + '</p>';
    }
}