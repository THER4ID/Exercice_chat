package com.niwatorichan.chatLib;

	

import java.io.Serializable;
        
	public class ChatMessage implements Serializable {
	 
	    protected static final long serialVersionUID = 1112122200L;
	 
	    protected ChatMessageType type;
	    protected String message;
	     
	    // constructor
	    public ChatMessage(ChatMessageType type, String message) {
	        this.type = type;
	        this.message = message;
	    }
	     
	    // getters
	    public ChatMessageType getType() {
	        return type;
	    }
	    public String getMessage() {
	        return message;
	    }
	}