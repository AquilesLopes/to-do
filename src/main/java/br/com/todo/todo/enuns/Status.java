package br.com.todo.todo.enuns;

public enum Status {
    PENDING("PENDING"),
    COMPLETED("COMPLETED");

    private String title;

    Status(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
