

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

public class DateAndDay {

    String academicCalendar[][]=new String[220][3];
    String startDate = "";
    String endDate = "";
    Date sDate = null;
    Date eDate = null;


    JLabel acYearLabel,semesterLabel,seniorLabel,seniorStartLabel,seniorEndLabel,juniorLabel,juniorStartLabel,juniorEndLabel;
    JButton generate;
    JTextField acYearTf,seniorStartTf,seniorEndTf,juniorStartTf,juniorEndTf;
    JRadioButton oddRb,evenRb;

    String acYear="",semester="",seniorStart="",seniorEnd="",juniorStart="",juniorEnd="";
    static boolean completedEntering=false;

    String internalSeniors[]=new String[5];
    String internalJuniors[]=new String[5];

    String govtHoliday2017[][]={
            {"01/JAN/2017","New Year's Day" },
            {"14/JAN/2017","Pongal"},
            {"15/JAN/2017","Thiruvalluvar Day"},
            {"16/JAN/2017","Uzhavar THirunal"},
            {"26/JAN/2017","Republic Day"},
            {"29/MAR/2017","Telugu New Year"},
            {"09/APR/2017","Mahaveer Jayanthi"},
            {"14/APR/2017","Tamil New Year"},
            {"01/MAY/2017","May Day"},
            {"26/JUN/2017","Ramzan"},
            {"14/AUG/2017","Krishna Jayanthi"},
            {"15/AUG/2017","Independence Day"},
            {"25/AUG/2017","Vinayaka Chaturthi"},
            {"02/SEP/2017","Bakrid"},
            {"29/SEP/2017","Ayutha Pooja"},
            {"30/SEP/2017","Vijaya Dasami"},
            {"01/OCT/2017","Muharram"},
            {"02/OCT/2017","Gandhi Jayanthi"},
            {"18/OCT/2017","Deepavali"},
            {"01/DEC/2017","Milad-Un-Nabi"},
            {"25/DEC/2017","Christmas"}
    };

    String govtHoliday2018[][]={
            {"01/JAN/2018","New Year's Day" },
            {"14/JAN/2018","Pongal"},
            {"15/JAN/2018","Thiruvalluvar Day"},
            {"16/JAN/2018","Uzhavar THirunal"},
            {"26/JAN/2018","Republic Day"},
            {"18/MAR/2018","Telugu New Year"},
            {"29/MAR/2018","Mahaveer Jayanthi"},
            {"30/MAR/2018","Good Friday"},
            {"14/APR/2018","Tamil New Year"},
            {"01/MAY/2018","May Day"},
            {"15/JUN/2018","Ramzan"},
            {"03/SEP/2018","Krishna Jayanthi"},
            {"15/AUG/2018","Independence Day"},
            {"13/SEP/2018","Vinayaka Chaturthi"},
            {"22/AUG/2018","Bakrid"},
            {"18/OCT/2018","Ayutha Pooja"},
            {"19/OCT/2018","Vijaya Dasami"},
            {"20/SEP/2018","Muharram"},
            {"02/OCT/2018","Gandhi Jayanthi"},
            {"06/NOV/2018","Deepavali"},
            {"21/NOV/2018","Milad-Un-Nabi"},
            {"25/DEC/2018","Christmas"}

    };

    String ropenDateSeniors="";
    String lastInstDaySeniors="";
    String ropenDateJuniors="";
    String lastInstDayJuniors="";
    String year="",fileName="";


    String flagDateThirdInternal="";
    String flagDateSecondInternal="";
    String flagDateFirstInternal="";

    public void executeBackground(){

        System.out.println("\nI have started!");
        assignObtained();
        findYear();
        defineStartAndEnd();
        datePrinter();
        makeSunHoliday();
        makeSatHoliday();
        makeHolidays();
        assingReopenDay();
        assignLastInstructionalDay();
        assignInternalsSeniors();
        assignInternalJuniors();
        assignFeedbackSessionsSenior();
        assignFeedbackSessionsJunior();
        assignCCMSeniors();
        assignCCMJuniors();
        FileHandling();
        launch();
        display();
    }
    public void assignObtained(){
        //Gui obj=new Gui();
        ropenDateSeniors=seniorStart;
        lastInstDaySeniors=seniorEnd;
        ropenDateJuniors=juniorStart;
        lastInstDayJuniors=juniorEnd;
        System.out.println(ropenDateSeniors+"\n"+lastInstDaySeniors+"\n"+ropenDateJuniors+"\n"+lastInstDayJuniors);
    }

