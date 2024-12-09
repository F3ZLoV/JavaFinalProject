/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import nl.captcha.Captcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.text.producer.DefaultTextProducer;
import nl.captcha.text.renderer.DefaultWordRenderer;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
/**
 *
 * @author watmx
 */


public class CaptchaGenerator {
    private Captcha captcha;
    public Captcha getCaptcha() {
        return captcha;
    }
    public ImageIcon generateCaptcha() {
        // CAPTCHA 생성
        captcha = new Captcha.Builder(200, 50).addText(new DefaultTextProducer()).addBackground(new GradiatedBackgroundProducer()).gimp().build();

        BufferedImage image = captcha.getImage();
        return new ImageIcon(image);
    }

    public boolean validateCaptcha(String userInput) {
        return captcha != null && captcha.isCorrect(userInput);
    }
}

