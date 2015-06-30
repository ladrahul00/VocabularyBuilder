/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package first;

import java.awt.event.*;
import com.mongodb.*;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author HACKER
 */
public class page_2 extends javax.swing.JFrame {
    public final int lev_max = 10;
    public final int lev_min = 0;
    /**
     * Creates new form page_2
     * use jlabelsettext("new text");
     */
    
    String UserId;
    public static int out;


    public page_2() {
        setVisible(true);
        out=0;
        UserId = NewJFrame.User_id;
        initComponents();
        jLabel8.setText(UserId);
        //22 10 9
        jPanel22.addMouseListener(new BasicPanel());
        int count = levlAccess("Basic");
        if(count==4)
            jPanel10.addMouseListener(new IntermidPanel());
        count = levlAccess("Intermediate");
        if(count==4)
            jPanel9.addMouseListener(new AdvancedPanel());
        count = levlAccess("Advanced");
        if(count==4){
            setVisible(false);
            JOptionPane.showMessageDialog(null, "Congratulations You completed all levels");
            NewJFrame k =new NewJFrame();
            k.setVisible(true);
        }
        
       
       
        jPanel5.setVisible(false);
        jPanel8.setVisible(false);
        jPanel15.setVisible(false);
        jPanel14.setVisible(false);
        jProgressBar4.setMinimum(lev_min);
        jProgressBar4.setMaximum(lev_max);
        jProgressBar8.setMinimum(lev_min);
        jProgressBar8.setMaximum(lev_max);
        jProgressBar10.setMinimum(lev_min);
        jProgressBar10.setMaximum(lev_max);
        jProgressBar9.setMinimum(lev_min);
        jProgressBar9.setMaximum(lev_max);
        jProgressBar11.setMinimum(0);
        jProgressBar11.setMaximum(4);        
        jProgressBar2.setMinimum(0);
        jProgressBar2.setMaximum(4);
        jProgressBar3.setMinimum(0);
        jProgressBar3.setMaximum(4);
        repaint();
    }
    
    private int levlAccess(String cat){
        int count=0;
        int l1 = countWords(cat,"level1","Correct");
        if(l1==10)
            count++;
        int l2 = countWords(cat,"level2","Correct");
        if(l2==10)
            count++;
        int l3 = countWords(cat,"level3","Correct");
        if(l3==10)
            count++;        
        int l4 = countWords(cat,"level4","Correct");
        if(l4==10)
            count++;

             // 11 2 3
        switch(cat){
            case "Basic":
                jProgressBar11.setValue(count);
                break;
            case "Intermediate":
                jProgressBar2.setValue(count);
                break;
            default:
                jProgressBar3.setValue(count);
                break;           
        }
        return count;
    }
    
    public int countWords(String cat,String level,String Arr){
         try{   
            MongoClient client = new MongoClient();
            DB db = client.getDB("test");
            DBCollection coll = db.getCollection("users");
            BasicDBObject uobj = new BasicDBObject();
            uobj.put("userId", UserId);
            DBObject uquery = coll.findOne(uobj); 
            coll.ensureIndex(cat);
            BasicDBObject catg = (BasicDBObject)uquery.get(cat);
            BasicDBObject levl= (BasicDBObject)catg.get(level);
            ArrayList<String> Correct = (ArrayList) levl.get("Correct");
            ArrayList<String> Wrong = (ArrayList) levl.get("Wrong");
            ArrayList<String> DontKnow = (ArrayList)levl.get("DontKnow");
            ArrayList<String> Repeat = (ArrayList) levl.get("Repeat");
            client.close();
            int k1,k2,k3,k4;
            if(Correct==null)
                k1=0;
            else
                k1=Correct.size();
            if(Wrong==null)
                k2=0;
            else
                k2=Wrong.size();
            if(DontKnow==null)
                k3=0;
            else
                k3=DontKnow.size();
            if(Repeat==null)
                k4=0;
            else
                k4=Repeat.size();
               client.close();
              
               switch (level) {
                 case "level1":
                     jProgressBar4.setValue(k1);
                     break;
                 case "level2":
                     jProgressBar8.setValue(k1);
                     break;
                 case "level3":
                     jProgressBar10.setValue(k1);
                     break;
                 default:
                     jProgressBar9.setValue(k1);
                     break;
             }
              
               switch (Arr) {
                 case "Correct":
                     return k1;
                 case "Wrong":
                     return k2;
                 case "DontKnow":
                     return k3;
                 default:
                     return k4;
             }
             

               
         }catch(UnknownHostException e){
             System.out.println(e);
         }
        return 0;
    }
    
    private int wordsPresent(String cat,String lev){
        int k1 = countWords(cat,lev,"Correct");
        int k2 = countWords(cat,lev,"Wrong");
        int k3 = countWords(cat,lev,"DontKnow");
        int k4 = countWords(cat,lev,"Repeat");
        return k1+k2+k3+k4;
        
    }
     

    
    public class BasicPanel implements MouseListener{
        
