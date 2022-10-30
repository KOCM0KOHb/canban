package status;

public enum Status {
    NEW("new"), IN_PROGRESS("inProgress"), DONE("done");
    private final String translation;

    Status(String translation) {
        this.translation = translation;
    }

    public String toString() {
        return translation;
    }
}