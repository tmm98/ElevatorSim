package Elevator.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

class CallButton
{
    void makeCall()
    {
        Thread callThread = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    AudioInputStream inputStream
                        = AudioSystem.getAudioInputStream(getClass().
                        getClassLoader().getResource(
                        "Elevator/util/CallButton.wav"));
                    Clip clip = AudioSystem.getClip();
                    clip.open(inputStream);
                    clip.loop(1);
                    Thread.sleep(1500);
                    clip.stop();
                }
                catch (FileNotFoundException e){}
                catch (IOException error){}
                catch (UnsupportedAudioFileException ex){}
                catch (LineUnavailableException ex){}
                catch (InterruptedException ex){}
            }
        };
        callThread.start();
    }
}
