package Models;

import Models.Enum.WatermarkType;

/**
 * Created by Administrator on 2017-08-14.
 */
public class ImageRectangle {
    private int _x;
    private int _y;
    private int _width;
    private int _height;
    private int _dissolve=50;//透明度
    private String _Content;//内容如果是图片存路径
    private WatermarkType _watermarkType;

    public int getdissolve() {
        return _dissolve;
    }
    public void setdissolve(int _dissolve) {
        this._dissolve = _dissolve;
    }

    public String getContent() {
        return _Content;
    }
    public void setContent(String _Content) {
        this._Content = _Content;
    }

    public int getWidth() {
        return this._width;
    }
    public void setWidth(int width) {
        this._width = width;
    }

    public int getX() {
        return _x;
    }
    public void setX(int x) {
        this._x = x;
    }

    public int getY() {
        return this._y;
    }
    public void setY(int y) {
        _y = y;
    }

    public int getHeight() {
        return this._height;
    }
    public void setHeight(int height) {
        this._height = height;
    }

    public WatermarkType getWatermarkType() {
        return this._watermarkType;
    }
    public void setWatermarkType(WatermarkType thumbnailType) {
        this._watermarkType = thumbnailType;
    }
}