    public void findYear(){
        if(semester.equals("Odd")){
            year= acYear.substring(0,4);
            System.out.println("\nYear we are working with: "+year);
        }

        if(semester.equals("Even")){
            year=acYear.substring(5,acYear.length());
            System.out.println("\nYear we are working with: "+year);
        }
    }

    void defineStartAndEnd(){
        if(semester.equals("Odd")){

            startDate="01/Jun/"+year;
            endDate="31/Dec/"+year;
            System.out.println("\nStart Date: "+startDate+"\nEnd Date: "+endDate);
        }
        else{

            String yr="";
            int y= Integer.parseInt(year);
            --y;
            yr=String.valueOf(y);
            startDate="01/Dec/"+yr;
            endDate="30/Jun/"+year;
            System.out.println("\nStart Date: "+startDate+"\nEnd Date: "+endDate);
        }
    }

    public  void datePrinter() {

        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MMM/yyyy");
        SimpleDateFormat formatDay = new SimpleDateFormat("EEEE");



        try {
            sDate = formatDate.parse(startDate);
            eDate = formatDate.parse(endDate);
        } catch (ParseException e) {
            System.out.println(e);
        }

        Calendar startInstance = Calendar.getInstance();
        Calendar endInstance = Calendar.getInstance();

        startInstance.setTime(sDate);
        endInstance.setTime(eDate);
        endInstance.add(Calendar.DATE,1);

        String date="",day="";
        int i=0;

            while (!startInstance.equals(endInstance)) {

                date = formatDate.format(startInstance.getTime());
                day = formatDay.format(startInstance.getTime());
                academicCalendar[i][0]=date;
                academicCalendar[i][1]=day;
                academicCalendar[i][2]=" ";
            //    System.out.println(date + "         " + day);
                startInstance.add(Calendar.DATE, 1);
                ++i;
            }


    }
    public void makeSunHoliday(){
        for(int i=0;academicCalendar[i][0]!=null;++i){
           if(academicCalendar[i][1].equals("Sunday")){
               academicCalendar[i][2]="Holiday";
           }
        }
    }

    public  int getMonthsDifference(Date date1, Date date2) {
        int m1 = date1.getYear() * 12 + date1.getMonth();
        int m2 = date2.getYear() * 12 + date2.getMonth();
        return m2 - m1 + 1;
    }



    public void makeSatHoliday(){
        Date dt=null;
        int months=getMonthsDifference(sDate,eDate);
        for(int i=0;i<months;++i){

            boolean prev=false;
            Calendar instance= Calendar.getInstance();
            instance.setTime(sDate);
            instance.add(Calendar.MONTH,i);
            dt=instance.getTime();
            String curMonth=dt.toString().substring(4,7);

            for(int j=0;academicCalendar[j][0]!=null;++j){
                if  ((academicCalendar[j][0].substring(3,6)).equals(curMonth)  ){

                    if(academicCalendar[j][1].equals("Saturday")&& !prev ){
                        academicCalendar[j][2]="Institutional Holiday";
                        prev=true;

                    }
                    else if(academicCalendar[j][1].equals("Saturday") && prev){
                        prev=false;

                    }


                }

            }


        }

    }

    public void makeHolidays(){
       // String curYear=startDate.substring(7,11);

        if(year.equals("2017")){


            for(int i=0; academicCalendar[i][0]!=null;++i){

                for(int j=0;j<govtHoliday2017.length;++j){
                   // System.out.println("\nCheck");
                    if(  academicCalendar[i][0].toUpperCase().equals(govtHoliday2017[j][0])  ){
                        academicCalendar[i][2]=govtHoliday2017[j][1];
                       // System.out.println(academicCalendar[i][2]);
                    }
                }
            }
        }
        if(year.equals("2018")){
            for(int i=0; academicCalendar[i][0]!=null;++i){

                for(int j=0;j<govtHoliday2018.length;++j){

                    if(  academicCalendar[i][0].toUpperCase().equals(govtHoliday2018[j][0])  ){
                        academicCalendar[i][2]=govtHoliday2018[j][1];
                    }
                }
            }

        }
    }

