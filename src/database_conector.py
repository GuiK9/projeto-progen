import psycopg2 as db
import dotenv
import os

dotenv.load_dotenv(dotenv.find_dotenv())

class Config:
    def __init__(self):
        self.config = {
            "postgres": {
            "database": os.getenv("DATABASE_NAME"),
            "host": os.getenv("HOST_DB"),
            "user": os.getenv("USER_NAME"),
            "password": os.getenv("PASSWORD_USER"),
            "port": os.getenv("PORT_DB")
            }
        }

class Connection(Config):
    def __init__(self):
        Config.__init__(self)
        try:
            self.conn = db.connect(**self.config["postgres"])
            self.cur = self.conn.cursor()
        except Exception as e:
            print("erro na conexção", e)

    def __enter__(self):
        return self

    def __exit__(self, exc_type, exc_val, exc_tb):
        self.commit()
        self.connection.close()

    @property
    def connection(self):
        return self.conn

    @property 
    def cursor(self):
        return self.cur

    def commit(self):
        self.connection.commit()
    