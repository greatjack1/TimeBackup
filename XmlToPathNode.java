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
import java.util.Stack;

public class XmlToPathNode {

    private PathNode rootNode;
    private String xml;
    private final Stack<PathNode> stk = new Stack<>();

    /**
     * This constructor takes a string of xml and will product a root PathNode from
     * it
     *
     * @param xml
     */
    public XmlToPathNode(String xml) {
        this.xml = xml;
        readXml();

    }
/**
 * 
 * @return PathNode Returns the root PathNode that was created from the xml
 */
    public PathNode getRootNode() {
        return rootNode;
    }
//THis is the method tha parses the xml
    private void readXml() {
        //convert the xml to a char array
        char[] cmlChars = xml.toCharArray();
        //declare a stringbuffer to be used as we iterate through the xml
   StringBuilder sb = new StringBuilder();
  //this is the root nore that we will add children to
   rootNode = new PathNode(true, "Main Folder");
//create a stack that at the top will holde the current node to add children to as we iterate
        stk.add(rootNode);
        //a boolean to keep track whether the current info that we have in the iteration is for a file or for a folder
        boolean lastFolder = false;
        //a string to hole the current Name
        String currName = "";
        //a string to hold the curren DBREfId
        String currRefId = "";
        //the loop to iterate through the xml
       
        for (int i = 0; i < cmlChars.length; i++) {
                //append the char to the string builder
            sb.append(cmlChars[i]);
            
        //   switch statement to see what the current part of the xml file we are holding at
            switch (sb.toString().trim()) {
                case "<Folder":
                    //set lastfolder to true since we are holding by a folder
                    lastFolder = true;
                    //reset the string builder since we found a command
                    sb.setLength(0);
                    
                    break;
                case "<File":
                    //set last folder to false since we found a file
                    lastFolder = false;
                    sb.setLength(0);
                   
                    break;
                case "name=\"":
                    //get the name value from the xmlstring
                 //   increment i in the loop since the curren value the current value is the " in name
                    i++;
                    //a loop to get the value of the name property
                    while (cmlChars[i] != '"') {
                        currName = currName + cmlChars[i];
                        i++;
                    }
//if last folder is true then add a folder to the node at the top of stack
                    if (lastFolder) {
                        PathNode newNode = new PathNode(true, currName);
                        //peek the top node but dont pop it since their might be more nodes that need to be added
                        stk.peek().getChildren().add(newNode);
                       //now add the folder node so in the future the current node to add chilren to will be this one
                        stk.add(newNode);
                        //reset the current name since we used it for the folder
                        currName = "";
                    }
                   sb.setLength(0);
                    break;
                case "dbRefID=\"":
                    //get the dbRefId value from the xmlstring
                    //increment one to start from after the "
                    i++;
                    //get the db ref value until we hit double qoutes
                    while (cmlChars[i] != '"') {
                        currRefId = currRefId + cmlChars[i];
                        i++;
                    }
                    //skip one more so that we take care of the extra >
                    i++;
//if last folder is false then it means we have a file so add a file to the top node in the stack
                    if (!lastFolder) {
                        PathNode newNode = new PathNode(false, currName, currRefId);
                        //only peek the folder as their might be more nodes to add
                        stk.peek().getChildren().add(newNode);
                        //reset currname since we used it
                        currName = "";
                    }
                    //even if its a folder then clear the refId Since it is meaningless
                    currRefId = "";
                    //clear the string buffer
                   sb.setLength(0);
                   
                    break;
                case "</Folder>":
                    //since its a folder remove the currNode from the Stack
                    stk.pop();
                    sb.setLength(0);
                    
                    break;
                case "</File>":
                 sb.setLength(0);
                
                    break;
            }

        }

    }
}
