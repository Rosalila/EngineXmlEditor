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
public class StringDeclaration {
    private String mVariable;
    private String mValue;
    
    private static final String ELEMENT_NAME = "string";
    private static final String VARIABLE_ATT_NAME = "variable";
    private static final String VALUE_ATT_NAME = "value";
    
    public StringDeclaration(String variable,String value) {
        mVariable = variable;
        mValue = value;
    }
    
    public StringDeclaration(Element e) throws ParsingException{
        if ( e.getLocalName().compareTo(ELEMENT_NAME) != 0 )throw new ParsingException("Not a valid string element");
        
        String attribute = e.getAttributeValue(VARIABLE_ATT_NAME);
        if ( attribute == null )throw new ParsingException("Missing variable attribute in string declaration");
        mVariable = attribute;
        
        attribute = e.getAttributeValue(VALUE_ATT_NAME);
        if ( attribute == null )throw new ParsingException("Missing value attribute in string declaration");
        mValue = attribute;
    }
    
    public void setVariable(String variable) {
        this.mVariable = variable;    
    }
    
    public String getVariable() {
        return mVariable;
    }
    
    public void setValue(String val) {
        this.mValue = val;
    }
    
    public String getValue() {
        return mValue;
    }
    
    public Element generateXml() {
        Element e = new Element(ELEMENT_NAME);
        e.addAttribute(new Attribute(VALUE_ATT_NAME, mValue));
        e.addAttribute(new Attribute(VARIABLE_ATT_NAME,mVariable));
        
        return e;
    }
    
}
