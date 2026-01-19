package com.dsplugin.gitcelebration;

import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Overlay grafico non modale con fade e audio
 */
public class CelebrationDisplay {

    private final Project project;
    private final CelebrationConfig config;

    public CelebrationDisplay(@NotNull Project project) {
        this.project = project;
        this.config = CelebrationConfig.getInstance(project);
    }

    public void showCelebration(
            @NotNull String imagePath,
            @NotNull String soundPath,
            @NotNull String actionType
    ) {
        SwingUtilities.invokeLater(() -> {
            try {
                BufferedImage image = loadImage(imagePath);

                OverlayWindow window = new OverlayWindow(image);
                window.setVisible(true);

                // audio PARTE SUBITO (thread separato)
                playSoundAsync(soundPath);

                // fade in
                window.fadeIn(config.getFadeInDuration());

                // durata + fade out
                new Timer(config.getDisplayDuration(), e ->
                        window.fadeOut(config.getFadeOutDuration(), () -> {
                            window.setVisible(false);
                            window.dispose();
                        })
                ).start();

            } catch (Exception ignored) {
                // niente dialog: fallire silenziosamente
            }
        });
    }

    /* ========================================================= */

    private BufferedImage loadImage(String path) throws IOException {
        InputStream is = getClass().getResourceAsStream(path);
        if (is != null) return ImageIO.read(is);

        File f = new File(path);
        if (f.exists()) return ImageIO.read(f);

        throw new IOException("Image not found");
    }

    private void playSoundAsync(String path) {
        new Thread(() -> {
            try {
                InputStream is = getClass().getResourceAsStream(path);
                AudioInputStream ais;

                if (is != null) {
                    ais = AudioSystem.getAudioInputStream(
                            new BufferedInputStream(is));
                } else {
                    File f = new File(path);
                    if (!f.exists()) return;
                    ais = AudioSystem.getAudioInputStream(f);
                }

                Clip clip = AudioSystem.getClip();
                clip.open(ais);
                clip.start();

            } catch (Exception ignored) {
            }
        }, "DSPlugin-Sound").start();
    }

    /* ========================================================= */

    /**
     * Finestra overlay trasparente
     */
    private static class OverlayWindow extends JWindow {

        private float alpha = 0f;

        OverlayWindow(BufferedImage image) {
            setBackground(new Color(0, 0, 0, 0));
            setAlwaysOnTop(true);
            setFocusableWindowState(false);

            Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
            setSize(screen);
            setLocation(0, 0);

            add(new JPanel() {
                {
                    setOpaque(false);
                }

                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setComposite(
                            AlphaComposite.getInstance(
                                    AlphaComposite.SRC_OVER, alpha));

                    int x = (getWidth() - image.getWidth()) / 2;
                    int y = (getHeight() - image.getHeight()) / 2;
                    g2.drawImage(image, x, y, null);
                }
            });
        }

        void fadeIn(int durationMs) {
            animate(0f, 1f, durationMs, null);
        }

        void fadeOut(int durationMs, Runnable end) {
            animate(1f, 0f, durationMs, end);
        }

        private void animate(float from, float to, int durationMs, Runnable end) {
            int steps = 30;
            float delta = (to - from) / steps;
            int delay = durationMs / steps;

            alpha = from;

            Timer timer = new Timer(delay, e -> {
                alpha += delta;
                repaint();

                if ((delta > 0 && alpha >= to) ||
                        (delta < 0 && alpha <= to)) {

                    alpha = to;
                    repaint();
                    ((Timer) e.getSource()).stop();
                    if (end != null) end.run();
                }
            });
            timer.start();
        }
    }
}