    public void assingReopenDay(){

        //For Seniors

        for(int i=0; academicCalendar[i][0]!=null;++i){

            if(academicCalendar[i][0].equals(ropenDateSeniors)){

                academicCalendar[i][2]="College Reopens for Seniors";
            }

        }

        //For Juniors

        for(int i=0; academicCalendar[i][0]!=null;++i){

            if(academicCalendar[i][0].equals(ropenDateJuniors)){

                academicCalendar[i][2]="College Reopens for Juniors";
            }

        }

    }

    public void assignLastInstructionalDay(){

        //For Seniors

        for(int i=0; academicCalendar[i][0]!=null;++i){

            if(academicCalendar[i][0].equals(lastInstDaySeniors)){

                academicCalendar[i][2]="Last Instructional Day for Seniors";
            }

        }

        //For Juniors

        for(int i=0; academicCalendar[i][0]!=null;++i){

            if(academicCalendar[i][0].equals(lastInstDayJuniors)){

                 academicCalendar[i][2]=" Last Instructional Day for Juniors";
            }

        }
    }

    public void assignInternalsSeniorsOdd(){

        //For Seniors

        int workingDays=0,workingFraction=1,startDay=0;

        for(int i=0; academicCalendar[i][0]!=null; ++i){

            if((academicCalendar[i][0].equals(ropenDateSeniors))){
                break;

            }
            ++startDay;

        }
      //  System.out.println("\nStart Day: "+startDay);

        for(int i=startDay;!(academicCalendar[i][0].equals(lastInstDaySeniors));++i){

            if(  academicCalendar[i][2].equals(" ") ){
                ++workingDays;
            }
        }
     //   System.out.println("\nWorking days: "+workingDays);
       // workingDays+=5;

        //------------------------------------------------------------------------------------------


            //Customize working days


        //--------------------------------------------------------------------------------------------
        workingFraction=workingDays/4;
        //System.out.println("\nWorking fraction: "+workingFraction);
        for(int i=1;i<=5;++i){
            boolean assigned=false;
            for(int j=startDay+(workingFraction*i); academicCalendar[j][0]!=null;++j){

                if((academicCalendar[j][1].equals("Monday") || academicCalendar[j][1].equals("Thursday"))&& !assigned && academicCalendar[j][2].equals(" ")) {

                    academicCalendar[j][2] = " Internal Test " + i + " - Seniors";
                    assigned = true;
                    internalSeniors[i-1]=academicCalendar[j][0];

                    if ((i == 3) || (i == 4) || (i == 5)) {

                        academicCalendar[j][2] += " &\n\t\t\t\t\t\t\t\t\t Internal Test " + (i - 2) + "- Juniors";

                    }
                    if(i==3){
                        flagDateFirstInternal=academicCalendar[j][0];
                        internalJuniors[i-3]=academicCalendar[j][0];
                    }
                    if(i==4){
                        flagDateSecondInternal=academicCalendar[j][0];
                        internalJuniors[i-3]=academicCalendar[j][0];
                    }
                    if(i==5){
                        flagDateThirdInternal=academicCalendar[j][0];
                        internalJuniors[i-3]=academicCalendar[j][0];
                    }
                }
            }
        }


    }

    public void assignInternalsSeniorsEven(){

        int workingDays=0,workingFraction=1,startDay=0;

        for(int i=0; academicCalendar[i][0]!=null; ++i){

            if((academicCalendar[i][0].equals(ropenDateSeniors))){
                break;

            }
            ++startDay;

        }


        for(int i=startDay;!(academicCalendar[i][0].equals(lastInstDaySeniors));++i){

            if(  academicCalendar[i][2].equals(" ") ){
                ++workingDays;
            }
        }
        //   System.out.println("\nWorking days: "+workingDays);
        // workingDays+=5;

        //------------------------------------------------------------------------------------------


        //Customize working days


        //--------------------------------------------------------------------------------------------
        workingFraction=workingDays/4;
        //System.out.println("\nWorking fraction: "+workingFraction);
        for(int i=1;i<=5;++i){
            boolean assigned=false;
            for(int j=startDay+(workingFraction*i); academicCalendar[j][0]!=null;++j){

                if((academicCalendar[j][1].equals("Monday") || academicCalendar[j][1].equals("Thursday"))&& !assigned && academicCalendar[j][2].equals(" ")) {

                    academicCalendar[j][2] = " Internal Test " + i + " - Seniors";
                    internalSeniors[i-1]=academicCalendar[j][0];
                    assigned = true;



                }
            }
        }
    }

