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
import java.util.ArrayList;
/**
 * 
 * This class provides a implementation of a node which can
 * either be a file or a folder, If it is a folder then it
 * can store child PathNodes
 */
public class PathNode  {
   
    private boolean folder;
    private String name;
    private String dbRefId;
    private ArrayList<PathNode> pathNodeList = new ArrayList<>();
/**
 * 
 * @param folder This is a boolean to determine if this PathNode is a folder
 * @param name This is the name of the file or folder
 * @param dbRefId This is the dbRefId and it holds the referance to this file in the database
 * 
 */
    public PathNode(boolean folder, String name, String dbRefId) {
     
        this.folder = folder;
        this.name = name;
        this.dbRefId = dbRefId;
    }
/**
 * 
 * @param folder This is a boolean to determine if this PathNode is a folder
 * @param name This is the name of the file or folder
 *
 * 
 */
    public PathNode(boolean folder, String name) {
        
        this.folder = folder;
        this.name = name;
    }
/**
 * This method add a child PathNode to the Current Node
 * @param pathNode 
 */
    public void addPathNode(PathNode pathNode) {
        pathNodeList.add(pathNode);
    }
/**
 * 
 * @return the name of the PAthNode
 */
    public String getName() {
        return name;
    }
/**
 * 
 * @return The DbRefId for the File, if its a folder then it returns none
 */
    public String getDbRefId() {
        if (folder) {
            return "none";
        }
        return dbRefId;

    }

    public boolean isFolder() {
        return folder;
    }

    public ArrayList<PathNode> getChildren() {
        return pathNodeList;
    }
}
