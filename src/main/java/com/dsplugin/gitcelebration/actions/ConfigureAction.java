package com.dsplugin.gitcelebration.actions;

import com.dsplugin.gitcelebration.CelebrationConfig;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

/**
 * Azione per configurare immagini e suoni delle celebrazioni
 */
public class ConfigureAction extends AnAction {
    
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        if (project == null) return;
        
        CelebrationConfig config = CelebrationConfig.getInstance(project);
        ConfigurationDialog dialog = new ConfigurationDialog(project, config);
        dialog.show();
    }
    
    private static class ConfigurationDialog extends JDialog {
        
        private final Project project;
        private final CelebrationConfig config;
        private final JTabbedPane tabbedPane;
        
        public ConfigurationDialog(@NotNull Project project, @NotNull CelebrationConfig config) {
            super((Frame) null, "Configura Git Celebration", true);
            this.project = project;
            this.config = config;
            
            setSize(800, 600);
            setLocationRelativeTo(null);
            
            tabbedPane = new JTabbedPane();
            
            // Tab per ogni tipo di azione
            String[] actions = {"commit", "merge", "push", "pull", "rebase", "checkout", "branch"};
            
            for (String action : actions) {
                tabbedPane.addTab(capitalize(action), createActionPanel(action));
            }
            
            // Tab per impostazioni generali
            tabbedPane.addTab("Impostazioni", createSettingsPanel());
            
            add(tabbedPane);
            
            // Pulsanti
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton okButton = new JButton("OK");
            okButton.addActionListener(e -> {
                saveConfiguration();
                dispose();
            });
            JButton cancelButton = new JButton("Annulla");
            cancelButton.addActionListener(e -> dispose());
            
            buttonPanel.add(cancelButton);
            buttonPanel.add(okButton);
            
            add(buttonPanel, BorderLayout.SOUTH);
        }
        
        private JPanel createActionPanel(@NotNull String actionType) {
            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.anchor = GridBagConstraints.WEST;
            
            // Immagine
            gbc.gridx = 0;
            gbc.gridy = 0;
            panel.add(new JLabel("Immagine:"), gbc);
            
            gbc.gridx = 1;
            JTextField imageField = new JTextField(config.getImagePath(actionType), 40);
            panel.add(imageField, gbc);
            
            gbc.gridx = 2;
            JButton imageBrowseButton = new JButton("Sfoglia...");
            imageBrowseButton.addActionListener(e -> {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileFilter(new FileNameExtensionFilter(
                    "Immagini", "png", "jpg", "jpeg", "gif", "bmp"));
                if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                    imageField.setText(chooser.getSelectedFile().getAbsolutePath());
                }
            });
            panel.add(imageBrowseButton, gbc);
            
            // Suono
            gbc.gridx = 0;
            gbc.gridy = 1;
            panel.add(new JLabel("Suono:"), gbc);
            
            gbc.gridx = 1;
            JTextField soundField = new JTextField(config.getSoundPath(actionType), 40);
            panel.add(soundField, gbc);
            
            gbc.gridx = 2;
            JButton soundBrowseButton = new JButton("Sfoglia...");
            soundBrowseButton.addActionListener(e -> {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileFilter(new FileNameExtensionFilter(
                    "File audio", "wav", "mp3", "ogg"));
                if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                    soundField.setText(chooser.getSelectedFile().getAbsolutePath());
                }
            });
            panel.add(soundBrowseButton, gbc);
            
            // Salva i riferimenti ai campi per il salvataggio
            imageField.setName(actionType + "_image");
            soundField.setName(actionType + "_sound");
            
            return panel;
        }
        
        private JPanel createSettingsPanel() {
            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.anchor = GridBagConstraints.WEST;
            
            // Durata visualizzazione
            gbc.gridx = 0;
            gbc.gridy = 0;
            panel.add(new JLabel("Durata visualizzazione (ms):"), gbc);
            
            gbc.gridx = 1;
            JSpinner displayDurationSpinner = new JSpinner(
                new SpinnerNumberModel(config.getDisplayDuration(), 500, 10000, 100));
            panel.add(displayDurationSpinner, gbc);
            
            // Durata dissolvenza in entrata
            gbc.gridx = 0;
            gbc.gridy = 1;
            panel.add(new JLabel("Dissolvenza in entrata (ms):"), gbc);
            
            gbc.gridx = 1;
            JSpinner fadeInSpinner = new JSpinner(
                new SpinnerNumberModel(config.getFadeInDuration(), 100, 2000, 50));
            panel.add(fadeInSpinner, gbc);
            
            // Durata dissolvenza in uscita
            gbc.gridx = 0;
            gbc.gridy = 2;
            panel.add(new JLabel("Dissolvenza in uscita (ms):"), gbc);
            
            gbc.gridx = 1;
            JSpinner fadeOutSpinner = new JSpinner(
                new SpinnerNumberModel(config.getFadeOutDuration(), 100, 2000, 50));
            panel.add(fadeOutSpinner, gbc);
            
            // Salva i riferimenti
            displayDurationSpinner.setName("displayDuration");
            fadeInSpinner.setName("fadeInDuration");
            fadeOutSpinner.setName("fadeOutDuration");
            
            return panel;
        }
        
        private void saveConfiguration() {
            // Salva configurazioni per ogni azione
            String[] actions = {"commit", "merge", "push", "pull", "rebase", "checkout", "branch"};
            
            for (String action : actions) {
                Component[] components = tabbedPane.getComponents();
                for (Component tab : components) {
                    if (tab instanceof JPanel) {
                        JTextField imageField = findComponent((JPanel) tab, action + "_image", JTextField.class);
                        JTextField soundField = findComponent((JPanel) tab, action + "_sound", JTextField.class);
                        
                        if (imageField != null) {
                            config.setImagePath(action, imageField.getText());
                        }
                        if (soundField != null) {
                            config.setSoundPath(action, soundField.getText());
                        }
                    }
                }
            }
            
            // Salva impostazioni generali
            Component settingsTab = tabbedPane.getComponentAt(tabbedPane.getTabCount() - 1);
            if (settingsTab instanceof JPanel) {
                JSpinner displaySpinner = findComponent((JPanel) settingsTab, "displayDuration", JSpinner.class);
                JSpinner fadeInSpinner = findComponent((JPanel) settingsTab, "fadeInDuration", JSpinner.class);
                JSpinner fadeOutSpinner = findComponent((JPanel) settingsTab, "fadeOutDuration", JSpinner.class);
                
                if (displaySpinner != null) {
                    config.setDisplayDuration((Integer) displaySpinner.getValue());
                }
                if (fadeInSpinner != null) {
                    config.setFadeInDuration((Integer) fadeInSpinner.getValue());
                }
                if (fadeOutSpinner != null) {
                    config.setFadeOutDuration((Integer) fadeOutSpinner.getValue());
                }
            }
        }
        
        @SuppressWarnings("unchecked")
        private <T extends Component> T findComponent(@NotNull Container container, 
                                                      @NotNull String name, 
                                                      @NotNull Class<T> type) {
            for (Component comp : container.getComponents()) {
                if (name.equals(comp.getName()) && type.isInstance(comp)) {
                    return (T) comp;
                }
                if (comp instanceof Container) {
                    T found = findComponent((Container) comp, name, type);
                    if (found != null) return found;
                }
            }
            return null;
        }
        
        private String capitalize(@NotNull String str) {
            if (str.isEmpty()) return str;
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
    }
}