        public BasicPanel(){
            //16 19 20 21
         
        }
        @Override
        public void mouseClicked(MouseEvent evt) { }
        @Override
        public void mouseReleased(MouseEvent evt) { }
        @Override
        public void mouseEntered(MouseEvent evt) { }
        @Override
        public void mouseExited(MouseEvent evt) { }
        @Override
        public void mousePressed(MouseEvent evt) {
                jPanel16.addMouseListener(new blevel1Panel());
            int k = countWords("Basic","level1","Correct");
                if(k==10){
                    jPanel19.addMouseListener(new blevel2Panel());  
                }
                k = countWords("Basic","level2","Correct");
                if(k==10){
                    jPanel20.addMouseListener(new blevel3Panel());  
                }
                k = countWords("Basic","level3","Correct");
                if(k==10){
                    jPanel21.addMouseListener(new blevel4Panel());  
                } 
                k=countWords("Basic","level4","Correct");
            jPanel5.setVisible(true);
            jPanel8.setVisible(true);
            jPanel15.setVisible(true);
            jPanel14.setVisible(true);
            jLabel7.setText("Basic Level 1");
            jLabel17.setText("Basic Level 2");
            jLabel21.setText("Basic Level 3");
            jLabel19.setText("Basic Level 4");

            repaint();
            try{
                MongoClient client = new MongoClient();
                DB db = client.getDB("test");
                DBCollection col_user = db.getCollection("users");
                DBCollection col_word = db.getCollection("words");
                if(wordsPresent("Basic","level1")==0){ 
                    BasicDBObject wquery = new BasicDBObject();
                    wquery.put("Type","Basic");
                    DBCursor wcur = col_word.find(wquery);   
                    //Reading words
                    int i=0;
                    String [] wds = new String [10];
                    while(i<10){
                       DBObject wobj = wcur.next();//type basic limit result to 10
                       wds[i] = String.valueOf(wobj.get("word"));
                       //System.out.println(wds[i]);
                       i++;
                    }
                    BasicDBObject sectn = new BasicDBObject("Correct",new String [0])
                            .append("Wrong",new String [0])
                            .append("DontKnow",wds)
                            .append("Repeat",new String [0]);
                    BasicDBObject other = new BasicDBObject("Correct",new String [0])
                            .append("Wrong",new String [0])
                            .append("DontKnow",new String [0])
                            .append("Repeat",new String [0]);
                    BasicDBObject words = new BasicDBObject("level1",sectn)
                            .append("level2",other)
                            .append("level3",other)
                            .append("level4",other);

                    BasicDBObject newobj = new BasicDBObject();
                    newobj.append("$set",new BasicDBObject("Basic", words));

                    BasicDBObject uquery = new BasicDBObject().append("userId", UserId);

                   col_user.update(uquery,newobj);
                   client.close();
                }
                
            }catch(UnknownHostException e){
                
                System.out.println(e);
            }
        }   
        
