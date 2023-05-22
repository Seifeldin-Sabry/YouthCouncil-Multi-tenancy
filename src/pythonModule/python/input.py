import tensorflow as tf
from tensorflow.keras.preprocessing.text import Tokenizer
from tensorflow.keras.preprocessing.sequence import pad_sequences
import sys

# take input string which contains the sentences
input_string = sys.argv[1]
print('this is the input string: ', input_string)

new_data = [
    input_string
]


model = tf.keras.models.load_model("src\pythonModule\python\saved_model")
tokenizer = Tokenizer()

# tokenize and pad the data
tokenizer.fit_on_texts(new_data)
new_sequences = tokenizer.texts_to_sequences(new_data)
print(new_sequences)
max_sequence_length = 8  # taken from training.py, must be updated when updating model
padded_new_sequences = pad_sequences(new_sequences, maxlen=max_sequence_length)

# predict with the model
predictions = model.predict(padded_new_sequences)

# convert predictions to readable data
print(predictions)
predicted_labels = [int(round(prediction[0])) for prediction in predictions]

# print the readable predictions
for text, label in zip(new_data, predicted_labels):
    print(f"Text: {text} | Predicted Label: {label}")
