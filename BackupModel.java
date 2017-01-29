/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timebackup;

import java.io.File;
import java.util.Stack;
/**
 *
 * @author yaakov
 */
public class BackupModel {
private Stack<PathNode> stk = new Stack<>();
    private PathNode rootPathNode;
    

    public BackupModel(PathNode root) {
        rootPathNode = root;
        stk.add(rootPathNode);
    }
    /**
     * 
     * @param directoryPath the path of the directory to generate root nodes from
     */
    public void GeneratePathNodesFromDirectory(String directoryPath){
File[] files = new File(directoryPath).listFiles();
System.out.println(files.length);
    showFiles(files);
}
    /**
     * 
     * @return PathNode that is the root for this backup
     */
    public PathNode getRootNode(){
    return rootPathNode;
    }
    private void showFiles(File[] files){
        for (File file:files){
            System.out.println("The file name is" + file.getName());
        if (file.isDirectory()){
            PathNode temp =new PathNode(true,file.getName());
            stk.peek().getChildren().add(temp);
            stk.add(temp);
            showFiles(file.listFiles());
            stk.pop();
        }else{
            stk.peek().addPathNode(new PathNode(false,file.getName(),"unknown"));
        }
        
        }
    }
}
