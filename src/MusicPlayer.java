
import java.io.BufferedInputStream;
import java.io.File;
//When some kind of I/O exception occurs, this exception is thrown. 

import java.io.FileInputStream;
import java.io.IOException;
//It is a class that specifies a specific data arrangement in the sound stream.

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Control;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
//The exception that caused the operation to fail because the file did not contain valid data that could identify the file type and format. 
import javax.sound.sampled.UnsupportedAudioFileException;


/**
 * Music player implemented according to the underlying API of jdk
 * Only wav is supported, and only one song can be played.
 * It can be played in a loop and stopped at any time (not paused) 
 * @author Ruotong Wang
 */
public class MusicPlayer {
			private File file;// The path of the .wav file 
			private boolean isLoop = false;// Whether to play in loop 
			private boolean isPlaying;// Is it playing 
			private PlayThread playThread;
			
			/**
			 * Constructor. Get file path
			 * @param srcPath The path of file
			 */
			public MusicPlayer(String srcPath) {
				file = new File(srcPath);//relative path 
			}
			
			/**
			 * Play the music
			 */
			public void play() {
				playThread = new PlayThread();
				playThread.start();
			}
			
			/**
			 * End music (not pause) 
			 */
			public void over() {
				isPlaying = false;
				if (playThread != null) {
					playThread = null;
				}
			}
			
			/**
			* Set loop playback
			* @return returns the current object
			*/ 
			public MusicPlayer setLoop(boolean isLoop) {
				this.isLoop = isLoop;
				return this;
			}
		
			/**
			* Asynchronous playback thread
			*/ 
			private class PlayThread extends Thread {
			
				@Override
				public void run() {
					isPlaying = true;
					do {
						SourceDataLine sourceDataLine = null;
						BufferedInputStream bufIn = null;
						AudioInputStream audioIn = null;
						try {
							bufIn = new BufferedInputStream(new FileInputStream(file));//A new object of File type is created here to point to the file 
							audioIn = AudioSystem.getAudioInputStream(bufIn); //can be directly passed in file 
							// Get audio encoding object 
							AudioFormat format = audioIn.getFormat();//Get the audio format of the sound data in this audio input stream. 
							sourceDataLine = AudioSystem.getSourceDataLine(format);//Get the line that matches the description in the specified format object. 
							sourceDataLine.open();//Open the line with the specified format 
							
							sourceDataLine.start();
							/*
							** Read data from the input stream and send it to the mixer 
							*/					
							byte[] buf = new byte[512];//Read a certain number of bytes from the audio input stream and store them in the buffer array buf. 
							int len = -1;//Returns the number of bytes actually read as an integer. 
							while (isPlaying && (len = audioIn.read(buf)) != -1) {//Use while to carry out data transfer operations until the end of the input 	
								sourceDataLine.write(buf, 0, len);//The audio data is written to the mixer through this source data line. 
							}
							
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							
							if (sourceDataLine != null) {//Clear the data buffer and close the input 
								sourceDataLine.drain(); //By continuing the data I/O before clearing the internal buffer of the data row, the queued data in the data row is drained. 
								sourceDataLine.close();//Close the line, indicating that all system resources used by the line can be released. If this operation succeeds, mark the row as closed and assign a CLOSE event to the row's listener. 
							}
							try {
								if (bufIn != null) {
									bufIn.close();
								}
								if (audioIn != null) {
									audioIn.close();
								}
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					} while (isPlaying && isLoop);
				}
			}
}
