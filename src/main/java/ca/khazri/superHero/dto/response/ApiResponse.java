package ca.khazri.superHero.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
public abstract class ApiResponse {
    private Boolean success;
    private List<String> messages;

    protected ApiResponse(boolean success) {
        this.success = success;
        messages = new ArrayList<>();
    }

    protected void addMessage(String message) {
        if (message == null)
            return;
        if (messages == null)
            messages = new ArrayList<> ();

        messages.add(message);
    }

}
