package com.dsplugin.gitcelebration;

import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Componente che mostra la celebrazione full screen con dissolvenza
 */
public class CelebrationDisplay {
    
    private final Project project;
    private final CelebrationConfig config;
    
    public CelebrationDisplay(@NotNull Project project) {
        this.project = project;
        this.config = CelebrationConfig.getInstance(project);
    }
    
    /**
     * Mostra la celebrazione con immagine e suono
     */
    public void showCelebration(@NotNull String imagePath, @NotNull String soundPath, 
                               @NotNull String actionType) {
        // Carica e mostra l'immagine
        SwingUtilities.invokeLater(() -> {
            try {
                // Carica immagine
                BufferedImage image = loadImage(imagePath);
                
                // Crea finestra full screen
                JFrame frame = createFullScreenFrame(image);
                
                // Riproduci suono
                playSound(soundPath);
                
                // Mostra con dissolvenza in entrata
                fadeIn(frame);
                
                // Aspetta la durata configurata
                Timer timer = new Timer(config.getDisplayDuration(), e -> {
                    // Dissolvenza in uscita
                    fadeOut(frame, () -> {
                        frame.dispose();
                    });
                });
                timer.setRepeats(false);
                timer.start();
                
            } catch (Exception e) {
                // In caso di errore, mostra un messaggio semplice
                showSimpleMessage(actionType);
            }
        });
    }
    
    private BufferedImage loadImage(@NotNull String imagePath) throws IOException {
        // Prova prima come risorsa del plugin
        InputStream resourceStream = getClass().getResourceAsStream(imagePath);
        if (resourceStream != null) {
            return ImageIO.read(resourceStream);
        }
        
        // Altrimenti prova come file system
        File file = new File(imagePath);
        if (file.exists()) {
            return ImageIO.read(file);
        }
        
        // Se non trovata, crea un'immagine di default
        return createDefaultImage();
    }
    
    private BufferedImage createDefaultImage() {
        BufferedImage img = new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1920, 1080);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 72));
        String text = "Git Celebration!";
        FontMetrics fm = g.getFontMetrics();
        int x = (1920 - fm.stringWidth(text)) / 2;
        int y = 540;
        g.drawString(text, x, y);
        g.dispose();
        return img;
    }
    
    private JFrame createFullScreenFrame(@NotNull BufferedImage image) {
        JFrame frame = new JFrame();
        frame.setUndecorated(true);
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Imposta full screen
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        
        if (gd.isFullScreenSupported()) {
            gd.setFullScreenWindow(frame);
        } else {
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);
        }
        
        // Crea pannello con immagine
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
                                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, 
                                    RenderingHints.VALUE_RENDER_QUALITY);
                
                Dimension size = getSize();
                int imgWidth = image.getWidth();
                int imgHeight = image.getHeight();
                
                // Scala l'immagine per riempire lo schermo mantenendo le proporzioni
                double scaleX = size.getWidth() / imgWidth;
                double scaleY = size.getHeight() / imgHeight;
                double scale = Math.max(scaleX, scaleY);
                
                int scaledWidth = (int) (imgWidth * scale);
                int scaledHeight = (int) (imgHeight * scale);
                int x = (size.width - scaledWidth) / 2;
                int y = (size.height - scaledHeight) / 2;
                
                g2d.drawImage(image, x, y, scaledWidth, scaledHeight, null);
            }
        };
        
        panel.setBackground(Color.BLACK);
        frame.add(panel);
        frame.setOpacity(0.0f); // Inizia trasparente per la dissolvenza
        
        return frame;
    }
    
    private void fadeIn(@NotNull JFrame frame) {
        Timer fadeTimer = new Timer(16, null); // ~60 FPS
        fadeTimer.addActionListener(new ActionListener() {
            float opacity = 0.0f;
            final float targetOpacity = 1.0f;
            final float increment = 1.0f / (config.getFadeInDuration() / 16.0f);
            
            @Override
            public void actionPerformed(ActionEvent e) {
                opacity += increment;
                if (opacity >= targetOpacity) {
                    opacity = targetOpacity;
                    fadeTimer.stop();
                }
                frame.setOpacity(opacity);
            }
        });
        fadeTimer.start();
    }
    
    private void fadeOut(@NotNull JFrame frame, @NotNull Runnable onComplete) {
        Timer fadeTimer = new Timer(16, null); // ~60 FPS
        fadeTimer.addActionListener(new ActionListener() {
            float opacity = 1.0f;
            final float decrement = 1.0f / (config.getFadeOutDuration() / 16.0f);
            
            @Override
            public void actionPerformed(ActionEvent e) {
                opacity -= decrement;
                if (opacity <= 0.0f) {
                    opacity = 0.0f;
                    fadeTimer.stop();
                    onComplete.run();
                }
                frame.setOpacity(opacity);
            }
        });
        fadeTimer.start();
    }
    
    private void playSound(@NotNull String soundPath) {
        try {
            // Prova prima come risorsa del plugin
            InputStream resourceStream = getClass().getResourceAsStream(soundPath);
            AudioInputStream audioStream;
            
            if (resourceStream != null) {
                audioStream = AudioSystem.getAudioInputStream(resourceStream);
            } else {
                // Altrimenti prova come file system
                File soundFile = new File(soundPath);
                if (soundFile.exists()) {
                    audioStream = AudioSystem.getAudioInputStream(soundFile);
                } else {
                    // Suono di default (beep)
                    Toolkit.getDefaultToolkit().beep();
                    return;
                }
            }
            
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            
            // Non chiudere il clip immediatamente, lascia che finisca
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });
            
        } catch (Exception e) {
            // In caso di errore, usa un beep di sistema
            Toolkit.getDefaultToolkit().beep();
        }
    }
    
    private void showSimpleMessage(@NotNull String actionType) {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(null, 
                "Git " + actionType + " completato!", 
                "Git Celebration", 
                JOptionPane.INFORMATION_MESSAGE);
        });
    }
}

