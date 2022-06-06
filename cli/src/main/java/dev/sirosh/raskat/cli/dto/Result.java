package dev.sirosh.raskat.cli.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {
    private boolean success;
    private List<String> errors;
    private List<String> warnings;
    private T data;

    public static <D> Result<D> successResult(D data, List<String> warnings) {
        Result<D> result = new Result<>();
        result.setSuccess(true);
        result.setData(data);
        result.setWarnings(warnings);
        return result;
    }

    public static <D> Result<D> successResult(D data) {
        Result<D> result = new Result<>();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    public static Result<Void> successResult() {
        Result<Void> result = new Result<>();
        result.setSuccess(true);
        return result;
    }

    public static Result<Void> failResult(List<String> warnings, List<String> errors) {
        Result<Void> result = new Result<>();
        result.setSuccess(false);
        result.setWarnings(warnings);
        result.setErrors(errors);
        return result;
    }

    public static Result<Void> failResult(List<String> errors) {
        Result<Void> result = new Result<>();
        result.setSuccess(false);
        result.setErrors(errors);
        return result;
    }
}
