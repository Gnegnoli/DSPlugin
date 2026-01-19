package com.dsplugin.gitcelebration;

import com.intellij.openapi.components.*;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * Configurazione persistente per immagini e suoni delle celebrazioni
 */
@State(name = "GitCelebrationConfig", storages = @Storage("git-celebration.xml"))
public class CelebrationConfig implements PersistentStateComponent<CelebrationConfig.State> {
    
    public static class State {
        public Map<String, String> imagePaths = new HashMap<>();
        public Map<String, String> soundPaths = new HashMap<>();
        public int displayDuration = 3000; // Durata in millisecondi
        public int fadeInDuration = 500;
        public int fadeOutDuration = 500;
    }
    
    private State state = new State();

    public CelebrationConfig() {
        // Se lo state non viene caricato subito, evitiamo mappe vuote/null
        initializeDefaults();
    }
    
    public static CelebrationConfig getInstance(@NotNull Project project) {
        return project.getService(CelebrationConfig.class);
    }
    
    @Nullable
    @Override
    public State getState() {
        return state;
    }
    
    @Override
    public void loadState(@NotNull State state) {
        this.state = state;
        initializeDefaults();
    }
    
    private void initializeDefaults() {
        // Inizializza percorsi di default se non presenti
        if (state.imagePaths.isEmpty()) {
            state.imagePaths.put("commit", getDefaultImagePath("commit"));
            state.imagePaths.put("merge", getDefaultImagePath("merge"));
            state.imagePaths.put("push", getDefaultImagePath("push"));
            state.imagePaths.put("pull", getDefaultImagePath("pull"));
            state.imagePaths.put("rebase", getDefaultImagePath("rebase"));
            state.imagePaths.put("checkout", getDefaultImagePath("checkout"));
            state.imagePaths.put("branch", getDefaultImagePath("branch"));
        }
        
        if (state.soundPaths.isEmpty()) {
            state.soundPaths.put("commit", getDefaultSoundPath("commit"));
            state.soundPaths.put("merge", getDefaultSoundPath("merge"));
            state.soundPaths.put("push", getDefaultSoundPath("push"));
            state.soundPaths.put("pull", getDefaultSoundPath("pull"));
            state.soundPaths.put("rebase", getDefaultSoundPath("rebase"));
            state.soundPaths.put("checkout", getDefaultSoundPath("checkout"));
            state.soundPaths.put("branch", getDefaultSoundPath("branch"));
        }
    }
    
    public String getImagePath(@NotNull String actionType) {
        return state.imagePaths.get(actionType);
    }
    
    public String getSoundPath(@NotNull String actionType) {
        return state.soundPaths.get(actionType);
    }
    
    public void setImagePath(@NotNull String actionType, @NotNull String path) {
        state.imagePaths.put(actionType, path);
    }
    
    public void setSoundPath(@NotNull String actionType, @NotNull String path) {
        state.soundPaths.put(actionType, path);
    }
    
    public String getDefaultImagePath(@NotNull String actionType) {
        // Percorsi relativi alle risorse del plugin
        return "/images/" + actionType + ".png";
    }
    
    public String getDefaultSoundPath(@NotNull String actionType) {
        // Percorsi relativi alle risorse del plugin
        return "/sounds/" + actionType + ".wav";
    }
    
    public int getDisplayDuration() {
        return state.displayDuration;
    }
    
    public int getFadeInDuration() {
        return state.fadeInDuration;
    }
    
    public int getFadeOutDuration() {
        return state.fadeOutDuration;
    }
    
    public void setDisplayDuration(int duration) {
        state.displayDuration = duration;
    }
    
    public void setFadeInDuration(int duration) {
        state.fadeInDuration = duration;
    }
    
    public void setFadeOutDuration(int duration) {
        state.fadeOutDuration = duration;
    }
}

