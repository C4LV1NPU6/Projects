package edu.byu.cs.tweeter.client.model.service.backgroundTask.handler;

import android.os.Bundle;

import edu.byu.cs.tweeter.client.model.service.backgroundTask.task.GetUserTask;
import edu.byu.cs.tweeter.client.model.service.observer.UserObserver;
import edu.byu.cs.tweeter.model.domain.User;

public class GetUserHandler extends BaseHandler<UserObserver> {
    public GetUserHandler(UserObserver observer) {
        super(observer);
    }

    @Override
    protected void handleSuccessMessage(UserObserver observer, Bundle data) {
        User user = (User) data.getSerializable(GetUserTask.USER_KEY);
        observer.handleGetUser(user);
    }
}