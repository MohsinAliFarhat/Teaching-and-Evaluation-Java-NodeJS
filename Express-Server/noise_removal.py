from scipy.io import wavfile
import noisereduce as nr
import soundfile as sf
from noisereduce.generate_noise import band_limited_noise
from scipy.io.wavfile import write



def noise_removal(audio):

    data, rate = sf.read(audio)
    data = data
    
    noise_len = 2 # seconds
    noise = band_limited_noise(min_freq=2000, max_freq = 12000, samples=len(data), samplerate=rate)*10
    noise_clip = noise[:rate*noise_len]
    audio_clip_band_limited = data+noise
    
    
    noise_reduced = nr.reduce_noise(audio_clip=audio_clip_band_limited, noise_clip=noise_clip, prop_decrease=1.0, verbose=True)
    
    
    write('audio_new.wav', rate, noise_reduced) 