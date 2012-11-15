/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rosalila.engine.xmleditor.mainfile;

import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.NoSuchAttributeException;
import nu.xom.ParsingException;

/**
 *
 * @author oscarr
 */
public class ProjectileHitbox {
    private int mX1;
    private int mX2;
    private int mY1;
    private int mY2;
    
    private static final String ELEMENT_NAME = "Hitbox";
    private static final String X1_ATT_NAME = "x1";
    private static final String X2_ATT_NAME = "x2";
    private static final String Y1_ATT_NAME = "y1";
    private static final String Y2_ATT_NAME = "y2";
    
    public ProjectileHitbox(int x1,int y1, int x2,int y2) {
        setX1(x1);
        setX2(x2);
        setY1(y1);
        setY2(y2);
    }
    
    public ProjectileHitbox(Element e) throws ParsingException,NoSuchAttributeException {
        if (e.getLocalName().compareTo(ELEMENT_NAME) != 0 ) throw new ParsingException("Not a valid projectile hitbox element");
        
        String attribute = e.getAttributeValue(X1_ATT_NAME);
        if ( attribute == null) throw new NoSuchAttributeException("Missing x1 attribute in projectile hitbox element.");
        setX1(Integer.parseInt(attribute));
        
        attribute = e.getAttributeValue(X2_ATT_NAME);
        if ( attribute == null) throw new NoSuchAttributeException("Missing x2 attribute in projectile hitbox element.");
        setX2(Integer.parseInt(attribute));
        
        attribute = e.getAttributeValue(Y1_ATT_NAME);
        if ( attribute == null) throw new NoSuchAttributeException("Missing y1 attribute in projectile hitbox element.");
        setY1(Integer.parseInt(attribute));
        
        attribute = e.getAttributeValue(Y2_ATT_NAME);
        if ( attribute == null) throw new NoSuchAttributeException("Missing y1 attribute in projectile hitbox element.");
        setY2(Integer.parseInt(attribute));
    }
    
    public Element generateXml() {
        Element e = new Element(ELEMENT_NAME);
        e.addAttribute(new Attribute(X1_ATT_NAME,String.valueOf(mX1)));
        e.addAttribute(new Attribute(X2_ATT_NAME,String.valueOf(mX2)));
        e.addAttribute(new Attribute(Y1_ATT_NAME,String.valueOf(mY1)));
        e.addAttribute(new Attribute(Y2_ATT_NAME,String.valueOf(mY2)));
        
        return e;
    }

    public int getX1() {
        return mX1;
    }

    public void setX1(int x1) {
        this.mX1 = x1;
    }

    public int getX2() {
        return mX2;
    }

    public void setX2(int x2) {
        this.mX2 = x2;
    }

    public int getY1() {
        return mY1;
    }

    public void setY1(int y1) {
        this.mY1 = y1;
    }

    public int getY2() {
        return mY2;
    }

    public void setY2(int y2) {
        this.mY2 = y2;
    }
}