            public class blevel1Panel implements MouseListener{
                @Override
                public void mouseClicked(MouseEvent evt) { }
                @Override
                public void mouseReleased(MouseEvent evt) { }
                @Override
                public void mouseEntered(MouseEvent evt) { }
                @Override
                public void mouseExited(MouseEvent evt) { }
                @Override
                public void mousePressed(MouseEvent evt) {
                    //page_2.setVisible(false);
                    quest_page ques = new quest_page("Basic","level1");               
                    ques.disp_que();
                    ques.setVisible(true);
                    repaint();
                    //JFrame.setVisible(false);
            try{
                MongoClient client = new MongoClient();
                DB db = client.getDB("test");
                DBCollection col_user = db.getCollection("users");
                DBCollection col_word = db.getCollection("words");
                int k = countWords("Basic","level1","Correct");

                    if(k==0){
                        
                    BasicDBObject wquery = new BasicDBObject();
                    wquery.put("Type","Basic");
                    DBCursor wcur = col_word.find(wquery);   
                    //Reading words
                    int i=0;
                    String [] wds = new String [10];
                    while(i<10){
                        DBObject wobj = wcur.next();
                        i++;
                    }
                    i=0;
                    while(i<10){
                       DBObject wobj = wcur.next();//type basic limit result to 10
                       wds[i] = String.valueOf(wobj.get("word"));
                       //System.out.println(wds[i]);
                       i++;
                    }
                    BasicDBObject uobj = new BasicDBObject();
                    uobj.put("userId",UserId );
                    DBObject uq = col_user.findOne(uobj);
                    BasicDBObject cat = (BasicDBObject)uq.get("Basic");
                    BasicDBObject lev1 = (BasicDBObject)cat.get("level1");
                    BasicDBObject sectn = new BasicDBObject("Correct",new String [0])
                            .append("Wrong",new String [0])
                            .append("DontKnow",wds)
                            .append("Repeat",new String [0]);
                    BasicDBObject other = new BasicDBObject("Correct",new String [0])
                            .append("Wrong",new String [0])
                            .append("DontKnow",new String [0])
                            .append("Repeat",new String [0]);
                    BasicDBObject words = new BasicDBObject("level1",lev1)
                            .append("level2",sectn)
                            .append("level3",other)
                            .append("level4",other);

                    BasicDBObject newobj = new BasicDBObject();
                    newobj.append("$set",new BasicDBObject("Basic", words));

                    BasicDBObject uquery = new BasicDBObject().append("userId", UserId);

                   col_user.update(uquery,newobj);
                   client.close();
                }
                
            }catch(UnknownHostException e){
                System.out.println(e);
            }                   
            }            

        }
            public class blevel2Panel implements MouseListener{
                @Override
                public void mouseClicked(MouseEvent evt) { }
                @Override
                public void mouseReleased(MouseEvent evt) { }
                @Override
                public void mouseEntered(MouseEvent evt) { }
                @Override
                public void mouseExited(MouseEvent evt) { }
                @Override
                public void mousePressed(MouseEvent evt) {
                    
                    quest_page ques = new quest_page("Basic","level2");
                    ques.disp_que();
                    ques.setVisible(true); 
            try{
                MongoClient client = new MongoClient();
                DB db = client.getDB("test");
                DBCollection col_user = db.getCollection("users");
                DBCollection col_word = db.getCollection("words");
                int k = countWords("Basic","level2","Correct");
                if(k==0){
         
             
                    BasicDBObject wquery = new BasicDBObject();
                    wquery.put("Type","Basic");
                    DBCursor wcur = col_word.find(wquery);   
                    //Reading words
                    int i=0;
                    String [] wds = new String [10];
                    while(i<20){
                        DBObject wobj = wcur.next();
                        i++;
                    }
                    i=0;
                    while(i<10){
                       DBObject wobj = wcur.next();//type basic limit result to 10
                       wds[i] = String.valueOf(wobj.get("word"));
                       //System.out.println(wds[i]);
                       i++;
                    }
                    BasicDBObject uobj = new BasicDBObject();
                    uobj.put("userId",UserId );
                    DBObject uq = col_user.findOne(uobj);
                    BasicDBObject cat = (BasicDBObject)uq.get("Basic");
                    BasicDBObject lev1 = (BasicDBObject)cat.get("level1");
                    BasicDBObject lev2 = (BasicDBObject)cat.get("level2");
                    BasicDBObject sectn = new BasicDBObject("Correct",null)
                            .append("Wrong",null)
                            .append("DontKnow",wds)
                            .append("Repeat",null);
                    BasicDBObject other = new BasicDBObject("Correct",null)
                            .append("Wrong",null)
                            .append("DontKnow",null)
                            .append("Repeat",null);
                    BasicDBObject words = new BasicDBObject("level1",lev1)
                            .append("level2",lev2)
                            .append("level3",sectn)
                            .append("level4",other);

                    BasicDBObject newobj = new BasicDBObject();
                    newobj.append("$set",new BasicDBObject("Basic", words));

                    BasicDBObject uquery = new BasicDBObject().append("userId", UserId);

                   col_user.update(uquery,newobj);
                   client.close();
                }
                
            }catch(UnknownHostException e){
                System.out.println(e);
            }                     
            }            
        }
            public class blevel3Panel implements MouseListener{
                @Override
                public void mouseClicked(MouseEvent evt) { }
                @Override
                public void mouseReleased(MouseEvent evt) { }
                @Override
                public void mouseEntered(MouseEvent evt) { }
                @Override
                public void mouseExited(MouseEvent evt) { }
                @Override
                public void mousePressed(MouseEvent evt) {
                    
                    quest_page ques = new quest_page("Basic","level3");
                    ques.disp_que();
                    ques.setVisible(true); 
            try{
                MongoClient client = new MongoClient();
                DB db = client.getDB("test");
                DBCollection col_user = db.getCollection("users");
                DBCollection col_word = db.getCollection("words");
                int k = countWords("Basic","level3","Correct");
                if(k==0){

                    BasicDBObject wquery = new BasicDBObject();
                    wquery.put("Type","Basic");
                    DBCursor wcur = col_word.find(wquery);   
                    //Reading words
                    int i=0;
                    String [] wds = new String [10];
                    while(i<30){
                        DBObject wobj = wcur.next();
                        i++;
                    }
                    i=0;
                    while(i<10){
                       DBObject wobj = wcur.next();//type basic limit result to 10
                       wds[i] = String.valueOf(wobj.get("word"));
                       //System.out.println(wds[i]);
                       i++;
                    }
                    BasicDBObject uobj = new BasicDBObject();
                    uobj.put("userId",UserId );
                    DBObject uq = col_user.findOne(uobj);
                    BasicDBObject cat = (BasicDBObject)uq.get("Basic");
                    BasicDBObject lev1 = (BasicDBObject)cat.get("level1");
                    BasicDBObject lev2 = (BasicDBObject)cat.get("level2"); 
                    BasicDBObject lev3 = (BasicDBObject)cat.get("level3");
                    BasicDBObject sectn = new BasicDBObject("Correct",null)
                            .append("Wrong",null)
                            .append("DontKnow",wds)
                            .append("Repeat",null);
                    BasicDBObject words = new BasicDBObject("level1",lev1)
                            .append("level2",lev2)
                            .append("level3",lev3)
                            .append("level4",sectn);

                    BasicDBObject newobj = new BasicDBObject();
                    newobj.append("$set",new BasicDBObject("Basic", words));

                    BasicDBObject uquery = new BasicDBObject().append("userId", UserId);

                   col_user.update(uquery,newobj);
                   client.close();
                }
                
            }catch(UnknownHostException e){
                System.out.println(e);
            } 
            }            
        }
            public class blevel4Panel implements MouseListener{            
                @Override
                public void mouseClicked(MouseEvent evt) { }
                @Override
                public void mouseReleased(MouseEvent evt) { }
                @Override
                public void mouseEntered(MouseEvent evt) { }
                @Override
                public void mouseExited(MouseEvent evt) { }
                @Override
                public void mousePressed(MouseEvent evt) { 
                    
                    quest_page ques = new quest_page("Basic","level4");
                    ques.disp_que();
                    ques.setVisible(true); 
                }            
        }
       
    }
    
