/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timebackup;

/**
 *
 * @author yaakov
 */
import javafx.scene.control.TreeItem;
public class TreeItemPath extends TreeItem<String> {
    private PathNode pathNode;
    
    TreeItemPath(PathNode pn){
    super(pn.getName());
    pathNode = pn;
    
    }
}
