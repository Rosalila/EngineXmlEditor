/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rosalila.engine.xmleditor.spritesfile;

import java.util.ArrayList;
import java.util.List;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;

/**
 *
 * @author Oscar Rene Flores Presidente
 */
public class MoveElement {
    String mName;
    List<SpriteElement> mSprites;
    
    private static String NAME_ATT = "name";
    private static String ELEMENT_NAME = "Move";
    
    public MoveElement(String name) {
        this(name,new ArrayList<SpriteElement>());
    }
    
    public MoveElement(String name,List<SpriteElement> sprites ) {
        mName = name;
        mSprites = sprites;
    }
    
    public MoveElement(Element e) throws ParsingException {
        if ( e.getLocalName().compareTo(ELEMENT_NAME) != 0) throw new ParsingException("Not a valid element for move");
        
        mName = e.getAttributeValue(NAME_ATT);
        
        Elements elements = e.getChildElements();
        int childCount = elements.size();
        mSprites = new ArrayList<>();
        
        for ( int i = 0 ; i < childCount ; i++)
            mSprites.add(new SpriteElement(elements.get(i)));
    }
    
    @Override
    public boolean equals(Object that) {
        if ( that == null )return false;
        if ( that.getClass() != this.getClass() ) return false;
        MoveElement thatMove = (MoveElement)that;
        
        if ( thatMove.mSprites.size() != this.mSprites.size() )return false;
        for (int i = 0 ; i < mSprites.size() ; i++ )
            if (!this.mSprites.get(i).equals(thatMove.mSprites.get(i))) return false;
        
        return true;
    }
    
    public void removeSprite(int index) {
        mSprites.remove(index);
    }
    
    public void addSprite(SpriteElement sprite) {
        mSprites.add(sprite);
    }
    
    public void insertSprite(SpriteElement sprite,int index) {
        mSprites.add(index, sprite);
    }
    
    public SpriteElement getSprite(int index) {
        return mSprites.get(index);
    }
    
    public int getSpriteCount() {
        return mSprites.size();
    }
    
    @Override
    public String toString() {
        return mName;
    }
    
    public Element generateXml() {
        Element e = new Element(mName);
        for ( SpriteElement s : mSprites )
            e.appendChild(s.generateXml());
        return e;
    }
}
