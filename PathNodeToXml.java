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
public class PathNodeToXml {

    private String xml = "";
    private PathNode root;

    /**
     * Constructor takes a Root PathNode and constructs a xml file from it
     *
     * @param pathNode
     */
    public PathNodeToXml(PathNode pathNode) {
        root = pathNode;
        CreateXml(root);
    }

    /**
     * This method returns a String containing the xml
     *
     * @return String A String containing the xml
     */
    public String getXml() {
        return xml;
    }
//This is a recursive method to pars a root node into a xml file

    private void CreateXml(PathNode pathNode) {
        if (pathNode.isFolder()) {
            xml = xml + "<Folder name=\"" + pathNode.getName() + "\" dbRefID=\"none\">";
            for (PathNode node : pathNode.getChildren()) {
                CreateXml(node);
            }
            xml = xml + "</Folder>";
        } //if its not a folder then insert the file in the Xml
        else {
            xml = xml + "<File name=\"" + pathNode.getName() + "\" dbRefID=\"" + pathNode.getDbRefId() + "\">";
            xml = xml + "</File>";
        }

    }

    @Override
    public String toString() {
        return xml;
    }
}
