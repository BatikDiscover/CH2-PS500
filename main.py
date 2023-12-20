import os
from google.cloud import storage
import tensorflow as tf
from io import BytesIO
from flask import Flask, request, jsonify
from keras.models import load_model
import numpy as np
from tensorflow.keras.applications.mobilenet import preprocess_input

app = Flask(__name__)
os.environ['GOOGLE_APPLICATION_CREDENTIALS'] = 'batik-discover-sa.json'
storage_client = storage.Client()

def req(y_true, y_pred):
    req = tf.metrics.req(y_true, y_pred)[1]
    tf.keras.backend.get_session().run(tf.local_variables_initializer())
    return req

model = load_model('model.h5', custom_objects={'req': req})

@app.route('/predicts', methods=['POST'])
def predict_batik():
    if 'file' not in request.files:
        respond = jsonify({'message': 'No file part'})
        respond.status_code = 400
        return respond

    file = request.files['file']

    if file.filename == '':
        respond = jsonify({'message': 'No selected file'})
        respond.status_code = 400
        return respond

    try:
        image_bucket = storage_client.get_bucket('batik-discover-bucket')
        img_blob = image_bucket.blob('predict_uploads/' + file.filename)
        img_blob.upload_from_file(file)
        img_path = BytesIO(img_blob.download_as_bytes())
    except Exception:
        respond = jsonify({'message': 'Error loading image file'})
        respond.status_code = 400
        return respond

    img = tf.keras.utils.load_img(img_path, target_size=(224, 224))
    x = tf.keras.utils.img_to_array(img)
    x = np.expand_dims(x, axis=0)
    x = preprocess_input(x)
    images = np.vstack([x])

    # model predict
    pred_batik = model.predict(images)
    # find the max prediction of the image
    max_prediction = pred_batik.max()

    batik = ['Celup', 'Insang', 'Kawung', 'Megamendung', 'Parang', 'Poleng', 'Truntum']

    # for respond output from prediction if predict <=0.4
    if max_prediction <= 0.75:
        respond = jsonify({'message': 'Batik tidak terdeteksi'})
        respond.status_code = 400
        return respond

    result = {
        "nama": batik[np.argmax(pred_batik)],
    }

    respond = jsonify(result)
    respond.status_code = 200
    return respond

if __name__ == '__main__':
    app.run(debug=True, port=8000)
