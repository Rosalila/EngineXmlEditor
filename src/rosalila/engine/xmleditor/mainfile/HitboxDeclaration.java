/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rosalila.engine.xmleditor.mainfile;

import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.ParsingException;

/**
 *
 * @author oscarr
 */
public class HitboxDeclaration {
    private String mVariable;
    
    public static final String ELEMENT_NAME = "hitboxes";
    private static final String VARIABLE_ATT_NAME = "variable";
    
    public HitboxDeclaration(String variable) {
        mVariable = variable;
    }
    
    public HitboxDeclaration(Element e) throws ParsingException {
        if ( e.getLocalName().compareTo(ELEMENT_NAME)!= 0 )throw new ParsingException("Not a valid hitbox element");
        
        String variableAtt = e.getAttributeValue(VARIABLE_ATT_NAME);
        if ( variableAtt == null )throw new ParsingException("Missing variable attribute in hitbox declaration.");
        mVariable = variableAtt;
        
    }
    
    public void setVariable(String variable) {
        mVariable = variable;
    }
    
    public String getVariable() {
        return mVariable;
    }
    
    public Element generateXml() {
        Element e = new Element(ELEMENT_NAME);
        e.addAttribute(new Attribute(VARIABLE_ATT_NAME,mVariable));
        return e;
    }
}
