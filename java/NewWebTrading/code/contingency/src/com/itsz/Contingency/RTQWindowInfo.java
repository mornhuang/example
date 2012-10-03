package com.itsz.Contingency;

public class RTQWindowInfo {
    private String status="FRAME";
    private String name="";
    private int width=660;
    private int height=330;
    private String toolbar = "yes";
    private String scrollbar = "yes";
    private String resize = "yes";
    public RTQWindowInfo(String product) {
        setProperties(product);
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public String isToolbar() {
        return toolbar;
    }
    public void setToolbar(String toolbar) {
        this.toolbar = toolbar;
    }
    public String isScrollbar() {
        return scrollbar;
    }
    public void setScrollbar(String scrollbar) {
        this.scrollbar = scrollbar;
    }
    public String isResize() {
        return resize;
    }
    public void setResize(String resize) {
        this.resize = resize;
    }
    private void setProperties(String product){
        if("QPIF".equalsIgnoreCase(product)){
            status = "POPUP";
            width = 800;
            height = 580;
            scrollbar = "no";
            toolbar = "no";
            name="RTQPopUp";
        }else if("TFFO".equalsIgnoreCase(product)){
            width = 660;
            height = 330;
            scrollbar = "no";
            toolbar = "no";
            name="RTQPopUpTFFO";
        }

    }
}