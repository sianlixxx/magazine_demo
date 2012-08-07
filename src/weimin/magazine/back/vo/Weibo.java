package weimin.magazine.back.vo;

import weimin.magazine.back.dao.pojo.TAccessToken;

public class Weibo {
    
    private TAccessToken tAccessToken; //授权信息
    
    private String text ="" ; //微博内容
    
    private String locPicUrl = "" ; //本地图片地址
    
    private byte[] pic; // 图片流
    
    private float lat ; //
    
    private float longs ;//
    
    private String annotations = ""; //JSON

    public Weibo() {
        // TODO Auto-generated constructor stub
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLocPicUrl() {
        return locPicUrl;
    }

    public void setLocPicUrl(String locPicUrl) {
        this.locPicUrl = locPicUrl;
    }

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLongs() {
        return longs;
    }

    public void setLongs(float longs) {
        this.longs = longs;
    }

    public String getAnnotations() {
        return annotations;
    }

    public void setAnnotations(String annotations) {
        this.annotations = annotations;
    }

    public TAccessToken gettAccessToken() {
        return tAccessToken;
    }

    public void settAccessToken(TAccessToken tAccessToken) {
        this.tAccessToken = tAccessToken;
    }

}
