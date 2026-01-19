package com.dsplugin.gitcelebration;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Mappa gli actionId IntelliJ/PHPStorm alle "azioni" interne del plugin.
 * Questo evita dipendenze compile-time da git4idea.*.
 */
public final class GitActionIds {

    private GitActionIds() {}

    private static final Map<String, String> ID_TO_TYPE = new HashMap<>();

    static {
        // Git
        ID_TO_TYPE.put("Git.Commit", "commit");
        ID_TO_TYPE.put("Git.Merge", "merge");
        ID_TO_TYPE.put("Git.Rebase", "rebase");
        ID_TO_TYPE.put("Git.Checkout", "checkout");
        ID_TO_TYPE.put("Git.Branch", "branch");

        // VCS generici (push/pull spesso stanno qui)
        ID_TO_TYPE.put("Vcs.Push", "push");
        ID_TO_TYPE.put("Vcs.UpdateProject", "pull"); // spesso equivale a "pull"/update
    }

    public static String toCelebrationType(@NotNull String actionId) {
        return ID_TO_TYPE.get(actionId);
    }
}

