"""
Created on Fri Feb  7 18:13:29 2020
@author: MOHSIN
"""
from pydub import AudioSegment
from scipy.io.wavfile import write
import sounddevice as sd
from keras.utils import to_categorical
from keras.models import Sequential
from keras.layers import Dense, Dropout, Activation, Flatten
from keras.layers import Convolution2D, Conv2D, MaxPooling2D, GlobalAveragePooling2D
from keras.optimizers import Adam
from keras.utils import np_utils
import librosa
import librosa.display
import numpy as np
#from noise_removal import noise_removal
#from wav_file_props import change_channels



def extract_features(file_name):

    try:
        audio, sample_rate = librosa.load(file_name, res_type='kaiser_fast') 
        mfccs = librosa.feature.mfcc(y=audio, sr=sample_rate, n_mfcc=40)
        mfccsscaled = np.mean(mfccs.T,axis=0)
        
    except Exception as ex:
        print("Error encountered while parsing file: ", file_name)
        return None 
     
    return mfccsscaled


def detect_leading_silence(sound, silence_threshold=-25, chunk_size=10):

        '''
        sound is a pydub.AudioSegment
        silence_threshold in dB
        chunk_size in ms
        iterate over chunks until you find the first one with sound
        '''
        trim_ms = 0  # ms
        while sound[trim_ms:trim_ms+chunk_size].dBFS < silence_threshold:
            trim_ms += chunk_size
    
        return trim_ms


def noise_and_silence_removal():
        
#        change_channels()#change the channel to 1
        
        sound = AudioSegment.from_file("F:\\fyp-server\\python-scripts\\mono.wav", format="wav")
        
        sd.wait()
        start_trim = detect_leading_silence(sound)
        end_trim = detect_leading_silence(sound.reverse())
        
        duration = len(sound)
        trimmed_sound = sound[start_trim:duration-end_trim]
        
        trimmed_sound.export("F:\\fyp-server\\python-scripts\\audio_new.wav", format="wav")
        
#        noise_removal("audio_new.wav")
        
        
#        Prediction 
                
        model.load_weights("F:\\fyp-server\\python-scripts\\diacritics_models\\segmented_diacritics_3.h5")
        test_file = extract_features('F:\\fyp-server\\python-scripts\\audio_new.wav')
        
        test_file = np.array(test_file)
        
        test_file = test_file.reshape(1,8,5,1)
        
        answer = model.predict(test_file)
        array = answer[0]
        greatest_probability = max(array)
        
#        print(answer)
      #  print(np.where(array == greatest_probability))
        cat = str(np.where(array == greatest_probability))[8]
        
        if(cat=="0"):
            print("jaa")
        elif(cat=="1"):
            print("ji")
        elif(cat=="2"):
            print("ju")
        else:
            print("Noise")
        
#        print(print("Index = "+str(np.where(array == greatest_probability))[8]))




num_rows = 8
num_columns = 5
num_channels = 1

model = Sequential()
model.add(Conv2D(filters=16, kernel_size=2, input_shape=(num_rows, num_columns, num_channels), activation='relu'))
model.add(MaxPooling2D(pool_size=1))
model.add(Dropout(0.2))

model.add(Conv2D(filters=32, kernel_size=2, activation='relu'))
model.add(MaxPooling2D(pool_size=1))
model.add(Dropout(0.2))


model.add(Conv2D(filters=128, kernel_size=2, activation='relu'))
model.add(MaxPooling2D(pool_size=2))
model.add(Dropout(0.2))


model.add(GlobalAveragePooling2D())
model.add(Dense(3, activation='softmax'))

noise_and_silence_removal()



