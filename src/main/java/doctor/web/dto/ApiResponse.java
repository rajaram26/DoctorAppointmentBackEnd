package doctor.web.dto;

public record ApiResponse<T>(
        int statusCode,
        boolean success,
        String message,
        T data
) {
}