    public void assignInternalJuniorsOdd(){

        int start=0,workingDays=0,workingFraction=1,i;
      //  System.out.println("\nSecond: "+flagDateSecondInternal);
       // System.out.println("\nFlag: "+flagDateThirdInternal);

        for(i=0;!academicCalendar[i][0].equals(flagDateThirdInternal);++i){

        }

        start=i;
      //  System.out.println("\nStart day: "+start);
        for(int j=start;!(academicCalendar[j][0].equals(lastInstDayJuniors));++j){
            ++workingDays;
        }
        System.out.println("\nworking Days:"+workingDays);

        //-------------------------------------------------------------------


                //Customize working days

        //----------------------------------------------------------------------

        workingFraction=workingDays/3;
      //  System.out.println("\nWorking Fraction : "+workingFraction);

        for(int j=1;j<=2;++j){
            boolean assigned=false;

            for(int k=start+(workingFraction*j); academicCalendar[k][0]!=null;++k){

                if((academicCalendar[k][1].equals("Monday") || academicCalendar[k][1].equals("Thursday"))&& !assigned && academicCalendar[k][2].equals(" ") ){

                    academicCalendar[k][2]="Internal Test "+(j+3)+" -Juniors";
                    internalJuniors[j+2]=academicCalendar[k][0];
                    assigned=true;

                }

            }

        }
    }

    public void assignInternalJuniorsEven(){


        for(int i=0,ptr=1; academicCalendar[i][0]!=null && ptr<5;++i){

            if(academicCalendar[i][0].equals(internalSeniors[ptr])){

                academicCalendar[i][2] +=" &\n\t\t\t\t\t\t\t\t\t Internal Test " + (ptr) + "- Juniors";
                internalJuniors[ptr-1]=academicCalendar[i][0];
                ++ptr;
            }
        }

        int j;
        for(j=0;!academicCalendar[j][0].equals(lastInstDayJuniors);++j){

        }

        j-=6;
        for(;!academicCalendar[j][0].equals(internalJuniors[3]);--j){
            if( academicCalendar[j][2].equals(" ")  ){
                academicCalendar[j][2]=" Internal Test 5 - Juniors";
                internalJuniors[4]=academicCalendar[j][0];
                break;
            }
        }

    }

    public void assignInternalsSeniors(){
        if(semester.equals("Odd")){
            assignInternalsSeniorsOdd();
        }

        else{
            assignInternalsSeniorsEven();
        }
    }

    public void assignInternalJuniors(){
        if(semester.equals("Odd")){
            assignInternalJuniorsOdd();
        }
        else{
            assignInternalJuniorsEven();
        }
    }

    public void assignFeedbackSessionsSenior(){

        //For Seniors
        int count;
        for(count=0; !academicCalendar[count][0].equals(internalSeniors[1]);++count ){

        }
       // System.out.println("\nCount: "+count);

        for(int i=count-1; !academicCalendar[i][0].equals(internalSeniors[0]);--i){
            boolean completed =false;

            if( academicCalendar[i][2].equals(" ") && !completed ){
                academicCalendar[i][2]="Mid Semester Feedback - Seniors";
                completed=true;
                break;
            }

        }

        for(count=0; !academicCalendar[count][0].equals(internalSeniors[4]);++count ){

        }

        for(int i=count-1; !academicCalendar[i][0].equals(ropenDateSeniors);--i){
            boolean completed=false;

            if( academicCalendar[i][2].equals(" ") && !completed){
                academicCalendar[i][2]="End Semester Feedback - Seniors";
                completed=true;
                break;
            }

        }

    }

