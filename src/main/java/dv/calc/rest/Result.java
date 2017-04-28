package dv.calc.rest;

/**
 * Response entity class with results of calculation request processing.
 *
 * @author Denys Volchkov
 */
public class Result {

    enum Status {
        SUCCESS,
        ERROR
    }

    private static String EMPTY_MESSAGE = "";

    private Status status;
    private double value = Double.NaN;
    private String message;

    public Result() {
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Result {" +
                "status=" + status +
                ", value='" + value + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public static Result success(double value) {
        Result result = new Result();
        result.setStatus(Status.SUCCESS);
        result.setValue(value);
        result.setMessage(EMPTY_MESSAGE);

        return result;
    }

    public static Result error(String message) {
        Result result = new Result();
        result.setStatus(Status.ERROR);
        result.setMessage(message);

        return result;
    }
}
