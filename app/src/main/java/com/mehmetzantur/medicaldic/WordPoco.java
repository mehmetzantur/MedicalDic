package com.mehmetzantur.medicaldic;

/**
 * Created by mehme on 11.05.2016.
 */
public class WordPoco {
    private int _id;
    private String Title;
    private String Definition;

    public WordPoco (int __id, String _Title, String _Definition){
        this._id = __id;
        this.Title = _Title;
        this.Definition = _Definition;
    }

    public int getId(){
        return _id;
    }

    public String getTitle(){
        return Title;
    }

    public String getDefinition(){
        return Definition;
    }
}