    public class IntermidPanel implements MouseListener{
        public IntermidPanel(){
            //16 19 20 21
         
        }
        @Override
        public void mouseClicked(MouseEvent evt) { }
        @Override
        public void mouseReleased(MouseEvent evt) { }
        @Override
        public void mouseEntered(MouseEvent evt) { }
        @Override
        public void mouseExited(MouseEvent evt) { }
        @Override
        public void mousePressed(MouseEvent evt) {
                jPanel16.addMouseListener(new ilevel1Panel());
            int k = countWords("Intermediate","level1","Correct");
                if(k==10){
                    jPanel19.addMouseListener(new ilevel2Panel());  
                }
                k = countWords("Intermediate","level2","Correct");
                if(k==10){
                    jPanel20.addMouseListener(new ilevel3Panel());  
                }
                k = countWords("Intermediate","level3","Correct");
                if(k==10){
                    jPanel21.addMouseListener(new ilevel4Panel());  
                } 
                countWords("Intermediate","level4","Correct");
            jPanel5.setVisible(true);
            jPanel8.setVisible(true);
            jPanel15.setVisible(true);
            jPanel14.setVisible(true);
            jLabel7.setText("Intermediate Level 1");
            jLabel17.setText("Intermediate Level 2");
            jLabel21.setText("Intermediate Level 3");
            jLabel19.setText("Intermediate Level 4");

            repaint();
            try{
                MongoClient client = new MongoClient();
                DB db = client.getDB("test");
                DBCollection col_user = db.getCollection("users");
                DBCollection col_word = db.getCollection("words");
                if(wordsPresent("Intermediate","level1")==0){ 
                    BasicDBObject wquery = new BasicDBObject();
                    wquery.put("Type","Intermediate");
                    DBCursor wcur = col_word.find(wquery);   
                    //Reading words
                    int i=0;
                    String [] wds = new String [10];
                    while(i<10){
                       DBObject wobj = wcur.next();//type basic limit result to 10
                       wds[i] = String.valueOf(wobj.get("word"));
                       //System.out.println(wds[i]);
                       i++;
                    }
                    BasicDBObject sectn = new BasicDBObject("Correct",new String [0])
                            .append("Wrong",new String [0])
                            .append("DontKnow",wds)
                            .append("Repeat",new String [0]);
                    BasicDBObject other = new BasicDBObject("Correct",new String [0])
                            .append("Wrong",new String [0])
                            .append("DontKnow",new String [0])
                            .append("Repeat",new String [0]);
                    BasicDBObject words = new BasicDBObject("level1",sectn)
                            .append("level2",other)
                            .append("level3",other)
                            .append("level4",other);

                    BasicDBObject newobj = new BasicDBObject();
                    newobj.append("$set",new BasicDBObject("Intermediate", words));

                    BasicDBObject uquery = new BasicDBObject().append("userId", UserId);

                   col_user.update(uquery,newobj);
                   client.close();
                }
                
            }catch(UnknownHostException e){
                
                System.out.println(e);
            }
        }   
        
