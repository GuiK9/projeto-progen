from flask import Flask, request
from dotenv import load_dotenv
import os
import psycopg2

load_dotenv()

app = Flask(__name__)
db_params = {
            "database": os.getenv("DATABASE_NAME"),
            "host": os.getenv("HOST_DB"),
            "user": os.getenv("USER_NAME"),
            "password": os.getenv("PASSWORD_USER"),
            "port": os.getenv("PORT_DB")
            }
connection = psycopg2.connect(**db_params)


# String Queries
INSERT_PRODUCT = """INSERT INTO produtos (descricao, marca, preco_custo, preco_venda, codigo_barras, embalagem)
VALUES (%s, %s, %s, %s, %s, %s) RETURNING id;"""

GET_ALL_PRODUCTS = "SELECT * FROM produtos"

UPDATE_WITH_ID = """UPDATE produtos SET descricao = %s, marca = %s, preco_custo = %s, preco_venda = %s, codigo_barras = %s, embalagem = %s WHERE id = %s RETURNING id;"""

DELETE_WITH_ID = """DELETE FROM produtos WHERE id = %s RETURNING id"""

@app.get("/")
def get_all():
    products_table = []
    with connection:
        with connection.cursor() as cursor:
            cursor.execute(GET_ALL_PRODUCTS)
            rows = cursor.fetchall()
            for row in rows:
                products_table.append({
                    "Descrição": row[1],
                    "Marca": row[2],
                    "Preço de custo": row[3],
                    "Preço de venda": row[4],
                    "Codigo de Barras": row[5],
                    "Embalagem": row[6],
                    "id": row[0]
                })            
    return products_table

@app.post("/produto")
def create_product():
    product_json = request.get_json()
    attributes = tuple(product_json.values())
    try:
        with connection:
            with connection.cursor() as cursor:
                cursor.execute(INSERT_PRODUCT, attributes)
                product_id = cursor.fetchone()[0]
                connection.commit()
                return {"id": product_id, "message": "Produto foi criado"}
    except Exception as e:
        print(e)

@app.put("/produto/<int:id>")
def update_product(id):
    product_json = request.get_json()
    attributes = tuple(list(product_json.values()) + [id])
    try:
        with connection:
            with connection.cursor() as cursor:
                cursor.execute(UPDATE_WITH_ID, attributes)
                connection.commit()
                product_id = cursor.fetchone()[0]
                return {"id": product_id, "message": "produto foi atulizado"}
    except Exception as e:
        print(e)            

@app.delete("/produto/<int:id>")
def delete_product(id):
    try:
        with connection:
            with connection.cursor() as cursor:
                cursor.execute(DELETE_WITH_ID, (id,))
                connection.commit()
                product_id = cursor.fetchone()[0]
                return{"id": product_id, "message": "produto deletado"}
    except Exception as e:
        print(e)