package edu.byu.cs.tweeter.model.net.response;

public class IsFollowerResponse extends Response {
    private boolean isFollower;

    public IsFollowerResponse() {
        super(true);
    }

    public IsFollowerResponse(boolean isFollower) {
        super(true);
        this.isFollower = isFollower;
    }

    public boolean getIsFollower() {
        return isFollower;
    }

    public void setIsFollower(boolean isFollower) {
        this.isFollower = isFollower;
    }
}