            public class ilevel1Panel implements MouseListener{
                @Override
                public void mouseClicked(MouseEvent evt) { }
                @Override
                public void mouseReleased(MouseEvent evt) { }
                @Override
                public void mouseEntered(MouseEvent evt) { }
                @Override
                public void mouseExited(MouseEvent evt) { }
                @Override
                public void mousePressed(MouseEvent evt) {
                    //page_2.setVisible(false);
                    quest_page ques = new quest_page("Intermediate","level1");               
                    ques.disp_que();
                    ques.setVisible(true);
                    repaint();
                    //JFrame.setVisible(false);
            try{
                MongoClient client = new MongoClient();
                DB db = client.getDB("test");
                DBCollection col_user = db.getCollection("users");
                DBCollection col_word = db.getCollection("words");
                int k = countWords("Intermediate","level1","Correct");

                    if(k==0){
                        
                    BasicDBObject wquery = new BasicDBObject();
                    wquery.put("Type","Intermediate");
                    DBCursor wcur = col_word.find(wquery);   
                    //Reading words
                    int i=0;
                    String [] wds = new String [10];
                    while(i<10){
                        DBObject wobj = wcur.next();
                        i++;
                    }
                    i=0;
                    while(i<10){
                       DBObject wobj = wcur.next();//type basic limit result to 10
                       wds[i] = String.valueOf(wobj.get("word"));
                       //System.out.println(wds[i]);
                       i++;
                    }
                    BasicDBObject uobj = new BasicDBObject();
                    uobj.put("userId",UserId );
                    DBObject uq = col_user.findOne(uobj);
                    BasicDBObject cat = (BasicDBObject)uq.get("Intermediate");
                    BasicDBObject lev1 = (BasicDBObject)cat.get("level1");
                    BasicDBObject sectn = new BasicDBObject("Correct",new String [0])
                            .append("Wrong",new String [0])
                            .append("DontKnow",wds)
                            .append("Repeat",new String [0]);
                    BasicDBObject other = new BasicDBObject("Correct",new String [0])
                            .append("Wrong",new String [0])
                            .append("DontKnow",new String [0])
                            .append("Repeat",new String [0]);
                    BasicDBObject words = new BasicDBObject("level1",lev1)
                            .append("level2",sectn)
                            .append("level3",other)
                            .append("level4",other);

                    BasicDBObject newobj = new BasicDBObject();
                    newobj.append("$set",new BasicDBObject("Intermediate", words));

                    BasicDBObject uquery = new BasicDBObject().append("userId", UserId);

                   col_user.update(uquery,newobj);
                   client.close();
                }
                
            }catch(UnknownHostException e){
                System.out.println(e);
            }                   
            }            

        }
            public class ilevel2Panel implements MouseListener{
                @Override
                public void mouseClicked(MouseEvent evt) { }
                @Override
                public void mouseReleased(MouseEvent evt) { }
                @Override
                public void mouseEntered(MouseEvent evt) { }
                @Override
                public void mouseExited(MouseEvent evt) { }
                @Override
                public void mousePressed(MouseEvent evt) {
                    
                    quest_page ques = new quest_page("Intermediate","level2");
                    ques.disp_que();
                    ques.setVisible(true); 
            try{
                MongoClient client = new MongoClient();
                DB db = client.getDB("test");
                DBCollection col_user = db.getCollection("users");
                DBCollection col_word = db.getCollection("words");
                int k = countWords("Intermediate","level2","Correct");
                if(k==0){
         
             
                    BasicDBObject wquery = new BasicDBObject();
                    wquery.put("Type","Intermediate");
                    DBCursor wcur = col_word.find(wquery);   
                    //Reading words
                    int i=0;
                    String [] wds = new String [10];
                    while(i<20){
                        DBObject wobj = wcur.next();
                        i++;
                    }
                    i=0;
                    while(i<10){
                       DBObject wobj = wcur.next();//type basic limit result to 10
                       wds[i] = String.valueOf(wobj.get("word"));
                       //System.out.println(wds[i]);
                       i++;
                    }
                    BasicDBObject uobj = new BasicDBObject();
                    uobj.put("userId",UserId );
                    DBObject uq = col_user.findOne(uobj);
                    BasicDBObject cat = (BasicDBObject)uq.get("Intermediate");
                    BasicDBObject lev1 = (BasicDBObject)cat.get("level1");
                    BasicDBObject lev2 = (BasicDBObject)cat.get("level2");
                    BasicDBObject sectn = new BasicDBObject("Correct",null)
                            .append("Wrong",null)
                            .append("DontKnow",wds)
                            .append("Repeat",null);
                    BasicDBObject other = new BasicDBObject("Correct",null)
                            .append("Wrong",null)
                            .append("DontKnow",null)
                            .append("Repeat",null);
                    BasicDBObject words = new BasicDBObject("level1",lev1)
                            .append("level2",lev2)
                            .append("level3",sectn)
                            .append("level4",other);

                    BasicDBObject newobj = new BasicDBObject();
                    newobj.append("$set",new BasicDBObject("Intermediate", words));

                    BasicDBObject uquery = new BasicDBObject().append("userId", UserId);

                   col_user.update(uquery,newobj);
                   client.close();
                }
                
            }catch(UnknownHostException e){
                System.out.println(e);
            }                     
            }            
        }
            public class ilevel3Panel implements MouseListener{
                @Override
                public void mouseClicked(MouseEvent evt) { }
                @Override
                public void mouseReleased(MouseEvent evt) { }
                @Override
                public void mouseEntered(MouseEvent evt) { }
                @Override
                public void mouseExited(MouseEvent evt) { }
                @Override
                public void mousePressed(MouseEvent evt) {
                    
                    quest_page ques = new quest_page("Intermediate","level3");
                    ques.disp_que();
                    ques.setVisible(true); 
            try{
                MongoClient client = new MongoClient();
                DB db = client.getDB("test");
                DBCollection col_user = db.getCollection("users");
                DBCollection col_word = db.getCollection("words");
                int k = countWords("Intermediate","level3","Correct");
                if(k==0){

                    BasicDBObject wquery = new BasicDBObject();
                    wquery.put("Type","Intermediate");
                    DBCursor wcur = col_word.find(wquery);   
                    //Reading words
                    int i=0;
                    String [] wds = new String [10];
                    while(i<30){
                        DBObject wobj = wcur.next();
                        i++;
                    }
                    i=0;
                    while(i<10){
                       DBObject wobj = wcur.next();//type basic limit result to 10
                       wds[i] = String.valueOf(wobj.get("word"));
                       //System.out.println(wds[i]);
                       i++;
                    }
                    BasicDBObject uobj = new BasicDBObject();
                    uobj.put("userId",UserId );
                    DBObject uq = col_user.findOne(uobj);
                    BasicDBObject cat = (BasicDBObject)uq.get("Intermediate");
                    BasicDBObject lev1 = (BasicDBObject)cat.get("level1");
                    BasicDBObject lev2 = (BasicDBObject)cat.get("level2"); 
                    BasicDBObject lev3 = (BasicDBObject)cat.get("level3");
                    BasicDBObject sectn = new BasicDBObject("Correct",null)
                            .append("Wrong",null)
                            .append("DontKnow",wds)
                            .append("Repeat",null);
                    BasicDBObject words = new BasicDBObject("level1",lev1)
                            .append("level2",lev2)
                            .append("level3",lev3)
                            .append("level4",sectn);

                    BasicDBObject newobj = new BasicDBObject();
                    newobj.append("$set",new BasicDBObject("Intermediate", words));

                    BasicDBObject uquery = new BasicDBObject().append("userId", UserId);

                   col_user.update(uquery,newobj);
                   client.close();
                }
                
            }catch(UnknownHostException e){
                System.out.println(e);
            } 
            }            
        }
            public class ilevel4Panel implements MouseListener{            
                @Override
                public void mouseClicked(MouseEvent evt) { }
                @Override
                public void mouseReleased(MouseEvent evt) { }
                @Override
                public void mouseEntered(MouseEvent evt) { }
                @Override
                public void mouseExited(MouseEvent evt) { }
                @Override
                public void mousePressed(MouseEvent evt) { 
                    
                    quest_page ques = new quest_page("Intermediate","level4");
                    ques.disp_que();
                    ques.setVisible(true); 
                }            
        }

    }
    
