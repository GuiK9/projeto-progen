from flask import Flask, jsonify, request

app = Flask(__name__)

livros = [
    {
        'id': 1,
        'titulo':'O senhor dos anéis - A sociedade do anel',
        'autor': 'J.R.R Tolkien'
    },
    {
        'id': 2 ,
        'título': 'Harry Potter e a pedra filosofal',
        'autor': 'J.K Howlling'
    },
    {
        'id': 3,
        'título':'Hábitos Atômicos',
        'autor': 'james clear'
    }
]


# Consultar (todos)
@app.route('/livros',methods=['GET'])
def obter_livros():
    return jsonify(livros)

#consultar id
@app.route('/livros/<int:id>',methods=['GET'])
def obter_livro_por_id(id):
    for livro in livros:
        if livro.get('id') == id:
            return jsonify(livro)
# Editar
@app.route('/livros/<int:id>',methods=['PUT'])
def editar_livro_por_id(id):
    livro_alterado = request.get_json()
    for indice,livro in enumerate(livros):
        if livro.get('id') == id:
            livros[indice].update(livro_alterado)
            return jsonify(livros[indice])

# Criar 
@app.route('/livros',methods=['POST'])
def incluir_novo_livro():
    novo_livro = request.get_json()
    livros.append(novo_livro)
    return jsonify(livros)

# Deletar 
@app.route('/livros/<int:id>',methods=['DELETE'])
def deletar_livros(id):
    for livro in livros:
        if livro.get('id') == id:
            livros.remove(livro)
    return jsonify(livros)

app.run(port=5000, host='localhost', debug=True)