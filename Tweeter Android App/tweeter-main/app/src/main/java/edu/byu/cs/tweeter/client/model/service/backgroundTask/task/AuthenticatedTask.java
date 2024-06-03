package edu.byu.cs.tweeter.client.model.service.backgroundTask.task;

import android.os.Handler;

import edu.byu.cs.tweeter.model.domain.AuthToken;

public abstract class AuthenticatedTask extends BackgroundTask {
    private final AuthToken authToken;

    protected AuthenticatedTask(AuthToken authToken, Handler messageHandler) {
        super(messageHandler);
        this.authToken = authToken;
    }

    public AuthToken getAuthToken() {
        return authToken;
    }
}
