from flask import Flask, request, jsonify
import tensorflow as tf
import numpy as np
from tensorflow.keras.preprocessing.sequence import pad_sequences
import pickle
import mysql.connector

# Config database
dbName = "student_project"
host = "localhost"
username = "spring"
password = "spring"

# Kết nối đến MySQL
conn = mysql.connector.connect(
    host=host,
    user=username,
    password=password,
    database=dbName,
    port=3307
)
cursor = conn.cursor()

app = Flask(__name__)

# Load model và tokenizer
model = tf.keras.models.load_model('model.h5')

with open('tokenizer.pickle', 'rb') as handle:
    tokenizer = pickle.load(handle)

# Hàm tiền xử lý văn bản
def get_sequences(tokenizer, comments):
    max_length = 80
    sequences = tokenizer.texts_to_sequences(comments)
    padded = pad_sequences(sequences, truncating='post', padding='post', maxlen=max_length)
    return np.array(padded, dtype=np.float32)

# Định nghĩa các lớp
classes = {'negative': 0, 'positive': 1, 'neutral': 2}
ids_to_classes = {v: k for k, v in classes.items()}

@app.route('/predict_feedback', methods=['POST'])
def predict_feedback():
    data = request.json
    feedback_text = data['comment']
    student_id = data['student_id']
    teacher_id = data['schoolstaff']

    # Check if teacher ID exists in school_staff table
    cursor.execute("SELECT id FROM school_staff WHERE id = %s", (teacher_id,))
    if cursor.fetchone() is None:
        return jsonify({'error': f"Teacher ID {teacher_id} not found in school_staff"}), 400

    # Tiền xử lý văn bản
    text_sequences = get_sequences(tokenizer, [feedback_text])

    # Dự đoán với mô hình
    prediction = model.predict(text_sequences)

    # Lấy nhãn của dự đoán
    label = prediction.argmax(axis=1)[0]
    feedback_label = ids_to_classes[label]  # Chuyển đổi id thành tên lớp

    # Lưu feedback và nhãn vào database
    insert_query = """
        INSERT INTO feedback (student_id, schoolstaff, comment, label)
        VALUES (%s, %s, %s, %s)
    """
    cursor.execute(insert_query, (student_id, teacher_id, feedback_text, feedback_label))
    conn.commit()

    return jsonify({'label': feedback_label})

if __name__ == '__main__':
    app.run(debug=True, port=5001)
