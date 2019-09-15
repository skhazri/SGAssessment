package ca.khazri.superHero.dto.response;

public class ErrorResponse extends ApiResponse {

    public ErrorResponse(String errorMessage) {
        super(false);
        addMessage(errorMessage);
    }

}