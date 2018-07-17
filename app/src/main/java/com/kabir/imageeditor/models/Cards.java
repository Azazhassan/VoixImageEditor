package com.kabir.imageeditor.models;

import java.io.Serializable;

public class Cards  implements Serializable{

    private String cardID;
    private String cardURL;

    public Cards(String cardID, String cardURL) {
        this.cardID = cardID;
        this.cardURL = cardURL;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getCardURL() {
        return cardURL;
    }

    public void setCardURL(String cardURL) {
        this.cardURL = cardURL;
    }

    public Cards()
    {

    }
}
