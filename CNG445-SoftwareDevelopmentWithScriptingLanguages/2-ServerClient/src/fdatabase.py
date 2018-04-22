import sqlite3


class FDatabase:
    def __init__(self):
        self.lastrowid = -1
        self.conn = self.connect_question_db()
        self.create_question_table()

    # ------> Table Operations
    def create_question_table(self):
        q = '''
        CREATE TABLE question(
            id INTEGER ,
            description TEXT NOT NULL ,
            isActive INTEGER DEFAULT 1, 
            PRIMARY KEY (id)
        )
        '''
        try:
            self.exec(q)
        except:
            return

        self.create_testcase_table()

    def create_testcase_table(self):
        q = '''
        CREATE TABLE testcase(
            q_id INTEGER ,
            input TEXT NOT NULL ,
            output TEXT NOT NULL ,
            PRIMARY KEY (q_id, input) ,
            FOREIGN KEY (q_id) REFERENCES question(id)
        )
        '''
        self.exec(q)

    def insert_question(self, description, isActive=1):
        q = '''
        INSERT INTO question(
          description,
          isActive
        ) VALUES ('%s',%s)
        ''' % (description, isActive)
        self.exec(q)
        return self.lastrowid

    def update_question_description(self, id, new_description):
        q = 'UPDATE question SET description = "%s" WHERE id = %s' % (new_description, id)
        self.exec(q)

    def update_question_isActive(self, id, new_isActive):

        q = 'UPDATE question SET isActive = %s WHERE id = %s' % (new_isActive, id)
        self.exec(q)

    def update_question(self, id, description="", isActive=""):
        if description != "": self.update_question_description(id, description)
        if isActive != "": self.update_question_isActive(id, isActive)

    def insert_testcase(self, output=str(), input=str(), q_id=None):
        if q_id == None: q_id = self.lastrowid
        q = '''
        INSERT INTO testcase(
          q_id,
          input,
          output
        ) VALUES (%s,'%s','%s')
        ''' % (q_id, input, output)

        self.exec(q)

    def is_question_exists(self, id):
        q = 'SELECT * FROM question WHERE id = %s' % id

        if len(list(self.exec(q))):
            return True

        return False

    def is_testcase_exists(self, q_id, input):
        q = 'SELECT * FROM testcase WHERE q_id = %s AND input = "%s"' % (q_id, input)

        if len(list(self.exec(q))):
            return True

        return False

    def update_testcase_output(self, q_id, old_input, new_output):
        q = 'UPDATE testcase SET output = "%s" WHERE q_id = %s AND input = "%s"' % (new_output, q_id, old_input)
        self.exec(q)

    def update_testcase_input(self, q_id, old_input, new_input):
        q = 'UPDATE testcase SET input = "%s" WHERE q_id = %s AND input = "%s"' % (new_input, q_id, old_input)
        self.exec(q)

    def update_testcase(self, q_id, old_input, new_input="", new_output=""):
        if new_input != "": self.update_testcase_input(q_id, old_input, new_input)
        if new_output != "": self.update_testcase_output(q_id, old_input, new_output)

    def delete_testcase(self, q_id, input):
        q = 'DELETE FROM testcase WHERE q_id = %s AND input = "%s"' % (q_id, input)
        self.exec(q)

    def get_testcases(self, q_id):
        q = 'SELECT * FROM testcase WHERE q_id = %s' % (q_id)
        return list(self.exec(q))

    def print_questions(self):
        for row in self.exec('SELECT * FROM question'):
            print(row)

    def is_output_true(self, q_id, output):
        q = 'SELECT * FROM testcase WHERE q_id = %s AND output = "%s"' % (q_id, output)

        if len(list(self.exec(q))):
            return True

        return False

    def get_questions(self):
        q = 'SELECT * FROM question WHERE isActive = 1'
        return list(self.exec(q))

    def get_all_testcases(self):
        q = 'SELECT * FROM testcase'
        return list(self.exec(q))

    # ------> Other Operations
    def exec(self, q):
        c = self.conn.cursor()
        data = c.execute(q)
        self.lastrowid = c.lastrowid
        self.conn.commit()
        return data

    def connect_question_db(self):
        return self.connect_db("fquestion.db")

    def connect_db(self, name):
        conn = sqlite3.connect(name)
        return conn


if __name__ == "__main__":
    db = FDatabase()
    print(db.get_questions())
