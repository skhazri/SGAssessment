package ca.khazri.superHero.dto.response;

public class SuccessResponse extends ApiResponse {

    public SuccessResponse(String successMessage) {
        super(true);
        addMessage(successMessage);
    }

    public SuccessResponse() {
        this(null);
    }
}