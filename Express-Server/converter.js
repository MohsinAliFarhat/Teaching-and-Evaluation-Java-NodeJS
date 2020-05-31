const ffmpegPath = require('@ffmpeg-installer/ffmpeg').path;
const ffmpeg = require('fluent-ffmpeg');
ffmpeg.setFfmpegPath(ffmpegPath);

ffmpeg()
  .input('python-scripts/audio.mp4')
  .output('python-scripts/out.wav')
  .run();
