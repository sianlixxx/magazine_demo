package weimin.magazine.back.cache.message;

public class MSGCollectUserInfo {
    
    private long userId;
    
    private int type;//消息类型

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public MSGCollectUserInfo() {
    }
    
    public MSGCollectUserInfo(long userId,int type) {
        this.userId =  userId;
        this.type = type;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
