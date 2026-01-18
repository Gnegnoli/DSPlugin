package com.dsplugin.gitcelebration;

import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Manager principale che gestisce le celebrazioni per le azioni Git
 */
public class CelebrationManager {
    
    private final Project project;
    private final CelebrationConfig config;
    private static final Map<Project, CelebrationManager> instances = new HashMap<>();
    
    public CelebrationManager(@NotNull Project project) {
        this.project = project;
        this.config = CelebrationConfig.getInstance(project);
    }
    
    public static CelebrationManager getInstance(@NotNull Project project) {
        return instances.computeIfAbsent(project, CelebrationManager::new);
    }
    
    /**
     * Attiva una celebrazione per un tipo di azione Git
     */
    public void celebrate(@NotNull String actionType) {
        // Ottieni configurazione per questo tipo di azione
        String imagePath = config.getImagePath(actionType);
        String soundPath = config.getSoundPath(actionType);
        
        if (imagePath == null || soundPath == null) {
            // Usa valori di default se non configurati
            imagePath = config.getDefaultImagePath(actionType);
            soundPath = config.getDefaultSoundPath(actionType);
        }
        
        // Mostra celebrazione in un thread separato per non bloccare l'UI
        CompletableFuture.runAsync(() -> {
            CelebrationDisplay display = new CelebrationDisplay(project);
            display.showCelebration(imagePath, soundPath, actionType);
        });
    }
}