    public void assignFeedbackSessionsJunior(){
        int count;
        for(count=0; !academicCalendar[count][0].equals(internalJuniors[1]);++count ){

        }

        for(int i=count-1; !academicCalendar[i][0].equals(ropenDateJuniors);--i){

            boolean completed=false;

            if( academicCalendar[i][2].equals(" ") && !completed ){
                academicCalendar[i][2]="Mid Semester Feedback - Juniors";
                completed=true;
                break;
            }

        }

        for(count=0; !academicCalendar[count][0].equals(internalJuniors[4]);++count ){

        }

        for(int i=count-1; !academicCalendar[i][0].equals(ropenDateJuniors);--i){

            boolean completed=false;
            if( academicCalendar[i][2].equals(" ") && !completed){
                academicCalendar[i][2]="End Semester Feedback - Juniors";
                completed=true;
                break;
            }

        }


    }

    public void assignCCMSeniors(){

        //For I ccm
        int count;
        for(count=0;!academicCalendar[count][0].equals(internalSeniors[0]);++count){

        }

        for(int i=count-6; !academicCalendar[i][0].equals(ropenDateSeniors);--i){
            boolean completed=false;

            if( academicCalendar[i][2].equals(" ") && !completed){

                academicCalendar[i][2]="I CCM for Seniors";
                completed=true;
                break;
            }
        }

        //For II ccm -seniors
        for(count=0;!academicCalendar[count][0].equals(internalSeniors[2]);++count){

        }

        for(int i=count-6; !academicCalendar[i][0].equals(ropenDateJuniors);--i){
            boolean completed=false;

            if( academicCalendar[i][2].equals(" ") && !completed){

                academicCalendar[i][2]="II CCM for Seniors";
                completed=true;
                break;
            }
        }

        //For III ccm -seniors
        for(count=0;!academicCalendar[count][0].equals(internalSeniors[4]);++count){

        }

        for(int i=count-6; !academicCalendar[i][0].equals(ropenDateJuniors);--i){
            boolean completed=false;

            if( academicCalendar[i][2].equals(" ") && !completed){

                academicCalendar[i][2]="III CCM for Senior";
                completed=true;
                break;
            }
        }



    }



    public void assignCCMJuniors(){

        //For I ccm
        int count;
        for(count=0;!academicCalendar[count][0].equals(internalJuniors[0]);++count){

        }

        for(int i=count-6; !academicCalendar[i][0].equals(ropenDateSeniors);--i){
            boolean completed=false;

            if( academicCalendar[i][2].equals(" ") && !completed){

                academicCalendar[i][2]="I CCM for Juniors";
                completed=true;
                break;
            }
        }

        //For II ccm -Juniors
        for(count=0;!academicCalendar[count][0].equals(internalJuniors[2]);++count){

        }

        for(int i=count-6; !academicCalendar[i][0].equals(ropenDateJuniors);--i){
            boolean completed=false;

            if( academicCalendar[i][2].equals(" ") && !completed){

                academicCalendar[i][2]="II CCM for Juniors";
                completed=true;
                break;
            }
        }

        //For III ccm -Juniors
        for(count=0;!academicCalendar[count][0].equals(internalJuniors[4]);++count){

        }

        for(int i=count-6; !academicCalendar[i][0].equals(ropenDateJuniors);--i){
            boolean completed=false;

            if( academicCalendar[i][2].equals(" ") && !completed){

                academicCalendar[i][2]="III CCM for Juniors";
                completed=true;
                break;
            }
        }


    }


    public  void display(){

        System.out.println("Date\t\t\t\tDay\t\t\t\tEvent");
        System.out.println("----------------------------------------------------------------");
        for(int i=0;academicCalendar[i][0]!=null;++i){
            for(int j=0;j<3;++j){
                System.out.print(academicCalendar[i][j]+"\t\t\t");
            }
            System.out.println("\n----------------------------------------------------------------");
        }
    }

