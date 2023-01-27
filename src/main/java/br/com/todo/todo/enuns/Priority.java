package br.com.todo.todo.enuns;

public enum Priority {
    LOW("LOW"),
    MEDIUM("MEDIUM"),
    HIGH("HIGH");

    private String title;

    Priority(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
