/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rosalilaenginexmleditor;

import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author oscarr
 */
public class MovesTreeModel
        implements TreeModel {

    private List<TreeModelListener> mListeners;
    private SpritesFile mRoot;

    public MovesTreeModel(SpritesFile file) {
        mRoot = file;
        mListeners = new ArrayList<>();
    }

    public void reload() {
        int n = getChildCount(mRoot);
        int[] childIndex = new int[n];
        Object[] childs = new Object[n];
        for (int i = 0; i < n; i++) {
            childIndex[i] = i;
            childs[i] = getChild(mRoot, i);
        }
        fireTreeStructureChanged(new TreeModelEvent(this, new Object[]{mRoot}, childIndex, childs));
    }

    @Override
    public Object getRoot() {
        return mRoot;
    }

    @Override
    public Object getChild(Object parent, int index) {
        if (isLeaf(parent)) {
            return null;
        }

        if (parent.getClass() == SpritesFile.class) {
            return mRoot.getMove(index);
        } else {
            MoveElement animation = (MoveElement) parent;
            SpriteElement sprite = animation.getSprite(index);
            return sprite;
        }

    }

    @Override
    public int getChildCount(Object parent) {
        if (parent.getClass() == SpritesFile.class) {
            return mRoot.getMoveCount();
        } else {
            MoveElement move = (MoveElement)parent;
            return move.getSpriteCount();
        }

    }

    @Override
    public boolean isLeaf(Object node) {
        return node.getClass() == SpriteElement.class;
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        if ( parent.getClass() == SpritesFile.class ) {
            SpritesFile file = (SpritesFile)parent;
            
            for ( int i = 0 ; i < file.getMoveCount() ; i++ )
                if (child.equals(file.getMove(i)))return i;
        } else if ( parent instanceof MoveElement ){ 
            MoveElement move = (MoveElement)parent;
            for ( int i = 0 ; i < move.getSpriteCount(); i++)
                if (move.getSprite(i).equals(child))return i;
            
        }
        return -1;
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
        if (mListeners != null && !mListeners.contains(l)) {
            mListeners.add(l);
        }
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        if (mListeners != null) {
            mListeners.remove(l);
        }
    }

    public void fireTreeNodesInserted(TreeModelEvent e) {
        for (TreeModelListener listener : mListeners) {
            listener.treeNodesInserted(e);
        }
    }

    public void fireTreeNodesRemoved(TreeModelEvent e) {
        for (TreeModelListener listener : mListeners) {
            listener.treeNodesRemoved(e);
        }
    }

    public void fireTreeNodesChanged(TreeModelEvent e) {
        for (TreeModelListener listener : mListeners) {
            listener.treeNodesChanged(e);
        }
    }

    public void fireTreeStructureChanged(TreeModelEvent e) {
        for (TreeModelListener listener : mListeners) {
            listener.treeStructureChanged(e);
        }
    }
}