    public class AdvancedPanel implements MouseListener{
        public AdvancedPanel(){
            //16 19 20 21
         
        }
        @Override
        public void mouseClicked(MouseEvent evt) { }
        @Override
        public void mouseReleased(MouseEvent evt) { }
        @Override
        public void mouseEntered(MouseEvent evt) { }
        @Override
        public void mouseExited(MouseEvent evt) { }
        @Override
        public void mousePressed(MouseEvent evt) {
                jPanel16.addMouseListener(new alevel1Panel());
            int k = countWords("Advanced","level1","Correct");
                if(k==10){
                    jPanel19.addMouseListener(new alevel2Panel());  
                }
                k = countWords("Advanced","level2","Correct");
                if(k==10){
                    jPanel20.addMouseListener(new alevel3Panel());  
                }
                k = countWords("Advanced","level3","Correct");
                if(k==10){
                    jPanel21.addMouseListener(new alevel4Panel());  
                } 
                countWords("Advanced","level4","Correct");
            jPanel5.setVisible(true);
            jPanel8.setVisible(true);
            jPanel15.setVisible(true);
            jPanel14.setVisible(true);
            jLabel7.setText("Advanced Level 1");
            jLabel17.setText("Advanced Level 2");
            jLabel21.setText("Advanced Level 3");
            jLabel19.setText("Advanced Level 4");

            repaint();
            try{
                MongoClient client = new MongoClient();
                DB db = client.getDB("test");
                DBCollection col_user = db.getCollection("users");
                DBCollection col_word = db.getCollection("words");
                if(wordsPresent("Advanced","level1")==0){ 
                    BasicDBObject wquery = new BasicDBObject();
                    wquery.put("Type","Advanced");
                    DBCursor wcur = col_word.find(wquery);   
                    //Reading words
                    int i=0;
                    String [] wds = new String [10];
                    while(i<10){
                       DBObject wobj = wcur.next();//type basic limit result to 10
                       wds[i] = String.valueOf(wobj.get("word"));
                       //System.out.println(wds[i]);
                       i++;
                    }
                    BasicDBObject sectn = new BasicDBObject("Correct",new String [0])
                            .append("Wrong",new String [0])
                            .append("DontKnow",wds)
                            .append("Repeat",new String [0]);
                    BasicDBObject other = new BasicDBObject("Correct",new String [0])
                            .append("Wrong",new String [0])
                            .append("DontKnow",new String [0])
                            .append("Repeat",new String [0]);
                    BasicDBObject words = new BasicDBObject("level1",sectn)
                            .append("level2",other)
                            .append("level3",other)
                            .append("level4",other);

                    BasicDBObject newobj = new BasicDBObject();
                    newobj.append("$set",new BasicDBObject("Advanced", words));

                    BasicDBObject uquery = new BasicDBObject().append("userId", UserId);

                   col_user.update(uquery,newobj);
                   client.close();
                }
                
            }catch(UnknownHostException e){
                
                System.out.println(e);
            }
        }   
        