    public void FileHandling(){

        try {

            fileName= "Academic_Calendar_for_"+acYear+"_"+semester+"_Semester";
            //fileName="\""+fn+"\"";
            System.out.println("\nFileName: "+fileName);
            FileWriter output = new FileWriter("//home//abisek//Desktop//"+fileName+".docx");
            output.write(String.format("%-15s %-15s %-100s\n----------------------------------------------------------------\n","Date","Day","Event"));
            for(int i=0;academicCalendar[i][0]!=null;++i){

                output.write(String.format("%-15s %-15s %-100s\n----------------------------------------------------------------\n",academicCalendar[i][0],academicCalendar[i][1],academicCalendar[i][2]));

            }
            output.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void launch(){
        try{

            Runtime runtime=Runtime.getRuntime();
            Process p= runtime.exec("nemo //home//abisek//Desktop//"+fileName);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }



    DateAndDay(){



        acYearLabel=new JLabel("Academic Year :");
        // acYearLabel.setToolTipText("YYYY-YYYY");
        acYearLabel.setBounds(400,200,400,25);

        acYearTf=new JTextField();
        acYearTf.setBounds(800,200,400,25);
        acYearTf.setToolTipText("YYYY-YYYY");

        semesterLabel=new JLabel("Semester:");
        semesterLabel.setBounds(400,240,400,25);

        oddRb=new JRadioButton("Odd");
        oddRb.setBounds(800,240,400,20);
        evenRb=new JRadioButton("Even");
        evenRb.setBounds(800,260,400,20);
        ButtonGroup group= new ButtonGroup();
        group.add(oddRb);
        group.add(evenRb);

        seniorLabel = new JLabel("Seniors:");
        seniorLabel.setBounds(400,280,400,25);

        seniorStartLabel=new JLabel("Reopening Date: ");
        seniorStartLabel.setBounds(500,310,300,25);

        seniorStartTf=new JTextField();
        seniorStartTf.setToolTipText("DD/MMM/YYYY");
        seniorStartTf.setBounds(800,310,400,25);

        seniorEndLabel=new JLabel("Last Instructional Day: ");
        seniorEndLabel.setBounds(500,340,300,25);

        seniorEndTf=new JTextField();
        seniorEndTf.setToolTipText("DD/MMM/YYYY");
        seniorEndTf.setBounds(800,340,400,25);

        juniorLabel=new JLabel("I Years:");
        juniorLabel.setBounds(400,370,400,25);

        juniorStartLabel=new JLabel("Reopening Date: ");
        juniorStartLabel.setBounds(500,400,300,25);

        juniorStartTf=new JTextField();
        juniorStartTf.setToolTipText("DD/MMM/YYYY");
        juniorStartTf.setBounds(800,400,400,25);

        juniorEndLabel=new JLabel("Last Instructional Day: ");
        juniorEndLabel.setBounds(500,430,300,25);

        juniorEndTf=new JTextField();
        juniorEndTf.setToolTipText("DD/MMM/YYYY");
        juniorEndTf.setBounds(800,430,400,25);

        JButton generate= new JButton("Generate");
        generate.setBounds(600,600,200,20);
        generate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(oddRb.isSelected()){
                    // System.out.println("\nYou Chose Odd sem");
                    semester="Odd";
                }

                if(evenRb.isSelected()){
                    //  System.out.println("\nYou chose Even sem");
                    semester="Even";
                }

                acYear=acYearTf.getText();
                System.out.println(acYear);

                seniorStart= seniorStartTf.getText();
                System.out.println("\nsr. rD: "+seniorStart);

                seniorEnd=seniorEndTf.getText();
                System.out.println("\nSr. lD: "+seniorEnd);

                juniorStart=juniorStartTf.getText();
                System.out.println("\nJr. rD: "+juniorStart);

                juniorEnd=juniorEndTf.getText();
                System.out.println("\nJr. lD: "+juniorEnd);
                completedEntering=true;
                System.out.println("\nChanged to "+completedEntering);

            }
        });


        JFrame frame= new JFrame("Academic Calendar Generator ");

        frame.add(acYearLabel);
        frame.add(acYearTf);
        frame.add(semesterLabel);
        frame.add(oddRb);
        frame.add(evenRb);
        frame.add(seniorLabel);
        frame.add(seniorStartLabel);
        frame.add(seniorStartTf);
        frame.add(seniorEndLabel);
        frame.add(seniorEndTf);
        frame.add(juniorLabel);
        frame.add(juniorStartLabel);
        frame.add(juniorEndLabel);
        frame.add(juniorStartTf);
        frame.add(juniorEndTf);
        frame.add(generate);
        frame.setSize(1500,1000);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);





        //executeBackground();


    }




   public static void main(String args[]){

       System.out.println("Status: "+completedEntering);
       DateAndDay ob=new DateAndDay();
       while (true ){
           try {
               Thread.sleep(1000);

           }
           catch (Exception e){
               System.out.println(e);
           }
           if(completedEntering)
               break;

       }

       System.out.println("\nChecck: "+completedEntering);
       ob.executeBackground();

        //new Gui();
    }

}

