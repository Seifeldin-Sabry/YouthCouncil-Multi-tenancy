import tensorflow as tf
import pandas as pd
from tensorflow.keras.preprocessing.text import Tokenizer
from tensorflow.keras.preprocessing.sequence import pad_sequences
print(tf.__version__)
# Sample dataset
data = [
    ("I love this movie", 1),
    ("This is great", 1),
    ("I dislike it", 0),
    ("Not a good experience", 0)
]

data_pd = pd.read_csv('hateful_sentences.csv', sep=',')

# Separate features (strings) and labels (integers)
# texts = [text for text, _ in data]
# labels = [label for _, label in data]
texts = data_pd['Sentence'].tolist()
labels = data_pd['Label'].tolist()

# Tokenize the text and convert them to sequences
tokenizer = Tokenizer()
tokenizer.fit_on_texts(texts)
sequences = tokenizer.texts_to_sequences(texts)

# Pad sequences to have the same length
max_sequence_length = max(len(seq) for seq in sequences)
print('max sequence is: ',max_sequence_length)
padded_sequences = pad_sequences(sequences, maxlen=max_sequence_length)

# Define the model
model = tf.keras.Sequential([
    tf.keras.layers.Embedding(len(tokenizer.word_index) + 1, 16, input_length=max_sequence_length),
    tf.keras.layers.GlobalAveragePooling1D(),
    tf.keras.layers.Dense(1, activation='sigmoid')
])

# Compile the model
model.compile(loss='binary_crossentropy', optimizer='adam', metrics=['accuracy'])

# Convert labels to numpy array
labels = tf.convert_to_tensor(labels)

# Train the model
model.fit(padded_sequences, labels, epochs=10, batch_size=2)

# Make predictions
test_text = ["I really like this product"]
test_sequence = tokenizer.texts_to_sequences(test_text)
padded_test_sequence = pad_sequences(test_sequence, maxlen=max_sequence_length)
predictions = model.predict(padded_test_sequence)

# Convert predictions to integer labels (0 or 1)
predicted_labels = [int(round(prediction[0])) for prediction in predictions]
print(predicted_labels)

# Train the model
model.fit(padded_sequences, labels, epochs=10, batch_size=2)

# Make predictions
test_text = ["I like green apples"]
test_sequence = tokenizer.texts_to_sequences(test_text)
padded_test_sequence = pad_sequences(test_sequence, maxlen=max_sequence_length)
predictions = model.predict(padded_test_sequence)

# Convert predictions to boolean values
predicted_labels = [bool(round(prediction[0])) for prediction in predictions]
print(predicted_labels)
print('saving...')
model.save('./saved_model')

print('testing...')


test_data = [
    ("This is a great movie", 1),
    ("I didn't like it", 0),
    ("Amazing product", 1),
    ("Terrible experience", 0)
]

# Separate features (strings) and labels (integers)
test_texts = [text for text, _ in test_data]
test_labels = [label for _, label in test_data]

# Tokenize and pad the test sequences
test_sequences = tokenizer.texts_to_sequences(test_texts)
padded_test_sequences = pad_sequences(test_sequences, maxlen=max_sequence_length)

# Convert labels to a TensorFlow tensor
test_labels = tf.convert_to_tensor(test_labels)

# Evaluate the model on the test dataset
loss, accuracy = model.evaluate(padded_test_sequences, test_labels)

print(f"Test Loss: {loss:.4f}")
print(f"Test Accuracy: {accuracy*100:.2f}%")