            public class alevel1Panel implements MouseListener{
                @Override
                public void mouseClicked(MouseEvent evt) { }
                @Override
                public void mouseReleased(MouseEvent evt) { }
                @Override
                public void mouseEntered(MouseEvent evt) { }
                @Override
                public void mouseExited(MouseEvent evt) { }
                @Override
                public void mousePressed(MouseEvent evt) {
                    //page_2.setVisible(false);
                    quest_page ques = new quest_page("Advanced","level1");               
                    ques.disp_que();
                    ques.setVisible(true);
                    repaint();
                    //JFrame.setVisible(false);
            try{
                MongoClient client = new MongoClient();
                DB db = client.getDB("test");
                DBCollection col_user = db.getCollection("users");
                DBCollection col_word = db.getCollection("words");
                int k = countWords("Advanced","level1","Correct");

                    if(k==0){
                        
                    BasicDBObject wquery = new BasicDBObject();
                    wquery.put("Type","Advanced");
                    DBCursor wcur = col_word.find(wquery);   
                    //Reading words
                    int i=0;
                    String [] wds = new String [10];
                    while(i<10){
                        DBObject wobj = wcur.next();
                        i++;
                    }
                    i=0;
                    while(i<10){
                       DBObject wobj = wcur.next();//type basic limit result to 10
                       wds[i] = String.valueOf(wobj.get("word"));
                       //System.out.println(wds[i]);
                       i++;
                    }
                    BasicDBObject uobj = new BasicDBObject();
                    uobj.put("userId",UserId );
                    DBObject uq = col_user.findOne(uobj);
                    BasicDBObject cat = (BasicDBObject)uq.get("Advanced");
                    BasicDBObject lev1 = (BasicDBObject)cat.get("level1");
                    BasicDBObject sectn = new BasicDBObject("Correct",new String [0])
                            .append("Wrong",new String [0])
                            .append("DontKnow",wds)
                            .append("Repeat",new String [0]);
                    BasicDBObject other = new BasicDBObject("Correct",new String [0])
                            .append("Wrong",new String [0])
                            .append("DontKnow",new String [0])
                            .append("Repeat",new String [0]);
                    BasicDBObject words = new BasicDBObject("level1",lev1)
                            .append("level2",sectn)
                            .append("level3",other)
                            .append("level4",other);

                    BasicDBObject newobj = new BasicDBObject();
                    newobj.append("$set",new BasicDBObject("Advanced", words));

                    BasicDBObject uquery = new BasicDBObject().append("userId", UserId);

                   col_user.update(uquery,newobj);
                   client.close();
                }
                
            }catch(UnknownHostException e){
                System.out.println(e);
            }                   
            }            

        }
            public class alevel2Panel implements MouseListener{
                @Override
                public void mouseClicked(MouseEvent evt) { }
                @Override
                public void mouseReleased(MouseEvent evt) { }
                @Override
                public void mouseEntered(MouseEvent evt) { }
                @Override
                public void mouseExited(MouseEvent evt) { }
                @Override
                public void mousePressed(MouseEvent evt) {
                    
                    quest_page ques = new quest_page("Advanced","level2");
                    ques.disp_que();
                    ques.setVisible(true); 
            try{
                MongoClient client = new MongoClient();
                DB db = client.getDB("test");
                DBCollection col_user = db.getCollection("users");
                DBCollection col_word = db.getCollection("words");
                int k = countWords("Advanced","level2","Correct");
                if(k==0){
         
             
                    BasicDBObject wquery = new BasicDBObject();
                    wquery.put("Type","Advanced");
                    DBCursor wcur = col_word.find(wquery);   
                    //Reading words
                    int i=0;
                    String [] wds = new String [10];
                    while(i<20){
                        DBObject wobj = wcur.next();
                        i++;
                    }
                    i=0;
                    while(i<10){
                       DBObject wobj = wcur.next();//type basic limit result to 10
                       wds[i] = String.valueOf(wobj.get("word"));
                       //System.out.println(wds[i]);
                       i++;
                    }
                    BasicDBObject uobj = new BasicDBObject();
                    uobj.put("userId",UserId );
                    DBObject uq = col_user.findOne(uobj);
                    BasicDBObject cat = (BasicDBObject)uq.get("Advanced");
                    BasicDBObject lev1 = (BasicDBObject)cat.get("level1");
                    BasicDBObject lev2 = (BasicDBObject)cat.get("level2");
                    BasicDBObject sectn = new BasicDBObject("Correct",null)
                            .append("Wrong",null)
                            .append("DontKnow",wds)
                            .append("Repeat",null);
                    BasicDBObject other = new BasicDBObject("Correct",null)
                            .append("Wrong",null)
                            .append("DontKnow",null)
                            .append("Repeat",null);
                    BasicDBObject words = new BasicDBObject("level1",lev1)
                            .append("level2",lev2)
                            .append("level3",sectn)
                            .append("level4",other);

                    BasicDBObject newobj = new BasicDBObject();
                    newobj.append("$set",new BasicDBObject("Advanced", words));

                    BasicDBObject uquery = new BasicDBObject().append("userId", UserId);

                   col_user.update(uquery,newobj);
                   client.close();
                }
                
            }catch(UnknownHostException e){
                System.out.println(e);
            }                     
            }            
        }
            public class alevel3Panel implements MouseListener{
                @Override
                public void mouseClicked(MouseEvent evt) { }
                @Override
                public void mouseReleased(MouseEvent evt) { }
                @Override
                public void mouseEntered(MouseEvent evt) { }
                @Override
                public void mouseExited(MouseEvent evt) { }
                @Override
                public void mousePressed(MouseEvent evt) {
                    
                    quest_page ques = new quest_page("Advanced","level3");
                    ques.disp_que();
                    ques.setVisible(true); 
            try{
                MongoClient client = new MongoClient();
                DB db = client.getDB("test");
                DBCollection col_user = db.getCollection("users");
                DBCollection col_word = db.getCollection("words");
                int k = countWords("Advanced","level3","Correct");
                if(k==0){

                    BasicDBObject wquery = new BasicDBObject();
                    wquery.put("Type","Advanced");
                    DBCursor wcur = col_word.find(wquery);   
                    //Reading words
                    int i=0;
                    String [] wds = new String [10];
                    while(i<30){
                        DBObject wobj = wcur.next();
                        i++;
                    }
                    i=0;
                    while(i<10){
                       DBObject wobj = wcur.next();//type basic limit result to 10
                       wds[i] = String.valueOf(wobj.get("word"));
                       //System.out.println(wds[i]);
                       i++;
                    }
                    BasicDBObject uobj = new BasicDBObject();
                    uobj.put("userId",UserId );
                    DBObject uq = col_user.findOne(uobj);
                    BasicDBObject cat = (BasicDBObject)uq.get("Advanced");
                    BasicDBObject lev1 = (BasicDBObject)cat.get("level1");
                    BasicDBObject lev2 = (BasicDBObject)cat.get("level2"); 
                    BasicDBObject lev3 = (BasicDBObject)cat.get("level3");
                    BasicDBObject sectn = new BasicDBObject("Correct",null)
                            .append("Wrong",null)
                            .append("DontKnow",wds)
                            .append("Repeat",null);
                    BasicDBObject words = new BasicDBObject("level1",lev1)
                            .append("level2",lev2)
                            .append("level3",lev3)
                            .append("level4",sectn);

                    BasicDBObject newobj = new BasicDBObject();
                    newobj.append("$set",new BasicDBObject("Advanced", words));

                    BasicDBObject uquery = new BasicDBObject().append("userId", UserId);

                   col_user.update(uquery,newobj);
                   client.close();
                }
                
            }catch(UnknownHostException e){
                System.out.println(e);
            } 
            }            
        }
            public class alevel4Panel implements MouseListener{            
                @Override
                public void mouseClicked(MouseEvent evt) { }
                @Override
                public void mouseReleased(MouseEvent evt) { }
                @Override
                public void mouseEntered(MouseEvent evt) { }
                @Override
                public void mouseExited(MouseEvent evt) { }
                @Override
                public void mousePressed(MouseEvent evt) { 
                    
                    quest_page ques = new quest_page("Advanced","level4");
                    ques.disp_que();
                    ques.setVisible(true); 
                }            
        }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jProgressBar11 = new javax.swing.JProgressBar();
        jLabel23 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jProgressBar2 = new javax.swing.JProgressBar();
        jLabel3 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jProgressBar3 = new javax.swing.JProgressBar();
        jLabel4 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jProgressBar4 = new javax.swing.JProgressBar();
        jLabel7 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jProgressBar8 = new javax.swing.JProgressBar();
        jLabel17 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jProgressBar10 = new javax.swing.JProgressBar();
        jLabel21 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jProgressBar9 = new javax.swing.JProgressBar();
        jLabel19 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("GRE VOCABULARY BUILDER");
        jLabel9.setToolTipText("");
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(139, 1, 252));

