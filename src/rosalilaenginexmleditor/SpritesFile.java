/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rosalilaenginexmleditor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

/**
 *
 * @author oscarr
 */
public class SpritesFile {
    private String mPath;
    private List<MoveElement> mMoves;
    private static String ROOT_NAME = "SpritesFile";
    
    public SpritesFile(String path) {
        this(path,new ArrayList<MoveElement>());
    }
    
    public SpritesFile(String path,List<MoveElement> animations) {
        mPath = path;
        mMoves = animations;
    }
    
    public void readFromFile() throws ParsingException, ValidityException, IOException  {
        Builder builder = new Builder();   
        Document doc = builder.build(new File(mPath));
        Element root = doc.getRootElement();
        Elements children = root.getChildElements();
        
        mMoves = new ArrayList<>();
        for ( int i = 0 ; i < children.size() ; i++ ) {
            mMoves.add(new MoveElement(children.get(i)));
        }
    }
    
    public void writeToFile() {
        //TODO method to read an xml file
    }
    
    public MoveElement getMove(int index) {
        if (index < 0 || index >= mMoves.size() )
            return null;
        return mMoves.get(index);
    }
    
    public int getMoveCount() {
        return mMoves.size();
    }
    
    @Override
    public String toString() {
        return mPath;
    }
    
    public void addMove(MoveElement m) {
        if ( mMoves != null )mMoves.add(m);
    }
    
    public void removeMove(int index) {
        if ( mMoves != null )mMoves.remove(index);
    }
    
    @Override
    public boolean equals(Object that) {
        if ( that == null )return false;
        if ( that.getClass() != this.getClass() )return false;
        SpritesFile thatFile = (SpritesFile)that;
        return this.mPath.equals(thatFile.mPath);
    }
    
    public Document generateXml() {
        Element root = new Element(ROOT_NAME);
        
        for (MoveElement move: mMoves)
            root.appendChild(move.generateXml());
        
        return new Document(root);
    }
}
