package com.amp.helper;

public class EventsData 
{
	public String descStr;
    public String title;
    public String detail;
    public String imageURL;
    public int imageId;

    public EventsData()
    {
        super();
    }
   
    public EventsData(String cdesc, String cTitle,String cDetail, String cImageURL) 
    {
        super();
        this.descStr = cdesc;
        this.title =cTitle;
        this.detail = cDetail;
        this.imageURL=cImageURL;
    }
    public EventsData(String cdesc, String cTitle,String cDetail, String cImageURL, int cImageId)
    {
        super();
        this.descStr = cdesc;
        this.title =cTitle;
        this.detail = cDetail;
        this.imageURL=cImageURL;
        this.imageId=cImageId;
    }
}
