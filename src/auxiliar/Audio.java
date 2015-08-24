/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar;

import java.io.InputStream;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio extends Thread {

    private String l_nomeArquivo;

    private Position l_posicaoCorrente;

    private final int EXTERNAL_BUFFER_SIZE = 524288; // 128Kb

    enum Position {
        LEFT, RIGHT, NORMAL
    };

    public Audio(String l_arquivoWav) {

        l_nomeArquivo = l_arquivoWav;

        l_posicaoCorrente = Position.NORMAL;
    }

    @Override
    public void run() {

        InputStream in = null;
        try{
            //in = new FileInputStream(getClass().getResource("/audio/"+l_nomeArquivo).getPath());
            in = this.getClass().getResourceAsStream("/audio/"+l_nomeArquivo);
        }catch(Exception e){
            System.err.println("Wave file not found("+l_nomeArquivo+").\nErro: "+e);
            return;
        }

        AudioInputStream audioInputStream = null;
            try {
                audioInputStream = AudioSystem.getAudioInputStream(in);
            } catch (UnsupportedAudioFileException e1) {
                e1.printStackTrace();
                return;
            } catch (IOException e1) {
                e1.printStackTrace();
                return;
            }

        AudioFormat format = audioInputStream.getFormat();
        SourceDataLine auline = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

            try {
                auline = (SourceDataLine) AudioSystem.getLine(info);
                auline.open(format);
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            return;
            } catch (Exception e) {
                e.printStackTrace();
            return;
            }

        if (auline.isControlSupported(FloatControl.Type.PAN)) {
            FloatControl pan = (FloatControl) auline.getControl(FloatControl.Type.PAN);
        if (l_posicaoCorrente == Position.RIGHT)
            pan.setValue(1.0f);
        else if (l_posicaoCorrente == Position.LEFT)
            pan.setValue(-1.0f);
        }

        auline.start();
        int nBytesRead = 0;
        byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];

            try {
            while (nBytesRead != -1) {
                nBytesRead = audioInputStream.read(abData, 0, abData.length);
            if (nBytesRead >= 0)
                auline.write(abData, 0, nBytesRead);
            }
            } catch (IOException e) {
                e.printStackTrace();
            return;
            }
        finally{
            auline.drain();
            auline.close();
        }
    }
}
