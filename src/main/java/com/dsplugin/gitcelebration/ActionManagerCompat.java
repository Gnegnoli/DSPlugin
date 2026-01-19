package com.dsplugin.gitcelebration;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Helper per ottenere l'actionId in modo compatibile.
 */
public final class ActionManagerCompat {

    private ActionManagerCompat() {}

    @Nullable
    public static String getActionId(@NotNull AnAction action, @NotNull AnActionEvent event) {
        try {
            // In genere disponibile
            return ActionManager.getInstance().getId(action);
        } catch (Throwable ignored) {
            // Fallback
            try {
                return event.getActionManager().getId(action);
            } catch (Throwable ignored2) {
                return null;
            }
        }
    }
}

