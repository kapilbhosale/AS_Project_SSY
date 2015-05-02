package com.amp.helper;

public class EventsData 
{
	public String descStr;
    public String title;
    public String detail;
    public String imageURL;
   
 
    
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
}