        jPanel23.setBackground(new java.awt.Color(220, 220, 220));

        jProgressBar11.setForeground(new java.awt.Color(34, 177, 76));

        jLabel23.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel23.setText("Basic Words");

        jPanel22.setBackground(new java.awt.Color(180, 180, 180));

        jLabel24.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(100, 100, 100));
        jLabel24.setText("Study this section → ");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel24)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel24)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBackground(new java.awt.Color(220, 220, 220));

        jProgressBar2.setForeground(new java.awt.Color(34, 177, 76));

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel3.setText("Intermediate Words");

        jPanel10.setBackground(new java.awt.Color(180, 180, 180));

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(100, 100, 100));
        jLabel5.setText("Study this section →");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4.setBackground(new java.awt.Color(220, 220, 220));

        jProgressBar3.setForeground(new java.awt.Color(34, 177, 76));

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel4.setText("Advanced Words");

        jPanel9.setBackground(new java.awt.Color(180, 180, 180));

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(100, 100, 100));
        jLabel6.setText("Study this section → ");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jProgressBar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.setBackground(new java.awt.Color(220, 220, 220));

        jProgressBar4.setForeground(new java.awt.Color(34, 177, 76));

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel7.setText("Level 1");

        jPanel16.setBackground(new java.awt.Color(180, 180, 180));

        jLabel13.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(100, 100, 100));
        jLabel13.setText("Continue this level → ");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel8.setBackground(new java.awt.Color(220, 220, 220));

        jProgressBar8.setForeground(new java.awt.Color(34, 177, 76));

        jLabel17.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel17.setText("Level 2");

        jPanel19.setBackground(new java.awt.Color(180, 180, 180));

        jLabel18.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(100, 100, 100));
        jLabel18.setText("Continue this level → ");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel18)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(220, 220, 220));

        jProgressBar10.setForeground(new java.awt.Color(34, 177, 76));

        jLabel21.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel21.setText("Leve 3");

        jPanel20.setBackground(new java.awt.Color(180, 180, 180));

        jLabel22.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(100, 100, 100));
        jLabel22.setText("Continue this level → ");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel22)
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel14.setBackground(new java.awt.Color(220, 220, 220));

        jProgressBar9.setForeground(new java.awt.Color(34, 177, 76));

        jLabel19.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel19.setText("Level 4");

        jPanel21.setBackground(new java.awt.Color(180, 180, 180));

        jLabel20.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(100, 100, 100));
        jLabel20.setText("Continue this level → ");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel20)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("VOCABULARY BUILDER");
        jLabel10.setToolTipText("");
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Welcome");

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("jLabel8");

        jButton1.setBackground(new java.awt.Color(181, 230, 29));
        jButton1.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jButton1.setText("Sign Out");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(181, 230, 29));
        jButton2.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jButton2.setText("Refresh");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(181, 230, 29));
        jButton3.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jButton3.setText("Analysis");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(150, 150, 150)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(174, 174, 174)
                        .addComponent(jLabel10))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(80, 80, 80)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setVisible(false);
        out=1;
        basic b= new basic();
        b.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        levlAccess("Basic");
        levlAccess("Intemediate");
        levlAccess("Advanced");
        setVisible(false);
        repaint();
        setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        NewJFrame1 n = new NewJFrame1();
        NewJFrame1.UserId = this.UserId;
        n.countWords("Basic","level1","Corect");
        n.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        
        //22 10 9
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(page_2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new page_2().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JProgressBar jProgressBar10;
    private javax.swing.JProgressBar jProgressBar11;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JProgressBar jProgressBar3;
    private javax.swing.JProgressBar jProgressBar4;
    private javax.swing.JProgressBar jProgressBar8;
    private javax.swing.JProgressBar jProgressBar9;
    // End of variables declaration//GEN-END:variables
}
